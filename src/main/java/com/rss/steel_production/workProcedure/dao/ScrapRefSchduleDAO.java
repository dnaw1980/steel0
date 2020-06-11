package com.rss.steel_production.workProcedure.dao;

import com.rss.steel_production.workProcedure.model.ScrapRefSchdule;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface ScrapRefSchduleDAO extends Mapper<ScrapRefSchdule>, MySqlMapper<ScrapRefSchdule>, InsertListMapper<ScrapRefSchdule>, ConditionMapper<ScrapRefSchdule> {

}