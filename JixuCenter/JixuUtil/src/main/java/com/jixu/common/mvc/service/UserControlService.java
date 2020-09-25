package com.jixu.common.mvc.service;

import java.util.HashMap;
import java.util.List;

import com.jixu.common.pojo.PreVerify;
import com.jixu.common.pojo.UserHisPwd;
import com.jixu.common.pojo.UserRole;
import com.jixu.common.pojo.UserRoleGroup;
import com.jixu.common.pojo.Users;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.Pages;

/** 
 * @ClassName 		UserControlService
 * @author  		Pan Wei 
 * @Date 			2016年11月26日 下午11:58:43 	
 * @Description		[]  
 */

public interface UserControlService {
	
	public ResultContainer login(String useracct, String pwd, String ip);
	
	public void createRole(UserRole role);
	
	public Users loadUserByID(int userid);
	
	public List<UserRole> loadUserRoleList();
	
	public void createUser(Users user);
	
	public HashMap<Integer, String> loadRoleTypes();
	
	public List<Users> loadUerList();
	
	public void updateUser(Users user);
	
	public void createUserRoleGroup(UserRoleGroup group);
	
	public List<UserRoleGroup> loadUserRoleGroupList();
	
	public void createRoleGroupTypeMap(int groupID, List<Integer> actionList);
	
	public HashMap<Integer, String> loadRoleTypeHM(int groupID);
	
	public void createUserRoleGroupMap(int roleID, List<Integer> groupIDList);
	
	public HashMap<Integer, String> loadRoleGroupHM(int roleID);
	
	public void updateUserOpRole(Users user, List<String> operRoleList);
	
	public void reflushRoleMapListHM();
	
	//录入原始密码
	public void createUserHisPwd(UserHisPwd uhp);
	
	//通过userid查询用户修改密码记录
	public List<UserHisPwd> getUserHisPwdList(int userid, Pages<UserHisPwd> pags);

	public void createVerifyConfig(int groupID, List<Object> actionList);
	
	public HashMap<String, String> loadVerifyConfig(int groupID);
	
	public void updatePreVerify(PreVerify pv);
	
	public Boolean verifyUrlAccess(Users user,String checkURL);
}
