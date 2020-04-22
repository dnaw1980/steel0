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
@Table(name = "lf_info")
public class LfInfo {
    @Id
    private String lf_infoUID;

    private String chargeNo;

    private String stationNo;

    private Date enterStation;

    private String powerConsume;

    private Date startLF;

    private Date endLF;

    private String voltageA;

    private String voltageB;

    private String voltageC;

    private String electricityA;

    private String electricityB;

    private String electricityC;

    private Integer heatTime;

    private Integer smeltTime;

    private Date refine1;

    private Date refine2;

    private Date crane1;

    private Date crane2;

    private Date startArgon11;

    private Date startArgon12;

    private Date startArgon21;

    private Date startArgon22;

    private Integer timeArgon11;

    private Integer timeArgon12;

    private Integer timeArgon21;

    private Integer timeArgon22;

    private String blowArgon11;

    private String blowArgon12;

    private String blowArgon21;

    private String blowArgon22;

    private String oxygenConsume;

    private String materialConsume;

    private Date exitStation;

    private String temperature;

    private String weight;

    private Date acquireTime;

}