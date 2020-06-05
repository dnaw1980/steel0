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
 * 板坯工序
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_slab_info")
public class WpSlabInfo extends WpBase {
    //    slab_info_sn         int not null,
    @Id
    @Column(name = "slab_info_sn")
    private Integer slabInfoSn;

    /**
     * sta_id               varchar(36) comment '工位ID',
     */
    @Column(name = "sta_id")
    private String staId;

    //    schedule_seq_id      varchar(36) comment '调度序列ID',
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //    baleNo               varchar(32) comment '大包包号',
    private String baleNo;

    //    tundishNo            varchar(32) comment '中间包号',
    private String tundishNo;

    //    castSeq              varchar(3) comment '浇内序号',
    private String castSeq;

    //    baleArriveTime       datetime comment '大包到达时间',
    private Timestamp baleArriveTime;

    //    baleArriveWeight     decimal(16,2) comment '大包到达重量',
    private BigDecimal baleArriveWeight;

    //    baleArriveTemperature decimal(16,2) comment '大包到达温度',
    private BigDecimal baleArriveTemperature;

    //    baleLeaveTime        datetime comment '大包离开时间',
    private Timestamp baleLeaveTime;

    //    baleLeaveWeight      decimal(16,2) comment '大包离开重量',
    private BigDecimal baleLeaveWeight;

    //    tundishTemperature   text comment '中包连续测温',
    private String tundishTemperature;

    //    castStartTime        datetime comment '开浇时间',
    private Timestamp castStartTime;

    //    castStopTime         datetime comment '停浇时间',
    private Timestamp castStopTime;

    //    vibrateCurve         varchar(10) comment '振动模式',
    private String vibrateCurve;

    //    crystallizerColdCurve varchar(10) comment '结晶器冷却方式',
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

    //    baleSelfOpen         varchar(10) comment '大包自开',
    private String baleSelfOpen;

    //    langNozzleChangeTime datetime comment '长水口更换时间',
    private Timestamp langNozzleChangeTime;

    //    intrusiveNozzleChangeTime datetime comment '浸入式水口更换时间',
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

    //    crystallizerNo1      varchar(32) comment '一流结晶器号',
    private String crystallizerNo1;

    //    crystallizerNo2      varchar(32) comment '二流结晶器号',
    private String crystallizerNo2;

    //    billetHeadQty        decimal(16,2) comment '头坯量',
    private BigDecimal billetHeadQty;

    //    billetTailQty        decimal(16,2) comment '尾坯量',
    private BigDecimal billetTailQty;

    //    liquidusTemperature  decimal(16,2) comment '钢种液相线温度',
    private BigDecimal liquidusTemperature;

    //    superHeatDegree      decimal(16,2) comment '过热度',
    private BigDecimal superHeatDegree;

    //    qualityAccident      varchar(200) comment '质量异常事件',
    private String qualityAccident;

    //    readFlag             int comment '读取标志位',
    private Integer readFlag;
}
