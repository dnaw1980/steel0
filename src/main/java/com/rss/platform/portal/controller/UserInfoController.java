package com.rss.platform.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.platform.portal.model.UserInfo;
import com.rss.platform.portal.model.UserInfoTO;
import com.rss.platform.portal.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/platform/portal/userInfo")
@Api
public class UserInfoController{
	 @Resource
	private UserInfoService userInfoService;

	@PostMapping
	public Result add(@RequestBody UserInfo userInfo){
		userInfo.setUser_infoUID(UUIDGenerator.generate());
		Condition condition=new Condition(UserInfo.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("userID",userInfo.getUserID());
		if (userInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("userID值重复");
		userInfoService.insert(userInfo);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody UserInfo userInfo){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(UserInfo.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("userID",userInfo.getUserID());
		criteria.andNotEqualTo("user_infoUID",userInfo.getUser_infoUID());
		if (userInfoService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("userID值重复");
		userInfoService.updateByPrimaryKey(userInfo);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString= QuotesUtil.addQuotesToString(ids);
		userInfoService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody UserInfoTO userInfoTO){
		int pageNo=userInfoTO.getPageNo();
		int pageSize=userInfoTO.getPageSize();
		UserInfo userInfo=userInfoTO.getUserInfo();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(userInfoService.findByModel(userInfo));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody UserInfoTO userInfoTO){
		int pageNo=userInfoTO.getPageNo();
		int pageSize=userInfoTO.getPageSize();
		 UserInfo model=userInfoTO.getUserInfo();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(UserInfo.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getUserName()!=null){
				criteria.andLike("userName","%"+model.getUserName()+"%");
			}
			if(model.getRoleName()!=null){
				criteria.andLike("roleName","%"+model.getRoleName()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(userInfoService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}