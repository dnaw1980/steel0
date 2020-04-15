package com.rss.steel_production.foundation.service;

import com.rss.framework.Service;
import com.rss.steel_production.foundation.model.ProcessStandard;

import java.util.List;

public interface ProcessStandardService extends Service<ProcessStandard>{
    //获取全部工艺标准数据
    public List<ProcessStandard> getStandardList();
}