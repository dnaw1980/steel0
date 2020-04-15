package com.rss.steel_production.foundation.model;

import java.util.Date;
import javax.persistence.*;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "process_standard")
public class ProcessStandard {
	@Id
	private String process_standardeUID;
	private String standardNo;
	private String itemID;
	private String itemName;
	private String itemType;
	private String steelGrade;
	private String productSpec;
	private String measureUnit;
	private Double standardValue;
	private Double lowerLimit;
	private Double upperLimit;
	private String validPeriod;
	private String standardSatus;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getProcess_standardeUID() {
		return process_standardeUID;
	}
	public void setProcess_standardeUID(String process_standardeUID) {
		this.process_standardeUID = process_standardeUID;
	}
	public String getStandardNo() {
		return standardNo;
	}
	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSteelGrade() {
		return steelGrade;
	}
	public void setSteelGrade(String steelGrade) {
		this.steelGrade = steelGrade;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}
	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}
	public Double getStandardValue() {
		return standardValue;
	}
	public void setStandardValue(Double standardValue) {
		this.standardValue = standardValue;
	}
	public Double getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public Double getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}
	public String getStandardSatus() {
		return standardSatus;
	}
	public void setStandardSatus(String standardSatus) {
		this.standardSatus = standardSatus;
	}
	public String getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	}

