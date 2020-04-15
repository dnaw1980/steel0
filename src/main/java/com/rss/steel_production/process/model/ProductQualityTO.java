package com.rss.steel_production.process.model;
public class ProductQualityTO{
	private int pageNo=0;
	private int pageSize=0;
	private ProductQuality productQuality;
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
	public ProductQuality getProductQuality() {
		return productQuality;
	}
	public void setProductQuality(ProductQuality productQuality) {
		this.productQuality=productQuality;
	}
}