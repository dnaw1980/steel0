package com.rss.steel_production.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "process_info")
public class ProcessInfo {
    @Id
    private String process_infoUID;
    private String chargeNo;
    private String ironStationNo;
    private String steelGrade;
    private String castNo;
    private Integer castSeq;
    private String planNo;
    private String length;
    private String width;
    private String processRoute;
    private String specification;
    private String ironNo;
    private Integer ironSeq;
    private String blastNo;
    private String tankNo;
    private String ladleFrame;
    private String tare;
    private Float bucketGross;
    private Float bucketTare;
    private Float grossWeight;
    private Float netWeight;
    private String scrab;
    private Date outIronTime;
    private Date startTime;
    private Date stopTime;
    private String outIronPort;
    private String ironGoWhere;
    private String desulfuriStationNo;
    private Date desulfuriEnterTime;
    private Date desulfuriExitTime;
    private Date handleTime;
    private Date desulfTime;
    private String stir;
    private String nitrogen;
    private String desulfuriMaterialInfo;
    private String desulfuriWeight;
    private String desulfuriTemperature;
    private String desulfuriGoWhere;
    private Date desulfuriPlanEnter;
    private String convererStationNo;
    private Date convererEnterTime;
    private Date beginOxygen;
    private Date endOxygen;
    private Date beginSoft;
    private Date endSoft;
    private Date slagSplash;
    private Date steelTap;
    private Float blowOxygenAmt;
    private Float topNitrogenAmt;
    private Float bottomNitrogenAmt;
    private Float blowArgonAmt;
    private Date exitConverer;
    private Float smeltingCycle;
    private String convererWeight;
    private String convererTemperature;
    private String convererMaterialInfo;
    private Date verticleSmelt;
    private String convererGoWhere;
    private Date convererPlanEnter;
    private String casStationNo;
    private Date casEnterTime;
    private Date casOxygenTime;
    private Date casBottomBlow;
    private Date casSoftBlow;
    private Float casEnterOxygen;
    private Float casExitOxygen;
    private Float casOxygenOBAmt;
    private Float casBottomBlowAmt;
    private Float casWireConsumeQty;
    private Date casExitTime;
    private String casMaterialInfo;
    private String casWeight;
    private String casTemperature;
    private String casGoWhere;
    private Date casPlanEnter;
    private String lfStationNo;
    private Date lfEnterTime;
    private Date startLFTime;
    private Date endLFTime;
    private Date lfExitTime;
    private Date lfStartPowerTime;
    private Date lfSoftBlowTime;
    private Date heatTime;
    private Date smeltTime;
    private Float lfEnterOxygen;
    private Float lfExitOxygen;
    private Float lfPowerConsume;
    private Float lfArgonConsume;
    private Float lfNitrogenConsume;
    private Float lfWireConsume;
    private String lfVoltage;
    private String lfElectricity;
    private String lfMaterialInfo;
    private String lfWeight;
    private String lfTemperature;
    private String lfGoWhere;
    private Date lfPlanEnter;
    private String rhStationNo;
    private Date enterRHTime;
    private Date rhCirculaStart;
    private Date rhBreakVacuum;
    private Date rhExitTime;
    private Date rhBlowOxygen;
    private Date rhSoftBlow;
    private Float rhEnterOxygen;
    private Float rhExitOxygen;
    private Float rhOxygenConsume;
    private Float rhArgonBottom;
    private Float rhArgonCircula;
    private Float rhWaterConsume;
    private Float rhSteamConsume;
    private Float rhGasConsume;
    private Float rhPropaneConsume;
    private Float rhWireConsume;
    private String rhMaterialInfo;
    private String rhTemperature;
    private String slabStationNo;
    private String slabLadleNo;
    private String slabTundishNo;
    private Date slabStartCastTime;
    private Date slabEndCastTime;
    private Float slabBilletSizing;
    private String enterRotating;
    private String exitRotating;
    private String slabTundishCarNo;
    private Float slabTundishWeigh;
    private Float slabLiquidusTemper;
    private String slabTundishTemper;
    private Float slabOverHeat;
    private String slabDrawingSpeed;
    private String slabCrystalWater;
    private String slabCrystalHeat;
    private String slabCrystalPress;
    private String slabEnterTemper;
    private String slabExitTemper;
    private String slabDifferTemper;
    private String slabLiquidLevel;
    private String cold2AirPress;
    private String cold2AirFlow;
    private String cold2Flow;
    private String slabCold2Press;
    private String slabCold2ControlWay;
    private String slabCold2WaterDistrib;
    private Float cold2Model;
    private Float slabStraightPress;
    private Float slabStraightCurrent;
    private Float iraneFlow;
    private Float iranePress;
    private Float slabCastFlow;
    private Float slabCastPress;
    private String slabCasterStatus;
    private Float slabVibrationSlope;
    private Float slabVibrationFrequ;
    private Float slabVibrationAmt;
    private String slabLadleSelfOpen;
    private Float slabSteelWaterPour;
    private String slabCoverCompany;
    private Float slabCoverAddAmt;
    private String slabCoverType;
    private String slabPowderCompany;
    private String slabPowderType;
    private Float slabPowderAddAmt;
    private Date slabTundishSmallTime;
    private Date slabTundishMiddleTime;
    private Date slabTundishBigTime;
    private Float slabTundishGasPress;
    private Integer slabCount;
    private String slabCrystalNo1;
    private String slabCrystalNo2;
    private Float slabTundishPouring;
    private Float slabProcessTossing;
    private Float slabHeadTrimAmt;
    private Float slabTailTrimAmt;
    private Float slabTundishMargin;
    private Date longReplaceTime;
    private Date infuseReplaceTime;
    private String rollGap;
    private String slabTemperature;
    private String slabDeviceStatus;
    private Date slabPlanEnter;
    private String ladleNo;
    private String billetTundishNo;
    private String billetStationNo;
    private String tundish1Temper;
    private String tundish2Temper;
    private String billetEnterTemper;
    private String billetExitTemper;
    private String billetCrystalWater;
    private String billetDifferTemper;
    private String cutLength;
    private String billetCasterStatus;
    private String billetDrawingSpeed;
    private String cold2Flow1;
    private String cold2Flow2;
    private String cold2Flow3;
    private String cold2Flow4;
    private String cold2Press;
    private String billetVibrationSlope;
    private String billetVibrationFrequ;
    private String billetVibrationAmt;
    private String billetTundishCarNo;
    private String billetCoverCompany;
    private String billetCoverAddAmt;
    private String billetCoverType;
    private String billetPowderCompany;
    private String billetPowderType;
    private Float billetPowderAddAmt;
    private String billetCrystalNo1;
    private String billetCrystalNo2;
    private String billetCrystalNo3;
    private String billetCrystalNo4;
    private String billetCrystalNo5;
    private String billetCrystalNo6;
    private String billetCrystalNo7;
    private String billetCrystalNo8;
    private String billetCrystalNo9;
    private String billetCrystalNo10;
    private String billetTemperature;
    private Date billetEnterRotatingTime;
    private Date billetExitRotatingTime;
    private Date billetStartCastTime;
    private Date billetEndCastTime;
    private Date billetTundishSmall;
    private Date billetTundishMiddle;
    private Date billetTundishBigTime;
    private Date longReplace;
    private Date infuseReplace;
    private String billetDeviceStatus;
    private Float billetSizing;
    private Float baleWeigh1;
    private Float baleWeigh2;
    private Float billetTundishWeigh;
    private Float billetLiquidusTemper;
    private Float billetOverHeat;
    private String billetCrystalPress;
    private String billetCold2Press;
    private String equipPress;
    private Float crystalWaterTemper;
    private String billetLiquidLevel;
    private String billetStraightPress;
    private Float billetCold2ControlWay;
    private String billetCastFlow;
    private String billetCastPress;
    private Float billetTundishGasPress;
    private Float billetTundishPouring;
    private Float billetProcessTossing;
    private Float billetHeadTrimAmt;
    private Float billetTailTrimAmt;
    private Float billetTundishMargin;
    private Integer billetCount;
    private String billetLadleSelfOpen;
    private String billetSteelWaterPour;
    private Date billetPlanEnter;
    private String scheduleStatus;
    private Date processDate;

    public String getProcess_infoUID() {
        return process_infoUID;
    }

    public void setProcess_infoUID(String process_infoUID) {
        this.process_infoUID = process_infoUID;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public String getSteelGrade() {
        return steelGrade;
    }

    public void setSteelGrade(String steelGrade) {
        this.steelGrade = steelGrade;
    }

    public String getCastNo() {
        return castNo;
    }

    public void setCastNo(String castNo) {
        this.castNo = castNo;
    }

    public Integer getCastSeq() {
        return castSeq;
    }

    public void setCastSeq(Integer castSeq) {
        this.castSeq = castSeq;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getProcessRoute() {
        return processRoute;
    }

    public void setProcessRoute(String processRoute) {
        this.processRoute = processRoute;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getIronNo() {
        return ironNo;
    }

    public void setIronNo(String ironNo) {
        this.ironNo = ironNo;
    }

    public Integer getIronSeq() {
        return ironSeq;
    }

    public void setIronSeq(Integer ironSeq) {
        this.ironSeq = ironSeq;
    }

    public String getBlastNo() {
        return blastNo;
    }

    public void setBlastNo(String blastNo) {
        this.blastNo = blastNo;
    }

    public String getTankNo() {
        return tankNo;
    }

    public void setTankNo(String tankNo) {
        this.tankNo = tankNo;
    }

    public String getLadleFrame() {
        return ladleFrame;
    }

    public void setLadleFrame(String ladleFrame) {
        this.ladleFrame = ladleFrame;
    }

    public String getTare() {
        return tare;
    }

    public void setTare(String tare) {
        this.tare = tare;
    }

    public Float getBucketGross() {
        return bucketGross;
    }

    public void setBucketGross(Float bucketGross) {
        this.bucketGross = bucketGross;
    }

    public Float getBucketTare() {
        return bucketTare;
    }

    public void setBucketTare(Float bucketTare) {
        this.bucketTare = bucketTare;
    }

    public Float getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Float grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Float getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Float netWeight) {
        this.netWeight = netWeight;
    }

    public String getScrab() {
        return scrab;
    }

    public void setScrab(String scrab) {
        this.scrab = scrab;
    }

    public Date getOutIronTime() {
        return outIronTime;
    }

    public void setOutIronTime(Date outIronTime) {
        this.outIronTime = outIronTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getOutIronPort() {
        return outIronPort;
    }

    public void setOutIronPort(String outIronPort) {
        this.outIronPort = outIronPort;
    }

    public String getIronGoWhere() {
        return ironGoWhere;
    }

    public void setIronGoWhere(String ironGoWhere) {
        this.ironGoWhere = ironGoWhere;
    }

    public String getDesulfuriStationNo() {
        return desulfuriStationNo;
    }

    public void setDesulfuriStationNo(String desulfuriStationNo) {
        this.desulfuriStationNo = desulfuriStationNo;
    }

    public Date getDesulfuriEnterTime() {
        return desulfuriEnterTime;
    }

    public void setDesulfuriEnterTime(Date desulfuriEnterTime) {
        this.desulfuriEnterTime = desulfuriEnterTime;
    }

    public Date getDesulfuriExitTime() {
        return desulfuriExitTime;
    }

    public void setDesulfuriExitTime(Date desulfuriExitTime) {
        this.desulfuriExitTime = desulfuriExitTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getDesulfTime() {
        return desulfTime;
    }

    public void setDesulfTime(Date desulfTime) {
        this.desulfTime = desulfTime;
    }

    public String getStir() {
        return stir;
    }

    public void setStir(String stir) {
        this.stir = stir;
    }

    public String getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(String nitrogen) {
        this.nitrogen = nitrogen;
    }

    public String getDesulfuriMaterialInfo() {
        return desulfuriMaterialInfo;
    }

    public void setDesulfuriMaterialInfo(String desulfuriMaterialInfo) {
        this.desulfuriMaterialInfo = desulfuriMaterialInfo;
    }

    public String getDesulfuriWeight() {
        return desulfuriWeight;
    }

    public void setDesulfuriWeight(String desulfuriWeight) {
        this.desulfuriWeight = desulfuriWeight;
    }

    public String getDesulfuriTemperature() {
        return desulfuriTemperature;
    }

    public void setDesulfuriTemperature(String desulfuriTemperature) {
        this.desulfuriTemperature = desulfuriTemperature;
    }

    public String getDesulfuriGoWhere() {
        return desulfuriGoWhere;
    }

    public void setDesulfuriGoWhere(String desulfuriGoWhere) {
        this.desulfuriGoWhere = desulfuriGoWhere;
    }

    public Date getDesulfuriPlanEnter() {
        return desulfuriPlanEnter;
    }

    public void setDesulfuriPlanEnter(Date desulfuriPlanEnter) {
        this.desulfuriPlanEnter = desulfuriPlanEnter;
    }

    public String getConvererStationNo() {
        return convererStationNo;
    }

    public void setConvererStationNo(String convererStationNo) {
        this.convererStationNo = convererStationNo;
    }

    public Date getConvererEnterTime() {
        return convererEnterTime;
    }

    public void setConvererEnterTime(Date convererEnterTime) {
        this.convererEnterTime = convererEnterTime;
    }

    public Date getBeginOxygen() {
        return beginOxygen;
    }

    public void setBeginOxygen(Date beginOxygen) {
        this.beginOxygen = beginOxygen;
    }

    public Date getEndOxygen() {
        return endOxygen;
    }

    public void setEndOxygen(Date endOxygen) {
        this.endOxygen = endOxygen;
    }

    public Date getBeginSoft() {
        return beginSoft;
    }

    public void setBeginSoft(Date beginSoft) {
        this.beginSoft = beginSoft;
    }

    public Date getEndSoft() {
        return endSoft;
    }

    public void setEndSoft(Date endSoft) {
        this.endSoft = endSoft;
    }

    public Date getSlagSplash() {
        return slagSplash;
    }

    public void setSlagSplash(Date slagSplash) {
        this.slagSplash = slagSplash;
    }

    public Date getSteelTap() {
        return steelTap;
    }

    public void setSteelTap(Date steelTap) {
        this.steelTap = steelTap;
    }

    public Float getBlowOxygenAmt() {
        return blowOxygenAmt;
    }

    public void setBlowOxygenAmt(Float blowOxygenAmt) {
        this.blowOxygenAmt = blowOxygenAmt;
    }

    public Float getTopNitrogenAmt() {
        return topNitrogenAmt;
    }

    public void setTopNitrogenAmt(Float topNitrogenAmt) {
        this.topNitrogenAmt = topNitrogenAmt;
    }

    public Float getBottomNitrogenAmt() {
        return bottomNitrogenAmt;
    }

    public void setBottomNitrogenAmt(Float bottomNitrogenAmt) {
        this.bottomNitrogenAmt = bottomNitrogenAmt;
    }

    public Float getBlowArgonAmt() {
        return blowArgonAmt;
    }

    public void setBlowArgonAmt(Float blowArgonAmt) {
        this.blowArgonAmt = blowArgonAmt;
    }

    public Date getExitConverer() {
        return exitConverer;
    }

    public void setExitConverer(Date exitConverer) {
        this.exitConverer = exitConverer;
    }

    public Float getSmeltingCycle() {
        return smeltingCycle;
    }

    public void setSmeltingCycle(Float smeltingCycle) {
        this.smeltingCycle = smeltingCycle;
    }

    public String getConvererWeight() {
        return convererWeight;
    }

    public void setConvererWeight(String convererWeight) {
        this.convererWeight = convererWeight;
    }

    public String getConvererTemperature() {
        return convererTemperature;
    }

    public void setConvererTemperature(String convererTemperature) {
        this.convererTemperature = convererTemperature;
    }

    public String getConvererMaterialInfo() {
        return convererMaterialInfo;
    }

    public void setConvererMaterialInfo(String convererMaterialInfo) {
        this.convererMaterialInfo = convererMaterialInfo;
    }

    public Date getVerticleSmelt() {
        return verticleSmelt;
    }

    public void setVerticleSmelt(Date verticleSmelt) {
        this.verticleSmelt = verticleSmelt;
    }

    public String getConvererGoWhere() {
        return convererGoWhere;
    }

    public void setConvererGoWhere(String convererGoWhere) {
        this.convererGoWhere = convererGoWhere;
    }

    public Date getConvererPlanEnter() {
        return convererPlanEnter;
    }

    public void setConvererPlanEnter(Date convererPlanEnter) {
        this.convererPlanEnter = convererPlanEnter;
    }

    public String getCasStationNo() {
        return casStationNo;
    }

    public void setCasStationNo(String casStationNo) {
        this.casStationNo = casStationNo;
    }

    public Date getCasEnterTime() {
        return casEnterTime;
    }

    public void setCasEnterTime(Date casEnterTime) {
        this.casEnterTime = casEnterTime;
    }

    public Date getCasOxygenTime() {
        return casOxygenTime;
    }

    public void setCasOxygenTime(Date casOxygenTime) {
        this.casOxygenTime = casOxygenTime;
    }

    public Date getCasBottomBlow() {
        return casBottomBlow;
    }

    public void setCasBottomBlow(Date casBottomBlow) {
        this.casBottomBlow = casBottomBlow;
    }

    public Date getCasSoftBlow() {
        return casSoftBlow;
    }

    public void setCasSoftBlow(Date casSoftBlow) {
        this.casSoftBlow = casSoftBlow;
    }

    public Float getCasEnterOxygen() {
        return casEnterOxygen;
    }

    public void setCasEnterOxygen(Float casEnterOxygen) {
        this.casEnterOxygen = casEnterOxygen;
    }

    public Float getCasExitOxygen() {
        return casExitOxygen;
    }

    public void setCasExitOxygen(Float casExitOxygen) {
        this.casExitOxygen = casExitOxygen;
    }

    public Float getCasOxygenOBAmt() {
        return casOxygenOBAmt;
    }

    public void setCasOxygenOBAmt(Float casOxygenOBAmt) {
        this.casOxygenOBAmt = casOxygenOBAmt;
    }

    public Float getCasBottomBlowAmt() {
        return casBottomBlowAmt;
    }

    public void setCasBottomBlowAmt(Float casBottomBlowAmt) {
        this.casBottomBlowAmt = casBottomBlowAmt;
    }

    public Float getCasWireConsumeQty() {
        return casWireConsumeQty;
    }

    public void setCasWireConsumeQty(Float casWireConsumeQty) {
        this.casWireConsumeQty = casWireConsumeQty;
    }

    public Date getCasExitTime() {
        return casExitTime;
    }

    public void setCasExitTime(Date casExitTime) {
        this.casExitTime = casExitTime;
    }

    public String getCasMaterialInfo() {
        return casMaterialInfo;
    }

    public void setCasMaterialInfo(String casMaterialInfo) {
        this.casMaterialInfo = casMaterialInfo;
    }

    public String getCasWeight() {
        return casWeight;
    }

    public void setCasWeight(String casWeight) {
        this.casWeight = casWeight;
    }

    public String getCasTemperature() {
        return casTemperature;
    }

    public void setCasTemperature(String casTemperature) {
        this.casTemperature = casTemperature;
    }

    public String getCasGoWhere() {
        return casGoWhere;
    }

    public void setCasGoWhere(String casGoWhere) {
        this.casGoWhere = casGoWhere;
    }

    public Date getCasPlanEnter() {
        return casPlanEnter;
    }

    public void setCasPlanEnter(Date casPlanEnter) {
        this.casPlanEnter = casPlanEnter;
    }

    public String getLfStationNo() {
        return lfStationNo;
    }

    public void setLfStationNo(String lfStationNo) {
        this.lfStationNo = lfStationNo;
    }

    public Date getLfEnterTime() {
        return lfEnterTime;
    }

    public void setLfEnterTime(Date lfEnterTime) {
        this.lfEnterTime = lfEnterTime;
    }

    public Date getStartLFTime() {
        return startLFTime;
    }

    public void setStartLFTime(Date startLFTime) {
        this.startLFTime = startLFTime;
    }

    public Date getEndLFTime() {
        return endLFTime;
    }

    public void setEndLFTime(Date endLFTime) {
        this.endLFTime = endLFTime;
    }

    public Date getLfExitTime() {
        return lfExitTime;
    }

    public void setLfExitTime(Date lfExitTime) {
        this.lfExitTime = lfExitTime;
    }

    public Date getLfStartPowerTime() {
        return lfStartPowerTime;
    }

    public void setLfStartPowerTime(Date lfStartPowerTime) {
        this.lfStartPowerTime = lfStartPowerTime;
    }

    public Date getLfSoftBlowTime() {
        return lfSoftBlowTime;
    }

    public void setLfSoftBlowTime(Date lfSoftBlowTime) {
        this.lfSoftBlowTime = lfSoftBlowTime;
    }

    public Date getHeatTime() {
        return heatTime;
    }

    public void setHeatTime(Date heatTime) {
        this.heatTime = heatTime;
    }

    public Date getSmeltTime() {
        return smeltTime;
    }

    public void setSmeltTime(Date smeltTime) {
        this.smeltTime = smeltTime;
    }

    public Float getLfEnterOxygen() {
        return lfEnterOxygen;
    }

    public void setLfEnterOxygen(Float lfEnterOxygen) {
        this.lfEnterOxygen = lfEnterOxygen;
    }

    public Float getLfExitOxygen() {
        return lfExitOxygen;
    }

    public void setLfExitOxygen(Float lfExitOxygen) {
        this.lfExitOxygen = lfExitOxygen;
    }

    public Float getLfPowerConsume() {
        return lfPowerConsume;
    }

    public void setLfPowerConsume(Float lfPowerConsume) {
        this.lfPowerConsume = lfPowerConsume;
    }

    public Float getLfArgonConsume() {
        return lfArgonConsume;
    }

    public void setLfArgonConsume(Float lfArgonConsume) {
        this.lfArgonConsume = lfArgonConsume;
    }

    public Float getLfNitrogenConsume() {
        return lfNitrogenConsume;
    }

    public void setLfNitrogenConsume(Float lfNitrogenConsume) {
        this.lfNitrogenConsume = lfNitrogenConsume;
    }

    public Float getLfWireConsume() {
        return lfWireConsume;
    }

    public void setLfWireConsume(Float lfWireConsume) {
        this.lfWireConsume = lfWireConsume;
    }

    public String getLfVoltage() {
        return lfVoltage;
    }

    public void setLfVoltage(String lfVoltage) {
        this.lfVoltage = lfVoltage;
    }

    public String getLfElectricity() {
        return lfElectricity;
    }

    public void setLfElectricity(String lfElectricity) {
        this.lfElectricity = lfElectricity;
    }

    public String getLfMaterialInfo() {
        return lfMaterialInfo;
    }

    public void setLfMaterialInfo(String lfMaterialInfo) {
        this.lfMaterialInfo = lfMaterialInfo;
    }

    public String getLfWeight() {
        return lfWeight;
    }

    public void setLfWeight(String lfWeight) {
        this.lfWeight = lfWeight;
    }

    public String getLfTemperature() {
        return lfTemperature;
    }

    public void setLfTemperature(String lfTemperature) {
        this.lfTemperature = lfTemperature;
    }

    public String getLfGoWhere() {
        return lfGoWhere;
    }

    public void setLfGoWhere(String lfGoWhere) {
        this.lfGoWhere = lfGoWhere;
    }

    public Date getLfPlanEnter() {
        return lfPlanEnter;
    }

    public void setLfPlanEnter(Date lfPlanEnter) {
        this.lfPlanEnter = lfPlanEnter;
    }

    public String getRhStationNo() {
        return rhStationNo;
    }

    public void setRhStationNo(String rhStationNo) {
        this.rhStationNo = rhStationNo;
    }

    public Date getEnterRHTime() {
        return enterRHTime;
    }

    public void setEnterRHTime(Date enterRHTime) {
        this.enterRHTime = enterRHTime;
    }

    public Date getRhCirculaStart() {
        return rhCirculaStart;
    }

    public void setRhCirculaStart(Date rhCirculaStart) {
        this.rhCirculaStart = rhCirculaStart;
    }

    public Date getRhBreakVacuum() {
        return rhBreakVacuum;
    }

    public void setRhBreakVacuum(Date rhBreakVacuum) {
        this.rhBreakVacuum = rhBreakVacuum;
    }

    public Date getRhExitTime() {
        return rhExitTime;
    }

    public void setRhExitTime(Date rhExitTime) {
        this.rhExitTime = rhExitTime;
    }

    public Date getRhBlowOxygen() {
        return rhBlowOxygen;
    }

    public void setRhBlowOxygen(Date rhBlowOxygen) {
        this.rhBlowOxygen = rhBlowOxygen;
    }

    public Date getRhSoftBlow() {
        return rhSoftBlow;
    }

    public void setRhSoftBlow(Date rhSoftBlow) {
        this.rhSoftBlow = rhSoftBlow;
    }

    public Float getRhEnterOxygen() {
        return rhEnterOxygen;
    }

    public void setRhEnterOxygen(Float rhEnterOxygen) {
        this.rhEnterOxygen = rhEnterOxygen;
    }

    public Float getRhExitOxygen() {
        return rhExitOxygen;
    }

    public void setRhExitOxygen(Float rhExitOxygen) {
        this.rhExitOxygen = rhExitOxygen;
    }

    public Float getRhOxygenConsume() {
        return rhOxygenConsume;
    }

    public void setRhOxygenConsume(Float rhOxygenConsume) {
        this.rhOxygenConsume = rhOxygenConsume;
    }

    public Float getRhArgonBottom() {
        return rhArgonBottom;
    }

    public void setRhArgonBottom(Float rhArgonBottom) {
        this.rhArgonBottom = rhArgonBottom;
    }

    public Float getRhArgonCircula() {
        return rhArgonCircula;
    }

    public void setRhArgonCircula(Float rhArgonCircula) {
        this.rhArgonCircula = rhArgonCircula;
    }

    public Float getRhWaterConsume() {
        return rhWaterConsume;
    }

    public void setRhWaterConsume(Float rhWaterConsume) {
        this.rhWaterConsume = rhWaterConsume;
    }

    public Float getRhSteamConsume() {
        return rhSteamConsume;
    }

    public void setRhSteamConsume(Float rhSteamConsume) {
        this.rhSteamConsume = rhSteamConsume;
    }

    public Float getRhGasConsume() {
        return rhGasConsume;
    }

    public void setRhGasConsume(Float rhGasConsume) {
        this.rhGasConsume = rhGasConsume;
    }

    public Float getRhPropaneConsume() {
        return rhPropaneConsume;
    }

    public void setRhPropaneConsume(Float rhPropaneConsume) {
        this.rhPropaneConsume = rhPropaneConsume;
    }

    public Float getRhWireConsume() {
        return rhWireConsume;
    }

    public void setRhWireConsume(Float rhWireConsume) {
        this.rhWireConsume = rhWireConsume;
    }

    public String getRhMaterialInfo() {
        return rhMaterialInfo;
    }

    public void setRhMaterialInfo(String rhMaterialInfo) {
        this.rhMaterialInfo = rhMaterialInfo;
    }

    public String getRhTemperature() {
        return rhTemperature;
    }

    public void setRhTemperature(String rhTemperature) {
        this.rhTemperature = rhTemperature;
    }

    public String getSlabStationNo() {
        return slabStationNo;
    }

    public void setSlabStationNo(String slabStationNo) {
        this.slabStationNo = slabStationNo;
    }

    public String getSlabLadleNo() {
        return slabLadleNo;
    }

    public void setSlabLadleNo(String slabLadleNo) {
        this.slabLadleNo = slabLadleNo;
    }

    public String getSlabTundishNo() {
        return slabTundishNo;
    }

    public void setSlabTundishNo(String slabTundishNo) {
        this.slabTundishNo = slabTundishNo;
    }

    public Date getSlabStartCastTime() {
        return slabStartCastTime;
    }

    public void setSlabStartCastTime(Date slabStartCastTime) {
        this.slabStartCastTime = slabStartCastTime;
    }

    public Date getSlabEndCastTime() {
        return slabEndCastTime;
    }

    public void setSlabEndCastTime(Date slabEndCastTime) {
        this.slabEndCastTime = slabEndCastTime;
    }

    public Float getSlabBilletSizing() {
        return slabBilletSizing;
    }

    public void setSlabBilletSizing(Float slabBilletSizing) {
        this.slabBilletSizing = slabBilletSizing;
    }

    public String getEnterRotating() {
        return enterRotating;
    }

    public void setEnterRotating(String enterRotating) {
        this.enterRotating = enterRotating;
    }

    public String getExitRotating() {
        return exitRotating;
    }

    public void setExitRotating(String exitRotating) {
        this.exitRotating = exitRotating;
    }

    public String getSlabTundishCarNo() {
        return slabTundishCarNo;
    }

    public void setSlabTundishCarNo(String slabTundishCarNo) {
        this.slabTundishCarNo = slabTundishCarNo;
    }

    public Float getSlabTundishWeigh() {
        return slabTundishWeigh;
    }

    public void setSlabTundishWeigh(Float slabTundishWeigh) {
        this.slabTundishWeigh = slabTundishWeigh;
    }

    public Float getSlabLiquidusTemper() {
        return slabLiquidusTemper;
    }

    public void setSlabLiquidusTemper(Float slabLiquidusTemper) {
        this.slabLiquidusTemper = slabLiquidusTemper;
    }

    public String getSlabTundishTemper() {
        return slabTundishTemper;
    }

    public void setSlabTundishTemper(String slabTundishTemper) {
        this.slabTundishTemper = slabTundishTemper;
    }

    public Float getSlabOverHeat() {
        return slabOverHeat;
    }

    public void setSlabOverHeat(Float slabOverHeat) {
        this.slabOverHeat = slabOverHeat;
    }

    public String getSlabDrawingSpeed() {
        return slabDrawingSpeed;
    }

    public void setSlabDrawingSpeed(String slabDrawingSpeed) {
        this.slabDrawingSpeed = slabDrawingSpeed;
    }

    public String getSlabCrystalWater() {
        return slabCrystalWater;
    }

    public void setSlabCrystalWater(String slabCrystalWater) {
        this.slabCrystalWater = slabCrystalWater;
    }

    public String getSlabCrystalHeat() {
        return slabCrystalHeat;
    }

    public void setSlabCrystalHeat(String slabCrystalHeat) {
        this.slabCrystalHeat = slabCrystalHeat;
    }

    public String getSlabCrystalPress() {
        return slabCrystalPress;
    }

    public void setSlabCrystalPress(String slabCrystalPress) {
        this.slabCrystalPress = slabCrystalPress;
    }

    public String getSlabEnterTemper() {
        return slabEnterTemper;
    }

    public void setSlabEnterTemper(String slabEnterTemper) {
        this.slabEnterTemper = slabEnterTemper;
    }

    public String getSlabExitTemper() {
        return slabExitTemper;
    }

    public void setSlabExitTemper(String slabExitTemper) {
        this.slabExitTemper = slabExitTemper;
    }

    public String getSlabDifferTemper() {
        return slabDifferTemper;
    }

    public void setSlabDifferTemper(String slabDifferTemper) {
        this.slabDifferTemper = slabDifferTemper;
    }

    public String getSlabLiquidLevel() {
        return slabLiquidLevel;
    }

    public void setSlabLiquidLevel(String slabLiquidLevel) {
        this.slabLiquidLevel = slabLiquidLevel;
    }

    public String getCold2AirPress() {
        return cold2AirPress;
    }

    public void setCold2AirPress(String cold2AirPress) {
        this.cold2AirPress = cold2AirPress;
    }

    public String getCold2AirFlow() {
        return cold2AirFlow;
    }

    public void setCold2AirFlow(String cold2AirFlow) {
        this.cold2AirFlow = cold2AirFlow;
    }

    public String getCold2Flow() {
        return cold2Flow;
    }

    public void setCold2Flow(String cold2Flow) {
        this.cold2Flow = cold2Flow;
    }

    public String getSlabCold2Press() {
        return slabCold2Press;
    }

    public void setSlabCold2Press(String slabCold2Press) {
        this.slabCold2Press = slabCold2Press;
    }

    public String getSlabCold2ControlWay() {
        return slabCold2ControlWay;
    }

    public void setSlabCold2ControlWay(String slabCold2ControlWay) {
        this.slabCold2ControlWay = slabCold2ControlWay;
    }

    public String getSlabCold2WaterDistrib() {
        return slabCold2WaterDistrib;
    }

    public void setSlabCold2WaterDistrib(String slabCold2WaterDistrib) {
        this.slabCold2WaterDistrib = slabCold2WaterDistrib;
    }

    public Float getCold2Model() {
        return cold2Model;
    }

    public void setCold2Model(Float cold2Model) {
        this.cold2Model = cold2Model;
    }

    public Float getSlabStraightPress() {
        return slabStraightPress;
    }

    public void setSlabStraightPress(Float slabStraightPress) {
        this.slabStraightPress = slabStraightPress;
    }

    public Float getSlabStraightCurrent() {
        return slabStraightCurrent;
    }

    public void setSlabStraightCurrent(Float slabStraightCurrent) {
        this.slabStraightCurrent = slabStraightCurrent;
    }

    public Float getIraneFlow() {
        return iraneFlow;
    }

    public void setIraneFlow(Float iraneFlow) {
        this.iraneFlow = iraneFlow;
    }

    public Float getIranePress() {
        return iranePress;
    }

    public void setIranePress(Float iranePress) {
        this.iranePress = iranePress;
    }

    public Float getSlabCastFlow() {
        return slabCastFlow;
    }

    public void setSlabCastFlow(Float slabCastFlow) {
        this.slabCastFlow = slabCastFlow;
    }

    public Float getSlabCastPress() {
        return slabCastPress;
    }

    public void setSlabCastPress(Float slabCastPress) {
        this.slabCastPress = slabCastPress;
    }

    public String getSlabCasterStatus() {
        return slabCasterStatus;
    }

    public void setSlabCasterStatus(String slabCasterStatus) {
        this.slabCasterStatus = slabCasterStatus;
    }

    public Float getSlabVibrationSlope() {
        return slabVibrationSlope;
    }

    public void setSlabVibrationSlope(Float slabVibrationSlope) {
        this.slabVibrationSlope = slabVibrationSlope;
    }

    public Float getSlabVibrationFrequ() {
        return slabVibrationFrequ;
    }

    public void setSlabVibrationFrequ(Float slabVibrationFrequ) {
        this.slabVibrationFrequ = slabVibrationFrequ;
    }

    public Float getSlabVibrationAmt() {
        return slabVibrationAmt;
    }

    public void setSlabVibrationAmt(Float slabVibrationAmt) {
        this.slabVibrationAmt = slabVibrationAmt;
    }

    public String getSlabLadleSelfOpen() {
        return slabLadleSelfOpen;
    }

    public void setSlabLadleSelfOpen(String slabLadleSelfOpen) {
        this.slabLadleSelfOpen = slabLadleSelfOpen;
    }

    public Float getSlabSteelWaterPour() {
        return slabSteelWaterPour;
    }

    public void setSlabSteelWaterPour(Float slabSteelWaterPour) {
        this.slabSteelWaterPour = slabSteelWaterPour;
    }

    public String getSlabCoverCompany() {
        return slabCoverCompany;
    }

    public void setSlabCoverCompany(String slabCoverCompany) {
        this.slabCoverCompany = slabCoverCompany;
    }

    public Float getSlabCoverAddAmt() {
        return slabCoverAddAmt;
    }

    public void setSlabCoverAddAmt(Float slabCoverAddAmt) {
        this.slabCoverAddAmt = slabCoverAddAmt;
    }

    public String getSlabCoverType() {
        return slabCoverType;
    }

    public void setSlabCoverType(String slabCoverType) {
        this.slabCoverType = slabCoverType;
    }

    public String getSlabPowderCompany() {
        return slabPowderCompany;
    }

    public void setSlabPowderCompany(String slabPowderCompany) {
        this.slabPowderCompany = slabPowderCompany;
    }

    public String getSlabPowderType() {
        return slabPowderType;
    }

    public void setSlabPowderType(String slabPowderType) {
        this.slabPowderType = slabPowderType;
    }

    public Float getSlabPowderAddAmt() {
        return slabPowderAddAmt;
    }

    public void setSlabPowderAddAmt(Float slabPowderAddAmt) {
        this.slabPowderAddAmt = slabPowderAddAmt;
    }

    public Date getSlabTundishSmallTime() {
        return slabTundishSmallTime;
    }

    public void setSlabTundishSmallTime(Date slabTundishSmallTime) {
        this.slabTundishSmallTime = slabTundishSmallTime;
    }

    public Date getSlabTundishMiddleTime() {
        return slabTundishMiddleTime;
    }

    public void setSlabTundishMiddleTime(Date slabTundishMiddleTime) {
        this.slabTundishMiddleTime = slabTundishMiddleTime;
    }

    public Date getSlabTundishBigTime() {
        return slabTundishBigTime;
    }

    public void setSlabTundishBigTime(Date slabTundishBigTime) {
        this.slabTundishBigTime = slabTundishBigTime;
    }

    public Float getSlabTundishGasPress() {
        return slabTundishGasPress;
    }

    public void setSlabTundishGasPress(Float slabTundishGasPress) {
        this.slabTundishGasPress = slabTundishGasPress;
    }

    public Integer getSlabCount() {
        return slabCount;
    }

    public void setSlabCount(Integer slabCount) {
        this.slabCount = slabCount;
    }

    public String getSlabCrystalNo1() {
        return slabCrystalNo1;
    }

    public void setSlabCrystalNo1(String slabCrystalNo1) {
        this.slabCrystalNo1 = slabCrystalNo1;
    }

    public String getSlabCrystalNo2() {
        return slabCrystalNo2;
    }

    public void setSlabCrystalNo2(String slabCrystalNo2) {
        this.slabCrystalNo2 = slabCrystalNo2;
    }

    public Float getSlabTundishPouring() {
        return slabTundishPouring;
    }

    public void setSlabTundishPouring(Float slabTundishPouring) {
        this.slabTundishPouring = slabTundishPouring;
    }

    public Float getSlabProcessTossing() {
        return slabProcessTossing;
    }

    public void setSlabProcessTossing(Float slabProcessTossing) {
        this.slabProcessTossing = slabProcessTossing;
    }

    public Float getSlabHeadTrimAmt() {
        return slabHeadTrimAmt;
    }

    public void setSlabHeadTrimAmt(Float slabHeadTrimAmt) {
        this.slabHeadTrimAmt = slabHeadTrimAmt;
    }

    public Float getSlabTailTrimAmt() {
        return slabTailTrimAmt;
    }

    public void setSlabTailTrimAmt(Float slabTailTrimAmt) {
        this.slabTailTrimAmt = slabTailTrimAmt;
    }

    public Float getSlabTundishMargin() {
        return slabTundishMargin;
    }

    public void setSlabTundishMargin(Float slabTundishMargin) {
        this.slabTundishMargin = slabTundishMargin;
    }

    public Date getLongReplaceTime() {
        return longReplaceTime;
    }

    public void setLongReplaceTime(Date longReplaceTime) {
        this.longReplaceTime = longReplaceTime;
    }

    public Date getInfuseReplaceTime() {
        return infuseReplaceTime;
    }

    public void setInfuseReplaceTime(Date infuseReplaceTime) {
        this.infuseReplaceTime = infuseReplaceTime;
    }

    public String getRollGap() {
        return rollGap;
    }

    public void setRollGap(String rollGap) {
        this.rollGap = rollGap;
    }

    public String getSlabTemperature() {
        return slabTemperature;
    }

    public void setSlabTemperature(String slabTemperature) {
        this.slabTemperature = slabTemperature;
    }

    public String getSlabDeviceStatus() {
        return slabDeviceStatus;
    }

    public void setSlabDeviceStatus(String slabDeviceStatus) {
        this.slabDeviceStatus = slabDeviceStatus;
    }

    public Date getSlabPlanEnter() {
        return slabPlanEnter;
    }

    public void setSlabPlanEnter(Date slabPlanEnter) {
        this.slabPlanEnter = slabPlanEnter;
    }

    public String getLadleNo() {
        return ladleNo;
    }

    public void setLadleNo(String ladleNo) {
        this.ladleNo = ladleNo;
    }

    public String getBilletTundishNo() {
        return billetTundishNo;
    }

    public void setBilletTundishNo(String billetTundishNo) {
        this.billetTundishNo = billetTundishNo;
    }

    public String getBilletStationNo() {
        return billetStationNo;
    }

    public void setBilletStationNo(String billetStationNo) {
        this.billetStationNo = billetStationNo;
    }

    public String getTundish1Temper() {
        return tundish1Temper;
    }

    public void setTundish1Temper(String tundish1Temper) {
        this.tundish1Temper = tundish1Temper;
    }

    public String getTundish2Temper() {
        return tundish2Temper;
    }

    public void setTundish2Temper(String tundish2Temper) {
        this.tundish2Temper = tundish2Temper;
    }

    public String getBilletEnterTemper() {
        return billetEnterTemper;
    }

    public void setBilletEnterTemper(String billetEnterTemper) {
        this.billetEnterTemper = billetEnterTemper;
    }

    public String getBilletExitTemper() {
        return billetExitTemper;
    }

    public void setBilletExitTemper(String billetExitTemper) {
        this.billetExitTemper = billetExitTemper;
    }

    public String getBilletCrystalWater() {
        return billetCrystalWater;
    }

    public void setBilletCrystalWater(String billetCrystalWater) {
        this.billetCrystalWater = billetCrystalWater;
    }

    public String getBilletDifferTemper() {
        return billetDifferTemper;
    }

    public void setBilletDifferTemper(String billetDifferTemper) {
        this.billetDifferTemper = billetDifferTemper;
    }

    public String getCutLength() {
        return cutLength;
    }

    public void setCutLength(String cutLength) {
        this.cutLength = cutLength;
    }

    public String getBilletCasterStatus() {
        return billetCasterStatus;
    }

    public void setBilletCasterStatus(String billetCasterStatus) {
        this.billetCasterStatus = billetCasterStatus;
    }

    public String getBilletDrawingSpeed() {
        return billetDrawingSpeed;
    }

    public void setBilletDrawingSpeed(String billetDrawingSpeed) {
        this.billetDrawingSpeed = billetDrawingSpeed;
    }

    public String getCold2Flow1() {
        return cold2Flow1;
    }

    public void setCold2Flow1(String cold2Flow1) {
        this.cold2Flow1 = cold2Flow1;
    }

    public String getCold2Flow2() {
        return cold2Flow2;
    }

    public void setCold2Flow2(String cold2Flow2) {
        this.cold2Flow2 = cold2Flow2;
    }

    public String getCold2Flow3() {
        return cold2Flow3;
    }

    public void setCold2Flow3(String cold2Flow3) {
        this.cold2Flow3 = cold2Flow3;
    }

    public String getCold2Flow4() {
        return cold2Flow4;
    }

    public void setCold2Flow4(String cold2Flow4) {
        this.cold2Flow4 = cold2Flow4;
    }

    public String getCold2Press() {
        return cold2Press;
    }

    public void setCold2Press(String cold2Press) {
        this.cold2Press = cold2Press;
    }

    public String getBilletVibrationSlope() {
        return billetVibrationSlope;
    }

    public void setBilletVibrationSlope(String billetVibrationSlope) {
        this.billetVibrationSlope = billetVibrationSlope;
    }

    public String getBilletVibrationFrequ() {
        return billetVibrationFrequ;
    }

    public void setBilletVibrationFrequ(String billetVibrationFrequ) {
        this.billetVibrationFrequ = billetVibrationFrequ;
    }

    public String getBilletVibrationAmt() {
        return billetVibrationAmt;
    }

    public void setBilletVibrationAmt(String billetVibrationAmt) {
        this.billetVibrationAmt = billetVibrationAmt;
    }

    public String getBilletTundishCarNo() {
        return billetTundishCarNo;
    }

    public void setBilletTundishCarNo(String billetTundishCarNo) {
        this.billetTundishCarNo = billetTundishCarNo;
    }

    public String getBilletCoverCompany() {
        return billetCoverCompany;
    }

    public void setBilletCoverCompany(String billetCoverCompany) {
        this.billetCoverCompany = billetCoverCompany;
    }

    public String getBilletCoverAddAmt() {
        return billetCoverAddAmt;
    }

    public void setBilletCoverAddAmt(String billetCoverAddAmt) {
        this.billetCoverAddAmt = billetCoverAddAmt;
    }

    public String getBilletCoverType() {
        return billetCoverType;
    }

    public void setBilletCoverType(String billetCoverType) {
        this.billetCoverType = billetCoverType;
    }

    public String getBilletPowderCompany() {
        return billetPowderCompany;
    }

    public void setBilletPowderCompany(String billetPowderCompany) {
        this.billetPowderCompany = billetPowderCompany;
    }

    public String getBilletPowderType() {
        return billetPowderType;
    }

    public void setBilletPowderType(String billetPowderType) {
        this.billetPowderType = billetPowderType;
    }

    public Float getBilletPowderAddAmt() {
        return billetPowderAddAmt;
    }

    public void setBilletPowderAddAmt(Float billetPowderAddAmt) {
        this.billetPowderAddAmt = billetPowderAddAmt;
    }

    public String getBilletCrystalNo1() {
        return billetCrystalNo1;
    }

    public void setBilletCrystalNo1(String billetCrystalNo1) {
        this.billetCrystalNo1 = billetCrystalNo1;
    }

    public String getBilletCrystalNo2() {
        return billetCrystalNo2;
    }

    public void setBilletCrystalNo2(String billetCrystalNo2) {
        this.billetCrystalNo2 = billetCrystalNo2;
    }

    public String getBilletCrystalNo3() {
        return billetCrystalNo3;
    }

    public void setBilletCrystalNo3(String billetCrystalNo3) {
        this.billetCrystalNo3 = billetCrystalNo3;
    }

    public String getBilletCrystalNo4() {
        return billetCrystalNo4;
    }

    public void setBilletCrystalNo4(String billetCrystalNo4) {
        this.billetCrystalNo4 = billetCrystalNo4;
    }

    public String getBilletCrystalNo5() {
        return billetCrystalNo5;
    }

    public void setBilletCrystalNo5(String billetCrystalNo5) {
        this.billetCrystalNo5 = billetCrystalNo5;
    }

    public String getBilletCrystalNo6() {
        return billetCrystalNo6;
    }

    public void setBilletCrystalNo6(String billetCrystalNo6) {
        this.billetCrystalNo6 = billetCrystalNo6;
    }

    public String getBilletCrystalNo7() {
        return billetCrystalNo7;
    }

    public void setBilletCrystalNo7(String billetCrystalNo7) {
        this.billetCrystalNo7 = billetCrystalNo7;
    }

    public String getBilletCrystalNo8() {
        return billetCrystalNo8;
    }

    public void setBilletCrystalNo8(String billetCrystalNo8) {
        this.billetCrystalNo8 = billetCrystalNo8;
    }

    public String getBilletCrystalNo9() {
        return billetCrystalNo9;
    }

    public void setBilletCrystalNo9(String billetCrystalNo9) {
        this.billetCrystalNo9 = billetCrystalNo9;
    }

    public String getBilletCrystalNo10() {
        return billetCrystalNo10;
    }

    public void setBilletCrystalNo10(String billetCrystalNo10) {
        this.billetCrystalNo10 = billetCrystalNo10;
    }

    public String getBilletTemperature() {
        return billetTemperature;
    }

    public void setBilletTemperature(String billetTemperature) {
        this.billetTemperature = billetTemperature;
    }

    public Date getBilletEnterRotatingTime() {
        return billetEnterRotatingTime;
    }

    public void setBilletEnterRotatingTime(Date billetEnterRotatingTime) {
        this.billetEnterRotatingTime = billetEnterRotatingTime;
    }

    public Date getBilletExitRotatingTime() {
        return billetExitRotatingTime;
    }

    public void setBilletExitRotatingTime(Date billetExitRotatingTime) {
        this.billetExitRotatingTime = billetExitRotatingTime;
    }

    public Date getBilletStartCastTime() {
        return billetStartCastTime;
    }

    public void setBilletStartCastTime(Date billetStartCastTime) {
        this.billetStartCastTime = billetStartCastTime;
    }

    public Date getBilletEndCastTime() {
        return billetEndCastTime;
    }

    public void setBilletEndCastTime(Date billetEndCastTime) {
        this.billetEndCastTime = billetEndCastTime;
    }

    public Date getBilletTundishSmall() {
        return billetTundishSmall;
    }

    public void setBilletTundishSmall(Date billetTundishSmall) {
        this.billetTundishSmall = billetTundishSmall;
    }

    public Date getBilletTundishMiddle() {
        return billetTundishMiddle;
    }

    public void setBilletTundishMiddle(Date billetTundishMiddle) {
        this.billetTundishMiddle = billetTundishMiddle;
    }

    public Date getBilletTundishBigTime() {
        return billetTundishBigTime;
    }

    public void setBilletTundishBigTime(Date billetTundishBigTime) {
        this.billetTundishBigTime = billetTundishBigTime;
    }

    public Date getLongReplace() {
        return longReplace;
    }

    public void setLongReplace(Date longReplace) {
        this.longReplace = longReplace;
    }

    public Date getInfuseReplace() {
        return infuseReplace;
    }

    public void setInfuseReplace(Date infuseReplace) {
        this.infuseReplace = infuseReplace;
    }

    public String getBilletDeviceStatus() {
        return billetDeviceStatus;
    }

    public void setBilletDeviceStatus(String billetDeviceStatus) {
        this.billetDeviceStatus = billetDeviceStatus;
    }

    public Float getBilletSizing() {
        return billetSizing;
    }

    public void setBilletSizing(Float billetSizing) {
        this.billetSizing = billetSizing;
    }

    public Float getBaleWeigh1() {
        return baleWeigh1;
    }

    public void setBaleWeigh1(Float baleWeigh1) {
        this.baleWeigh1 = baleWeigh1;
    }

    public Float getBaleWeigh2() {
        return baleWeigh2;
    }

    public void setBaleWeigh2(Float baleWeigh2) {
        this.baleWeigh2 = baleWeigh2;
    }

    public Float getBilletTundishWeigh() {
        return billetTundishWeigh;
    }

    public void setBilletTundishWeigh(Float billetTundishWeigh) {
        this.billetTundishWeigh = billetTundishWeigh;
    }

    public Float getBilletLiquidusTemper() {
        return billetLiquidusTemper;
    }

    public void setBilletLiquidusTemper(Float billetLiquidusTemper) {
        this.billetLiquidusTemper = billetLiquidusTemper;
    }

    public Float getBilletOverHeat() {
        return billetOverHeat;
    }

    public void setBilletOverHeat(Float billetOverHeat) {
        this.billetOverHeat = billetOverHeat;
    }

    public String getBilletCrystalPress() {
        return billetCrystalPress;
    }

    public void setBilletCrystalPress(String billetCrystalPress) {
        this.billetCrystalPress = billetCrystalPress;
    }

    public String getBilletCold2Press() {
        return billetCold2Press;
    }

    public void setBilletCold2Press(String billetCold2Press) {
        this.billetCold2Press = billetCold2Press;
    }

    public String getEquipPress() {
        return equipPress;
    }

    public void setEquipPress(String equipPress) {
        this.equipPress = equipPress;
    }

    public Float getCrystalWaterTemper() {
        return crystalWaterTemper;
    }

    public void setCrystalWaterTemper(Float crystalWaterTemper) {
        this.crystalWaterTemper = crystalWaterTemper;
    }

    public String getBilletLiquidLevel() {
        return billetLiquidLevel;
    }

    public void setBilletLiquidLevel(String billetLiquidLevel) {
        this.billetLiquidLevel = billetLiquidLevel;
    }

    public String getBilletStraightPress() {
        return billetStraightPress;
    }

    public void setBilletStraightPress(String billetStraightPress) {
        this.billetStraightPress = billetStraightPress;
    }

    public Float getBilletCold2ControlWay() {
        return billetCold2ControlWay;
    }

    public void setBilletCold2ControlWay(Float billetCold2ControlWay) {
        this.billetCold2ControlWay = billetCold2ControlWay;
    }

    public String getBilletCastFlow() {
        return billetCastFlow;
    }

    public void setBilletCastFlow(String billetCastFlow) {
        this.billetCastFlow = billetCastFlow;
    }

    public String getBilletCastPress() {
        return billetCastPress;
    }

    public void setBilletCastPress(String billetCastPress) {
        this.billetCastPress = billetCastPress;
    }

    public Float getBilletTundishGasPress() {
        return billetTundishGasPress;
    }

    public void setBilletTundishGasPress(Float billetTundishGasPress) {
        this.billetTundishGasPress = billetTundishGasPress;
    }

    public Float getBilletTundishPouring() {
        return billetTundishPouring;
    }

    public void setBilletTundishPouring(Float billetTundishPouring) {
        this.billetTundishPouring = billetTundishPouring;
    }

    public Float getBilletProcessTossing() {
        return billetProcessTossing;
    }

    public void setBilletProcessTossing(Float billetProcessTossing) {
        this.billetProcessTossing = billetProcessTossing;
    }

    public Float getBilletHeadTrimAmt() {
        return billetHeadTrimAmt;
    }

    public void setBilletHeadTrimAmt(Float billetHeadTrimAmt) {
        this.billetHeadTrimAmt = billetHeadTrimAmt;
    }

    public Float getBilletTailTrimAmt() {
        return billetTailTrimAmt;
    }

    public void setBilletTailTrimAmt(Float billetTailTrimAmt) {
        this.billetTailTrimAmt = billetTailTrimAmt;
    }

    public Float getBilletTundishMargin() {
        return billetTundishMargin;
    }

    public void setBilletTundishMargin(Float billetTundishMargin) {
        this.billetTundishMargin = billetTundishMargin;
    }

    public Integer getBilletCount() {
        return billetCount;
    }

    public void setBilletCount(Integer billetCount) {
        this.billetCount = billetCount;
    }

    public String getBilletLadleSelfOpen() {
        return billetLadleSelfOpen;
    }

    public void setBilletLadleSelfOpen(String billetLadleSelfOpen) {
        this.billetLadleSelfOpen = billetLadleSelfOpen;
    }

    public String getBilletSteelWaterPour() {
        return billetSteelWaterPour;
    }

    public void setBilletSteelWaterPour(String billetSteelWaterPour) {
        this.billetSteelWaterPour = billetSteelWaterPour;
    }

    public Date getBilletPlanEnter() {
        return billetPlanEnter;
    }

    public void setBilletPlanEnter(Date billetPlanEnter) {
        this.billetPlanEnter = billetPlanEnter;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }
}
