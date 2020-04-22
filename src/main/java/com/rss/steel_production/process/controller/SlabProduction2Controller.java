package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.SlabProduction2;
import com.rss.steel_production.process.model.SlabProduction2TO;
import com.rss.steel_production.process.service.SlabProduction2Service;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/slabProduction2")
@Api
public class SlabProduction2Controller {
	 @Resource
	private SlabProduction2Service slabProduction2Service;

	@PostMapping
	public Result add(@RequestBody SlabProduction2 slabProduction2){
		slabProduction2.setSlab_production2UID(UUIDGenerator.generate());
		slabProduction2Service.insert(slabProduction2);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody SlabProduction2 slabProduction2){
		slabProduction2Service.updateByPrimaryKey(slabProduction2);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		slabProduction2Service.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody SlabProduction2TO slabProduction2TO){
		int pageNo=slabProduction2TO.getPageNo();
		int pageSize=slabProduction2TO.getPageSize();
		SlabProduction2 slabProduction2=slabProduction2TO.getSlabProduction2();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(slabProduction2Service.findByModel(slabProduction2));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody SlabProduction2TO slabProduction2TO){
		int pageNo=slabProduction2TO.getPageNo();
		int pageSize=slabProduction2TO.getPageSize();
		SlabProduction2 model=slabProduction2TO.getSlabProduction2();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(SlabProduction2.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}
			if(model.getCastNo()!=null){
				criteria.andLike("castNo","%"+model.getCastNo()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(slabProduction2Service.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}