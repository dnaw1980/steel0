package com.rss.steel_production.workProcedure.service;

import com.rss.framework.Service;
import com.rss.steel_production.workProcedure.model.DpScheduleSeq;

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
}