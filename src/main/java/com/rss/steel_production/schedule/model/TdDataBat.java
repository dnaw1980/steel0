package com.rss.steel_production.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "td_data_bat")
public class TdDataBat {

    /*
    create table td_data_bat (
       bat_sn               bigint not null auto_increment comment '批次序号',
       sta_id               varchar(36) comment '工位ID',
       dat_dt               datetime not null comment '数据时间',
       primary key (bat_sn)
    );
     */

    /**
     * 批次序号
     */
    @Id
    @Column(name = "bat_sn")
    private Long batSn;

    /**
     * 工位ID
     */
    @Column(name = "sta_id")
    private String staId;

    /**
     * 数据时间
     */
    @Column(name = "dat_dt")
    private java.sql.Timestamp datDt;
}

