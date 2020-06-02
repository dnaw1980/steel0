package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpCasInfoDAO;
import com.rss.steel_production.workProcedure.model.WpCasInfo;
import com.rss.steel_production.workProcedure.service.WpCasInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpCasInfoImpl extends AbstractService<WpCasInfo> implements WpCasInfoService {

    @Resource
    private WpCasInfoDAO wpCasInfoDAO;

}
