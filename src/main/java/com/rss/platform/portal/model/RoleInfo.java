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
@Table(name = "role_info")
public class RoleInfo {
	@Id
	private String role_infoUID;
	private String roleID;
	private String roleName;
	private String appID;
	private String appName;
	private String permissionSet;
	private String permission;
	private String tableContent;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getRole_infoUID() {
		return role_infoUID;
	}
	public void setRole_infoUID(String role_infoUID) {
		this.role_infoUID = role_infoUID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getPermissionSet() {
		return permissionSet;
	}
	public void setPermissionSet(String permissionSet) {
		this.permissionSet = permissionSet;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getTableContent() {
		return tableContent;
	}

	public void setTableContent(String tableContent) {
		this.tableContent = tableContent;
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
