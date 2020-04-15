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
@Table(name = "steel_composition")
public class CompositionInfo {

    @Id
    private String steel_compositionUID;
    private String sampleCode;
    private String chargeNo;
    private String blastNo;
    private String sampleType;
    private Date sampleTime;
    private Float ratioFe;
    private Float ratioC;
    private Float ratioSi;
    private Float ratioMn;
    private Float ratioP;
    private Float ratioS;
    private Float ratioNi;
    private Float ratioCr;
    private Float ratioV;
    private Float ratioAl;
    private Float ratioAl_Sol;
    private Float ratioCa;
    private Float ratioCu;
    private Float ratioTi;
    private Float ratioCeq;
    private String acquisitionTime;
    private String acquireTime;

    public String getSteel_compositionUID() {
        return steel_compositionUID;
    }

    public void setSteel_compositionUID(String steel_compositionUID) {
        this.steel_compositionUID = steel_compositionUID;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public String getBlastNo() {
        return blastNo;
    }

    public void setBlastNo(String blastNo) {
        this.blastNo = blastNo;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Date getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }

    public Float getRatioFe() {
        return ratioFe;
    }

    public void setRatioFe(Float ratioFe) {
        this.ratioFe = ratioFe;
    }

    public Float getRatioC() {
        return ratioC;
    }

    public void setRatioC(Float ratioC) {
        this.ratioC = ratioC;
    }

    public Float getRatioSi() {
        return ratioSi;
    }

    public void setRatioSi(Float ratioSi) {
        this.ratioSi = ratioSi;
    }

    public Float getRatioMn() {
        return ratioMn;
    }

    public void setRatioMn(Float ratioMn) {
        this.ratioMn = ratioMn;
    }

    public Float getRatioP() {
        return ratioP;
    }

    public void setRatioP(Float ratioP) {
        this.ratioP = ratioP;
    }

    public Float getRatioS() {
        return ratioS;
    }

    public void setRatioS(Float ratioS) {
        this.ratioS = ratioS;
    }

    public Float getRatioNi() {
        return ratioNi;
    }

    public void setRatioNi(Float ratioNi) {
        this.ratioNi = ratioNi;
    }

    public Float getRatioCr() {
        return ratioCr;
    }

    public void setRatioCr(Float ratioCr) {
        this.ratioCr = ratioCr;
    }

    public Float getRatioV() {
        return ratioV;
    }

    public void setRatioV(Float ratioV) {
        this.ratioV = ratioV;
    }

    public Float getRatioAl() {
        return ratioAl;
    }

    public void setRatioAl(Float ratioAl) {
        this.ratioAl = ratioAl;
    }

    public Float getRatioAl_Sol() {
        return ratioAl_Sol;
    }

    public void setRatioAl_Sol(Float ratioAl_Sol) {
        this.ratioAl_Sol = ratioAl_Sol;
    }

    public Float getRatioCa() {
        return ratioCa;
    }

    public void setRatioCa(Float ratioCa) {
        this.ratioCa = ratioCa;
    }

    public Float getRatioCu() {
        return ratioCu;
    }

    public void setRatioCu(Float ratioCu) {
        this.ratioCu = ratioCu;
    }

    public Float getRatioTi() {
        return ratioTi;
    }

    public void setRatioTi(Float ratioTi) {
        this.ratioTi = ratioTi;
    }

    public Float getRatioCeq() {
        return ratioCeq;
    }

    public void setRatioCeq(Float ratioCeq) {
        this.ratioCeq = ratioCeq;
    }

    public String getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(String acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public String getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(String acquireTime) {
        this.acquireTime = acquireTime;
    }
}
