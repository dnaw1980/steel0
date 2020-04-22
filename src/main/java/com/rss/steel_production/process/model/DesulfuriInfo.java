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
@Table(name = "desulfuri_info")
public class DesulfuriInfo {
    @Id
    private String desulfuri_infoUID;

    private String chargeNo;

    private String stationNo;

    private Date startProcess;

    private Date startDesulfuri;

    private Date startStir;

    private Date starTtippler;

    private String liquidMeasures;

    private String nitrogenFlow;

    private String nitrogenPressure;

    private String stirHeadSpeed;

    private String materialWeight;

    private Date startFeed;

    private Date exitDesulfuri;

    private Date exitStir;

    private Date exitTtippler;

    private Date exitFeed;

    private Date exitProcess;

    private String temperature;

    private String weight;

    private Date acquireTime;

}