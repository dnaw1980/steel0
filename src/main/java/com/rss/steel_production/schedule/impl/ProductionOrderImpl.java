package com.rss.steel_production.schedule.impl;

import javax.annotation.Resource;

import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.schedule.model.ProductionOrderExample;
import com.rss.steel_production.schedule.service.ChargePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rss.framework.AbstractService;

import com.rss.steel_production.schedule.model.ProductionOrder;
import com.rss.steel_production.schedule.dao.ProductionOrderDAO;
import com.rss.steel_production.schedule.service.ProductionOrderService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductionOrderImpl extends AbstractService<ProductionOrder> implements ProductionOrderService {
	@Resource
	private ProductionOrderDAO productionOrderDAO;

	@Autowired
	ProcessStandardService processStandardService;
	@Autowired
	ChargePlanService chargePlanService;

	/*
	 * name:获取合同信息
	 * note:获取<DateInterval>天后到dates天后需要组炉的全部合同，返回合同列表List
	 */
	@Override
	public List<ProductionOrder> getOrderListByTimes(Date[] dateInterval) {
		ProductionOrderExample po_iExample = new ProductionOrderExample();
		ProductionOrderExample.Criteria cc = po_iExample.createCriteria();
		cc.andOrderdateBetween(dateInterval[0], dateInterval[1]);
		po_iExample.setOrderByClause("targetTime ASC, steelGrade ASC, productLevel ASC");
		return productionOrderDAO.selectByExample(po_iExample);
	}

	/*
	 * 获得合同list，通过List<orderNo>
	 */
	@Override
	public List<ProductionOrder> getOrderListByOrderUIDList(List<String> orderUIDList){
		ProductionOrderExample poExample = new ProductionOrderExample();
		ProductionOrderExample.Criteria cc = poExample.createCriteria();
		for(String orderUID: orderUIDList) {
			//2020,3,1修改，由UID改为NO，原为：andProductionOrderuidEqualTo(orderUID);
			poExample.or().andOrdernoEqualTo(orderUID);
		}
		poExample.setOrderByClause("targetTime ASC, steelGrade ASC, productLevel DESC");
		List<ProductionOrder> orderList = productionOrderDAO.selectByExample(poExample);
		return orderList;
	}
}
