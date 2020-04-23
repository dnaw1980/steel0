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

    public String getDesulfuri_infoUID() {
        return desulfuri_infoUID;
    }

    public void setDesulfuri_infoUID(String desulfuri_infoUID) {
        this.desulfuri_infoUID = desulfuri_infoUID;
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

    public Date getStartProcess() {
        return startProcess;
    }

    public void setStartProcess(Date startProcess) {
        this.startProcess = startProcess;
    }

    public Date getStartDesulfuri() {
        return startDesulfuri;
    }

    public void setStartDesulfuri(Date startDesulfuri) {
        this.startDesulfuri = startDesulfuri;
    }

    public Date getStartStir() {
        return startStir;
    }

    public void setStartStir(Date startStir) {
        this.startStir = startStir;
    }

    public Date getStarTtippler() {
        return starTtippler;
    }

    public void setStarTtippler(Date starTtippler) {
        this.starTtippler = starTtippler;
    }

    public String getLiquidMeasures() {
        return liquidMeasures;
    }

    public void setLiquidMeasures(String liquidMeasures) {
        this.liquidMeasures = liquidMeasures;
    }

    public String getNitrogenFlow() {
        return nitrogenFlow;
    }

    public void setNitrogenFlow(String nitrogenFlow) {
        this.nitrogenFlow = nitrogenFlow;
    }

    public String getNitrogenPressure() {
        return nitrogenPressure;
    }

    public void setNitrogenPressure(String nitrogenPressure) {
        this.nitrogenPressure = nitrogenPressure;
    }

    public String getStirHeadSpeed() {
        return stirHeadSpeed;
    }

    public void setStirHeadSpeed(String stirHeadSpeed) {
        this.stirHeadSpeed = stirHeadSpeed;
    }

    public String getMaterialWeight() {
        return materialWeight;
    }

    public void setMaterialWeight(String materialWeight) {
        this.materialWeight = materialWeight;
    }

    public Date getStartFeed() {
        return startFeed;
    }

    public void setStartFeed(Date startFeed) {
        this.startFeed = startFeed;
    }

    public Date getExitDesulfuri() {
        return exitDesulfuri;
    }

    public void setExitDesulfuri(Date exitDesulfuri) {
        this.exitDesulfuri = exitDesulfuri;
    }

    public Date getExitStir() {
        return exitStir;
    }

    public void setExitStir(Date exitStir) {
        this.exitStir = exitStir;
    }

    public Date getExitTtippler() {
        return exitTtippler;
    }

    public void setExitTtippler(Date exitTtippler) {
        this.exitTtippler = exitTtippler;
    }

    public Date getExitFeed() {
        return exitFeed;
    }

    public void setExitFeed(Date exitFeed) {
        this.exitFeed = exitFeed;
    }

    public Date getExitProcess() {
        return exitProcess;
    }

    public void setExitProcess(Date exitProcess) {
        this.exitProcess = exitProcess;
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
