package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.SlabProduction2DAO;
import com.rss.steel_production.process.model.SlabProduction2;
import com.rss.steel_production.process.service.SlabProduction2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SlabProduction2Impl extends AbstractService<SlabProduction2> implements SlabProduction2Service {
	@Resource
	private SlabProduction2DAO slabProduction2DAO;
}