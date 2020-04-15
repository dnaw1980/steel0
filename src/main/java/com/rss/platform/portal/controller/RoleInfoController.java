package com.rss.platform.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.platform.portal.model.RoleInfo;
import com.rss.platform.portal.model.RoleInfoTO;
import com.rss.platform.portal.service.RoleInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/platform/portal/roleInfo")
@Api
public class RoleInfoController{
	 @Resource
	private RoleInfoService roleInfoService;

	@PostMapping
	public Result add(@RequestBody RoleInfo roleInfo){
		roleInfo.setRole_infoUID(UUIDGenerator.generate());
		Condition condition=new Condition(RoleInfo.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("appID",roleInfo.getAppID());
		criteria.andEqualTo("roleName",roleInfo.getRoleName());
		if (roleInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("appID,roleName值重复");
		roleInfoService.insert(roleInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody RoleInfo roleInfo){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(RoleInfo.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("appID",roleInfo.getAppID());
		criteria.andEqualTo("roleName",roleInfo.getRoleName());
		criteria.andNotEqualTo("role_infoUID",roleInfo.getRole_infoUID());
		if (roleInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("appID,roleName值重复");
		roleInfoService.updateByPrimaryKey(roleInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString= QuotesUtil.addQuotesToString(ids);
		roleInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody RoleInfoTO roleInfoTO){
		int pageNo=roleInfoTO.getPageNo();
		int pageSize=roleInfoTO.getPageSize();
		RoleInfo roleInfo=roleInfoTO.getRoleInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(roleInfoService.findByModel(roleInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody RoleInfoTO roleInfoTO){
		int pageNo=roleInfoTO.getPageNo();
		int pageSize=roleInfoTO.getPageSize();
		 RoleInfo model=roleInfoTO.getRoleInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(RoleInfo.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getRoleID()!=null){
				criteria.andLike("roleID","%"+model.getRoleID()+"%");
			}
			if(model.getRoleName()!=null){
				criteria.andLike("roleName","%"+model.getRoleName()+"%");
			}
			if(model.getAppName()!=null){
				criteria.andLike("appName","%"+model.getAppName()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(roleInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}