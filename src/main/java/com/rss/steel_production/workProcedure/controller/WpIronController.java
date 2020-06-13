package com.rss.steel_production.workProcedure.controller;

import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.workProcedure.controller.bean.IronDirectionBean;
import com.rss.steel_production.workProcedure.model.DpCastPlan;
import com.rss.steel_production.workProcedure.model.DpCastPlanTO;
import com.rss.steel_production.workProcedure.model.WpIronInfo;
import com.rss.steel_production.workProcedure.model.WpIronInfoTO;
import com.rss.steel_production.workProcedure.service.DpCastPlanService;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.steel_production.workProcedure.service.WpIronInfoService;
import com.rss.tools.Tools;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 铁水信息
 */
@RestController
@RequestMapping("/steel_production/workProcedure/iron")
@Api
public class WpIronController {

    static Logger log;

    static {
        log = Logger.getLogger(WpIronController.class.getName());
    }

    @Autowired
    private WpIronInfoService wpIronInfoService;

    @Autowired
    private DpScheduleSeqService pScheduleSeqService;

    /**
     * 铁水登记
     *
     * @param wpIronInfo
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody WpIronInfo wpIronInfo) {

        if (wpIronInfo == null) {
            return ResultGenerator.genFailResult("铁水信息为空");
        }

        WpIronInfo rs = this.wpIronInfoService.regIronInfo(wpIronInfo);

        return ResultGenerator.genSuccessResult(rs);
    }

    /**
     * 铁水去向确认
     *
     * @param ironDirectionBean
     * @return
     */
    @PostMapping("/confirmDirect")
    public Result confirmDirect(@RequestBody IronDirectionBean ironDirectionBean) {

        if (ironDirectionBean == null) {
            return ResultGenerator.genFailResult("铁水信息为空");
        }

        if (ironDirectionBean.getDirection() == null
                || ironDirectionBean.getDirection().intValue() == WpIronInfo.DESC_NONE) {
            return ResultGenerator.genFailResult("铁水去向为空");
        }

        if (ironDirectionBean.getIronInfoSn() == null
                || ironDirectionBean.getIronInfoSn().intValue() == 0) {
            return ResultGenerator.genFailResult("铁水序号为空");
        }

        String rs = this.wpIronInfoService.confirmDirect(ironDirectionBean);

        if (Tools.empty(rs)) {
            if (ironDirectionBean.getDirection() == WpIronInfo.DESC_STEEL) {
                return ResultGenerator.genSuccessResult("已经为铁水分配炼钢调度计划！");
            } else {
                return ResultGenerator.genSuccessResult("已经为铁水分配去铸铁！");
            }
        } else {
            return ResultGenerator.genFailResult(rs);
        }
    }

    /**
     * 修改铁水信息
     *
     * @param wpIronInfo
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody WpIronInfo wpIronInfo) {

        if (wpIronInfo == null) {
            return ResultGenerator.genFailResult("铁水信息为空");
        }

        int ironSn = wpIronInfo.getIronInfoSn();
        int row = this.wpIronInfoService.update(wpIronInfo);
        if (row == 0) {
            return ResultGenerator.genFailResult("未修改信息信息！");
        }
        WpIronInfo rs = this.wpIronInfoService.findById(String.valueOf(ironSn));

        return ResultGenerator.genSuccessResult(rs);
    }

    /**
     * 铁水列表
     *
     * @param wpIronInfoTO
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody WpIronInfoTO wpIronInfoTO) {

        if (wpIronInfoTO == null) {
            wpIronInfoTO = new WpIronInfoTO();
        }
        if (wpIronInfoTO.getPageNo() == 0) {
            wpIronInfoTO.setPageNo(1);
        }
        if (wpIronInfoTO.getPageSize() == 0) {
            wpIronInfoTO.setPageSize(20);
        }

        List<WpIronInfo> rsList = this.wpIronInfoService.list(wpIronInfoTO);

        return ResultGenerator.genSuccessResult(rsList);
    }

}
