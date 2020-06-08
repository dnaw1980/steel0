package com.rss.steel_production.workProcedure.service;

import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;

import java.util.Map;

public interface WpBaseService {
    /**
     * 同步站点工序数据，从实时数据同步到工序数据。
     *
     * @param sta
     * @param channelMap
     */
    void synWpData(TdSta sta, Map<String, TdChannel> channelMap);
}
