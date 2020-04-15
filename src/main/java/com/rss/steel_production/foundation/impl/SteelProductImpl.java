package com.rss.steel_production.foundation.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.foundation.model.SteelProduct;
import com.rss.steel_production.foundation.dao.SteelProductDAO;
import com.rss.steel_production.foundation.service.SteelProductService;

@Service
@Transactional
public class SteelProductImpl extends AbstractService<SteelProduct> implements SteelProductService {
	@Resource
	private SteelProductDAO steelProductDAO;
}