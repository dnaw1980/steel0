package com.rss.platform.portal.impl;

import com.rss.framework.AbstractService;
import com.rss.platform.portal.dao.RoleInfoDAO;
import com.rss.platform.portal.model.RoleInfo;
import com.rss.platform.portal.service.RoleInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class RoleInfoImpl extends AbstractService<RoleInfo> implements RoleInfoService {
	@Resource
	private RoleInfoDAO roleInfoDAO;
}