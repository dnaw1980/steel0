package com.rss.steel_production.foundation.dao;

import com.rss.steel_production.foundation.model.ProcessStandardExample;
import org.apache.ibatis.annotations.Param;
import com.rss.framework.iMapper;
import com.rss.steel_production.foundation.model.ProcessStandard;

import java.util.List;

public interface ProcessStandardDAO extends iMapper<ProcessStandard> {
    int countByExample(ProcessStandardExample example);

    int deleteByExample(ProcessStandardExample example);

    int deleteByPrimaryKey(String processStandarduid);

//    int insert(ProcessStandard record);

//    int insertSelective(ProcessStandard record);

    List<ProcessStandard> selectByExample(ProcessStandardExample example);
    
    List<ProcessStandard> getAllProcessStandard();

//    ProcessStandard selectByPrimaryKey(String processStandarduid);

//    int updateByExampleSelective(@Param("record") ProcessStandard record, @Param("example") ProcessStandardExample example);

//    int updateByExample(@Param("record") ProcessStandard record, @Param("example") ProcessStandardExample example);

//    int updateByPrimaryKeySelective(ProcessStandard record);

//    int updateByPrimaryKey(ProcessStandard record);
}
