package com.rss.steel_production.workProcedure.service;

import com.rss.framework.Service;
import com.rss.steel_production.workProcedure.model.WpIronInfo;
import com.rss.steel_production.workProcedure.model.WpIronInfoTO;

import java.util.List;

public interface WpIronInfoService extends Service<WpIronInfo> {

    /**
     * 注册铁水信息，生成一条调度计划
     *
     * @param wpIronInfo
     * @return
     */
    WpIronInfo regIronInfo(WpIronInfo wpIronInfo);

    /**
     * 查看铁水信息列表
     *
     * @param wpIronInfoTO
     * @return
     */
    List<WpIronInfo> list(WpIronInfoTO wpIronInfoTO);
}