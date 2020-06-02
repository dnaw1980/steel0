package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.DpCastPlanDAO;
import com.rss.steel_production.workProcedure.model.DpCastPlan;
import com.rss.steel_production.workProcedure.service.DpCastPlanService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class DpCastPlanImpl extends AbstractService<DpCastPlan> implements DpCastPlanService {

    @Resource
    private DpCastPlanDAO dpCastPlanDAO;

}
