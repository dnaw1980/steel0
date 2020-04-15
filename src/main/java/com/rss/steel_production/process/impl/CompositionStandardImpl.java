package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.CompositionStandardDAO;
import com.rss.steel_production.process.model.CompositionStandard;
import com.rss.steel_production.process.service.CompositionStandardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CompositionStandardImpl extends AbstractService<CompositionStandard> implements CompositionStandardService {
    @Resource
    private CompositionStandardDAO steelCompositionStandardDAO;
}
