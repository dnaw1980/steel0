package com.rss.steel_production.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import io.swagger.annotations.Api;
import com.rss.framework.QuotesUtil;
import com.rss.framework.UUIDGenerator;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import com.rss.steel_production.schedule.service.ProductionOrderService;
import com.rss.steel_production.schedule.model.ProductionOrder;
import com.rss.steel_production.schedule.model.ProductionOrderTO;

@RestController
@RequestMapping("/steel_production/schedule/productionOrder")
@Api
public class ProductionOrderController{
	 @Resource
	private ProductionOrderService productionOrderService;

	@PostMapping
	public Result add(@RequestBody ProductionOrder productionOrder){
		productionOrder.setProduct_orderUID(UUIDGenerator.generate());
		productionOrderService.insert(productionOrder);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody ProductionOrder productionOrder){
		productionOrderService.updateByPrimaryKey(productionOrder);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString=QuotesUtil.addQuotesToString(ids);
		productionOrderService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody ProductionOrderTO productionOrderTO){
		int pageNo=productionOrderTO.getPageNo();
		int pageSize=productionOrderTO.getPageSize();
		ProductionOrder productionOrder=productionOrderTO.getProductionOrder();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(productionOrderService.findByModel(productionOrder));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody ProductionOrderTO productionOrderTO){
		int pageNo=productionOrderTO.getPageNo();
		int pageSize=productionOrderTO.getPageSize();
		 ProductionOrder model=productionOrderTO.getProductionOrder();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(ProductionOrder.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getOrderNo()!=null){
				criteria.andLike("orderNo","%"+model.getOrderNo()+"%");
			}
			if(model.getOrderDate()!=null){
				criteria.andLike("orderDate","%"+new SimpleDateFormat("yyyy-MM-dd").format(model.getOrderDate())+"%");
			}
			if(model.getProductCode()!=null){
				criteria.andLike("productCode","%"+model.getProductCode()+"%");
			}
			if(model.getProductName()!=null){
				criteria.andLike("productName","%"+model.getProductName()+"%");
			}
			if(model.getProductType()!=null){
				criteria.andLike("productType","%"+model.getProductType()+"%");
			}
			if(model.getSteelGrade()!=null){
				criteria.andLike("steelGrade","%"+model.getSteelGrade()+"%");
			}
			if(model.getTargetTime()!=null){
				criteria.andLike("targetTime","%"+new SimpleDateFormat("yyyy-MM-dd").format(model.getTargetTime())+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(productionOrderService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}