package com.rss.steel_production.schedule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "td_hist_data")
public class TdHistData {

    /*
    create table td_hist_data (
       dat_sn               bigint not null auto_increment comment '历史数据SN',
       ch_sn                int comment '通道SN',
       bat_sn               bigint comment '历史数据SN',
       dat_val              decimal(24,6) not null comment '数据值',
       primary key (dat_sn)
    );
     */

    /**
     * 历史数据SN
     */
    @Id
    @Column(name = "dat_sn")
    private Long datSn;

    /**
     * 通道SN
     */
    @Column(name = "ch_sn")
    private Integer chSn;

    /**
     * 批次序号
     */
    @Column(name = "bat_sn")
    private Long batSn;

    /**
     * 数据值
     */
    @Column(name = "dat_val")
    private BigDecimal datVal;

}

