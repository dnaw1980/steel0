package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.ConvererInfoDAO;
import com.rss.steel_production.process.model.ConvererInfo;
import com.rss.steel_production.process.service.ConvererInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ConvererInfoImpl extends AbstractService<ConvererInfo> implements ConvererInfoService {
    @Resource
    private ConvererInfoDAO convererInfoDAO;

}
