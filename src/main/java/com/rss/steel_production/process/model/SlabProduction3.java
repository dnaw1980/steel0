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
@Table(name = "slab_production3")
public class SlabProduction3 {
    @Id
    private String slab_production3UID;

    private String chargeNo;

    private String temperature;

    private String weight;

    private Date enterRotating;

    private Date startCast;

    private String castNo;

    private String baleTemperature;

    private String baleWeighA;

    private String baleWeighB;

    private String tundishWeigh;

    private String chargeCount;

    private String tundishQuick;

    private String tundishContinuous;

    private String drawingActual1;

    private String drawingSet1;

    private String pouringLength1;

    private String drawingActual2;

    private String drawingSet2;

    private String pouringLength2;

    private Date acquireTime;

}