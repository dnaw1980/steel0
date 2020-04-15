package com.rss.steel_production.process.model;

public class CompositionInfoTO {
    private int pageNo=0;
    private int pageSize=0;
    private CompositionInfo compositionInfo;

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

    public CompositionInfo getCompositionInfo() {
        return compositionInfo;
    }

    public void setCompositionInfo(CompositionInfo compositionInfo) {
        this.compositionInfo = compositionInfo;
    }
}
