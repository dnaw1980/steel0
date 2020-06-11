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
 * 浇次计划
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scrap_ref_schdule")
public class ScrapRefSchdule {

    @Id
    @Column(name = "schedule_detail_sn")
    private Integer scheduleDetailSn;

    @Column(name = "schedule_seq_id")
    private String scheduleSeqId;

    @Column(name = "cast_plan_id")
    private String castPlanId;

    @Column(name = "schedule_station")
    private String scheduleStation;

    @Column(name = "blast_no")
    private String blastNo;

    @Column(name = "charge_no")
    private String chargeNo;

    @Column(name = "state")
    private Integer state;

    @Column(name = "work_proc_id")
    private String workProcId;

    @Column(name = "steel_scrap_info_sn")
    private Integer steelScrapInfoSn;
}
