package com.rss.steel_production.schedule.model;

import java.util.List;

public class SteelScheduleReturn {
    private String chargeNo;
    private List<SteelSchedule> list;

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public List<SteelSchedule> getList() {
        return list;
    }

    public void setList(List<SteelSchedule> list) {
        this.list = list;
    }
}
