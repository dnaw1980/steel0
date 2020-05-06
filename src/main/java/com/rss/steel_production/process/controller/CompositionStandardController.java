package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.CompositionStandard;
import com.rss.steel_production.process.model.CompositionStandardTO;
import com.rss.steel_production.process.service.CompositionStandardService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/compositionStandard")
@Api
public class CompositionStandardController {
    @Resource
    private CompositionStandardService compositionStandardService;

    @PostMapping
    public Result add(@RequestBody CompositionStandard compositionStandard) {
        compositionStandard.setSteel_composition_standardUID(UUIDGenerator.generate());
        Condition condition = new Condition(CompositionStandard.class);
        Condition.Criteria criteria;
        criteria = condition.createCriteria();
        criteria.andEqualTo("sampleType", compositionStandard.getSampleType());
        criteria.andEqualTo("itemID", compositionStandard.getItemID());
        if (compositionStandardService.findByCondition(condition).size() > 0)
            return ResultGenerator.genFailResult("元素名称和样品类型重复");
        compositionStandardService.insert(compositionStandard);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CompositionStandard compositionStandard) {
        Condition condition = new Condition(CompositionStandard.class);
        Condition.Criteria criteria;
        criteria = condition.createCriteria();
        criteria.andEqualTo("sampleType", compositionStandard.getSampleType());
        criteria.andEqualTo("itemID", compositionStandard.getItemID());
        criteria.andNotEqualTo("steel_composition_standardUID", compositionStandard.getSteel_composition_standardUID());
        if (compositionStandardService.findByCondition(condition).size() > 0)
            return ResultGenerator.genFailResult("元素名称和样品类型重复");
        compositionStandardService.updateByPrimaryKey(compositionStandard);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<String> ids) {
        String idString = QuotesUtil.addQuotesToString(ids);
        compositionStandardService.deleteByIds(idString);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/find")
    public Result find(@RequestBody CompositionStandardTO compositionStandardTO) {
        int pageNo = compositionStandardTO.getPageNo();
        int pageSize = compositionStandardTO.getPageSize();
        CompositionStandard compositionStandard = compositionStandardTO.getCompositionStandard();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(compositionStandardService.findByModel(compositionStandard));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findBy")
    public Result findBy(@RequestBody CompositionStandardTO compositionStandardTO) {
        int pageNo = compositionStandardTO.getPageNo();
        int pageSize = compositionStandardTO.getPageSize();
        CompositionStandard model = compositionStandardTO.getCompositionStandard();
        PageHelper.startPage(pageNo, pageSize);
        Condition condition = new Condition(CompositionStandard.class);
        Condition.Criteria criteria = condition.createCriteria();
        if (model != null) {
            if (model.getSampleType() != null) {
                criteria.andLike("sampleType", "%" + model.getSampleType() + "%");
            }
            if (model.getItemID() != null) {
                criteria.andLike("itemID", model.getItemID());
            }
        }
        PageInfo pageInfo = new PageInfo(compositionStandardService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
