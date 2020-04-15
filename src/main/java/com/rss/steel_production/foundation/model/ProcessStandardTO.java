package com.rss.steel_production.foundation.model;
public class ProcessStandardTO{
	private int pageNo=0;
	private int pageSize=0;
	private ProcessStandard processStandard;
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
	public ProcessStandard getProcessStandard() {
		return processStandard;
	}
	public void setProcessStandard(ProcessStandard processStandard) {
		this.processStandard=processStandard;
	}
}