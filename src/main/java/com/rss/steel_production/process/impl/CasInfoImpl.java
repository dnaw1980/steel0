package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.CasInfoDAO;
import com.rss.steel_production.process.model.CasInfo;
import com.rss.steel_production.process.service.CasInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CasInfoImpl extends AbstractService<CasInfo> implements CasInfoService {
    @Resource
    private CasInfoDAO casInfoDAO;

}
