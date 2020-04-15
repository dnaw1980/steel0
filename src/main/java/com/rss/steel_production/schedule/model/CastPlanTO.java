package com.rss.steel_production.schedule.model;
public class CastPlanTO{
	private int pageNo=0;
	private int pageSize=0;
	private CastPlan castPlan;
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
	public CastPlan getCastPlan() {
		return castPlan;
	}
	public void setCastPlan(CastPlan castPlan) {
		this.castPlan=castPlan;
	}
}