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
import com.rss.steel_production.process.model.IronInfo;
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

	private String createChargeNo(String deviceNo, String deviceSign) {
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("deviceNo", deviceNo);
		param.put("deviceSign", deviceSign);
		List<Map<String,String>> maxChargeNos = sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getSignNum",param);
		
		String result = maxChargeNos.get(0).get("signNum");

		sqlSession.update("com.rss.steel_production.schedule.dao.SteelScheduleDao.updateSignNum",param);
		return result;
	}
	@Override
	public void autoCreate(IronInfo ironInfo) throws ParseException {
		
		List<SteelSchedule> steelSchedules = new ArrayList<SteelSchedule>();
		String bofNum = "";
		String maxChargeNo = "";
		
		Date tempStartDate = ironInfo.getStartTime()==null?new Date():ironInfo.getStartTime();
		// ???????????????
		SimpleDateFormat sdfS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// ????????? ??? ?????????
		String castNo = ironInfo.getCastNo();
		
		// ?????????????????????
		Map<String,Object> parameters=new java.util.HashMap<>();
        parameters.put("castNo",castNo);
        CastPlan castPlan = (CastPlan) sqlSession.selectList("com.rss.steel_production.schedule.dao.CastPlanDao.selectByChargeNo", parameters).get(0);
    	// ????????????
		String steelGrade = castPlan.getSteelGrade();
		// ????????????
		String productSpec = castPlan.getProductSpec();
		// ????????????
		String ccDevice = castPlan.getCcDevice();
		
		// ????????????
		Map<String,Object> parametersSteelProeuct=new java.util.HashMap<>();
		parametersSteelProeuct.put("productSpec",productSpec);
		parametersSteelProeuct.put("steelGrade",steelGrade);
		List<SteelProduct> steelProduct = sqlSession.selectList("com.rss.steel_production.foundation.dao.SteelProductDao.getSteelProductRouter", parametersSteelProeuct);
		String routers = steelProduct.get(0).getProcessRoute();
		String[] stationNames = routers.split("_");
		
		//??????????????????????????????
		List<Map<String,Object>> scheduleExitGroup = sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getScheduleExitGroupByStation");
		Map<String,Object> planedScheduleMap = new HashMap<>();
		for(Map<String,Object> stationSchedule : scheduleExitGroup) {
			planedScheduleMap.put(stationSchedule.get("stationName").toString(), stationSchedule.get("exitTime"));
		}
		
		// ???????????????????????????
		parameters = new java.util.HashMap<>();
        parameters.put("deviceStatus","??????");
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
		
		// ???????????????????????????
		List<ProcessStandard> processStandards = sqlSession.selectList("com.rss.steel_production.foundation.dao.ProcessStandardDao.getAllProcessStandard");
		Map<String, ProcessStandard> processStandasMap = new HashMap<>();
		for (ProcessStandard processStandard : processStandards) {
			String key = processStandard.getItemID();
			processStandasMap.put(key, processStandard);
		}
		
		// ???????????????????????????????????????
		for(int i = 0; i<stationNames.length; i++) {
			String stationName = stationNames[i];
			
			double totalMinutes = 0.0;
			
			// ???????????????????????? ??? ???????????? + ????????????????????? + ??????????????????
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
			
			
			// ?????????????????????
			List<String> stations = deviceMap.get(stationName);
			
			// ????????????????????????????????????
			String checkStation = "";
			
			Date tempStationEndDate = null;
			
			// ???????????????????????? ?????????????????????????????????????????????
			if(tempStartDate==null) {
				tempStartDate = sdfS.parse(planedScheduleMap.get(stationName).toString());
			}
			
			// ?????????????????????????????????????????????
			if(stationName.equals(ccDevice.split("#")[1])) {
				checkStation = ccDevice;
				Date standExitDate = planedScheduleMap.get(checkStation)==null? tempStartDate: sdfS.parse(planedScheduleMap.get(checkStation).toString());
				tempStationEndDate = standExitDate;
			}else {
				for(String station : stations) {
					
					Date standExitDate = planedScheduleMap.get(station)==null? tempStartDate: sdfS.parse(planedScheduleMap.get(station).toString());
					
					// ???????????? - ????????????
					if("".equals(checkStation) || tempStationEndDate.after(standExitDate)) {
						tempStationEndDate = standExitDate;
						checkStation = station;
					}
				}
			}
					
			// ?????????????????????????????????
			
			if(tempStationEndDate.after(tempStartDate)) {
				tempStartDate = tempStationEndDate;
			}
			
		    LocalDateTime localDateTime = LocalDateTime.ofInstant(tempStartDate.toInstant(), ZoneId.systemDefault());
		    Long totalLongMinutes = (long) totalMinutes;
		    LocalDateTime currentDateTime = localDateTime.plusMinutes(totalLongMinutes);
			
			SteelSchedule steelSchedule = new SteelSchedule();
			steelSchedule.setSteel_scheduleUID(UUIDGenerator.generate());
			steelSchedule.setCastNo(castNo);
			steelSchedule.setIronNo(ironInfo.getBlastOrder());
			steelSchedule.setStationName(checkStation);
			steelSchedule.setPlanEnter(tempStartDate);
			
			Date exitDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
			steelSchedule.setPlanExit(exitDate);
			steelSchedule.setPlanStatus("1");
			
			tempStartDate = exitDate;
			
			if(checkStation.contains("BOF")) {
				bofNum = checkStation.split("#")[0];
			}
			
			steelSchedules.add(steelSchedule);
			
		}
		
		// ???????????? ???????????????????????????
		SteelSchedule steelSchedule = new SteelSchedule();
		steelSchedule.setSteel_scheduleUID(UUIDGenerator.generate());
		steelSchedule.setCastNo(castNo);
		steelSchedule.setIronNo(ironInfo.getBlastOrder());
		steelSchedule.setStationName("SA");
		steelSchedule.setPlanEnter(tempStartDate);
		steelSchedule.setActualEnter(tempStartDate);
		steelSchedule.setActualExit(ironInfo.getStopTime()==null?new Date():ironInfo.getStopTime());
		steelSchedule.setPlanExit(ironInfo.getStopTime()==null?new Date():ironInfo.getStopTime());
		steelSchedule.setPlanStatus("3");
		steelSchedule.setWeight(ironInfo.getNetWeight());
		steelSchedule.setTemperature(ironInfo.getExitTemperature());
		
		steelSchedules.add(steelSchedule);
		
		// ???????????????????????????
		maxChargeNo = this.createChargeNo(bofNum, "X");
		for(SteelSchedule schedule : steelSchedules) {
			schedule.setChargeNo(maxChargeNo);
			steelScheduleService.insert(schedule);
		}
	}
}