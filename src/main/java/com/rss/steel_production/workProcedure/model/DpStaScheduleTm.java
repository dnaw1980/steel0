package com.rss.steel_production.workProcedure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rss.steel_production.schedule.model.TdSta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 每个工位的最早开始时间
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dp_sta_schedule_tm")
public class DpStaScheduleTm {

    /**
     * 工位ID
     */
    @Id
    @Column(name = "sta_id")
    private String staId;

    /**
     * 工位序号
     */
    @Column(name = "sta_no")
    private Integer staNo;

    /**
     * work_proc_id         varchar(36) not null comment '工序ID',
     */
    @Column(name = "work_proc_id")
    private String workProcId;

    @Column(name = "begin_tm")
    private Timestamp beginTm;

    @Column(name = "end_tm")
    private Timestamp endTm;

    @Column(name = "work_proc_code")
    private String workProcCode;

    @Column(name = "work_cycle")
    private Integer workCycle;


}
