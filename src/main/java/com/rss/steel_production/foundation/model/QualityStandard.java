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
@Table(name = "quality_standard")
public class QualityStandard {
	@Id
	private String quality_standardUID;
	private String standardNo;
	private String standardType;
//	@Id
	private String productCode;
	private String productName;
	private String qualityLevel;
	private String qualityIndexes;
	private String criteriaRule;
	private String remark;
	private String validPeriod;
	private String standardSatus;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getQuality_standardUID() {
		return quality_standardUID;
	}
	public void setQuality_standardUID(String quality_standardUID) {
		this.quality_standardUID = quality_standardUID;
	}
	public String getStandardNo() {
		return standardNo;
	}
	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}
	public String getStandardType() {
		return standardType;
	}
	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQualityLevel() {
		return qualityLevel;
	}
	public void setQualityLevel(String qualityLevel) {
		this.qualityLevel = qualityLevel;
	}
	public String getQualityIndexes() {
		return qualityIndexes;
	}
	public void setQualityIndexes(String qualityIndexes) {
		this.qualityIndexes = qualityIndexes;
	}
	public String getCriteriaRule() {
		return criteriaRule;
	}
	public void setCriteriaRule(String criteriaRule) {
		this.criteriaRule = criteriaRule;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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

