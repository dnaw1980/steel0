package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 调度序列，<br/>
 * 指一包铁水从铁水接受到进入连铸机进行浇铸这一个流程的信息，要包含高炉炉号，转炉炉号。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dp_schedule_seq")
public class DpScheduleSeq {
    /*
    create table dp_schedule_seq
(
   schedule_seq_id      varchar(36) not null comment '调度序列ID',
   cast_plan_id         varchar(36) not null,
   furnace_no           varchar(64) comment '高炉炉号',
   charge_no            varchar(64) comment '转炉炉号',
   primary key (schedule_seq_id)
);
     */

    //schedule_seq_id      varchar(36) not null comment '调度序列ID',
    @Id
    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    //cast_plan_id         varchar(36) not null,
    @Column(name = "cast_plan_id")
    private String castPlanId;

    //furnace_no           varchar(64) comment '高炉炉号',
    @Column(name = "furnace_no")
    private String furnaceNo;

    //charge_no            varchar(64) comment '转炉炉号',
    @Column(name = "charge_no")
    private String chargeNo;
}
