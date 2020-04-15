package com.rss.platform.portal.model;
public class DataObjectTO{
	private int pageNo=0;
	private int pageSize=0;
	private DataObject dataObject;
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
	public DataObject getDataObject() {
		return dataObject;
	}
	public void setDataObject(DataObject dataObject) {
		this.dataObject=dataObject;
	}
}