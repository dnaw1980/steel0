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
@Table(name = "iron_info")
public class IronInfo {
    @Id
    private String iron_infoUID;

    private String chargeNo;

    private String blastOrder;

    private String blastNo;

    private String ladleNumber;

    private String scrabNet;

    private String netWeight;

    private Date outIronTime;

    private Date stopTime;

    private Date startTime;

    private String exitTemperature;

    private Date acquireTime;
}