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
@Table(name = "dp_schedule_detail")
public class DpScheduleDetail {

    /**
     * schedule_detail_sn   int not null auto_increment comment '调度明细SN',
     */
    @Id
    @Column(name = "schedule_detail_sn")
    private Integer scheduleDetailSn;

    /**
     * schedule_seq_id      varchar(36) not null comment '调度序列ID',
     */
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

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
    @Column(name = "state")
    private Integer state;

    /**
     * -1作废
     */
    public static final int STATE_FAIL = -1;

    /**
     * 0-计划
     */
    public static final int STATE_PLAN = 0;

    /**
     * 1-下达
     */
    public static final int STATE_SEND = 1;

    /**
     * 2-执行
     */
    public static final int STATE_EXEC = 2;

    /**
     * 3-完成
     */
    public static final int STATE_FINISH = 3;

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
}
