package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.DpScheduleSeqDAO;
import com.rss.steel_production.workProcedure.model.DpScheduleSeq;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class DpScheduleSeqImpl extends AbstractService<DpScheduleSeq> implements DpScheduleSeqService {

    @Resource
    private DpScheduleSeqDAO dpScheduleSeqDAO;

}
