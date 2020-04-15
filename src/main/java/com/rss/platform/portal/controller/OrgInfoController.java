package com.rss.platform.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.platform.portal.model.OrgInfo;
import com.rss.platform.portal.model.OrgInfoTO;
import com.rss.platform.portal.service.OrgInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/platform/portal/orgInfo")
@Api
public class OrgInfoController{
	 @Resource
	private OrgInfoService orgInfoService;

	@PostMapping
	public Result add(@RequestBody OrgInfo orgInfo){
		orgInfo.setOrg_infoUID(UUIDGenerator.generate());
		Condition condition=new Condition(OrgInfo.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("orgCode",orgInfo.getOrgCode());
		if (orgInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("orgCode值重复");
		criteria=condition.createCriteria();
		criteria.andEqualTo("orgName",orgInfo.getOrgName());
		if (orgInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("orgName值重复");
		orgInfoService.insert(orgInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody OrgInfo orgInfo){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(OrgInfo.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("orgCode",orgInfo.getOrgCode());
		criteria.andNotEqualTo("org_infoUID",orgInfo.getOrg_infoUID());
		if (orgInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("orgCode值重复");
		condition=new Condition(OrgInfo.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("orgName",orgInfo.getOrgName());
		criteria.andNotEqualTo("org_infoUID",orgInfo.getOrg_infoUID());
		if (orgInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("orgName值重复");
		orgInfoService.updateByPrimaryKey(orgInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString= QuotesUtil.addQuotesToString(ids);
		orgInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody OrgInfoTO orgInfoTO){
		int pageNo=orgInfoTO.getPageNo();
		int pageSize=orgInfoTO.getPageSize();
		OrgInfo orgInfo=orgInfoTO.getOrgInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(orgInfoService.findByModel(orgInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody OrgInfoTO orgInfoTO){
		int pageNo=orgInfoTO.getPageNo();
		int pageSize=orgInfoTO.getPageSize();
		 OrgInfo model=orgInfoTO.getOrgInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(OrgInfo.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getOrgCode()!=null){
				criteria.andLike("orgCode","%"+model.getOrgCode()+"%");
			}
			if(model.getOrgName()!=null){
				criteria.andLike("orgName","%"+model.getOrgName()+"%");
			}
			if(model.getOrgType()!=null){
				criteria.andLike("orgType","%"+model.getOrgType()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(orgInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}