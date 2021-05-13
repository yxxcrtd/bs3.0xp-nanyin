package com.jixu.common.tool;

/** 
 * @ClassName 		SystemInitialization
 * @author  		Pan Wei 
 * @Date 			2016年12月7日 下午10:35:02 	
 * @Description		[]  
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.jixu.common.pojo.RoleType;
import com.jixu.common.pojo.RoleTypeRoleGroupMap;
import com.jixu.common.pojo.UserRole;
import com.jixu.common.pojo.UserRoleGroup;
import com.jixu.common.pojo.UserRoleGroupMap;
import com.jixu.common.pojo.Users;
import com.jixu.framework.dao.intf.RoleTypeDao;
import com.jixu.framework.dao.intf.RoleTypeRoleGroupMapDao;
import com.jixu.framework.dao.intf.UsersDao;


@Component("systemInit")
@Order(value=1)
public class SystemInitialization implements CommandLineRunner{ 

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RoleTypeDao roleTypeDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private RoleTypeRoleGroupMapDao roleTypeRoleGroupMapDao;
	
	//@Resource(name="sessionFactory")
	//private SessionFactory sessionFactory;
	
	@Autowired
	private List<RequestMappingHandlerMapping> handlerMap;

	//roleTypeHM: controller配置的url:名称作为权限控制，这里放置roleid:权限名称。roleid为roletype表的自增长主键ID
	private HashMap<Integer, String> roleTypeHM;
	private HashMap<Integer, HashMap<String, String>> userRoleListHM;
	/**		所有的controller层配置的@RequestMapping
	 *		样例:@RequestMapping(value="/client.do", method = RequestMethod.GET, name="客户-页面显示")
	 *		"/client.do":"客户-页面显示"
	 */
	private HashMap<String, String> roleTypeNameHM;
	/**
	 * 如果在@RequestMapping中没有配置name,则该页面的访问则没有权限控制
	 */
	private HashMap<String, String> ignoreRolesHM;

	@Override
	
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		logger.info("开始初始化系统参数...");
		//session=sessionFactory.openSession();
		//Transaction tx=session.beginTransaction();
		//initDAO.setSession(session);
		
		// 初始化证券ID
		roleTypeHM = new HashMap<Integer, String>();
		
		initRoleType();	//把所有的controller层的@RequestMapping配置放入roleTypeNameHM/ignoreRolesHM和数据库中
		
		if(JixuUserUtil.jixuUserSystemFlag()) {
			//jixu用户系统，才需要用户是否有登陆某个页面的信息
			setUserAction();
		}
		//tx.commit();
		//session.flush();
		//session.clear();
		//session.close();
		logger.info("系统参数初始化结束");
	}
	
	public HashMap<Integer, String> getRoleTypeHM() {
		return roleTypeHM;
	}
	
	/*public void initUserRules(){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		Map<RequestMappingInfo, HandlerMethod> handleMap=handlerMap.getHandlerMethods();
		Iterator<Entry<RequestMappingInfo, HandlerMethod>> handleIter=handleMap.entrySet().iterator();
		int item=0;
		while(handleIter.hasNext()){
			item++;
			Entry<RequestMappingInfo, HandlerMethod> entry=handleIter.next();
			RequestMappingInfo key=entry.getKey();
			if(key.getName()!=null){
				result.put(item, key.getName());
			}else{
				result.put(item, key.getPatternsCondition().toString());
			}
		}
	}*/
	
	/**
	 * 初始化系统各项数据，主要以用户操作权限为主
	 */
	public void initRoleType(){
		roleTypeHM = new HashMap<Integer, String>();
		ignoreRolesHM=new HashMap<String, String>();
		roleTypeNameHM=new HashMap<String, String>();
		
		//获取controller层所有的@RequestMapping配置
		//样例:@RequestMapping(value="/client.do", method = RequestMethod.GET, name="客户-页面显示")
		RequestMappingHandlerMapping handleMap=null;
		for(RequestMappingHandlerMapping tmphand:handlerMap) {
			if(tmphand.getClass().getName().equals(RequestMappingHandlerMapping.class.getName())) {
				handleMap=tmphand;
				break;
			}
		}
		if (handleMap != null){
			Iterator<Entry<RequestMappingInfo, HandlerMethod>> handleIter=handleMap.getHandlerMethods().entrySet().iterator();
			String roleURL="";

			if(!JixuUserUtil.jixuUserSystemFlag()) {
				//如果非几许用户登陆，放开页面的所有访问权限
				while(handleIter.hasNext()){
					Entry<RequestMappingInfo, HandlerMethod> entry=handleIter.next();
					RequestMappingInfo key=entry.getKey();
					roleURL=key.getPatternsCondition().toString().replace("[", "");
					roleURL=roleURL.replace("]", "");
					if(key.getName()!=null){
						roleTypeNameHM.put(roleURL, key.getName());
					}
				}
			}else {
				List<RoleType> roleList=roleTypeDao.loadRoleType();
				boolean roleExist=false;

				List<String> roleURLList=new ArrayList<String>();
				while(handleIter.hasNext()){
					Entry<RequestMappingInfo, HandlerMethod> entry=handleIter.next();
					RequestMappingInfo key=entry.getKey();
					roleURL=key.getPatternsCondition().toString().replace("[", "");
					roleURL=roleURL.replace("]", "");
					if(key.getName()!=null){
						roleExist=false;
						//查找是否存在该权限
						for(RoleType role:roleList){
							if(roleURL.equals(role.getRoleURLPath())){
								//如果配置在数据库中存在，则查看是否需要更新数据  样例:{xxx.do:显示页面}
								roleExist=true;
								if(!key.getName().equals(role.getRoleName())){
									role.setRoleName(key.getName());
									roleTypeDao.updateEntityBean(role);
								}
								roleTypeHM.put(role.getRoleID(), role.getRoleName());
								break;
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
						roleTypeNameHM.put(roleURL, key.getName());
					}else{
						ignoreRolesHM.put(roleURL, roleURL);
					}
				}

				//某条权限配置在数据库中存在，但controller中不存在的权限,则删除数据库中该权限
				boolean urlExist=false;
				for(RoleType role:roleList){
					urlExist=false;
					if(roleURLList.contains(role.getRoleURLPath())){
						urlExist=true;
						break;
					}
					if(!urlExist){
						roleTypeRoleGroupMapDao.deleteRoleGroupMap(role.getRoleID());
						roleTypeDao.deleteEntity(role);
					}
				}
			}
		}

	}
	
	
	private void setUserAction(){
		if(userRoleListHM!=null){
			userRoleListHM.clear();
		}else{
			userRoleListHM=new HashMap<Integer, HashMap<String,String>>();
		}
		synchronized(userRoleListHM){
			List<Users> usersList=usersDao.loadAllUser();
			for(Users user:usersList){
				setUserRoleSet(user);
			}
		}
	}
	
	/**
	 * 用户权限和.do相匹配
	 * @param user
	 */
	private void setUserRoleSet(Users user){
		if(userRoleListHM==null){
			userRoleListHM=new HashMap<Integer, HashMap<String,String>>();
		}
		HashMap<String, String> userActionHM=new HashMap<String, String>();
		UserRole userRole=user.getUserRole();
		if(userRole!=null){
			Set<UserRoleGroupMap> groupMap=userRole.getUserRoleGroupMap();
			if(groupMap!=null && groupMap.size()>0){
				Iterator<UserRoleGroupMap> groupMapIter=groupMap.iterator();
				while(groupMapIter.hasNext()){
					UserRoleGroupMap roleGroupMap=groupMapIter.next();
					UserRoleGroup roleGroup=roleGroupMap.getUserRoleGroup();
					Set<RoleTypeRoleGroupMap> typeGroupMap=roleGroup.getRoleTypeRoleGroupMap();
					if(typeGroupMap!=null && typeGroupMap.size()>0){
						Iterator<RoleTypeRoleGroupMap> typeGroupMapIter=typeGroupMap.iterator();
						while(typeGroupMapIter.hasNext()){
							RoleTypeRoleGroupMap typegroupMap=typeGroupMapIter.next();
							RoleType roleType=typegroupMap.getRoleType();
							userActionHM.put(roleType.getRoleURLPath(), roleType.getRoleName());
						}
					}
				}
			}
		}
		if(userActionHM.size()>0){
			userRoleListHM.put(user.getUserRole().getRoleID(), userActionHM);
		}
	}

	/**
	 * 获取用户的操作权限map
	 * @return 获取用户的操作权限map
	 */
	public HashMap<Integer, HashMap<String, String>> getUserRoleListHM() {
		return userRoleListHM;
	}
	
	/**
	 * 在controller里没有设值name的.do操作，可以给任何用户开放
	 * @return	xxx.do的访问地址map
	 */
	public HashMap<String, String> getIgnoreRolesHM() {
		return ignoreRolesHM;
	}
	
	/**
	 * 通过访问地址找到该操作的名称,即.do后面的name
	 * @return	xxx.do和name组成的map
	 */
	public HashMap<String, String> getRoleTypeNameHM() {
		return roleTypeNameHM;
	}

	/**
	 * 设置 userRoleListHM，用于当用户在系统中更改权限，这里负责刷新权限列表
	 */
	public void setUserRoleListHM(
			HashMap<Integer, HashMap<String, String>> userRoleListHM) {
		this.userRoleListHM = userRoleListHM;
	}
}
