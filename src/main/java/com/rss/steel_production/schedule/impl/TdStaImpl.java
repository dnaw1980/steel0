package com.rss.steel_production.schedule.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.dao.TdStaDAO;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.schedule.service.TdStaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TdStaImpl extends AbstractService<TdSta> implements TdStaService {
    @Resource
    private TdStaDAO tdStaDAO;

}
