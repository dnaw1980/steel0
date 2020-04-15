package com.rss.steel_production.schedule.model;
public class SteelScheduleTO{
	private int pageNo=0;
	private int pageSize=0;
	private SteelSchedule steelSchedule;
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
	public SteelSchedule getSteelSchedule() {
		return steelSchedule;
	}
	public void setSteelSchedule(SteelSchedule steelSchedule) {
		this.steelSchedule=steelSchedule;
	}
}