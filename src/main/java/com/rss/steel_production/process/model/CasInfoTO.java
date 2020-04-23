package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class CasInfoTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private CasInfo casInfo;

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

    public CasInfo getCasInfo() {
        return casInfo;
    }

    public void setCasInfo(CasInfo casInfo) {
        this.casInfo = casInfo;
    }
}
