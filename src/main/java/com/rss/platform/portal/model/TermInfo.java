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
@Table(name = "system_param")
public class TermInfo {
	@Id
	private String system_paramUID;
	private String appName;
	private String paramName;
	private String paramValues;
	private String lastModifyUser;
	private Date lastModifyDate;
	private String paramType;

	public String getSystem_paramUID() {
		return system_paramUID;
	}

	public void setSystem_paramUID(String system_paramUID) {
		this.system_paramUID = system_paramUID;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValues() {
		return paramValues;
	}

	public void setParamValues(String paramValues) {
		this.paramValues = paramValues;
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

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
}
