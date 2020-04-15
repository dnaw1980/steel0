package com.rss.steel_production.schedule.dao;

import com.rss.steel_production.schedule.model.CastPlanExample;
import org.apache.ibatis.annotations.Param;
import com.rss.framework.iMapper;
import com.rss.steel_production.schedule.model.CastPlan;

import java.util.List;

public interface CastPlanDAO extends iMapper<CastPlan> {
    int countByExample(CastPlanExample example);

    int deleteByExample(CastPlanExample example);

    int deleteByPrimaryKey(String castPlanuid);

//    int insert(CastPlan record);
//
//    int insertSelective(CastPlan record);

    List<CastPlan> selectByExample(CastPlanExample example);

    CastPlan selectByPrimaryKey(String castPlanuid);

    int updateByExampleSelective(@Param("record") CastPlan record, @Param("example") CastPlanExample example);

    int updateByExample(@Param("record") CastPlan record, @Param("example") CastPlanExample example);

//    int updateByPrimaryKeySelective(CastPlan record);
//
//    int updateByPrimaryKey(CastPlan record);
}