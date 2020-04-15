package com.rss.platform.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfo {
    private String userID;
    private String userName;
    //	private String orgCode;
//	private String orgName;
    private String roleID;
    private String roleName;
    private String appID;
    private String appName;
    private String rolePermission;
    private String orgName;
    private String tableContent;

    public SessionInfo(UserInfo user) {
        this.userID = user.getUserID();
        this.userName = user.getUserName();
//		this.orgCode=user.getDeptCode();
//		this.orgName=user.getUserID();
        this.roleID = user.getRoleID();
        this.orgName = user.getOrgName();
//		this.appID=user.getAppID();
//		this.appName=user.getAppName();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//	public String getOrgCode() {
//		return orgCode;
//	}
//
//	public void setOrgCode(String orgCode) {
//		this.orgCode = orgCode;
//	}
//
//	public String getOrgName() {
//		return orgName;
//	}
//
//	public void setOrgName(String orgName) {
//		this.orgName = orgName;
//	}

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

    public String getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(String rolePermission) {
        this.rolePermission = rolePermission;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTableContent() {
        return tableContent;
    }

    public void setTableContent(String tableContent) {
        this.tableContent = tableContent;
    }
}
