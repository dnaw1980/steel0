package com.rss.steel_production.schedule.controller.bean;

import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.model.TdSta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealData {

    private String label;

    private String value;

    private int show = 0;

}
