package com.rss.steel_production.workProcedure.dao;

import com.rss.steel_production.workProcedure.model.DpCastWpEndTm;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface DpCastWpEndTmDAO extends Mapper<DpCastWpEndTm>, MySqlMapper<DpCastWpEndTm>, InsertListMapper<DpCastWpEndTm>, ConditionMapper<DpCastWpEndTm> {

}