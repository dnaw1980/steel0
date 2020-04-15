package com.rss.platform.portal.impl;

import com.rss.framework.AbstractService;
import com.rss.platform.portal.dao.DataObjectDAO;
import com.rss.platform.portal.model.DataObject;
import com.rss.platform.portal.service.DataObjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DataObjectImpl extends AbstractService<DataObject> implements DataObjectService {
	@Resource
	private DataObjectDAO dataObjectDAO;

}