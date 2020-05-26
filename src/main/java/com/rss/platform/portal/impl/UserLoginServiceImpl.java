package com.rss.platform.portal.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rss.platform.portal.dao.RoleInfoDAO;
import com.rss.platform.portal.dao.UserInfoDAO;
import com.rss.platform.portal.model.RoleInfo;
import com.rss.platform.portal.model.SessionInfo;
import com.rss.platform.portal.model.UserInfo;
import com.rss.platform.portal.service.UserLoginService;

import tk.mybatis.mapper.entity.Condition;

@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserInfoDAO userInfoMapper;
    //    @Resource
//    private UserLoginMapper userLoginMapper;
    @Resource
    private RoleInfoDAO roleInfoMapper;
    @Autowired
	private SqlSessionTemplate sqlSession;

    @Override
    public SessionInfo userLogin(UserInfo userInfo) {
        if (userInfo.getUserName() != null && userInfo.getPassword() != null) {
            Condition condition = new Condition(UserInfo.class);
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("userName", userInfo.getUserName());
            List<UserInfo> userInfoList = userInfoMapper.selectByCondition(condition);
            if (userInfoList.size() > 0 && userInfo.getPassword().equals(userInfoList.get(0).getPassword())) {
                SessionInfo sessionInfo = new SessionInfo(userInfoList.get(0));
                Condition condition1 = new Condition(RoleInfo.class);
                Condition.Criteria criteria1 = condition1.createCriteria();
                criteria1.andEqualTo("roleID", userInfoList.get(0).getRoleID());
                List<RoleInfo> roleInfoList = roleInfoMapper.selectByCondition(condition1);
                if (roleInfoList.size() > 0) {
                    sessionInfo.setAppID(roleInfoList.get(0).getAppID());
                    sessionInfo.setAppName(roleInfoList.get(0).getAppName());
                    sessionInfo.setRoleName(roleInfoList.get(0).getRoleName());
                    sessionInfo.setRolePermission(roleInfoList.get(0).getPermission());
                    sessionInfo.setTableContent(roleInfoList.get(0).getTableContent());
                }
                return sessionInfo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

	@Override
	public List<String> getUserInfoBySchedule() {
		List<String> userNames = sqlSession.selectList("com.rss.platform.portal.dao.UserInfoDao.getUserInfoBySchedule");
		return userNames;
	}
}
