package com.jixu.common.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.mvc.service.UserControlService;
import com.jixu.common.pojo.UserHisPwd;
import com.jixu.common.pojo.UserOpRuleMap;
import com.jixu.common.pojo.UserRole;
import com.jixu.common.pojo.UserRoleGroup;
import com.jixu.common.pojo.Users;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.tool.SystemInitialization;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.HttpRequestUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.Md5Util;
import com.jixu.common.util.Pages;
import com.jixu.common.util.ReturnResult;
import com.jixu.framework.msg.MsgFactory;

@Controller
public class UserController extends ExceptionControl{
	
	@Resource(name = "jixuUserService")
	private UserControlService userService;
	
	@RequestMapping(value = "/jixulogin.do", method = RequestMethod.GET)
	public String login() {
		return "jixulogin";
	}

	@Resource(name="systemInit")
	private SystemInitialization systemInit;
	
	@RequestMapping(value = "/user.do", method = RequestMethod.GET, name="用户-页面显示")
	public String client() {
		return "user";
	}

	@RequestMapping(value = "/user/authenticate.do", method = RequestMethod.POST)
	public void authenticate(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response) {
		String ip = HttpRequestUtil.getIpAddress(request);

		ResultContainer container = userService.login(username, password, ip);
		if (container.isCorrectFlag()) {
			Users user = (Users) container.getEntityObj();
			request.getSession().setAttribute(JixuUserUtil.getLoginUser(), user);
			JsonUtil.writeObjToResponse(response, true);
		} else {
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}

	@RequestMapping(value = "/user/logout.do", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(JixuUserUtil.getLoginUser());
		JsonUtil.writeObjToResponse(response, true);
	}

	@RequestMapping(value = "/user/users.do", method = RequestMethod.POST, name="用户-查询")
	public void users(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "role", required = false) int role,
			@RequestParam(value = "client", required = false) int client,
			HttpServletResponse response) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("ID", "用户名", "用户角色", "工作流角色",
				"真实姓名", "电话号码", "电子邮箱", "是否为有效", "最后修改时间");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();

		List<Users> userList=userService.loadUerList();
		for(Users user:userList){
			List<Object> item = new ArrayList<Object>();
			item.add(user.getUserid());
			item.add(user.getUsername());
			if(user.getUserRole()==null){
				item.add("-");
			}else{
				item.add(user.getUserRole().getRoleDesc());
			}
			item.add("-");
			item.add(user.getRealname());
			item.add(user.getPhonenum());
			item.add(user.getEmailAddress());
			if(user.isActive()){
				item.add(ReturnResult.getDicHM().get(ReturnResult.yes_chinese));
			}else{
				item.add(ReturnResult.getDicHM().get(ReturnResult.no_chinese));
			}
			if(user.getLastLoginTime()==null){
				item.add("-");
			}else{
				item.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(user.getLastLoginTime()));
			}
			data.add(item);
		}
		
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/userDetail.do", method = RequestMethod.POST, name="用户-详情")
	public void userDetail(
			@RequestParam(value = "id", required = true) int userID,
			HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("ID", "用户名", "用户角色","真实姓名", "电话号码", "电子邮箱", "是否为有效","工作流角色");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		Users user=userService.loadUserByID(userID);
		
		data.add(user.getUserid());
		data.add(user.getUsername());
		data.add(user.getUserpwd());
		if(user.getUserRole()==null){
			data.add(0);
		}else{
			data.add(user.getUserRole().getRoleID());
		}
		data.add(user.getRealname());
		data.add(user.getPhonenum());
		data.add(user.getEmailAddress());
		data.add(user.isActive());
		
		Set<UserOpRuleMap> useropruleSet=user.getUserOpRuleMap();
		List<Integer> wfRoles = new ArrayList<Integer>();
		if(useropruleSet.size()>0){
			Iterator<UserOpRuleMap> roleIter=useropruleSet.iterator();
			while(roleIter.hasNext()){
				wfRoles.add(roleIter.next().getOperationRole());
			}
		}
		data.add(wfRoles);
		
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/userCreate.do", method = RequestMethod.POST, name="用户-创建")
	public void userCreate(
			@RequestParam(value = "params", required = true) String params,
			HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		Users user=new Users();
		user.setUsername(param_list.get(0).toString());
		//密码加密
		String pwd=Md5Util.md5(param_list.get(1).toString());
		user.setUserpwd(pwd);
		user.setUserRole(new UserRole(Integer.valueOf(param_list.get(2).toString())));
		user.setRealname(param_list.get(3).toString());
		user.setPhonenum(param_list.get(4).toString());
		user.setEmailAddress(param_list.get(5).toString());
		user.setActive(Boolean.valueOf(param_list.get(6).toString()));
		user.setLastLoginTime(DateUtil.convertStr2Date("20000101"));
		
		List<String> roleList=(List<String>)param_list.get(7);
		userService.createUser(user);
		userService.updateUserOpRole(user, roleList);
		//用户创建时记录用户密码
		UserHisPwd uhp=new UserHisPwd();
		Date date=new Date();
		uhp.setUserid(user.getUserid());
		uhp.setHispwd(pwd);
		uhp.setPwdDate(date);
		userService.createUserHisPwd(uhp);
		
		JsonUtil.writeObjToResponse(response, user.getUserid());
	}

	@RequestMapping(value = "/user/userUpdate.do", method = RequestMethod.POST, name="用户-更新")
	public void userUpdate(
			@RequestParam(value = "id", required = true) int userID,
			@RequestParam(value = "params", required = true) String params,
			HttpServletResponse response) {
        List<Object> param_list = JsonUtil.stringToList(params);
        
        int maxRepeatnum=6;	//to-do pending 需要界面配置
        
        Pages<UserHisPwd>  pages=new Pages<UserHisPwd>(1);
		pages.setPageMaxRecords(maxRepeatnum);
		
		Users user=userService.loadUserByID(userID);
		//1判断密码有没有重复
		List<UserHisPwd> ulist=userService.getUserHisPwdList(userID, pages);
		if(!param_list.get(1).toString().equals(ulist.get(0).getHispwd().toString())){//更新时如果输入的密码与上一次密码一致则不修改密码
			for (int i = 0; i < ulist.size(); i++) {
				if(Md5Util.md5(param_list.get(1).toString()).equals(ulist.get(i).getHispwd().toString())){
					//JsonUtil.writeObjToResponse(response, "密码6次内不能重复");
					
					HashMap<String, Object> result = new HashMap<String, Object>();
					HashMap<String, Object> issue = new HashMap<String, Object>();
					issue.put("code", "1111");
					issue.put("level", "fail");
					issue.put("detail", ReturnResult.getResultHM().get(ReturnResult.user_pwd_repeat_nums)+": " + maxRepeatnum);
					result.put("issue", issue);
					JsonUtil.writeObjToResponse(response, result);
					return;
				}
			}
			String pwd=Md5Util.md5(param_list.get(1).toString());
			user.setUserpwd(pwd);
			//记录修改的密码
			UserHisPwd uhp=new UserHisPwd();
			Date date=new Date();
			uhp.setUserid(user.getUserid());
			uhp.setHispwd(pwd);
			uhp.setPwdDate(date);
			userService.createUserHisPwd(uhp);
		}
		//2 修改密码
		user.setUsername(param_list.get(0).toString());
		user.setUserRole(new UserRole(Integer.valueOf(param_list.get(2).toString())));
		user.setRealname(param_list.get(3).toString());
		user.setPhonenum(param_list.get(4).toString());
		user.setEmailAddress(param_list.get(5).toString());
		user.setActive(Boolean.valueOf(param_list.get(6).toString()));
			
		userService.updateUser(user);
		List<String> roleList=(List<String>)param_list.get(7);
		userService.updateUserOpRole(user, roleList);
		JsonUtil.writeObjToResponse(response, "");
	}

	@RequestMapping(value = "/user/roles.do", method = RequestMethod.POST, name="用户角色-查询")
	public void roles(HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();

		List<UserRole> roleList = userService.loadUserRoleList();
		for (UserRole role : roleList) {
			result.put(role.getRoleID(), role.getRoleDesc());
		}
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/wfRoles.do", method = RequestMethod.POST)
	public void wfRoles(HttpServletResponse response) {
		HashMap<Integer, String> result = WorkFlowSteps.operationRuleHM;
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/roleDetail.do", method = RequestMethod.POST, name="用户角色-详情")
	public void roleDetail(
			@RequestParam(value = "id", required = true) int roleID,
			HttpServletResponse response) {
		HashMap<Integer, String> result = userService.loadRoleGroupHM(roleID);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/roleUpdate.do", method = RequestMethod.POST, name="用户角色-更新")
	public void roleUpdate(
			@RequestParam(value = "id", required = true) int roleID,
			@RequestParam(value = "params", required = true) String params,
			HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		userService.createUserRoleGroupMap(roleID, DataConvert.convert2IntList(param_list));
		
		userService.reflushRoleMapListHM();
		JsonUtil.writeObjToResponse(response, "");
	}

	@RequestMapping(value = "/user/roleCreate.do", method = RequestMethod.POST, name="用户角色-创建")
	public void roleCreate(
			@RequestParam(value = "name", required = true) String roleName,
			HttpServletResponse response) {
		UserRole role = new UserRole();
		role.setRoleDesc(roleName);
		userService.createRole(role);
		JsonUtil.writeObjToResponse(response, role.getRoleID());
	}

	@RequestMapping(value = "/user/groups.do", method = RequestMethod.POST, name="用户分组-查询")
	public void groups(HttpServletResponse response) {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		List<UserRoleGroup> roleGroups=userService.loadUserRoleGroupList();
		for(UserRoleGroup group:roleGroups){
			result.put(group.getGroupID(), group.getGroupName());
		}
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/groupDetail.do", method = RequestMethod.POST, name="用户分组-详情")
	public void groupDetail(
			@RequestParam(value = "id", required = true) int groupID,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<Integer, String> result = userService.loadRoleTypeHM(groupID);
		
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/groupUpdate.do", method = RequestMethod.POST, name="用户分组-更新")
	public void groupUpdate(
			@RequestParam(value = "id", required = true) int groupID,
			@RequestParam(value = "params", required = true) String params,
			HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		userService.createRoleGroupTypeMap(groupID, DataConvert.convert2IntList(param_list));
		
		userService.reflushRoleMapListHM();
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/user/groupVerifyDetail.do", method = RequestMethod.POST, name="用户分组-审核-详情")
	public void verifyDetail(@RequestParam(value = "id", required = true) int groupID, 
											     HttpServletRequest request, HttpServletResponse response) {
		// 替换成该用户组下具有的审核功能详情
		HashMap<String, String> result = userService.loadVerifyConfig(groupID);
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/groupVerifyUpdate.do", method = RequestMethod.POST, name="用户分组-审核-更新")
	public void verifyUpdate(@RequestParam(value = "id", required = true) int groupID,
													@RequestParam(value = "params", required = true) String params,
													HttpServletResponse response) {
		// 替换成该用户组下具有的审核功能更新
		List<Object> param_list = JsonUtil.stringToList(params);
		userService.createVerifyConfig(groupID, param_list);
		userService.reflushRoleMapListHM();
		JsonUtil.writeObjToResponse(response, "");
	}

	@RequestMapping(value = "/user/groupCreate.do", method = RequestMethod.POST, name="用户分组-创建")
	public void groupCreate(
			@RequestParam(value = "name", required = true) String groupName,
			HttpServletResponse response) {
		UserRoleGroup group=new UserRoleGroup();
		group.setGroupName(groupName);
		userService.createUserRoleGroup(group);
		JsonUtil.writeObjToResponse(response, group.getGroupID());
	}

	@RequestMapping(value = "/user/actions.do", method = RequestMethod.POST)
	public void actions(HttpServletResponse response) {
		HashMap<Integer, String> result=userService.loadRoleTypes();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/user/verifyActions.do", method = RequestMethod.POST)
	public void verifyActions(HttpServletResponse response) {
		// 用全部审核可选动作代替
		HashMap<String, String> result=MsgFactory.getMessageNameHM();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/user/verifyURL.do", method = RequestMethod.POST)
	public void verifyURL(
			@RequestParam(value = "checkURL", required = true) String checkURL,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		boolean accessFlag=false;
		if(JixuUserUtil.jixuUserSystemFlag()){
			Users user=JixuUserUtil.getUserByRequest(request);
			//判断是否有权限访问下一个URL
			if(user!=null)
				accessFlag=userService.verifyUrlAccess(user, checkURL);
		}else{
			accessFlag=true;
		}
		
		String urlName = "";
		if(systemInit.getRoleTypeNameHM()!=null) {
			urlName = systemInit.getRoleTypeNameHM().get(checkURL);
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", accessFlag);
		result.put("urlName", urlName);
		JsonUtil.writeObjToResponse(response, result);
	}
}
