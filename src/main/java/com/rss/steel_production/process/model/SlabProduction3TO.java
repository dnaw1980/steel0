package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class SlabProduction3TO {
    private int pageNo = 0;
    private int pageSize = 0;
    private SlabProduction3 slabProduction3;

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

    public SlabProduction3 getSlabProduction3() {
        return slabProduction3;
    }

    public void setSlabProduction3(SlabProduction3 slabProduction3) {
        this.slabProduction3 = slabProduction3;
    }
}
