package com.rss.steel_production.workProcedure.impl;

import com.rss.framework.AbstractService;
import com.rss.steel_production.workProcedure.dao.WpIronInfoDAO;
import com.rss.steel_production.workProcedure.model.WpIronInfo;
import com.rss.steel_production.workProcedure.service.WpIronInfoService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@EnableScheduling
@Transactional
public class WpIronInfoImpl extends AbstractService<WpIronInfo> implements WpIronInfoService {

    @Resource
    private WpIronInfoDAO wpIronInfoDAO;

    /**
     * 注册铁水信息，生成一条调度计划
     *
     * @param wpIronInfo
     * @return
     */
    @Override
    public WpIronInfo regIronInfo(WpIronInfo wpIronInfo) {

        /*
        1、查看当前正在执行的浇次计划，查每个浇次对应的工艺卡中的第一个工艺路径。
        按这个工艺路径对应的工序，找到所有工位，及每个工位最后一个作业的完成时间（实际优先于计划）。

        2、比较每个工位的完成时间是最早的那个，就是这个调度的第一个工序的开始时间。
        3、根据当前工序查询出标准作业时间，计算当前工序完成时间。
        2、
         */
        return null;
    }
}
