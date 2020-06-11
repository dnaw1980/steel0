package com.rss.steel_production.workProcedure.controller.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rss.steel_production.workProcedure.model.DpScheduleDetail;
import com.rss.steel_production.workProcedure.model.DpStaScDetail;
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
public class ScheduleSeqBean {

    /**
     * schedule_seq_id      varchar(36) not null comment '调度序列ID',
     */
    private String scheduleSeqId;

    /**
     * ast_plan_id         varchar(36) not null,
     */
    private String castPlanId;

    /**
     * blast_no           varchar(64) comment '高炉炉号',
     */
    private String blastNo;

    /**
     * charge_no  varchar(64) comment '转炉炉号',
     */
    private String chargeNo;

    /**
     * state  tinyint not null default 0 comment '状态，-1作废，0-计划，1-下达，2-执行，3-完成',
     */
    private Integer state;


    /**
     * 开始时间头（包含）
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp beginTmL;

    /**
     * 开始时间尾（不包含）
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp beginTmH;

    /**
     * 结束时间头（包含）
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTmL;

    /**
     * 结束时间尾（包含）
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTmH;


}
