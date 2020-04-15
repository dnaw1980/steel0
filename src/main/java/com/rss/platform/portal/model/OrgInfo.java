package com.rss.platform.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "org_info")
public class OrgInfo {
	private String orgCode;
	private String orgName;
	private String orgType;
	private String adminArea;
	private String orgAddress;
	private String orgDesc;
	private Integer staffCount;
	private String contactDetail;
	@Id
	private String org_infoUID;
	private String registerStaff;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getAdminArea() {
		return adminArea;
	}
	public void setAdminArea(String adminArea) {
		this.adminArea = adminArea;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	public Integer getStaffCount() {
		return staffCount;
	}
	public void setStaffCount(Integer staffCount) {
		this.staffCount = staffCount;
	}
	public String getContactDetail() {
		return contactDetail;
	}
	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}
	public String getOrg_infoUID() {
		return org_infoUID;
	}
	public void setOrg_infoUID(String org_infoUID) {
		this.org_infoUID = org_infoUID;
	}
	public String getRegisterStaff() {
		return registerStaff;
	}
	public void setRegisterStaff(String registerStaff) {
		this.registerStaff = registerStaff;
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
