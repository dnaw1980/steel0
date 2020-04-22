package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.LfInfoDAO;
import com.rss.steel_production.process.model.LfInfo;
import com.rss.steel_production.process.service.LfInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LfInfoImpl extends AbstractService<LfInfo> implements LfInfoService {
	@Resource
	private LfInfoDAO lfInfoDAO;
}