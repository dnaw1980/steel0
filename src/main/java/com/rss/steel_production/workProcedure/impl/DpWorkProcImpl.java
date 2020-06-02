package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.DpWorkProcDAO;
import com.rss.steel_production.workProcedure.model.DpWorkProc;
import com.rss.steel_production.workProcedure.service.DpWorkProcService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
@Transactional
public class DpWorkProcImpl extends AbstractService<DpWorkProc> implements DpWorkProcService {

    @Resource
    private DpWorkProcDAO dpWorkProcDAO;

    @Override
    public Map<String, DpWorkProc> showAllMap() {
        List<DpWorkProc> workProcList = this.dpWorkProcDAO.selectAll();
        Map<String, DpWorkProc> rs = new HashMap<String, DpWorkProc>();

        for (DpWorkProc workProc : workProcList) {

            rs.put(workProc.getWorkProcId(), workProc);

        }
        return rs;
    }
}
