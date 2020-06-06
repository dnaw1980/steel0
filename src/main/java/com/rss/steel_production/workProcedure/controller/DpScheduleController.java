package com.rss.steel_production.workProcedure.controller;

import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.workProcedure.controller.bean.EnterExitStaBean;
import com.rss.steel_production.workProcedure.controller.bean.StaScDataBean;
import com.rss.steel_production.workProcedure.dao.DpStaScDetailDAO;
import com.rss.steel_production.workProcedure.model.DpScheduleSeq;
import com.rss.steel_production.workProcedure.model.DpStaScDetail;
import com.rss.steel_production.workProcedure.model.DpTechCard;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.steel_production.workProcedure.service.DpTechCardService;
import com.rss.tools.Tools;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/steel_production/schedule/schedule")
@Api
public class DpScheduleController {

    static Logger log;

    static {
        log = Logger.getLogger(DpScheduleController.class.getName());
    }

    @Autowired
    private DpScheduleSeqService dpScheduleSeqService;

    @Resource
    private DpStaScDetailDAO dpStaScDetailDAO;


    /**
     * 查询工艺卡信息
     *
     * @return
     */
    @GetMapping("/add/{castPlanId}/{beginTm}")
    public Result techCard(@PathVariable(required = true) String castPlanId, @PathVariable Timestamp beginTm) {

        DpScheduleSeq rs = this.dpScheduleSeqService.add(castPlanId, beginTm);

        return ResultGenerator.genSuccessResult(rs);
    }

    @PostMapping("/staWpData")
    public Result staWpData(@RequestBody List<String> stationNames) {

        for (String sn : stationNames) {
            log.info("sn:" + sn);
        }

        return ResultGenerator.genSuccessResult(this.dpScheduleSeqService.staWpData(stationNames));
    }

    /**
     * 进站时，忽略出站时间
     *
     * @param enterStaBean
     * @return
     */
    @PostMapping("/enterSta")
    public Result enterSta(@RequestBody EnterExitStaBean enterStaBean) {
        enterStaBean.setActualExit(null);
        /*

         */
        String rs = this.dpScheduleSeqService.enterSta(enterStaBean);
        return ResultGenerator.genSuccessResult(rs);
    }

    /**
     * 出站时，忽略进站时间
     *
     * @param exitStaBean
     * @return
     */
    @PostMapping("/exitSta")
    public Result exitSta(@RequestBody EnterExitStaBean exitStaBean) {
        exitStaBean.setActualEnter(null);
        /*

         */
        String rs = this.dpScheduleSeqService.exitSta(exitStaBean);
        return ResultGenerator.genSuccessResult(rs);
    }

    /**
     * 按工位查当前调度的时间轴
     *
     * @param stationName
     * @return
     */
    @GetMapping("/timeAxis/{stationName}")
    public Result timeAxis(@PathVariable(required = true) String stationName) {
        /*
        1、查当前工位状态为2的 detail
        2、取调度号，再查一遍
         */

        DpStaScDetail staScDetail = null;
        {
            Condition condition = new Condition(DpStaScDetail.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleStation", stationName);

            List<DpStaScDetail> staScDetailList = this.dpStaScDetailDAO.selectByCondition(condition);
            if (Tools.notEmpty(staScDetailList)) {
                staScDetail = staScDetailList.get(0);
            } else {
                return ResultGenerator.genFailResult("当前工位没有调度作业");
            }
        }

        List<DpStaScDetail> staScDetailList = null;
        {
            Condition condition = new Condition(DpStaScDetail.class);
            condition.setOrderByClause("order_sn asc");

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", staScDetail.getScheduleSeqId());

            staScDetailList = this.dpStaScDetailDAO.selectByCondition(condition);

        }
        return ResultGenerator.genSuccessResult(staScDetailList);
    }

    /**
     * 为即将进入的工位，查询可用的调度信息
     *
     * @param stationName 如 1#KR
     * @return
     */
    @GetMapping("/perEnterSc/{stationName}")
    public Result perEnterSc(@PathVariable(required = true) String stationName) {
        /*
        查 dp_schedule_seq
        state 小于3的（应该是1或2）

        查看每个调度对应本工位的明细，时间差最小的那个，排第一位
         */
        Condition condition = new Condition(DpScheduleSeq.class);
        Condition.Criteria criteria = condition.createCriteria();
        criteria.andLessThan("state", DpScheduleSeq.STATE_FINISH);
        criteria.andNotEqualTo("state", DpScheduleSeq.STATE_FAIL);

        List<DpScheduleSeq> rsList = this.dpScheduleSeqService.findByCondition(condition);

        return ResultGenerator.genSuccessResult(rsList);
    }

    /**
     * 返回实时数据甘特图对象
     *
     * @return
     */
    @GetMapping("/realGantt")
    public Result realGantt() {

         /*
          {
           routerNames: [
             // "铁水预处理",
             "脱硫", "转炉", "精炼", "连铸"],
           charges: [
             {
               name: "炉次1", //炉次名
               beginTime: "2020-04-10 13:00:00", //计划开始时间
               endTime: "2020-04-10 13:35:00", //计划结束时间
               routers: [
                 // {
                 //   name: "铁水预处理", //工序名
                 //   beginTime: "2020-04-10 13:00:00", //计划开始时间
                 //   endTime: "2020-04-10 13:05:30", //计划结束时间
                 //   state: 1 // -1 未开始，0 进行中， 1 已完成
                 // },
                 {
                   name: "脱硫",
                   beginTime: "2020-04-10 13:05:30",
                   endTime: "2020-04-10 13:15:45",
                   state: 1
                 },
                 {
                   name: "转炉",
                   beginTime: "2020-04-10 13:15:45",
                   endTime: "2020-04-10 13:20:00",
                   state: 1
                 },
                 {
                   name: "精炼",
                   beginTime: "2020-04-10 13:20:00",
                   endTime: "2020-04-10 13:30:00",
                   state: 1
                 },
                 {
                   name: "连铸",
                   beginTime: "2020-04-10 13:30:00",
                   endTime: "2020-04-10 13:35:00",
                   state: 0
                 }
               ]
             },
             {
               name: "炉次2", //炉次名
               beginTime: "2020-04-10 13:05:00", //计划开始时间
               endTime: "2020-04-10 13:40:00", //计划结束时间
               routers: [
                 // {
                 //   name: "铁水预处理", //工序名
                 //   beginTime: "2020-04-10 13:05:00", //计划开始时间
                 //   endTime: "2020-04-10 13:10:30", //计划结束时间
                 //   state: 1 // -1 未开始，0 进行中， 1 已完成
                 // },
                 {
                   name: "脱硫",
                   beginTime: "2020-04-10 13:10:30",
                   endTime: "2020-04-10 13:20:45",
                   state: 1
                 },
                 {
                   name: "转炉",
                   beginTime: "2020-04-10 13:20:45",
                   endTime: "2020-04-10 13:25:00",
                   state: 1
                 },
                 {
                   name: "精炼",
                   beginTime: "2020-04-10 13:25:00",
                   endTime: "2020-04-10 13:35:00",
                   state: 0
                 },
                 {
                   name: "连铸",
                   beginTime: "2020-04-10 13:35:00",
                   endTime: "2020-04-10 13:40:00",
                   state: -1
                 }
               ]
             },
             {
               name: "炉次3", //炉次名
               beginTime: "2020-04-10 13:15:00", //计划开始时间
               endTime: "2020-04-10 13:50:00", //计划结束时间
               routers: [
                 // {
                 //   name: "铁水预处理", //工序名
                 //   beginTime: "2020-04-10 13:15:00", //计划开始时间
                 //   endTime: "2020-04-10 13:20:30", //计划结束时间
                 //   state: 1 // -1 未开始，0 进行中， 1 已完成
                 // },
                 {
                   name: "脱硫",
                   beginTime: "2020-04-10 13:20:30",
                   endTime: "2020-04-10 13:30:45",
                   state: 1
                 },
                 {
                   name: "转炉",
                   beginTime: "2020-04-10 13:30:45",
                   endTime: "2020-04-10 13:35:00",
                   state: 0
                 },
                 {
                   name: "精炼",
                   beginTime: "2020-04-10 13:35:00",
                   endTime: "2020-04-10 13:45:00",
                   state: -1
                 },
                 {
                   name: "连铸",
                   beginTime: "2020-04-10 13:45:00",
                   endTime: "2020-04-10 13:50:00",
                   state: -1
                 }
               ]
             }
           ]
         }
         */

        return ResultGenerator.genSuccessResult("");
    }


}
