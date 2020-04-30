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
@Table(name = "slab_production2")
public class SlabProduction2 {
    @Id
    private String slab_production2UID;

    private String chargeNo;

    private String temperature;

    private String weight;

    private Date enterRotating;

    private Date exitRotating;

    private Date startCast;

    private Date endCast;

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

    private String tundishNo;
    private String ladleSelfOpen;
    private String steelWaterPoure;
    private String coverCompany;
    private String coverAddAmount;
    private String coverType;
    private String powderCompany;
    private String powderType;
    private String powderAddAmount;
    private Date tundishSmallTime;
    private Date tundishMiddleTime;
    private Date tundishBigTime;
    private String tundishGasPress;
    private String slabCount;
    private String tundishPouring;
    private String headTrimAmt;
    private String tailTrimAmt;
    private String tundishMargin;
    private Date longReplaceTime;
    private Date infuseReplaceTime;
    private String casterStatus;
    private String crystalNo1;
    private String crystalNo2;

    private Date acquireTime;

    public String getSlab_production2UID() {
        return slab_production2UID;
    }

    public void setSlab_production2UID(String slab_production2UID) {
        this.slab_production2UID = slab_production2UID;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
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

    public Date getEnterRotating() {
        return enterRotating;
    }

    public void setEnterRotating(Date enterRotating) {
        this.enterRotating = enterRotating;
    }

    public Date getExitRotating() {
        return exitRotating;
    }

    public void setExitRotating(Date exitRotating) {
        this.exitRotating = exitRotating;
    }

    public Date getStartCast() {
        return startCast;
    }

    public void setStartCast(Date startCast) {
        this.startCast = startCast;
    }

    public Date getEndCast() {
        return endCast;
    }

    public void setEndCast(Date endCast) {
        this.endCast = endCast;
    }

    public String getCastNo() {
        return castNo;
    }

    public void setCastNo(String castNo) {
        this.castNo = castNo;
    }

    public String getBaleTemperature() {
        return baleTemperature;
    }

    public void setBaleTemperature(String baleTemperature) {
        this.baleTemperature = baleTemperature;
    }

    public String getBaleWeighA() {
        return baleWeighA;
    }

    public void setBaleWeighA(String baleWeighA) {
        this.baleWeighA = baleWeighA;
    }

    public String getBaleWeighB() {
        return baleWeighB;
    }

    public void setBaleWeighB(String baleWeighB) {
        this.baleWeighB = baleWeighB;
    }

    public String getTundishWeigh() {
        return tundishWeigh;
    }

    public void setTundishWeigh(String tundishWeigh) {
        this.tundishWeigh = tundishWeigh;
    }

    public String getChargeCount() {
        return chargeCount;
    }

    public void setChargeCount(String chargeCount) {
        this.chargeCount = chargeCount;
    }

    public String getTundishQuick() {
        return tundishQuick;
    }

    public void setTundishQuick(String tundishQuick) {
        this.tundishQuick = tundishQuick;
    }

    public String getTundishContinuous() {
        return tundishContinuous;
    }

    public void setTundishContinuous(String tundishContinuous) {
        this.tundishContinuous = tundishContinuous;
    }

    public String getDrawingActual1() {
        return drawingActual1;
    }

    public void setDrawingActual1(String drawingActual1) {
        this.drawingActual1 = drawingActual1;
    }

    public String getDrawingSet1() {
        return drawingSet1;
    }

    public void setDrawingSet1(String drawingSet1) {
        this.drawingSet1 = drawingSet1;
    }

    public String getPouringLength1() {
        return pouringLength1;
    }

    public void setPouringLength1(String pouringLength1) {
        this.pouringLength1 = pouringLength1;
    }

    public String getDrawingActual2() {
        return drawingActual2;
    }

    public void setDrawingActual2(String drawingActual2) {
        this.drawingActual2 = drawingActual2;
    }

    public String getDrawingSet2() {
        return drawingSet2;
    }

    public void setDrawingSet2(String drawingSet2) {
        this.drawingSet2 = drawingSet2;
    }

    public String getPouringLength2() {
        return pouringLength2;
    }

    public void setPouringLength2(String pouringLength2) {
        this.pouringLength2 = pouringLength2;
    }

    public String getTundishNo() {
        return tundishNo;
    }

    public void setTundishNo(String tundishNo) {
        this.tundishNo = tundishNo;
    }

    public String getLadleSelfOpen() {
        return ladleSelfOpen;
    }

    public void setLadleSelfOpen(String ladleSelfOpen) {
        this.ladleSelfOpen = ladleSelfOpen;
    }

    public String getSteelWaterPoure() {
        return steelWaterPoure;
    }

    public void setSteelWaterPoure(String steelWaterPoure) {
        this.steelWaterPoure = steelWaterPoure;
    }

    public String getCoverCompany() {
        return coverCompany;
    }

    public void setCoverCompany(String coverCompany) {
        this.coverCompany = coverCompany;
    }

    public String getCoverAddAmount() {
        return coverAddAmount;
    }

    public void setCoverAddAmount(String coverAddAmount) {
        this.coverAddAmount = coverAddAmount;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getPowderCompany() {
        return powderCompany;
    }

    public void setPowderCompany(String powderCompany) {
        this.powderCompany = powderCompany;
    }

    public String getPowderType() {
        return powderType;
    }

    public void setPowderType(String powderType) {
        this.powderType = powderType;
    }

    public String getPowderAddAmount() {
        return powderAddAmount;
    }

    public void setPowderAddAmount(String powderAddAmount) {
        this.powderAddAmount = powderAddAmount;
    }

    public Date getTundishSmallTime() {
        return tundishSmallTime;
    }

    public void setTundishSmallTime(Date tundishSmallTime) {
        this.tundishSmallTime = tundishSmallTime;
    }

    public Date getTundishMiddleTime() {
        return tundishMiddleTime;
    }

    public void setTundishMiddleTime(Date tundishMiddleTime) {
        this.tundishMiddleTime = tundishMiddleTime;
    }

    public Date getTundishBigTime() {
        return tundishBigTime;
    }

    public void setTundishBigTime(Date tundishBigTime) {
        this.tundishBigTime = tundishBigTime;
    }

    public String getTundishGasPress() {
        return tundishGasPress;
    }

    public void setTundishGasPress(String tundishGasPress) {
        this.tundishGasPress = tundishGasPress;
    }

    public String getSlabCount() {
        return slabCount;
    }

    public void setSlabCount(String slabCount) {
        this.slabCount = slabCount;
    }

    public String getTundishPouring() {
        return tundishPouring;
    }

    public void setTundishPouring(String tundishPouring) {
        this.tundishPouring = tundishPouring;
    }

    public String getHeadTrimAmt() {
        return headTrimAmt;
    }

    public void setHeadTrimAmt(String headTrimAmt) {
        this.headTrimAmt = headTrimAmt;
    }

    public String getTailTrimAmt() {
        return tailTrimAmt;
    }

    public void setTailTrimAmt(String tailTrimAmt) {
        this.tailTrimAmt = tailTrimAmt;
    }

    public String getTundishMargin() {
        return tundishMargin;
    }

    public void setTundishMargin(String tundishMargin) {
        this.tundishMargin = tundishMargin;
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

    public String getCasterStatus() {
        return casterStatus;
    }

    public void setCasterStatus(String casterStatus) {
        this.casterStatus = casterStatus;
    }

    public String getCrystalNo1() {
        return crystalNo1;
    }

    public void setCrystalNo1(String crystalNo1) {
        this.crystalNo1 = crystalNo1;
    }

    public String getCrystalNo2() {
        return crystalNo2;
    }

    public void setCrystalNo2(String crystalNo2) {
        this.crystalNo2 = crystalNo2;
    }

    public Date getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(Date acquireTime) {
        this.acquireTime = acquireTime;
    }
}
