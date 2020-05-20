package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.foundation.service.SteelProductService;
import com.rss.steel_production.process.dao.IronInfoDAO;
import com.rss.steel_production.process.model.IronInfo;
import com.rss.steel_production.process.service.IronInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import javax.annotation.Resource;

@Service
@Transactional
public class IronInfoImpl extends AbstractService<IronInfo> implements IronInfoService {
    @Resource
    private IronInfoDAO ironInfoDAO;
    @Resource
    private SteelProductService steelProductService;

	@Override
	public void insertIronINfo(IronInfo ironInfo) throws Exception {
		ironInfo.setAcquireTime(new Date());
		this.insert(ironInfo);
		steelProductService.autoCreate(ironInfo);
	}

}
