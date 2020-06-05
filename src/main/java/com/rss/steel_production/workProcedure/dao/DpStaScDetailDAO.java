package com.rss.steel_production.workProcedure.dao;

import com.rss.steel_production.workProcedure.model.DpStaScDetail;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface DpStaScDetailDAO extends Mapper<DpStaScDetail>, MySqlMapper<DpStaScDetail>, InsertListMapper<DpStaScDetail>, ConditionMapper<DpStaScDetail> {

}