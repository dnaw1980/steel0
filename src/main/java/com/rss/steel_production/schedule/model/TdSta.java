package com.rss.steel_production.schedule.model;

import com.rss.steel_production.workProcedure.model.DpWorkProc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "td_sta")
public class TdSta {

    /*
    SELECT
    `td_sta`.`sta_id`,
    `td_sta`.`sta_name`,
    `td_sta`.`real_data_key`,
    `td_sta`.`schedule_station`,
    `td_sta`.`chk_tag`,


   sta_id               varchar(36) not null comment '工位ID',
   work_proc_id         varchar(36) comment '工序ID',
   sta_name             varchar(64) not null comment '工位名称',
   sta_no               int not null comment '工位序号',
   state                int not null default 0 comment '状态，0-暂停，1-生产',
   real_data_key        varchar(128) not null comment '对应采集主键, 表 real_data 中 dat_kind_id 中的值',
   schedule_station     varchar(32) not null comment '对应调度标识 steel_schedule 表中的 stationName',
   chk_tag              int not null default -1 comment '新数据标识，对应 real_data 中的 chk_tag',
   dat_dt               datetime not null default CURRENT_TIMESTAMP comment '数据时间',
     */

    /**
     * 工位ID
     */
    @Id
    @Column(name = "sta_id")
    private String staId;

    @Column(name = "work_proc_id")
    private String workProcId;

    /**
     * 工位名称
     */
    @Column(name = "sta_name")
    private String staName;

    /**
     * 工位序号
     */
    @Column(name = "sta_no")
    private Integer staNo;

    /**
     * 状态，0-暂停，1-生产
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 1-生产
     */
    public static final int STATE_ON = 1;

    /**
     * 0-暂停
     */
    public static final int STATE_OFF = 0;

    /**
     * 对应采集主键, 表 real_data 中 dat_kind_id 中的值
     */
    @Column(name = "real_data_key")
    private String realDataKey;

    /**
     * 对应调度标识 steel_schedule 表中的 stationName
     */
    @Column(name = "schedule_station")
    private String scheduleStation;

    /**
     * 新数据标识，对应 real_data 中的 chk_tag
     */
    @Column(name = "chk_tag")
    private Integer chkTag;

    /**
     * 数据时间
     */
    @Column(name = "dat_dt")
    private java.sql.Timestamp datDt;

    /**
     * 通道列表，包含实时数据
     */
    private List<TdChannel> channelList;

    private DpWorkProc workProc;
}

