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
@Table(name = "charge_plan")
public class ChargePlan {
	@Id
	private String charge_planUID;
	private String chargeNo;
	private Date planTime;
	private Date prepareTime;
	private String prepareStaff;
	private Date approveTime;
	private String approveStaff;
	private String orderList;
	private String steelGrade;
	private String productSpec;
	private String productLevel;
	private String technicalStandard;
	private String billetLength;
	private String billetWidth;
	private String billetThick;
	private Double targetWeight;
	private Double targetQuantity;
	private String targetTime;
	private String processRoute;
	private String assignCast;
	private String castSeq;
	private String ironNo;
	private String ironSeq;
	private Date ironTime;
	private String planStatus;
	private String remarks;
	private String lastModifyUser;
	private Date lastModifyDate;

	public String getCharge_planUID() {
		return charge_planUID;
	}

	public void setCharge_planUID(String charge_planUID) {
		this.charge_planUID = charge_planUID;
	}

	public String getChargeNo() {
		return chargeNo;
	}

	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
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

	public String getOrderList() {
		return orderList;
	}

	public void setOrderList(String orderList) {
		this.orderList = orderList;
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

	public Double getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(Double targetWeight) {
		this.targetWeight = targetWeight;
	}

	public Double getTargetQuantity() {
		return targetQuantity;
	}

	public void setTargetQuantity(Double targetQuantity) {
		this.targetQuantity = targetQuantity;
	}

	public String getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(String targetTime) {
		this.targetTime = targetTime;
	}

	public String getProcessRoute() {
		return processRoute;
	}

	public void setProcessRoute(String processRoute) {
		this.processRoute = processRoute;
	}

	public String getAssignCast() {
		return assignCast;
	}

	public void setAssignCast(String assignCast) {
		this.assignCast = assignCast;
	}

	public String getCastSeq() {
		return castSeq;
	}

	public void setCastSeq(String castSeq) {
		this.castSeq = castSeq;
	}

	public String getIronNo() {
		return ironNo;
	}

	public void setIronNo(String ironNo) {
		this.ironNo = ironNo;
	}

	public String getIronSeq() {
		return ironSeq;
	}

	public void setIronSeq(String ironSeq) {
		this.ironSeq = ironSeq;
	}

	public Date getIronTime() {
		return ironTime;
	}

	public void setIronTime(Date ironTime) {
		this.ironTime = ironTime;
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

