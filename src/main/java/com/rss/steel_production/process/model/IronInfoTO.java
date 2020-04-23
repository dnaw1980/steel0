package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class IronInfoTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private IronInfo ironInfo;

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

    public IronInfo getIronInfo() {
        return ironInfo;
    }

    public void setIronInfo(IronInfo ironInfo) {
        this.ironInfo = ironInfo;
    }
}
