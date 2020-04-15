package com.rss.steel_production.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "steel_composition_standard")
public class CompositionStandard {
    @Id
    private String steel_composition_standardUID;
    private String itemID;
    private String sampleType;
    private Float lowerLimit;
    private Float upperLimit;

    public String getSteel_composition_standardUID() {
        return steel_composition_standardUID;
    }

    public void setSteel_composition_standardUID(String steel_composition_standardUID) {
        this.steel_composition_standardUID = steel_composition_standardUID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public Float getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Float lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Float getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Float upperLimit) {
        this.upperLimit = upperLimit;
    }
}
