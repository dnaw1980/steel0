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
 * 转炉工序
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_converer_info")
public class WpConvererInfo {

    //    converer_info_sn     int not null auto_increment comment '炼钢信息序号',
    @Id
    @Column(name = "converer_info_sn")
    private Integer convererInfoSn;

    //    schedule_seq_id      varchar(36) comment '调度序列ID',
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //    enterTemperature     int comment '进站铁水温度',
    private Integer enterTemperature;

    //    enterWeigh           decimal(16,2) comment '铁水装入量',
    private BigDecimal enterWeigh;

    //    scrabWeigh           decimal(16,2) comment '废钢装入量',
    private BigDecimal scrabWeigh;

    //    ironMolt             datetime comment '兑铁水开始时刻',
    private Timestamp ironMolt;

    //    blowStart            datetime comment '吹炼开始时刻',
    private Timestamp blowStart;

    //    blowEnd              datetime comment '吹炼结束时刻',
    private Timestamp blowEnd;

    //    oxygenBlow           decimal(16,2) comment '吹炼用氧气量',
    private BigDecimal oxygenBlow;

    //    nitrogenBottom       decimal(16,2) comment '底吹氮气量',
    private BigDecimal nitrogenBottom;

    //    argonBottom          decimal(16,2) comment '底吹氩气用量',
    private BigDecimal argonBottom;

    //    slagSplash           datetime comment '溅渣时刻',
    private Timestamp slagSplash;

    //    steelStart           datetime comment '出钢时刻',
    private Timestamp steelStart;

    //    exitTemperature      int comment '终点温度',
    private Integer exitTemperature;

    //    exitWeigh            decimal(16,2) comment '出钢重量',
    private BigDecimal exitWeigh;

    //    steelEnd             datetime comment '出站时刻',
    private Timestamp steelEnd;

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

    //    consumePremeltSlag   decimal(16,2) comment '预熔渣加入量',
    private BigDecimal consumePremeltSlag;

    //    consumeAl40          decimal(16,2) comment 'Al40加入量',
    private BigDecimal consumeAl40;

    //    consumeOther         decimal(16,2) comment '其他合金加入量',
    private BigDecimal consumeOther;

}
