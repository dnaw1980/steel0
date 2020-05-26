package com.rss.steel_production.schedule.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.dao.TdDataBatDAO;
import com.rss.steel_production.schedule.model.TdDataBat;
import com.rss.steel_production.schedule.service.TdDataBatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TdDataBatImpl extends AbstractService<TdDataBat> implements TdDataBatService {
    @Resource
    private TdDataBatDAO tdDataBatDAO;

}
