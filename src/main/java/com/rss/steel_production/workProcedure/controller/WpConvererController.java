package com.rss.steel_production.workProcedure.controller;

import com.github.pagehelper.PageHelper;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.workProcedure.dao.DpScheduleSeqDAO;
import com.rss.steel_production.workProcedure.dao.DpStaScDetailDAO;
import com.rss.steel_production.workProcedure.dao.WpConvererInfoDAO;
import com.rss.steel_production.workProcedure.dao.WpConvererSteelScrapInfoDAO;
import com.rss.steel_production.workProcedure.model.*;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.tools.DateUtil;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 铁水信息
 */
@RestController
@RequestMapping("/steel_production/workProcedure/converer")
@Api
public class WpConvererController {

    static Logger log;

    static {
        log = Logger.getLogger(WpConvererController.class.getName());
    }

    @Resource
    private WpConvererInfoDAO wpConvererInfoDAO;

    @Resource
    private WpConvererSteelScrapInfoDAO wpConvererSteelScrapInfoDAO;

    @Resource
    private DpScheduleSeqDAO dpScheduleSeqDAO;

    @Autowired
    private DpScheduleSeqService pScheduleSeqService;

    /**
     * 修改废钢信息
     *
     * @param steelScrapInfo
     * @return
     */
    @PostMapping("/steelAdd")
    public Result steelAdd(@RequestBody WpConvererSteelScrapInfo steelScrapInfo) {

        if (steelScrapInfo == null) {
            return ResultGenerator.genFailResult("废钢信息为空");
        }

        steelScrapInfo.setTankSteelRegTm(DateUtil.getDateTime());
//        WpConvererInfo desc = new WpConvererInfo();
//        ConvererCpHelper.copyInfo(desc, steelScrapInfo);

        int rs = this.wpConvererSteelScrapInfoDAO.insertUseGeneratedKeys(steelScrapInfo);

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("登记信息成功！");
        } else {
            return ResultGenerator.genSuccessResult("登记信息失败！");
        }

    }

    /**
     * 修改废钢信息
     *
     * @param steelScrapInfo
     * @return
     */
    @PostMapping("/steelUpdate")
    public Result steelUpdate(@RequestBody WpConvererSteelScrapInfo steelScrapInfo) {

        if (steelScrapInfo == null) {
            return ResultGenerator.genFailResult("废钢信息为空");
        }

        steelScrapInfo.setTankSteelRegTm(DateUtil.getDateTime());
//        WpConvererInfo desc = new WpConvererInfo();
//        ConvererCpHelper.copyInfo(desc, steelScrapInfo);

        //

        int rs = this.wpConvererSteelScrapInfoDAO.updateByPrimaryKeySelective(steelScrapInfo);

        //修改对应转炉的工序信息
        if (steelScrapInfo.getConvererInfoSn() != null) {
            WpConvererInfo convererInfo = new WpConvererInfo();
            ConvererCpHelper.copyInfo(convererInfo, steelScrapInfo);

            this.wpConvererInfoDAO.updateByPrimaryKeySelective(convererInfo);
        }

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("登记信息成功！");
        } else {
            return ResultGenerator.genSuccessResult("登记信息失败！");
        }

    }

    @Resource
    private DpStaScDetailDAO dpStaScDetailDAO;

    /**
     * 废钢列表
     *
     * @return
     */
    @GetMapping("/steelList/{pageNum}")
    public Result steelList(@PathVariable(required = true) int pageNum) {

        //查该转炉工位对应的未完成的调度明细
        List<WpConvererSteelScrapInfo> steelScrapList = null;
        {
            Condition condition = new Condition(WpConvererSteelScrapInfo.class);
            condition.setOrderByClause("tankSteelRegTm desc");
            PageHelper.startPage(pageNum, 20);

            steelScrapList = this.wpConvererSteelScrapInfoDAO.selectByCondition(condition);
        }

        return ResultGenerator.genSuccessResult(steelScrapList);
    }

}
