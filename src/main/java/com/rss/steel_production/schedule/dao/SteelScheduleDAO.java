package com.rss.steel_production.schedule.dao;

import com.rss.steel_production.schedule.model.SteelScheduleExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import com.rss.framework.iMapper;
import com.rss.steel_production.schedule.model.SteelSchedule;

import java.util.List;
import java.util.Map;

public interface SteelScheduleDAO extends iMapper<SteelSchedule> {
//    int countByExample(SteelScheduleExample example);
//
//    int deleteByExample(SteelScheduleExample example);
//
//    int deleteByPrimaryKey(String steelScheduleuid);

//    int insert(SteelSchedule record);
//
//    int insertSelective(SteelSchedule record);

//    List<SteelSchedule> selectByExample(SteelScheduleExample example);
//
//    SteelSchedule selectByPrimaryKey(String steelScheduleuid);
//
//    int updateByExampleSelective(@Param("record") SteelSchedule record, @Param("example") SteelScheduleExample example);
//
//    int updateByExample(@Param("record") SteelSchedule record, @Param("example") SteelScheduleExample example);

//    int updateByPrimaryKeySelective(SteelSchedule record);
//
//    int updateByPrimaryKey(SteelSchedule record);
	
	  public List<Map<String,Object>> getTimeLineInfoMapList(String orgName);
	  
	  public List<Map<String,Object>> getPlanedSteelSchedule();
	  
	  public List<SteelSchedule> getOptionSchedules();
	  
	  public List<Map<String,Object>> getScheduleExitGroupByStation();
}