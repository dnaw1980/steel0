package com.rss.steel_production.schedule.impl;

import javax.annotation.Resource;

import com.rss.steel_production.schedule.model.IronPlanExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.schedule.model.IronPlan;
import com.rss.steel_production.schedule.dao.IronPlanDAO;
import com.rss.steel_production.schedule.service.IronPlanService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IronPlanImpl extends AbstractService<IronPlan> implements IronPlanService {
	@Resource
	private IronPlanDAO ironPlanDAO;

	/*
	 * 获取铁次列表，根据状态
	 */
	@Override
	public List<IronPlan> getIronListByStatus(String[] planStatus) {
		IronPlanExample ipExample = new IronPlanExample();
		IronPlanExample.Criteria cc = ipExample.createCriteria();
		if(planStatus == null) {
			cc.andPlanstatusIsNull();
		} else {
			for(String s: planStatus) {
				ipExample.or().andPlanstatusEqualTo(s);
			}
		}
		ipExample.setOrderByClause("tappingTime ASC");
		List<IronPlan> iplist = ironPlanDAO.selectByExample(ipExample);
		return iplist;
	}

	/*
	 * 获取铁次列表，根据时间段
	 */
	@Override
	public List<IronPlan> getIronListByTimes(Date[] dateInterval) {
		IronPlanExample ipExample = new IronPlanExample();
		IronPlanExample.Criteria cc = ipExample.createCriteria();

		cc.andTappingtimeBetween(dateInterval[0], dateInterval[1]);
		ipExample.setOrderByClause("tappingTime ASC");
		List<IronPlan> iplist = ironPlanDAO.selectByExample(ipExample);
		return iplist;
	}
	/*
	 * 获取铁次列表，根据状态和时间段
	 */
	@Override
	public List<IronPlan> getIronListByStatusAndTimes(String[] planStatus, Date[] dateInterval) {
		IronPlanExample ipExample = new IronPlanExample();
		IronPlanExample.Criteria cc = ipExample.createCriteria();

		if(planStatus == null) {
			cc.andIrondirectionIsNull();
		} else {
			for(String s: planStatus) {
				ipExample.or().andPlanstatusEqualTo(s);
			}
		}
		cc.andTappingtimeBetween(dateInterval[0], dateInterval[1]);
		ipExample.setOrderByClause("tappingTime ASC");
		List<IronPlan> iplist = ironPlanDAO.selectByExample(ipExample);
		return iplist;
	}
	/*
	 * 获取铁次列表，根据最早时间
	 */
	@Override
	public List<IronPlan> getIronListByEarlyTime(Date earlyTime) {
		IronPlanExample ipExample = new IronPlanExample();
		IronPlanExample.Criteria cc = ipExample.createCriteria();

		cc.andTappingtimeGreaterThan(earlyTime);
		ipExample.setOrderByClause("tappingTime ASC");
		List<IronPlan> iplist = ironPlanDAO.selectByExample(ipExample);
		return iplist;
	}
	/*
	 * 设置铁次去向
	 */
//	@Override
//	public int updateIronDirection(String ironPlanUID, String ironDirection) {
//		IronPlanExample ipExample = new IronPlanExample();
//		IronPlan ip = ironPlanDAO.selectByPrimaryKey(ironPlanUID);
//		ip.setIronDirection(ironDirection);
//		int result = ironPlanDAO.updateByPrimaryKeySelective(ip);
//		return result;
//	}

	/*
	 * 更新铁次状态
	 */
	public int updateIronStatus(String ironPlanUID, String status) {
		IronPlanExample ipExample = new IronPlanExample();
		IronPlan ip = ironPlanDAO.selectByPrimaryKey(ironPlanUID);
		ip.setPlanStatus(status);
		int result = ironPlanDAO.updateByPrimaryKeySelective(ip);
		return result;
	}
}