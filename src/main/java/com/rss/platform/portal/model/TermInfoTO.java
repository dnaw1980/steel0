package com.rss.platform.portal.model;
public class TermInfoTO{
	private int pageNo=0;
	private int pageSize=0;
	private TermInfo termInfo;
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
	public TermInfo getTermInfo() {
		return termInfo;
	}
	public void setTermInfo(TermInfo termInfo) {
		this.termInfo=termInfo;
	}
}