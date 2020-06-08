package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.workProcedure.dao.DpScheduleDetailDAO;
import com.rss.steel_production.workProcedure.dao.WpCasInfoDAO;
import com.rss.steel_production.workProcedure.model.DpScheduleDetail;
import com.rss.steel_production.workProcedure.model.WpCasInfo;
import com.rss.steel_production.workProcedure.model.WpConvererInfo;
import com.rss.steel_production.workProcedure.model.WpDesulfuriInfo;
import com.rss.steel_production.workProcedure.service.WpCasInfoService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
@Transactional
public class WpCasInfoImpl extends AbstractService<WpCasInfo> implements WpCasInfoService {

    static Logger log;

    static {
        log = Logger.getLogger(WpCasInfoImpl.class.getName());
    }

    private static long BASE_TIME = DateUtil.getDateTime("2020-01-01 00:00:00").getTime();


    @Resource
    private WpCasInfoDAO wpCasInfoDAO;

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
        WpCasInfo wpCasInfo = null;
        {
            Condition condition = new Condition(WpCasInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("staId", sta.getStaId());
            criteria.andIsNull("scheduleSeqId");


            List<WpCasInfo> wpList = this.wpCasInfoDAO.selectByCondition(condition);
            if (Tools.notEmpty(wpList)) {
                wpCasInfo = wpList.get(0);
            }
        }

        if (wpCasInfo == null) {
            log.error("站点：" + sta.getStaId() + ",没有对应的实时工序信息");
            return;
        }
        //计算工序数据，注意工位1 和 工位2 不一样，要分别处理。
        if (sta.getStaNo().intValue() == 1) {
            this.sta1(sta, channelMap, wpCasInfo);
        } else {
            this.sta2(sta, channelMap, wpCasInfo);
        }

        //更新到数据库
        this.wpCasInfoDAO.updateByPrimaryKey(wpCasInfo);

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
            Condition condition = new Condition(WpCasInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", scheduleSeqId);

            List<WpCasInfo> dsList = this.wpCasInfoDAO.selectByCondition(condition);

            if (Tools.notEmpty(dsList)) {
                WpCasInfo scWpInfo = dsList.get(0);

                wpCasInfo.setCasInfoSn(scWpInfo.getCasInfoSn());
                wpCasInfo.setStaId(null);
                wpCasInfo.setScheduleSeqId(scheduleSeqId);

                this.wpCasInfoDAO.updateByPrimaryKey(wpCasInfo);
            }
        }
    }


    private void sta1(TdSta sta, Map<String, TdChannel> channelMap, WpCasInfo wpCasInfo) {

//        enterStationTime	进站时刻	datetime		进站时间
        TdChannel enterStationTime = channelMap.get("enterStationTime");
        if (enterStationTime.getDatVal().longValue() > BASE_TIME) {
            wpCasInfo.setEnterStationTime(DateUtil.getDateTime(enterStationTime.getDatVal().longValue()));
        }

//        exitStationTime	出站时刻	datetime		出站时间
        TdChannel exitStationTime = channelMap.get("exitStationTime");
        if (exitStationTime.getDatVal().longValue() > BASE_TIME) {
            wpCasInfo.setExitStationTime(DateUtil.getDateTime(exitStationTime.getDatVal().longValue()));
        }

//        enterTemperature	进站温度	float(10, 2)		进站时间
//        TdChannel enterTemperature = channelMap.get("enterStationTime");
//        wpCasInfo.setEnterTemperature(enterTemperature.getDatVal().intValue());

//        exitTemperature	出站温度	float(10, 2)		出站时间
//        TdChannel enterTemperature = channelMap.get("enterStationTime");
//        wpCasInfo.setEnterTemperature(enterTemperature.getDatVal().intValue());

//        enterWeigh	进炉钢水重量	float(10, 2)	20	进站重量
//        exitWeigh	出炉钢水重量	float(10, 2)	20	进站重量
//        oxygenTotal	吹氧总时间	int	20
//        bottomBlow	底吹氩总时间	int	20
//        consumeOxygen	吹氧含量	float(10, 2)
//        nitrogenPress	底吹氩平均流量	float(10, 2)



    }


    private void sta2(TdSta sta, Map<String, TdChannel> channelMap, WpCasInfo wpCasInfo) {


//        enterStationTime	进站时刻	datetime		进站时间
        TdChannel enterStationTime = channelMap.get("enterStationTime");
        if (enterStationTime.getDatVal().longValue() > BASE_TIME) {
            wpCasInfo.setEnterStationTime(DateUtil.getDateTime(enterStationTime.getDatVal().longValue()));
        }

//        exitStationTime	出站时刻	datetime		出站时间
        TdChannel exitStationTime = channelMap.get("exitStationTime");
        if (exitStationTime.getDatVal().longValue() > BASE_TIME) {
            wpCasInfo.setExitStationTime(DateUtil.getDateTime(exitStationTime.getDatVal().longValue()));
        }

//        enterTemperature	进站温度	float(10, 2)		进站时间
//        TdChannel enterTemperature = channelMap.get("enterStationTime");
//        wpCasInfo.setEnterTemperature(enterTemperature.getDatVal().intValue());

//        exitTemperature	出站温度	float(10, 2)		出站时间
//        TdChannel enterTemperature = channelMap.get("enterStationTime");
//        wpCasInfo.setEnterTemperature(enterTemperature.getDatVal().intValue());

//        enterWeigh	进炉钢水重量	float(10, 2)	20	进站重量
//        exitWeigh	出炉钢水重量	float(10, 2)	20	进站重量
//        oxygenTotal	吹氧总时间	int	20
//        bottomBlow	底吹氩总时间	int	20
//        consumeOxygen	吹氧含量	float(10, 2)
//        nitrogenPress	底吹氩平均流量	float(10, 2)


    }

}
