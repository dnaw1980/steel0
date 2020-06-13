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

/**
 * 铁水去向参数对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IronDirectionBean {

    private Integer ironInfoSn;

    private Integer direction;
}
