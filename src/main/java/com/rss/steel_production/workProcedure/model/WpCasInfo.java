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
 * CAS工序
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_cas_info")
public class WpCasInfo {

    //    cas_info_sn          int not null auto_increment comment 'CAS精炼信息序号',
    @Id
    @Column(name = "cas_info_sn")
    private Integer casInfoSn;

    //    schedule_seq_id      varchar(36) comment '调度序列ID',
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //    enterStationTime     datetime comment '进站时刻',
    private Timestamp enterStationTime;

    //    exitStationTime      datetime comment '出站时刻',
    private Timestamp exitStationTime;

    //    enterTemperature     int comment '进站温度',
    private Integer enterTemperature;

    //    exitTemperature      int comment '出站温度',
    private Integer exitTemperature;

    //    enterWeigh           decimal(16,2) comment '进炉钢水重量',
    private BigDecimal enterWeigh;

    //    exitWeigh            decimal(16,2) comment '出炉钢水重量',
    private BigDecimal exitWeigh;

    //    oxygenTotal          int comment '吹氧总时间',
    private Integer oxygenTotal;

    //    bottomBlow           int comment '底吹氩总时间',
    private Integer bottomBlow;

    //    consumeOxygen        decimal(16,2) comment '吹氧含量',
    private BigDecimal consumeOxygen;

    //    nitrogenPress        decimal(16,2) comment '底吹氩平均流量',
    private BigDecimal nitrogenPress;

    //    consumeSiFe          decimal(16,2) comment '硅铁加入量',
    private BigDecimal consumeSiFe;

    //    consumeSiMn          decimal(16,2) comment '硅锰合金加入量',
    private BigDecimal consumeSiMn;

    //    consumeMnFe          decimal(16,2) comment '锰铁加入量',
    private BigDecimal consumeMnFe;

    //    consumeOther         decimal(16,2) comment '其他合金加入量',
    private BigDecimal consumeOther;

}
