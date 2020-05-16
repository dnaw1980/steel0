package com.rss.steel_production.foundation.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.foundation.model.ProcessStandard;
import com.rss.steel_production.foundation.model.SteelDevice;
import com.rss.steel_production.foundation.model.SteelProduct;
import com.rss.steel_production.foundation.dao.SteelProductDAO;
import com.rss.steel_production.foundation.service.SteelProductService;
import com.rss.steel_production.schedule.model.CastPlan;
import com.rss.steel_production.schedule.model.ChargePlan;
import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.service.ChargePlanService;
import com.rss.steel_production.schedule.service.SteelScheduleService;

import tk.mybatis.mapper.util.StringUtil;

@Service
@Transactional
public class SteelProductImpl extends AbstractService<SteelProduct> implements SteelProductService {
	@Resource
	private SteelProductDAO steelProductDAO;
	@Resource
	private ChargePlanService chargePlanService;
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private SteelScheduleService steelScheduleService;

	private String createChargeNo(String maxChargeNo) {
		String result = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int yearNum = Integer.parseInt(sdf.format(new Date()).substring(2));
		result = yearNum + "";
		if(StringUtil.isEmpty(maxChargeNo)) {
			result += "0001";
		}else {
			int maxChargeNoNum = Integer.parseInt(maxChargeNo.substring(2));
			maxChargeNoNum++;
			result += maxChargeNoNum;
		}
		return result;
	}
	@Override
	public void autoCreate(String ironNo, String startDt) throws ParseException {
		// 开始时间格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse(startDt);
		Date tempStartDate = startDate;
		// 炉次号 同 铁次号
		String castNo = ironNo;
		
		// 取最大的炉次号
		List<String> maxChargeNos = sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getMaxChargeNo");
		String maxChargeNo = "";
		String curreentChargeNo = "";
		if(!maxChargeNos.isEmpty()) {
			curreentChargeNo = maxChargeNos.get(0);
		}
		maxChargeNo = this.createChargeNo(curreentChargeNo);
		// 对应的浇次信息
		Map<String,Object> parameters=new java.util.HashMap<>();
        parameters.put("castNo",castNo);
        CastPlan castPlan = (CastPlan) sqlSession.selectList("com.rss.steel_production.schedule.dao.CastPlanDao.selectByChargeNo", parameters).get(0);
    	// 钢种编号
		String steelGrade = castPlan.getSteelGrade();
		// 产品规格
		String productSpec = castPlan.getProductSpec();
		
		// 工艺路线
		Map<String,Object> parametersSteelProeuct=new java.util.HashMap<>();
		parametersSteelProeuct.put("productSpec",productSpec);
		parametersSteelProeuct.put("steelGrade",steelGrade);
		List<SteelProduct> steelProduct = sqlSession.selectList("com.rss.steel_production.foundation.dao.SteelProductDao.getSteelProductRouter", parametersSteelProeuct);
		String routers = steelProduct.get(0).getProcessRoute();
		String[] stationNames = routers.split("_");
		
		//每个工序相关计划信息
		List<Map<String,Object>> scheduleExitGroup = sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getScheduleExitGroupByStation");
		Map<String,Object> planedScheduleMap = new HashMap<>();
		for(Map<String,Object> stationSchedule : scheduleExitGroup) {
			planedScheduleMap.put(stationSchedule.get("stationName").toString(), stationSchedule.get("exitTime"));
		}
		
		// 当前所有的工位信息
		parameters = new java.util.HashMap<>();
        parameters.put("deviceStatus","空闲");
		List<Map<String,Object>> steelDevices = sqlSession.selectList("com.rss.steel_production.foundation.dao.SteelDeviceDao.getSteelDeviceByStatus",parameters);
		Map<String,List<String>> deviceMap = new HashMap<>();
		for (Map<String,Object> deviceNameMap : steelDevices) {
			String deviceName = deviceNameMap.get("deviceName").toString();
			String key = deviceName.split("#")[1];
			List<String> value = new ArrayList<>(); 
			if(deviceMap.containsKey(key)) {
				value = deviceMap.get(key);
			}
			value.add(deviceName);
			deviceMap.put(key, value);
		}
		
		// 各个工艺的标准信息
		List<ProcessStandard> processStandards = sqlSession.selectList("com.rss.steel_production.foundation.dao.ProcessStandardDao.getAllProcessStandard");
		Map<String, ProcessStandard> processStandasMap = new HashMap<>();
		for (ProcessStandard processStandard : processStandards) {
			String key = processStandard.getItemID();
			processStandasMap.put(key, processStandard);
		}
		
		// 为每个工艺线路分配调度计划
		for(int i = 0; i<stationNames.length; i++) {
			String stationName = stationNames[i];
			
			double totalMinutes = 0.0;
			
			// 工序整体的时间为 ： 等待时间 + 工位间运输时间 + 工序处理时间
			String processStandasProcessTimeKey = stationName + "_" + steelGrade + "_" + productSpec + "_processTime";
			ProcessStandard processStandasProcessTime = processStandasMap.get(processStandasProcessTimeKey);
			totalMinutes += processStandasProcessTime.getStandardValue() + processStandasProcessTime.getUpperLimit();
			
			String processStandasWaitTimeKey = stationName + "_waitTime";
			ProcessStandard processStandasWaitTime = processStandasMap.get(processStandasWaitTimeKey);
			if(processStandasWaitTime!=null) {
				totalMinutes += processStandasWaitTime.getStandardValue() + processStandasWaitTime.getUpperLimit();
			}
			
			if(i>0) {
				String processStandasTransTimeKey = stationNames[i-1] + "_" + stationName + "_transTime";
				ProcessStandard processStandasTransTime = processStandasMap.get(processStandasTransTimeKey);
				if(processStandasTransTime!=null) {
					totalMinutes += processStandasTransTime.getStandardValue() + processStandasTransTime.getUpperLimit();
				}

			}
			
			
			// 看当前相关设备
			List<String> stations = deviceMap.get(stationName);
			
			// 看当前相关设备下预设时间
			String checkStation = "";
			
			Date tempStationEndDate = null;
			
			for(String station : stations) {
				Date standExitDate = planedScheduleMap.get(station)==null? tempStartDate: sdfS.parse(planedScheduleMap.get(station).toString());
				
				// 分配依据 - 尽早完成
				if("".equals(checkStation) || tempStationEndDate.before(standExitDate)) {
					tempStationEndDate = standExitDate;
					checkStation = station;
				}
			}
			
			// 确定挺工位后，分配时间
			
			if(tempStationEndDate.after(tempStartDate)) {
				tempStartDate = tempStationEndDate;
			}
			
		    LocalDateTime localDateTime = LocalDateTime.ofInstant(tempStartDate.toInstant(), ZoneId.systemDefault());
		    Long totalLongMinutes = (long) totalMinutes;
		    LocalDateTime currentDateTime = localDateTime.plusMinutes(totalLongMinutes);
			
			SteelSchedule steelSchedule = new SteelSchedule();
			steelSchedule.setSteel_scheduleUID(UUIDGenerator.generate());
			steelSchedule.setCastNo(castNo);
			steelSchedule.setChargeNo(maxChargeNo);
			steelSchedule.setStationName(checkStation);
			steelSchedule.setPlanEnter(tempStartDate);
			
			Date exitDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
			steelSchedule.setPlanExit(exitDate);
			steelSchedule.setPlanStatus("1");
			
			tempStartDate = exitDate;
			
			steelScheduleService.insert(steelSchedule);
		}
	}
}