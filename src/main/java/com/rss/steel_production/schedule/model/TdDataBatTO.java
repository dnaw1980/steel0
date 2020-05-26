package com.rss.steel_production.schedule.model;

import lombok.Data;

@Data
public class TdDataBatTO {

    private int pageNo = 0;

    private int pageSize = 0;

    private TdDataBat tdDataBat;
}