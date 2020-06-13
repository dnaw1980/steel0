package com.rss.steel_production.workProcedure.impl;

import com.github.pagehelper.PageHelper;
import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.workProcedure.controller.bean.IronDirectionBean;
import com.rss.steel_production.workProcedure.dao.DpCastWpEndTmDAO;
import com.rss.steel_production.workProcedure.dao.WpIronInfoDAO;
import com.rss.steel_production.workProcedure.model.*;
import com.rss.steel_production.workProcedure.service.DpCastPlanService;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.steel_production.workProcedure.service.WpIronInfoService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
@Transactional
public class WpIronInfoImpl extends AbstractService<WpIronInfo> implements WpIronInfoService {

    @Resource
    private WpIronInfoDAO wpIronInfoDAO;

    @Resource
    private DpCastPlanService dpCastPlanService;

    @Resource
    private DpCastWpEndTmDAO dpCastWpEndTmDAO;

    @Autowired
    private DpScheduleSeqService dpScheduleSeqService;

    /**
     * 注册铁水信息，生成一条调度计划
     *
     * @param wpIronInfo
     * @return
     */
    @Override
    public WpIronInfo regIronInfo(WpIronInfo wpIronInfo) {

        if (wpIronInfo.getRegisterTime() == null) {
            wpIronInfo.setRegisterTime(DateUtil.getDateTime());
        }
        this.wpIronInfoDAO.insertUseGeneratedKeys(wpIronInfo);

        return wpIronInfo;
    }

    /**
     * 查看铁水信息列表
     *
     * @param wpIronInfoTO
     * @return
     */
    @Override
    public List<WpIronInfo> list(WpIronInfoTO wpIronInfoTO) {

        Condition condition = new Condition(WpIronInfo.class);
        condition.setOrderByClause("registerTime desc");
        PageHelper.startPage(wpIronInfoTO.getPageNo(), wpIronInfoTO.getPageSize());

        //TODO 查询条件暂时为空

        List<WpIronInfo> rsList = this.wpIronInfoDAO.selectByCondition(condition);

        return rsList;
    }

    /**
     * 铁水去向确认
     *
     * @param ironDirectionBean
     * @return
     */
    @Override
    public String confirmDirect(IronDirectionBean ironDirectionBean) {

        //去铸铁
        if (ironDirectionBean.getDirection().intValue() == WpIronInfo.DESC_IRON) {
            WpIronInfo ironInfo = new WpIronInfo();
            ironInfo.setIronInfoSn(ironDirectionBean.getIronInfoSn());
            ironInfo.setDirection(ironDirectionBean.getDirection());

            int rs = this.wpIronInfoDAO.updateByPrimaryKeySelective(ironInfo);
            if (rs < 1) {
                return "未能修改任何数据";
            } else {
                return null;
            }
        }

        //去炼钢


        /*
        1、查看当前正在执行的浇次计划，查每个浇次对应的工艺卡中的第一个工艺路径。
        按这个工艺路径对应的工序，找到所有工位，及每个工位最后一个作业的完成时间（实际优先于计划）。

        2、比较每个工位的完成时间是最早的那个，就是这个调度的第一个工序的开始时间。
        3、根据当前工序查询出标准作业时间，计算当前工序完成时间。
        2、
         */
        List<DpCastPlan> castList = dpCastPlanService.listExec();

        DpCastPlan _descCast = null;
        Timestamp _lastTime = null;
        for (DpCastPlan dpCastPlan : castList) {
            DpTechRoute techRoute = dpCastPlan.getDpTechCard().getTechRouteList().get(0);

            Condition condition = new Condition(DpCastWpEndTm.class);
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("castPlanId", dpCastPlan.getCastPlanId());
            criteria.andEqualTo("workProcId", techRoute.getWorkProcId());

            List<DpCastWpEndTm> cwetList = this.dpCastWpEndTmDAO.selectByCondition(condition);
            if (Tools.notEmpty(cwetList)) {
                if (_lastTime == null) {
                    _lastTime = cwetList.get(0).getPlanEnd();
                    _descCast = dpCastPlan;
                } else {
                    if (cwetList.get(0).getPlanEnd().before(_lastTime)) {
                        _lastTime = cwetList.get(0).getPlanEnd();
                        _descCast = dpCastPlan;
                    }
                }
            } else {
                _descCast = dpCastPlan;
                break;
            }
        }

        //生成调度

        WpIronInfo ironInfo = this.wpIronInfoDAO.selectByPrimaryKey(ironDirectionBean.getIronInfoSn());

        DpScheduleSeq scheduleSeq = this.autoGenScheduleSeq(ironInfo);
        ironInfo.setScheduleSeqId(scheduleSeq.getScheduleSeqId());

        this.wpIronInfoDAO.updateByPrimaryKey(ironInfo);

//        if (wpIronInfo.getRegisterTime() == null) {
//            wpIronInfo.setRegisterTime(DateUtil.getDateTime());
//        }
//        this.wpIronInfoDAO.insertUseGeneratedKeys(wpIronInfo);
//
//        return wpIronInfo;

        return null;
    }


    private DpScheduleSeq autoGenScheduleSeq(final WpIronInfo wpIronInfo) {
        /*
        1、查看当前正在执行的浇次计划，查每个浇次对应的工艺卡中的第一个工艺路径。
        按这个工艺路径对应的工序，找到所有工位，及每个工位最后一个作业的完成时间（实际优先于计划）。

        2、比较每个工位的完成时间是最早的那个，就是这个调度的第一个工序的开始时间。
        3、根据当前工序查询出标准作业时间，计算当前工序完成时间。
        2、
         */
        List<DpCastPlan> castList = dpCastPlanService.listExec();

        DpCastPlan _descCast = null;
        Timestamp _lastTime = null;
        for (DpCastPlan dpCastPlan : castList) {
            DpTechRoute techRoute = dpCastPlan.getDpTechCard().getTechRouteList().get(0);

            Condition condition = new Condition(DpCastWpEndTm.class);
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("castPlanId", dpCastPlan.getCastPlanId());
            criteria.andEqualTo("workProcId", techRoute.getWorkProcId());

            List<DpCastWpEndTm> cwetList = this.dpCastWpEndTmDAO.selectByCondition(condition);
            if (Tools.notEmpty(cwetList)) {
                if (_lastTime == null) {
                    _lastTime = cwetList.get(0).getPlanEnd();
                    _descCast = dpCastPlan;
                } else {
                    if (cwetList.get(0).getPlanEnd().before(_lastTime)) {
                        _lastTime = cwetList.get(0).getPlanEnd();
                        _descCast = dpCastPlan;
                    }
                }
            } else {
                _descCast = dpCastPlan;
                break;
            }
        }

        //生成调度
        DpScheduleSeq dpScheduleSeq = this.dpScheduleSeqService.add(_descCast.getCastPlanId(), wpIronInfo.getArriveTime() == null ? DateUtil.getDateTime() : wpIronInfo.getArriveTime());
//        wpIronInfo.setScheduleSeqId(dpScheduleSeq.getScheduleSeqId());

        if (wpIronInfo.getRegisterTime() == null) {
            wpIronInfo.setRegisterTime(DateUtil.getDateTime());
        }
//        this.wpIronInfoDAO.insertUseGeneratedKeys(wpIronInfo);
//
//        return wpIronInfo;

        return dpScheduleSeq;
    }

    /**
     * 同步站点工序数据，从实时数据同步到工序数据。
     *
     * @param sta
     */
    @Override
    public void synWpData(TdSta sta, Map<String, TdChannel> channelMap) {
        //TODO
        /*
        1、查询本站点的实时工序数据。
        2、逐步从实时数据中计算中工序数据。
        3、更新工序数据到数据库
        4、查找调度工序数据，复制实时工序数据到调度工序数据中。
         */
    }
}
