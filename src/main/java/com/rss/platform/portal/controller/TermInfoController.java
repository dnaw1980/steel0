package com.rss.platform.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.platform.portal.model.TermInfo;
import com.rss.platform.portal.model.TermInfoTO;
import com.rss.platform.portal.service.TermInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/platform/portal/termInfo")
@Api
public class TermInfoController{
	 @Resource
	private TermInfoService termInfoService;

	@PostMapping
	public Result add(@RequestBody TermInfo termInfo){
		termInfo.setSystem_paramUID(UUIDGenerator.generate());
		Condition condition=new Condition(TermInfo.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("paraName",termInfo.getParamName());
		if (termInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("paraName值重复");
		termInfoService.insert(termInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody TermInfo termInfo){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(TermInfo.class);
		criteria=condition.createCriteria();
		criteria.andCondition("paramName = '" + termInfo.getParamName() + "'");
//		criteria.andEqualTo("paraName",termInfo.getParamName());
//		criteria.andNotEqualTo("system_paramUID",termInfo.getSystem_paramUID());
		if (termInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("paraName值重复");
		termInfoService.updateByPrimaryKey(termInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString= QuotesUtil.addQuotesToString(ids);
		termInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody TermInfoTO termInfoTO){
		int pageNo=termInfoTO.getPageNo();
		int pageSize=termInfoTO.getPageSize();
		TermInfo termInfo=termInfoTO.getTermInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(termInfoService.findByModel(termInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody TermInfoTO termInfoTO){
		int pageNo=termInfoTO.getPageNo();
		int pageSize=termInfoTO.getPageSize();
		 TermInfo model=termInfoTO.getTermInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(TermInfo.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getAppName()!=null){
				criteria.andLike("appName","%"+model.getAppName()+"%");
			}
			if(model.getParamName()!=null){
				criteria.andLike("paramName","%"+model.getParamName()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(termInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}