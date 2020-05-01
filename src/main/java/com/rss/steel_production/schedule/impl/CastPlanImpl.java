package com.rss.steel_production.schedule.impl;

import javax.annotation.Resource;

import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.foundation.model.ProcessStandard;
import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.schedule.model.CastPlanExample;
import com.rss.steel_production.schedule.model.ChargePlan;
import com.rss.steel_production.schedule.model.CastPlanExample.Criteria;
import com.rss.steel_production.schedule.service.ChargePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.schedule.model.CastPlan;
import com.rss.steel_production.schedule.dao.CastPlanDAO;
import com.rss.steel_production.schedule.service.CastPlanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class CastPlanImpl extends AbstractService<CastPlan> implements CastPlanService {
    @Resource
    private CastPlanDAO castPlanDAO;
    @Autowired
    ChargePlanService chargePlanService;
    @Autowired
    ProcessStandardService processStandardService;

    /*
     * name:新增浇次
     * note:传入炉次list，将list打包成一个浇次，返回>0成功
     */
    @Override
    public CastPlan addCast(List<ChargePlan> list, String castNo) {
        //String uid = getUUID();
        System.out.println("进入addCast方法，uid:" + castNo);
        double quantity = 0;
        double weight = 0;
        StringBuilder charges = new StringBuilder();
        ChargePlan first, last;
        first = null;
        last = null;
        for (int i = 0; i < list.size(); i++) {
            weight += list.get(i).getTargetWeight();
            quantity += list.get(i).getTargetQuantity();
            if (i == 0) {
                first = list.get(i);
                //2020,3,1修改，序列由UID改为NO
                charges.append(list.get(i).getChargeNo());
            } else {
                //2020,3,1修改，序列由UID改为NO
                charges.append(",").append(list.get(i).getChargeNo());
            }
            if (i == list.size() - 1) {
                last = list.get(i);
            }
        }
        // 更改cast_info表数据
        CastPlan ca = new CastPlan();
        ca.setCast_planUID(UUIDGenerator.generate());
        ca.setCastNo(castNo);
        ca.setChargeList(charges.toString());
        ca.setTargetWeight(weight);
        ca.setTargetQuantity(quantity);
        if (first != null && last != null) {
            ca.setPlanTime(first.getPlanTime());
            ca.setSteelGrade(first.getSteelGrade());
            ca.setProductLevel(first.getProductLevel().split("_")[0] + "_" + last.getProductLevel().split("_")[1]);
            ca.setTechnicalStandard(first.getTechnicalStandard());
            //20/4/16 修改
            /*
            ca.setBilletLength(first.getBilletLength().split("_")[0] + "_" + last.getBilletLength().split("_")[1]);
            ca.setBilletWidth(first.getBilletWidth().split("_")[0] + "_" + last.getBilletWidth().split("_")[1]);
            ca.setBilletThick(first.getBilletThick().split("_")[0] + "_" + last.getBilletThick().split("_")[1]);
             */
            ca.setProductSpec(first.getProductSpec());
            ca.setTargetTime(first.getTargetTime().split("_")[0] + "_" + last.getTargetTime().split("_")[1]);
        }
        ca.setPlanStatus("");

        int result = castPlanDAO.insert(ca);
        if (result == 1) {
            // 更改charge_info表数据
            chargePlanService.updateChargeByCast(castNo, list);
            return ca;
        }
        return null;
    }

    /*
     * name:删除浇次
     * note:传入DateInterval即几天后，将该天的浇次删除，返回>0成功
     */
    @Override
    public int deleteCastByTimes(Date[] dateInterval) {
        CastPlanExample caExample = new CastPlanExample();
        Criteria cc = caExample.createCriteria();
        // 更改ChargePlan表数据
        cc.andPlantimeBetween(dateInterval[0], dateInterval[1]);
        List<ChargePlan> chargeList = chargePlanService.getChargeListByTimes(dateInterval);
        chargePlanService.updateChargeByCast(null, chargeList);
        System.out.println("update complete.");

        int result = castPlanDAO.deleteByExample(caExample);
        System.out.println("Cast delete complete.");
        return result;
    }

    /*
     * name:删除浇次
     * note:根据状态
     */
    @Override
    public int deleteCastByStatus(String status) {
        CastPlanExample caExample = new CastPlanExample();
        Criteria cc = caExample.createCriteria();
        // 更改ChargePlan表数据
        cc.andPlanstatusEqualTo(status);
        List<ChargePlan> chargeList = chargePlanService.getChargeListByStatus(status);
        chargePlanService.updateChargeByCast(null, chargeList);
        return castPlanDAO.deleteByExample(caExample);
    }

    /*
     * name:获取浇次信息
     * note:获取dateInterval期间的全部浇次，返回List
     */
    @Override
    public List<CastPlan> getCastListByTimes(Date[] dateInterval) {
        CastPlanExample caExample = new CastPlanExample();
        Criteria cc = caExample.createCriteria();
        cc.andPlantimeBetween(dateInterval[0], dateInterval[1]);
        caExample.setOrderByClause("castNo ASC, targetTime ASC, steelGrade ASC, productLevel DESC");
        return castPlanDAO.selectByExample(caExample);
    }

    /*
     * 获得list通过状态
     */
    @Override
    public List<CastPlan> getCastListByStatus(String status) {
        CastPlanExample caExample = new CastPlanExample();
        Criteria cc = caExample.createCriteria();
        cc.andPlanstatusEqualTo(status);
        return castPlanDAO.selectByExample(caExample);
    }

    /*
     * 获得通过uid
     */
    @Override
    public CastPlan getCastByNo(String no) {
        CastPlanExample caExample = new CastPlanExample();
        Criteria cc = caExample.createCriteria();
        cc.andCastnoEqualTo(no);
        List<CastPlan> calist = castPlanDAO.selectByExample(caExample);
        if (calist == null || calist.size() == 0) {
            return null;
        } else {
            return calist.get(0);
        }
    }

    /*
     * name:获得浇次信息
     * note:传入DateInterval即几天后，返回该天的浇次信息Map<key=浇次号，value=该浇次包含的炉次List>
     */
    @Override
    public Map<Integer, List<ChargePlan>> getCastMap(Date[] dateInterval) {
        Map<Integer, List<ChargePlan>> castMap = new HashMap<>();
        // 获得cast_info表数据
        List<CastPlan> calist = getCastListByTimes(dateInterval);
        // 根据cast_infoUID，获取charge_info表中数据
        for (int i = 0; i < calist.size(); i++) {
            castMap.put(i + 1, getChargeListByCastUID(calist.get(i).getCast_planUID()));
        }
        return castMap;
    }

    /*
     * name:创建浇次信息--自动
     * note:传入参数<chargeList>炉次列表，返回组炉结果Map<key=浇次号，value=该浇次包含的炉次List>
     */
    @Override
    public Map createCastMap(Date[] dateInterval) {
        // 浇次序号，初始为1
        int castNo = 1;
        // 浇次map
        Map<Integer, List<ChargePlan>> castMap = new HashMap<>();
        // 交货期公差单位，分钟
        int chargeTimeUnit = 1000 * 60;
        // 工艺标准map
        // 获取宽度，厚度公差等工艺标准
        Map<String, double[]> limit = new HashMap<>(getStandard());
        // 当前需要浇次的产品等级
        int productLevel = 10;
        //获取全部时间段内炉次
        List<ChargePlan> chargeList = chargePlanService.getChargeListByTimes(dateInterval);
        // 计算浇次时当前浇次包含的所有炉次
        List<ChargePlan> assignChargeList = new ArrayList<>();
        //计划日期
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        String plantime;
        if (chargeList != null && chargeList.size() != 0)
            plantime = sim.format(new Date());
        else
            return null;
        System.out.println("获取标准完毕" + chargeList.size());
        // 开始浇次
        /*
         * productLevel > 0 : 数据库中设置的等级最高为1，当1级产品也无法满足组炉需要时，
         * 停止继续从更高级别钢级寻求替代品组炉，此条件随钢级的梯度设置而修改
         */
        while (chargeList.size() != 0 && productLevel > 0) {
            for (int i = 0; i < chargeList.size(); i++) {
                if (assignChargeList.size() == 0) {
                    System.out.println("初始化新浇次的第一个炉次");
                    productLevel = Integer.parseInt(chargeList.get(i).getProductLevel().split("_")[0]);
                    assignChargeList.add(chargeList.get(i));
                    chargeList.remove(chargeList.get(i));
                    i--;
                } else if (assignChargeList.get(0).getSteelGrade().equals(chargeList.get(i).getSteelGrade())
                        && productLevel == Integer.parseInt(chargeList.get(i).getProductLevel().split("_")[0])
                        //20/4/16 修改
                        /*
                        && isLessThanInterval(
                        Math.abs(Integer.parseInt(assignChargeList.get(0).getBilletWidth().split("_")[0])
                                - Integer.parseInt(chargeList.get(i).getBilletWidth().split("_")[0])),
                        limit.get("ChargeWidth"))
                        && isLessThanInterval(
                        Math.abs(Integer.parseInt(assignChargeList.get(0).getBilletThick().split("_")[0])
                                - Integer.parseInt(chargeList.get(i).getBilletThick().split("_")[0])),
                        limit.get("ChargeThick"))
                        */
                        && assignChargeList.get(0).getProductSpec().equals(chargeList.get(i).getProductSpec())
                        && isLessThanInterval(
                        Math.abs(stringToDate(assignChargeList.get(0).getTargetTime().split("_")[0]).getTime()
                                - stringToDate(chargeList.get(i).getTargetTime().split("_")[0]).getTime()) / chargeTimeUnit,
                        limit.get("ChargeTime"))
                        && isLessThanInterval(
                        assignChargeList.size() + 1,
                        limit.get("ChargeCount"))
                ) {
                    System.out.println("添加炉次：" + chargeList.get(i).getCharge_planUID());
                    assignChargeList.add(chargeList.get(i));
                    chargeList.remove(chargeList.get(i));
                    i--;
                    /*
                     * 此处原本考虑，当组炉的交货总数量满足工艺标准时就跳出循环，以免orderList里的当日生产合同 数量太多在此处浪费时间。
                     * 但由于不清楚现实生产中，每个生产合同交货数量的量级，担心出现已组炉数量满足工艺标准时，再添加
                     * 合同仍然满足工艺标准的情况，这种情况下若提前在满足标准时就结束该炉次，会造成“明明可以在这个炉 次中继续添加合同来生产”的浪费情况。 if(!
                     * isinInterval(amount , limit.get("Converter"))){ break; }
                     */
                }

            }

            // 此时，以chargeList的第一个浇次为基准，其他能浇次的炉次都组进去了
            // 此时若数量满足工艺标准，则castMap添加此浇次
            // 若数量不满足标准，但高钢级合同中仍然没有满足的炉次了，则以这个数量不够的浇次为一浇次（暂定）
            if (isinInterval(assignChargeList.size() + 1, limit.get("ChargeCount"))) {
                // || chargeList.size() == 0
                System.out.println("浇次+1，chargeNo=" + castNo);
                String uid = getJCUid(plantime, castNo);
                List<ChargePlan> list = new ArrayList<>(assignChargeList);
                addCast(list, uid);
                castMap.put(castNo, list);
                assignChargeList.clear();
                castNo++;
                productLevel = 10;
            } else if (isinInterval(productLevel - Integer.parseInt(assignChargeList.get(0).getProductLevel().split("_")[0]) + 1, limit.get("ProductLevel"))) {
                // 否则，钢级提高一级，继续循环
                productLevel++;
            } else {
                // 若数量不满足标准，钢级不能再高了，但仍然没有满足组炉的合同了，异常，反馈给用户
                HashMap<Integer, String> errormsg = new HashMap<>();
                errormsg.put(-1, "auto charge failure");
                return errormsg;
            }

        }
        // 在console展示，实际使用时替换成需要的代码
        System.out.println("-------------");
        System.out.println("组炉结束，有" + castMap.size() + "个炉次");
        System.out.println("-------------");
        for (int key : castMap.keySet()) {
            List<ChargePlan> newp;
            newp = castMap.get(key);
            System.out.println("炉次chargeNo:" + key);
            for (int i = 0; i < newp.size(); i++) {
                System.out.println("第" + "[" + (i + 1) + "]个合同uid：" + newp.get(i).getCharge_planUID());
            }
            System.out.println("---------------------------------------");
        }
        return castMap;
    }

    /*
     * name:创建浇次信息--人工
     * note:传入参数newMap<key=炉次UID，value=该合同所属浇次号>，返回0错误（逾期等）>1成功
     */
    @Override
    public CastPlan setCastManual(List<String> uids) {
        List<ChargePlan> chlist = chargePlanService.getChargeListByChargeUIDList(uids);
        //计划日期
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
        if (chlist.size() > 0) {
            String plantime = sim.format(new Date());
            boolean b = checkIfRight(chlist);
            if (b) {
                return addCast(chlist, getJCNo(plantime));
            } else {
                return null;
            }
        } else
            return null;
    }

    /*
     * 检查炉次组浇次是否合规
     */
    @Override
    public boolean checkIfRight(List<ChargePlan> chlist) {
        // 工艺标准map
        // 获取宽度，厚度公差等工艺标准
        Map<String, double[]> limit = new HashMap<>(getStandard());
        // 交货期公差单位，分钟
        int chargeTimeUnit = 1000 * 60;
        if (chlist == null || chlist.size() == 0) {
            return false;
        }
        //目标合同
        ChargePlan aimch = chlist.get(0);
        for (ChargePlan ch : chlist) {
            if (!(ch.getSteelGrade().equals(aimch.getSteelGrade())
                    && isLessThanInterval(
                    Math.abs(Integer.parseInt(ch.getProductLevel().split("_")[0]) - Integer.parseInt(aimch.getProductLevel().split("_")[0])),
                    limit.get("ProductLevel"))
                    //20/4/17 修改，注释宽和高
                    /*
                    && isLessThanInterval(
                    Math.abs(Double.parseDouble(ch.getBilletWidth().split("_")[0]) - Double.parseDouble(aimch.getBilletWidth().split("_")[0])),
                    limit.get("ChargeWidth"))
                    && isLessThanInterval(
                    Math.abs(Double.parseDouble(ch.getBilletThick().split("_")[0]) - Double.parseDouble(aimch.getBilletThick().split("_")[0])),
                    limit.get("ChargeThick"))
                    */
                    && isLessThanInterval(
                    Math.abs(stringToDate(ch.getTargetTime().split("_")[0]).getTime()
                            - stringToDate(aimch.getTargetTime().split("_")[0]).getTime()) / chargeTimeUnit,
                    limit.get("ChargeTime"))
                    && isLessThanInterval(
                    chlist.size(),
                    limit.get("ChargeCount")))) {
                return false;
            }
        }
        return true;
    }

    /*
     * 检车炉次合规否
     */
    public boolean checkIfRightByCastList(List<CastPlan> list) {
        for (CastPlan c : list) {
            ArrayList<String> uids = new ArrayList<>();
            Collections.addAll(uids, c.getChargeList().split(","));
            List<ChargePlan> chlist = chargePlanService.getChargeListByChargeUIDList(uids);
            boolean b = checkIfRight(chlist);
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /*
     * name:获取浇次包含的炉次列表
     * note:传入参数castUID(castPlanUID)浇次id，返回该浇次包含的炉次List
     */
    public List<ChargePlan> getChargeListByCastUID(String castUID) {
        CastPlan ca = castPlanDAO.selectByPrimaryKey(castUID);
        //2020,3,1注释，实为NOList，此处用到，无修改
        ArrayList<String> chargeUIDList = new ArrayList<>();
        Collections.addAll(chargeUIDList, ca.getChargeList().split(","));
        return chargePlanService.getChargeListByChargeUIDList(chargeUIDList);
    }

    /*
     * name:获取工艺标准数据-浇次
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
                    double[] d = {p.getLowerLimit(), p.getUpperLimit()};
                    limit.put("ChargeWidth", d);
                    break;
                }
                case "ChargeThick": {
                    double[] d = {p.getLowerLimit(), p.getUpperLimit()};
                    limit.put("ChargeThick", d);
                    break;
                }
                case "ChargeTime": {
                    double[] d = {p.getLowerLimit(), p.getUpperLimit()};
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
                case "ChargeCount": {
                    double[] d = {p.getStandardValue() - p.getLowerLimit(), p.getStandardValue() + p.getUpperLimit()};
                    limit.put("ChargeCount", d);
                    break;
                }
                case "SlabWidth": {
                    double[] d = {p.getLowerLimit(), p.getUpperLimit()};
                    limit.put("SlabWidth", d);
                    break;
                }
            }

        }
        return limit;
    }

    /*
     * 交期string转成date
     */
    @Override
    public Date stringToDate(String targetTimeStr) {
        //交期区间formatter
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date targetTime = null;
        try {
            targetTime = formatter.parse(targetTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetTime;
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
        return num >= limit[0] && num < limit[1];
    }

    /*
     * name:工具-获取浇次的id
     * note:生成并返回一个浇次id，格式是JC+日期+序号
     */
    public String getJCUid(String plantime, int castNo) {
        String uid = "JC" + plantime;
        if (castNo > 0 && castNo <= 9) {
            uid += "00" + castNo;
        } else if (castNo > 9 && castNo <= 99) {
            uid += "0" + castNo;
        } else if (castNo > 99 && castNo <= 999) {
            uid += castNo;
        }
        return uid;
    }

    /*
     * 根据数据库数据创建编号
     */
    @Override
    public String getJCNo(String plantime) {
        List<CastPlan> calist = getCastListByStatus("编制");
        calist.addAll(getCastListByStatus("上传"));
        calist.addAll(getCastListByStatus("通过"));
        int castno = 1;
        if (calist.size() != 0) {
            String castnoStr = calist.get(calist.size() - 1).getCastNo();
            castno = Integer.valueOf(castnoStr.substring(castnoStr.length() - 3)) + 1;
        }
        return getJCUid(plantime, castno);
    }
}
