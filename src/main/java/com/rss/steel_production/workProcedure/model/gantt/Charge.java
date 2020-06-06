package com.rss.steel_production.workProcedure.model.gantt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Charge {

    /**
     * name: "炉次1", //炉次名
     */
    private String name;

    /**
     * beginTime: "2020-04-10 13:00:00", //计划开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp beginTime;

    /**
     * endTime: "2020-04-10 13:35:00", //计划结束时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;

    private List<Router> routers;
}
