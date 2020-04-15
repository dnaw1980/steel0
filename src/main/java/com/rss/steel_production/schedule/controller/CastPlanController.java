package com.rss.steel_production.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.steel_production.foundation.service.ProcessStandardService;
import com.rss.steel_production.schedule.service.ChargePlanService;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import com.rss.steel_production.schedule.service.CastPlanService;
import com.rss.steel_production.schedule.model.CastPlan;
import com.rss.steel_production.schedule.model.CastPlanTO;

@RestController
@RequestMapping("/steel_production/schedule/castPlan")
@Api
public class CastPlanController{
	@Resource
	private CastPlanService castPlanService;
	@Autowired
	ChargePlanService chargePlanService;
	@Autowired
	ProcessStandardService processStandardService;

	//3.计划编制，起始日期，终止日期
	@RequestMapping("/prepareAuto")
	@ResponseBody
	public Result prepare(
			//	@DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			//	@DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
			Date start,Date end
	) {
		Date[] dateInterval = { start, end };
		castPlanService.deleteCastByTimes(dateInterval);
		castPlanService.createCastMap(dateInterval);
		List<CastPlan> calist = castPlanService.getCastListByTimes(dateInterval);
		if(calist == null || calist.size() == 0) {
			return ResultGenerator.genFailResult("fail");
		}else {
			return ResultGenerator.genSuccessResult(calist);
		}
	}

	//4.人工
	@PostMapping("/prepareManual")
	public Result prepareManual(@RequestBody List<String> uids) {
		CastPlan result = castPlanService.setCastManual(uids);
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
		castPlanService.deleteCastByStatus(status);
		return ResultGenerator.genSuccessResult();
	}

	//6.检查
	@PostMapping("/check")
	public Result check(@RequestBody List<CastPlan> chlist) {
		boolean result = castPlanService.checkIfRightByCastList(chlist);
		if(result) {
			return ResultGenerator.genSuccessResult();
		}else {
			return ResultGenerator.genFailResult("fail");
		}
	}

	@PostMapping
	public Result add(@RequestBody CastPlan castPlan){
		castPlan.setCast_planUID(UUIDGenerator.generate());
		Condition condition=new Condition(CastPlan.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("castNo",castPlan.getCastNo());
		if (castPlanService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("castNo值重复");
		castPlanService.insert(castPlan);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody CastPlan castPlan){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(CastPlan.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("castNo",castPlan.getCastNo());
		criteria.andNotEqualTo("cast_planUID",castPlan.getCast_planUID());
		if (castPlanService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("castNo值重复");
		castPlanService.updateByPrimaryKey(castPlan);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		castPlanService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody CastPlanTO castPlanTO){
		int pageNo=castPlanTO.getPageNo();
		int pageSize=castPlanTO.getPageSize();
		CastPlan castPlan=castPlanTO.getCastPlan();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(castPlanService.findByModel(castPlan));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody CastPlanTO castPlanTO){
		int pageNo=castPlanTO.getPageNo();
		int pageSize=castPlanTO.getPageSize();
		CastPlan model=castPlanTO.getCastPlan();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(CastPlan.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getCastNo()!=null){
				criteria.andLike("castNo","%"+model.getCastNo()+"%");
			}
			if(model.getPlanTime()!=null){
				criteria.andLike("planTime","%"+new SimpleDateFormat("yyyy-MM-dd").format(model.getPlanTime())+"%");
			}
			if(model.getPlanStatus()!=null){
				criteria.andLike("planStatus","%"+model.getPlanStatus()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(castPlanService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
