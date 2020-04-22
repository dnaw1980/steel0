package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.DesulfuriInfo;
import com.rss.steel_production.process.model.DesulfuriInfoTO;
import com.rss.steel_production.process.service.DesulfuriInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/desulfuriInfo")
@Api
public class DesulfuriInfoController {
	 @Resource
	private DesulfuriInfoService desulfuriInfoService;

	@PostMapping
	public Result add(@RequestBody DesulfuriInfo desulfuriInfo){
		desulfuriInfo.setDesulfuri_infoUID(UUIDGenerator.generate());
		desulfuriInfoService.insert(desulfuriInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody DesulfuriInfo desulfuriInfo){
		desulfuriInfoService.updateByPrimaryKey(desulfuriInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		desulfuriInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody DesulfuriInfoTO desulfuriInfoTO){
		int pageNo=desulfuriInfoTO.getPageNo();
		int pageSize=desulfuriInfoTO.getPageSize();
		DesulfuriInfo desulfuriInfo=desulfuriInfoTO.getDesulfuriInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(desulfuriInfoService.findByModel(desulfuriInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody DesulfuriInfoTO desulfuriInfoTO){
		int pageNo=desulfuriInfoTO.getPageNo();
		int pageSize=desulfuriInfoTO.getPageSize();
		DesulfuriInfo model=desulfuriInfoTO.getDesulfuriInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(DesulfuriInfo.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}
			if(model.getStationNo()!=null){
				criteria.andLike("stationNo","%"+model.getStationNo()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(desulfuriInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}