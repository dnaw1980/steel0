package com.rss.steel_production.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "td_channel")
public class TdChannel {

    /*
   ch_sn                int not null auto_increment comment '通道SN',
   sta_id               varchar(36) comment '工位ID',
   ch_name              varchar(64) not null comment '通道名称，在页面上显示的名称',
   com_tag              varchar(64) not null comment '通讯标识 real_data 中 dat_val 的 key',
   dk_cls               int not null comment '分类0-开关量;1-模拟量;2-累计量;3-时间',
   sw_0_stat            varchar(16) not null comment '开关量0意义',
   sw_1_stat            varchar(16) not null comment '开关量1意义',
   is_important         int not null default 0 comment '是否重要数据,0-否,1-是',
   order_sn             int not null comment '显示序号',
   input_com_tag        varchar(64) comment '输入通道标识',
   input_chk_val        decimal(24,6) comment '当变化到当前值的时候，要记录当前时间，目前只支持时间类型',
   dat_dt               datetime not null comment '数据时间',
   dat_val              decimal(24,6) not null comment '数据值',
     */

    /**
     * 通道SN
     */
    @Id
    @Column(name = "ch_sn")
    private Integer chSn;

    /**
     * 工位ID
     */
    @Column(name = "sta_id")
    private String staId;

    /**
     * 通道名称，在页面上显示的名称
     */
    @Column(name = "ch_name")
    private String chName;

    /**
     * 通讯标识 real_data 中 dat_val 的 key
     */
    @Column(name = "com_tag")
    private String comTag;

    /**
     * 分类0-开关量;1-模拟量;2-累计量;3-时间
     */
    @Column(name = "dk_cls")
    private Integer dkCls;

    /**
     * 开关量0意义
     */
    @Column(name = "sw_0_stat")
    private String sw0Stat;

    /**
     * 开关量1意义
     */
    @Column(name = "sw_1_stat")
    private String sw1Stat;

    /**
     * 是否重要数据,0-否,1-是
     */
    @Column(name = "is_important")
    private Integer isImportant;

    /**
     * 显示序号
     */
    @Column(name = "order_sn")
    private Integer orderSn;

    /**
     * 输入通道标识
     */
    @Column(name = "input_com_tag")
    private String inputComTag;

    /**
     * 当变化到当前值的时候，要记录当前时间，目前只支持时间类型
     */
    @Column(name = "input_chk_val")
    private BigDecimal inputChkVal;

    /**
     * 数据时间
     */
    @Column(name = "dat_dt")
    private java.sql.Timestamp datDt;

    /**
     * 数据值
     */
    @Column(name = "dat_val")
    private BigDecimal datVal;
}

