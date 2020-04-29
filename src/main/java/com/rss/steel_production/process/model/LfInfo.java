package com.rss.steel_production.process.model;

import com.rss.steel_production.schedule.model.SteelSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

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

    @Transient
    private List<CompositionInfo> compositionList;

    @Transient
    private List<SteelSchedule> steelScheduleList;

    @Transient
    private Integer scheduleIdx;

    @Transient
    List<CompositionStandard> composStand;

    public List<CompositionStandard> getComposStand() {
        return composStand;
    }

    public void setComposStand(List<CompositionStandard> composStand) {
        this.composStand = composStand;
    }

    public Integer getScheduleIdx() {
        return scheduleIdx;
    }

    public void setScheduleIdx(Integer scheduleIdx) {
        this.scheduleIdx = scheduleIdx;
    }

    public List<CompositionInfo> getCompositionList() {
        return compositionList;
    }

    public void setCompositionList(List<CompositionInfo> compositionList) {
        this.compositionList = compositionList;
    }

    public List<SteelSchedule> getSteelScheduleList() {
        return steelScheduleList;
    }

    public void setSteelScheduleList(List<SteelSchedule> steelScheduleList) {
        this.steelScheduleList = steelScheduleList;
    }

    public String getLf_infoUID() {
        return lf_infoUID;
    }

    public void setLf_infoUID(String lf_infoUID) {
        this.lf_infoUID = lf_infoUID;
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

    public String getPowerConsume() {
        return powerConsume;
    }

    public void setPowerConsume(String powerConsume) {
        this.powerConsume = powerConsume;
    }

    public Date getStartLF() {
        return startLF;
    }

    public void setStartLF(Date startLF) {
        this.startLF = startLF;
    }

    public Date getEndLF() {
        return endLF;
    }

    public void setEndLF(Date endLF) {
        this.endLF = endLF;
    }

    public String getVoltageA() {
        return voltageA;
    }

    public void setVoltageA(String voltageA) {
        this.voltageA = voltageA;
    }

    public String getVoltageB() {
        return voltageB;
    }

    public void setVoltageB(String voltageB) {
        this.voltageB = voltageB;
    }

    public String getVoltageC() {
        return voltageC;
    }

    public void setVoltageC(String voltageC) {
        this.voltageC = voltageC;
    }

    public String getElectricityA() {
        return electricityA;
    }

    public void setElectricityA(String electricityA) {
        this.electricityA = electricityA;
    }

    public String getElectricityB() {
        return electricityB;
    }

    public void setElectricityB(String electricityB) {
        this.electricityB = electricityB;
    }

    public String getElectricityC() {
        return electricityC;
    }

    public void setElectricityC(String electricityC) {
        this.electricityC = electricityC;
    }

    public Integer getHeatTime() {
        return heatTime;
    }

    public void setHeatTime(Integer heatTime) {
        this.heatTime = heatTime;
    }

    public Integer getSmeltTime() {
        return smeltTime;
    }

    public void setSmeltTime(Integer smeltTime) {
        this.smeltTime = smeltTime;
    }

    public Date getRefine1() {
        return refine1;
    }

    public void setRefine1(Date refine1) {
        this.refine1 = refine1;
    }

    public Date getRefine2() {
        return refine2;
    }

    public void setRefine2(Date refine2) {
        this.refine2 = refine2;
    }

    public Date getCrane1() {
        return crane1;
    }

    public void setCrane1(Date crane1) {
        this.crane1 = crane1;
    }

    public Date getCrane2() {
        return crane2;
    }

    public void setCrane2(Date crane2) {
        this.crane2 = crane2;
    }

    public Date getStartArgon11() {
        return startArgon11;
    }

    public void setStartArgon11(Date startArgon11) {
        this.startArgon11 = startArgon11;
    }

    public Date getStartArgon12() {
        return startArgon12;
    }

    public void setStartArgon12(Date startArgon12) {
        this.startArgon12 = startArgon12;
    }

    public Date getStartArgon21() {
        return startArgon21;
    }

    public void setStartArgon21(Date startArgon21) {
        this.startArgon21 = startArgon21;
    }

    public Date getStartArgon22() {
        return startArgon22;
    }

    public void setStartArgon22(Date startArgon22) {
        this.startArgon22 = startArgon22;
    }

    public Integer getTimeArgon11() {
        return timeArgon11;
    }

    public void setTimeArgon11(Integer timeArgon11) {
        this.timeArgon11 = timeArgon11;
    }

    public Integer getTimeArgon12() {
        return timeArgon12;
    }

    public void setTimeArgon12(Integer timeArgon12) {
        this.timeArgon12 = timeArgon12;
    }

    public Integer getTimeArgon21() {
        return timeArgon21;
    }

    public void setTimeArgon21(Integer timeArgon21) {
        this.timeArgon21 = timeArgon21;
    }

    public Integer getTimeArgon22() {
        return timeArgon22;
    }

    public void setTimeArgon22(Integer timeArgon22) {
        this.timeArgon22 = timeArgon22;
    }

    public String getBlowArgon11() {
        return blowArgon11;
    }

    public void setBlowArgon11(String blowArgon11) {
        this.blowArgon11 = blowArgon11;
    }

    public String getBlowArgon12() {
        return blowArgon12;
    }

    public void setBlowArgon12(String blowArgon12) {
        this.blowArgon12 = blowArgon12;
    }

    public String getBlowArgon21() {
        return blowArgon21;
    }

    public void setBlowArgon21(String blowArgon21) {
        this.blowArgon21 = blowArgon21;
    }

    public String getBlowArgon22() {
        return blowArgon22;
    }

    public void setBlowArgon22(String blowArgon22) {
        this.blowArgon22 = blowArgon22;
    }

    public String getOxygenConsume() {
        return oxygenConsume;
    }

    public void setOxygenConsume(String oxygenConsume) {
        this.oxygenConsume = oxygenConsume;
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
