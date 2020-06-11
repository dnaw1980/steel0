package com.rss.steel_production.workProcedure.controller;

import com.github.pagehelper.PageHelper;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.workProcedure.controller.bean.ScrapRefConvererBean;
import com.rss.steel_production.workProcedure.dao.DpScheduleSeqDAO;
import com.rss.steel_production.workProcedure.dao.DpStaScDetailDAO;
import com.rss.steel_production.workProcedure.dao.WpConvererInfoDAO;
import com.rss.steel_production.workProcedure.dao.WpConvererSteelScrapInfoDAO;
import com.rss.steel_production.workProcedure.model.ConvererCpHelper;
import com.rss.steel_production.workProcedure.model.WpConvererInfo;
import com.rss.steel_production.workProcedure.model.WpConvererSteelScrapInfo;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
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
     * @param pageNum 页号
     * @param isRef   是否关联，0否，1是，没有全部
     * @return
     */
    @GetMapping("/steelList/{pageNum}")
    public Result steelList(@PathVariable(required = true) int pageNum, @RequestParam Integer isRef) {

        //查该转炉工位对应的未完成的调度明细
        List<WpConvererSteelScrapInfo> steelScrapList = null;
        {
            Condition condition = new Condition(WpConvererSteelScrapInfo.class);
            condition.setOrderByClause("tankSteelRegTm desc");

            if (isRef != null) {

                Condition.Criteria criteria = condition.createCriteria();
                if (isRef.intValue() == 0) {
                    //查未关联的
                    criteria.andIsNull("scheduleSeqId");
                } else {
                    //查已经关联的
                    criteria.andIsNotNull("scheduleSeqId");
                }
            }

            PageHelper.startPage(pageNum, 20);

            steelScrapList = this.wpConvererSteelScrapInfoDAO.selectByCondition(condition);
        }

        return ResultGenerator.genSuccessResult(steelScrapList);
    }


    /**
     * 将废钢与转炉关联起来
     *
     * @param srcBean
     * @return
     */
    @PostMapping("/scrapRefConverer")
    public Result scrapRefConverer(@RequestBody ScrapRefConvererBean srcBean) {

        /**
         通过调度ID，查转炉工序表，
         修改废钢表的调度ID和工序序号
         */
        if (srcBean == null) {
            return ResultGenerator.genFailResult("参数为空");
        }

        if (Tools.empty(srcBean.getScheduleSeqId())) {
            return ResultGenerator.genFailResult("调度ID为空");
        }

        if (srcBean.getSteelScrapInfoSn() == null
                || srcBean.getSteelScrapInfoSn().intValue() < 1) {
            return ResultGenerator.genFailResult("废钢序号不合法或为空");
        }

        Integer infoSn = null;
        {
            Condition condition = new Condition(WpConvererInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", srcBean.getScheduleSeqId());

            List<WpConvererInfo> convererInfoList = this.wpConvererInfoDAO.selectByCondition(condition);
            if (Tools.notEmpty(convererInfoList)) {
                infoSn = convererInfoList.get(0).getConvererInfoSn();
            }
        }

        if (infoSn == null) {
            return ResultGenerator.genFailResult("没有对应的调度信息或工序信息");
        }

        WpConvererSteelScrapInfo css = new WpConvererSteelScrapInfo();
        css.setScheduleSeqId(srcBean.getScheduleSeqId());
        css.setConvererInfoSn(infoSn);
        css.setSteelScrapInfoSn(srcBean.getSteelScrapInfoSn());

        int rs = this.wpConvererSteelScrapInfoDAO.updateByPrimaryKeySelective(css);

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("关联成功");
        } else {
            return ResultGenerator.genFailResult("关联失败");
        }
    }
}
