package com.rss.steel_production.foundation.model;
public class SteelProductTO{
	private int pageNo=0;
	private int pageSize=0;
	private SteelProduct steelProduct;
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
	public SteelProduct getSteelProduct() {
		return steelProduct;
	}
	public void setSteelProduct(SteelProduct steelProduct) {
		this.steelProduct=steelProduct;
	}
}