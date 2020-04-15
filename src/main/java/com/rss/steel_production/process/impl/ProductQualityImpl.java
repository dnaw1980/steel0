package com.rss.steel_production.process.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.process.model.ProductQuality;
import com.rss.steel_production.process.dao.ProductQualityDAO;
import com.rss.steel_production.process.service.ProductQualityService;

@Service
@Transactional
public class ProductQualityImpl extends AbstractService<ProductQuality> implements ProductQualityService {
	@Resource
	private ProductQualityDAO productQualityDAO;
}