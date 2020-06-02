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
 * LF工序
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_lf_info")
public class WpLfInfo {
    //    lf_info_sn           int not null auto_increment comment 'LF精炼信息序号',
    @Id
    @Column(name = "lf_info_sn")
    private Integer lfInfoSn;

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

    //    electricityStart     datetime comment '送电开始时刻',
    private Timestamp electricityStart;

    //    electricityEnd       datetime comment '送电结束时刻',
    private Timestamp electricityEnd;

    //    softBlowStart        datetime comment '软吹开始时刻',
    private Timestamp softBlowStart;

    //    softBlowEnd          datetime comment '软吹结束时刻',
    private Timestamp softBlowEnd;

    //    softBlowPress        decimal(16,2) comment '平均软吹压力',
    private BigDecimal softBlowPress;

    //    enterStation         datetime comment '进站时刻',
    private Timestamp enterStation;

    //    enterWeigh           decimal(16,2) comment '进站钢水重量',
    private BigDecimal enterWeigh;

    //    powerConsume         decimal(16,2) comment 'LF电耗',
    private BigDecimal powerConsume;

    //    voltageA             decimal(16,2) comment '二次A相电压',
    private BigDecimal voltageA;

    //    voltageB             decimal(16,2) comment '二次B相电压',
    private BigDecimal voltageB;

    //    voltageC             decimal(16,2) comment '二次C相电压',
    private BigDecimal voltageC;

    //    electricityA         decimal(16,2) comment '二次A相电流',
    private BigDecimal electricityA;

    //    electricityB         decimal(16,2) comment '二次B相电流',
    private BigDecimal electricityB;

    //    electricityC         decimal(16,2) comment '二次C相电流',
    private BigDecimal electricityC;

    //    heatTime             int comment '加热时间长度',
    private Integer heatTime;

    //    smeltTime            int comment '精炼时间',
    private Integer smeltTime;

    //    consumeOxygen        decimal(16,2) comment '底吹氩总量',
    private BigDecimal consumeOxygen;

    //    exitStation          datetime comment '出站时刻',
    private Timestamp exitStation;

    //    exitWeigh            decimal(16,2) comment '出站钢水重量',
    private BigDecimal exitWeigh;

    //    consumeSiFe          decimal(16,2) comment '硅铁加入量',
    private BigDecimal consumeSiFe;

    //    consumeSiMn          decimal(16,2) comment '硅锰合金加入量',
    private BigDecimal consumeSiMn;

    //    consumeMnFe          decimal(16,2) comment '锰铁加入量',
    private BigDecimal consumeMnFe;

    //    consumeAlFe          decimal(16,2) comment '铝铁加入量',
    private BigDecimal consumeAlFe;

    //    consumeLime          decimal(16,2) comment '石灰加入量',
    private BigDecimal consumeLime;

    //    consumeRefineSlag    decimal(16,2) comment '精炼渣加入量',
    private BigDecimal consumeRefineSlag;

    //    consumeOther         decimal(16,2) comment '其他合金加入量',
    private BigDecimal consumeOther;

    //    consumeCaLine        decimal(16,2) comment '钙线加入量',
    private BigDecimal consumeCaLine;

}
