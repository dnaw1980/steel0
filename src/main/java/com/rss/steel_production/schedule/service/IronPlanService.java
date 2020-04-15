package com.rss.steel_production.schedule.service;

import com.rss.framework.Service;
import com.rss.steel_production.schedule.model.IronPlan;

import java.util.Date;
import java.util.List;

public interface IronPlanService extends Service<IronPlan>{
    /*
     * 获取铁次列表，根据状态
     */
    public List<IronPlan> getIronListByStatus(String[] planStatus);
    /*
     * 获取铁次列表，根据时间段
     */
    public List<IronPlan> getIronListByTimes(Date[] dateInterval);
    /*
     * 获取铁次列表，根据状态和时间段
     */
    public List<IronPlan> getIronListByStatusAndTimes(String[] planStatus, Date[] dateInterval);
    /*
     * 获取铁次列表，根据最早时间
     */
    public List<IronPlan> getIronListByEarlyTime(Date earlyTime);
    /*
     * 更新铁次去向
     */
//    public int updateIronDirection(String ironPlanUID, String ironDirection);
    /*
     * 更新铁次状态
     */
    public int updateIronStatus(String ironPlanUID, String status);
}