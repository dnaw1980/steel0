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

    public String getIron_infoUID() {
        return iron_infoUID;
    }

    public void setIron_infoUID(String iron_infoUID) {
        this.iron_infoUID = iron_infoUID;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public String getBlastOrder() {
        return blastOrder;
    }

    public void setBlastOrder(String blastOrder) {
        this.blastOrder = blastOrder;
    }

    public String getBlastNo() {
        return blastNo;
    }

    public void setBlastNo(String blastNo) {
        this.blastNo = blastNo;
    }

    public String getLadleNumber() {
        return ladleNumber;
    }

    public void setLadleNumber(String ladleNumber) {
        this.ladleNumber = ladleNumber;
    }

    public String getScrabNet() {
        return scrabNet;
    }

    public void setScrabNet(String scrabNet) {
        this.scrabNet = scrabNet;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public Date getOutIronTime() {
        return outIronTime;
    }

    public void setOutIronTime(Date outIronTime) {
        this.outIronTime = outIronTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getExitTemperature() {
        return exitTemperature;
    }

    public void setExitTemperature(String exitTemperature) {
        this.exitTemperature = exitTemperature;
    }

    public Date getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(Date acquireTime) {
        this.acquireTime = acquireTime;
    }
}
