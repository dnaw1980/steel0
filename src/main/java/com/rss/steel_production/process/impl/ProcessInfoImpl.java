package com.rss.steel_production.process.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.process.model.ProcessInfo;
import com.rss.steel_production.process.dao.ProcessInfoDAO;
import com.rss.steel_production.process.service.ProcessInfoService;

@Service
@Transactional
public class ProcessInfoImpl extends AbstractService<ProcessInfo> implements ProcessInfoService {
	@Resource
	private ProcessInfoDAO processInfoDAO;
}