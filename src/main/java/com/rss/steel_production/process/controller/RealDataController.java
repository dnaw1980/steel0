package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.IronInfo;
import com.rss.steel_production.process.model.IronInfoTO;
import com.rss.steel_production.process.model.RealData;
import com.rss.steel_production.process.model.RealDataTO;
import com.rss.steel_production.process.service.RealDataService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/steel_production/process/realData")
@Api
public class RealDataController {
	 @Resource
	private RealDataService realDataService;

	@PostMapping("/find")
	public Result find(@RequestBody RealDataTO realDataTO){
		int pageNo=realDataTO.getPageNo();
		int pageSize=realDataTO.getPageSize();
		RealData realData=realDataTO.getRealData();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(realDataService.findByModel(realData));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
