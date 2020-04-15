package com.rss.platform.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.platform.portal.model.DataObject;
import com.rss.platform.portal.model.DataObjectTO;
import com.rss.platform.portal.service.DataObjectService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

//import com.rss.framework.generator.ConfigFileGenerator;
//import com.rss.framework.generator.SMMClassGenerator;
//import com.rss.framework.generator.SourceGenerator;

@RestController
@RequestMapping("/platform/portal/dataObject")
@Api
public class DataObjectController{
	 @Resource
	private DataObjectService dataObjectService;

	@PostMapping
	public Result add(@RequestBody DataObject dataObject){
		dataObject.setData_objectUID(UUIDGenerator.generate());
		Condition condition=new Condition(DataObject.class);
		Condition.Criteria criteria;
		criteria=condition.createCriteria();
		criteria.andEqualTo("appID",dataObject.getAppID());
		criteria.andEqualTo("objectID",dataObject.getObjectID());
		if (dataObjectService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("appID,objectID值重复");
		dataObjectService.insert(dataObject);
		return ResultGenerator.genSuccessResult();
	}

	@PutMapping
	public Result update(@RequestBody DataObject dataObject){
		Condition condition;
		Condition.Criteria criteria;
		condition=new Condition(DataObject.class);
		criteria=condition.createCriteria();
		criteria.andEqualTo("appID",dataObject.getAppID());
		criteria.andEqualTo("objectID",dataObject.getObjectID());
		criteria.andNotEqualTo("data_objectUID",dataObject.getData_objectUID());
		if (dataObjectService.findByCondition(condition).size()>0)
			return ResultGenerator.genFailResult("appID,objectID值重复");
		dataObjectService.updateByPrimaryKey(dataObject);
		return ResultGenerator.genSuccessResult();
	}

	@DeleteMapping
	public Result delete(@RequestBody List<String> ids){
		String idString= QuotesUtil.addQuotesToString(ids);
		dataObjectService.deleteByIds(idString);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/find")
	public Result find(@RequestBody DataObjectTO dataObjectTO){
		int pageNo=dataObjectTO.getPageNo();
		int pageSize=dataObjectTO.getPageSize();
		DataObject dataObject=dataObjectTO.getDataObject();
		PageHelper.startPage(pageNo, pageSize);
		PageInfo pageInfo = new PageInfo(dataObjectService.findByModel(dataObject));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/findBy")
	public Result findBy(@RequestBody DataObjectTO dataObjectTO){
		int pageNo=dataObjectTO.getPageNo();
		int pageSize=dataObjectTO.getPageSize();
		DataObject model=dataObjectTO.getDataObject();
		PageHelper.startPage(pageNo, pageSize);
		Condition condition=new Condition(DataObject.class);
		Condition.Criteria criteria=condition.createCriteria();
		if (model!=null){
			if(model.getObjectID()!=null){
				criteria.andLike("objectID","%"+model.getObjectID()+"%");
			}
			if(model.getObjectName()!=null){
				criteria.andLike("objectName","%"+model.getObjectName()+"%");
			}
			if(model.getAppID()!=null){
				criteria.andLike("appID","%"+model.getAppID()+"%");
			}
		}
		PageInfo pageInfo = new PageInfo(dataObjectService.findByCondition(condition));
		return ResultGenerator.genSuccessResult(pageInfo);
	}

//	@PostMapping("/upload")
//	public Result upload(
////			@RequestParam FormData formData
//			@RequestParam("file") MultipartFile file
////			,@RequestParam("appID") String appID,@RequestParam("moduleID") String moduleID
//	){
////		MultipartFile file = formData.getFile();
////		String appID = formData.getAppID();
////		String moduleID = formData.getModuleID();
//
//		String appID = "bulk_logistic";
//		String moduleID ="dosing";
//		HashMap<String,DataObject> objectMap=new DataObjectLoader().load(file);
//		for (DataObject dataObject : objectMap.values()) {
//			dataObject.setData_objectUID(UUIDGenerator.generate());
//			dataObject.setAppID(appID);
//			dataObject.setModuleID(moduleID);
//		}
//		SourceGenerator generator=new SourceGenerator(null, objectMap);
//		generator.generateSql();
//
//		for (DataObject dataObject : objectMap.values()) {
//			System.out.println(dataObject.getObjectID()+"插入数据库开始");
//			Condition condition=new Condition(DataObject.class);
//			Condition.Criteria criteria;
//			criteria=condition.createCriteria();
//			criteria.andEqualTo("objectType",dataObject.getObjectType());
//			criteria.andEqualTo("objectID",dataObject.getObjectID());
//			criteria.andEqualTo("referTable",dataObject.getReferTable());
//			if (dataObjectService.findByCondition(condition).size()>0)
//				return ResultGenerator.genFailResult("objectType,objectID,referTable值重复");
//			dataObjectService.insert(dataObject);
//			System.out.println(dataObject.getObjectID()+"插入数据库完成");
//
//			generator = new SourceGenerator(dataObject,objectMap);
//			generator.generateBackground();
//			generator.generateFrontground();
//		}
//		System.out.println("生成完成");
//		return ResultGenerator.genSuccessResult();
//	}
}