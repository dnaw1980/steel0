package com.rss.steel_production.workProcedure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 浇次计划
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dp_cast_plan")
public class DpCastPlan {

    /*
    create table dp_cast_plan
(
   cast_plan_id         varchar(36) not null comment '浇次计划ID',
   tech_card_id         varchar(36) not null comment '工艺卡ID',
   sta_id               varchar(36) comment '工位ID',
   cast_no              varchar(36) not null comment '浇次号',
   state                tinyint not null default 0 comment '状态，0-计划，1-下达，2-执行，3-完成',
);

     */

    //cast_plan_id         varchar(36) not null comment '浇次计划ID',
    @Id
    @Column(name = "cast_plan_id")
    private String castPlanId;

    //tech_card_id         varchar(36) not null comment '工艺卡ID',
    @Column(name = "tech_card_id")
    private String techCardId;

    //sta_id               varchar(36) comment '工位ID',
    @Column(name = "sta_id")
    private String staId;

    //cast_no              varchar(36) not null comment '浇次号',
    @Column(name = "cast_no")
    private String castNo;

    //state                int not null default 0 comment '状态，0-计划，1-下达，2-执行，3-完成',
    @Column(name = "state")
    private Integer state;
}
