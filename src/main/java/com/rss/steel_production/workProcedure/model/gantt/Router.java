package com.rss.steel_production.workProcedure.model.gantt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Router {

    /**
     * name: "脱硫",
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

    /**
     * state: 1 // -1 未开始，0 进行中， 1 已完成
     * 对应调度明细 -1 < 2
     * 0 == 2
     * 1 > 2
     */
    private Integer state;
}
