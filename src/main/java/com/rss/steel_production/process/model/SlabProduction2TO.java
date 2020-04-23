package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class SlabProduction2TO {
    private int pageNo = 0;
    private int pageSize = 0;
    private SlabProduction2 slabProduction2;

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

    public SlabProduction2 getSlabProduction2() {
        return slabProduction2;
    }

    public void setSlabProduction2(SlabProduction2 slabProduction2) {
        this.slabProduction2 = slabProduction2;
    }
}
