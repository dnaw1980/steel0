package com.rss.steel_production.schedule.model;
public class IronPlanTO{
	private int pageNo=0;
	private int pageSize=0;
	private IronPlan ironPlan;
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
	public IronPlan getIronPlan() {
		return ironPlan;
	}
	public void setIronPlan(IronPlan ironPlan) {
		this.ironPlan=ironPlan;
	}
}