package com.rss.steel_production.process.controller;

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
import com.rss.steel_production.process.service.ProductQualityService;
import com.rss.steel_production.process.model.ProductQuality;
import com.rss.steel_production.process.model.ProductQualityTO;

@RestController
@RequestMapping("/steel_production/process/productQuality")
@Api
public class ProductQualityController{
	 @Resource
	private ProductQualityService productQualityService;

	@PostMapping
	public Result add(@RequestBody ProductQuality productQuality){
		productQuality.setProcess_qualityUID(UUIDGenerator.generate());
		productQualityService.insert(productQuality);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody ProductQuality productQuality){
		productQualityService.updateByPrimaryKey(productQuality);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		productQualityService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody ProductQualityTO productQualityTO){
		int pageNo=productQualityTO.getPageNo();
		int pageSize=productQualityTO.getPageSize();
		ProductQuality productQuality=productQualityTO.getProductQuality();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(productQualityService.findByModel(productQuality));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody ProductQualityTO productQualityTO){
		int pageNo=productQualityTO.getPageNo();
		int pageSize=productQualityTO.getPageSize();
		ProductQuality model=productQualityTO.getProductQuality();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(ProductQuality.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getProductionNo()!=null){
				criteria.andLike("productionNo","%"+model.getProductionNo()+"%");
			}
			if(model.getSampleType()!=null){
				criteria.andLike("sampleType","%"+model.getSampleType()+"%");
			}
			if(model.getStationName()!=null){
				criteria.andLike("stationName","%"+model.getStationName()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(productQualityService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}