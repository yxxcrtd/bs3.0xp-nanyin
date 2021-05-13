package com.jixu.common.mvc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jixu.common.mvc.service.UserControlService;
import com.jixu.common.pojo.PreVerify;
import com.jixu.common.pojo.RoleType;
import com.jixu.common.pojo.RoleTypeRoleGroupMap;
import com.jixu.common.pojo.UserHisPwd;
import com.jixu.common.pojo.UserLoginHis;
import com.jixu.common.pojo.UserOpRuleMap;
import com.jixu.common.pojo.UserRole;
import com.jixu.common.pojo.UserRoleGroup;
import com.jixu.common.pojo.UserRoleGroupMap;
import com.jixu.common.pojo.Users;
import com.jixu.common.pojo.VerifyConfig;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.tool.SystemInitialization;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.UserTmpID;
import com.jixu.common.util.Md5Util;
import com.jixu.common.util.Pages;
import com.jixu.common.util.ReturnResult;
import com.jixu.framework.dao.intf.PreVerifyDao;
import com.jixu.framework.dao.intf.RoleTypeDao;
import com.jixu.framework.dao.intf.RoleTypeRoleGroupMapDao;
import com.jixu.framework.dao.intf.UserHisPwdDao;
import com.jixu.framework.dao.intf.UserLoginHisDao;
import com.jixu.framework.dao.intf.UserOpRuleMapDao;
import com.jixu.framework.dao.intf.UserRoleDao;
import com.jixu.framework.dao.intf.UserRoleGroupDao;
import com.jixu.framework.dao.intf.UserRoleGroupMapDao;
import com.jixu.framework.dao.intf.UsersDao;
import com.jixu.framework.dao.intf.VerifyConfigDao;
import com.jixu.framework.msg.MsgFactory;

/** 
 * @ClassName 		UserControlImpl
 * @author  		Pan Wei 
 * @Date 			2016年11月27日 上午12:01:31 	
 * @Description		[]  
 */

@Service("jixuUserService")
public class UserControlServiceImpl implements UserControlService{
	
	@Autowired
	private List<RequestMappingHandlerMapping> handlerMap;
	
	@Resource(name="systemInit")
	private SystemInitialization systemInit;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleTypeDao roleTypeDao;
	
	@Autowired
	private UserLoginHisDao userLoginHisDao;
	
	@Autowired
	private PreVerifyDao preVerifyDao;
	
	@Autowired
	private UserRoleGroupDao userRoleGroupDao;
	
	@Autowired
	private RoleTypeRoleGroupMapDao roleTypeRoleGroupMapDao;
	
	@Autowired
	private UserRoleGroupMapDao userRoleGroupMapDao;
	
	@Autowired
	private UserOpRuleMapDao userOpRuleMapDao;
	
	@Autowired
	private UserHisPwdDao userHisPwdDao;
	
	@Autowired
	private VerifyConfigDao verifyConfigDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer login(String useracct, String pwd, String ip) {
		Users user=usersDao.getUserAccount(useracct);
		ResultContainer container=new ResultContainer();
		pwd=Md5Util.md5(pwd);
		if(user==null){
			container.setCorrectFlag(false);
			container.setErrorMsg(ReturnResult.getDicHM().get(ReturnResult.user_notexist_chinese));
		}else if(!user.getUserpwd().equals(pwd)){
			container.setCorrectFlag(false);
			container.setErrorMsg(ReturnResult.getDicHM().get(ReturnResult.user_incorrect_pwd_chinese));
		}else{
	        user.setLastLoginIP(ip);
	        user.setLastLoginTime(new Date());
			usersDao.updateEntityBean(user);
			
			UserLoginHis loginHis=new UserLoginHis();
			loginHis.setUsers(user);
			loginHis.setLoginIP(ip);
			loginHis.setLoginTime(new Date());
			userLoginHisDao.saveEntityBean(loginHis);
			
			container.setEntityObj(user);
			container.setObjID(user.getUserid());
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createRole(UserRole role) {
		userRoleDao.saveEntity(role);
	}
	
	@Override
	public List<UserRole> loadUserRoleList() {
		return userRoleDao.loadUserRoleList();
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createUser(Users user) {
		usersDao.saveEntity(user);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public HashMap<Integer, String> loadRoleTypes() {
		if(systemInit.getRoleTypeHM()==null || systemInit.getRoleTypeHM().size()==0){
			HashMap<Integer, String> roleTypeHM = new HashMap<Integer, String>();
			List<RoleType> roleList=roleTypeDao.loadRoleType();
			
			RequestMappingHandlerMapping handleMap=null;
			for(RequestMappingHandlerMapping tmphand:handlerMap) {
				if(tmphand.getClass().getName().equals(RequestMappingHandlerMapping.class.getName())) {
					handleMap=tmphand;
					break;
				}
			}
			if (handleMap != null){
				Iterator<Entry<RequestMappingInfo, HandlerMethod>> handleIter=handleMap.getHandlerMethods().entrySet().iterator();
				boolean roleExist=false;

				List<String> roleURLList=new ArrayList<String>();
				String roleURL="";
				while(handleIter.hasNext()){
					Entry<RequestMappingInfo, HandlerMethod> entry=handleIter.next();
					RequestMappingInfo key=entry.getKey();
					if(key.getName()!=null){
						roleExist=false;
						roleURL=key.getPatternsCondition().toString().replace("[", "");
						roleURL=roleURL.replace("]", "");
						//查找是否存在该权限
						for(RoleType role:roleList){
							if(roleURL.equals(role.getRoleURLPath())){
								roleExist=true;
								if(!key.getName().equals(role.getRoleName())){
									role.setRoleName(key.getName());
									userRoleDao.updateEntityBean(role);
								}
								roleTypeHM.put(role.getRoleID(), role.getRoleName());
							}
						}

						if(!roleExist){
							//该权限在数据库不存在
							RoleType role=new RoleType();
							role.setRoleName(key.getName());
							role.setRoleURLPath(roleURL);
							roleTypeDao.saveEntityBean(role);
							roleTypeHM.put(role.getRoleID(), role.getRoleName());
						}


						roleURLList.add(roleURL);
					}
				}
				//删除数据库中存在，但文件中不存在的权限
				boolean urlExist=false;
				for(RoleType role:roleList){
					urlExist=false;
					for(String fileURL:roleURLList){
						if(fileURL.equals(role.getRoleURLPath())){
							urlExist=true;
							break;
						}
					}
					if(!urlExist){
						roleTypeDao.deleteEntity(role);
					}
				}
			}
			return roleTypeHM;
		}else{
			return systemInit.getRoleTypeHM();
		}
	}
	
	@Override
	public List<Users> loadUerList() {
		return usersDao.loadEntityAll(Users.class);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateUser(Users user) {
		usersDao.updateEntity(user);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	@CacheEvict(value="portDataCache", key="'UserID'+#user.getUserid()")
	public void updateUserOpRole(Users user, List<String> operRoleList) {
		userOpRuleMapDao.deleteUserOpRole(user.getUserid());
		if(operRoleList.size()>0){
			for(String roleid:operRoleList){
				UserOpRuleMap useroprole=new UserOpRuleMap();
				useroprole.setUsers(user);
				useroprole.setOperationRole(Integer.valueOf(roleid));
				userOpRuleMapDao.saveEntity(useroprole);
			}
		}
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createUserRoleGroup(UserRoleGroup group) {
		userOpRuleMapDao.saveEntity(group);
	}
	
	@Override
	public List<UserRoleGroup> loadUserRoleGroupList() {
		return userRoleGroupDao.loadEntityAll(UserRoleGroup.class);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createRoleGroupTypeMap(int groupID, List<Integer> actionList) {
		roleTypeRoleGroupMapDao.deleteRoleGroupRoleTypeMap(groupID);
		for(int actionID:actionList){
			RoleTypeRoleGroupMap map=new RoleTypeRoleGroupMap();
			map.setRoleGroup(new UserRoleGroup(groupID));
			map.setRoleType(new RoleType(actionID));
			roleTypeRoleGroupMapDao.saveEntity(map);
		}
	}
	
	@Override
	public HashMap<Integer, String> loadRoleTypeHM(int groupID) {
		HashMap<Integer, String> result=new HashMap<Integer, String>();
		UserRoleGroup roleGroup=(UserRoleGroup)userRoleGroupDao.loadEntity(UserRoleGroup.class, groupID);
		Set<RoleTypeRoleGroupMap> mapSet=roleGroup.getRoleTypeRoleGroupMap();
		if(mapSet!=null){
			Iterator<RoleTypeRoleGroupMap> mapIter=mapSet.iterator();
			while(mapIter.hasNext()){
				RoleType roleType=mapIter.next().getRoleType();
				result.put(roleType.getRoleID(), roleType.getRoleName());
			}
		}
		
		return result;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createUserRoleGroupMap(int roleID, List<Integer> groupIDList) {
		userRoleGroupMapDao.deleteUserRoleGroupMap(roleID);
		for(int groupID:groupIDList){
			UserRoleGroupMap map=new UserRoleGroupMap();
			map.setUserRole(new UserRole(roleID));
			map.setUserRoleGroup(new UserRoleGroup(groupID));
			userRoleGroupMapDao.saveEntity(map);
		}
	}
	
	@Override
	public HashMap<Integer, String> loadRoleGroupHM(int roleID) {
		HashMap<Integer, String> result=new HashMap<Integer, String>();
		UserRole userRole=(UserRole)userRoleDao.loadEntity(UserRole.class, roleID);
		Set<UserRoleGroupMap> mapSet=userRole.getUserRoleGroupMap();
		if(mapSet!=null){
			Iterator<UserRoleGroupMap> mapIter=mapSet.iterator();
			while(mapIter.hasNext()){
				UserRoleGroup group=mapIter.next().getUserRoleGroup();
				result.put(group.getGroupID(), group.getGroupName());
			}
		}
		return result;
	}
	
	@Override
	public void reflushRoleMapListHM() {
		List<UserRoleGroup> groupList=userRoleGroupDao.loadEntityAll(UserRoleGroup.class);
		HashMap<Integer, HashMap<String, String>> roleMapHM=new HashMap<Integer, HashMap<String,String>>();
		
		for(UserRoleGroup group:groupList){
			//获取该权限组的所有权限
			List<RoleType> roleList=roleTypeDao.loadRoleList(group.getGroupID());
			HashMap<String, String> roleHM=new HashMap<String, String>();
			for(RoleType roleType:roleList){
				roleHM.put(roleType.getRoleURLPath(), roleType.getRoleName());
			}
			
			roleMapHM.put(group.getGroupID(), roleHM);
		}
		
		systemInit.setUserRoleListHM(roleMapHM);
	}
	
	//将历史密码导入到新建的表中
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createUserHisPwd(UserHisPwd uhp) {
		// TODO Auto-generated method stub
		userHisPwdDao.saveEntity(uhp);
	}
	
	//查询记录的所有历史密码
	@Override
	public List<UserHisPwd> getUserHisPwdList(int userid, Pages<UserHisPwd> pages) {
//		List<UserHisPwd> pwdList=new ArrayList<UserHisPwd>();
//		//pageHelper分页
//		if(frameWorkFlag==1){
//			Page<UserHisPwd> page=userHisPwdDao.getPageHelperUpdatePwdList(PageHelper.startPage(pages.getCurrentPage(), pages.getPageMaxRecords()),userid);
//			pwdList=page.getResult();
//		}else{
//			pages=userHisPwdDao.getUpdatePwdList(pages, userid);
//			pwdList=pages.getResultList();
//		}
//		return pwdList;
		pages=userHisPwdDao.getUpdatePwdList(pages, userid);
		return pages.getResultList();
	}
	
	//更新需要进行审核的操作对象
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createVerifyConfig(int groupID, List<Object> actionList) {
		verifyConfigDao.deleteVerifyConfig(groupID);
		for(Object ob:actionList){
			VerifyConfig vc=new VerifyConfig();
			vc.setRoleGroup(new UserRoleGroup(groupID));
			vc.setMsgHeader(ob.toString());
			vc.setName(MsgFactory.getMessageNameHM().get(ob.toString()));
			verifyConfigDao.saveEntity(vc);
		}
	}
	
	//查询需要进行审核的操作对象
	@Override
	public HashMap<String, String> loadVerifyConfig(int groupID) {
		HashMap<String, String> result=new HashMap<String, String>();
		UserRoleGroup roleGroup=(UserRoleGroup)userRoleGroupDao.loadEntity(UserRoleGroup.class, groupID);
		Set<VerifyConfig> mapSet=roleGroup.getVerifyConfig();
		if(mapSet!=null){
			Iterator<VerifyConfig> mapIter=mapSet.iterator();
			while(mapIter.hasNext()){
				VerifyConfig vc=mapIter.next();
				String megHeader=vc.getMsgHeader();
				String name=vc.getName();
				result.put(megHeader, name);
			}
		}
		return result;
	}
	//更新待审核消息状态
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updatePreVerify(PreVerify pv) {
		preVerifyDao.updateEntityBean(pv);
	}
	
	@Override
	public Boolean verifyUrlAccess(Users user,String checkURL) {
		boolean accessFlag=false;
		
		if(user.getUsername().equals(UserTmpID.superUser) || !JixuUserUtil.jixuUserSystemFlag())
			accessFlag=true;
		else if(systemInit.getIgnoreRolesHM().containsKey(checkURL)){
			//如果忽略的url里面包括请求url,返回true
			accessFlag=true;
		}else{
			HashMap<String, String> actionHM=systemInit.getUserRoleListHM().get(user.getUserRole().getRoleID());
			if(actionHM!=null && actionHM.containsKey(checkURL)){
				accessFlag=true;
			}
		}
		return accessFlag;
	}
	
	@Override
	public Users loadUserByID(int userid) {
		return (Users)usersDao.loadEntity(Users.class, userid);
	}
}
