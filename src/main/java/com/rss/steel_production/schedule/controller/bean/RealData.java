package com.rss.steel_production.schedule.controller.bean;

import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.model.TdSta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealData {

    private String label;

    private String value;

    private int show = 0;

    private int dkCls = -1;

    private int realVal = 0;

    public RealData(String label, String value, int show) {
        this.label = label;
        this.value = value;
        this.show = show;
    }
}
