package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.SlabProduction3DAO;
import com.rss.steel_production.process.model.SlabProduction3;
import com.rss.steel_production.process.service.SlabProduction3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SlabProduction3Impl extends AbstractService<SlabProduction3> implements SlabProduction3Service {
    @Resource
    private SlabProduction3DAO slabProduction3DAO;
}