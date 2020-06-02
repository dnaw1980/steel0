package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpSlabInfoDAO;
import com.rss.steel_production.workProcedure.model.WpSlabInfo;
import com.rss.steel_production.workProcedure.service.WpSlabInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpSlabInfoImpl extends AbstractService<WpSlabInfo> implements WpSlabInfoService {

    @Resource
    private WpSlabInfoDAO wpSlabInfoDAO;

}
