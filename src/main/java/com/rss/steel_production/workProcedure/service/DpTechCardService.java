package com.rss.steel_production.workProcedure.service;

import com.rss.framework.Service;
import com.rss.steel_production.workProcedure.model.DpTechCard;

import java.util.List;

public interface DpTechCardService extends Service<DpTechCard> {

    List<DpTechCard> allTechCard();
}