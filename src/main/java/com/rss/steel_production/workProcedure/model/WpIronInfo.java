package com.rss.steel_production.workProcedure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 铁水信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_iron_info")
public class WpIronInfo extends WpBase {
    /*

   iron_info_sn         int not null auto_increment,
   schedule_seq_id      varchar(36) comment '调度序列ID',
   sta_id               varchar(36) comment '工位ID',
   blastNo              varchar(32) comment '高炉炉次',
   track                varchar(32) comment '轨道',
   carNo                varchar(32) comment '车号',
   ladleNo              varchar(32) comment '罐号',
   tareWeight           decimal(16,2) comment '皮重',
   scrabNet             decimal(16,2) comment '废钢净重',
   scrabGrossWeight     decimal(16,2) comment '废钢毛重，为废钢净重与皮重的和',
   netWeight            decimal(16,2) comment '铁水净重，为铁水毛重减去皮重',
   grossWeight          decimal(16,2) comment '铁水毛重含废钢',
   arriveTime           datetime comment '到达时刻',
   "desc"               int comment '去向0-未定，1-炼钢，2-铸铁',
   registerTime         datetime comment '登记时刻',
     */
    //    iron_info_sn         int not null auto_increment,
    @Id
    @Column(name = "iron_info_sn")
    private Integer ironInfoSn;

    /**
     * sta_id               varchar(36) comment '工位ID',
     */
    @Column(name = "sta_id")
    private String staId;

    /**
     * schedule_seq_id      varchar(36) comment '调度序列ID',
     *
     * @return
     */

    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    /**
     * blastNo              varchar(32) comment '高炉炉次',
     */
    private String blastNo;

    /**
     * track                varchar(32) comment '轨道',
     */
    private String track;

    /**
     * carNo                varchar(32) comment '车号',
     */
    private String carNo;


    /**
     * ladleNo              varchar(32) comment '罐号',
     */
    private String ladleNo;

    /**
     * tareWeight           decimal(16,2) comment '皮重',
     */
    private BigDecimal tareWeight;

    /**
     * scrabNet             decimal(16,2) comment '废钢净重',
     */
    private BigDecimal scrabNet;

    /**
     * scrabGrossWeight     decimal(16,2) comment '废钢毛重，为废钢净重与皮重的和',
     */
    private BigDecimal scrabGrossWeight;

    /**
     * netWeight            decimal(16,2) comment '铁水净重，为铁水毛重减去皮重',
     */
    private BigDecimal netWeight;

    /**
     * grossWeight          decimal(16,2) comment '铁水毛重含废钢',
     */
    private BigDecimal grossWeight;

    /**
     * arriveTime           datetime comment '到达时刻',
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp arriveTime;

    /**
     * "direction"               int comment '去向0-未定，1-炼钢，2-铸铁',
     */
    private Integer direction = DESC_NONE;

    /**
     * 去向0-未定
     */
    public final static int DESC_NONE = 0;

    /**
     * 去向1-炼钢
     */
    public final static int DESC_STEEL = 1;

    /**
     * 去向2-铸铁
     */
    public final static int DESC_IRON = 2;


    /**
     * registerTime         datetime comment '登记时刻',
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp registerTime;

}
