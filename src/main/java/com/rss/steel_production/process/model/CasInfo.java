package com.rss.steel_production.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cas_info")
public class CasInfo {
    @Id
    private String cas_infoUID;

    private String chargeNo;

    private String stationNo;

    private Date enterStation;

    private Integer oxygenTotal;

    private Integer bottomBlow;

    private String oxygenConsume;

    private String argonConsume;

    private String materialConsume;

    private Date exitStation;

    private String temperature;

    private String weight;

    private Date acquireTime;

}