package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class BilletProductionTO {

    private int pageNo = 0;

    private int pageSize = 0;

    private BilletProduction billetProduction;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public BilletProduction getBilletProduction() {
        return billetProduction;
    }

    public void setBilletProduction(BilletProduction billetProduction) {
        this.billetProduction = billetProduction;
    }
}
