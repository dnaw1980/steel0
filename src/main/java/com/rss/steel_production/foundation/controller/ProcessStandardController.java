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
import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.foundation.model.ProcessStandard;
import com.rss.steel_production.foundation.model.ProcessStandardTO;

@RestController
@RequestMapping("/steel_production/foundation/processStandard")
@Api
public class ProcessStandardController{
	 @Resource
	private ProcessStandardService processStandardService;

	@PostMapping
	public Result add(@RequestBody ProcessStandard processStandard){
		processStandard.setProcess_standardeUID(UUIDGenerator.generate());
		processStandardService.insert(processStandard);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody ProcessStandard processStandard){
		processStandardService.updateByPrimaryKey(processStandard);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		processStandardService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody ProcessStandardTO processStandardTO){
		int pageNo=processStandardTO.getPageNo();
		int pageSize=processStandardTO.getPageSize();
		ProcessStandard processStandard=processStandardTO.getProcessStandard();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(processStandardService.findByModel(processStandard));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody ProcessStandardTO processStandardTO){
		int pageNo=processStandardTO.getPageNo();
		int pageSize=processStandardTO.getPageSize();
		 ProcessStandard model=processStandardTO.getProcessStandard();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(ProcessStandard.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getStandardNo()!=null){
				criteria.andLike("standardNo","%"+model.getStandardNo()+"%");
			}
			if(model.getItemID()!=null){
				criteria.andLike("itemID","%"+model.getItemID()+"%");
			}
			if(model.getItemName()!=null){
				criteria.andLike("itemName","%"+model.getItemName()+"%");
			}
			if(model.getItemType()!=null){
				criteria.andLike("itemType","%"+model.getItemType()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(processStandardService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}