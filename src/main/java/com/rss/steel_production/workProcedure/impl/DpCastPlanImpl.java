package com.rss.steel_production.workProcedure.impl;

import com.github.pagehelper.PageHelper;
import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.schedule.service.TdStaService;
import com.rss.steel_production.workProcedure.dao.DpCastPlanDAO;
import com.rss.steel_production.workProcedure.model.DpCastPlan;
import com.rss.steel_production.workProcedure.model.DpCastPlanTO;
import com.rss.steel_production.workProcedure.model.DpTechCard;
import com.rss.steel_production.workProcedure.model.DpTechRoute;
import com.rss.steel_production.workProcedure.service.DpCastPlanService;
import com.rss.steel_production.workProcedure.service.DpTechCardService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
@Transactional
public class DpCastPlanImpl extends AbstractService<DpCastPlan> implements DpCastPlanService {

    @Resource
    private DpCastPlanDAO dpCastPlanDAO;

    @Resource
    private DpTechCardService dpTechCardService;

    @Resource
    private TdStaService tdStaService;


    @Override
    public DpCastPlan add(DpCastPlan src) {
        /*
        判断是否有浇次号，如果没有，查询表中最大的浇次号，加1
         */
        if (Tools.empty(src.getCastNo())) {

            Condition condition = new Condition(DpCastPlan.class);
            condition.setOrderByClause("cast_no desc");
            PageHelper.startPage(1, 1);

            List<DpCastPlan> lastCastlist = dpCastPlanDAO.selectByCondition(condition);
            if (Tools.notEmpty(lastCastlist)) {
                int castNo = Integer.parseInt(lastCastlist.get(0).getCastNo());
                castNo++;
                src.setCastNo(String.valueOf(castNo));
            } else {
                //取年的后两位
                int year = DateUtil.getYear();
                String castNoStr = String.valueOf(year).substring(2) + "0001";
                src.setCastNo(castNoStr);
            }
        }

        //设置主键
        src.setCastPlanId(Tools.getUUID());
        src.setState(0);
        int rs = this.dpCastPlanDAO.insert(src);

        if (rs == 1) {
            DpCastPlan rsObj = this.dpCastPlanDAO.selectByPrimaryKey(src.getCastPlanId());
            return rsObj;
        }

        return null;
    }

    @Override
    public List<DpCastPlan> list(DpCastPlanTO dpCastPlanTO) {

        Condition condition = new Condition(DpCastPlan.class);
        condition.setOrderByClause("cast_no desc");
        PageHelper.startPage(dpCastPlanTO.getPageNo(), dpCastPlanTO.getPageSize());

        //工位
        if (Tools.notEmpty(dpCastPlanTO.getDpCastPlan().getStaId())) {
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("staId", dpCastPlanTO.getDpCastPlan().getStaId());
        }

        //钢种
        if (Tools.notEmpty(dpCastPlanTO.getDpCastPlan().getTechCardId())) {
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("techCardId", dpCastPlanTO.getDpCastPlan().getTechCardId());
        }

        //状态
        if (dpCastPlanTO.getDpCastPlan().getState() != null) {
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("state", dpCastPlanTO.getDpCastPlan().getState());
        }

        //浇次号
        if (Tools.notEmpty(dpCastPlanTO.getDpCastPlan().getCastNo())) {
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("castNo", dpCastPlanTO.getDpCastPlan().getCastNo());
        }

        List<DpCastPlan> rsList = this.dpCastPlanDAO.selectByCondition(condition);
        this.fillCastPlan(rsList);
        return rsList;

    }

    @Override
    public List<DpCastPlan> listExec() {
        Condition condition = new Condition(DpCastPlan.class);
        condition.setOrderByClause("cast_no asc");

        Condition.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("state", DpCastPlan.STATE_EXEC);

        List<DpCastPlan> rsList = this.dpCastPlanDAO.selectByCondition(condition);
        this.fillCastPlan(rsList);
        return rsList;
    }

    @Override
    public DpCastPlan get(String castPlanId) {
        DpCastPlan rs = this.dpCastPlanDAO.selectByPrimaryKey(castPlanId);
        List<DpCastPlan> rsList = new ArrayList<DpCastPlan>(1);
        rsList.add(rs);

        this.fillCastPlan(rsList);

        return rsList.get(0);
    }

    /**
     * 填充浇次对应的工艺卡和工序信息
     *
     * @param castList
     */
    private void fillCastPlan(List<DpCastPlan> castList) {
        List<DpTechCard> cardList = this.dpTechCardService.allTechCard();
        Map<String, DpTechCard> cardMap = new HashMap<String, DpTechCard>();

        for (DpTechCard card : cardList) {
            cardMap.put(card.getTechCardId(), card);
        }

        ////////////////////////////////////////////////
        List<TdSta> staList = this.tdStaService.showAll();
        Map<String, TdSta> staMap = new HashMap<String, TdSta>();

        for (TdSta sta : staList) {
            staMap.put(sta.getStaId(), sta);
        }


        for (DpCastPlan cast : castList) {
            cast.setTdSta(staMap.get(cast.getStaId()));
            cast.setDpTechCard(cardMap.get(cast.getTechCardId()));
        }
    }
}
