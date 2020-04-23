package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.CasInfo;
import com.rss.steel_production.process.model.CasInfoTO;
import com.rss.steel_production.process.service.CasInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/casInfo")
@Api
public class CasInfoController {
	 @Resource
	private CasInfoService casInfoService;

	@PostMapping
	public Result add(@RequestBody CasInfo casInfo){
		casInfo.setCas_infoUID(UUIDGenerator.generate());
		casInfoService.insert(casInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody CasInfo casInfo){
		casInfoService.updateByPrimaryKey(casInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/updateByPrimaryKeySelective")
	public Result updateByPrimaryKeySelective(@RequestBody CasInfo casInfo) {
		casInfoService.update(casInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		casInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody CasInfoTO casInfoTO){
		int pageNo=casInfoTO.getPageNo();
		int pageSize=casInfoTO.getPageSize();
		CasInfo casInfo=casInfoTO.getCasInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(casInfoService.findByModel(casInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody CasInfoTO casInfoTO){
		int pageNo=casInfoTO.getPageNo();
		int pageSize=casInfoTO.getPageSize();
		CasInfo model=casInfoTO.getCasInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(CasInfo.class);
		condition.setOrderByClause("acquireTime desc");
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}
			if(model.getStationNo()!=null){
				criteria.andLike("stationNo","%"+model.getStationNo()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(casInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
