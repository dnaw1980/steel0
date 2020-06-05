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
 * 铁水信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_iron_info")
public class WpIronInfo {
    /*

   track                varchar(32) comment '轨道',
   carNo                varchar(32) comment '车号',
   ladleNo              varchar(32) comment '铁包号',
   tareWeight           decimal(16,2) comment '皮重',
   grossWeight          decimal(16,2) comment '毛重',
   scrabNet             decimal(16,2) comment '废钢净量',
   netWeight            decimal(16,2) comment '铁水净重',
   arriveTime           datetime comment '到达时刻',
   registerTime         datetime comment '登记时刻',
     */
    //    iron_info_sn         int not null auto_increment,
    @Id
    @Column(name = "iron_info_sn")
    private Integer ironInfoSn;

    /**
     * schedule_seq_id      varchar(36) comment '调度序列ID',
     *
     * @return
     */

    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    /**
     * blastOrder           varchar(32) comment '铁次编号',
     */
    private String blastOrder;

    /**
     * blastNo              varchar(32) comment '高炉编号',
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
     * ladleNo              varchar(32) comment '铁包号',
     */
    private String ladleNo;

    /**
     * tareWeight           decimal(16,2) comment '皮重',
     */
    private BigDecimal tareWeight;

    /**
     * grossWeight          decimal(16,2) comment '毛重',
     */
    private BigDecimal grossWeight;

    /**
     * scrabNet             decimal(16,2) comment '废钢净量',
     */
    private BigDecimal scrabNet;

    /**
     * netWeight            decimal(16,2) comment '铁水净重',
     */
    private BigDecimal netWeight;

    /**
     * arriveTime           datetime comment '到达时刻',
     */
    private Timestamp arriveTime;

    /**
     * registerTime         datetime comment '登记时刻',
     */
    private Timestamp registerTime;

}
