package com.rss.steel_production.process.model;

import java.util.Date;
import javax.persistence.*;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "process_quality")
public class ProductQuality {
	@Id
	private String process_qualityUID;
	private String sampleCode;
	private String sampleName;
	private String sampleType;
	private Date sampleTime;
	private String stationName;
	private String productionNo;
	private String evaluResult;
	private String lastModifyUser;
	private Date lastModifyDate;

	public String getProcess_qualityUID() {
		return process_qualityUID;
	}

	public void setProcess_qualityUID(String process_qualityUID) {
		this.process_qualityUID = process_qualityUID;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
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

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getProductionNo() {
		return productionNo;
	}

	public void setProductionNo(String productionNo) {
		this.productionNo = productionNo;
	}

	public String getEvaluResult() {
		return evaluResult;
	}

	public void setEvaluResult(String evaluResult) {
		this.evaluResult = evaluResult;
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

