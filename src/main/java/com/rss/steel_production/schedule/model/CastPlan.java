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
@Table(name = "cast_plan")
public class CastPlan {
	@Id
	private String cast_planUID;
	private String castNo;
	private Date planTime;
	private Date prepareTime;
	private String prepareStaff;
	private Date approveTime;
	private String approveStaff;
	private String chargeList;
	private String steelGrade;
	private String productLevel;
	private String technicalStandard;
	private String productSpec;
	private int chargeNum;
	private int continueTime;
	private String billetLength;
	private String billetWidth;
	private String billetThick;
	private Double targetWeight;
	private String targetTime;
	private Double targetQuantity;
	private String planStatus;
	private String remarks;
	private String lastModifyUser;
	private Date lastModifyDate;

	public String getCast_planUID() {
		return cast_planUID;
	}

	public void setCast_planUID(String cast_planUID) {
		this.cast_planUID = cast_planUID;
	}

	public String getCastNo() {
		return castNo;
	}

	public void setCastNo(String castNo) {
		this.castNo = castNo;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public Date getPrepareTime() {
		return prepareTime;
	}

	public void setPrepareTime(Date prepareTime) {
		this.prepareTime = prepareTime;
	}

	public String getPrepareStaff() {
		return prepareStaff;
	}

	public void setPrepareStaff(String prepareStaff) {
		this.prepareStaff = prepareStaff;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproveStaff() {
		return approveStaff;
	}

	public void setApproveStaff(String approveStaff) {
		this.approveStaff = approveStaff;
	}

	public String getChargeList() {
		return chargeList;
	}

	public void setChargeList(String chargeList) {
		this.chargeList = chargeList;
	}

	public String getSteelGrade() {
		return steelGrade;
	}

	public void setSteelGrade(String steelGrade) {
		this.steelGrade = steelGrade;
	}

	public int getChargeNum() {
		return chargeNum;
	}

	public void setChargeNum(int chargeNum) {
		this.chargeNum = chargeNum;
	}

	public int getContinueTime() {
		return continueTime;
	}

	public void setContinueTime(int continueTime) {
		this.continueTime = continueTime;
	}

	public String getProductLevel() {
		return productLevel;
	}

	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}

	public String getTechnicalStandard() {
		return technicalStandard;
	}

	public void setTechnicalStandard(String technicalStandard) {
		this.technicalStandard = technicalStandard;
	}

	public String getBilletLength() {
		return billetLength;
	}

	public void setBilletLength(String billetLength) {
		this.billetLength = billetLength;
	}

	public String getBilletWidth() {
		return billetWidth;
	}

	public void setBilletWidth(String billetWidth) {
		this.billetWidth = billetWidth;
	}

	public String getBilletThick() {
		return billetThick;
	}

	public void setBilletThick(String billetThick) {
		this.billetThick = billetThick;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}



	public Double getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(Double targetWeight) {
		this.targetWeight = targetWeight;
	}

	public String getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}

	public Double getTargetQuantity() {
		return targetQuantity;
	}

	public void setTargetQuantity(Double targetQuantity) {
		this.targetQuantity = targetQuantity;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

