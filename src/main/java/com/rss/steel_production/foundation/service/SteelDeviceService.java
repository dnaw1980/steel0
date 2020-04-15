package com.rss.steel_production.foundation.service;

import com.rss.framework.Service;
import com.rss.steel_production.foundation.model.SteelDevice;

import java.util.List;

public interface SteelDeviceService extends Service<SteelDevice>{
    /*
     * 获取可用设备列表，即状态为1的
     */
    public List<SteelDevice> getDeviceList();

    /*
     * 获取可用设备工序名称列表
     */
    public List<String> getProcessNameList();
    /*
     * 获取设备所在工位
     */
    public String getTypeByNo(String deviceNo);
    /*
     * 获取指定类型设备列表，如：CC
     */
    public List<SteelDevice> getDeviceListByType(String type);
    /*
    通过deviceno获取设备
     */
    public SteelDevice getDeviceByDeviceNo(String deviceNo);

    /*
    更改设备的enterTime，exitTime。
     */
    public int updateByDeviceNo(SteelDevice sd);
}