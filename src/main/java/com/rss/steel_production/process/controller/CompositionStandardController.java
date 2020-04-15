package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.process.model.CompositionStandard;
import com.rss.steel_production.process.model.CompositionStandardTO;
import com.rss.steel_production.process.service.CompositionStandardService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;

@RestController
@RequestMapping("/steel_production/process/compositionStandard")
@Api
public class CompositionStandardController {
    @Resource
    private CompositionStandardService CompositionStandardService;

    @PostMapping("/findBy")
    public Result find(@RequestBody CompositionStandardTO compositionStandardTO){
        int pageNo=compositionStandardTO.getPageNo();
        int pageSize=compositionStandardTO.getPageSize();
        CompositionStandard model=compositionStandardTO.getCompositionStandard();
        PageHelper.startPage(pageNo, pageSize);
        Condition condition=new Condition(CompositionStandard.class);
        Condition.Criteria criteria=condition.createCriteria();
        if (model!=null){
            if(model.getSampleType()!=null) {
                criteria.andLike("sampleType", "%" + model.getSampleType() + "%");
            }
        }
        PageInfo pageInfo = new PageInfo(CompositionStandardService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
