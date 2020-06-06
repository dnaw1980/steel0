package com.rss.steel_production.workProcedure.controller.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 进出站点时用到的数据
 */
@Data
public class EnterExitStaBean {
    /**
     * stationName: "1#KR",
     */
    private String stationName;

    /**
     * actualEnter: "2020-05-27 13:02:10",
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp actualEnter;

    /**
     * actualExit: "2020-05-27 14:02:10",
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp actualExit;

    /**
     * temperature:"1234",
     */
    private BigDecimal temperature;

    /**
     * weight:"123123",
     */
    private BigDecimal weight;

    /**
     * scheduleId:'19798e2e-9576-4a87-98ff-b113d1011a91'
     */
    private String scheduleId;
}
