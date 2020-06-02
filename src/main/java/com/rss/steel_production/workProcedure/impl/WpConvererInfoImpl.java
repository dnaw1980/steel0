package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpConvererInfoDAO;
import com.rss.steel_production.workProcedure.model.WpConvererInfo;
import com.rss.steel_production.workProcedure.service.WpConvererInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpConvererInfoImpl extends AbstractService<WpConvererInfo> implements WpConvererInfoService {

    @Resource
    private WpConvererInfoDAO wpConvererInfoDAO;

}
