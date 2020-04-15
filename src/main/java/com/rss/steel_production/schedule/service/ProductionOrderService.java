package com.rss.steel_production.schedule.service;

import com.rss.framework.Service;
import com.rss.steel_production.schedule.model.ProductionOrder;

import java.util.Date;
import java.util.List;

public interface ProductionOrderService extends Service<ProductionOrder>{
    /*
     * name:获取合同信息
     * note:获取<DateInterval>天后到dates天后需要组炉的全部合同，返回合同列表List
     */
    public List<ProductionOrder> getOrderListByTimes(Date[] dateInterval);
    /*
     * 获得合同list，通过List<orderNo>
     */
    public List<ProductionOrder> getOrderListByOrderUIDList(List<String> orderUIDList);
}