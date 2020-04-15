package com.rss.platform.portal.impl;

import com.rss.framework.AbstractService;
import com.rss.platform.portal.dao.OrgInfoDAO;
import com.rss.platform.portal.model.OrgInfo;
import com.rss.platform.portal.service.OrgInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class OrgInfoImpl extends AbstractService<OrgInfo> implements OrgInfoService {
	@Resource
	private OrgInfoDAO orgInfoDAO;
}