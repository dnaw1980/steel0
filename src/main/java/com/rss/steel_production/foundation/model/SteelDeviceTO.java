package com.rss.steel_production.foundation.model;
public class SteelDeviceTO{
	private int pageNo=0;
	private int pageSize=0;
	private SteelDevice steelDevice;
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
	public SteelDevice getSteelDevice() {
		return steelDevice;
	}
	public void setSteelDevice(SteelDevice steelDevice) {
		this.steelDevice=steelDevice;
	}
}