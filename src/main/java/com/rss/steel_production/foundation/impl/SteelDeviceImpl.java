package com.rss.steel_production.foundation.impl;

import javax.annotation.Resource;

import com.rss.steel_production.foundation.model.SteelDeviceExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.foundation.model.SteelDevice;
import com.rss.steel_production.foundation.dao.SteelDeviceDAO;
import com.rss.steel_production.foundation.service.SteelDeviceService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SteelDeviceImpl extends AbstractService<SteelDevice> implements SteelDeviceService {
	@Resource
	private SteelDeviceDAO steelDeviceDAO;

	/*
	 * 获取可用设备列表，即状态为1的
	 */
	@Override
	public List<SteelDevice> getDeviceList(){
		SteelDeviceExample sdExample = new SteelDeviceExample();
		SteelDeviceExample.Criteria cc = sdExample.createCriteria();
		cc.andDevicestatusEqualTo("空闲");
		List<SteelDevice> sdlist = steelDeviceDAO.selectByExample(sdExample);
		return sdlist;
	}

	/*
	 * 获取可用设备工序名称列表
	 */
	@Override
	public List<String> getProcessNameList() {
		List<SteelDevice> sdlist = getDeviceList();
		List<String> pnlist = new ArrayList<String>();
		for(SteelDevice sd: sdlist) {
			pnlist.add(sd.getStationName());
		}
		return pnlist;
	}

	/*
	 * 获取设备所在工位
	 */
	public String getTypeByNo(String deviceNo) {
		SteelDeviceExample sdExample = new SteelDeviceExample();
		SteelDeviceExample.Criteria cc = sdExample.createCriteria();
		cc.andDevicenameEqualTo(deviceNo);
		List<SteelDevice> sdlist = steelDeviceDAO.selectByExample(sdExample);
		SteelDevice sd = null;
		for(SteelDevice s: sdlist) {
			sd = s;
		}
		return sd.getStationName().split("#")[1];
	}

	/*
	 * 获取指定类型设备列表，如：CC
	 */
	@Override
	public List<SteelDevice> getDeviceListByType(String type) {
		List<SteelDevice> sdlist = getDeviceList();
		List<SteelDevice> list = new ArrayList<SteelDevice>();
		for(SteelDevice sd: sdlist) {
			if(sd.getStationName().split("#")[1].equals(type)) {
				list.add(sd);
			}
		}
		return list;
	}
	/*
	通过deviceno获取设备
	 */
	@Override
	public SteelDevice getDeviceByDeviceNo(String deviceNo){
		SteelDeviceExample sdExample = new SteelDeviceExample();
		SteelDeviceExample.Criteria cc = sdExample.createCriteria();
		cc.andDevicenameEqualTo(deviceNo);
		List<SteelDevice> sdlist = steelDeviceDAO.selectByExample(sdExample);
		if(sdlist.isEmpty())
			return null;
		else
			return sdlist.get(0);
	}

	/*
	更改设备的enterTime，exitTime。
	 */
	@Override
	public int updateByDeviceNo(SteelDevice sd){
		SteelDeviceExample sdExample = new SteelDeviceExample();
		SteelDeviceExample.Criteria cc = sdExample.createCriteria();
		cc.andDevicenameEqualTo(sd.getDeviceName());
		int result = steelDeviceDAO.updateByExample(sd, sdExample);
		return result;
	}
}