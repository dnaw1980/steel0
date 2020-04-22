package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.LfInfo;
import com.rss.steel_production.process.model.LfInfoTO;
import com.rss.steel_production.process.service.LfInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/lfInfo")
@Api
public class LfInfoController {
	 @Resource
	private LfInfoService lfInfoService;

	@PostMapping
	public Result add(@RequestBody LfInfo lfInfo){
		lfInfo.setLf_infoUID(UUIDGenerator.generate());
		lfInfoService.insert(lfInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody LfInfo lfInfo){
		lfInfoService.updateByPrimaryKey(lfInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		lfInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody LfInfoTO lfInfoTO){
		int pageNo=lfInfoTO.getPageNo();
		int pageSize=lfInfoTO.getPageSize();
		LfInfo lfInfo=lfInfoTO.getLfInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(lfInfoService.findByModel(lfInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody LfInfoTO lfInfoTO){
		int pageNo=lfInfoTO.getPageNo();
		int pageSize=lfInfoTO.getPageSize();
		LfInfo model=lfInfoTO.getLfInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(LfInfo.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}
			if(model.getStationNo()!=null){
				criteria.andLike("stationNo","%"+model.getStationNo()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(lfInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}