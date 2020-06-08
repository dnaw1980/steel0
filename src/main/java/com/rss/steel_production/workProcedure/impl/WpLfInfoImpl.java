package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.dao.TdChannelDAO;
import com.rss.steel_production.schedule.dao.TdStaDAO;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.workProcedure.dao.DpScheduleDetailDAO;
import com.rss.steel_production.workProcedure.dao.WpLfInfoDAO;
import com.rss.steel_production.workProcedure.model.DpScheduleDetail;
import com.rss.steel_production.workProcedure.model.WpCasInfo;
import com.rss.steel_production.workProcedure.model.WpLfInfo;
import com.rss.steel_production.workProcedure.service.WpLfInfoService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
@Transactional
public class WpLfInfoImpl extends AbstractService<WpLfInfo> implements WpLfInfoService {

    static Logger log;

    static {
        log = Logger.getLogger(WpLfInfoImpl.class.getName());
    }

    private static long BASE_TIME = DateUtil.getDateTime("2020-01-01 00:00:00").getTime();

    @Resource
    private WpLfInfoDAO wpLfInfoDAO;

    @Resource
    private TdStaDAO tdStaDAO;

    @Resource
    private TdChannelDAO tdChannelDAO;

    @Resource
    private DpScheduleDetailDAO scheduleDetailDAO;

    private List<TdChannel> listChannelForSta(TdSta sta) {

        Condition condition = new Condition(TdChannel.class);
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andEqualTo("staId", sta.getStaId());

        List<TdChannel> channelList = this.tdChannelDAO.selectByCondition(condition);
        return channelList;
    }

    /**
     * 同步站点工序数据，从实时数据同步到工序数据。
     *
     * @param sta
     */
    @Override
    public void synWpData(TdSta sta, Map<String, TdChannel> channelMap) {

        //LF比较特殊，两个工位数据在一个工位里面。
        // 所以必须要重新查 LF1的 channelMap
        TdSta sta1 = null;
        {
            Condition condition = new Condition(TdSta.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleStation", "1#LF");

            List<TdSta> staList = this.tdStaDAO.selectByCondition(condition);
            if (Tools.notEmpty(staList)) {
                sta1 = staList.get(0);
                sta1.setChannelList(this.listChannelForSta(sta1));

                channelMap = new HashMap<String, TdChannel>();
                for (TdChannel ch : sta1.getChannelList()) {
                    channelMap.put(ch.getComTag(), ch);
                }
            }
        }


        //TODO
        /*
        1、查询本站点的实时工序数据。
        2、逐步从实时数据中计算中工序数据。
        3、更新工序数据到数据库
        4、查找调度工序数据，复制实时工序数据到调度工序数据中。
         */
        WpLfInfo wpLfInfo = null;
        {

            Condition condition = new Condition(WpLfInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("staId", sta.getStaId());
            criteria.andIsNull("scheduleSeqId");


            List<WpLfInfo> wpList = this.wpLfInfoDAO.selectByCondition(condition);
            if (Tools.notEmpty(wpList)) {
                wpLfInfo = wpList.get(0);
            }
        }

        if (wpLfInfo == null) {
            log.error("站点：" + sta.getStaId() + ",没有对应的实时工序信息");
            return;
        }
        //计算工序数据，注意工位1 和 工位2 不一样，要分别处理。
        if (sta.getStaNo().intValue() == 1) {
            this.sta1(sta, channelMap, wpLfInfo);
        } else {
            this.sta2(sta, channelMap, wpLfInfo);
        }

        //更新到数据库
        this.wpLfInfoDAO.updateByPrimaryKey(wpLfInfo);

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
            Condition condition = new Condition(WpLfInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", scheduleSeqId);

            List<WpLfInfo> dsList = this.wpLfInfoDAO.selectByCondition(condition);

            if (Tools.notEmpty(dsList)) {
                WpLfInfo scWpInfo = dsList.get(0);

                wpLfInfo.setLfInfoSn(scWpInfo.getLfInfoSn());
                wpLfInfo.setStaId(null);
                wpLfInfo.setScheduleSeqId(scheduleSeqId);

                this.wpLfInfoDAO.updateByPrimaryKey(wpLfInfo);
            }
        }
    }


    private void sta1(TdSta sta, Map<String, TdChannel> channelMap, WpLfInfo wpLfInfo) {


//        enterTemperature	进站温度			℃
//        TdChannel enterTemperature = channelMap.get("1enterStation");
//        if (enterTemperature.getDatVal().longValue() > BASE_TIME) {
//            wpLfInfo.setEnterTemperature(DateUtil.getDateTime(enterTemperature.getDatVal().longValue()));
//        }

//        refineTemperature1	过程一次测温	按照温度变化		℃
//        refineTemperature2	过程二次测温			℃
//        exitTemperature	出站温度			℃
//        electricityStart	送电开始时刻			年月日时分秒
//        electricityEnd	送电结束时刻			年月日时分秒
//        softBlowStart	软吹开始时刻			年月日时分秒
//        softBlowEnd	软吹结束时刻			年月日时分秒
//        softBlowPress	平均软吹压力			Mpa
//        enterStation	进站时刻	datetime	32	进站钢包车到调包位
        TdChannel enterStation = channelMap.get("1enterStation");
        if (enterStation.getDatVal().longValue() > BASE_TIME) {
            wpLfInfo.setEnterStation(DateUtil.getDateTime(enterStation.getDatVal().longValue()));
        }

//        enterWeigh	进站钢水重量	float(10, 2)	20	进站重量
//        powerConsume	LF电耗	float(10, 2)
//        voltageA	二次A相电压	float(10, 2)
//        voltageB	二次B相电压	float(10, 2)
//        voltageC	二次C相电压	float(10, 2)
//        electricityA	二次A相电流	float(10, 2)
//        electricityB	二次B相电流	float(10, 2)
//        electricityC	二次C相电流	float(10, 2)
//        heatTime	加热时间长度	int
//        smeltTime	精炼时间	int	32
//        consumeOxygen	底吹氩总量	float(10, 2) 1#车1路氩气累计流量 + 1#车2路氩气累计流量
        TdChannel consumeOxygen1 = channelMap.get("1cas12");
        TdChannel consumeOxygen2 = channelMap.get("1cas13");
        wpLfInfo.setConsumeOxygen(consumeOxygen1.getDatVal().add(consumeOxygen2.getDatVal()));

//        exitStation	出站时刻	datetime	32	钢包出站，钢包车到调车位
        TdChannel exitStation = channelMap.get("1exitStation");
        if (exitStation.getDatVal().longValue() > BASE_TIME) {
            wpLfInfo.setExitStation(DateUtil.getDateTime(exitStation.getDatVal().longValue()));
        }
//        exitWeigh	出站钢水重量	float(10, 2)	20	出站重量


    }

    private void sta2(TdSta sta, Map<String, TdChannel> channelMap, WpLfInfo wpLfInfo) {

        //        enterStation	进站时刻	datetime	32	进站钢包车到调包位
        TdChannel enterStation = channelMap.get("2enterStation");
        if (enterStation.getDatVal().longValue() > BASE_TIME) {
            wpLfInfo.setEnterStation(DateUtil.getDateTime(enterStation.getDatVal().longValue()));
        }

        //        consumeOxygen	底吹氩总量	float(10, 2) 1#车1路氩气累计流量 + 1#车2路氩气累计流量
        TdChannel consumeOxygen1 = channelMap.get("1cas14");
        TdChannel consumeOxygen2 = channelMap.get("1cas15");
        wpLfInfo.setConsumeOxygen(consumeOxygen1.getDatVal().add(consumeOxygen2.getDatVal()));

        //        exitStation	出站时刻	datetime	32	钢包出站，钢包车到调车位
        TdChannel exitStation = channelMap.get("2exitStation");
        if (exitStation.getDatVal().longValue() > BASE_TIME) {
            wpLfInfo.setExitStation(DateUtil.getDateTime(exitStation.getDatVal().longValue()));
        }
    }

}
