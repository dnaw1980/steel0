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
 * RH工序
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_rh_info")
public class WpRhInfo extends WpBase {

    //    rh_info_sn           int not null auto_increment comment 'RH精炼信息序号',
    @Id
    @Column(name = "rh_info_sn")
    private Integer rhInfoSn;

    /**
     * sta_id               varchar(36) comment '工位ID',
     */
    @Column(name = "sta_id")
    private String staId;

    //    schedule_seq_id      varchar(36) comment '调度序列ID',
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //    enterTemperature     int comment '进站温度',
    private Integer enterTemperature;

    //    refineTemperature1   int comment '过程一次测温',
    private Integer refineTemperature1;

    //    refineTemperature2   int comment '过程二次测温',
    private Integer refineTemperature2;

    //    exitTemperature      int comment '出站温度',
    private Integer exitTemperature;

    //    enterStation         datetime comment '进站时刻',
    private Timestamp enterStation;

    //    enterWeigh           decimal(16,2) comment '进炉钢水重量',
    private BigDecimal enterWeigh;

    //    circulaStart         datetime comment '环流开始时刻',
    private Timestamp circulaStart;

    //    breakVacuum          datetime comment '破真空时刻',
    private Timestamp breakVacuum;

    //    processEnd           datetime comment '结束处理时刻',
    private Timestamp processEnd;

    //    exitStation          datetime comment '出站时刻',
    private Timestamp exitStation;

    //    softBlow             int comment '软吹时间',
    private Integer softBlow;

    //    blowOxygen           int comment '吹氧时间',
    private Integer blowOxygen;

    //    oxygenConsume        decimal(16,2) comment '吹氧总量',
    private BigDecimal oxygenConsume;

    //    argonBottom          decimal(16,2) comment '底吹氩气消耗',
    private BigDecimal argonBottom;

    //    argonCircula         decimal(16,2) comment '环流氩气消耗',
    private BigDecimal argonCircula;

    //    waterConsume         decimal(16,2) comment '冷凝水消耗',
    private BigDecimal waterConsume;

    //    steamConsume         decimal(16,2) comment '蒸汽消耗',
    private BigDecimal steamConsume;

    //    gasConsume           decimal(16,2) comment '煤气消耗',
    private BigDecimal gasConsume;

    //    propaneConsume       decimal(16,2) comment '丙烷消耗',
    private BigDecimal propaneConsume;

    //    exitWeigh            decimal(16,2) comment '出炉重量',
    private BigDecimal exitWeigh;

    //    consumeSiFe          decimal(16,2) comment '硅铁加入量',
    private BigDecimal consumeSiFe;

    //    consumeMnFe          decimal(16,2) comment '锰铁加入量',
    private BigDecimal consumeMnFe;

    //    consumeAlFe          decimal(16,2) comment '铝铁加入量',
    private BigDecimal consumeAlFe;

    //    consumeOther         decimal(16,2) comment '其他合金加入量',
    private BigDecimal consumeOther;

}
