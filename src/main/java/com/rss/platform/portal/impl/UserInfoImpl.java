package com.rss.platform.portal.impl;

import com.rss.framework.AbstractService;
import com.rss.platform.portal.dao.UserInfoDAO;
import com.rss.platform.portal.model.UserInfo;
import com.rss.platform.portal.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserInfoImpl extends AbstractService<UserInfo> implements UserInfoService {
	@Resource
	private UserInfoDAO userInfoDAO;
}