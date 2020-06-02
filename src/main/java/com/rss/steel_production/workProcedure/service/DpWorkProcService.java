package com.rss.steel_production.workProcedure.service;

import com.rss.framework.Service;
import com.rss.steel_production.workProcedure.model.DpWorkProc;

import java.util.Map;

public interface DpWorkProcService extends Service<DpWorkProc> {

    Map<String, DpWorkProc> showAllMap();
}