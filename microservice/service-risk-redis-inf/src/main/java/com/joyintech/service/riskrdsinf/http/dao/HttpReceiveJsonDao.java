package com.joyintech.service.riskrdsinf.http.dao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by zy_wangjunteng on 2019/10/18.
 */

@Repository
public interface HttpReceiveJsonDao {
    int selectUserByUserId(Map<String, Object> cons);
    int insertUser(Map<String, Object> cons);
    int updateUser(Map<String, Object> cons);
    int selectRoleByRoleId(Map<String, Object> cons);
    int insertRole(Map<String, Object> cons);
    int updateRole(Map<String, Object> cons);
    int selectUserRoleById(Map<String, Object> cons);
    int insertUserRole(Map<String, Object> cons);
    int updateUserRole(Map<String, Object> cons);
    int selectUserGroupById(Map<String, Object> cons);
    int insertUserGroup(Map<String, Object> cons);
    int updateUserGroup(Map<String, Object> cons);
    int deleteUserGroup(Map result);
	String getUserName(String userId);
	@SuppressWarnings("rawtypes")
	int deleteAllUserRole(Map result);
	@SuppressWarnings("rawtypes")
	String selectUserName(Map result);
	@SuppressWarnings("rawtypes")
	int updateUserName(Map result);
}
