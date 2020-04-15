package com.rss.steel_production.schedule.service;

import com.rss.framework.Service;
import com.rss.steel_production.schedule.model.ChargePlan;
import com.rss.steel_production.schedule.model.ProductionOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ChargePlanService extends Service<ChargePlan>{

    /*
     * name:新增炉次
     * note:传入合同list与炉次号，将list打包成一个炉次，返回>0成功
     */
    public ChargePlan addCharge(List<ProductionOrder> list, String chargeNo);
    /*
     * name:删除炉次
     * note:传入DateInterval即几天后，将该天的炉次删除，返回>0成功
     */
    public int deleteChargeByTimes(Date[] dateInterval);
    /*
     * name:删除炉次
     * note:根据状态
     */
    public int deleteChargeByStatus(String status);
    /*
     * name:获取炉次信息
     * note:获取<DateInterval>天后需要浇次的全部合同，返回炉次列表List
     */
    public List<ChargePlan> getChargeListByTimes(Date[] dateInterval);
    /*
     * 获得炉次list，通过chargeUIDList
     */
    public List<ChargePlan> getChargeListByChargeUIDList(List<String> chargeUIDList);
    /*
     * 获得list，by状态
     */
    public List<ChargePlan> getChargeListByStatus(String status);
    /*
     * 获得list，by uid
     */
    public ChargePlan getChargeByNo(String no);
    /*
     * 设置炉次的分配浇次assignCast和浇内序号castSeq
     */
    public int updateChargeByCast(String assignCast, List<ChargePlan> chargeList);
    /*
     * 设置炉次的出铁时间
     */
    public int updateChargeByIronTime(String chargeUID, Date ironTime);
    /*
     * name:获得炉次信息
     * note:传入DateInterval即几天后，返回该天的炉次信息Map<key=炉次号，value=该炉次包含的合同List>
     */
    public Map<Integer, List<ProductionOrder>> getChargeMap(Date[] dateInterval);
    /*
     * name:创建炉次信息（执行组炉的计算）--自动
     * note:传入参数<orderList>合同列表，返回组炉结果Map<key=炉次号，value=该炉次包含的合同List>
     */
    public Map<Integer, List<ProductionOrder>> createChargeMap(Date[] dateInterval);
    /*
     * name:创建炉次信息--人工
     * note:传入参数newMap<key=合同UID，value=该合同所属炉次号>，返回-1错误（逾期等）>1成功
     */
    public ChargePlan setChargeManual(List<String> uids);
    /*
     * 检查合同组炉次是否合规
     */
    public boolean checkIfRight(List<ProductionOrder> polist);
    /*
     * 检车炉次合规否
     */
    public boolean checkIfRightByChargeList(List<ChargePlan> list);
    /*
     * name:获取炉次包含的合同列表
     * note:传入参数chargeUID(chargePlanUID)炉次id，返回该炉次包含的合同List
     */
    public List<ProductionOrder> getOrderListByChargeUID(String chargeUID);
    /*
     * name:工具-计算时间起终点（多日）
     * note:传入DateInterval即从几天后开算，=0则起终点为此刻，终点为多日后的23:59
     */
    public Date[] calculateInterval(int DateInterval, int dates);
    /*
     * name:获取工艺标准数据
     * note:从数据库中读取工艺标准的数据List并处理提取有用的数据如宽度，厚度公差等，
     * 返回Map<key=标准ID，value=上下限，double[0]下限[1]上限>
     */
    public Map<String, double[]> getStandard();
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
     * name:工具-获取炉次的id
     * note:生成并返回一个炉次id，格式是LC+日期+序号
     */
    public String getLCUid(String plantime, int chargeNo);
    /*
     * 根据数据库数据创建编号
     */
    public String getLCNo(String plantime);
}