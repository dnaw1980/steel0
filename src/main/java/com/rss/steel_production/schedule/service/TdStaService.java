package com.rss.steel_production.schedule.service;

import com.rss.framework.Service;
import com.rss.steel_production.schedule.controller.bean.RealDataBean;
import com.rss.steel_production.schedule.model.TdSta;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TdStaService extends Service<TdSta> {
    public void show();

    List<TdSta> showAll();

    /**
     * 查询站点实时数据
     *
     * @param stationNo
     * @return
     */
    RealDataBean realData(String stationNo);
}
