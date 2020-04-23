package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class ConvererInfoTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private ConvererInfo convererInfo;

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

    public ConvererInfo getConvererInfo() {
        return convererInfo;
    }

    public void setConvererInfo(ConvererInfo convererInfo) {
        this.convererInfo = convererInfo;
    }
}
