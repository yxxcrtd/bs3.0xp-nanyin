package com.jixu.common.tool;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jixu.common.pojo.Users;
import com.jixu.common.type.UserTmpID;
import com.joyintech.system.user.dto.User;

@Component
public final class JixuUserUtil {
	
	private static int jixuUserType;
	
	private static boolean joyinTablesFlag;
	
	private static String joyinBatchUser="system";

	//为static的变量赋值，需要用get set
	@Value("${jixu.user.type}")
	public void setJixuUserType(int jixuUserType) {
		JixuUserUtil.jixuUserType = jixuUserType;
	}

	public static String getLoginUser(){
		String loginUser="";
		if(jixuUserType==UserTmpID.userJoyinType){
			//joyin的用户系统
			loginUser=UserTmpID.loginJoyinUser;
		}else{
			//默认jixu用户系统
			loginUser=UserTmpID.loginJixuUser;
		}
		
		return loginUser;
	}
	
	/**
	 * 获取登陆用户实例
	 * @param request httprequest
	 * @return
	 */
	public static Users getUserByRequest(HttpServletRequest request){
		return (Users)request.getSession().getAttribute(getLoginUser());
	}
	
	/**
	 * 检查用户是否登陆
	 * @param request
	 * @return
	 */
	public static boolean checkLogin(HttpServletRequest request){
		Object obj=request.getSession().getAttribute(getLoginUser());
		if(obj!=null){
			return true;
		}else
			return false;
	}
	
	/**
	 * 获取登陆用户系统内ID
	 * @param request
	 * @return 登陆用户ID
	 */
	public static int getUserIDbyRequest(HttpServletRequest request){
		int userid=0;
		
		if(request!=null){
			if(jixuUserType==UserTmpID.userJoyinType){
				//joyin的用户系统
				userid=0;
			}else if(jixuUserType==UserTmpID.userJoyinBatchType){
				userid=0;
			}else{
				//默认jixu用户系统
				Users user=getUserByRequest(request);
				userid=user.getUserid();
			}
		}
		
		return userid;
	}
	
	/**
	 * 获取登陆用户系统内ID
	 * @return 登陆用户ID
	 */
	public static int getUserID(){
		int userid=0;
		
		if(jixuUserType==UserTmpID.userJixuType){
			HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			Users user= (Users)request.getSession().getAttribute(getLoginUser());
			if(user!=null) {
				//如果是跑批，则无法获取登陆用户，可能是自动运行
				userid=user.getUserid();
			}
		}
		return userid;
	}
	
	/**
	 * 获取登陆用户名
	 * @param request
	 * @return
	 */
	public static String getUserNamebyRequest(HttpServletRequest request){
		if(request!=null){
			String username="";
			if(jixuUserType==UserTmpID.userJoyinType){
				//joyin的用户系统
				Object obj=request.getSession().getAttribute(getLoginUser());
				if(obj instanceof User) {
					User user=(User)obj;
					username=user.getUserName();
				}else {
					username=joyinBatchUser;
				}
				
				
			}else if(jixuUserType==UserTmpID.userJoyinBatchType){
				username=joyinBatchUser;
			}else{
				//默认jixu用户系统
				Users user=getUserByRequest(request);
				if(user==null)
					username=joyinBatchUser;
				else
					username=user.getUsername();
			}
			return username;
		}else{
			return "";
		}
	}
	
	/**
	 * 获取当前登陆用户
	 * @return
	 */
	public static String getLoginUserName(){
		String username="";
		
		if(jixuUserType==UserTmpID.userJoyinType){
			ServletRequestAttributes srt=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes());
			if(srt==null){
				username=joyinBatchUser;
			}else{
				HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				Object obj=request.getSession().getAttribute(getLoginUser());
				if(obj instanceof User) {
					User user=(User)obj;
					username=user.getUserName();
				}else {
					username=joyinBatchUser;
				}
			}
		}else if(jixuUserType==UserTmpID.userJoyinBatchType){
			username=joyinBatchUser;
		}else{
			ServletRequestAttributes srt=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes());
			if(srt==null){
				username=joyinBatchUser;
			}else{
				HttpServletRequest request=srt.getRequest();
				Users user= (Users)request.getSession().getAttribute(getLoginUser());
				if(user==null)
					username=joyinBatchUser;
				else
					username=user.getUsername();
			}
		}
		return username;
	}
	
	/**
	 * 是否位启用几许用户权限判定系统
	 * @return
	 */
	public static boolean jixuUserSystemFlag(){
		if(jixuUserType==UserTmpID.userJixuType)
			return true;
		else
			return false;
	}
	
	public static boolean joyinUserSystemFlag(){
		if(jixuUserType==UserTmpID.userJoyinType)
			return true;
		else
			return false;
	}

	public static boolean isJoyinTablesFlag() {
		return joyinTablesFlag;
	}

	@Value("${jixu.joyintables.flag:true}")
	public void setJoyinTablesFlag(boolean joyinTablesFlag) {
		JixuUserUtil.joyinTablesFlag = joyinTablesFlag;
	}
}
