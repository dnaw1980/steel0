package com.rss.steel_production.workProcedure.model;

import lombok.Data;

@Data
public class WpLfInfoTO {

    private int pageNo = 0;

    private int pageSize = 0;

    private WpLfInfo wpLfInfo;
}