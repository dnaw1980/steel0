package com.rss.steel_production.process.service;

import com.rss.framework.Service;
import com.rss.steel_production.process.model.IronInfo;

public interface IronInfoService extends Service<IronInfo> {
	public void insertIronINfo(IronInfo ironINfo) throws Exception;
}
