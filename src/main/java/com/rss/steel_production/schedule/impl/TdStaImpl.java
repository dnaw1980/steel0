package com.rss.steel_production.schedule.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rss.framework.AbstractService;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.process.dao.RealDataDAO;
import com.rss.steel_production.process.model.RealData;
import com.rss.steel_production.schedule.controller.bean.RealDataBean;
import com.rss.steel_production.schedule.dao.TdChannelDAO;
import com.rss.steel_production.schedule.dao.TdDataBatDAO;
import com.rss.steel_production.schedule.dao.TdHistDataDAO;
import com.rss.steel_production.schedule.dao.TdStaDAO;
import com.rss.steel_production.schedule.model.*;
import com.rss.steel_production.schedule.service.*;
import com.rss.steel_production.workProcedure.dao.DpStaScheduleTmDAO;
import com.rss.steel_production.workProcedure.model.DpStaScheduleTm;
import com.rss.steel_production.workProcedure.model.DpWorkProc;
import com.rss.steel_production.workProcedure.service.*;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service
//@EnableScheduling
//@Transactional
public class TdStaImpl extends AbstractService<TdSta> implements TdStaService {

    @Resource
    private TdStaDAO tdStaDAO;

    @Resource
    private TdChannelDAO tdChannelDAO;

    @Resource
    private TdDataBatDAO tdDataBatDAO;

    @Resource
    private TdHistDataDAO tdHistDataDAO;

    @Resource
    private DpStaScheduleTmDAO dpStaScheduleTmDAO;

    @Resource
    private RealDataDAO realDataDAO;

    @Resource
    private DpWorkProcService workProcService;

    @Autowired
    private TdStaService tdStaService;

    @Autowired
    private TdChannelService tdChannelService;

    @Autowired
    private TdDataBatService tdDataBatService;

    @Autowired
    private TdHistDataService tdHistDataService;

    @Autowired
    private SteelScheduleService steelScheduleService;

    @Autowired
    private WpDesulfuriInfoService wpDesulfuriInfoService;

    @Autowired
    private WpConvererInfoService wpConvererInfoService;

    @Autowired
    private WpCasInfoService wpCasInfoService;

    @Autowired
    private WpLfInfoService wpLfInfoService;

    @Autowired
    private WpBilletInfoService wpBilletInfoService;

    @Autowired
    private WpSlabInfoService wpSlabInfoService;

    //    @Scheduled(cron = "0/5 * * * * ? ")
    public void show() {
        /**
         * 1、遍历 tdSta 表
         * 2、使用 real_data_key 查询 real_data 表，核对 chk_tag 是否一致，如一致，说明数据没有更新，跳过，
         *      否则，将数据串转成 Map
         * 3、查询 stSta 对应的 channel 信息，生成Map，key 与real_data 里面的串生成的Map 一致。
         * 4、先处理需要计算的值，更新到数据库。
         * 5、处理直接采的值，更新到数据库
         * 6、更新数据时间和 chk_tag
         */

        //1、遍历 tdSta 表
        List<TdSta> staList = tdStaDAO.selectAll();

        for (TdSta sta : staList) {

//            if (!sta.getStaId().equals("0157b42a-9ef6-11ea-aeba-fa163e16816f")) {
//                continue;
//            }
            this.parseSta(sta);

            /* TODO
            生成实时数据后，要生成工序数据。
            1、查询站点的实时数据。
            2、查询站点类型，
            3、根据站点类型和站点ID及实时数据信息，调用对应类型的工序数据解析方法。

             */
            //1、查询站点的实时数据。
            sta.setChannelList(this.listChannelForSta(sta));

            //2、查询站点类型，
            DpStaScheduleTm dpStaScheduleTm = null;
            {
                Condition condy = new Condition(DpStaScheduleTm.class);
                Condition.Criteria criteria = condy.createCriteria();

                criteria.andEqualTo("staId", sta.getStaId());
                List<DpStaScheduleTm> ssTmList = this.dpStaScheduleTmDAO.selectByCondition(condy);
                if (Tools.notEmpty(ssTmList)) {
                    dpStaScheduleTm = ssTmList.get(0);
                }
            }

            if (dpStaScheduleTm != null) {
                Map<String, TdChannel> channelMap = new HashMap<String, TdChannel>();
                for (TdChannel ch : sta.getChannelList()) {
                    channelMap.put(ch.getComTag(), ch);
                }

                switch (dpStaScheduleTm.getWorkProcCode()) {
                    case "KR": {
                        this.wpDesulfuriInfoService.synWpData(sta, channelMap);
                    }
                    break;

                    case "BOF": {
                        this.wpConvererInfoService.synWpData(sta, channelMap);
                    }
                    break;

                    case "CAS": {
                        this.wpCasInfoService.synWpData(sta, channelMap);
                    }
                    break;

                    case "LF": {
                        this.wpLfInfoService.synWpData(sta, channelMap);
                    }
                    break;
                    case "CC": {
                        if (dpStaScheduleTm.getStaNo().intValue() == 1) {
                            this.wpBilletInfoService.synWpData(sta, channelMap);
                        } else {
                            this.wpSlabInfoService.synWpData(sta, channelMap);
                        }

                    }
                    break;
                }
            }

            //调试，只执行一次
            //return;
        }
    }

    @Override
    public List<TdSta> showAll() {

        Map<String, DpWorkProc> workProcMap = this.workProcService.showAllMap();

        List<TdSta> staList = this.tdStaDAO.selectAll();
        for (TdSta sta : staList) {
            sta.setWorkProc(workProcMap.get(sta.getWorkProcId()));
        }

        return staList;
    }

    private String lastElement(String src) {
        String desc = "";
        if (!Tools.empty(src)) {
            String[] tArr = src.split(",");
            desc = tArr[tArr.length - 1];
        }
        return desc;
    }

    /**
     * 查询站点实时数据
     *
     * @param stationNo
     * @return
     */
    @Override
    public RealDataBean realData(String stationNo) {

        RealDataBean rsBean = new RealDataBean();
        rsBean.setRealDataList(new ArrayList<com.rss.steel_production.schedule.controller.bean.RealData>());

        /**
         * 1、查询本工位的，有实时进站时间，没有实际出站时间的调度记录
         * 2、查询本工位信息，查询本工位对应的通道信息（按重要级别和排序号进行排序）
         */

        {
            Condition scheduleCondition = new Condition(SteelSchedule.class);
            Condition.Criteria criteria = scheduleCondition.createCriteria();

            criteria.andLike("stationName", "%" + stationNo + "%");
            criteria.andIsNotNull("actualEnter");
            criteria.andIsNull("actualExit");

            List<SteelSchedule> steelScheduleList = steelScheduleService.findByCondition(scheduleCondition);

            if (Tools.notEmpty(steelScheduleList)) {
                SteelSchedule steelSchedule = steelScheduleList.get(0);

                rsBean.getRealDataList().add(
                        new com.rss.steel_production.schedule.controller.bean.RealData("炉次号", steelSchedule.getChargeNo(), 1)
                );

                rsBean.getRealDataList().add(
                        new com.rss.steel_production.schedule.controller.bean.RealData("浇次号", steelSchedule.getCastNo(), 1)
                );

                rsBean.getRealDataList().add(
                        new com.rss.steel_production.schedule.controller.bean.RealData("进站时间", DateUtil.dateToString(steelSchedule.getActualEnter(), DateUtil.DATETIME_FMT), 1)
                );

                //温度
                String temp = this.lastElement(steelSchedule.getTemperature());
                rsBean.getRealDataList().add(new com.rss.steel_production.schedule.controller.bean.RealData("温度", temp, 1));

                //重量
                String weight = this.lastElement(steelSchedule.getWeight());
                rsBean.getRealDataList().add(new com.rss.steel_production.schedule.controller.bean.RealData("重量", weight, 1));

                //进站重量
                String scrabWeight = this.lastElement(steelSchedule.getScrabWeight());
                rsBean.getRealDataList().add(new com.rss.steel_production.schedule.controller.bean.RealData("进站重量", scrabWeight, 1));

                //出站重量
                String exitWeight = this.lastElement(steelSchedule.getExitWeight());
                rsBean.getRealDataList().add(new com.rss.steel_production.schedule.controller.bean.RealData("出站重量", exitWeight, 1));
            }
        }

        //查本工位信息
        TdSta sta = null;
        {
            Condition staCondition = new Condition(TdSta.class);
            Condition.Criteria criteria = staCondition.createCriteria();

            criteria.andEqualTo("scheduleStation", stationNo);

            List<TdSta> staList = this.tdStaService.findByCondition(staCondition);

            if (Tools.notEmpty(staList)) {
                sta = staList.get(0);

                rsBean.setDtTime(
                        DateUtil.datetimeToString(sta.getDatDt())
                );
            }
        }

        //查数据通道信息
        {
            Condition chCondition = new Condition(TdChannel.class);
            Condition.Criteria criteria = chCondition.createCriteria();
            chCondition.setOrderByClause("is_important desc, order_sn asc");
            criteria.andEqualTo("staId", sta.getStaId());

            List<TdChannel> chList = this.tdChannelService.findByCondition(chCondition);

            for (TdChannel ch : chList) {

                com.rss.steel_production.schedule.controller.bean.RealData rd = new com.rss.steel_production.schedule.controller.bean.RealData();
                rd.setLabel(ch.getChName());
                rd.setShow(ch.getIsImportant());

                rsBean.getRealDataList().add(rd);

                DecimalFormat df1 = new DecimalFormat("0.######");

                //采集数据
//                if (Tools.empty(ch.getInputComTag())) {
                switch (ch.getDkCls()) {

                    case 0://开关量
                        String val = ch.getDatVal().intValue() == 1 ? ch.getSw1Stat() : ch.getSw0Stat();
                        rd.setValue(val);
                        break;
                    case 1://模拟量
                    case 2://累计量
                        rd.setValue(df1.format(ch.getDatVal()));
                        break;
                    case 3://日期
                        String dt = DateUtil.datetimeToString(DateUtil.getDateTime(ch.getDatVal().longValue()));
                        rd.setValue(dt);
                        break;
                    default:
                        break;
                }
//                } else {//计算数据
//
//                }
            }


        }

        return rsBean;

    }

    private void parseSta(TdSta sta) {
        /**
         * 2、使用 real_data_key 查询 real_data 表，核对 chk_tag 是否一致，如一致，说明数据没有更新，跳过，
         *      否则，将数据串转成 Map(转成大写)
         * 3、查询 stSta 对应的 channel 信息，生成Map，key 与real_data 里面的串生成的Map 一致。
         * 4、先处理需要计算的值，更新到数据库。
         * 5、处理直接采的值，更新到数据库
         * 6、更新数据时间和 chk_tag
         */

        RealData rd = realDataDAO.selectByPrimaryKey(sta.getRealDataKey());
        if (rd.getChkTag().equals(sta.getChkTag())) {
            return;
        }

        //生成数据批次
        TdDataBat dataBat = new TdDataBat();
        dataBat.setStaId(sta.getStaId());
        dataBat.setDatDt(rd.getDatTm());

        this.tdDataBatDAO.insertUseGeneratedKeys(dataBat);

        String valStr = rd.getDatVal();
        System.out.println("valStr:\n" + valStr);
        valStr = "{" + valStr.substring(1, valStr.length() - 1) + "}";
        System.out.println("valStr:\n" + valStr);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            map = mapper.readValue(valStr, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(map.size());

        //要重新生成目标MAP，key都要转成大写，因为 板柸从数据库转来的都是大写的。
        Map<String, String> valMap = new HashMap<String, String>();
        Set<String> keySet = map.keySet();

        for (String key : keySet) {
            valMap.put(key.toUpperCase(), map.get(key));
        }

        //3、查询 stSta 对应的 channel 信息，生成Map，key 与real_data 里面的串生成的Map 一致。
        List<TdChannel> chList = listChannelForSta(sta);
        System.out.println("chList size: " + chList.size());

        //TODO 4、先处理需要计算的值，更新到数据库。
        /*
        目前只处理时间类型，即dkCls 为 3 的，
        使用当前的 ch 里面的 inputComTag 去  valMap 找对应的值 curVal，
        如果 curVal 等于 ch里面的 inputChkVal，并且 curVal 不等于当前值。说明值变化触发。
        将当前时间转成秒数，记录到 ch 的 datVal 里面。
         */

        Map<String, TdChannel> chMap = new HashMap<String, TdChannel>();
        for (TdChannel ch : chList) {
            chMap.put(ch.getComTag().toUpperCase(), ch);
        }

        for (TdChannel ch : chList) {
            //如果是采集通道，跳过
            if (Tools.empty(ch.getInputComTag())) {
                continue;
            }

            if (ch.getDkCls().intValue() == 3) {
                //如果是时间类型
                String _str = valMap.get(ch.getInputComTag().toUpperCase());
                if (Tools.empty(_str) || !Tools.isDigit(_str)) {
                    System.out.println("val:" + _str + " 不是有效数字");
                    continue;
                }

                BigDecimal curVal = new BigDecimal(_str);
                TdChannel inputCh = chMap.get(ch.getInputComTag().toUpperCase());
                //如果 curVal 等于 ch里面的 inputChkVal，并且 curVal 不等于当前值。说明值变化触发。
                if (curVal.intValue() == ch.getInputChkVal().intValue()) {
                    if (!curVal.equals(inputCh.getDatVal())) {
                        long now = DateUtil.getDateTime().getTime();

                        ch.setDatVal(new BigDecimal(now));
                        ch.setDatDt(rd.getDatTm());

                        this.tdChannelDAO.updateByPrimaryKeySelective(ch);
                    }
                }

                TdHistData histData = new TdHistData();
                histData.setBatSn(dataBat.getBatSn());
                histData.setChSn(ch.getChSn());
                histData.setDatVal(ch.getDatVal());

                this.tdHistDataDAO.insert(histData);

            } else {
                //其它类型

            }
        }

        //5、处理直接采的值，更新到数据库
        for (TdChannel ch : chList) {
            //如果是计算通道，跳过
            if (Tools.notEmpty(ch.getInputComTag())) {
                continue;
            }

            //从valMap中取数据
            String val = valMap.get(ch.getComTag().toUpperCase());

            if (Tools.notEmpty(val) && !Tools.isDigit(val)) {
                System.out.println("val:" + val + " 不是有效数字");
                continue;
            }

            if (Tools.empty(val)) {
                ch.setDatVal(new BigDecimal(0));
            } else {
                ch.setDatVal(new BigDecimal(val));
            }

            ch.setDatDt(rd.getDatTm());

            this.tdChannelDAO.updateByPrimaryKeySelective(ch);

            TdHistData histData = new TdHistData();
            histData.setBatSn(dataBat.getBatSn());
            histData.setChSn(ch.getChSn());
            histData.setDatVal(ch.getDatVal());

            this.tdHistDataDAO.insert(histData);

        }

        //TODO 6、更新数据时间和 chk_tag
        {
            sta.setChkTag(rd.getChkTag());
            sta.setDatDt(rd.getDatTm());

            this.tdStaDAO.updateByPrimaryKey(sta);
        }
    }

    private List<TdChannel> listChannelForSta(TdSta sta) {

        Condition condition = new Condition(TdChannel.class);
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andEqualTo("staId", sta.getStaId());

        List<TdChannel> channelList = this.tdChannelDAO.selectByCondition(condition);
        return channelList;
    }

}
