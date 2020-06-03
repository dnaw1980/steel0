package com.rss.steel_production.workProcedure.service;

import com.rss.framework.Service;
import com.rss.steel_production.workProcedure.model.DpCastPlan;
import com.rss.steel_production.workProcedure.model.DpCastPlanTO;

import java.util.List;

public interface DpCastPlanService extends Service<DpCastPlan> {

    DpCastPlan add(DpCastPlan src);

    List<DpCastPlan> list(DpCastPlanTO dpCastPlanTO);

    List<DpCastPlan> listExec();

}