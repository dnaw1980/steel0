package com.rss.platform.portal.model;
public class RoleInfoTO{
	private int pageNo=0;
	private int pageSize=0;
	private RoleInfo roleInfo;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo=pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize=pageSize;
	}
	public RoleInfo getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo=roleInfo;
	}
}