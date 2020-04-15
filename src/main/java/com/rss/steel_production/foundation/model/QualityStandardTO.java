package com.rss.steel_production.foundation.model;
public class QualityStandardTO{
	private int pageNo=0;
	private int pageSize=0;
	private QualityStandard qualityStandard;
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
	public QualityStandard getQualityStandard() {
		return qualityStandard;
	}
	public void setQualityStandard(QualityStandard qualityStandard) {
		this.qualityStandard=qualityStandard;
	}
}