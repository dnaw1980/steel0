package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.steel_production.process.model.CompositionInfo;
import com.rss.steel_production.process.model.CompositionInfoTO;
import com.rss.steel_production.process.service.CompositionInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;

@RestController
@RequestMapping("/steel_production/process/compositionInfo")
@Api
public class CompositionInfoController {
    @Resource
    private CompositionInfoService compositionInfoService;

    @PostMapping("/findBy")
    public Result find(@RequestBody CompositionInfoTO compositionInfoTO){
        int pageNo=compositionInfoTO.getPageNo();
        int pageSize=compositionInfoTO.getPageSize();
        CompositionInfo model=compositionInfoTO.getCompositionInfo();
        pageSize = pageSize==0?50:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        Condition condition=new Condition(CompositionInfo.class);
        condition.setOrderByClause("acquireTime desc");
        Condition.Criteria criteria=condition.createCriteria();
        if (model!=null) {
            if (model.getSampleType() != null) {
                criteria.andLike("sampleType", "%" + model.getSampleType() + "%");
            }
            if (model.getAcquireTime() != null) {
                criteria.andLike("acquireTime", "%"+model.getAcquireTime()+"%");
            }
            if (model.getChargeNo() != null) {
                criteria.andLike("chargeNo", "%"+model.getChargeNo()+"%");
            }
        }
        PageInfo pageInfo = new PageInfo(compositionInfoService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);

    }
}
