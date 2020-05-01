package com.rss.steel_production.schedule.impl;

import javax.annotation.Resource;

import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.foundation.model.ProcessStandard;
import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.schedule.model.ChargePlanExample;
import com.rss.steel_production.schedule.model.ProductionOrder;
import com.rss.steel_production.schedule.service.CastPlanService;
import com.rss.steel_production.schedule.service.ProductionOrderService;
import com.rss.steel_production.schedule.model.ChargePlanExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.schedule.model.ChargePlan;
import com.rss.steel_production.schedule.dao.ChargePlanDAO;
import com.rss.steel_production.schedule.service.ChargePlanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ChargePlanImpl extends AbstractService<ChargePlan> implements ChargePlanService {
    @Resource
    private ChargePlanDAO chargePlanDAO;
    @Autowired
    ProductionOrderService productionOrderService;
    @Autowired
    ProcessStandardService processStandardService;
    @Autowired
    CastPlanService castPlanService;

    /*
     * name:新增炉次
     * note:传入合同list与炉次号，将list打包成一个炉次，返回>0成功
     */
    @Override
    public ChargePlan addCharge(List<ProductionOrder> list, String chargeNo) {
        System.out.println("进入addCharge方法，chargeNo:" + chargeNo);
        double quantity = 0;
        double weight = 0;
        StringBuilder orders = new StringBuilder();
        ProductionOrder first, last;
        first = null;
        last = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size(); i++) {
            weight += list.get(i).getTargetWeight();
            quantity += list.get(i).getTargetQuantity();
            if (i == 0) {
                first = list.get(i);
                //2020,3,1修改，序列由UID改为NO（getProduct_orderUID()）
                orders.append(list.get(i).getOrderNo());
            } else {
                //2020,3,1修改，序列由UID改为NO（getProduct_orderUID()）
                orders.append(",").append(list.get(i).getOrderNo());
            }
            if (i == list.size() - 1) {
                last = list.get(i);
            }
        }
        // 更改charge_info表数据
        ChargePlan ch = new ChargePlan();
        ch.setCharge_planUID(UUIDGenerator.generate());
        ch.setChargeNo(chargeNo);
        ch.setPlanTime(list.get(0).getOrderDate());
        ch.setOrderList(orders.toString());
        ch.setTargetWeight(weight);
        ch.setTargetQuantity(quantity);
        ch.setPlanStatus("");
        if (first != null && last != null) {
            ch.setSteelGrade(first.getSteelGrade());
            ch.setTechnicalStandard(first.getTechnicalStandard());
            ch.setProductLevel(first.getProductLevel() + "_" + last.getProductLevel());
            //20/4/16 修改
            /*
            ch.setBilletLength(first.getBilletLength() + "_" + last.getBilletLength());
            ch.setBilletWidth(first.getBilletWidth() + "_" + last.getBilletWidth());
            ch.setBilletThick(first.getBilletThick() + "_" + last.getBilletThick());
            */
            ch.setProductSpec(first.getProductSpec());
            ch.setTargetTime(formatter.format(first.getTargetTime()) + "_" + formatter.format(last.getTargetTime()));
            ch.setProcessRoute(first.getProcessRoute());
            ch.setProcessRoute(first.getProcessRoute());
        }
        int result = chargePlanDAO.insert(ch);
        if (result == 1) {
            return ch;
        }
        return null;
    }

    /*
     * name:删除炉次
     * note:传入DateInterval即几天后，将该天的炉次删除，返回>0成功
     */
    @Override
    public int deleteChargeByTimes(Date[] dateInterval) {
        ChargePlanExample ciExample = new ChargePlanExample();
        Criteria cc = ciExample.createCriteria();
        // 更改charge_info表数据
        cc.andPlantimeBetween(dateInterval[0], dateInterval[1]);
        castPlanService.deleteCastByTimes(dateInterval);
        return chargePlanDAO.deleteByExample(ciExample);
    }

    /*
     * name:删除炉次
     * note:根据状态
     */
    @Override
    public int deleteChargeByStatus(String status) {
        ChargePlanExample ciExample = new ChargePlanExample();
        Criteria cc = ciExample.createCriteria();
        // 更改charge_info表数据
        cc.andPlanstatusEqualTo(status);
        castPlanService.deleteCastByStatus(status);
        return chargePlanDAO.deleteByExample(ciExample);
    }

    /*
     * name:获取炉次信息
     * note:获取<DateInterval>天后需要浇次的全部合同，返回炉次列表List
     */
    @Override
    public List<ChargePlan> getChargeListByTimes(Date[] dateInterval) {
        ChargePlanExample chExample = new ChargePlanExample();
        Criteria cc = chExample.createCriteria();
        cc.andPlantimeBetween(dateInterval[0], dateInterval[1]);
        chExample.setOrderByClause("chargeNo ASC, targetTime ASC, steelGrade ASC, productLevel ASC");
        return chargePlanDAO.selectByExample(chExample);
    }

    /*
     * 获得炉次list，通过chargeUIDList
     */
    @Override
    public List<ChargePlan> getChargeListByChargeUIDList(List<String> chargeUIDList) {
        //2020,3,1修改，实为通过NOList获得炉次序列，原为：.andChargePlanuidEqualTo(chargeUID)
        ChargePlanExample chExample = new ChargePlanExample();
        for (String chargeUID : chargeUIDList) {
            chExample.or().andChargenoEqualTo(chargeUID);
        }
        chExample.setOrderByClause("chargeNo ASC, targetTime ASC, steelGrade ASC, productLevel DESC");
        return chargePlanDAO.selectByExample(chExample);
    }

    /*
     * 获得list，by状态
     */
    @Override
    public List<ChargePlan> getChargeListByStatus(String status) {
        ChargePlanExample chExample = new ChargePlanExample();
        Criteria cc = chExample.createCriteria();
        cc.andPlanstatusEqualTo(status);
        return chargePlanDAO.selectByExample(chExample);
    }

    /*
     * 获得list，by uid
     */
    @Override
    public ChargePlan getChargeByNo(String no) {
        ChargePlanExample chExample = new ChargePlanExample();
        Criteria cc = chExample.createCriteria();
        cc.andChargenoEqualTo(no);
        List<ChargePlan> chlist = chargePlanDAO.selectByExample(chExample);
        if (chlist == null || chlist.size() == 0) {
            return null;
        } else {
            return chlist.get(0);
        }
    }

    /*
     * 设置浇次的分配浇次assignCast和浇内序号castSeq
     */
    @Override
    public int updateChargeByCast(String assignCast, List<ChargePlan> chargeList) {
        int castSeq = 0;
        for (ChargePlan ch : chargeList) {
            castSeq++;
            if (assignCast != null) {
                ch.setAssignCast(assignCast);
                ch.setCastSeq(String.valueOf(castSeq));
                chargePlanDAO.updateByPrimaryKeySelective(ch);
            } else {
                ch.setAssignCast(null);
                ch.setCastSeq(null);
                chargePlanDAO.updateByPrimaryKey(ch);
                System.out.println("success");
            }
        }
        return castSeq;

    }

    /*
     * 设置炉次的出铁时间
     */
    public int updateChargeByIronTime(String chargeUID, Date ironTime) {
        int result;
        ChargePlan ch = chargePlanDAO.selectByPrimaryKey(chargeUID);
        if (ironTime != null) {
            ch.setIronTime(ironTime);
            result = chargePlanDAO.updateByPrimaryKeySelective(ch);
        } else {
            ch.setIronTime(null);
            result = chargePlanDAO.updateByPrimaryKey(ch);
        }
        return result;
    }

    /*
     * name:获得炉次信息
     * note:传入DateInterval即几天后，返回该天的炉次信息Map<key=炉次号，value=该炉次包含的合同List>
     */
    @Override
    public Map<Integer, List<ProductionOrder>> getChargeMap(Date[] dateInterval) {
        Map<Integer, List<ProductionOrder>> chargeMap = new HashMap<>();
        // 获得charge_info表数据
        List<ChargePlan> chlist = getChargeListByTimes(dateInterval);
        // 根据charge_infoUID，获取productionorder_info表中数据
        for (int i = 0; i < chlist.size(); i++) {
            chargeMap.put(i + 1, getOrderListByChargeUID(chlist.get(i).getCharge_planUID()));
        }
        return chargeMap;
    }

    /*
     * name:创建炉次信息（执行组炉的计算）--自动
     * note:传入参数<orderList>合同列表，返回组炉结果Map<key=炉次号，value=该炉次包含的合同List>
     */
    @Override
    public HashMap<Integer, List<ProductionOrder>> createChargeMap(Date[] dateInterval) {
        //获得时间内的生产合同列表
        List<ProductionOrder> orderList = productionOrderService.getOrderListByTimes(dateInterval);
        // 炉次序号，初始为1
        int chargeNo = 1;
        // 组炉map
        HashMap<Integer, List<ProductionOrder>> chargeMap = new HashMap<>();
        // 交货期公差单位，分钟
        int chargeTimeUnit = 1000 * 60;
        // 工艺标准map
        // 已组炉的交货总数量
        double amount = 0;
        // 当前需要组炉的产品等级
        int productLevel = 10;
        // 计算炉次时当前炉次包含的所有组炉的合同
        List<ProductionOrder> assignorderList = new ArrayList<>();
        // 获取宽度，厚度公差等工艺标准
        Map<String, double[]> limit = new HashMap<>(getStandard());
        //计划日期
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        String plantime;
        if (orderList.size() > 0)
            plantime = sim.format(new Date());
        else
            return null;
        System.out.println("获取标准完毕" + orderList.size());
        // 开始组炉
        /*
         * productLevel > 0 : 数据库中设置的等级最高为1，当1级产品也无法满足组炉需要时，
         * 停止继续从更高级别钢级寻求替代品组炉，此条件随钢级的梯度设置而修改
         */
        while (orderList.size() != 0 && productLevel > 0) {
            for (int i = 0; i < orderList.size(); i++) {
                if (assignorderList.size() == 0) {
                    System.out.println("初始化新炉次的第一个合同");
                    productLevel = Integer.parseInt(orderList.get(i).getProductLevel());
                    assignorderList.add(orderList.get(i));
                    amount += orderList.get(i).getTargetQuantity();
                    orderList.remove(orderList.get(i));
                    i--;
                    System.out.println("first order over.");
                } else if (assignorderList.get(0).getSteelGrade().equals(orderList.get(i).getSteelGrade())
                        && productLevel == Integer.parseInt(orderList.get(i).getProductLevel())
                        //20/4/16 修改
                        /*
                        && isLessThanInterval(
                        Math.abs(assignorderList.get(0).getBilletWidth() - orderList.get(i).getBilletWidth()),
                        limit.get("ChargeWidth"))
                        && isLessThanInterval(
                        Math.abs(assignorderList.get(0).getBilletThick() - orderList.get(i).getBilletThick()),
                        limit.get("ChargeThick"))
                        */
                        && assignorderList.get(0).getProductSpec().equals(orderList.get(i).getProductSpec())
                        && isLessThanInterval(
                        Math.abs(assignorderList.get(0).getTargetTime().getTime()
                                - orderList.get(i).getTargetTime().getTime()) / chargeTimeUnit,
                        limit.get("ChargeTime"))
                        && isLessThanInterval(amount + orderList.get(i).getTargetQuantity(), limit.get("Converter"))) {
                    System.out.println("添加合同：" + orderList.get(i).getProduct_orderUID());
                    amount += orderList.get(i).getTargetQuantity();
                    assignorderList.add(orderList.get(i));
                    orderList.remove(orderList.get(i));
                    System.out.println("amount:" + amount);
                    i--;
                }

            }

            // 此时，以orderList的第一个合同为基准，其他能组炉的合同都组进去了
            // 此时若数量满足工艺标准，则chargeMap添加此炉次
            if (isinInterval(amount, limit.get("Converter"))) {
                //  || orderList.size() == 0
                System.out.println("炉次+1，chargeNo=" + chargeNo);
                String uid = getLCUid(plantime, chargeNo);
                List<ProductionOrder> list = new ArrayList<>(assignorderList);
                addCharge(list, uid);
                chargeMap.put(chargeNo, list);
                assignorderList.clear();
                chargeNo++;
                productLevel = 10;
                amount = 0;
            } else if (isinInterval(productLevel - Integer.parseInt(assignorderList.get(0).getProductLevel()) + 1, limit.get("ProductLevel"))) {
                // 否则，钢级提高一级，继续循环
                productLevel++;
            } else {
                // 若数量不满足标准，钢级不能再高了，但仍然没有满足组炉的合同了，异常，反馈给用户
                HashMap<Integer, List<ProductionOrder>> errormsg = new HashMap<>();
                errormsg.put(-1, null);
                return errormsg;
            }

        }
        // 在console展示，实际使用时替换成需要的代码
        System.out.println("-------------");
        System.out.println("组炉结束，有" + chargeMap.size() + "个炉次");
        System.out.println("-------------");
        for (int key : chargeMap.keySet()) {
            List<ProductionOrder> newp;
            newp = chargeMap.get(key);
            System.out.println("炉次chargeNo:" + key);
            for (int i = 0; i < newp.size(); i++) {
                System.out.println("第" + "[" + (i + 1) + "]个合同uid：" + newp.get(i).getProduct_orderUID());
            }
            System.out.println("---------------------------------------");
        }

        return chargeMap;
    }

    /*
     * name:创建炉次信息--人工
     * note:传入参数uid数组，返回0错误（逾期等）>1成功
     */
    @Override
    public ChargePlan setChargeManual(List<String> uids) {
        List<ProductionOrder> polist = productionOrderService.getOrderListByOrderUIDList(uids);
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        if (polist.size() > 0) {
            String plantime = sim.format(new Date());
            if (checkIfRight(polist)) {
                return addCharge(polist, getLCNo(plantime));
            } else {
                return null;
            }
        } else
            return null;
    }

    /*
     * 检查合同组炉次是否合规
     */
    @Override
    public boolean checkIfRight(List<ProductionOrder> polist) {
        // 工艺标准map
        // 获取宽度，厚度公差等工艺标准
        Map<String, double[]> limit = new HashMap<>(getStandard());
        // 交货期公差单位，分钟
        int chargeTimeUnit = 1000 * 60;
        // 数量，用来计算炉容
        int quantity = 0;
        if (polist == null || polist.size() == 0) {
            return false;
        }
        //目标合同
        ProductionOrder aimpo = polist.get(0);
        for (ProductionOrder po : polist) {
            quantity += po.getTargetQuantity();
            if (!(po.getSteelGrade().equals(aimpo.getSteelGrade())
                    && isinInterval(
                    Math.abs(Integer.parseInt(po.getProductLevel()) - Integer.parseInt(aimpo.getProductLevel())),
                    limit.get("ProductLevel"))
                    //20/4/17 修改，注释宽和高
                    /*
                    && isinInterval(
                    Math.abs(po.getBilletWidth() - aimpo.getBilletWidth()),
                    limit.get("ChargeWidth"))
                    && isinInterval(
                    Math.abs(po.getBilletThick() - aimpo.getBilletThick()),
                    limit.get("ChargeThick"))
                    */
                    && isinInterval(
                    Math.abs(po.getTargetTime().getTime()
                            - aimpo.getTargetTime().getTime()) / chargeTimeUnit,
                    limit.get("ChargeTime")))) {
                return false;
            }
        }
        return isinInterval(Math.abs(quantity), limit.get("Converter"));
    }

    /*
     * 检车炉次合规否
     */
    public boolean checkIfRightByChargeList(List<ChargePlan> list) {
        for (ChargePlan c : list) {
            ArrayList<String> uids = new ArrayList<>();
            Collections.addAll(uids, c.getOrderList().split(","));
            List<ProductionOrder> polist = productionOrderService.getOrderListByOrderUIDList(uids);
            boolean b = checkIfRight(polist);
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /*
     * name:获取炉次包含的合同列表
     * note:传入参数chargeUID炉次id，返回该炉次包含的合同List
     */
    @Override
    public List<ProductionOrder> getOrderListByChargeUID(String chargeUID) {
        ChargePlan ch = chargePlanDAO.selectByPrimaryKey(chargeUID);
        ArrayList<String> orderUIDList = new ArrayList<>();
        Collections.addAll(orderUIDList, ch.getOrderList().split(","));
        return productionOrderService.getOrderListByOrderUIDList(orderUIDList);
    }

    /*
     * name:工具-计算时间起终点（多日）
     * note:传入DateInterval即从几天后开算，=0则起终点为此刻，终点为多日后的23:59
     */
    @Override
    public Date[] calculateInterval(int DateInterval, int dates) {
        Date[] result = new Date[2];
        Date d = new Date();
        Date targetday = null;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        String targetdayString = sim.format(new Date(d.getTime() + DateInterval * 24 * 60 * 60 * 1000L));
        try {
            targetday = sim.parse(targetdayString);
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
        // 如果是当天的，要排除已经过了交货日期的生产合同，只要从此刻到今夜23:59的生产订单
        if (targetday != null) {
            if (DateInterval == 0) {
                result[0] = d;
                result[1] = new Date(targetday.getTime() + dates * 24 * 60 * 60 * 1000L - 1);
            } else {
                result[0] = targetday;
                result[1] = new Date(targetday.getTime() + dates * 24 * 60 * 60 * 1000L - 1);
            }
        }
        return result;
    }

    /*
     * name:获取工艺标准数据，炉次计划
     * note:从数据库中读取工艺标准的数据List并处理提取有用的数据如宽度，厚度公差等，
     * 返回Map<key=标准ID，value=上下限，double[0]下限[1]上限>
     */
    @Override
    public Map<String, double[]> getStandard() {
        Map<String, double[]> limit = new HashMap<>();
        List<ProcessStandard> standardList = processStandardService.getStandardList();
        for (ProcessStandard p : standardList) {
            switch (p.getItemID()) {
                case "ChargeWidth": {
                    double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                    limit.put("ChargeWidth", d);
                    break;
                }
                case "ChargeThick": {
                    double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                    limit.put("ChargeThick", d);
                    break;
                }
                case "ChargeTime": {
                    double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                    limit.put("ChargeTime", d);
                    break;
                }
                case "Converter": {
                    double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                    limit.put("Converter", d);
                    break;
                }
                case "ProductLevel": {
                    double[] d = {p.getLowerLimit(), p.getUpperLimit()};
                    limit.put("ProductLevel", d);
                    break;
                }
            }

        }
        return limit;

    }

    /*
     * name:工具-工艺标准符合验证1
     * note:传入数值，工艺标准，判断数值是否小于规定公差的最大值，返回Boolean
     */
    @Override
    public boolean isLessThanInterval(double num, double[] limit) {
        return num < limit[1];
    }

    /*
     * name:工具-工艺标准符合验证2
     * note:传入数值，工艺标准，判断数值是否在公差之间，返回Boolean
     */
    @Override
    public boolean isinInterval(double num, double[] limit) {
        return num >= limit[0] && num <= limit[1];
    }

    /*
     * name:工具-获取炉次的id
     * note:生成并返回一个炉次id，格式是LC+日期+序号
     */
    @Override
    public String getLCUid(String plantime, int chargeNo) {
        String uid = "LC" + plantime;
        if (chargeNo > 0 && chargeNo <= 9) {
            uid += "00" + chargeNo;
        } else if (chargeNo > 9 && chargeNo <= 99) {
            uid += "0" + chargeNo;
        } else if (chargeNo > 99 && chargeNo <= 999) {
            uid += chargeNo;
        }
        return uid;
    }

    /*
     * 根据数据库数据创建编号
     */
    @Override
    public String getLCNo(String plantime) {
        List<ChargePlan> chlist = getChargeListByStatus("编制");
        chlist.addAll(getChargeListByStatus("上传"));
        chlist.addAll(getChargeListByStatus("通过"));
        int chargeno = 1;
        if (chlist.size() != 0) {
            String chargenoStr = chlist.get(chlist.size() - 1).getChargeNo();
            chargeno = Integer.valueOf(chargenoStr.substring(chargenoStr.length() - 3)) + 1;
        }
        return getLCUid(plantime, chargeno);
    }
}
