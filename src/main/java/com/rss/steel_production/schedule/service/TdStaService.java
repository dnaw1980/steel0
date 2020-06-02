package com.rss.steel_production.schedule.service;

import com.rss.framework.Service;
import com.rss.steel_production.schedule.model.TdSta;

import java.util.List;

public interface TdStaService extends Service<TdSta> {
    public void show();

    List<TdSta> showAll();
}
