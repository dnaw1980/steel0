package com.rss.steel_production.workProcedure.controller.bean;

import com.rss.steel_production.process.model.CompositionInfo;
import com.rss.steel_production.process.model.CompositionStandard;
import com.rss.steel_production.schedule.controller.bean.RealDataBean;
import com.rss.steel_production.workProcedure.model.DpScheduleDetail;
import com.rss.steel_production.workProcedure.model.WpBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaScDataBean {

    /**
     * 当前步骤工序的成份标准信息
     */
    private List<CompositionStandard> stand;

    /**
     * 当前步骤工序的成份化验信息
     */
    private List<CompositionInfo> composition;

    /**
     * 当前工位的工序信息
     */
    private WpBase info;

    /**
     * 工位上当前炉次号的工艺路径
     */
    private List<String> router;

    /**
     * 注意这里的实时数据
     */
    private RealDataBean realData;

    /**
     * 调度信息
     */
    private DpScheduleDetail scheduleDetail;
}
