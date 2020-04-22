package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class CasInfoTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private CasInfo casInfo;

}
