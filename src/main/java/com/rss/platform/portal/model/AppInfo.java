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
@Table(name = "app_info")
public class AppInfo {
	@Id
	private String app_infoUID;
	private String appID;
	private String appName;
	private String appDesc;
	private String moduleMap;
	private Date createDate;
	private String createPerson;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getApp_infoUID() {
		return app_infoUID;
	}
	public void setApp_infoUID(String app_infoUID) {
		this.app_infoUID = app_infoUID;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
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

	public String getModuleMap() {
		return moduleMap;
	}

	public void setModuleMap(String moduleMap) {
		this.moduleMap = moduleMap;
	}
}
