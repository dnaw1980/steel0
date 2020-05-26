package com.rss.steel_production.schedule.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.schedule.dao.TdChannelDAO;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.service.TdChannelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TdChannelImpl extends AbstractService<TdChannel> implements TdChannelService {
    @Resource
    private TdChannelDAO tdChannelDAO;

}
