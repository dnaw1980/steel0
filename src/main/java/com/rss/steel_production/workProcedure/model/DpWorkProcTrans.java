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
@Table(name = "dp_work_proc_trans")
public class DpWorkProcTrans {

    /**
     * src_work_proc_id     varchar(36) not null comment '源工序ID',
     */
    @Id
    @Column(name = "src_work_proc_id")
    private String srcWorkProcId;

    /**
     * desc_work_proc_id    varchar(36) not null comment '目标工序ID',
     */
//    @Id
    @Column(name = "desc_work_proc_id")
    private String descWorkProcId;

    /**
     * trans_cycle          int not null default 15 comment '运输时间（分钟）',
     */
    @Column(name = "trans_cycle")
    private Integer transCycle;

}
