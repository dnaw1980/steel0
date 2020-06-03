package com.rss.steel_production.workProcedure.controller;

import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.workProcedure.model.DpCastPlan;
import com.rss.steel_production.workProcedure.model.DpCastPlanTO;
import com.rss.steel_production.workProcedure.model.DpTechCard;
import com.rss.steel_production.workProcedure.service.DpCastPlanService;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.steel_production.workProcedure.service.DpTechCardService;
import com.rss.tools.Tools;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 浇次计划
 */
@RestController
@RequestMapping("/steel_production/workProcedure/castPlan")
@Api
public class DpCastPlanController {

    static Logger log;

    static {
        log = Logger.getLogger(DpCastPlanController.class.getName());
    }

    @Autowired
    private DpCastPlanService dpCastPlanService;

    @Autowired
    private DpScheduleSeqService pScheduleSeqService;

    /**
     * 添加浇次计划
     *
     * @param dpCastPlan
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody DpCastPlan dpCastPlan) {

        if (dpCastPlan == null) {
            return ResultGenerator.genFailResult("浇次为空");
        }

        if (Tools.empty(dpCastPlan.getTechCardId())) {
            return ResultGenerator.genFailResult("钢种产品为空");
        }

        if (Tools.empty(dpCastPlan.getStaId())) {
            return ResultGenerator.genFailResult("连铸工位为空");
        }


        DpCastPlan rs = this.dpCastPlanService.add(dpCastPlan);

        return ResultGenerator.genSuccessResult(rs);
    }

    /**
     * 修改浇次计划
     *
     * @param dpCastPlan
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody DpCastPlan dpCastPlan) {

        if (dpCastPlan == null) {
            return ResultGenerator.genFailResult("浇次为空");
        }

        int rs = this.dpCastPlanService.update(dpCastPlan);
        DpCastPlan rsObj = null;
        if (rs == 1) {
            rsObj = this.dpCastPlanService.findById(dpCastPlan.getCastPlanId());
        }

        return ResultGenerator.genSuccessResult(rsObj);
    }

    /**
     * 删除浇次计划，如果浇次上有调度序列，不能删除
     *
     * @param castPlanId
     * @return
     */
    @GetMapping("/remove/{castPlanId}")
    public Result remove(@PathVariable(required = true) String castPlanId) {

        if (Tools.empty(castPlanId)) {
            return ResultGenerator.genFailResult("浇次ID为空");
        }

        //判断调度序列中是否有对应本浇次的
        boolean hasSeq = this.pScheduleSeqService.castHasScheduleSeq(castPlanId);
        if (hasSeq) {
            return ResultGenerator.genFailResult("当前浇次已经有调度数据，不能删除！");
        }

        //删除浇次
        return ResultGenerator.genSuccessResult("删除成功");
    }

    /**
     * 查询浇次列表
     *
     * @param dpCastPlanTO
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody DpCastPlanTO dpCastPlanTO) {
        return ResultGenerator.genSuccessResult(this.dpCastPlanService.list(dpCastPlanTO));
    }

    /**
     * 查询正在执行的浇次
     *
     * @return
     */
    @PostMapping("/listExec")
    public Result listExec() {
        log.info("listExec");
        return ResultGenerator.genSuccessResult(this.dpCastPlanService.listExec());
    }

}
