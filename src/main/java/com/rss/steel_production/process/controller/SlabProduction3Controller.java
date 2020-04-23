package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.SlabProduction3;
import com.rss.steel_production.process.model.SlabProduction3TO;
import com.rss.steel_production.process.service.SlabProduction3Service;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/slabProduction3")
@Api
public class SlabProduction3Controller {
	 @Resource
	private SlabProduction3Service slabProduction3Service;

	@PostMapping
	public Result add(@RequestBody SlabProduction3 slabProduction3){
		slabProduction3.setSlab_production3UID(UUIDGenerator.generate());
		slabProduction3Service.insert(slabProduction3);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody SlabProduction3 slabProduction3){
		slabProduction3Service.updateByPrimaryKey(slabProduction3);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/updateByPrimaryKeySelective")
	public Result updateByPrimaryKeySelective(@RequestBody SlabProduction3 slabProduction3) {
		slabProduction3Service.update(slabProduction3);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		slabProduction3Service.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody SlabProduction3TO slabProduction3TO){
		int pageNo=slabProduction3TO.getPageNo();
		int pageSize=slabProduction3TO.getPageSize();
		SlabProduction3 slabProduction3=slabProduction3TO.getSlabProduction3();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(slabProduction3Service.findByModel(slabProduction3));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody SlabProduction3TO slabProduction3TO){
		int pageNo=slabProduction3TO.getPageNo();
		int pageSize=slabProduction3TO.getPageSize();
		SlabProduction3 model=slabProduction3TO.getSlabProduction3();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(SlabProduction3.class);
		condition.setOrderByClause("acquireTime desc");
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}
			if(model.getCastNo()!=null){
				criteria.andLike("castNo","%"+model.getCastNo()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(slabProduction3Service.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
