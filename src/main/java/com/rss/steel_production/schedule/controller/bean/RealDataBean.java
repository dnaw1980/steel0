package com.rss.steel_production.schedule.controller.bean;

import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.model.TdSta;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RealDataBean {

    private String dtTime;

    private List<RealData> realDataList;

}
