package com.rss.steel_production.schedule.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rss.framework.AbstractService;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.foundation.model.ProcessStandard;
import com.rss.steel_production.foundation.model.SteelDevice;
import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.foundation.service.SteelDeviceService;
import com.rss.steel_production.process.model.CompositionInfo;
import com.rss.steel_production.process.model.CompositionStandard;
import com.rss.steel_production.process.service.CompositionInfoService;
import com.rss.steel_production.process.service.CompositionStandardService;
import com.rss.steel_production.schedule.dao.SteelScheduleDAO;
import com.rss.steel_production.schedule.model.CastPlan;
import com.rss.steel_production.schedule.model.ChargePlan;
import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.model.SteelScheduleExample;
import com.rss.steel_production.schedule.service.CastPlanService;
import com.rss.steel_production.schedule.service.ChargePlanService;
import com.rss.steel_production.schedule.service.IronPlanService;
import com.rss.steel_production.schedule.service.SteelScheduleService;

import tk.mybatis.mapper.entity.Condition;

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
     * ??????
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
     * ?????????????????????????????????
     */
    @Override
    public int addSchedule(SteelSchedule ss) {
        return steelScheduleDAO.insert(ss);
    }

    /*
     * ???????????????????????????
     */
    @Override
    public void deleteScheduleByStatus(String status) {
        SteelScheduleExample ssExample = new SteelScheduleExample();
        SteelScheduleExample.Criteria cc = ssExample.createCriteria();
        cc.andPlanstatusEqualTo(status);
        steelScheduleDAO.deleteByExample(ssExample);
    }

    /*
     * ???????????????????????????????????????
     */
    @Override
    public List<SteelSchedule> getScheduleListByStatus(String status) {
        SteelScheduleExample ssExample = new SteelScheduleExample();
        SteelScheduleExample.Criteria cc = ssExample.createCriteria();
        cc.andPlanstatusEqualTo(status);
        return steelScheduleDAO.selectByExample(ssExample);
    }

    /*
     * ??????????????????
     */
    @Override
    public Map<String, List<SteelSchedule>> createSchedule(Date[] dateInterval) {
        //??????map
        Map<String, List<SteelSchedule>> scheduleMap = new LinkedHashMap<>();
        //????????????????????????map
        Map<String, Date> earlyMap = getDeviceEarlyAvailableTimeMap(steelDeviceService.getDeviceList());
        //???????????????????????????
        ChargePlan[][] chargeArr = getChargeArr(dateInterval);
        //????????????
        Map<String, double[]> limit = getStandard();
        //????????????????????????????????????
        int delayTime = 0;
        if (chargeArr.length <= 0) {
            return null;
        }
        Map<String, SteelDevice> devicemap = new HashMap<>();
        for (SteelDevice sd : steelDeviceService.getDeviceList()) {
            devicemap.put(sd.getDeviceName(), sd);
        }
        //????????????
        loop1:
        for (int i = 0; i < chargeArr.length; i++) {
            Map<String, List<SteelSchedule>> newScheduleMap = new LinkedHashMap<>();
            //???????????????,?????????CC
            String dkey = null;
            Date dvalue = null;
            System.out.println("??????????????????");
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
                //??????????????????????????????
                dvalue = new Date(dvalue.getTime() + delayTime * 60 * 1000L);
            }

            //?????????????????????
            for (int j = 0; j < chargeArr[i].length; j++) {
                System.out.println("??????????????????");
                int advanceTime = 0;
                String[] route = chargeArr[i][j].getProcessRoute().split("_");
                //???????????????????????????,??????????????????
                for (int r = 0; r < route.length; r++) {
                    int wait, process, trans;
                    wait = (int) limit.get(route[r] + "_waitTime")[0];
                    //20/4/17 ???????????????????????????????????????
                    process = (int) limit.get(route[r] + "_" + chargeArr[i][j].getSteelGrade() + "_" + chargeArr[i][j].getProductSpec() + "_processTime")[0];
                    if (r == 0) {
                        trans = (int) limit.get("HD_" + route[r] + "_transTime")[0];
                    } else {
                        trans = (int) limit.get(route[r - 1] + "_" + route[r] + "_transTime")[0];
                    }
                    advanceTime += wait + process + trans;
                    System.out.println("???????????????...");
                }
                Date first = null;
                if (dvalue != null) {
                    //????????????????????????first????????????????????????????????????????????????first????????????????????????????????????????????????
                    first = new Date(dvalue.getTime() - advanceTime * 60 * 1000L);
                }
                System.out.println("first:" + dateToString(first));
                System.out.println("dvalue:" + dvalue);
                System.out.println("advanceTime:" + advanceTime);
                System.out.println("delay:" + delayTime);
                if (dvalue != null) {
                    //20/4/17 ?????????????????????
                    dvalue = new Date(dvalue.getTime() + (int) limit.get("CC_" + chargeArr[i][j].getSteelGrade() + "_" + chargeArr[i][j].getProductSpec() + "_processTime")[0] * 60 * 1000L);
                }
                System.out.println("???????????????" + advanceTime);

                //???????????????????????????????????????
                List<SteelSchedule> sslist = new ArrayList<>();
                for (int r = 0; r < route.length; r++) {
                    //??????route[r]?????????????????????
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
                    //route[r]???????????????????????????????????????
                    SteelDevice newsd = null;
                    for (SteelDevice sd : sdlist) {
                        if (newsd == null) {
                            newsd = sd;
                        } else if (earlyMap.get(sd.getStationName()).before(earlyMap.get(newsd.getStationName()))) {
                            newsd = sd;
                        }
                    }

                    System.out.println("???????????????????????????????????????" + route[r]);
                    if(newsd != null) {
                        System.out.println("???????????????" + newsd.getStationName() + "??????????????????" + dateToString(earlyMap.get(newsd.getStationName())));
                    }
                    System.out.println("???????????????????????????" + dateToString(next));

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
                            //20/4/17 ?????????????????????
                            Date planexit = new Date(next.getTime() + (int) limit.get(route[r] + "_" + chargeArr[i][j].getSteelGrade() + "_" + chargeArr[i][j].getProductSpec() + "_processTime")[0] * 60 * 1000L);
                            newss.setPlanExit(planexit);
                            newss.setPlanStatus("");
                            //actual,lastmodify??????
                            Date newearly = new Date(planexit.getTime() + (int) limit.get(route[r] + "_waitTime")[0] * 60 * 1000L);
                            earlyMap.put(newsd.getStationName(), newearly);
                            sslist.add(newss);
                        } else {
                            delayTime += (int) (earlyMap.get(newsd.getStationName()).getTime() - next.getTime()) / (60 * 1000) + (int) limit.get(route[r] + "_waitTime")[0];
                            System.out.println(route[r] + "??????????????????????????????: " + delayTime);
                            i--;
                            continue loop1;
                        }
                    }
                }
                newScheduleMap.put(chargeArr[i][j].getChargeNo(), sslist);
                delayTime = 0;
            }
            //?????????????????????????????????scheduleMap
            scheduleMap.putAll(newScheduleMap);
            //???????????????
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
            //??????earlyMap
            earlyMap = getDeviceEarlyAvailableTimeMap(steelDeviceService.getDeviceList());
        }
        //??????device???
        for (String key : devicemap.keySet()) {
            steelDeviceService.updateByDeviceNo(devicemap.get(key));
        }
        return scheduleMap;
    }

    /*
     * name:???????????????????????????????????????????????????????????????
     * note:??????DateInterval?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    @Override
    public ChargePlan[][] getChargeArr(Date[] dateInterval) {
        ChargePlan[][] chargeArr;
        //????????????????????????
        List<SteelSchedule> sslist = getScheduleListByStatus("??????");
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
     * ?????????????????????????????????Map,key=??????????????????1#CC???value=??????????????????
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
     * ?????????????????????????????????
     */
    @Override
    public Date getDeviceEarlyAvailableTime(String stationNo) {
        SteelScheduleExample ssExample = new SteelScheduleExample();
        SteelScheduleExample.Criteria cc = ssExample.createCriteria();
        //??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
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
     * name:????????????????????????-??????
     * note:??????????????????????????????????????????List??????????????????????????????????????????????????????
     * ??????Map<key=??????ID???value=????????????double[0]??????[1]??????>
     */
    @Override
    public Map<String, double[]> getStandard() {
        Map<String, double[]> limit = new HashMap<>();
        List<ProcessStandard> standardList = processStandardService.getStandardList();
        for (ProcessStandard p : standardList) {
            String[] temp = p.getItemID().split("_");
            //???????????????????????????????????????????????????
            if (temp.length == 2 && temp[1].equals("waitTime")) {
                double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                limit.put(p.getItemID(), d);
            } else if (temp.length == 2 && temp[1].equals("tappingTime")) {
                double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                limit.put(p.getItemID(), d);
                //20/4/16 ????????????????????????????????????????????????????????????
            } else if (temp.length >= 3) {
                double[] d = {p.getStandardValue()};
                limit.put(p.getItemID(), d);
            }
        }
        return limit;
    }
    
    private List<Map<String, Object>> timeLineInfos(List<Map<String,Object>> selectList, String orgName){
    	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	// ???????????????????????????????????????????????????????????????????????????????????????????????????
    	String selectedChargeNo = "";
    	String currentChargeNo = "";   
    	long currentDate = 0;
    	LocalDateTime now = LocalDateTime.now();
    	
    	// ?????????????????????
    	String preStageExit = "";
    	
    	for(Map<String,Object> ele : selectList) {

			Date planEnter = (Date) ele.get("planEnter");
			LocalDateTime checkDateTime  = planEnter.toInstant()
			        .atZone( ZoneId.systemDefault() )
			        .toLocalDateTime();
			long tempDate = Math.abs(ChronoUnit.SECONDS.between(now, checkDateTime));
			Boolean isCheck = false;    			
			// ???????????????
			if("".equals(currentChargeNo) || !currentChargeNo.equals(ele.get("chargeNo").toString())) {
				isCheck = true;
			}
			
			// ??????????????????????????????????????????
			if(currentChargeNo.equals(ele.get("chargeNo").toString()) && !StringUtils.isEmpty(preStageExit)) {
				isCheck = true;
			}
			
			currentChargeNo = ele.get("chargeNo").toString();
			
			// ????????????????????????????????????
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
    	// ????????????????????????????????????
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
    			
    			// ??????????????????????????????????????????????????????
    			if(Math.abs(ChronoUnit.SECONDS.between(now, checkDateTime))>Math.abs(ChronoUnit.SECONDS.between(now, tempDateTime))) {
    				stationNameMapSechedules.put(key, schedule);
    			}
    		}else {
    			stationNameMapSechedules.put(key, schedule);
    		}
    	}
    	
    	// ?????????????????????????????????????????????
    	for(int i = 0; i<orderStationNames.length ; i++) {
    		String chargeNo = orderStationNames[i];
    		SteelSchedule schedule = new SteelSchedule();
    		if(chargeNo!=null && !"".equals(chargeNo)) {
    			schedule = stationNameMapSechedules.get(chargeNo);
    		}
    		//?????? ??????
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
     * ??????????????????????????????????????????????????????
     */
    @SuppressWarnings("unused")
	private Map<String, Object>  getStandardAndInfos(String[] orderStationNames) {
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	// ????????????????????????????????????????????????
    	String sampleType = "";
    	
    	// ????????????????????????
    	for(int i = 0; i<orderStationNames.length ; i++) {
    		// ????????????
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
        	    			sampleType = "??????";
        	    			break;
        	    	case "KR":
        	    			sampleType = "??????";
        	    			break;
        	        default:
        	        	sampleType = chargeType;
        	        		break;
            	}
            	
            	// ??????????????????????????????
            	Condition condition1=new Condition(CompositionInfo.class);
            	Condition.Criteria criteria=condition1.createCriteria();
            	criteria.andLike("sampleType", "%" + sampleType + "%");
            	compositionStandards =  compositionStandardService.findByCondition(condition1);
            	
            	// ????????????
            	Condition condition2=new Condition(CompositionInfo.class);
            	criteria=condition2.createCriteria();
            	condition2.setOrderByClause("acquireTime desc");
                if (chargeNo != null) {
                    criteria.andLike("chargeNo", "%"+chargeNo+"%");
                }
                compositionInfos = compositionInfoService.findByCondition(condition2);
    		}
            
            //?????? ??????
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
    			// ??????????????????????????????
    			String chargeNo = tempLineInfo.get("chargeNo").toString();
    			//????????????
    			result[1] = stationName + "___" + chargeNo;
    			
    			// ????????????  - ??????????????????????????????????????????????????????
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
    			
    			// ????????????
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
    	
    	// ????????????????????????
    	if(stationChargeNos[0]!=null && !"".equals(stationChargeNos[0])) {
    		String lastChargeNo = stationChargeNos[0];
    		List<String> lastStationNames =  selectList.stream().filter(( o -> lastChargeNo.equals(o.get("chargeNo").toString()))).map( value -> {
    			return value.get("stationName").toString();
    		}).collect(Collectors.toList());
    		result.put("lastTimeLine", lastStationNames);
    	}
    	
    	// ????????????????????????
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
    	
    	// ??????????????????
    	Map<String,Object> parameters=new java.util.HashMap<>();
        parameters.put("orgName",orgName);
    	List<Map<String,Object>> selectList = sqlSession.selectList("com.rss.steel_production.schedule.dao.SteelScheduleDao.getTimeLineInfoMapList",parameters);
    	
    	if(!selectList.isEmpty()) {
    		//???????????????
        	List<Map<String, Object>> timeline = this.timeLineInfos(selectList, orgName);
        	result.put("timeline", timeline);
        	
        	// ?????????????????????????????????
        	String[] orderStationNames = this.getOrderStationNames(timeline, orgName);
        	
        	// ????????????????????????
        	Map<String, Object> processInfos = this.processInfos(orgName,orderStationNames);
        	result.putAll(processInfos);
        	
        	// ?????????????????????????????????
        	Map<String, Object> standardAndInfos =  this.getStandardAndInfos(orderStationNames);
        	result.putAll(standardAndInfos);
        	
        	// ??????????????????????????????
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

	@Override
	public void alterBlastOrder(String blastOrder, String id) {
		SteelSchedule steelSchedule = new SteelSchedule();
		steelSchedule.setIronNo(blastOrder);
		steelSchedule.setSteel_scheduleUID(id);
		sqlSession.update("com.rss.steel_production.schedule.dao.SteelScheduleDao.alterBlastOrder",steelSchedule);
		
	}

	private void updateNewerPlan(Date endDate, Date startDate) {
		// ????????????????????????(??????)
		LocalDateTime endDateTime  = endDate.toInstant()
		        .atZone( ZoneId.systemDefault() )
		        .toLocalDateTime();
		LocalDateTime startDateTime  = startDate.toInstant()
		        .atZone( ZoneId.systemDefault() )
		        .toLocalDateTime();
		
		// ??????????????????????????????????????????????????????
		long secondsBetween = ChronoUnit.SECONDS.between(endDateTime, startDateTime);
		
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("secondsBetween", secondsBetween);
		param.put("planExit", endDate);
		sqlSession.update("com.rss.steel_production.schedule.dao.SteelScheduleDao.alterPlan",param);
	}
	
	@Override
	public void deletePlan(Map<String, Object> requestParam) throws Exception {
		// ??????????????????????????????????????????????????????????????????
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String planEnterTimeStr = requestParam.get("planEnter").toString();
		String planExitTimeStr = requestParam.get("planExit").toString();
		String steel_scheduleUID = requestParam.get("id").toString();
		Date planExit = formatter.parse(planExitTimeStr);
		Date planEnter = formatter.parse(planEnterTimeStr);
		
		// ???????????????????????????
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("steel_scheduleUID", steel_scheduleUID);
		sqlSession.update("com.rss.steel_production.schedule.dao.SteelScheduleDao.deletePlan",param);
		
		//?????????????????????????????????
		this.updateNewerPlan(planExit, planEnter);
	}

	@Override
	public void updatePlan(Map<String, Object> requestParam) throws Exception {
		// ??????????????????????????????????????????????????????????????????
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = requestParam.get("planEnter_now").toString();
		String endTimeStr = requestParam.get("planExit_now").toString();
		//String startOldTimeStr = requestParam.get("planEnter_old").toString();
		String endOldTimeStr = requestParam.get("planExit_old").toString();
		String steel_scheduleUID = requestParam.get("id").toString();
		Date planExitOld = formatter.parse(endOldTimeStr);
		Date planExit = formatter.parse(endTimeStr);
		Date planEnter = formatter.parse(startTimeStr);
		
		// ???????????????????????????
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("steel_scheduleUID", steel_scheduleUID);
		param.put("planEnter", planEnter);
		param.put("planExit", planExit);
		sqlSession.update("com.rss.steel_production.schedule.dao.SteelScheduleDao.updatePlan",param);
		
		//?????????????????????????????????
		this.updateNewerPlan(planExit, planExitOld);
		
	}
}
