package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.ConvererInfo;
import com.rss.steel_production.process.model.ConvererInfoTO;
import com.rss.steel_production.process.service.ConvererInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/convererInfo")
@Api
public class ConvererInfoController {
	 @Resource
	private ConvererInfoService convererInfoService;

	@PostMapping
	public Result add(@RequestBody ConvererInfo convererInfo){
		convererInfo.setConverer_infoUID(UUIDGenerator.generate());
		convererInfoService.insert(convererInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody ConvererInfo convererInfo){
		convererInfoService.updateByPrimaryKey(convererInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		convererInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody ConvererInfoTO convererInfoTO){
		int pageNo=convererInfoTO.getPageNo();
		int pageSize=convererInfoTO.getPageSize();
		ConvererInfo convererInfo=convererInfoTO.getConvererInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(convererInfoService.findByModel(convererInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody ConvererInfoTO convererInfoTO){
		int pageNo=convererInfoTO.getPageNo();
		int pageSize=convererInfoTO.getPageSize();
		ConvererInfo model=convererInfoTO.getConvererInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(ConvererInfo.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}
			if(model.getStationNo()!=null){
				criteria.andLike("stationNo","%"+model.getStationNo()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(convererInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}