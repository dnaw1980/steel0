package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class LfInfoTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private LfInfo lfInfo;

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

    public LfInfo getLfInfo() {
        return lfInfo;
    }

    public void setLfInfo(LfInfo lfInfo) {
        this.lfInfo = lfInfo;
    }
}
