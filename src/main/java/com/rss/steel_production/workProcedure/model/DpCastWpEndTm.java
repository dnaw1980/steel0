package com.rss.steel_production.workProcedure.model;

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
@Table(name = "cast_wp_end_tm")
public class DpCastWpEndTm {


    /**
     * 浇次ID
     */
    @Column(name = "cast_plan_id")
    private String castPlanId;

    /**
     * 工序ID
     */
    @Column(name = "work_proc_id")
    private String workProcId;

    /**
     * 最后有效计划完成时间
     */
    @Column(name = "plan_end")
    private Timestamp planEnd;

    /**
     * 最后有效实际完成时间
     */
    @Column(name = "act_end")
    private Timestamp actEnd;

}
