package com.rss.platform.portal.impl;

		import com.rss.framework.AbstractService;
		import com.rss.platform.portal.dao.AppInfoDAO;
		import com.rss.platform.portal.model.AppInfo;
		import com.rss.platform.portal.service.AppInfoService;
		import org.springframework.stereotype.Service;
		import org.springframework.transaction.annotation.Transactional;

		import javax.annotation.Resource;

@Service
@Transactional
public class AppInfoImpl extends AbstractService<AppInfo> implements AppInfoService {
	@Resource
	private AppInfoDAO appInfoDAO;
}