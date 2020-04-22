package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class IronInfoTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private IronInfo ironInfo;
}
