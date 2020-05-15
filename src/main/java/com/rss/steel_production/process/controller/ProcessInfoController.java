package com.rss.steel_production.process.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

import com.rss.steel_production.process.service.ProcessInfoService;
import com.rss.steel_production.process.model.ProcessInfo;
import com.rss.steel_production.process.model.ProcessInfoTO;

@RestController
@RequestMapping("/steel_production/process/processInfo")
@Api
public class ProcessInfoController {
    @Resource
    private ProcessInfoService processInfoService;

    @PostMapping
    public Result add(@RequestBody ProcessInfo processInfo) {
        processInfo.setProcess_infoUID(UUIDGenerator.generate());
        processInfoService.insert(processInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody ProcessInfo processInfo) {
        processInfoService.updateByPrimaryKey(processInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public Result updateByPrimaryKeySelective(@RequestBody ProcessInfo processInfo) {
        processInfoService.update(processInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<String> ids) {
        String idString = QuotesUtil.addQuotesToString(ids);
        processInfoService.deleteByIds(idString);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/find")
    public Result find(@RequestBody ProcessInfoTO processInfoTO) {
        int pageNo = processInfoTO.getPageNo();
        int pageSize = processInfoTO.getPageSize();
        ProcessInfo processInfo = processInfoTO.getProcessInfo();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(processInfoService.findByModel(processInfo));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findBy")
    public Result findBy(@RequestBody ProcessInfoTO processInfoTO) {
        int pageNo = processInfoTO.getPageNo();
        int pageSize = processInfoTO.getPageSize();
        PageHelper.startPage(pageNo, pageSize);
        ProcessInfo model = processInfoTO.getProcessInfo();
        Condition condition = new Condition(ProcessInfo.class);
        Condition.Criteria criteria = condition.createCriteria();
        if (processInfoTO.getScheduleStatusNot() != null) {
            criteria.andNotEqualTo("scheduleStatus", processInfoTO.getScheduleStatusNot());
        }
        if (model != null) {
            if (model.getProcessDate() != null) {
                criteria.andLike("processDate", "%" + new SimpleDateFormat("yyyy-MM-dd").format(model.getProcessDate()) + "%");
            }
            if (model.getDesulfuriExitTime() != null) {
                criteria.andIsNotNull("desulfuriExitTime");
            }
            if (model.getExitConverer() != null) {
                criteria.andIsNotNull("exitConverer");
            }
            if (model.getCasExitTime() != null) {
                criteria.andIsNotNull("casExitTime");
            }
            if (model.getEndLFTime() != null) {
                criteria.andIsNotNull("endLFTime");
            }
            if (model.getSlabEndCastTime() != null) {
                criteria.andIsNotNull("slabEndCastTime");
            }
            if (model.getBilletEndCastTime() != null) {
                criteria.andIsNotNull("billetEndCastTime");
            }
        }
        PageInfo pageInfo = new PageInfo(processInfoService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
