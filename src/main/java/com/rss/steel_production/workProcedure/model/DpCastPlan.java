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
   begin_dt             datetime comment '开浇时间',
   end_dt               datetime comment '完成时间',
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

    /**
     * 状态，0-计划
     */
    public static final int STATE_PLAN = 0;

    /**
     * 状态，1-下达，
     */
    public static final int STATE_SEND = 1;

    /**
     * 状态，2-执行，
     */
    public static final int STATE_EXEC = 2;

    /**
     * 状态，3-完成，
     */
    public static final int STATE_FINISH = 3;

    //begin_dt             datetime comment '开浇时间',
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "begin_dt")
    private Timestamp beginDt;

    //end_dt               datetime comment '完成时间',
    @Column(name = "end_dt")
    private Timestamp endDt;

    /**
     * 对应工艺卡
     */
    private DpTechCard dpTechCard;

    /**
     * 对应工位
     */
    private TdSta tdSta;

}
