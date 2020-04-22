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
@Table(name = "billet_production")
public class BilletProduction {
    @Id
    private String billet_productionUID;

    private String chargeNo;

    private String temperature;

    private String weight;

    private Date enterRotating;

    private Date startCast;

    private String castNo;

    private String baleTemperature;

    private String baleWeigh1;

    private String baleWeigh2;

    private String tundishTemperature1;

    private String tundishContinuous1;

    private String tundishTemperature2;

    private String tundishContinuous2;

    private String drawingSpeed01;

    private Integer pouringState01;

    private String drawingSpeed02;

    private Integer pouringState02;

    private String drawingSpeed03;

    private Integer pouringState03;

    private String drawingSpeed04;

    private Integer pouringState04;

    private String drawingSpeed05;

    private Integer pouringState05;

    private String drawingSpeed06;

    private Integer pouringState06;

    private String drawingSpeed07;

    private Integer pouringState07;

    private String drawingSpeed08;

    private Integer pouringState08;

    private String drawingSpeed09;

    private Integer pouringState09;

    private String drawingSpeed10;

    private Integer pouringState10;

    private Date acquireTime;

}