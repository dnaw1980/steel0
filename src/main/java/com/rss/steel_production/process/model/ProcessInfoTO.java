package com.rss.steel_production.process.model;
public class ProcessInfoTO{
	private int pageNo=0;
	private int pageSize=0;
	private ProcessInfo processInfo;
	private String scheduleStatusNot;
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
	public ProcessInfo getProcessInfo() {
		return processInfo;
	}
	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo=processInfo;
	}

	public String getScheduleStatusNot() {
		return scheduleStatusNot;
	}

	public void setScheduleStatusNot(String scheduleStatusNot) {
		this.scheduleStatusNot = scheduleStatusNot;
	}
}
