package com.rss.platform.portal.impl;

import com.rss.framework.AbstractService;
import com.rss.platform.portal.dao.TermInfoDAO;
import com.rss.platform.portal.model.TermInfo;
import com.rss.platform.portal.service.TermInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TermInfoImpl extends AbstractService<TermInfo> implements TermInfoService {
	@Resource
	private TermInfoDAO termInfoDAO;
}