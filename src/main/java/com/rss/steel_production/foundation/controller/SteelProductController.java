package com.rss.steel_production.foundation.controller;

import java.util.List;
import java.util.Map;
import java.text.ParseException;
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

import com.rss.steel_production.foundation.service.SteelProductService;
import com.rss.steel_production.foundation.model.SteelProduct;
import com.rss.steel_production.foundation.model.SteelProductTO;

@RestController
@RequestMapping("/steel_production/foundation/steelProduct")
@Api
public class SteelProductController {
    @Resource
    private SteelProductService steelProductService;

    @PostMapping
    public Result add(@RequestBody SteelProduct steelProduct) {
        steelProduct.setSteel_productUID(UUIDGenerator.generate());
        Condition condition = new Condition(SteelProduct.class);
        Condition.Criteria criteria;
        criteria = condition.createCriteria();
        criteria.andEqualTo("productCode", steelProduct.getProductCode());
        if (steelProductService.findByCondition(condition).size() > 0)
            return ResultGenerator.genFailResult("productCode值重复");

        Condition condition1 = new Condition(SteelProduct.class);
        Condition.Criteria criteria1;
        criteria1 = condition.createCriteria();
        criteria1.andEqualTo("productSpec", steelProduct.getProductSpec());
        criteria1.andEqualTo("steelGrade", steelProduct.getSteelGrade());
        if (steelProductService.findByCondition(condition1).size() > 0)
            return ResultGenerator.genFailResult("productSpec值和steelGrade重复");

        steelProductService.insert(steelProduct);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody SteelProduct steelProduct) {
        Condition condition;
        Condition.Criteria criteria;
        condition = new Condition(SteelProduct.class);
        criteria = condition.createCriteria();
        criteria.andEqualTo("productCode", steelProduct.getProductCode());
        criteria.andNotEqualTo("steel_productUID", steelProduct.getSteel_productUID());
        if (steelProductService.findByCondition(condition).size() > 0)
            return ResultGenerator.genFailResult("productCode值重复");
        steelProductService.updateByPrimaryKey(steelProduct);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<String> ids) {
        String idString = QuotesUtil.addQuotesToString(ids);
        steelProductService.deleteByIds(idString);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/find")
    public Result find(@RequestBody SteelProductTO steelProductTO) {
        int pageNo = steelProductTO.getPageNo();
        int pageSize = steelProductTO.getPageSize();
        SteelProduct steelProduct = steelProductTO.getSteelProduct();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(steelProductService.findByModel(steelProduct));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findBy")
    public Result findBy(@RequestBody SteelProductTO steelProductTO) {
        int pageNo = steelProductTO.getPageNo();
        int pageSize = steelProductTO.getPageSize();
        SteelProduct model = steelProductTO.getSteelProduct();
        PageHelper.startPage(pageNo, pageSize);
        Condition condition = new Condition(SteelProduct.class);
        Condition.Criteria criteria = condition.createCriteria();
        if (model != null) {
            if (model.getProductCode() != null) {
                criteria.andLike("productCode", "%" + model.getProductCode() + "%");
            }
            if (model.getProductName() != null) {
                criteria.andLike("productName", "%" + model.getProductName() + "%");
            }
            if (model.getProductType() != null) {
                criteria.andLike("productType", "%" + model.getProductType() + "%");
            }
        }
        PageInfo pageInfo = new PageInfo(steelProductService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    /**
     * 根据铁次号和开始时间自动生成调度计划信息
     * @return
     * @throws ParseException 
     */
    @PostMapping("/autoCreate")
    public Result autoCreate(@RequestBody Map<String,String> steelScheduleInfo) throws ParseException {
    	String ironNo = steelScheduleInfo.get("ironNo");
    	String startDt = steelScheduleInfo.get("startDt");
    	steelProductService.autoCreate(ironNo, startDt);
    	return ResultGenerator.genSuccessResult();
    }
}
