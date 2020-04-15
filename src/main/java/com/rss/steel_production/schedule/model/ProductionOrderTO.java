package com.rss.steel_production.schedule.model;
public class ProductionOrderTO{
	private int pageNo=0;
	private int pageSize=0;
	private ProductionOrder productionOrder;
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
	public ProductionOrder getProductionOrder() {
		return productionOrder;
	}
	public void setProductionOrder(ProductionOrder productionOrder) {
		this.productionOrder=productionOrder;
	}
}