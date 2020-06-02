package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 工艺路径
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dp_tech_route")
public class DpTechRoute {
    /*
    create table dp_tech_route
(
   tech_card_id         varchar(36) not null comment '工艺卡ID',
   work_proc_id         varchar(36) not null comment '工序ID',
   order_sn             int not null default 1 comment '工艺序号，指这一道工序在整个产品工艺中的顺序位置',
   primary key (tech_card_id, work_proc_id)
);
     */


    //tech_card_id         varchar(36) not null comment '工艺卡ID',
    @Id
    @Column(name = "tech_card_id")
    private String techCardId;

    //work_proc_id         varchar(36) not null comment '工序ID',
    @Column(name = "work_proc_id")
    private String workProcId;

    //order_sn             int not null default 1 comment '工艺序号，指这一道工序在整个产品工艺中的顺序位置',
    @Column(name = "order_sn")
    private Integer orderSn;


    private DpWorkProc workProc;

}