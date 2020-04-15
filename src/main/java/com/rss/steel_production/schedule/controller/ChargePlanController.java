package com.rss.steel_production.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.schedule.service.ProductionOrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import com.rss.steel_production.schedule.service.ChargePlanService;
import com.rss.steel_production.schedule.model.ChargePlan;
import com.rss.steel_production.schedule.model.ChargePlanTO;

@RestController
@RequestMapping("/steel_production/schedule/chargePlan")
@Api
public class ChargePlanController{
	@Resource
	private ChargePlanService chargePlanService;
	@Autowired
	ProductionOrderService productionOrderService;
	@Autowired
	ProcessStandardService processStandardService;

	//3.自动，计划编制，起始日期，终止日期
	@RequestMapping("/prepareAuto")
	@ResponseBody
	public Result prepareAuto(Date start, Date end) {
		Date[] dateInterval = { start, end};
		System.out.println("start:"+dateInterval[0]+"end:"+dateInterval[1]);
		chargePlanService.deleteChargeByTimes(dateInterval);
		System.out.println("delete over.");
		chargePlanService.createChargeMap(dateInterval);
		System.out.println("create over.");
		List<ChargePlan> chlist = chargePlanService.getChargeListByTimes(dateInterval);
		System.out.println("get over.");
		if(chlist == null || chlist.size() == 0) {
			return ResultGenerator.genFailResult("fail");
		}else {
			return ResultGenerator.genSuccessResult(chlist);
		}
	}

	//4.人工
	@PostMapping("/prepareManual")
	public Result prepareManual(@RequestBody List<String> uids) {
		ChargePlan result = chargePlanService.setChargeManual(uids);
		if(result != null) {
			return ResultGenerator.genSuccessResult(result);
		}else {
			return ResultGenerator.genFailResult("fail");
		}
	}

	//5.删除，通过状态
	@RequestMapping("/abandon")
	@ResponseBody
	public Result abandon() {
		String status = "";
		chargePlanService.deleteChargeByStatus(status);
		return ResultGenerator.genSuccessResult();
	}

	//6.检查
	@PostMapping("/check")
	public Result check(@RequestBody List<ChargePlan> chlist) {
		boolean result = chargePlanService.checkIfRightByChargeList(chlist);
		if(result) {
			return ResultGenerator.genSuccessResult();
		}else {
			return ResultGenerator.genFailResult("fail");
		}
	}

	@PostMapping
	public Result add(@RequestBody ChargePlan chargePlan){
		chargePlan.setCharge_planUID(UUIDGenerator.generate());
		Condition condition=new Condition(ChargePlan.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("chargeNo",chargePlan.getChargeNo());
		if (chargePlanService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("chargeNo值重复");
		chargePlanService.insert(chargePlan);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody ChargePlan chargePlan){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(ChargePlan.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("chargeNo",chargePlan.getChargeNo());
		criteria.andNotEqualTo("charge_planUID",chargePlan.getCharge_planUID());
		if (chargePlanService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("chargeNo值重复");
		chargePlanService.updateByPrimaryKey(chargePlan);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		chargePlanService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody ChargePlanTO chargePlanTO){
		int pageNo=chargePlanTO.getPageNo();
		int pageSize=chargePlanTO.getPageSize();
		ChargePlan chargePlan=chargePlanTO.getChargePlan();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(chargePlanService.findByModel(chargePlan));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody ChargePlanTO chargePlanTO){
		int pageNo=chargePlanTO.getPageNo();
		int pageSize=chargePlanTO.getPageSize();
		ChargePlan model=chargePlanTO.getChargePlan();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(ChargePlan.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getChargeNo()!=null){
				criteria.andLike("chargeNo","%"+model.getChargeNo()+"%");
			}
			if(model.getPlanTime()!=null){
				criteria.andLike("planTime","%"+new SimpleDateFormat("yyyy-MM-dd").format(model.getPlanTime())+"%");
			}
			if(model.getPlanStatus()!=null){
				criteria.andLike("planStatus","%"+model.getPlanStatus()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(chargePlanService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

}
