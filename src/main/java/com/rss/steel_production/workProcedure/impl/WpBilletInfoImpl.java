package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.workProcedure.dao.WpBilletInfoDAO;
import com.rss.steel_production.workProcedure.model.WpBilletInfo;
import com.rss.steel_production.workProcedure.service.WpBilletInfoService;
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
public class WpBilletInfoImpl extends AbstractService<WpBilletInfo> implements WpBilletInfoService {

    @Resource
    private WpBilletInfoDAO wpBilletInfoDAO;


    static Logger log;

    static {
        log = Logger.getLogger(WpBilletInfoImpl.class.getName());
    }

    private static long BASE_TIME = DateUtil.getDateTime("2020-01-01 00:00:00").getTime();

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
        WpBilletInfo BilletInfo = null;
        {
            Condition condition = new Condition(WpBilletInfo.class);

            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("staId", sta.getStaId());
            criteria.andIsNull("scheduleSeqId");


            List<WpBilletInfo> wpList = this.wpBilletInfoDAO.selectByCondition(condition);
            if (Tools.notEmpty(wpList)) {
                BilletInfo = wpList.get(0);
            }
        }

        if (BilletInfo == null) {
            log.error("站点：" + sta.getStaId() + ",没有对应的实时工序信息");
            return;
        }
        //计算工序数据，注意工位1 和 工位2 不一样，要分别处理。
//        if (sta.getStaNo().intValue() == 1) {
        this.sta1(sta, channelMap, BilletInfo);
//        } else {
//            this.sta2(sta, channelMap, BilletInfo);
//        }

        //更新到数据库
        this.wpBilletInfoDAO.updateByPrimaryKey(BilletInfo);

        //TODO 查找调度工序信息
    }


    private void sta1(TdSta sta, Map<String, TdChannel> channelMap, WpBilletInfo BilletInfo) {

//        baleNo	大包包号	string
//        baleArriveTime	大包到达时刻	datetime
//        baleArriveWeight	大包到达重量	float(10,2)
//        baleArriveTemperature	大包到达温度	int
//        baleLeaveTime	大包离开时刻	datetime
//        baleLeaveWeight	大包离开重量	float(10,2)
//        tundishTemperature1	中包第一次测温温度	int
//        tundishTemperature2	中包第二次测温温度	int
//        tundishTemperature3	中包第三次测温温度	int
//        waterEnterTemperature	进水温度	int
//        waterExitTemperature	出水温度	int
//        castStartTime	开浇时刻	datetime
//        castStopTime	停浇时刻	datetime

    }


}
