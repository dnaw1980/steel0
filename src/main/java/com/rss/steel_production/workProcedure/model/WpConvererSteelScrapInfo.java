package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wp_converer_steel_scrap_info")
public class WpConvererSteelScrapInfo {

    /**
     * steel_scrap_info_sn  int not null comment '转炉废钢信息序号',
     */
    @Id
    @Column(name = "steel_scrap_info_sn")
    private Integer steelScrapInfoSn;

    /**
     * converer_info_sn     int comment '炼钢信息序号',
     */
    @Column(name = "converer_info_sn")
    private Integer convererInfoSn;

    /**
     * schedule_seq_id      varchar(36) not null comment '调度序列ID',
     */
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    /**
     * blast_no           varchar(64) comment '高炉炉号',
     */
//    private String blastNo;

    /**
     * charge_no  varchar(64) comment '转炉炉号',
     */
//    private String chargeNo;

    /**
     * scrabWeigh  decimal(16,2) comment '废钢装入量',
     */
    private BigDecimal scrabWeigh;

    /**
     * tankCarNo            varchar(36) comment '槽车编号',
     */
    private String tankCarNo;

    /**
     * carTrackNo           varchar(36) comment '车道编号',
     */
    private String carTrackNo;

    /**
     * tankNo               varchar(36) comment '废钢槽号',
     */
    private String tankNo;

    /**
     * tankTareWeight       decimal(16,2) comment '废钢槽皮重',
     */
    private BigDecimal tankTareWeight;

    /**
     * tankGrossWeight      decimal(16,2) comment '废钢槽毛重',
     */
    private BigDecimal tankGrossWeight;

    /**
     * tankSteelRegTm       datetime comment '废钢登记时刻',
     */
    private Timestamp tankSteelRegTm;
}
