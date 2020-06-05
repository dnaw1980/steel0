package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 工序信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dp_work_proc")
public class DpWorkProc {
    /*
    create table pd_work_proc
(
   work_proc_id         varchar(36) not null comment '工序ID',
   work_proc_nm         varchar(24) not null comment '工序名称',
   work_proc_code       varchar(24) not null comment '工序代码',
   work_cycle           int not null comment '标准作业时间（分钟）',
   primary key (work_proc_id)
);

alter table pd_work_proc comment '工序信息';
     */

    /**
     * work_proc_id         varchar(36) not null comment '工序ID',
     */
    @Id
    @Column(name = "work_proc_id")
    private String workProcId;

    /**
     * work_proc_nm         varchar(24) not null comment '工序名称',
     */
    @Column(name = "work_proc_nm")
    private String workProcNm;

    /**
     * work_proc_code       varchar(24) not null comment '工序代码',
     */
    @Column(name = "work_proc_code")
    private String workProcCode;

    /**
     * work_cycle           int not null comment '标准作业时间（分钟）',
     */
    @Column(name = "work_cycle")
    private Integer workCycle;

}
