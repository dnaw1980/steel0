package com.rss.steel_production.workProcedure.controller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * 调度序列，<br/>
 * 指一包铁水从铁水接受到进入连铸机进行浇铸这一个流程的信息，要包含高炉炉号，转炉炉号。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class changeScheduleSeqNoBean {

    /**
     * schedule_seq_id      varchar(36) not null comment '调度序列ID',
     */
    private String scheduleSeqId;

    /**
     * 高炉炉号
     */
    private String blastNo;

    /**
     * '转炉炉号',
     */
    private String chargeNo;

}
