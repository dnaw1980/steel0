package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.workProcedure.dao.DpTechCardDAO;
import com.rss.steel_production.workProcedure.dao.DpTechRouteDAO;
import com.rss.steel_production.workProcedure.model.DpTechCard;
import com.rss.steel_production.workProcedure.model.DpTechRoute;
import com.rss.steel_production.workProcedure.model.DpWorkProc;
import com.rss.steel_production.workProcedure.service.DpTechCardService;
import com.rss.steel_production.workProcedure.service.DpWorkProcService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@EnableScheduling
@Transactional
public class DpTechCardImpl extends AbstractService<DpTechCard> implements DpTechCardService {

    @Resource
    private DpTechCardDAO dpTechCardDAO;

    @Resource
    private DpTechRouteDAO dpTechRouteDAO;

    @Resource
    private DpWorkProcService workProcService;

    @Override
    public List<DpTechCard> allTechCard() {
        /*
        查询所有工艺卡，包含工艺路径
         */

        List<DpTechCard> techCards = this.dpTechCardDAO.selectAll();

        Condition condition = new Condition(DpTechRoute.class);
        condition.setOrderByClause("order_sn asc");

        List<DpTechRoute> techRoutes = this.dpTechRouteDAO.selectByCondition(condition);

        Map<String, DpWorkProc> workProcMap = workProcService.showAllMap();

        for (DpTechCard techCard : techCards) {
            List<DpTechRoute> routes = new ArrayList<DpTechRoute>();
            techCard.setTechRouteList(routes);
            //是本工艺卡的，放到本工艺卡的 route List 里面去
            for (DpTechRoute techRoute : techRoutes) {
                if (techRoute.getTechCardId().equals(techCard.getTechCardId())) {
                    routes.add(techRoute);
                    techRoute.setWorkProc(workProcMap.get(techRoute.getWorkProcId()));
                }
            }
        }


        return techCards;
    }
}
