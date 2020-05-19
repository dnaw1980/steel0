package com.rss.steel_production.schedule.service;

import com.rss.framework.Service;
import com.rss.steel_production.foundation.model.SteelDevice;
import com.rss.steel_production.schedule.model.ChargePlan;
import com.rss.steel_production.schedule.model.SteelSchedule;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SteelScheduleService extends Service<SteelSchedule> {
    /*
     * 检查
     */
    boolean checkIfRight(List<SteelSchedule> sslist);

    /*
     * 添加调度计划（数据库）
     */
    int addSchedule(SteelSchedule ss);

    /*
     * 删除指定状态的计划
     */
    void deleteScheduleByStatus(String status);

    /*
     * 获得调度计划列表，通过状态
     */
    List<SteelSchedule> getScheduleListByStatus(String status);

    /*
     * 创建调度计划
     */
    Map<String, List<SteelSchedule>> createSchedule(Date[] dateInterval);

    /*
     * name:获得浇次信息（需要排程的一天炉次）
     * note:传入DateInterval即几天后，返回二维数组，第一维是浇次序号，第二维是浇次内炉次序号，值为炉次信息
     */
    ChargePlan[][] getChargeArr(Date[] dateInterval);

    /*
     * 获得设备的最早可用时间Map,key=工序名称如：1#CC，value=最早可用时间
     */
    Map<String, Date> getDeviceEarlyAvailableTimeMap(List<SteelDevice> deviceList);

    /*
     * 获得设备的最早可用时间
     */
    Date getDeviceEarlyAvailableTime(String stationNo);

    /*
     * name:获取工艺标准数据-调度
     * note:从数据库中读取工艺标准的数据List并处理提取有用的数据如工位间时间等，
     * 返回Map<key=标准ID，value=上下限，double[0]下限[1]上限>
     */
    Map<String, double[]> getStandard();
    
    /**
     * 通过工序名称获取该页面上的时间轴和上下工位相关信息
     * @param orgName
     * @return
     */
    Map<String,Object> getProcessInfo(String orgName);
    
    
    void alterBlastOrder(String blastOrder, String id);
}
