package com.rss.steel_production.process.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.CompositionInfoDAO;
import com.rss.steel_production.process.model.CompositionInfo;
import com.rss.steel_production.process.service.CompositionInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@Service
@Transactional
public class CompositionInfoImpl extends AbstractService<CompositionInfo> implements CompositionInfoService {
    @Resource
    private CompositionInfoDAO compositionInfoDAO;

}
