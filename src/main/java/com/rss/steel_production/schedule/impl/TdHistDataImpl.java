package com.rss.steel_production.schedule.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.dao.TdHistDataDAO;
import com.rss.steel_production.schedule.model.TdHistData;
import com.rss.steel_production.schedule.service.TdHistDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TdHistDataImpl extends AbstractService<TdHistData> implements TdHistDataService {
    @Resource
    private TdHistDataDAO tdHistDataDAO;

}
