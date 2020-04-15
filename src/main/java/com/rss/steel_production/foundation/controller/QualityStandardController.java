package com.rss.steel_production.foundation.controller;

import java.util.List;
import java.util.ArrayList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import com.rss.steel_production.foundation.service.QualityStandardService;
import com.rss.steel_production.foundation.model.QualityStandard;
import com.rss.steel_production.foundation.model.QualityStandardTO;

@RestController
@RequestMapping("/steel_production/foundation/qualityStandard")
@Api
public class QualityStandardController{
	 @Resource
	private QualityStandardService qualityStandardService;

	@PostMapping
	public Result add(@RequestBody QualityStandard qualityStandard){
		qualityStandard.setQuality_standardUID(UUIDGenerator.generate());
		qualityStandardService.insert(qualityStandard);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody QualityStandard qualityStandard){
		qualityStandardService.updateByPrimaryKey(qualityStandard);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		qualityStandardService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody QualityStandardTO qualityStandardTO){
		int pageNo=qualityStandardTO.getPageNo();
		int pageSize=qualityStandardTO.getPageSize();
		QualityStandard qualityStandard=qualityStandardTO.getQualityStandard();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(qualityStandardService.findByModel(qualityStandard));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody QualityStandardTO qualityStandardTO){
		int pageNo=qualityStandardTO.getPageNo();
		int pageSize=qualityStandardTO.getPageSize();
		 QualityStandard model=qualityStandardTO.getQualityStandard();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(QualityStandard.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getStandardNo()!=null){
				criteria.andLike("standardNo","%"+model.getStandardNo()+"%");
			}
			if(model.getStandardType()!=null){
				criteria.andLike("standardType","%"+model.getStandardType()+"%");
			}
			if(model.getProductName()!=null){
				criteria.andLike("productName","%"+model.getProductName()+"%");
			}
			if(model.getQualityLevel()!=null){
				criteria.andLike("qualityLevel","%"+model.getQualityLevel()+"%");
			}
			if(model.getQualityIndexes()!=null){
				criteria.andLike("qualityIndexes","%"+model.getQualityIndexes()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(qualityStandardService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}