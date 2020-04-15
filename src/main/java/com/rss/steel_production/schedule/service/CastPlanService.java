package com.rss.steel_production.schedule.service;

import com.rss.framework.Service;
import com.rss.steel_production.schedule.model.CastPlan;
import com.rss.steel_production.schedule.model.ChargePlan;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CastPlanService extends Service<CastPlan>{
    /*
     * name:新增浇次
     * note:传入炉次list和浇次号，将list打包成一个浇次，返回>0成功
     */
    public CastPlan addCast(List<ChargePlan> list, String castNo);
    /*
     * name:删除浇次
     * note:传入DateInterval即几天后，将该天的浇次删除，返回>0成功
     */
    public int deleteCastByTimes(Date[] dateInterval);
    /*
     * name:删除浇次
     * note:根据状态
     */
    public int deleteCastByStatus(String status);
    /*
     * name:获取浇次信息
     * note:获取dateInterval期间的全部浇次，返回List
     */
    public List<CastPlan> getCastListByTimes(Date[] dateInterval);
    /*
     * 获得list通过状态
     */
    public List<CastPlan> getCastListByStatus(String status);
    /*
     * 获得通过uid
     */
    public CastPlan getCastByNo(String no);
    /*
     * name:获得浇次信息
     * note:传入DateInterval即几天后，返回该天的浇次信息Map<key=浇次号，value=该浇次包含的炉次List>
     */
    public Map<Integer, List<ChargePlan>> getCastMap(Date[] dateInterval);
    /*
     * name:创建浇次信息--自动
     * note:传入参数<chargeList>炉次列表，返回组炉结果Map<key=浇次号，value=该浇次包含的炉次List>
     */
    public Map createCastMap(Date[] dateInterval);
    /*
     * name:创建浇次信息--人工
     * note:传入参数newMap<key=炉次UID，value=该合同所属浇次号>，返回-1错误（逾期等）>1成功
     */
    public CastPlan setCastManual(List<String> uids);
    /*
     * 检查炉次组浇次是否合规
     */
    public boolean checkIfRight(List<ChargePlan> chlist);
    /*
     * 检车浇次合规否
     */
    public boolean checkIfRightByCastList(List<CastPlan> list);
    /*
     * name:获取浇次包含的炉次列表
     * note:传入参数castUID(castPlanUID)浇次id，返回该浇次包含的炉次List
     */
    public List<ChargePlan> getChargeListByCastUID(String castUID);
    /*
     * name:获取工艺标准数据-浇次
     * note:从数据库中读取工艺标准的数据List并处理提取有用的数据如工位间时间等，
     * 返回Map<key=标准ID，value=上下限，double[0]下限[1]上限>
     */
    public Map<String, double[]> getStandard();
    /*
     * 交期string转成date
     */
    public Date stringToDate(String targetTimeStr);
    /*
     * name:工具-计算时间起终点（多日）
     * note:传入DateInterval即从几天后开算，=0则起终点为此刻，终点为多日后的23:59
     */
    public Date[] calculateInterval(int DateInterval, int dates);
    /*
     * name:工具-工艺标准符合验证1
     * note:传入数值，工艺标准，判断数值是否小于规定公差的最大值，返回Boolean
     */
    public boolean isLessThanInterval(double num, double[] limit);
    /*
     * name:工具-工艺标准符合验证2
     * note:传入数值，工艺标准，判断数值是否在公差之间，返回Boolean
     */
    public boolean isinInterval(double num, double[] limit);
    /*
     * name:工具-获取浇次的id
     * note:生成并返回一个浇次id，格式是JC+日期+序号
     */
    public String getJCUid(String plantime, int castNo);
    /*
     * 根据数据库数据创建编号
     */
    public String getJCNo(String plantime);
}
