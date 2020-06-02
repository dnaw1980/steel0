package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpRhInfoDAO;
import com.rss.steel_production.workProcedure.model.WpRhInfo;
import com.rss.steel_production.workProcedure.service.WpRhInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpRhInfoImpl extends AbstractService<WpRhInfo> implements WpRhInfoService {

    @Resource
    private WpRhInfoDAO wpRhInfoDAO;

}
