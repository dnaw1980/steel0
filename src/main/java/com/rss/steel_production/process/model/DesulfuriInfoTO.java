package com.rss.steel_production.process.model;

import lombok.Data;

@Data
public class DesulfuriInfoTO {
    private int pageNo = 0;
    private int pageSize = 0;
    private DesulfuriInfo desulfuriInfo;

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

    public DesulfuriInfo getDesulfuriInfo() {
        return desulfuriInfo;
    }

    public void setDesulfuriInfo(DesulfuriInfo desulfuriInfo) {
        this.desulfuriInfo = desulfuriInfo;
    }
}
