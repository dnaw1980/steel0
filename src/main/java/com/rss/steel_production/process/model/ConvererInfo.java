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
@Table(name = "converer_info")
public class ConvererInfo {
    @Id
    private String converer_infoUID;

    private String chargeNo;

    private String stationNo;

    private Date ironMolt;

    private Date blowStart;

    private Date blowEnd;

    private Date softStart;

    private Date softEnd;

    private Integer smeltCycle;

    private Date slagSplash;

    private Date steelStart;

    private String materialConsumes;

    private String oxygenBlow;

    private String nitrogenBlow;

    private String nitrogenBottom;

    private String argonBottom;

    private Date steelEnd;

    private String temperature;

    private String weight;

    private Date acquireTime;

}