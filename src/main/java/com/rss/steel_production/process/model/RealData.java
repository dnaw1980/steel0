package com.rss.steel_production.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "real_data")
public class RealData {
    @Id
    @Column( name = "dat_kind_id")
    private String datKindId;

    @Column( name = "dat_val")
    private String datVal;

    @Column( name = "dat_tm")
    private java.sql.Timestamp datTm;

    /**
     * 新数据标识
     */
    @Column(name = "chk_tag")
    private Integer chkTag;
}
