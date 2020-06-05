package com.rss.steel_production.workProcedure.controller;

import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.workProcedure.model.DpScheduleSeq;
import com.rss.steel_production.workProcedure.model.DpTechCard;
import com.rss.steel_production.workProcedure.service.DpScheduleSeqService;
import com.rss.steel_production.workProcedure.service.DpTechCardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/steel_production/schedule/schedule")
@Api
public class DpScheduleController {

    @Autowired
    private DpScheduleSeqService dpScheduleSeqService;


    /**
     * 查询工艺卡信息
     *
     * @return
     */
    @GetMapping("/add/{castPlanId}/{beginTm}")
    public Result techCard(@PathVariable(required = true) String castPlanId, @PathVariable Timestamp beginTm) {

        DpScheduleSeq rs = this.dpScheduleSeqService.add(castPlanId, beginTm);

        return ResultGenerator.genSuccessResult(rs);
    }


}
