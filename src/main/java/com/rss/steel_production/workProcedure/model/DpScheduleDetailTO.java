package com.rss.steel_production.workProcedure.model;

import lombok.Data;

@Data
public class DpScheduleDetailTO {

    private int pageNo = 0;

    private int pageSize = 0;

    private DpScheduleDetail dpScheduleDetail;
}