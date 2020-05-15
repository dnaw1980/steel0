package com.rss.steel_production.foundation.controller;

import java.util.List;
import java.util.ArrayList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import com.rss.steel_production.foundation.service.SteelDeviceService;
import com.rss.steel_production.foundation.model.SteelDevice;
import com.rss.steel_production.foundation.model.SteelDeviceTO;

@RestController
@RequestMapping("/steel_production/foundation/steelDevice")
@Api
public class SteelDeviceController{
	 @Resource
	private SteelDeviceService steelDeviceService;

	@PostMapping
	public Result add(@RequestBody SteelDevice steelDevice){
		steelDevice.setSteel_deviceUID(UUIDGenerator.generate());
		Condition condition=new Condition(SteelDevice.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("deviceNo",steelDevice.getDeviceNo());
		criteria.andEqualTo("deviceName",steelDevice.getDeviceName());
		if (steelDeviceService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("deviceNo或deviceName值重复");
		steelDeviceService.insert(steelDevice);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody SteelDevice steelDevice){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(SteelDevice.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("deviceNo",steelDevice.getDeviceNo());
		criteria.andEqualTo("deviceName",steelDevice.getDeviceName());
		criteria.andNotEqualTo("steel_deviceUID",steelDevice.getSteel_deviceUID());
		if (steelDeviceService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("deviceNo或deviceName值重复");
		steelDeviceService.updateByPrimaryKey(steelDevice);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		steelDeviceService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody SteelDeviceTO steelDeviceTO){
		int pageNo=steelDeviceTO.getPageNo();
		int pageSize=steelDeviceTO.getPageSize();
		SteelDevice steelDevice=steelDeviceTO.getSteelDevice();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(steelDeviceService.findByModel(steelDevice));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody SteelDeviceTO steelDeviceTO){
		int pageNo=steelDeviceTO.getPageNo();
		int pageSize=steelDeviceTO.getPageSize();
		 SteelDevice model=steelDeviceTO.getSteelDevice();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(SteelDevice.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getDeviceNo()!=null){
				criteria.andLike("deviceNo","%"+model.getDeviceNo()+"%");
			}
			if(model.getDeviceName()!=null){
				criteria.andLike("deviceName","%"+model.getDeviceName()+"%");
			}
			if(model.getDeviceType()!=null){
				criteria.andLike("deviceType","%"+model.getDeviceType()+"%");
			}
			if(model.getStationName()!=null){
				criteria.andLike("stationName","%"+model.getStationName()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(steelDeviceService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}