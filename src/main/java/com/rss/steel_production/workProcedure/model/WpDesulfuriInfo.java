package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 脱硫工序
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_desulfuri_info")
public class WpDesulfuriInfo extends WpBase {
    //    desulfuri_info_sn    int not null auto_increment comment '脱硫信息序号',
    @Id
    @Column(name = "desulfuri_info_sn")
    private Integer desulfuriInfoSn;

    /**
     * sta_id               varchar(36) comment '工位ID',
     */
    @Column(name = "sta_id")
    private String staId;

    //    schedule_seq_id      varchar(36) comment '调度序列ID',
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //    enterTemperature     int comment '进站铁水温度',
    private Integer enterTemperature;

    //    exitTemperature      int comment '出站铁水温度',
    private Integer exitTemperature;

    //    enterQty             decimal(16,2) comment '进站称重',
    private BigDecimal enterQty;

    //    exitWeight           decimal(16,2) comment '出站称重',
    private BigDecimal exitWeight;

    //    startStation         datetime comment '进站时刻',
    private Timestamp startStation;

    //    startFeed            datetime comment '加料开始时刻',
    private Timestamp startFeed;

    //    exitFeed             datetime comment '加料结束时刻',
    private Timestamp exitFeed;

    //    startStir            datetime comment '搅拌开始时刻',
    private Timestamp startStir;

    //    exitStir             datetime comment '搅拌结束时刻',
    private Timestamp exitStir;

    //    materialWeight       decimal(16,2) comment '加料重量',
    private BigDecimal materialWeight;

    //    exitStation          datetime comment '出站时刻',
    private Timestamp exitStation;

}
