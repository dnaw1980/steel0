package com.rss.steel_production.process.model;

public class RealDataTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private RealData realData;

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

    public RealData getRealData() {
        return realData;
    }

    public void setRealData(RealData realData) {
        this.realData = realData;
    }
}