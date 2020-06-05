package com.rss.steel_production.workProcedure.impl;

import com.github.pagehelper.PageHelper;
import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.CompositionInfoDAO;
import com.rss.steel_production.process.dao.CompositionStandardDAO;
import com.rss.steel_production.process.model.CompositionInfo;
import com.rss.steel_production.process.model.CompositionStandard;
import com.rss.steel_production.schedule.controller.bean.RealDataBean;
import com.rss.steel_production.schedule.dao.TdStaDAO;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.schedule.service.TdStaService;
import com.rss.steel_production.workProcedure.controller.bean.StaScDataBean;
import com.rss.steel_production.workProcedure.dao.*;
import com.rss.steel_production.workProcedure.model.*;
import com.rss.steel_production.workProcedure.service.DpCastPlanService;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
@EnableScheduling
@Transactional
public class DpScheduleSeqImpl extends AbstractService<DpScheduleSeq> implements DpScheduleSeqService {

    static Logger log;

    static {
        log = Logger.getLogger(DpScheduleSeqImpl.class.getName());
    }

    @Resource
    private DpScheduleSeqDAO dpScheduleSeqDAO;

    @Resource
    private TdStaDAO tdStaDAO;

    @Resource
    private DpScheduleDetailDAO dpScheduleDetailDAO;

    @Resource
    private DpWorkProcTransDAO dpWorkProcTransDAO;

    @Resource
    private DpStaScheduleTmDAO dpStaScheduleTmDAO;

    @Resource
    private DpCastPlanService dpCastPlanService;

    @Resource
    private WpBilletInfoDAO wpBilletInfoDAO;

    @Resource
    private WpCasInfoDAO wpCasInfoDAO;

    @Resource
    private WpConvererInfoDAO wpConvererInfoDAO;

    @Resource
    private WpDesulfuriInfoDAO wpDesulfuriInfoDAO;

    @Resource
    private WpIronInfoDAO wpIronInfoDAO;

    @Resource
    private WpLfInfoDAO wpLfInfoDAO;

    @Resource
    private WpRhInfoDAO wpRhInfoDAO;

    @Resource
    private WpSlabInfoDAO wpSlabInfoDAO;

    @Resource
    private CompositionStandardDAO compositionStandardDAO;

    @Resource
    private CompositionInfoDAO compositionInfoDAO;

    @Resource
    private DpStaScDetailDAO dpStaScDetailDAO;

    @Autowired
    private TdStaService tdStaService;


    @Override
    public boolean castHasScheduleSeq(String castPlanId) {

        Condition condition = new Condition(DpScheduleSeq.class);
        Condition.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("castPlanId", castPlanId);

        PageHelper.startPage(1, 1);
        List<DpScheduleSeq> seqList = dpScheduleSeqDAO.selectByCondition(condition);

        if (Tools.empty(seqList)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 添加调度计划
     *
     * @param castPlanId
     * @return
     */
    @Override
    public DpScheduleSeq add(String castPlanId, Timestamp beginTm) {
        /*
        1、通过浇次号查询浇次计划。
        2、查询浇次对应的工艺卡的工艺路径。
        查询每个工位的当前执行的炉次的计划完成时间（如果为空按当前时间处理），并按工序进行分组，装入MAP
        3、遍历工艺路径，
        计算本明细的最早开始时间。
        （如果不是CC）
        比较每个工序上工位的最小时间，作为本明细的开始时间。
        计算结束时间。
        生成调度明细
         */

        //工艺卡和工艺路径
        DpCastPlan dpCastPlan = this.dpCastPlanService.get(castPlanId);
        List<DpTechRoute> routeList = dpCastPlan.getDpTechCard().getTechRouteList();

        //TODO 工序及开始时间，应该查最后一个开始时间，而不是最早开始时间, end_tm
        List<DpStaScheduleTm> staScheduleTmList = this.dpStaScheduleTmDAO.selectAll();

        //工序MAP
        Map<String, List<DpStaScheduleTm>> workProcMap = new HashMap<String, List<DpStaScheduleTm>>();

        for (DpStaScheduleTm staScheduleTm : staScheduleTmList) {
            List<DpStaScheduleTm> list = workProcMap.get(staScheduleTm.getWorkProcId());
            if (list == null) {
                list = new ArrayList<DpStaScheduleTm>();
                workProcMap.put(staScheduleTm.getWorkProcId(), list);
            }
            list.add(staScheduleTm);
        }

        DpScheduleSeq scheduleSeq = new DpScheduleSeq();
        scheduleSeq.setCastPlanId(castPlanId);
        scheduleSeq.setBeginTm(beginTm);
        scheduleSeq.setState(DpScheduleSeq.STATE_PLAN);
        scheduleSeq.setScheduleSeqId(Tools.getUUID());

        int rs = this.dpScheduleSeqDAO.insert(scheduleSeq);

        scheduleSeq.setDetailList(new ArrayList<DpScheduleDetail>());

        //准备工序运输时间表
        List<DpWorkProcTrans> transList = this.dpWorkProcTransDAO.selectAll();

        //每一道工序的最早开始时间
        Timestamp posTime = scheduleSeq.getBeginTm();
        DpTechRoute lastRoute = null;
        //遍历工艺路径
        int orderSn = 1;
        for (DpTechRoute techRoute : routeList) {
            DpScheduleDetail scheduleDetail = new DpScheduleDetail();
            scheduleDetail.setScheduleSeqId(scheduleSeq.getScheduleSeqId());
            /*
            3、遍历工艺路径，
        计算本明细的最早开始时间。
        （如果不是CC）
        比较每个工序上工位的最小时间，作为本明细的开始时间。
        计算结束时间。
        生成调度明细
             */
            scheduleDetail.setState(DpScheduleDetail.STATE_PLAN);
            scheduleDetail.setOrderSn(orderSn++);

            List<DpStaScheduleTm> staList = workProcMap.get(techRoute.getWorkProcId());
            if (Tools.empty(staList)) {
                String msg = "techRoute :" + techRoute.getWorkProcId() + "没有可用的工位！";
                log.error(msg);
                throw new RuntimeException(msg);
            }

            //posTime 增加工序间运输时间
            if (lastRoute != null) {

                for (DpWorkProcTrans trans : transList) {
                    if (trans.getSrcWorkProcId().equals(lastRoute.getWorkProcId())
                            && trans.getDescWorkProcId().equals(techRoute.getWorkProcId())
                    ) {
                        posTime = DateUtil.minuteAdd(posTime, trans.getTransCycle());
                    }

                }
            }

            lastRoute = techRoute;
            //按EndTm 升序
            staList.sort(Comparator.comparing(DpStaScheduleTm::getEndTm));

            DpStaScheduleTm _ssTm = staList.get(0);
            //设置计划开始时间
            if (_ssTm.getEndTm().after(posTime)) {
                scheduleDetail.setPlanBegin(_ssTm.getEndTm());
            } else {
                scheduleDetail.setPlanBegin(posTime);
            }

            scheduleDetail.setStaId(_ssTm.getStaId());

            //设置计划结束时间，开始时间+作业周期
            Timestamp planEnd = DateUtil.minuteAdd(scheduleDetail.getPlanBegin(), _ssTm.getWorkCycle());
            scheduleDetail.setPlanEnd(planEnd);

            posTime = planEnd;

//TODO 要向工序信息表中插入记录
            int wpSn = this.pushWorkProcInfo(scheduleSeq.getScheduleSeqId(), _ssTm);
            scheduleDetail.setWpSn(wpSn);

            rs = this.dpScheduleDetailDAO.insertUseGeneratedKeys(scheduleDetail);
            if (rs == 1) {
                scheduleSeq.getDetailList().add(scheduleDetail);
            }

        }

        return scheduleSeq;
    }

    private int pushWorkProcInfo(String scheduleSeqId, DpStaScheduleTm ssTm) {
        int rs = 0;
        switch (ssTm.getWorkProcCode()) {
            case "KR": {
                WpDesulfuriInfo wpDesulfuriInfo = new WpDesulfuriInfo();
                wpDesulfuriInfo.setScheduleSeqId(scheduleSeqId);
                this.wpDesulfuriInfoDAO.insertUseGeneratedKeys(wpDesulfuriInfo);
                rs = wpDesulfuriInfo.getDesulfuriInfoSn();
            }
            break;
            case "BOF": {
                WpConvererInfo wpConvererInfo = new WpConvererInfo();
                wpConvererInfo.setScheduleSeqId(scheduleSeqId);
                this.wpConvererInfoDAO.insertUseGeneratedKeys(wpConvererInfo);
                rs = wpConvererInfo.getConvererInfoSn();
            }
            break;
            case "CAS": {
                WpCasInfo wpCasInfo = new WpCasInfo();
                wpCasInfo.setScheduleSeqId(scheduleSeqId);
                this.wpCasInfoDAO.insertUseGeneratedKeys(wpCasInfo);
                rs = wpCasInfo.getCasInfoSn();
            }
            break;
            case "LF": {
                WpLfInfo wpLfInfo = new WpLfInfo();
                wpLfInfo.setScheduleSeqId(scheduleSeqId);
                this.wpLfInfoDAO.insertUseGeneratedKeys(wpLfInfo);
                rs = wpLfInfo.getLfInfoSn();
            }
            break;
            case "RH": {
                WpRhInfo wpRhInfo = new WpRhInfo();
                wpRhInfo.setScheduleSeqId(scheduleSeqId);
                this.wpRhInfoDAO.insertUseGeneratedKeys(wpRhInfo);
                rs = wpRhInfo.getRhInfoSn();
            }
            break;
            case "CC": {
                if (ssTm.getStaNo() == 1) {//方坯
                    WpBilletInfo wpBilletInfo = new WpBilletInfo();
                    wpBilletInfo.setScheduleSeqId(scheduleSeqId);
                    this.wpBilletInfoDAO.insertUseGeneratedKeys(wpBilletInfo);
                    rs = wpBilletInfo.getBilletInfoSn();
                } else {//板坯
                    WpSlabInfo wpSlabInfo = new WpSlabInfo();
                    wpSlabInfo.setScheduleSeqId(scheduleSeqId);
                    this.wpSlabInfoDAO.insertUseGeneratedKeys(wpSlabInfo);
                    rs = wpSlabInfo.getSlabInfoSn();
                }
            }
            break;
        }

        return rs;

    }

    /**
     * 预览调度修改
     *
     * @param scSeqList 要修改的调度
     * @return
     */
    @Override
    public List<DpScheduleSeq> perSchedulePlan(List<DpScheduleSeq> scSeqList) {



        /*
        1、遍历 scSeqList 中的调度，看是否有作废的，如果有要先忽略，从数据库中查询其它的调度,
            按 beginTm asc, gmt_create asc 排序。
        2、查询每个调度的调度明细，状态为 0-计划，1-下达 两种情况的。
        3、查询每个工位的当前执行的炉次的计划完成时间（如果为空按当前时间处理），并按工序进行分组，装入MAP
        4、查询工序间运输时间。
        5、遍历从数据库查出来的调度记录，外层遍历调度，内层遍历明细。创建一个目标 List，将结果装入目标List.
            在内层遍历前，要使用调度的 begin_tm 作为最早的开始时间，每次内层循环要更新此变量。
            0、设置变量，上次工序 = null,
            如果有上次工序，要查运输时间表，把运输时间加到 begin_tm 里面去。
            5.1、查询明细，如果该明细不在入参 scSeqList 内存在跳过。

            5.2、判断工序是否是 CC（连铸），如果不是CC，{
                判断是否修改的工位，即数据库与参数不一样。
                如果修改了，
                    使用新工位
                如果没修改
                    比较当前工序内的几个工序，找出最早生产时间。
                    有最早开始时间的，就是当选工位
                }

             5.3、计算开始时间。
                    比较 begin_tm 与本工位的最早开始时间，最大的，就是本道工序的计划开始时间。
                  计算结束时间。开始时间 + 工作时间，就是本调度的结束时间，也是本工位的新开始时间，要修改本工位信息。

                  记录一下本道工序ID，为下次循环计算开始时间用。
         */

        return null;
    }

    /**
     * 执行调度修改
     *
     * @param scSeqList 要修改的调度
     * @return
     */
    @Override
    public List<DpScheduleSeq> execSchedulePlan(List<DpScheduleSeq> scSeqList) {
        /*
        1、遍历 scSeqList 中的调度，看是否有作废的，如果有要先执行作废操作
         */
        List<DpScheduleSeq> rs = this.perSchedulePlan(scSeqList);
        //TODO 将 rs 写入数据库
        return rs;
    }

    /**
     * 返回给定工位的实时调度信息
     *
     * @param stationNames
     * @return
     */
    @Override
    public List<StaScDataBean> staWpData(List<String> stationNames) {
        List<StaScDataBean> rsList = new ArrayList<StaScDataBean>(stationNames.size());
        for (String sn : stationNames) {
            log.info("sn:" + sn);
            StaScDataBean bean = this._fillSingleWpData(sn);
            rsList.add(bean);
        }
        return rsList;
    }

    /**
     * 为每个工位添加工序、调度等信息
     *
     * @param stationName
     * @return
     */
    private StaScDataBean _fillSingleWpData(String stationName) {
        //通过工位号查询站点信息
        TdSta tdSta = null;
        {
            Condition condition = new Condition(TdSta.class);
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleStation", stationName);
            List<TdSta> staList = this.tdStaDAO.selectByCondition(condition);
            if (Tools.empty(staList)) {
                log.info("工位号不合法：" + stationName);
                return null;
            }

            tdSta = staList.get(0);
        }

        StaScDataBean rsBean = new StaScDataBean();

        //当前步骤工序的成份标准信息
        String staType = stationName.split("#")[1]; //1#BOF 即 BOF
        rsBean.setStand(this.listCompStand(staType));

        //当前工位的工序信息
        rsBean.setInfo(this.workProc(tdSta, staType));

        DpScheduleSeq scheduleSeq = null;
        //调度信息
        //查调度明细，工位ID，状态为执行
        {
            Condition condition = new Condition(DpScheduleDetail.class);
            Condition.Criteria criteria = condition.createCriteria();

            criteria.andEqualTo("staId", tdSta.getStaId());
            criteria.andEqualTo("state", DpScheduleDetail.STATE_EXEC);

            List<DpScheduleDetail> scheduleDetails = this.dpScheduleDetailDAO.selectByCondition(condition);
            if (Tools.notEmpty(scheduleDetails)) {
                DpScheduleDetail detail = scheduleDetails.get(0);
                rsBean.setScheduleDetail(detail);

                //查询调度序列
                scheduleSeq = this.dpScheduleSeqDAO.selectByPrimaryKey(detail.getScheduleSeqId());
            }

        }

        //工位上当前炉次号的工艺路径
        if (scheduleSeq != null) {
            rsBean.setBlastNo(scheduleSeq.getBlastNo());
            rsBean.setChargeNo(scheduleSeq.getChargeNo());

            Condition condition = new Condition(DpStaScDetail.class);
            condition.setOrderByClause("order_sn asc");
            Condition.Criteria criteria = condition.createCriteria();

            criteria.andEqualTo("scheduleSeqId", scheduleSeq.getScheduleSeqId());
            List<DpStaScDetail> staScDetails = this.dpStaScDetailDAO.selectByCondition(condition);

            List<String> routeList = new ArrayList<String>();
            for (DpStaScDetail staScDetail : staScDetails) {
                routeList.add(staScDetail.getScheduleStation());
            }

            rsBean.setRouter(routeList);
        }

        //TODO 当前步骤工序的成份化验信息
        if (scheduleSeq != null) {
            List<CompositionInfo> compList = this.listComp(staType, scheduleSeq.getChargeNo());
            rsBean.setComposition(compList);
        }

        //实时数据
        RealDataBean rd = this.tdStaService.realData(stationName);
        rsBean.setRealData(rd);

        return rsBean;
    }

    private List<CompositionInfo> listComp(String staType, String chargeNo) {

        List<String> descType = this.exechangeType(staType);
        if (Tools.empty(descType)) {
            return null;
        }

        List<CompositionInfo> rsList = null;

        Condition condition = new Condition(CompositionInfo.class);
        Condition.Criteria criteria1 = condition.createCriteria();
        Condition.Criteria criteria2 = condition.createCriteria();

        criteria1.andLike("chargeNo", "%" + chargeNo + "%");
        condition.and(criteria1);

        for (String type : descType) {
            criteria2.orEqualTo("sampleType", type);
        }
        condition.and(criteria2);


        rsList = this.compositionInfoDAO.selectByCondition(condition);
        return rsList;
    }

    private List<String> exechangeType(String staType) {

        List<String> descType = new ArrayList<String>();
        switch (staType) {
            case "KR":
                descType.add("脱硫");
                break;
            case "BOF":
                descType.add("炉前");
                descType.add("转炉");
                break;
            case "CAS":
                descType.add("CAS");
                break;
            case "LF":
                descType.add("LF");
                break;
            case "CC":
                descType.add("连铸");
                break;
        }

        return descType;
    }


    private WpBase workProc(TdSta tdSta, String staType) {

        WpBase rsObj = null;
        switch (staType) {
            case "KR": {
                Condition condition = new Condition(WpDesulfuriInfo.class);
                Condition.Criteria criteria = condition.createCriteria();

                criteria.andEqualTo("staId", tdSta.getStaId());
                List<WpDesulfuriInfo> rsList = this.wpDesulfuriInfoDAO.selectByCondition(condition);
                if (Tools.notEmpty(rsList)) {
                    rsObj = rsList.get(0);
                }
            }
            break;
            case "BOF": {
                Condition condition = new Condition(WpConvererInfo.class);
                Condition.Criteria criteria = condition.createCriteria();

                criteria.andEqualTo("staId", tdSta.getStaId());
                List<WpConvererInfo> rsList = this.wpConvererInfoDAO.selectByCondition(condition);
                if (Tools.notEmpty(rsList)) {
                    rsObj = rsList.get(0);
                }
            }
            break;
            case "CAS": {
                Condition condition = new Condition(WpCasInfo.class);
                Condition.Criteria criteria = condition.createCriteria();

                criteria.andEqualTo("staId", tdSta.getStaId());
                List<WpCasInfo> rsList = this.wpCasInfoDAO.selectByCondition(condition);
                if (Tools.notEmpty(rsList)) {
                    rsObj = rsList.get(0);
                }
            }
            break;
            case "LF": {
                Condition condition = new Condition(WpLfInfo.class);
                Condition.Criteria criteria = condition.createCriteria();

                criteria.andEqualTo("staId", tdSta.getStaId());
                List<WpLfInfo> rsList = this.wpLfInfoDAO.selectByCondition(condition);
                if (Tools.notEmpty(rsList)) {
                    rsObj = rsList.get(0);
                }
            }
            break;
            case "CC": {
                if (tdSta.getStaNo().intValue() == 1) {
                    //方坯
                    Condition condition = new Condition(WpBilletInfo.class);
                    Condition.Criteria criteria = condition.createCriteria();

                    criteria.andEqualTo("staId", tdSta.getStaId());
                    List<WpBilletInfo> rsList = this.wpBilletInfoDAO.selectByCondition(condition);
                    if (Tools.notEmpty(rsList)) {
                        rsObj = rsList.get(0);
                    }
                } else {
                    //板坯
                    Condition condition = new Condition(WpSlabInfo.class);
                    Condition.Criteria criteria = condition.createCriteria();

                    criteria.andEqualTo("staId", tdSta.getStaId());
                    List<WpSlabInfo> rsList = this.wpSlabInfoDAO.selectByCondition(condition);
                    if (Tools.notEmpty(rsList)) {
                        rsObj = rsList.get(0);
                    }
                }
            }
            break;
        }

        return rsObj;
    }

    private List<CompositionStandard> listCompStand(String staType) {
        List<String> descType = this.exechangeType(staType);
        if (Tools.empty(descType)) {
            return null;
        }

        List<CompositionStandard> rsList = null;

        Condition condition = new Condition(CompositionStandard.class);
        Condition.Criteria criteria = condition.createCriteria();

        for (String type : descType) {
            criteria.orEqualTo("sampleType", type);
        }

        rsList = this.compositionStandardDAO.selectByCondition(condition);
        return rsList;
    }
}
