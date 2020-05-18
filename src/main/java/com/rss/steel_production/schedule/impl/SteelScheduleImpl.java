package com.rss.steel_production.schedule.impl;

import javax.annotation.Resource;

import com.rss.framework.UUIDGenerator;
import com.rss.framework.iMapper;
import com.rss.steel_production.foundation.model.ProcessStandard;
import com.rss.steel_production.foundation.model.SteelDevice;
import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.foundation.service.SteelDeviceService;
import com.rss.steel_production.process.model.CompositionInfo;
import com.rss.steel_production.process.model.CompositionStandard;
import com.rss.steel_production.process.service.CompositionInfoService;
import com.rss.steel_production.process.service.CompositionStandardService;
import com.rss.steel_production.schedule.model.*;
import com.rss.steel_production.schedule.service.CastPlanService;
import com.rss.steel_production.schedule.service.ChargePlanService;
import com.rss.steel_production.schedule.service.IronPlanService;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.dao.SteelScheduleDAO;
import com.rss.steel_production.schedule.service.SteelScheduleService;

import tk.mybatis.mapper.entity.Condition;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SteelScheduleImpl extends AbstractService<SteelSchedule> implements SteelScheduleService {
	@Resource
    SteelScheduleDAO steelScheduleDAO;
	@Autowired
	private SqlSessionTemplate sqlSession;
    @Autowired
    ChargePlanService chargePlanService;
    @Autowired
    CastPlanService castPlanService;
    @Autowired
    ProcessStandardService processStandardService;
    @Autowired
    SteelDeviceService steelDeviceService;
    @Autowired
    SteelScheduleService steelScheduleService;
    @Autowired
    IronPlanService ironPlanService;
    @Autowired
    CompositionInfoService compositionInfoService;
    @Autowired
    CompositionStandardService compositionStandardService;

    /*
     * 检查
     */
    public boolean checkIfRight(List<SteelSchedule> sslist) {
        List<CastPlan> cplist = new ArrayList<>();
        for (SteelSchedule steelSchedule : sslist) {
            if (castPlanService.getCastByNo(steelSchedule.getCastNo()) == null || chargePlanService.getChargeByNo(steelSchedule.getChargeNo()) == null) {
                return false;
            }
            if (cplist.size() == 0) {
                cplist.add(castPlanService.getCastByNo(steelSchedule.getCastNo()));
            }
        }
        return true;
    }

    /*
     * 添加调度计划（数据库）
     */
    @Override
    public int addSchedule(SteelSchedule ss) {
        return steelScheduleDAO.insert(ss);
    }

    /*
     * 删除指定状态的计划
     */
    @Override
    public void deleteScheduleByStatus(String status) {
        SteelScheduleExample ssExample = new SteelScheduleExample();
        SteelScheduleExample.Criteria cc = ssExample.createCriteria();
        cc.andPlanstatusEqualTo(status);
        steelScheduleDAO.deleteByExample(ssExample);
    }

    /*
     * 获得调度计划列表，通过状态
     */
    @Override
    public List<SteelSchedule> getScheduleListByStatus(String status) {
        SteelScheduleExample ssExample = new SteelScheduleExample();
        SteelScheduleExample.Criteria cc = ssExample.createCriteria();
        cc.andPlanstatusEqualTo(status);
        return steelScheduleDAO.selectByExample(ssExample);
    }

    /*
     * 创建调度计划
     */
    @Override
    public Map<String, List<SteelSchedule>> createSchedule(Date[] dateInterval) {
        //排程map
        Map<String, List<SteelSchedule>> scheduleMap = new LinkedHashMap<>();
        //最早开始任务时间map
        Map<String, Date> earlyMap = getDeviceEarlyAvailableTimeMap(steelDeviceService.getDeviceList());
        //需要排程的炉次数组
        ChargePlan[][] chargeArr = getChargeArr(dateInterval);
        //工艺标准
        Map<String, double[]> limit = getStandard();
        //后延时间变量，单位：分钟
        int delayTime = 0;
        if (chargeArr.length <= 0) {
            return null;
        }
        Map<String, SteelDevice> devicemap = new HashMap<>();
        for (SteelDevice sd : steelDeviceService.getDeviceList()) {
            devicemap.put(sd.getDeviceName(), sd);
        }
        //遍历浇次
        loop1:
        for (int i = 0; i < chargeArr.length; i++) {
            Map<String, List<SteelSchedule>> newScheduleMap = new LinkedHashMap<>();
            //找最早可用,初始为CC
            String dkey = null;
            Date dvalue = null;
            System.out.println("开始遍历浇次");
            for (String key : earlyMap.keySet()) {
                if (key.split("#")[1].equals("CC")) {
                    if (dkey == null) {
                        dkey = key;
                        dvalue = earlyMap.get(key);
                    } else if (earlyMap.get(key).before(dvalue)) {
                        dkey = key;
                        dvalue = earlyMap.get(key);
                    }
                }
            }
            if (dvalue != null) {
                //如果有后延时间，后延
                dvalue = new Date(dvalue.getTime() + delayTime * 60 * 1000L);
            }

            //遍历浇次的炉次
            for (int j = 0; j < chargeArr[i].length; j++) {
                System.out.println("开始遍历炉次");
                int advanceTime = 0;
                String[] route = chargeArr[i][j].getProcessRoute().split("_");
                //遍历炉次的工艺路径,计算提前时间
                for (int r = 0; r < route.length; r++) {
                    int wait, process, trans;
                    wait = (int) limit.get(route[r] + "_waitTime")[0];
                    //20/4/17 修改，加工时间增加规格判断
                    process = (int) limit.get(route[r] + "_" + chargeArr[i][j].getSteelGrade() + "_" + chargeArr[i][j].getProductSpec() + "_processTime")[0];
                    if (r == 0) {
                        trans = (int) limit.get("HD_" + route[r] + "_transTime")[0];
                    } else {
                        trans = (int) limit.get(route[r - 1] + "_" + route[r] + "_transTime")[0];
                    }
                    advanceTime += wait + process + trans;
                    System.out.println("时间遍历中...");
                }
                Date first = null;
                if (dvalue != null) {
                    //要求出铁时间在此first时间之前，最早不早过标准限制，即first为炉次实际从高炉出发运送开始时间
                    first = new Date(dvalue.getTime() - advanceTime * 60 * 1000L);
                }
                System.out.println("first:" + dateToString(first));
                System.out.println("dvalue:" + dvalue);
                System.out.println("advanceTime:" + advanceTime);
                System.out.println("delay:" + delayTime);
                if (dvalue != null) {
                    //20/4/17 修改，加工时间
                    dvalue = new Date(dvalue.getTime() + (int) limit.get("CC_" + chargeArr[i][j].getSteelGrade() + "_" + chargeArr[i][j].getProductSpec() + "_processTime")[0] * 60 * 1000L);
                }
                System.out.println("提前时间：" + advanceTime);

                //遍历工艺路线，计划设备路线
                List<SteelSchedule> sslist = new ArrayList<>();
                for (int r = 0; r < route.length; r++) {
                    //工艺route[r]的开始加工时间
                    Date next = null;
                    if (r == 0) {
                        if (first != null){
                            next = new Date(first.getTime() + (int) limit.get("HD_" + route[r] + "_transTime")[0] * 60 * 1000L
                                    + (int) limit.get(route[r] + "_waitTime")[0] * 60 * 1000L);
                        }
                    } else {
                        next = new Date(sslist.get(r - 1).getPlanExit().getTime()
                                + (int) limit.get(route[r - 1] + "_" + route[r] + "_transTime")[0] * 60 * 1000L
                                + (int) limit.get(route[r] + "_waitTime")[0] * 60 * 1000L);
                    }
                    List<SteelDevice> sdlist = steelDeviceService.getDeviceListByType(route[r]);
                    //route[r]类设备中，最早可开始的那台
                    SteelDevice newsd = null;
                    for (SteelDevice sd : sdlist) {
                        if (newsd == null) {
                            newsd = sd;
                        } else if (earlyMap.get(sd.getStationName()).before(earlyMap.get(newsd.getStationName()))) {
                            newsd = sd;
                        }
                    }

                    System.out.println("开始遍历工艺路线，进行到：" + route[r]);
                    if(newsd != null) {
                        System.out.println("此时，设备" + newsd.getStationName() + "的最早时间：" + dateToString(earlyMap.get(newsd.getStationName())));
                    }
                    System.out.println("该工序的开始时间：" + dateToString(next));

                    if (newsd != null && next != null) {
                        if (!earlyMap.get(newsd.getStationName()).after(next)) {
                            SteelSchedule newss = new SteelSchedule();
                            newss.setSteel_scheduleUID(UUIDGenerator.generate());
                            newss.setChargeNo(chargeArr[i][j].getChargeNo());
                            newss.setCastNo(chargeArr[i][j].getAssignCast());
                            newss.setCastSeq(chargeArr[i][j].getCastSeq());
                            newss.setStationName(newsd.getDeviceName());
                            newss.setPlanEnter(next);
                            newss.setIronNo(chargeArr[i][j].getIronNo());
                            newss.setIronSeq(chargeArr[i][j].getIronSeq());
                            //20/4/17 修改，加工时间
                            Date planexit = new Date(next.getTime() + (int) limit.get(route[r] + "_" + chargeArr[i][j].getSteelGrade() + "_" + chargeArr[i][j].getProductSpec() + "_processTime")[0] * 60 * 1000L);
                            newss.setPlanExit(planexit);
                            newss.setPlanStatus("");
                            //actual,lastmodify未知
                            Date newearly = new Date(planexit.getTime() + (int) limit.get(route[r] + "_waitTime")[0] * 60 * 1000L);
                            earlyMap.put(newsd.getStationName(), newearly);
                            sslist.add(newss);
                        } else {
                            delayTime += (int) (earlyMap.get(newsd.getStationName()).getTime() - next.getTime()) / (60 * 1000) + (int) limit.get(route[r] + "_waitTime")[0];
                            System.out.println(route[r] + "设备延时，此时延时为: " + delayTime);
                            i--;
                            continue loop1;
                        }
                    }
                }
                newScheduleMap.put(chargeArr[i][j].getChargeNo(), sslist);
                delayTime = 0;
            }
            //炉次遍历成功，把结果给scheduleMap
            scheduleMap.putAll(newScheduleMap);
            //插入数据库
            for (String key : newScheduleMap.keySet()) {
                List<SteelSchedule> sslist = newScheduleMap.get(key);
                for (SteelSchedule ss : sslist) {
                    System.out.println(ss.getSteel_scheduleUID());
                    int result = addSchedule(ss);
                    devicemap.get(ss.getStationName()).setEnterTime(ss.getPlanEnter());
                    devicemap.get(ss.getStationName()).setExitTime(ss.getPlanExit());
                    if (result == 0) {
                        return null;
                    }
                }
            }
            //更新earlyMap
            earlyMap = getDeviceEarlyAvailableTimeMap(steelDeviceService.getDeviceList());
        }
        //更新device表
        for (String key : devicemap.keySet()) {
            steelDeviceService.updateByDeviceNo(devicemap.get(key));
        }
        return scheduleMap;
    }

    /*
     * name:获得浇次信息（需要排程的一天炉次二维数组）
     * note:传入DateInterval即几天后，返回二维数组，第一维是浇次序号，第二维是浇次内炉次序号，值为炉次信息
     */
    @Override
    public ChargePlan[][] getChargeArr(Date[] dateInterval) {
        ChargePlan[][] chargeArr;
        //用来排除已计划的
        List<SteelSchedule> sslist = getScheduleListByStatus("编制");
        List<CastPlan> calist = castPlanService.getCastListByTimes(dateInterval);
        for (int i = 0; i < calist.size(); i++) {
            for (SteelSchedule ss : sslist) {
                if (ss.getCastNo().equals(calist.get(i).getCastNo())) {
                    calist.remove(calist.get(i));
                    i--;
                    break;
                }
            }
        }
        chargeArr = new ChargePlan[calist.size()][];
        for (int i = 0; i < calist.size(); i++) {
            List<ChargePlan> list = new ArrayList<>(castPlanService.getChargeListByCastUID(calist.get(i).getCast_planUID()));
            chargeArr[i] = new ChargePlan[list.size()];
            for (int j = 0; j < list.size(); j++) {
                chargeArr[i][j] = list.get(j);
            }
        }
        return chargeArr;
    }


    /*
     * 获得设备的最早可用时间Map,key=工序名称如：1#CC，value=最早可用时间
     */
    @Override
    public Map<String, Date> getDeviceEarlyAvailableTimeMap(List<SteelDevice> deviceList) {
        Map<String, Date> earlyTimeMap = new HashMap<>();
        for (SteelDevice sd : deviceList) {
            earlyTimeMap.put(sd.getStationName(), getDeviceEarlyAvailableTime(sd.getStationName()));
        }
        return earlyTimeMap;
    }

    /*
     * 获得设备的最早可用时间
     */
    @Override
    public Date getDeviceEarlyAvailableTime(String stationNo) {
        SteelScheduleExample ssExample = new SteelScheduleExample();
        SteelScheduleExample.Criteria cc = ssExample.createCriteria();
        //起源时间初始值是一个极早的时间，如果调度表中无设备调度信息时，认为设备是新设备，默认上次出站时间为此
        Date originTime = null;

        cc.andStationnameEqualTo(stationNo);
        List<SteelSchedule> sslist = steelScheduleDAO.selectByExample(ssExample);
        for (SteelSchedule ss : sslist) {
            if (originTime == null || ss.getPlanExit().after(originTime)) {
                originTime = ss.getPlanExit();
                if (ss.getActualExit() != null) {
                    originTime = ss.getActualExit();
                }
            }
        }
        if (originTime == null) {
            originTime = new Date();
        }
        String type = steelDeviceService.getTypeByNo(stationNo);
        double waittime = getStandard().get(type + "_waitTime")[0];
        originTime = new Date(originTime.getTime() + (int) waittime * 60 * 1000L);
        return originTime;
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /*
     * name:获取工艺标准数据-调度
     * note:从数据库中读取工艺标准的数据List并处理提取有用的数据如工位间时间等，
     * 返回Map<key=标准ID，value=上下限，double[0]下限[1]上限>
     */
    @Override
    public Map<String, double[]> getStandard() {
        Map<String, double[]> limit = new HashMap<>();
        List<ProcessStandard> standardList = processStandardService.getStandardList();
        for (ProcessStandard p : standardList) {
            String[] temp = p.getItemID().split("_");
            //加工和等待时间，和俩工位间运输时间
            if (temp.length == 2 && temp[1].equals("waitTime")) {
                double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                limit.put(p.getItemID(), d);
            } else if (temp.length == 2 && temp[1].equals("tappingTime")) {
                double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                limit.put(p.getItemID(), d);
                //20/4/16 修改，因加工时间改为与钢号和规格结合决定
            } else if (temp.length >= 3) {
                double[] d = {p.getStandardValue()};
                limit.put(p.getItemID(), d);
            }
        }
        return limit;
    }
    
    private List<Map<String, Object>> timeLineInfos(List<Map<String,Object>> selectList, String orgName){
    	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	// 取当前工位上对应不同炉次号的计划进场时间，取最近一条作为时间轴信息
    	String selectedChargeNo = "";
    	String currentChargeNo = "";   
    	long currentDate = 0;
    	LocalDateTime now = LocalDateTime.now();
    	
    	// 上一工序的状态
    	String preStageExit = "";
    	
    	for(Map<String,Object> ele : selectList) {

			Date planEnter = (Date) ele.get("planEnter");
			LocalDateTime checkDateTime  = planEnter.toInstant()
			        .atZone( ZoneId.systemDefault() )
			        .toLocalDateTime();
			long tempDate = Math.abs(ChronoUnit.SECONDS.between(now, checkDateTime));
			Boolean isCheck = false;    			
			// 第一道工艺
			if("".equals(currentChargeNo) || !currentChargeNo.equals(ele.get("chargeNo").toString())) {
				isCheck = true;
			}
			
			// 非一道工艺，但上一工艺有出站
			if(currentChargeNo.equals(ele.get("chargeNo").toString()) && !StringUtils.isEmpty(preStageExit)) {
				isCheck = true;
			}
			
			currentChargeNo = ele.get("chargeNo").toString();
			
			// 比对，找出最近的一次计划
			if(isCheck && ele.get("stationName").toString().equals(orgName) && ele.get("actualExit")==null) {
				if(currentDate==0) {
					currentDate = tempDate;
					selectedChargeNo = currentChargeNo;
				}else if(tempDate<currentDate){
					currentDate = tempDate;
					selectedChargeNo = currentChargeNo;
				}
			}
			
			preStageExit = ele.get("actualExit")==null?"":ele.get("actualExit").toString();
    			
    	}
    	final String  chargeNo =  selectedChargeNo;
    	if(!StringUtils.isEmpty(selectedChargeNo)) {
        	result = selectList.stream().filter(o ->o.get("chargeNo").toString().equals(chargeNo)).collect(Collectors.toList());
    	}
    	return result;
    }
    
    private Map<String, Object> processInfos(String orgName,String[] orderStationNames){
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	List<SteelSchedule> optionsSchedules =   sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getOptionSchedules");
    	// 每个工位上的最近计划信息
    	Map<String,SteelSchedule> stationNameMapSechedules = new HashMap<String,SteelSchedule>();
    	LocalDateTime now = LocalDateTime.now();
    	for(SteelSchedule schedule : optionsSchedules) {
    		String key = schedule.getStationName() + "___" + schedule.getChargeNo();
    		if(stationNameMapSechedules.containsKey(key)) {
    			SteelSchedule value = stationNameMapSechedules.get(key);
    			
    			LocalDateTime checkDateTime  = value.getPlanEnter().toInstant()
    			        .atZone( ZoneId.systemDefault() )
    			        .toLocalDateTime();
    			LocalDateTime tempDateTime  = schedule.getPlanEnter().toInstant()
    			        .atZone( ZoneId.systemDefault() )
    			        .toLocalDateTime();
    			
    			// 比较计划时间与当前时间，取最近的那个
    			if(Math.abs(ChronoUnit.SECONDS.between(now, checkDateTime))>Math.abs(ChronoUnit.SECONDS.between(now, tempDateTime))) {
    				stationNameMapSechedules.put(key, schedule);
    			}
    		}else {
    			stationNameMapSechedules.put(key, schedule);
    		}
    	}
    	
    	// 循环取得上下当前工位的计划信息
    	for(int i = 0; i<orderStationNames.length ; i++) {
    		String chargeNo = orderStationNames[i];
    		SteelSchedule schedule = new SteelSchedule();
    		if(chargeNo!=null && !"".equals(chargeNo)) {
    			schedule = stationNameMapSechedules.get(chargeNo);
    		}
    		//封装 数据
            switch(i) {
	            case 0:
	            	result.put("last",schedule);
	            	break;
	            case 1:
	            	result.put("current",schedule);
	            	break;
	            case 2:
	            	result.put("next",schedule);
	            	break;
	            default:
	            	break;
            }
    	}
    	return result;
    }
    
    /**
     * 封装上、当前、下工序的标准和工艺信息
     */
    @SuppressWarnings("unused")
	private Map<String, Object>  getStandardAndInfos(String[] orderStationNames) {
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	// 循环工位信息，获取工艺和标准信息
    	String sampleType = "";
    	
    	// 通行查询条件封装
    	for(int i = 0; i<orderStationNames.length ; i++) {
    		// 标准信息
    		String stationName = orderStationNames[i].split("___")[0];
    		if(stationName==null || "".equals(stationName)) {
    			continue;
    		}
    		String chargeNo = orderStationNames[i].split("___")[1];
    		List<CompositionStandard> compositionStandards = new ArrayList<CompositionStandard>();
    		List<CompositionInfo>  compositionInfos = new ArrayList<CompositionInfo>();
    		
    		if(stationName!=null && !"".equals(stationName)) {
    			String chargeType = stationName.split("#")[1];
            	switch(chargeType) {
        	    	case "BOF":
        	    			sampleType = "转炉";
        	    			break;
        	    	case "KR":
        	    			sampleType = "脱硫";
        	    			break;
        	        default:
        	        	sampleType = chargeType;
        	        		break;
            	}
            	
            	// 每个工位上的标准信息
            	Condition condition1=new Condition(CompositionInfo.class);
            	Condition.Criteria criteria=condition1.createCriteria();
            	criteria.andLike("sampleType", "%" + sampleType + "%");
            	compositionStandards =  compositionStandardService.findByCondition(condition1);
            	
            	// 工艺信息
            	Condition condition2=new Condition(CompositionInfo.class);
            	criteria=condition2.createCriteria();
            	condition2.setOrderByClause("acquireTime desc");
                if (chargeNo != null) {
                    criteria.andLike("chargeNo", "%"+chargeNo+"%");
                }
                compositionInfos = compositionInfoService.findByCondition(condition2);
    		}
            
            //封装 数据
            switch(i) {
	            case 0:
	            	result.put("lastStand",compositionStandards);
	            	result.put("lastInfo",compositionInfos);
	            	break;
	            case 1:
	            	result.put("currentStand",compositionStandards);
	            	result.put("currentInfo",compositionInfos);
	            	break;
	            case 2:
	            	result.put("nextStand",compositionStandards);
	            	result.put("nextInfo",compositionInfos);
	            	break;
	            default:
	            	break;
            }
    	}
    	
    	return result;
    }
    
    private String[] getOrderStationNames(List<Map<String, Object>> timeline, String orgName) {
    	String[] result = new String[3];
    	for(int i = 0; i < timeline.size(); i++) {
    		Map<String, Object> tempLineInfo = timeline.get(i);
    		String stationName = tempLineInfo.get("stationName").toString();
    		
    		if(stationName.equals(orgName)) {
    			// 获取当前工序的炉次号
    			String chargeNo = tempLineInfo.get("chargeNo").toString();
    			//当前工序
    			result[1] = stationName + "___" + chargeNo;
    			
    			// 取上工序  - 第一条数据或上一条数据非当前的炉次号
    			if(i==0) {
    				result[0] = "";
    			}else {
    				tempLineInfo = timeline.get(i-1);
    				String lastChargeNo = tempLineInfo.get("chargeNo").toString();
    				if(lastChargeNo.equals(chargeNo)) {
    					stationName = tempLineInfo.get("stationName").toString();
        	    		result[0] = stationName + "___" + lastChargeNo;
    				}else {
    					result[0] = "";
    				}
    			}
    			
    			// 取下工序
    			if(i==timeline.size()-1) {
    				result[2] = "";
    			}else {
    				tempLineInfo = timeline.get(i+1);
    				String nextChargeNo = tempLineInfo.get("chargeNo").toString();
    				if(nextChargeNo.equals(chargeNo)) {
    					stationName = tempLineInfo.get("stationName").toString();
        	    		result[2] = stationName + "___" + nextChargeNo;
    				}
    			}
    			break;
    		}
    	}
    	return  result;
    }
    
    private Map<String, Object> getLastAndNextTimeLine(List<Map<String,Object>> selectList, String[] stationChargeNos){
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	// 上一工序的任务流
    	if(stationChargeNos[0]!=null && !"".equals(stationChargeNos[0])) {
    		String lastChargeNo = stationChargeNos[0];
    		List<String> lastStationNames =  selectList.stream().filter(( o -> lastChargeNo.equals(o.get("chargeNo").toString()))).map( value -> {
    			return value.get("stationName").toString();
    		}).collect(Collectors.toList());
    		result.put("lastTimeLine", lastStationNames);
    	}
    	
    	// 下一工序的任务流
    	if(stationChargeNos[1]!=null && !"".equals(stationChargeNos[1])) {
    		String nextChargeNo = stationChargeNos[1];
    		List<String> nextStationNames  = selectList.stream().filter(( o -> nextChargeNo.equals(o.get("chargeNo").toString()))).map( value -> {
    			return value.get("stationName").toString();
    		}).collect(Collectors.toList());
    		result.put("nextTimeLine", nextStationNames);
    	}
    	
    	return result;
    }
    
    @Override
    public Map<String,Object> getProcessInfo(String orgName){
    	Map<String,Object> result = new HashMap<String, Object>();
    	
    	// 计划调度信息
    	Map<String,Object> parameters=new java.util.HashMap<>();
        parameters.put("orgName",orgName);
    	List<Map<String,Object>> selectList = sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getTimeLineInfoMapList",parameters);
    	
    	if(!selectList.isEmpty()) {
    		//时间轴信息
        	List<Map<String, Object>> timeline = this.timeLineInfos(selectList, orgName);
        	result.put("timeline", timeline);
        	
        	// 前、当前、序的工位信息
        	String[] orderStationNames = this.getOrderStationNames(timeline, orgName);
        	
        	// 上下当前工序信息
        	Map<String, Object> processInfos = this.processInfos(orgName,orderStationNames);
        	result.putAll(processInfos);
        	
        	// 上下当前工艺和标准信息
        	Map<String, Object> standardAndInfos =  this.getStandardAndInfos(orderStationNames);
        	result.putAll(standardAndInfos);
        	
        	// 上下工序的任务流信息
        	String stationChargeNos[] = new String[2];
        	SteelSchedule lastSteelSchedule = (SteelSchedule)processInfos.get("last");
        	if(lastSteelSchedule!=null)
        		stationChargeNos[0] = lastSteelSchedule.getChargeNo();
        	SteelSchedule nextSteelSchedule = (SteelSchedule)processInfos.get("next");
        	if(nextSteelSchedule!=null)
        		stationChargeNos[1] = nextSteelSchedule.getChargeNo();
        	
        	selectList = sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getTimeLineInfoMapList");
        	Map<String, Object>  lastAndNextTimeLines =  this.getLastAndNextTimeLine(selectList, stationChargeNos);
        	result.putAll(lastAndNextTimeLines);
    	}
    	
    	return result;
    }
}
