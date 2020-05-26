package com.rss.steel_production.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.netty_client.EchoClient;
import com.rss.steel_production.foundation.service.SteelDeviceService;
import com.rss.steel_production.schedule.controller.bean.EnterStationBean;
import com.rss.steel_production.schedule.controller.bean.ExitStationBean;
import com.rss.steel_production.schedule.controller.bean.TempBean;
import com.rss.steel_production.schedule.controller.bean.WeightBean;
import com.rss.steel_production.schedule.model.SteelScheduleReturn;
import com.rss.steel_production.schedule.service.CastPlanService;
import com.rss.steel_production.schedule.service.ChargePlanService;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

import com.rss.steel_production.schedule.service.SteelScheduleService;
import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.model.SteelScheduleTO;

@RestController
@RequestMapping("/steel_production/schedule/steelSchedule")
@Api
public class SteelScheduleController {
    @Resource
    private SteelScheduleService steelScheduleService;
    @Autowired
    SteelDeviceService steelDeviceService;
    @Autowired
    CastPlanService castPlanService;
    @Autowired
    ChargePlanService chargePlanService;

    //计划编制，起始日期，终止日期
    @RequestMapping("/prepareAuto")
    @ResponseBody
    public Result prepareAuto(Date start, Date end) {
        Date[] dateInterval = {start, end};
        String status = "";
        Map<String, List<SteelSchedule>> scheduleMap = steelScheduleService.createSchedule(dateInterval);
        ArrayList<SteelScheduleReturn> steelScheduleReturnArrayList = new ArrayList<>();
        List<SteelSchedule> sslist = steelScheduleService.getScheduleListByStatus(status);
        if (scheduleMap == null) {
            return ResultGenerator.genFailResult("无法计划调度，可能由于以下原因：<br>1.铁次不够用;<br>2.无需要调度的炉次.");
        } else {
            for (String key : scheduleMap.keySet()) {
                SteelScheduleReturn steelScheduleReturn = new SteelScheduleReturn();
                steelScheduleReturn.setChargeNo(key);
                steelScheduleReturn.setList(scheduleMap.get(key));
                steelScheduleReturnArrayList.add(steelScheduleReturn);
            }
        }
        System.out.println("在steelSchedule/prepare中，获得的sslist的大小为：" + sslist.size());
        return ResultGenerator.genSuccessResult(steelScheduleReturnArrayList);
    }

    //删除，通过状态
    @RequestMapping("/abandon")
    @ResponseBody
    public Result abandon() {
        steelScheduleService.deleteScheduleByStatus("");
        return ResultGenerator.genSuccessResult();
    }

    //检查
    @PostMapping("/check")
    public Result check(@RequestBody List<SteelSchedule> list) {
        boolean result = steelScheduleService.checkIfRight(list);
        if (result) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("fail");
        }
    }

    @PostMapping
    public Result add(@RequestBody SteelSchedule steelSchedule) {
        steelSchedule.setSteel_scheduleUID(UUIDGenerator.generate());
        steelScheduleService.insert(steelSchedule);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody SteelSchedule steelSchedule) {
        steelScheduleService.updateByPrimaryKey(steelSchedule);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<String> ids) {
        String idString = QuotesUtil.addQuotesToString(ids);
        steelScheduleService.deleteByIds(idString);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/find")
    public Result find(@RequestBody SteelScheduleTO steelScheduleTO) {
        int pageNo = steelScheduleTO.getPageNo();
        int pageSize = steelScheduleTO.getPageSize();
        SteelSchedule steelSchedule = steelScheduleTO.getSteelSchedule();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(steelScheduleService.findByModel(steelSchedule));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findBy")
    public Result findBy(@RequestBody SteelScheduleTO steelScheduleTO) {
        int pageNo = steelScheduleTO.getPageNo();
        int pageSize = steelScheduleTO.getPageSize();
        SteelSchedule model = steelScheduleTO.getSteelSchedule();
        PageHelper.startPage(pageNo, pageSize);
        Condition condition = new Condition(SteelSchedule.class);
        condition.setOrderByClause("planEnter desc");
        Condition.Criteria criteria = condition.createCriteria();
        if (model != null) {
            if (model.getChargeNo() != null) {
                criteria.andLike("chargeNo", "%" + model.getChargeNo() + "%");
            }
            if (model.getStationName() != null) {
                criteria.andLike("stationName", "%" + model.getStationName() + "%");
            }
            if (model.getPlanStatus() != null) {
                criteria.andLike("planStatus", "%" + model.getPlanStatus() + "%");
            }
            if (model.getPlanEnter() != null) {
                criteria.andLike("planEnter", "%" + new SimpleDateFormat("yyyy-MM-dd").format(model.getPlanEnter()) + "%");
            }
        }
        PageInfo pageInfo = new PageInfo(steelScheduleService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/send")
    public Result send(@RequestBody SteelSchedule steelSchedule) {
        String message = "<request sender='scheduleServer' type='steelSchedule'>" +
                "<steelSchedule>" +
                "<steel_scheduleUID>" + steelSchedule.getSteel_scheduleUID() + "</steel_scheduleUID>" +
                "<chargeNo>" + steelSchedule.getChargeNo() + "</chargeNo>" +
                "<castNo>" + steelSchedule.getCastNo() + "</castNo>" +
                "<castSeq>" + steelSchedule.getCastSeq() + "</castSeq>" +
                "<stationName>" + steelSchedule.getStationName() + "</stationName>" +
                "<planEnter>" + steelSchedule.getPlanEnter() + "</planEnter>" +
                "<planExit>" + steelSchedule.getPlanExit() + "</planExit>" +
                "<ironNo>" + steelSchedule.getIronNo() + "</ironNo>" +
                "<ironSeq>" + steelSchedule.getIronSeq() + "</ironSeq>" +
                "</steelSchedule>" +
                "</request>";
        EchoClient.getInstance().cH.sendToch(message);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 通过工序名称获取该页面上的时间轴和上下工位相关信息
     *
     * @param orgName
     * @return
     */
    @PostMapping("/processInfo")
    public Result getProcessInfo(@RequestParam(value = "orgName", required = true) String orgName) {
        Map<String, Object> result = steelScheduleService.getProcessInfo(orgName);
        return ResultGenerator.genSuccessResult(result);
    }
    
    
    @PostMapping("/alterBlastOrder")
    public Result alterBlastOrder(@RequestParam(value = "blastOrder", required = true) String blastOrder, @RequestParam(value = "id", required = true) String id) {
        steelScheduleService.alterBlastOrder(blastOrder,id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 进站
     * @param enterStationBean
     * @return
     */
    @PostMapping("/enter")
    public Result enter(@RequestBody EnterStationBean enterStationBean) {

        if (enterStationBean == null) {
            return ResultGenerator.genFailResult("参数为空");
        }

        if (enterStationBean.getChargeNo() == null || "".equals(enterStationBean.getChargeNo())) {
            return ResultGenerator.genFailResult("炉次为空");
        }

        if (enterStationBean.getStationName() == null || "".equals(enterStationBean.getStationName())) {
            return ResultGenerator.genFailResult("工位为空");
        }

        Condition condition = new Condition(SteelSchedule.class);
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andLike("chargeNo", "%" + enterStationBean.getChargeNo() + "%");
        criteria.andLike("stationName", "%" + enterStationBean.getStationName() + "%");

        List<SteelSchedule> scList = steelScheduleService.findByCondition(condition);

        if (scList == null || scList.size() != 1) {
            return ResultGenerator.genFailResult("未查到对应的调度记录，或不只一条！");
        }

        SteelSchedule steelSchedule = scList.get(0);

        if (enterStationBean.getTemperature() != null && !"".equals(enterStationBean.getTemperature())) {
            steelSchedule.setTemperature(enterStationBean.getTemperature());
        }

        if (enterStationBean.getWeight() != null && !"".equals(enterStationBean.getWeight())) {
            steelSchedule.setWeight(enterStationBean.getWeight());
        }

        if (enterStationBean.getActualEnter() == null) {
            return ResultGenerator.genFailResult("请输入进站时间！");
        }

        steelSchedule.setActualEnter(enterStationBean.getActualEnter());

        int rs = steelScheduleService.update(steelSchedule);

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("进站成功！");
        } else {
            return ResultGenerator.genSuccessResult("进站失败");
        }

    }


    /**
     * 出站
     * @param exitStationBean
     * @return
     */
    @PostMapping("/exit")
    public Result exit(@RequestBody ExitStationBean exitStationBean) {

        if (exitStationBean == null) {
            return ResultGenerator.genFailResult("参数为空");
        }

        if (exitStationBean.getChargeNo() == null || "".equals(exitStationBean.getChargeNo())) {
            return ResultGenerator.genFailResult("炉次为空");
        }

        if (exitStationBean.getStationName() == null || "".equals(exitStationBean.getStationName())) {
            return ResultGenerator.genFailResult("工位为空");
        }

        Condition condition = new Condition(SteelSchedule.class);
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andLike("chargeNo", "%" + exitStationBean.getChargeNo() + "%");
        criteria.andLike("stationName", "%" + exitStationBean.getStationName() + "%");

        List<SteelSchedule> scList = steelScheduleService.findByCondition(condition);

        if (scList == null || scList.size() != 1) {
            return ResultGenerator.genFailResult("未查到对应的调度记录，或不只一条！");
        }

        SteelSchedule steelSchedule = scList.get(0);

        if (exitStationBean.getTemperature() != null && !"".equals(exitStationBean.getTemperature())) {
            steelSchedule.setTemperature(exitStationBean.getTemperature());
        }

        if (exitStationBean.getWeight() != null && !"".equals(exitStationBean.getWeight())) {
            steelSchedule.setWeight(exitStationBean.getWeight());
        }

        if (exitStationBean.getActualExit() == null) {
            return ResultGenerator.genFailResult("请输入出站时间！");
        }

        steelSchedule.setActualExit(exitStationBean.getActualExit());
        //3表示完成
        steelSchedule.setPlanStatus("3");
        // TODO 需要根据录入的调度出或进站时间，自动调整该时间之后的计划进站和计划出站时间
        int rs = steelScheduleService.update(steelSchedule);

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("出站成功！");
        } else {
            return ResultGenerator.genSuccessResult("出站失败");
        }
    }

    /**
     * 温度
     * @param tempBean
     * @return
     */
    @PostMapping("/temp")
    public Result temp(@RequestBody TempBean tempBean) {

        if (tempBean == null) {
            return ResultGenerator.genFailResult("参数为空");
        }

        if (tempBean.getChargeNo() == null || "".equals(tempBean.getChargeNo())) {
            return ResultGenerator.genFailResult("炉次为空");
        }

        if (tempBean.getStationName() == null || "".equals(tempBean.getStationName())) {
            return ResultGenerator.genFailResult("工位为空");
        }

        Condition condition = new Condition(SteelSchedule.class);
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andLike("chargeNo", "%" + tempBean.getChargeNo() + "%");
        criteria.andLike("stationName", "%" + tempBean.getStationName() + "%");

        List<SteelSchedule> scList = steelScheduleService.findByCondition(condition);

        if (scList == null || scList.size() != 1) {
            return ResultGenerator.genFailResult("未查到对应的调度记录，或不只一条！");
        }

        SteelSchedule steelSchedule = scList.get(0);

        if (tempBean.getTemperature() == null) {
            return ResultGenerator.genFailResult("请输入温度！");
        }

        steelSchedule.setTemperature(tempBean.getTemperature());

        int rs = steelScheduleService.update(steelSchedule);

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("温度输入成功！");
        } else {
            return ResultGenerator.genSuccessResult("温度输入失败");
        }

    }

    /**
     * 重量
     * @param weightBean
     * @return
     */
    @PostMapping("/weight")
    public Result weight(@RequestBody WeightBean weightBean) {

        if (weightBean == null) {
            return ResultGenerator.genFailResult("参数为空");
        }

        if (weightBean.getChargeNo() == null || "".equals(weightBean.getChargeNo())) {
            return ResultGenerator.genFailResult("炉次为空");
        }

        if (weightBean.getStationName() == null || "".equals(weightBean.getStationName())) {
            return ResultGenerator.genFailResult("工位为空");
        }

        Condition condition = new Condition(SteelSchedule.class);
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andLike("chargeNo", "%" + weightBean.getChargeNo() + "%");
        criteria.andLike("stationName", "%" + weightBean.getStationName() + "%");

        List<SteelSchedule> scList = steelScheduleService.findByCondition(condition);

        if (scList == null || scList.size() != 1) {
            return ResultGenerator.genFailResult("未查到对应的调度记录，或不只一条！");
        }

        SteelSchedule steelSchedule = scList.get(0);

        if (weightBean.getWeight() == null) {
            return ResultGenerator.genFailResult("请输入重量！");
        }

        steelSchedule.setWeight(weightBean.getWeight());

        int rs = steelScheduleService.update(steelSchedule);

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("重量输入成功！");
        } else {
            return ResultGenerator.genSuccessResult("重量输入失败");
        }

    }
    
    
    /**
     * 删除指定的调度计划
     * @param param
     * @return
     * @throws Exception 
     */
    @PostMapping("/deletePlan")
    public Result deletePlan(@RequestBody Map<String,Object> param) throws Exception {
    	steelScheduleService.deletePlan(param);
    	return ResultGenerator.genSuccessResult();
    }
    
    /**
     * 更新指定的调度计划
     * @param param
     * @return
     * @throws Exception 
     */
    @PostMapping("/updatePlan")
    public Result updatePlan(@RequestBody Map<String,Object> param) throws Exception {
    	steelScheduleService.updatePlan(param);
    	return ResultGenerator.genSuccessResult();
    }
}
