package com.rss.steel_production.foundation.impl;

import javax.annotation.Resource;

import com.rss.steel_production.foundation.model.ProcessStandardExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.foundation.model.ProcessStandard;
import com.rss.steel_production.foundation.dao.ProcessStandardDAO;
import com.rss.steel_production.foundation.service.ProcessStandardService;

import java.util.List;

@Service
@Transactional
public class ProcessStandardImpl extends AbstractService<ProcessStandard> implements ProcessStandardService {
	@Resource
	private ProcessStandardDAO processStandardDAO;


	/*
	 * name:获取全部工艺标准数据
	 * note:从数据库中读取全部工艺标准的数据List并返回
	 */
	@Override
	public List<ProcessStandard> getStandardList() {
		// TODO Auto-generated method stub
		ProcessStandardExample psExample = new ProcessStandardExample();
		ProcessStandardExample.Criteria cc = psExample.createCriteria();
		cc.getAllCriteria();
		List<ProcessStandard> pslist = processStandardDAO.selectByExample(psExample);
		return pslist;
	}
}