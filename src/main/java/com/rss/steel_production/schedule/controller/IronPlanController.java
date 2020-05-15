package com.rss.steel_production.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.netty_client.EchoClient;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import com.rss.steel_production.schedule.service.IronPlanService;
import com.rss.steel_production.schedule.model.IronPlan;
import com.rss.steel_production.schedule.model.IronPlanTO;

@RestController
@RequestMapping("/steel_production/schedule/ironPlan")
@Api
public class IronPlanController{
	 @Resource
	private IronPlanService ironPlanService;

	@PostMapping
	public Result add(@RequestBody IronPlan ironPlan){
		ironPlan.setIron_planUID(UUIDGenerator.generate());
		Condition condition=new Condition(IronPlan.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("ironNo",ironPlan.getIronNo());
//		criteria.andEqualTo("seqNo",ironPlan.getSeqNo());
		if (ironPlanService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("ironNo值重复");
		ironPlanService.insert(ironPlan);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody IronPlan ironPlan){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(IronPlan.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("ironNo",ironPlan.getIronNo());
		criteria.andNotEqualTo("iron_planUID",ironPlan.getIron_planUID());
		if (ironPlanService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("ironNo值重复");
		ironPlanService.updateByPrimaryKey(ironPlan);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		ironPlanService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody IronPlanTO ironPlanTO){
		int pageNo=ironPlanTO.getPageNo();
		int pageSize=ironPlanTO.getPageSize();
		IronPlan ironPlan=ironPlanTO.getIronPlan();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(ironPlanService.findByModel(ironPlan));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody IronPlanTO ironPlanTO){
		int pageNo=ironPlanTO.getPageNo();
		int pageSize=ironPlanTO.getPageSize();
		 IronPlan model=ironPlanTO.getIronPlan();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(IronPlan.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getIronNo()!=null){
				criteria.andLike("ironNo","%"+model.getIronNo()+"%");
			}
//			if(model.getTappingStart()!=null){
//				criteria.andLike("tappingStart","%"+new SimpleDateFormat("yyyy-MM-dd").format(model.getTappingStart())+"%");
//			}
//			if(model.getTappingEnd()!=null){
//				criteria.andLike("tappingEnd","%"+new SimpleDateFormat("yyyy-MM-dd").format(model.getTappingEnd())+"%");
//			}
			if(model.getPlanStatus()!=null){
				criteria.andLike("planStatus","%"+model.getPlanStatus()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(ironPlanService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/send")
	public Result send(@RequestBody IronPlan ironPlan){
		String message = "<request sender='scheduleServer' type='ironPlan'>" +
				"<ironPlan>" +
				"<iron_planUID>"+ironPlan.getIron_planUID()+"</iron_planUID>" +
				"<ironNo>"+ironPlan.getIronNo()+"</ironNo>" +
				"<ladleCount>"+ironPlan.getLadleCount()+"</ladleCount>" +
				"<tappingStart>"+ironPlan.getTappingStart()+"</tappingStart>" +
				"<tappingEnd>"+ironPlan.getTappingEnd()+"</tappingEnd>" +
				"<stationName>"+ironPlan.getStationName()+"</stationName>" +
				"</ironPlan>" +
				"</request>";
		EchoClient.getInstance().cH.sendToch(message);
		return ResultGenerator.genSuccessResult();
	}
}
