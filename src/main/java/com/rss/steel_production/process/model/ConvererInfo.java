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

    public String getConverer_infoUID() {
        return converer_infoUID;
    }

    public void setConverer_infoUID(String converer_infoUID) {
        this.converer_infoUID = converer_infoUID;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public Date getIronMolt() {
        return ironMolt;
    }

    public void setIronMolt(Date ironMolt) {
        this.ironMolt = ironMolt;
    }

    public Date getBlowStart() {
        return blowStart;
    }

    public void setBlowStart(Date blowStart) {
        this.blowStart = blowStart;
    }

    public Date getBlowEnd() {
        return blowEnd;
    }

    public void setBlowEnd(Date blowEnd) {
        this.blowEnd = blowEnd;
    }

    public Date getSoftStart() {
        return softStart;
    }

    public void setSoftStart(Date softStart) {
        this.softStart = softStart;
    }

    public Date getSoftEnd() {
        return softEnd;
    }

    public void setSoftEnd(Date softEnd) {
        this.softEnd = softEnd;
    }

    public Integer getSmeltCycle() {
        return smeltCycle;
    }

    public void setSmeltCycle(Integer smeltCycle) {
        this.smeltCycle = smeltCycle;
    }

    public Date getSlagSplash() {
        return slagSplash;
    }

    public void setSlagSplash(Date slagSplash) {
        this.slagSplash = slagSplash;
    }

    public Date getSteelStart() {
        return steelStart;
    }

    public void setSteelStart(Date steelStart) {
        this.steelStart = steelStart;
    }

    public String getMaterialConsumes() {
        return materialConsumes;
    }

    public void setMaterialConsumes(String materialConsumes) {
        this.materialConsumes = materialConsumes;
    }

    public String getOxygenBlow() {
        return oxygenBlow;
    }

    public void setOxygenBlow(String oxygenBlow) {
        this.oxygenBlow = oxygenBlow;
    }

    public String getNitrogenBlow() {
        return nitrogenBlow;
    }

    public void setNitrogenBlow(String nitrogenBlow) {
        this.nitrogenBlow = nitrogenBlow;
    }

    public String getNitrogenBottom() {
        return nitrogenBottom;
    }

    public void setNitrogenBottom(String nitrogenBottom) {
        this.nitrogenBottom = nitrogenBottom;
    }

    public String getArgonBottom() {
        return argonBottom;
    }

    public void setArgonBottom(String argonBottom) {
        this.argonBottom = argonBottom;
    }

    public Date getSteelEnd() {
        return steelEnd;
    }

    public void setSteelEnd(Date steelEnd) {
        this.steelEnd = steelEnd;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(Date acquireTime) {
        this.acquireTime = acquireTime;
    }
}
