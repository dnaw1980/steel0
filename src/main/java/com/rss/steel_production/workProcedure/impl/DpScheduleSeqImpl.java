package com.rss.steel_production.workProcedure.impl;

import com.github.pagehelper.PageHelper;
import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.DpScheduleSeqDAO;
import com.rss.steel_production.workProcedure.model.DpCastPlan;
import com.rss.steel_production.workProcedure.model.DpScheduleSeq;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.tools.Tools;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@EnableScheduling
@Transactional
public class DpScheduleSeqImpl extends AbstractService<DpScheduleSeq> implements DpScheduleSeqService {

    @Resource
    private DpScheduleSeqDAO dpScheduleSeqDAO;

    @Override
    public boolean castHasScheduleSeq(String castPlanId) {

        Condition condition = new Condition(DpScheduleSeq.class);
        Condition.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("castPlanId", castPlanId);

        PageHelper.startPage(1, 1);
        List<DpScheduleSeq> seqList = dpScheduleSeqDAO.selectByCondition(condition);

        if (Tools.empty(seqList)) {
            return false;
        } else {
            return true;
        }

    }
}
