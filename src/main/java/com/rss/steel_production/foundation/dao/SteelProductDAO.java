package com.rss.steel_production.foundation.dao;

import java.util.List;

import com.rss.framework.iMapper;
import com.rss.steel_production.foundation.model.SteelProduct;

public interface SteelProductDAO extends iMapper<SteelProduct> {
	
	List<SteelProduct> getSteelProductRouter(String productSpec, String steelGrade);
}