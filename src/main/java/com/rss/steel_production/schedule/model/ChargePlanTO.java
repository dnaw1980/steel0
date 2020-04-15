package com.rss.steel_production.schedule.model;
public class ChargePlanTO{
	private int pageNo=0;
	private int pageSize=0;
	private ChargePlan chargePlan;
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
	public ChargePlan getChargePlan() {
		return chargePlan;
	}
	public void setChargePlan(ChargePlan chargePlan) {
		this.chargePlan=chargePlan;
	}
}