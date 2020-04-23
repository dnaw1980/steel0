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

    public String getCas_infoUID() {
        return cas_infoUID;
    }

    public void setCas_infoUID(String cas_infoUID) {
        this.cas_infoUID = cas_infoUID;
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

    public Date getEnterStation() {
        return enterStation;
    }

    public void setEnterStation(Date enterStation) {
        this.enterStation = enterStation;
    }

    public Integer getOxygenTotal() {
        return oxygenTotal;
    }

    public void setOxygenTotal(Integer oxygenTotal) {
        this.oxygenTotal = oxygenTotal;
    }

    public Integer getBottomBlow() {
        return bottomBlow;
    }

    public void setBottomBlow(Integer bottomBlow) {
        this.bottomBlow = bottomBlow;
    }

    public String getOxygenConsume() {
        return oxygenConsume;
    }

    public void setOxygenConsume(String oxygenConsume) {
        this.oxygenConsume = oxygenConsume;
    }

    public String getArgonConsume() {
        return argonConsume;
    }

    public void setArgonConsume(String argonConsume) {
        this.argonConsume = argonConsume;
    }

    public String getMaterialConsume() {
        return materialConsume;
    }

    public void setMaterialConsume(String materialConsume) {
        this.materialConsume = materialConsume;
    }

    public Date getExitStation() {
        return exitStation;
    }

    public void setExitStation(Date exitStation) {
        this.exitStation = exitStation;
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
