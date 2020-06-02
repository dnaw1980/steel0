package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpIronInfoDAO;
import com.rss.steel_production.workProcedure.model.WpIronInfo;
import com.rss.steel_production.workProcedure.service.WpIronInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpIronInfoImpl extends AbstractService<WpIronInfo> implements WpIronInfoService {

    @Resource
    private WpIronInfoDAO wpIronInfoDAO;

}
