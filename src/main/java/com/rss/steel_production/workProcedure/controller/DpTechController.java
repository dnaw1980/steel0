package com.rss.steel_production.workProcedure.controller;

import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.schedule.controller.bean.RealData;
import com.rss.steel_production.schedule.controller.bean.RealDataBean;
import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.schedule.service.*;
import com.rss.steel_production.workProcedure.model.DpTechCard;
import com.rss.steel_production.workProcedure.service.DpTechCardService;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/steel_production/schedule/tech")
@Api
public class DpTechController {

    @Autowired
    private DpTechCardService dpTechCardService;


    /**
     * 查询工艺卡信息
     *
     * @return
     */
    @GetMapping("/techCard")
    public Result techCard() {

        List<DpTechCard> rs = this.dpTechCardService.allTechCard();

        return ResultGenerator.genSuccessResult(rs);
    }


}
