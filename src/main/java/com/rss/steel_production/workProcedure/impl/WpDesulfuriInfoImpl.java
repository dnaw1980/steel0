package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.workProcedure.dao.DpScheduleDetailDAO;
import com.rss.steel_production.workProcedure.dao.WpDesulfuriInfoDAO;
import com.rss.steel_production.workProcedure.model.DpScheduleDetail;
import com.rss.steel_production.workProcedure.model.WpDesulfuriInfo;
import com.rss.steel_production.workProcedure.service.WpDesulfuriInfoService;
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
public class WpDesulfuriInfoImpl extends AbstractService<WpDesulfuriInfo> implements WpDesulfuriInfoService {

    static Logger log;

    static {
        log = Logger.getLogger(WpDesulfuriInfoImpl.class.getName());
    }

    private static long BASE_TIME = DateUtil.getDateTime("2020-01-01 00:00:00").getTime();


    @Resource
    private WpDesulfuriInfoDAO wpDesulfuriInfoDAO;

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
        WpDesulfuriInfo desulfuriInfo = null;
        {
            Condition condition = new Condition(WpDesulfuriInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("staId", sta.getStaId());
            criteria.andIsNull("scheduleSeqId");


            List<WpDesulfuriInfo> wpList = this.wpDesulfuriInfoDAO.selectByCondition(condition);
            if (Tools.notEmpty(wpList)) {
                desulfuriInfo = wpList.get(0);
            }
        }

        if (desulfuriInfo == null) {
            log.error("站点：" + sta.getStaId() + ",没有对应的实时工序信息");
            return;
        }
        //计算工序数据，注意工位1 和 工位2 不一样，要分别处理。
        if (sta.getStaNo().intValue() == 1) {
            this.sta1(sta, channelMap, desulfuriInfo);
        } else {
            this.sta2(sta, channelMap, desulfuriInfo);
        }

        //更新到数据库
        this.wpDesulfuriInfoDAO.updateByPrimaryKey(desulfuriInfo);

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
            Condition condition = new Condition(WpDesulfuriInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("scheduleSeqId", scheduleSeqId);

            List<WpDesulfuriInfo> dsList = this.wpDesulfuriInfoDAO.selectByCondition(condition);

            if (Tools.notEmpty(dsList)) {
                WpDesulfuriInfo scDesulfuriInfo = dsList.get(0);

                desulfuriInfo.setDesulfuriInfoSn(scDesulfuriInfo.getDesulfuriInfoSn());
                desulfuriInfo.setStaId(null);
                desulfuriInfo.setScheduleSeqId(scheduleSeqId);

                this.wpDesulfuriInfoDAO.updateByPrimaryKey(desulfuriInfo);
            }
        }
    }


    private void sta1(TdSta sta, Map<String, TdChannel> channelMap, WpDesulfuriInfo desulfuriInfo) {

        //        enterTemperature	进站铁水温度	int 无
        //TODO        exitTemperature	出站铁水温度	int 无 转炉的 进站铁水温度
        //TODO        enterQty	进站称重	float(10, 2) 对应铁水信息的 废钢净量 + 铁水净重

        //TODO         exitWeight	出站称重	float(10, 2)

        //        startStation	进站时刻	datetime
        TdChannel startStation = channelMap.get("startStation");
        if (startStation.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setStartStation(DateUtil.getDateTime(startStation.getDatVal().longValue()));
        }

        // startFeed	加料开始时刻	datetime
        TdChannel startFeed = channelMap.get("startFeed");
        if (startFeed.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setStartFeed(DateUtil.getDateTime(startFeed.getDatVal().longValue()));
        }

        // exitFeed	加料结束时刻	datetime
        TdChannel exitFeed = channelMap.get("exitFeed");
        if (exitFeed.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setExitFeed(DateUtil.getDateTime(exitFeed.getDatVal().longValue()));
        }

        // startStir	搅拌开始时刻	datetime
        TdChannel startStir = channelMap.get("startStir");
        if (startStir.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setStartStir(DateUtil.getDateTime(startStir.getDatVal().longValue()));
        }

        // exitStir	搅拌结束时刻	datetime
        TdChannel exitStir = channelMap.get("exitStir");
        if (exitStir.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setExitStir(DateUtil.getDateTime(exitStir.getDatVal().longValue()));
        }

        // exitStation	出站时刻	datetime
        TdChannel exitStation = channelMap.get("exitStation");
        if (exitStation.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setExitStation(DateUtil.getDateTime(exitStation.getDatVal().longValue()));
        }

        // materialWeight	加料重量	float(10, 2) 料仓重量1 - 称量斗重量1
        //料仓重量1
        TdChannel binWeight = channelMap.get("binWeight1");

        //称量斗重量1
        TdChannel hopperWeight = channelMap.get("hopperWeight1");

        //加料重量
        desulfuriInfo.setMaterialWeight(hopperWeight.getDatVal().subtract(binWeight.getDatVal()));


    }


    private void sta2(TdSta sta, Map<String, TdChannel> channelMap, WpDesulfuriInfo desulfuriInfo) {


        //        enterTemperature	进站铁水温度	int 无
        //TODO        exitTemperature	出站铁水温度	int 无 转炉的 进站铁水温度
        //TODO        enterQty	进站称重	float(10, 2) 对应铁水信息的 废钢净量 + 铁水净重

        //TODO         exitWeight	出站称重	float(10, 2)

        //        startStation	进站时刻	datetime
        TdChannel startStation = channelMap.get("startStation");
        if (startStation.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setStartStation(DateUtil.getDateTime(startStation.getDatVal().longValue()));
        }

        // startFeed	加料开始时刻	datetime
        TdChannel startFeed = channelMap.get("startFeed");
        if (startFeed.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setStartFeed(DateUtil.getDateTime(startFeed.getDatVal().longValue()));
        }

        // exitFeed	加料结束时刻	datetime
        TdChannel exitFeed = channelMap.get("exitFeed");
        if (exitFeed.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setExitFeed(DateUtil.getDateTime(exitFeed.getDatVal().longValue()));
        }

        // startStir	搅拌开始时刻	datetime
        TdChannel startStir = channelMap.get("startStir");
        if (startStir.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setStartStir(DateUtil.getDateTime(startStir.getDatVal().longValue()));
        }

        // exitStir	搅拌结束时刻	datetime
        TdChannel exitStir = channelMap.get("exitStir");
        if (exitStir.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setExitStir(DateUtil.getDateTime(exitStir.getDatVal().longValue()));
        }

        // exitStation	出站时刻	datetime
        TdChannel exitStation = channelMap.get("exitStation");
        if (exitStation.getDatVal().longValue() > BASE_TIME) {
            desulfuriInfo.setExitStation(DateUtil.getDateTime(exitStation.getDatVal().longValue()));
        }

        // materialWeight	加料重量	float(10, 2) 料仓重量1 - 称量斗重量1
        //料仓重量1
        TdChannel binWeight = channelMap.get("binWeight2");

        //称量斗重量1
        TdChannel hopperWeight = channelMap.get("hopperWeight2");

        //加料重量
        desulfuriInfo.setMaterialWeight(hopperWeight.getDatVal().subtract(binWeight.getDatVal()));

    }

}
