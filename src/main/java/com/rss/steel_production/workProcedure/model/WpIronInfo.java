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
    //    iron_info_sn         int not null auto_increment,
    @Id
    @Column(name = "iron_info_sn")
    private Integer ironInfoSn;

    //    schedule_seq_id      varchar(36) comment '调度序列ID',
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //    blastOrder           varchar(32) comment '铁次编号',
    private String blastOrder;

    //    blastNo              varchar(32) comment '高炉编号',
    private String blastNo;

    //    ladleNo              varchar(32) comment '铁包号',
    private String ladleNo;

    //    scrabNet             decimal(16,2) comment '废钢净量',
    private BigDecimal scrabNet;

    //    netWeight            decimal(16,2) comment '铁水净重',
    private BigDecimal netWeight;

    //    arriveTime           datetime comment '到达时刻',
    private Timestamp arriveTime;

}
