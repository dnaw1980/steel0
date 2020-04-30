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

    private Date exitRotating;

    private Date startCast;

    private Date endCast;

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
    private String billetCount;
    private String tundishPouring;
    private String processTossing;
    private String headTrimAmt;
    private String tailTrimAmt;
    private String tundishMargin;
    private Date longReplaceTime;
    private Date infuseReplaceTime;
    private String casterStatus;
    private String crystalNo1;
    private String crystalNo2;
    private String crystalNo3;
    private String crystalNo4;
    private String crystalNo5;
    private String crystalNo6;
    private String crystalNo7;
    private String crystalNo8;
    private String crystalNo9;
    private String crystalNo10;
    private Date acquireTime;

    public String getBillet_productionUID() {
        return billet_productionUID;
    }

    public void setBillet_productionUID(String billet_productionUID) {
        this.billet_productionUID = billet_productionUID;
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

    public String getBaleWeigh1() {
        return baleWeigh1;
    }

    public void setBaleWeigh1(String baleWeigh1) {
        this.baleWeigh1 = baleWeigh1;
    }

    public String getBaleWeigh2() {
        return baleWeigh2;
    }

    public void setBaleWeigh2(String baleWeigh2) {
        this.baleWeigh2 = baleWeigh2;
    }

    public String getTundishTemperature1() {
        return tundishTemperature1;
    }

    public void setTundishTemperature1(String tundishTemperature1) {
        this.tundishTemperature1 = tundishTemperature1;
    }

    public String getTundishContinuous1() {
        return tundishContinuous1;
    }

    public void setTundishContinuous1(String tundishContinuous1) {
        this.tundishContinuous1 = tundishContinuous1;
    }

    public String getTundishTemperature2() {
        return tundishTemperature2;
    }

    public void setTundishTemperature2(String tundishTemperature2) {
        this.tundishTemperature2 = tundishTemperature2;
    }

    public String getTundishContinuous2() {
        return tundishContinuous2;
    }

    public void setTundishContinuous2(String tundishContinuous2) {
        this.tundishContinuous2 = tundishContinuous2;
    }

    public String getDrawingSpeed01() {
        return drawingSpeed01;
    }

    public void setDrawingSpeed01(String drawingSpeed01) {
        this.drawingSpeed01 = drawingSpeed01;
    }

    public Integer getPouringState01() {
        return pouringState01;
    }

    public void setPouringState01(Integer pouringState01) {
        this.pouringState01 = pouringState01;
    }

    public String getDrawingSpeed02() {
        return drawingSpeed02;
    }

    public void setDrawingSpeed02(String drawingSpeed02) {
        this.drawingSpeed02 = drawingSpeed02;
    }

    public Integer getPouringState02() {
        return pouringState02;
    }

    public void setPouringState02(Integer pouringState02) {
        this.pouringState02 = pouringState02;
    }

    public String getDrawingSpeed03() {
        return drawingSpeed03;
    }

    public void setDrawingSpeed03(String drawingSpeed03) {
        this.drawingSpeed03 = drawingSpeed03;
    }

    public Integer getPouringState03() {
        return pouringState03;
    }

    public void setPouringState03(Integer pouringState03) {
        this.pouringState03 = pouringState03;
    }

    public String getDrawingSpeed04() {
        return drawingSpeed04;
    }

    public void setDrawingSpeed04(String drawingSpeed04) {
        this.drawingSpeed04 = drawingSpeed04;
    }

    public Integer getPouringState04() {
        return pouringState04;
    }

    public void setPouringState04(Integer pouringState04) {
        this.pouringState04 = pouringState04;
    }

    public String getDrawingSpeed05() {
        return drawingSpeed05;
    }

    public void setDrawingSpeed05(String drawingSpeed05) {
        this.drawingSpeed05 = drawingSpeed05;
    }

    public Integer getPouringState05() {
        return pouringState05;
    }

    public void setPouringState05(Integer pouringState05) {
        this.pouringState05 = pouringState05;
    }

    public String getDrawingSpeed06() {
        return drawingSpeed06;
    }

    public void setDrawingSpeed06(String drawingSpeed06) {
        this.drawingSpeed06 = drawingSpeed06;
    }

    public Integer getPouringState06() {
        return pouringState06;
    }

    public void setPouringState06(Integer pouringState06) {
        this.pouringState06 = pouringState06;
    }

    public String getDrawingSpeed07() {
        return drawingSpeed07;
    }

    public void setDrawingSpeed07(String drawingSpeed07) {
        this.drawingSpeed07 = drawingSpeed07;
    }

    public Integer getPouringState07() {
        return pouringState07;
    }

    public void setPouringState07(Integer pouringState07) {
        this.pouringState07 = pouringState07;
    }

    public String getDrawingSpeed08() {
        return drawingSpeed08;
    }

    public void setDrawingSpeed08(String drawingSpeed08) {
        this.drawingSpeed08 = drawingSpeed08;
    }

    public Integer getPouringState08() {
        return pouringState08;
    }

    public void setPouringState08(Integer pouringState08) {
        this.pouringState08 = pouringState08;
    }

    public String getDrawingSpeed09() {
        return drawingSpeed09;
    }

    public void setDrawingSpeed09(String drawingSpeed09) {
        this.drawingSpeed09 = drawingSpeed09;
    }

    public Integer getPouringState09() {
        return pouringState09;
    }

    public void setPouringState09(Integer pouringState09) {
        this.pouringState09 = pouringState09;
    }

    public String getDrawingSpeed10() {
        return drawingSpeed10;
    }

    public void setDrawingSpeed10(String drawingSpeed10) {
        this.drawingSpeed10 = drawingSpeed10;
    }

    public Integer getPouringState10() {
        return pouringState10;
    }

    public void setPouringState10(Integer pouringState10) {
        this.pouringState10 = pouringState10;
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

    public String getBilletCount() {
        return billetCount;
    }

    public void setBilletCount(String billetCount) {
        this.billetCount = billetCount;
    }

    public String getTundishPouring() {
        return tundishPouring;
    }

    public void setTundishPouring(String tundishPouring) {
        this.tundishPouring = tundishPouring;
    }

    public String getProcessTossing() {
        return processTossing;
    }

    public void setProcessTossing(String processTossing) {
        this.processTossing = processTossing;
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
        return  crystalNo1;
    }

    public void setCrystalNo1(String crystalNo1) {
        this. crystalNo1 =  crystalNo1;
    }

    public String getCrystalNo2() {
        return  crystalNo2;
    }

    public void setCrystalNo2(String crystalNo2) {
        this. crystalNo2 =  crystalNo2;
    }

    public String getCrystalNo3() {
        return  crystalNo3;
    }

    public void setCrystalNo3(String crystalNo3) {
        this. crystalNo3 =  crystalNo3;
    }

    public String getCrystalNo4() {
        return  crystalNo4;
    }

    public void setCrystalNo4(String crystalNo4) {
        this. crystalNo4 =  crystalNo4;
    }

    public String getCrystalNo5() {
        return  crystalNo5;
    }

    public void setCrystalNo5(String crystalNo5) {
        this. crystalNo5 =  crystalNo5;
    }

    public String getCrystalNo6() {
        return  crystalNo6;
    }

    public void setCrystalNo6(String crystalNo6) {
        this. crystalNo6 =  crystalNo6;
    }

    public String getCrystalNo7() {
        return  crystalNo7;
    }

    public void setCrystalNo7(String crystalNo7) {
        this. crystalNo7 =  crystalNo7;
    }

    public String getCrystalNo8() {
        return  crystalNo8;
    }

    public void setCrystalNo8(String crystalNo8) {
        this. crystalNo8 =  crystalNo8;
    }

    public String getCrystalNo9() {
        return  crystalNo9;
    }

    public void setCrystalNo9(String crystalNo9) {
        this. crystalNo9 =  crystalNo9;
    }

    public String getCrystalNo10() {
        return  crystalNo10;
    }

    public void setCrystalNo10(String crystalNo10) {
        this. crystalNo10 =  crystalNo10;
    }

    public Date getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(Date acquireTime) {
        this.acquireTime = acquireTime;
    }
}
