package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.workProcedure.dao.DpScheduleDetailDAO;
import com.rss.steel_production.workProcedure.dao.WpConvererInfoDAO;
import com.rss.steel_production.workProcedure.model.DpScheduleDetail;
import com.rss.steel_production.workProcedure.model.WpConvererInfo;
import com.rss.steel_production.workProcedure.model.WpDesulfuriInfo;
import com.rss.steel_production.workProcedure.service.WpConvererInfoService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
//@Transactional
public class WpConvererInfoImpl extends AbstractService<WpConvererInfo> implements WpConvererInfoService {

    static Logger log;

    static {
        log = Logger.getLogger(WpConvererInfoImpl.class.getName());
    }

    private static long BASE_TIME = DateUtil.getDateTime("2020-01-01 00:00:00").getTime();

    @Resource
    private WpConvererInfoDAO wpConvererInfoDAO;

    @Resource
    private DpScheduleDetailDAO scheduleDetailDAO;

    /**
     * 同步站点工序数据，从实时数据同步到工序数据。
     *
     * @param sta
     */
    @Override
    public void synWpData(TdSta sta, Map<String, TdChannel> channelMap) {
        //TODO
        /*
        1、查询本站点的实时工序数据。
        2、逐步从实时数据中计算中工序数据。
        3、更新工序数据到数据库
        4、查找调度工序数据，复制实时工序数据到调度工序数据中。
         */
        WpConvererInfo convererInfo = null;
        {
            Condition condition = new Condition(WpConvererInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("staId", sta.getStaId());
            criteria.andIsNull("scheduleSeqId");

            List<WpConvererInfo> wpList = this.wpConvererInfoDAO.selectByCondition(condition);
            if (Tools.notEmpty(wpList)) {
                convererInfo = wpList.get(0);
            }
        }

        if (convererInfo == null) {
            log.error("站点：" + sta.getStaId() + ",没有对应的实时工序信息");
            return;
        }
        //计算工序数据，注意工位1 和 工位2 不一样，要分别处理。
        if (sta.getStaNo().intValue() == 1) {
            this.sta1(sta, channelMap, convererInfo);
        } else {
            this.sta2(sta, channelMap, convererInfo);
        }

        //更新到数据库
        this.wpConvererInfoDAO.updateByPrimaryKey(convererInfo);

        //TODO 查找调度工序信息
        /*
        使用当前工位查调度明细，状态为2的，取对应的调度ID，
        用调度ID查工序表，就是当前的调度工序。
         */
        String scheduleSeqId = null;
        {
            Condition condition = new Condition(DpScheduleDetail.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("staId", sta.getStaId());
            criteria.andEqualTo("state", DpScheduleDetail.STATE_EXEC);

            List<DpScheduleDetail> detailList = this.scheduleDetailDAO.selectByCondition(condition);

            if (Tools.notEmpty(detailList)) {
                scheduleSeqId = detailList.get(0).getScheduleSeqId();
            }
        }

        if (scheduleSeqId != null) {
            Condition condition = new Condition(WpConvererInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", scheduleSeqId);

            List<WpConvererInfo> dsList = this.wpConvererInfoDAO.selectByCondition(condition);

            if (Tools.notEmpty(dsList)) {
                WpConvererInfo scWpInfo = dsList.get(0);

                convererInfo.setConvererInfoSn(scWpInfo.getConvererInfoSn());
                convererInfo.setStaId(null);
                convererInfo.setScheduleSeqId(scheduleSeqId);

                this.wpConvererInfoDAO.updateByPrimaryKey(convererInfo);
            }
        }
    }


    private void sta1(TdSta sta, Map<String, TdChannel> channelMap, WpConvererInfo convererInfo) {

        //        enterTemperature	进站铁水温度	int
        TdChannel enterTemperature = channelMap.get("enterConverterTemper");
        convererInfo.setEnterTemperature(enterTemperature.getDatVal().intValue());


        //        ironMolt	兑铁水开始时刻	datetime		进站时间
        TdChannel ironMolt = channelMap.get("ironMoltTm");
        if (ironMolt.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setIronMolt(DateUtil.getDateTime(ironMolt.getDatVal().longValue()));
        }

        //        blowStart 	吹炼开始时刻	datetime
        TdChannel blowStart = channelMap.get("blowStartTm");
        if (blowStart.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setBlowStart(DateUtil.getDateTime(blowStart.getDatVal().longValue()));
        }

        //        blowEnd	吹炼结束时刻	datetime
        TdChannel blowEnd = channelMap.get("blowEndTm");
        if (blowEnd.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setBlowEnd(DateUtil.getDateTime(blowEnd.getDatVal().longValue()));
        }

        //        oxygenBlow	吹炼用氧气量	float(10, 2)
        TdChannel oxygenBlow = channelMap.get("oxygenBlow");
        convererInfo.setOxygenBlow(oxygenBlow.getDatVal());

        //        nitrogenBottom	底吹氮气量	float(10, 2)
        TdChannel nitrogenBottom = channelMap.get("nitrogenBlow");
        convererInfo.setNitrogenBottom(nitrogenBottom.getDatVal());

        //        argonBottom	底吹氩气用量	float(10, 2)
        TdChannel argonBottom = channelMap.get("argonBottom");
        convererInfo.setArgonBottom(argonBottom.getDatVal());

        //        slagSplash	溅渣时刻	datetime
        TdChannel slagSplash = channelMap.get("slagSplash");
        if (slagSplash.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setSlagSplash(DateUtil.getDateTime(slagSplash.getDatVal().longValue()));
        }

        //        steelStart	出钢时刻	datetime
        TdChannel steelStart = channelMap.get("steelTap");
        if (steelStart.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setSteelStart(DateUtil.getDateTime(steelStart.getDatVal().longValue()));
        }

        //        exitTemperature	终点温度	int
        TdChannel exitTemperature = channelMap.get("exitConverterTemper");
        convererInfo.setExitTemperature(exitTemperature.getDatVal().intValue());

        //        exitWeigh	出钢重量	float(10, 2)
        TdChannel exitWeigh = channelMap.get("exitConverterWeigh");
        convererInfo.setExitWeigh(exitWeigh.getDatVal());

        //        steelEnd	出站时刻	datetime		本炉结束
        TdChannel steelEnd = channelMap.get("steelEndTm");
        if (steelEnd.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setSteelEnd(DateUtil.getDateTime(steelEnd.getDatVal().longValue()));
        }

    }


    private void sta2(TdSta sta, Map<String, TdChannel> channelMap, WpConvererInfo convererInfo) {

        //        enterTemperature	进站铁水温度	int
        TdChannel enterTemperature = channelMap.get("enterConverterTemper");
        convererInfo.setEnterTemperature(enterTemperature.getDatVal().intValue());


        //        ironMolt	兑铁水开始时刻	datetime		进站时间
//        TdChannel ironMolt = channelMap.get("ironMoltTm");
//        if (ironMolt.getDatVal().longValue() > BASE_TIME) {
//            convererInfo.setIronMolt(DateUtil.getDateTime(ironMolt.getDatVal().longValue()));
//        }

        //        blowStart 	吹炼开始时刻	datetime
        TdChannel blowStart = channelMap.get("beginOxygenTimeTm");
        if (blowStart.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setBlowStart(DateUtil.getDateTime(blowStart.getDatVal().longValue()));
        }

        //        blowEnd	吹炼结束时刻	datetime
        TdChannel blowEnd = channelMap.get("endOxygenTimeTm");
        if (blowEnd.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setBlowEnd(DateUtil.getDateTime(blowEnd.getDatVal().longValue()));
        }

        //        oxygenBlow	吹炼用氧气量	float(10, 2)
        TdChannel oxygenBlow = channelMap.get("blowOxygenAmt");
        convererInfo.setOxygenBlow(oxygenBlow.getDatVal());

        //        nitrogenBottom	底吹氮气量	float(10, 2)
        TdChannel nitrogenBottom = channelMap.get("bottonNitrogenAmt");
        convererInfo.setNitrogenBottom(nitrogenBottom.getDatVal());

        //        argonBottom	底吹氩气用量	float(10, 2)
        TdChannel argonBottom = channelMap.get("blowrArgonAmt");
        convererInfo.setArgonBottom(argonBottom.getDatVal());

        //        slagSplash	溅渣时刻	datetime
        TdChannel slagSplash = channelMap.get("slagSplashTime");
        if (slagSplash.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setSlagSplash(DateUtil.getDateTime(slagSplash.getDatVal().longValue()));
        }

        //        steelStart	出钢时刻	datetime
        TdChannel steelStart = channelMap.get("steelTappTime");
        if (steelStart.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setSteelStart(DateUtil.getDateTime(steelStart.getDatVal().longValue()));
        }

        //        exitTemperature	终点温度	int
        TdChannel exitTemperature = channelMap.get("exitConverterTemper");
        convererInfo.setExitTemperature(exitTemperature.getDatVal().intValue());

        //        exitWeigh	出钢重量	float(10, 2)
        TdChannel exitWeigh = channelMap.get("exitConverterWeigh");
        convererInfo.setExitWeigh(exitWeigh.getDatVal());

        //        steelEnd	出站时刻	datetime		本炉结束
        TdChannel steelEnd = channelMap.get("exitConverterTimeTm");
        if (steelEnd.getDatVal().longValue() > BASE_TIME) {
            convererInfo.setSteelEnd(DateUtil.getDateTime(steelEnd.getDatVal().longValue()));
        }


    }

}
