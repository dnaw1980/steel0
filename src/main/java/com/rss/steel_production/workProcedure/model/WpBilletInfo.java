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
 * 方坯工序信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_billet_info")
public class WpBilletInfo {
    /**
     * billet_info_sn       int not null auto_increment comment '方坯信息序号',
     */
    @Id
    @Column(name = "billet_info_sn")
    private Integer billetInfoSn;

    /**
     * schedule_seq_id      varchar(36) comment '调度序列ID',
     */
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //    baleNo               varchar(32) comment '大包包号',
    private String baleNo;

    //    baleArriveTime       datetime comment '大包到达时刻',
    private Timestamp baleArriveTime;

    //    baleArriveWeight     decimal(16,2) comment '大包到达重量',
    private BigDecimal baleArriveWeight;

    //    baleArriveTemperature int comment '大包到达温度',
    private Integer baleArriveTemperature;

    //    baleLeaveTime        datetime comment '大包离开时刻',
    private Timestamp baleLeaveTime;

    //    baleLeaveWeight      decimal(16,2) comment '大包离开重量',
    private BigDecimal baleLeaveWeight;

    //    tundishTemperature1  int comment '中包第一次测温温度',
    private Integer tundishTemperature1;

    //    tundishTemperature2  int comment '中包第二次测温温度',
    private Integer tundishTemperature2;

    //    tundishTemperature3  int comment '中包第三次测温温度',
    private Integer tundishTemperature3;

    //    waterEnterTemperature int comment '进水温度',
    private Integer waterEnterTemperature;

    //    waterExitTemperature int comment '出水温度',
    private Integer waterExitTemperature;

    //    castStartTime        datetime comment '开浇时刻',
    private Timestamp castStartTime;

    //    castStopTime         datetime comment '停浇时刻',
    private Timestamp castStopTime;

    //    vibrateCurve         varchar(10) comment '振动曲线',
    private String vibrateCurve;

    //    crystallizerColdCurve varchar(10) comment '结晶器冷却曲线',
    private String crystallizerColdCurve;

    //    cold2Curve           varchar(10) comment '二冷曲线',
    private String cold2Curve;

    //    pullRateMax          decimal(16,2) comment '拉速最大',
    private BigDecimal pullRateMax;

    //    pullRateMin          decimal(16,2) comment '拉速最小',
    private BigDecimal pullRateMin;

    //    crystallizerLiquidLevelMax decimal(16,2) comment '结晶器液位最大',
    private BigDecimal crystallizerLiquidLevelMax;

    //    crystallizerLiquidLevelMin decimal(16,2) comment '结晶器液位最小',
    private BigDecimal crystallizerLiquidLevelMin;

    //    softPressUsed        varchar(10) comment '动态轻压下投入',
    private String softPressUsed;

    //    baleSelfOpen         varchar(10) comment '大包自开,Y/N',
    private String baleSelfOpen;

    //    intrusiveNozzleChangeTime datetime comment '浸入式水口更换时刻',
    private Timestamp intrusiveNozzleChangeTime;

    //    tundishSteelRemnant  decimal(16,2) comment '中包余钢重量',
    private BigDecimal tundishSteelRemnant;

    //    castSteelOver        varchar(10) comment '钢水是否浇完',
    private String castSteelOver;

    //    coveringAgentFactory varchar(32) comment '覆盖剂厂家',
    private String coveringAgentFactory;

    //    coveringAgentModel   varchar(32) comment '覆盖剂型号',
    private String coveringAgentModel;

    //    coveringAgentAddiitonQty decimal(16,2) comment '覆盖剂加入量',
    private BigDecimal coveringAgentAddiitonQty;

    //    castingPowderFactory varchar(32) comment '保护渣厂家',
    private String castingPowderFactory;

    //    castingPowderModel   varchar(32) comment '保护渣型号',
    private String castingPowderModel;

    //    castingPowderAddiitonQty decimal(16,2) comment '保护渣加入量',
    private BigDecimal castingPowderAddiitonQty;

    //    tundishHeatWay       varchar(32) comment '中间包烘烤方式',
    private String tundishHeatWay;

    //    tundishHeatTime      decimal(16,2) comment '中间包加热时间',
    private BigDecimal tundishHeatTime;

    //    tundishHeatGasPress  decimal(16,2) comment '中间包加热煤气压力',
    private BigDecimal tundishHeatGasPress;

    //    tundishCastState     varchar(32) comment '中间包浇注情况',
    private String tundishCastState;

    //    billetHeadQty        decimal(16,2) comment '头坯量',
    private BigDecimal billetHeadQty;

    //    billetTailQty        decimal(16,2) comment '尾坯量',
    private BigDecimal billetTailQty;

    //    deviceState          varchar(32) comment '连铸机工况',
    private String deviceState;

    //    liquidusTemperature  decimal(16,2) comment '钢种液相线温度',
    private BigDecimal liquidusTemperature;

    //    superHeatDegree      decimal(16,2) comment '过热度',
    private BigDecimal superHeatDegree;

    //    castBlankCount       int comment '每炉铸坯支数',
    private Integer castBlankCount;

}
