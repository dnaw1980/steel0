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
@Table(name = "iron_plan")
public class IronPlan {
	@Id
	private String iron_planUID;
	private String ironNo;
	private Integer ladleCount;
	private Date tappingStart;
	private Date tappingEnd;
	private Date prepareTime;
	private String prepareStaff;
	private Date approveTime;
	private String approveStaff;
	private String planStatus;
	private String stationName;
	private String remarks;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getIron_planUID() {
		return iron_planUID;
	}
	public void setIron_planUID(String iron_planUID) {
		this.iron_planUID = iron_planUID;
	}
	public String getIronNo() {
		return ironNo;
	}
	public void setIronNo(String ironNo) {
		this.ironNo = ironNo;
	}

	public Integer getLadleCount() {
		return ladleCount;
	}

	public void setLadleCount(Integer ladleCount) {
		this.ladleCount = ladleCount;
	}

	public Date getTappingStart() {
		return tappingStart;
	}

	public void setTappingStart(Date tappingStart) {
		this.tappingStart = tappingStart;
	}

	public Date getTappingEnd() {
		return tappingEnd;
	}

	public void setTappingEnd(Date tappingEnd) {
		this.tappingEnd = tappingEnd;
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

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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

