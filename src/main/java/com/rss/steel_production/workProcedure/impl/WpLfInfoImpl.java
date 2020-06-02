package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpLfInfoDAO;
import com.rss.steel_production.workProcedure.model.WpLfInfo;
import com.rss.steel_production.workProcedure.service.WpLfInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpLfInfoImpl extends AbstractService<WpLfInfo> implements WpLfInfoService {

    @Resource
    private WpLfInfoDAO wpLfInfoDAO;

}
