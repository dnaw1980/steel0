package com.rss.steel_production.schedule.dao;

import com.rss.steel_production.schedule.model.ProductionOrderExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import com.rss.framework.iMapper;
import com.rss.steel_production.schedule.model.ProductionOrder;

import java.util.List;

public interface ProductionOrderDAO extends iMapper<ProductionOrder> {
    int countByExample(ProductionOrderExample example);

    int deleteByExample(ProductionOrderExample example);

    int deleteByPrimaryKey(String productOrderuid);

//    int insert(ProductionOrder record);
//
//    int insertSelective(ProductionOrder record);

    List<ProductionOrder> selectByExample(ProductionOrderExample example);

    ProductionOrder selectByPrimaryKey(String productOrderuid);

    int updateByExampleSelective(@Param("record") ProductionOrder record, @Param("example") ProductionOrderExample example);

    int updateByExample(@Param("record") ProductionOrder record, @Param("example") ProductionOrderExample example);

//    int updateByPrimaryKeySelective(ProductionOrder record);
//
//    int updateByPrimaryKey(ProductionOrder record);
}