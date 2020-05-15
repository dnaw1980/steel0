package com.rss.steel_production.foundation.service;

import java.text.ParseException;

import com.rss.framework.Service;
import com.rss.steel_production.foundation.model.SteelProduct;

public interface SteelProductService extends Service<SteelProduct>{

	/**
	 * 根据铁次号和开始时间自动生成调度计划信息
	 * @param ironNo
	 * @param startDt
	 * @throws ParseException 
	 */
	void autoCreate(String ironNo, String startDt) throws ParseException;

}