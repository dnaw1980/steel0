package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.BilletProductionDAO;
import com.rss.steel_production.process.model.BilletProduction;
import com.rss.steel_production.process.service.BilletProductionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BilletProductionImpl extends AbstractService<BilletProduction> implements BilletProductionService {
    @Resource
    private BilletProductionDAO billetProductionDAO;

}
