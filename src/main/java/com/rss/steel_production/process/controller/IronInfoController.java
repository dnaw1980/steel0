package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.IronInfo;
import com.rss.steel_production.process.model.IronInfoTO;
import com.rss.steel_production.process.service.IronInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/ironInfo")
@Api
public class IronInfoController {
	 @Resource
	private IronInfoService ironInfoService;

	@PostMapping
	public Result add(@RequestBody IronInfo ironInfo){
		ironInfo.setIron_infoUID(UUIDGenerator.generate());
		ironInfoService.insert(ironInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody IronInfo ironInfo){
		ironInfoService.updateByPrimaryKey(ironInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/updateByPrimaryKeySelective")
	public Result updateByPrimaryKeySelective(@RequestBody IronInfo ironInfo) {
		ironInfoService.update(ironInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		ironInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody IronInfoTO ironInfoTO){
		int pageNo=ironInfoTO.getPageNo();
		int pageSize=ironInfoTO.getPageSize();
		IronInfo ironInfo=ironInfoTO.getIronInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(ironInfoService.findByModel(ironInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody IronInfoTO ironInfoTO){
		int pageNo=ironInfoTO.getPageNo();
		int pageSize=ironInfoTO.getPageSize();
		IronInfo model=ironInfoTO.getIronInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(IronInfo.class);
		condition.setOrderByClause("acquireTime desc");
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}

			if(model.getBlastOrder()!=null){
				criteria.andLike("blastOrder","%"+model.getBlastOrder()+"%");
			}

			if(model.getBlastNo()!=null){
				criteria.andLike("blastNo","%"+model.getBlastNo()+"%");
			}

			if(model.getLadleNumber()!=null){
				criteria.andLike("ladleNumber","%"+model.getLadleNumber()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(ironInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
