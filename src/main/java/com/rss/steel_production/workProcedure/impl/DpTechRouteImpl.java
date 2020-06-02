package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.DpTechRouteDAO;
import com.rss.steel_production.workProcedure.model.DpTechRoute;
import com.rss.steel_production.workProcedure.service.DpTechRouteService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class DpTechRouteImpl extends AbstractService<DpTechRoute> implements DpTechRouteService {

    @Resource
    private DpTechRouteDAO dpTechRouteDAO;

}
