package com.rss.platform.portal.controller;

import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.platform.portal.model.SessionInfo;
import com.rss.platform.portal.model.UserInfo;
import com.rss.platform.portal.service.UserLoginService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userLogin")
@Api
public class UserLoginController {
    @Resource
    private UserLoginService userLoginService;

    @PostMapping
    public Result userLogin(@RequestBody UserInfo userInfo){
        SessionInfo sessionInfo =userLoginService.userLogin(userInfo);
        if(sessionInfo==null) {
            return ResultGenerator.genFailResult("用户名或密码错误");
        }else{
            return ResultGenerator.genSuccessResult("登录成功",sessionInfo);
        }
    }

}
