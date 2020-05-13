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
@Table(name = "steel_schedule")
public class SteelSchedule {
	@Id
	private String steel_scheduleUID;
	private String chargeNo;
	private String castNo;
	private String castSeq;
	private String stationName;
	private Date planEnter;
	private Date planExit;
	private Date actualEnter;
	private Date actualExit;
	private String planStatus;
	private Date approveTime;
	private String approveStaff;
	private Date prepareTime;
	private String prepareStaff;
	private String lastModifyUser;
	private Date lastModifyDate;
	private String ironNo;
	private String ironSeq;
	private String remarks;
	private String weight;
	private String temperature;
	public String getSteel_scheduleUID() {
		return steel_scheduleUID;
	}
	public void setSteel_scheduleUID(String steel_scheduleUID) {
		this.steel_scheduleUID = steel_scheduleUID;
	}
	public String getChargeNo() {
		return chargeNo;
	}
	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
	}
	public String getCastNo() {
		return castNo;
	}
	public void setCastNo(String castNo) {
		this.castNo = castNo;
	}
	public String getCastSeq() {
		return castSeq;
	}
	public void setCastSeq(String castSeq) {
		this.castSeq = castSeq;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Date getPlanEnter() {
		return planEnter;
	}
	public void setPlanEnter(Date planEnter) {
		this.planEnter = planEnter;
	}
	public Date getPlanExit() {
		return planExit;
	}
	public void setPlanExit(Date planExit) {
		this.planExit = planExit;
	}
	public Date getActualEnter() {
		return actualEnter;
	}
	public void setActualEnter(Date actualEnter) {
		this.actualEnter = actualEnter;
	}
	public Date getActualExit() {
		return actualExit;
	}
	public void setActualExit(Date actualExit) {
		this.actualExit = actualExit;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
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
	}

