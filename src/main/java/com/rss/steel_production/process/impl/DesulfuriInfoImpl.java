package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.DesulfuriInfoDAO;
import com.rss.steel_production.process.model.DesulfuriInfo;
import com.rss.steel_production.process.service.DesulfuriInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DesulfuriInfoImpl extends AbstractService<DesulfuriInfo> implements DesulfuriInfoService {
    @Resource
    private DesulfuriInfoDAO desulfuriInfoDAO;

}
