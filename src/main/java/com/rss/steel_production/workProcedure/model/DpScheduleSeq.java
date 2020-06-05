package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 * 调度序列，<br/>
 * 指一包铁水从铁水接受到进入连铸机进行浇铸这一个流程的信息，要包含高炉炉号，转炉炉号。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dp_schedule_seq")
public class DpScheduleSeq {

    /**
     * schedule_seq_id      varchar(36) not null comment '调度序列ID',
     */
    @Id
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    /**
     * ast_plan_id         varchar(36) not null,
     */
    @Column(name = "cast_plan_id")
    private String castPlanId;

    /**
     * furnace_no           varchar(64) comment '高炉炉号',
     */
    @Column(name = "furnace_no")
    private String furnaceNo;

    /**
     * charge_no  varchar(64) comment '转炉炉号',
     */
    @Column(name = "charge_no")
    private String chargeNo;

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
     * begin_tm             datetime comment '开始时刻，即铁水的到达时刻',
     */
    @Column(name = "begin_tm")
    private Timestamp beginTm;

    /**
     * end_tm               datetime comment '完成时刻，进入连铸时刻',
     */
    @Column(name = "end_tm")
    private Timestamp endTm;

    /**
     * 调度明细
     */
    private List<DpScheduleDetail> detailList;
}
