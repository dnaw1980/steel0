package com.rss.platform.portal.service;

import java.util.List;

import com.rss.platform.portal.model.SessionInfo;
import com.rss.platform.portal.model.UserInfo;

public interface UserLoginService {
    SessionInfo userLogin(UserInfo userInfo);

	List<String> getUserInfoBySchedule(String schedule);

}
