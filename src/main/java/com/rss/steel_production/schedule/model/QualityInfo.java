package com.rss.steel_production.schedule.model;

import java.util.Date;
import javax.persistence.*;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quality_info")
public class QualityInfo {
    @Id
    private String quality_infoUID;
    private String stationNo;
    private Date sampleTime;
    private String chargeNo;
    private Float Fe;
    private Float C;
    private Float Si;
    private Float Mn;
    private Float P;
    private Float S;
    private Float Ni;
    private Float Cr;
    private Float V;
    private Float Al;
    private Float Ca;
    private Float Cu;
    private Float Ti;
    private Float Ceq;

    public String getQuality_infoUID() {
        return quality_infoUID;
    }

    public void setQuality_infoUID(String quality_infoUID) {
        this.quality_infoUID = quality_infoUID;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public Date getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public Float getFe() {
        return Fe;
    }

    public void setFe(Float fe) {
        Fe = fe;
    }

    public Float getC() {
        return C;
    }

    public void setC(Float c) {
        C = c;
    }

    public Float getSi() {
        return Si;
    }

    public void setSi(Float si) {
        Si = si;
    }

    public Float getMn() {
        return Mn;
    }

    public void setMn(Float mn) {
        Mn = mn;
    }

    public Float getP() {
        return P;
    }

    public void setP(Float p) {
        P = p;
    }

    public Float getS() {
        return S;
    }

    public void setS(Float s) {
        S = s;
    }

    public Float getNi() {
        return Ni;
    }

    public void setNi(Float ni) {
        Ni = ni;
    }

    public Float getCr() {
        return Cr;
    }

    public void setCr(Float cr) {
        Cr = cr;
    }

    public Float getV() {
        return V;
    }

    public void setV(Float v) {
        V = v;
    }

    public Float getAl() {
        return Al;
    }

    public void setAl(Float al) {
        Al = al;
    }

    public Float getCa() {
        return Ca;
    }

    public void setCa(Float ca) {
        Ca = ca;
    }

    public Float getCu() {
        return Cu;
    }

    public void setCu(Float cu) {
        Cu = cu;
    }

    public Float getTi() {
        return Ti;
    }

    public void setTi(Float ti) {
        Ti = ti;
    }

    public Float getCeq() {
        return Ceq;
    }

    public void setCeq(Float ceq) {
        Ceq = ceq;
    }
}

