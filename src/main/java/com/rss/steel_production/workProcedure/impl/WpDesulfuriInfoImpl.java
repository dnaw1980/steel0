package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpDesulfuriInfoDAO;
import com.rss.steel_production.workProcedure.model.WpDesulfuriInfo;
import com.rss.steel_production.workProcedure.service.WpDesulfuriInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpDesulfuriInfoImpl extends AbstractService<WpDesulfuriInfo> implements WpDesulfuriInfoService {

    @Resource
    private WpDesulfuriInfoDAO wpDesulfuriInfoDAO;

}
