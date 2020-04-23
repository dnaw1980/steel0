package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.BilletProduction;
import com.rss.steel_production.process.model.BilletProductionTO;
import com.rss.steel_production.process.service.BilletProductionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/billetProduction")
@Api
public class BilletProductionController {
	 @Resource
	private BilletProductionService billetProductionService;

	@PostMapping
	public Result add(@RequestBody BilletProduction billetProduction){
		billetProduction.setBillet_productionUID(UUIDGenerator.generate());
		billetProductionService.insert(billetProduction);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody BilletProduction billetProduction){
		billetProductionService.updateByPrimaryKey(billetProduction);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/updateByPrimaryKeySelective")
	public Result updateByPrimaryKeySelective(@RequestBody BilletProduction billetProduction) {
		billetProductionService.update(billetProduction);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		billetProductionService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody BilletProductionTO billetProductionTO){
		int pageNo=billetProductionTO.getPageNo();
		int pageSize=billetProductionTO.getPageSize();
		BilletProduction billetProduction=billetProductionTO.getBilletProduction();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(billetProductionService.findByModel(billetProduction));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody BilletProductionTO billetProductionTO){
		int pageNo=billetProductionTO.getPageNo();
		int pageSize=billetProductionTO.getPageSize();
		BilletProduction model=billetProductionTO.getBilletProduction();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(BilletProduction.class);
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
		PageInfo pageInfo = new PageInfo(billetProductionService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
