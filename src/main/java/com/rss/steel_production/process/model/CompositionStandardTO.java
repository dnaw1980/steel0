package com.rss.steel_production.process.model;

public class CompositionStandardTO {

    private int pageNo=0;
    private int pageSize=0;
    private CompositionStandard compositionStandard;

    public CompositionStandard getCompositionStandard() {
        return compositionStandard;
    }

    public void setCompositionStandard(CompositionStandard compositionStandard) {
        this.compositionStandard = compositionStandard;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
