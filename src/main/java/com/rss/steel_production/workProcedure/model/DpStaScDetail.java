package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 调度序列，<br/>
 * 指一包铁水从铁水接受到进入连铸机进行浇铸这一个流程的信息，要包含高炉炉号，转炉炉号。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sta_sc_detail")
public class DpStaScDetail {

    /**
     * schedule_detail_sn   int not null auto_increment comment '调度明细SN',
     */
    @Column(name = "schedule_detail_sn")
    private Integer scheduleDetailSn;

    /**
     * schedule_seq_id      varchar(36) not null comment '调度序列ID',
     */
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    /**
     * 高炉炉次
     */
    @Column(name = "blast_no")
    private String blastNo;

    /**
     * 转炉炉次
     */
    @Column(name = "charge_no")
    private String chargeNo;

    /**
     * sta_id               varchar(36) comment '工位ID',
     */
    @Column(name = "sta_id")
    private String staId;

    /**
     * order_sn             int not null default 1 comment '调度顺序',
     */
    @Column(name = "order_sn")
    private Integer orderSn;


    /**
     * state  tinyint not null default 0 comment '状态，-1作废，0-计划，1-下达，2-执行，3-完成',
     */
    @Column(name = "detail_state")
    private Integer detailState;

    /**
     * plan_begin           datetime,
     */
    @Column(name = "plan_begin")
    private Timestamp planBegin;

    /**
     * plan_end             datetime,
     */
    @Column(name = "plan_end")
    private Timestamp planEnd;

    /**
     * act_begin            datetime,
     */
    @Column(name = "act_begin")
    private Timestamp actBegin;

    /**
     * act_end              datetime,
     */
    @Column(name = "act_end")
    private Timestamp actEnd;

    /**
     * wp_sn                int comment '工序信息序号，对应八个工序信息表中其中某一个表中的一条记录。',
     */
    @Column(name = "wp_sn")
    private Integer wpSn;


    @Column(name = "work_proc_id")
    private String workProcId;

    /**
     * 工位名称
     */
    @Column(name = "sta_name")
    private String staName;

    /**
     * 工位序号
     */
    @Column(name = "sta_no")
    private Integer staNo;

    /**
     * 状态，0-暂停，1-生产
     */
    @Column(name = "sta_state")
    private Integer staState;

    /**
     * 对应采集主键, 表 real_data 中 dat_kind_id 中的值
     */
    @Column(name = "real_data_key")
    private String realDataKey;

    /**
     * 对应调度标识 steel_schedule 表中的 stationName
     */
    @Column(name = "schedule_station")
    private String scheduleStation;

    /**
     * 新数据标识，对应 real_data 中的 chk_tag
     */
    @Column(name = "chk_tag")
    private Integer chkTag;

    /**
     * 数据时间
     */
    @Column(name = "dat_dt")
    private java.sql.Timestamp datDt;

}
