package com.rss.steel_production.workProcedure.model;

import com.rss.steel_production.schedule.model.TdSta;
import lombok.Data;

@Data
public class DpCastPlanTO {

    private int pageNo = 0;

    private int pageSize = 0;

    private DpCastPlan dpCastPlan;
}