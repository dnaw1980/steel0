package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpBilletInfoDAO;
import com.rss.steel_production.workProcedure.model.WpBilletInfo;
import com.rss.steel_production.workProcedure.service.WpBilletInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpBilletInfoImpl extends AbstractService<WpBilletInfo> implements WpBilletInfoService {

    @Resource
    private WpBilletInfoDAO wpBilletInfoDAO;

}
