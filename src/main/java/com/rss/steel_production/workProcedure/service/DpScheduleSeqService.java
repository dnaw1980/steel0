package com.rss.steel_production.workProcedure.service;

import com.rss.framework.Service;
import com.rss.steel_production.workProcedure.controller.bean.EnterExitStaBean;
import com.rss.steel_production.workProcedure.controller.bean.StaScDataBean;
import com.rss.steel_production.workProcedure.model.DpScheduleDetail;
import com.rss.steel_production.workProcedure.model.DpScheduleSeq;
import com.rss.steel_production.workProcedure.model.gantt.DpGanttBean;
import tk.mybatis.mapper.entity.Condition;

import java.sql.Timestamp;
import java.util.List;

public interface DpScheduleSeqService extends Service<DpScheduleSeq> {

    /**
     * 判断浇次里面是否有调度
     *
     * @param castPlanId
     * @return
     */
    boolean castHasScheduleSeq(String castPlanId);

    /**
     * 添加调度计划
     *
     * @param castPlanId
     * @return
     */
    DpScheduleSeq add(String castPlanId, Timestamp beginTm);

    /**
     * 预览调度修改
     *
     * @param scSeqList 要修改的调度
     * @return
     */
    List<DpScheduleSeq> perSchedulePlan(List<DpScheduleSeq> scSeqList);

    /**
     * 执行调度修改
     *
     * @param scSeqList 要修改的调度
     * @return
     */
    List<DpScheduleSeq> execSchedulePlan(List<DpScheduleSeq> scSeqList);

    /**
     * 返回给定工位的实时调度信息
     *
     * @param stationNames
     * @return
     */
    List<StaScDataBean> staWpData(List<String> stationNames);

    /**
     * 进站操作，忽略出站时间
     *
     * @param enterStaBean
     * @return
     */
    String enterSta(EnterExitStaBean enterStaBean);

    /**
     * 出站操作，忽略进站时间
     *
     * @param exitStaBean
     * @return
     */
    String exitSta(EnterExitStaBean exitStaBean);

    /**
     * 实时甘特图
     *
     * @return
     */
    DpGanttBean realGantt();

    /**
     * 调度明细调整，一回只能调整一个。
     * 如果 scheduleDetailSn为空 则表示新增
     * 如果 orderSn 为 -1 表示删除
     * 如果 planBegin 为空，则自动调整时间，否则强制调整时间
     *
     * @param dpScheduleDetail
     * @return
     */
    String changeScheduleDetail(DpScheduleDetail dpScheduleDetail);


    /**
     * 通过调度ID查询调度序列，并且带明细列表
     *
     * @param scheduleSeqId
     * @return
     */
    DpScheduleSeq showScheduleSeq(String scheduleSeqId);


    void fillDetail(DpScheduleSeq scheduleSeq);


    void fillScDetail(DpScheduleSeq scheduleSeq);
}