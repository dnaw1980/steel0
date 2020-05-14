package com.rss.steel_production.schedule.controller.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ExitStationBean extends ScheduleBase {

    private String temperature;

    private String weight;

    private Date actualExit;
}
