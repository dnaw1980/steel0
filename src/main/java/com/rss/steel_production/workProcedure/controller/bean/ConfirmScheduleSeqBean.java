package com.rss.steel_production.workProcedure.controller.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 调度序列，<br/>
 * 指一包铁水从铁水接受到进入连铸机进行浇铸这一个流程的信息，要包含高炉炉号，转炉炉号。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmScheduleSeqBean {

    /**
     * schedule_seq_id      varchar(36) not null comment '调度序列ID',
     */
    private String scheduleSeqId;

    /**
     * state  '状态，-1作废，0-计划，1-下达，2-执行，3-完成',<br/>
     * 实际上只有1和-1有效
     */
    private Integer state;

}
