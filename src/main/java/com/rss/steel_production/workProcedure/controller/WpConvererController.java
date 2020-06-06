package com.rss.steel_production.workProcedure.controller;

import com.github.pagehelper.PageHelper;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.workProcedure.dao.DpScheduleSeqDAO;
import com.rss.steel_production.workProcedure.dao.DpStaScDetailDAO;
import com.rss.steel_production.workProcedure.dao.WpConvererInfoDAO;
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
    private DpScheduleSeqDAO dpScheduleSeqDAO;

    @Autowired
    private DpScheduleSeqService pScheduleSeqService;


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
        WpConvererInfo desc = new WpConvererInfo();
        ConvererCpHelper.copyInfo(desc, steelScrapInfo);

        //

        int rs = this.wpConvererInfoDAO.updateByPrimaryKeySelective(desc);

        if (rs == 1) {
            return ResultGenerator.genSuccessResult("登记信息成功！");
        } else {
            return ResultGenerator.genSuccessResult("登记信息失败！");
        }

    }

    @Resource
    private DpStaScDetailDAO dpStaScDetailDAO;

    /**
     * 铁水列表
     *
     * @param stationName
     * @return
     */
    @GetMapping("/steelList/{stationName}")
    public Result steelList(@PathVariable(required = true) String stationName) {

        //查该转炉工位对应的未完成的调度明细
        List<DpStaScDetail> ssDetailList = null;
        {
            Condition condition = new Condition(DpStaScDetail.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleStation", stationName);
            criteria.andLessThan("detailState", DpScheduleDetail.STATE_FINISH);

            ssDetailList = this.dpStaScDetailDAO.selectByCondition(condition);
        }

        List<Integer> snList = new ArrayList<Integer>(ssDetailList.size());
        for (DpStaScDetail detail : ssDetailList) {
            snList.add(detail.getWpSn());
        }

        Condition condition = new Condition(WpConvererInfo.class);
        condition.setOrderByClause("tankSteelRegTm desc");
//        PageHelper.startPage(1, 20);
        Condition.Criteria criteria = condition.createCriteria();
        criteria.andIn("convererInfoSn", snList);

        //TODO 查询条件暂时为空

        List<WpConvererInfo> convererList = this.wpConvererInfoDAO.selectByCondition(condition);
        List<WpConvererSteelScrapInfo> rsList = new ArrayList<WpConvererSteelScrapInfo>();

        for (WpConvererInfo src : convererList) {
            if (src.getScheduleSeqId() == null) {
                continue;
            }
            WpConvererSteelScrapInfo desc = new WpConvererSteelScrapInfo();
            ConvererCpHelper.copyInfo(desc, src);

            DpScheduleSeq scheduleSeq = this.dpScheduleSeqDAO.selectByPrimaryKey(src.getScheduleSeqId());
            desc.setBlastNo(scheduleSeq.getBlastNo());
            desc.setChargeNo(scheduleSeq.getChargeNo());
            desc.setScheduleSeqId(src.getScheduleSeqId());
            rsList.add(desc);
        }

        return ResultGenerator.genSuccessResult(rsList);
    }

}
