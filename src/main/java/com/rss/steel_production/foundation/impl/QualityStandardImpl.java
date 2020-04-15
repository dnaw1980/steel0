package com.rss.steel_production.foundation.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.foundation.model.QualityStandard;
import com.rss.steel_production.foundation.dao.QualityStandardDAO;
import com.rss.steel_production.foundation.service.QualityStandardService;

@Service
@Transactional
public class QualityStandardImpl extends AbstractService<QualityStandard> implements QualityStandardService {
	@Resource
	private QualityStandardDAO qualityStandardDAO;
}