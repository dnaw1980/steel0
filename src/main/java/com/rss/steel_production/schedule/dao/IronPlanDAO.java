package com.rss.steel_production.schedule.dao;

import com.rss.steel_production.schedule.model.IronPlanExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import com.rss.framework.iMapper;
import com.rss.steel_production.schedule.model.IronPlan;

import java.util.List;

public interface IronPlanDAO extends iMapper<IronPlan> {
    int countByExample(IronPlanExample example);

    int deleteByExample(IronPlanExample example);

    int deleteByPrimaryKey(String ironPlanuid);

//    int insert(IronPlan record);
//
//    int insertSelective(IronPlan record);

    List<IronPlan> selectByExample(IronPlanExample example);

    IronPlan selectByPrimaryKey(String ironPlanuid);

    int updateByExampleSelective(@Param("record") IronPlan record, @Param("example") IronPlanExample example);

    int updateByExample(@Param("record") IronPlan record, @Param("example") IronPlanExample example);

//    int updateByPrimaryKeySelective(IronPlan record);
//
//    int updateByPrimaryKey(IronPlan record);
}