package com.rss.steel_production.schedule.dao;

import com.rss.steel_production.schedule.model.ChargePlanExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import com.rss.framework.iMapper;
import com.rss.steel_production.schedule.model.ChargePlan;

import java.util.List;

public interface ChargePlanDAO extends iMapper<ChargePlan> {
    int countByExample(ChargePlanExample example);

    int deleteByExample(ChargePlanExample example);

    int deleteByPrimaryKey(String chargePlanuid);

//    int insert(ChargePlan record);

//    int insertSelective(ChargePlan record);

    List<ChargePlan> selectByExample(ChargePlanExample example);

    ChargePlan selectByPrimaryKey(String chargePlanuid);

    int updateByExampleSelective(@Param("record") ChargePlan record, @Param("example") ChargePlanExample example);

    int updateByExample(@Param("record") ChargePlan record, @Param("example") ChargePlanExample example);

//    int updateByPrimaryKeySelective(ChargePlan record);

//    int updateByPrimaryKey(ChargePlan record);
}