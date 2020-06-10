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
import com.rss.steel_production.workProcedure.controller.bean.EnterExitStaBean;
import com.rss.steel_production.workProcedure.controller.bean.StaScDataBean;
import com.rss.steel_production.workProcedure.dao.*;
import com.rss.steel_production.workProcedure.model.*;
import com.rss.steel_production.workProcedure.model.gantt.Charge;
import com.rss.steel_production.workProcedure.model.gantt.DpGanttBean;
import com.rss.steel_production.workProcedure.model.gantt.Router;
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
    private DpWorkProcDAO dpWorkProcDAO;

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
//        List<DpStaScheduleTm> staScheduleTmList = this.dpStaScheduleTmDAO.selectAll();

        //工序MAP
//        Map<String, List<DpStaScheduleTm>> workProcMap = new HashMap<String, List<DpStaScheduleTm>>();
        // key 是工序ID，value 是每个工序对应的工位
        Map<String, List<DpStaScheduleTm>> workProcMap = genWorkProcMap();
//        for (DpStaScheduleTm staScheduleTm : staScheduleTmList) {
//            List<DpStaScheduleTm> list = workProcMap.get(staScheduleTm.getWorkProcId());
//            if (list == null) {
//                list = new ArrayList<DpStaScheduleTm>();
//                workProcMap.put(staScheduleTm.getWorkProcId(), list);
//            }
//            list.add(staScheduleTm);
//        }

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
     * 进站操作，忽略出站时间
     *
     * @param enterStaBean
     * @return
     */
    @Override
    public String enterSta(EnterExitStaBean enterStaBean) {
        //TODO
        /*
        1、根据调度ID，查询对应的调度明细，按 order_sn 排序。
        2、根据站点名，查询站点信息。通过站点信息和调度ID查询对应的调度明细。
        3、如果有对应的调度明细。
            3.1、遍历之前查出来的调度明细，把当前站点以前的明细全标记为完成状态。如果当前站点有在进行的调度明细，标记为完成，记录出站时间。
            3.2、记录当前工位的进站时间，将温度、重量记录到对应的工序信息中，完成。
        4、如果没有对应的调度明细，根据本工位的 workProcId 和 调度ID 查找sta_sc_detail 本工位类型的调度明细。
            4.1、如果找到了，查找对应的明细信息，替换明细对应的工位信息。进入3.1、3.2操作。
            4.2、如果没找到，说明计划中没有，如果当前站点有在进行的调度明细，标记为完成，记录出站时间。
                添加新的调度明细，状态为2，顺序暂时定为0，添加工序信息。
         */
        //1、根据调度ID，查询对应的调度明细，按 order_sn 排序。
        List<DpScheduleDetail> detailList = null;
        {
            Condition condition = new Condition(DpScheduleDetail.class);
            condition.setOrderByClause("order_sn asc");

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", enterStaBean.getScheduleId());
            detailList = this.dpScheduleDetailDAO.selectByCondition(condition);
        }

        //2、根据站点名，查询站点信息。通过站点信息和调度ID查询对应的调度明细。
        TdSta currSta = null;
        {
            Condition condition = new Condition(TdSta.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleStation", enterStaBean.getStationName());

            List<TdSta> tsList = this.tdStaDAO.selectByCondition(condition);
            if (Tools.empty(tsList)) {
                return "没有对应的工位！";
            }
            currSta = tsList.get(0);
        }

        //通过站点信息和调度ID查询对应的调度明细。
        DpStaScDetail staScDetail = null;
        {
            Condition condition = new Condition(DpStaScDetail.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", enterStaBean.getScheduleId());
            criteria.andEqualTo("staId", currSta.getStaId());

            List<DpStaScDetail> staScDetailList = this.dpStaScDetailDAO.selectByCondition(condition);
            if (Tools.notEmpty(staScDetailList)) {
                staScDetail = staScDetailList.get(0);
            }
        }

        /*
        3、如果有对应的调度明细。
            3.1、遍历之前查出来的调度明细，把当前站点以前的明细全标记为完成状态。如果当前站点有在进行的调度明细，标记为完成，记录出站时间。
            3.2、记录当前工位的进站时间，将温度、重量记录到对应的工序信息中，完成。
         */
        if (staScDetail != null) {
            this.do3(enterStaBean, detailList, currSta, staScDetail);
        } else {
            /*
            4、如果没有对应的调度明细，根据本工位的 workProcId 和 调度ID 查找sta_sc_detail 本工位类型的调度明细。
             */

            Condition condition = new Condition(DpStaScDetail.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("workProcId", currSta.getWorkProcId());
            criteria.andEqualTo("scheduleSeqId", enterStaBean.getScheduleId());

            List<DpStaScDetail> ssdList = this.dpStaScDetailDAO.selectByCondition(condition);

            if (Tools.notEmpty(ssdList)) {
                //  4.1、如果找到了，查找对应的明细信息，替换明细对应的工位信息。进入3.1、3.2操作。
                DpScheduleDetail detail = this.dpScheduleDetailDAO.selectByPrimaryKey(ssdList.get(0).getScheduleDetailSn());
                detail.setStaId(currSta.getStaId());
                this.dpScheduleDetailDAO.updateByPrimaryKey(detail);

                //重新查 detailList
                Condition detailCondy = new Condition(DpScheduleDetail.class);
                detailCondy.setOrderByClause("order_sn asc");

                Condition.Criteria detailCri = detailCondy.createCriteria();
                detailCri.andEqualTo("scheduleSeqId", enterStaBean.getScheduleId());
                detailList = this.dpScheduleDetailDAO.selectByCondition(detailCondy);

                //TODO 做3.1，3.2
                this.do3(enterStaBean, detailList, currSta, staScDetail);

            } else {
                //  4.2、如果没找到，说明计划中没有，如果当前站点有在进行的调度明细，标记为完成，记录出站时间。

                Condition _detailCondy = new Condition(DpScheduleDetail.class);

                Condition.Criteria _detailCri = condition.createCriteria();
                _detailCri.andEqualTo("state", DpScheduleDetail.STATE_EXEC);
                _detailCri.andEqualTo("staId", currSta.getStaId());

                List<DpScheduleDetail> _detailList = this.dpScheduleDetailDAO.selectByCondition(_detailCondy);
                if (Tools.notEmpty(_detailList)) {
                    DpScheduleDetail _currDetiail = _detailList.get(0);
                    _currDetiail.setState(DpScheduleDetail.STATE_FINISH);
                    _currDetiail.setActEnd(DateUtil.getDateTime());
                    this.dpScheduleDetailDAO.updateByPrimaryKey(_currDetiail);
                }

                //  添加新的调度明细，状态为2，顺序暂时定为0，添加工序信息。
                DpScheduleDetail detail = new DpScheduleDetail();
                detail.setScheduleSeqId(enterStaBean.getScheduleId());
                detail.setStaId(currSta.getStaId());
                detail.setState(DpScheduleDetail.STATE_EXEC);
                //TODO 需要找到 当前调度之前正在 执行的工位，找到顺序号，添加，后面的顺序号顺延
                detail.setOrderSn(0);
                detail.setActBegin(enterStaBean.getActualEnter());
                // 重新计算计划出站时间
                DpWorkProc wp = this.dpWorkProcDAO.selectByPrimaryKey(currSta.getWorkProcId());
                Timestamp endPlan = DateUtil.minuteAdd(enterStaBean.getActualEnter(), wp.getWorkCycle());
                detail.setPlanEnd(endPlan);
                this.dpScheduleDetailDAO.insertUseGeneratedKeys(detail);

                //TODO 添加工序信息
                this.putWorkProcInfo(currSta, detail, enterStaBean);
            }

        }
        return "进站成功";
    }

    private void do3(final EnterExitStaBean enterStaBean,
                     final List<DpScheduleDetail> detailList,
                     final TdSta currSta,
                     final DpStaScDetail staScDetail) {
        //3.1、遍历之前查出来的调度明细，把当前站点以前的明细全标记为完成状态。
        for (DpScheduleDetail detail : detailList) {
            if (detail.getStaId().equals(currSta.getStaId())) {
                //如果当前站点有在进行的调度明细，标记为完成，记录出站时间。
                //查询当前站点正在进行的明细
                {
                    Condition condition = new Condition(DpScheduleDetail.class);

                    Condition.Criteria criteria = condition.createCriteria();
                    criteria.andEqualTo("state", DpScheduleDetail.STATE_EXEC);
                    criteria.andEqualTo("staId", currSta.getStaId());

                    List<DpScheduleDetail> _detailList = this.dpScheduleDetailDAO.selectByCondition(condition);
                    if (Tools.notEmpty(_detailList)) {
                        DpScheduleDetail _currDetiail = _detailList.get(0);
                        _currDetiail.setState(DpScheduleDetail.STATE_FINISH);
                        _currDetiail.setActEnd(DateUtil.getDateTime());
                        this.dpScheduleDetailDAO.updateByPrimaryKey(_currDetiail);
                    }
                }
                break;
            }
            //其它的标记为完成
            {
                detail.setState(DpScheduleDetail.STATE_FINISH);
                detail.setActEnd(DateUtil.getDateTime());
                this.dpScheduleDetailDAO.updateByPrimaryKey(detail);
            }
        }

            /*
        3.2、记录当前工位的进站时间，将温度、重量记录到对应的工序信息中，完成。
         */
        Integer detailSn = staScDetail.getScheduleDetailSn();
        DpScheduleDetail _currDetail = this.dpScheduleDetailDAO.selectByPrimaryKey(detailSn);
        _currDetail.setActBegin(enterStaBean.getActualEnter());
        _currDetail.setState(DpScheduleDetail.STATE_EXEC);
        // 重新计算计划出站时间
        DpWorkProc wp = this.dpWorkProcDAO.selectByPrimaryKey(currSta.getWorkProcId());
        Timestamp endPlan = DateUtil.minuteAdd(enterStaBean.getActualEnter(), wp.getWorkCycle());
        _currDetail.setPlanEnd(endPlan);
        this.dpScheduleDetailDAO.updateByPrimaryKey(_currDetail);

        //TODO 添加工序信息
        this.putWorkProcInfo(currSta, _currDetail, enterStaBean);
    }

    private void putWorkProcInfo(TdSta sta, DpScheduleDetail detail, EnterExitStaBean enterStaBean) {

    }

    /**
     * 出站操作，忽略进站时间
     *
     * @param exitStaBean
     * @return
     */
    @Override
    public String exitSta(EnterExitStaBean exitStaBean) {
        //TODO
        //2、根据站点名，查询站点信息。通过站点信息和调度ID查询对应的调度明细。
        TdSta currSta = null;
        {
            Condition condition = new Condition(TdSta.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleStation", exitStaBean.getStationName());

            List<TdSta> tsList = this.tdStaDAO.selectByCondition(condition);
            if (Tools.empty(tsList)) {
                return "没有对应的工位！";
            }
            currSta = tsList.get(0);
        }
        DpStaScDetail staScDetail = null;
        {
            Condition condition = new Condition(DpStaScDetail.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", exitStaBean.getScheduleId());
            criteria.andEqualTo("staId", currSta.getStaId());

            List<DpStaScDetail> staScDetailList = this.dpStaScDetailDAO.selectByCondition(condition);
            if (Tools.empty(staScDetailList)) {
                return "没有对应的站点信息";
            }

            staScDetail = staScDetailList.get(0);
        }

        DpScheduleDetail scheduleDetail = this.dpScheduleDetailDAO.selectByPrimaryKey(staScDetail.getScheduleDetailSn());

        scheduleDetail.setActEnd(exitStaBean.getActualExit());
        scheduleDetail.setState(DpScheduleDetail.STATE_FINISH);

        this.dpScheduleDetailDAO.updateByPrimaryKey(scheduleDetail);

        //如果是CC，表示最后一个，将调度设置成完成 3
        DpScheduleSeq seq = this.showScheduleSeq(scheduleDetail.getScheduleSeqId());
        if (seq.getDetailList().get(seq.getDetailList().size() - 1).getScheduleDetailSn().intValue() == scheduleDetail.getScheduleDetailSn().intValue()) {
            seq.setState(DpScheduleSeq.STATE_FINISH);
            seq.setEndTm(exitStaBean.getActualExit());
            this.dpScheduleSeqDAO.updateByPrimaryKey(seq);
        }

        //TODO 更新工序信息

        return "出站成功";
    }

    /**
     * 实时甘特图
     *
     * @return
     */
    @Override
    public DpGanttBean realGantt() {

        /*
        1、查工序信息表，生成工序名List
        2、查询状态为1,2的调度信息，遍历调度信息，用调度ID查，sta_sc_detail
        生成Router
         */

        DpGanttBean rsBean = new DpGanttBean();

        //  1、查工序信息表，生成工序名List
        List<DpWorkProc> workProcList = this.dpWorkProcDAO.selectAll();
        List<String> wpName = new ArrayList<String>();
        Map<String, String> wpMap = new HashMap<String, String>();
        for (DpWorkProc wp : workProcList) {
            wpName.add(wp.getWorkProcNm());
            wpMap.put(wp.getWorkProcId(), wp.getWorkProcNm());
        }
        rsBean.setRouterNames(wpName);


        //2、查询状态为1的调度信息，
        List<DpScheduleSeq> scheduleSeqList = null;
        {
            Condition condition = new Condition(DpScheduleSeq.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andGreaterThan("state", DpScheduleSeq.STATE_PLAN);
            criteria.andLessThan("state", DpScheduleSeq.STATE_FINISH);

            scheduleSeqList = this.dpScheduleSeqDAO.selectByCondition(condition);
        }

        if (Tools.empty(scheduleSeqList)) {
            return rsBean;
        }

        List<Charge> chargeList = new ArrayList<Charge>(scheduleSeqList.size());
        rsBean.setCharges(chargeList);
        for (DpScheduleSeq scheduleSeq : scheduleSeqList) {
//            遍历调度信息，用调度ID查，sta_sc_detail
//                    生成Router
            Charge charge = new Charge();
            charge.setName(""
                    + (scheduleSeq.getBlastNo() == null ? "" : scheduleSeq.getBlastNo())
                    + ","
                    + (scheduleSeq.getChargeNo() == null ? "" : scheduleSeq.getChargeNo())
            );

            Condition condition = new Condition(DpStaScDetail.class);
            condition.setOrderByClause("order_sn asc");

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", scheduleSeq.getScheduleSeqId());

            List<DpStaScDetail> detailList = this.dpStaScDetailDAO.selectByCondition(condition);

            List<Router> routerList = new ArrayList<Router>(detailList.size());
            charge.setRouters(routerList);

            int i = 0;
            for (DpStaScDetail detail : detailList) {

                Router router = new Router();
                routerList.add(router);

                Timestamp beginTm = detail.getActBegin() != null ? detail.getActBegin() : detail.getPlanBegin();
                if (i == 0) {
                    charge.setBeginTime(beginTm);
                }

                router.setBeginTime(beginTm);
                Timestamp endTm = detail.getActEnd() != null ? detail.getActEnd() : detail.getPlanEnd();

                charge.setEndTime(endTm);
                router.setEndTime(endTm);

                router.setName(wpMap.get(detail.getWorkProcId()));

                //状态
                int state = detail.getDetailState();
                if (state == 2) {
                    router.setState(0);
                } else if (state < 2) {
                    router.setState(-1);
                } else {
                    router.setState(1);
                }

                i++;
            }

            chargeList.add(charge);
        }


        return rsBean;
    }

    /**
     * 调度明细调整，一回只能调整一个。
     * 如果 scheduleDetailSn为空 则表示新增
     * 如果 orderSn 为 -1 表示删除
     * 如果 planBegin 为空，则自动调整时间，否则强制调整时间
     *
     * @param dpScheduleDetail
     * @return
     */
    @Override
    public String changeScheduleDetail(DpScheduleDetail dpScheduleDetail) {
        /*
        1、判断输入参数的合法性。
        2、将参数写入数据库。
        3、重新调整数据库中的调度明细，当遇到参数同一个明细时，才开始进行修改。要注意参数时需要修改的内容。
         */

        //开始明细的主键
        Integer beginDetailSn = 0;
        //开始明细，如果开始明细为 null 表示，删除操作。如果不为空，需要判断相关属性。
        DpScheduleDetail beginDetail = null;
        /*
        1、判断输入参数的合法性。
         */

        //查询明细对应的调序。
        if (Tools.empty(dpScheduleDetail.getScheduleSeqId())) {
            return "调度明细对应的调度ID为空！";
        }

        final DpScheduleSeq descScheduleSeq = this.showScheduleSeq(dpScheduleDetail.getScheduleSeqId());

        if (dpScheduleDetail.getScheduleDetailSn() == null
                || dpScheduleDetail.getScheduleDetailSn().intValue() == 0) {
            /*新增明细*/
            //遍历 descScheduleSeq 找到 orderSn 相等的那个，就是要插入的位置
            boolean finded = false;
            for (DpScheduleDetail posDetail : descScheduleSeq.getDetailList()) {

                if (finded) {
                    posDetail.setOrderSn(posDetail.getOrderSn().intValue() + 1);
                    this.dpScheduleDetailDAO.updateByPrimaryKey(posDetail);
                    continue;
                }

                if (posDetail.getOrderSn().intValue() < dpScheduleDetail.getOrderSn().intValue()) {
                    continue;
                }

                this.dpScheduleDetailDAO.insertUseGeneratedKeys(dpScheduleDetail);
                beginDetailSn = dpScheduleDetail.getScheduleDetailSn().intValue();
                beginDetail = dpScheduleDetail;

                //当前这个序号也是增1
                posDetail.setOrderSn(posDetail.getOrderSn().intValue() + 1);
                this.dpScheduleDetailDAO.updateByPrimaryKey(posDetail);
            }

        } else {
            //先查询明细，看是否已经执行。
            DpScheduleDetail tmpDetail = this.dpScheduleDetailDAO.selectByPrimaryKey(dpScheduleDetail.getScheduleDetailSn());
            if (tmpDetail.getState().intValue() >= DpScheduleDetail.STATE_EXEC) {
                return "当前工序已经执行或完成，不能删除。可以让当前工位执行出站操作，或下个工位直接进站！";
            }

            if (dpScheduleDetail.getState() != null && dpScheduleDetail.getState().intValue() == DpScheduleDetail.STATE_FAIL) {
                /*删除明细*/
                //删除前需要找到被删除明细后面的工序，做为开始明细。

                DpScheduleDetail nextDetail = null;
                boolean hasFind = false;
                for (DpScheduleDetail posDetail : descScheduleSeq.getDetailList()) {
                    if (hasFind) {
                        nextDetail = posDetail;
                        break;
                    }

                    if (posDetail.getScheduleDetailSn().intValue() == dpScheduleDetail.getScheduleDetailSn().intValue()) {
                        hasFind = true;
                    }
                }

                //如果被删除的是最后一个工序，就找到下一个调度的第一个未开始工序做为开始工序。
                if (nextDetail == null) {
                    //说明没找到，查调度序列，按开始时间升序，查状态不为 3 的

                    Condition condition = new Condition(DpScheduleSeq.class);
                    condition.setOrderByClause("begin_tm asc");

                    Condition.Criteria criteria = condition.createCriteria();
                    criteria.andNotEqualTo("state", DpScheduleSeq.STATE_FAIL);
                    criteria.andNotEqualTo("state", DpScheduleSeq.STATE_FINISH);

                    List<DpScheduleSeq> seqList = this.dpScheduleSeqDAO.selectByCondition(condition);
                    //descScheduleSeq
                    boolean found = false;
                    for (DpScheduleSeq posSeq : seqList) {
                        if (found) {
                            //todo 查明细
                            DpScheduleSeq _seq = this.showScheduleSeq(posSeq.getScheduleSeqId());

                            //判断内层是否找到。
                            boolean _f = false;
                            for (DpScheduleDetail detail : _seq.getDetailList()) {
                                if (detail.getState().intValue() == DpScheduleDetail.STATE_PLAN
                                        || detail.getState().intValue() == DpScheduleDetail.STATE_SEND) {
                                    //找到
                                    nextDetail = detail;
                                    _f = true;
                                    break;
                                }
                            }

                            //找到，跳出
                            if (_f) {
                                break;
                            }
                        } else {
                            if (descScheduleSeq.getScheduleSeqId().equals(posSeq.getScheduleSeqId())) {
                                found = true;
                            }
                        }
                    }
                }

                //执行删除，就是修改为作废
                this.dpScheduleDetailDAO.updateByPrimaryKey(dpScheduleDetail);

                beginDetailSn = nextDetail.getScheduleDetailSn();
            } else {
                //调整工序
                this.dpScheduleDetailDAO.updateByPrimaryKeySelective(dpScheduleDetail);
                beginDetail = dpScheduleDetail;
                beginDetailSn = dpScheduleDetail.getScheduleDetailSn();
            }
        }

        /*
        TODO
        3、重新调整数据库中的调度明细，当遇到参数同一个明细时(beginDetailSn, beginDetail)，
        才开始进行修改。要注意参数时需要修改的内容。
         */
        /*
        先查询各个有效工位最早开始时间，就是每个工位当前调度明细（状态为2）的计划结束时间。dp_sta_schedule_tm
        遍历未完成的调度，依次更新每个工位的最早开始时间。
        当遇到参数工位时，开始重新计算。
        */
        //TODO 工序及开始时间，应该查最后一个开始时间，而不是最早开始时间, end_tm

        //先查询各个有效工位最早开始时间，就是每个工位当前调度明细（状态为2）的计划结束时间。dp_sta_schedule_tm
        // key 是工序ID，value 是每个工序对应的工位
        Map<String, List<DpStaScheduleTm>> workProcMap = genWorkProcMap();

        //遍历未完成的调度，依次更新每个工位的最早开始时间。
        List<DpScheduleSeq> seqList = null;
        {
            Condition condition = new Condition(DpScheduleSeq.class);
            condition.setOrderByClause("begin_tm asc");

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andNotEqualTo("state", DpScheduleSeq.STATE_FAIL);
            criteria.andNotEqualTo("state", DpScheduleSeq.STATE_FINISH);

            seqList = this.dpScheduleSeqDAO.selectByCondition(condition);
        }

        if (Tools.empty(seqList)) {
            return "没有有效的可调度炉次";
        }


        ///////////////////////////////////////////////////////////////////////////////
        //准备工序运输时间表
        List<DpWorkProcTrans> transList = this.dpWorkProcTransDAO.selectAll();
        //是否找到目标
        boolean found = false;
        for (DpScheduleSeq _seq : seqList) {
            this.fillScDetail(_seq);
int i = 0;
            DpStaScDetail lastDtl = null;
            for (DpStaScDetail _dtl : _seq.getScDetailList()) {
                if (_dtl.getScheduleDetailSn().intValue() == DpScheduleDetail.STATE_FAIL
                        || _dtl.getScheduleDetailSn().intValue() == DpScheduleDetail.STATE_EXEC
                        || _dtl.getScheduleDetailSn().intValue() == DpScheduleDetail.STATE_FINISH
                ) {
                    continue;
                }
                System.out.println(i++);
                //取工位信息
                // key 是工序ID，value 是每个工序对应的工位 workProcMap
                if (!found) {
                    System.out.println("没找到");
                    //没找到，判断一下，是不是这个
                    if (!_dtl.getScheduleDetailSn().equals(beginDetailSn)) {
                        //没找到，不是
                        List<DpStaScheduleTm> tmList = workProcMap.get(_dtl.getWorkProcId());
                        tmList.get(_dtl.getStaNo() - 1).setBeginTm(_dtl.getPlanEnd());
//                        System.out.println(tmList.get(_dtl.getStaNo() - 1).getBeginTm());
//                        //开始明细的主键
//                        Integer beginDetailSn = 0;
//                        //开始明细，如果开始明细为 null 表示，删除操作。如果不为空，需要判断相关属性。
//                        DpScheduleDetail beginDetail = null;

                        System.out.println("_dtl begin:"+_dtl.getPlanBegin() + "\tend:"+_dtl.getPlanEnd());
                        if(lastDtl != null) {
                            System.out.println("lastDtl begin:" + lastDtl.getPlanBegin() + "\tend:" + lastDtl.getPlanEnd());
                        }

                    } else {
                        //找到了
                        found = true;

                        //判断 beginDetail 是否为空。如果为空，按普通调整。
                        System.out.println("_dtl begin:"+_dtl.getPlanBegin() + "\tend:"+_dtl.getPlanEnd());
                        if(lastDtl != null) {
                            System.out.println("lastDtl begin:" + lastDtl.getPlanBegin() + "\tend:" + lastDtl.getPlanEnd());
                        }
                        if (beginDetail == null) {
                            //按普通调整
                            //取当前工位
                            List<DpStaScheduleTm> tmList = workProcMap.get(_dtl.getWorkProcId());
                            DpStaScheduleTm _tm = tmList.get(_dtl.getStaNo() - 1);
                            //取开始时间，肯定不对，应该考虑上道工序的完成时间。

                            DpScheduleDetail dsd = new DpScheduleDetail();
                            dsd.setScheduleDetailSn(_dtl.getScheduleDetailSn());

                            if(lastDtl != null){
                                //TODO 取运输时间

                                if(lastDtl.getPlanEnd().after(_tm.getBeginTm())){
                                    dsd.setPlanBegin(lastDtl.getPlanEnd());
                                }
                            } else {
                                //设置本工序的开始时间为工位开始时间。
                                dsd.setPlanBegin(_tm.getBeginTm());
                            }

                            if (lastDtl != null) {

                                for (DpWorkProcTrans trans : transList) {
                                    if (trans.getSrcWorkProcId().equals(lastDtl.getWorkProcId())
                                            && trans.getDescWorkProcId().equals(_dtl.getWorkProcId())
                                    ) {
                                        dsd.setPlanBegin(DateUtil.minuteAdd(dsd.getPlanBegin(), trans.getTransCycle()));
                                    }

                                }
                            }

                            //加上加工时间，设置本工序结束时间。
                            dsd.setPlanEnd(DateUtil.minuteAdd(dsd.getPlanBegin(), _tm.getWorkCycle().intValue()));
                            
                            _dtl.setPlanBegin(dsd.getPlanBegin());
                            _dtl.setPlanEnd(dsd.getPlanEnd());
                            //更新为工位的开始时间
                            _tm.setBeginTm(dsd.getPlanEnd());

                            //写数据库
                            this.dpScheduleDetailDAO.updateByPrimaryKeySelective(dsd);
                            //结束
                        } else {

                            List<DpStaScheduleTm> tmList = workProcMap.get(_dtl.getWorkProcId());
                            //如果不为空，计算结束时间
                            if (beginDetail != null) {
                                //先判断工位，如果工们为空，要分配一个工位
                                if (Tools.empty(beginDetail.getStaId())) {

                                    //比较早的那个工位
                                    DpStaScheduleTm beginTm = null;
                                    for (DpStaScheduleTm tm : tmList) {
                                        if (beginTm == null) {
                                            beginTm = tm;
                                        } else {
                                            if (beginTm.getBeginTm().after(tm.getBeginTm())) {
                                                beginTm = tm;
                                            }
                                        }
                                    }

                                    beginDetail.setStaId(beginTm.getStaId());
                                    _dtl.setStaId(beginTm.getStaId());
                                    _dtl.setStaNo(beginTm.getStaNo());

                                }
                                DpStaScheduleTm _tm = tmList.get(_dtl.getStaNo() - 1);

                                //再判断开始时间
                               if (beginDetail.getPlanBegin() == null) {

                                    if(lastDtl.getPlanEnd().after(_tm.getBeginTm())){
                                        beginDetail.setPlanBegin(lastDtl.getPlanEnd());
                                    }  else {
                                        beginDetail.setPlanBegin(_tm.getBeginTm());
                                    }
                                }



                                beginDetail.setPlanEnd(
                                        DateUtil.minuteAdd(
                                                beginDetail.getPlanBegin(), _tm.getWorkCycle().intValue()
                                        )
                                );

                                _tm.setBeginTm(beginDetail.getPlanEnd());
                            } else {
                                //beginDetail.getPlanBegin() 为空
                            }
                            this.dpScheduleDetailDAO.updateByPrimaryKeySelective(beginDetail);

                        }
                    }

                } else {
                    System.out.println("找到了");
                    System.out.println("_dtl begin:"+_dtl.getPlanBegin() + "\tend:"+_dtl.getPlanEnd());
                    if(lastDtl != null) {
                        System.out.println("lastDtl begin:" + lastDtl.getPlanBegin() + "\tend:" + lastDtl.getPlanEnd());
                    }

                    //跟上面的按普通调整是一样的。
                    //取当前工位
                    List<DpStaScheduleTm> tmList = workProcMap.get(_dtl.getWorkProcId());
                    DpStaScheduleTm _tm = tmList.get(_dtl.getStaNo() - 1);
                    //取开始时间，肯定不对，应该考虑上道工序的完成时间。

                    DpScheduleDetail dsd = new DpScheduleDetail();
                    dsd.setScheduleDetailSn(_dtl.getScheduleDetailSn());

                    //设置本工序的开始时间为工位开始时间。
//                    dsd.setPlanBegin(_tm.getBeginTm());

                    if(lastDtl != null){
                        //TODO 取运输时间

                        if(lastDtl.getPlanEnd().after(_tm.getBeginTm())){
                            dsd.setPlanBegin(lastDtl.getPlanEnd());
                        } else {
                            dsd.setPlanBegin(_tm.getBeginTm());
                        }
                    } else {
                        //设置本工序的开始时间为工位开始时间。
                        dsd.setPlanBegin(_tm.getBeginTm());
                    }

                    if (lastDtl != null) {

                        for (DpWorkProcTrans trans : transList) {
                            if (trans.getSrcWorkProcId().equals(lastDtl.getWorkProcId())
                                    && trans.getDescWorkProcId().equals(_dtl.getWorkProcId())
                            ) {
                                dsd.setPlanBegin(DateUtil.minuteAdd(dsd.getPlanBegin(), trans.getTransCycle()));
                            }

                        }
                    }

                    //加上加工时间，设置本工序结束时间。
                    dsd.setPlanEnd(DateUtil.minuteAdd(dsd.getPlanBegin(), _tm.getWorkCycle().intValue()));



                    _dtl.setPlanBegin(dsd.getPlanBegin());
                    _dtl.setPlanEnd(dsd.getPlanEnd());
                    //更新为工位的开始时间
                    _tm.setBeginTm(dsd.getPlanEnd());

                    //写数据库
                    this.dpScheduleDetailDAO.updateByPrimaryKeySelective(dsd);
                    //结束

                }

                lastDtl = _dtl;
            }
        }

        return null;
    }

    private Map<String, List<DpStaScheduleTm>> genWorkProcMap() {
        //从 add 方法复制过来的
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
        //复制完
        return workProcMap;
    }

    /**
     * 通过调度ID查询调度序列，并且带明细列表
     *
     * @param scheduleSeqId
     * @return
     */
    @Override
    public DpScheduleSeq showScheduleSeq(String scheduleSeqId) {
        if (Tools.empty(scheduleSeqId)) {
            return null;
        }

        DpScheduleSeq scheduleSeq = this.dpScheduleSeqDAO.selectByPrimaryKey(scheduleSeqId);
        if (scheduleSeq == null) {
            return null;
        }

        this.fillDetail(scheduleSeq);
        return scheduleSeq;
    }

    private void fillDetail(DpScheduleSeq scheduleSeq) {
        Condition condition = new Condition(DpScheduleDetail.class);
        condition.setOrderByClause("order_sn asc");

        Condition.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("scheduleSeqId", scheduleSeq.getScheduleSeqId());

        scheduleSeq.setDetailList(this.dpScheduleDetailDAO.selectByCondition(condition));
    }

    private void fillScDetail(DpScheduleSeq scheduleSeq) {
        Condition condition = new Condition(DpStaScDetail.class);
        condition.setOrderByClause("order_sn asc");

        Condition.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("scheduleSeqId", scheduleSeq.getScheduleSeqId());

        scheduleSeq.setScDetailList(this.dpStaScDetailDAO.selectByCondition(condition));
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
