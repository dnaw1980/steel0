package com.rss.platform.portal.service;

import com.rss.platform.portal.model.SessionInfo;
import com.rss.platform.portal.model.UserInfo;

public interface UserLoginService {
    SessionInfo userLogin(UserInfo userInfo);

}
