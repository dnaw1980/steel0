package com.rss.platform.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.platform.portal.model.AppInfo;
import com.rss.platform.portal.model.AppInfoTO;
import com.rss.platform.portal.service.AppInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/platform/portal/appInfo")
@Api
public class AppInfoController{
    @Resource
    private AppInfoService appInfoService;

    @PostMapping
    public Result add(@RequestBody AppInfo appInfo){
        appInfo.setApp_infoUID(UUIDGenerator.generate());
        Condition condition=new Condition(AppInfo.class);
        Condition.Criteria criteria;
        criteria=condition.createCriteria();
        criteria.andEqualTo("appID",appInfo.getAppID());
        if (appInfoService.findByCondition(condition).size()>0)
            return ResultGenerator.genFailResult("appID值重复");
        appInfoService.insert(appInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody AppInfo appInfo){
        Condition condition;
        Condition.Criteria criteria;
        condition=new Condition(AppInfo.class);
        criteria=condition.createCriteria();
        criteria.andEqualTo("appID",appInfo.getAppID());
        criteria.andNotEqualTo("app_infoUID",appInfo.getApp_infoUID());
        if (appInfoService.findByCondition(condition).size()>0)
            return ResultGenerator.genFailResult("appID值重复");
        appInfoService.updateByPrimaryKey(appInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<String> ids){
        String idString= QuotesUtil.addQuotesToString(ids);
        appInfoService.deleteByIds(idString);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/find")
    public Result find(@RequestBody AppInfoTO appInfoTO){
        int pageNo=appInfoTO.getPageNo();
        int pageSize=appInfoTO.getPageSize();
        AppInfo appInfo=appInfoTO.getAppInfo();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(appInfoService.findByModel(appInfo));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findBy")
    public Result findBy(@RequestBody AppInfoTO appInfoTO){
        int pageNo=appInfoTO.getPageNo();
        int pageSize=appInfoTO.getPageSize();
        AppInfo model=appInfoTO.getAppInfo();
        PageHelper.startPage(pageNo, pageSize);
        Condition condition=new Condition(AppInfo.class);
        Condition.Criteria criteria=condition.createCriteria();
        if (model!=null){
            if(model.getAppID()!=null){
                criteria.andLike("appID","%"+model.getAppID()+"%");
            }
            if(model.getAppName()!=null){
                criteria.andLike("appName","%"+model.getAppName()+"%");
            }
        }
        PageInfo pageInfo = new PageInfo(appInfoService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
