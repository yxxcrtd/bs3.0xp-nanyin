package com.jixu.common.tool;
/** 
 * @ClassName 		JixuInterceptor
 * @author  		Pan Wei 
 * @Date 			2016年11月27日 下午11:09:31 	
 * @Description		[]  
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class JixuInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(JixuInterceptor.class);
	
	@Resource(name="systemInit")
	private SystemInitialization systemInit;
	
	/*@Autowired
	private UserPortfolioMapDao userPortfolioMapDao;*/
	
	private String loginFailedURL="jixulogin.do";
	
	public String getLoginFailedURL() {
		return loginFailedURL;
	}

	public void setLoginFailedURL(String loginFailedURL) {
		this.loginFailedURL = loginFailedURL;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag = JixuUserUtil.checkLogin(request);
		if(!flag){
			response.sendRedirect(getLoginFailedURL());
		}
		logger.debug("访问地址: " + request.getServletPath());
		
		/*if (flag && user != null){
			if(!user..toLowerCase().equals(UserTmpID.superUser)){
				if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
					//不去验证没有name的.do的权限
					if(!systemInit.getIgnoreRolesHM().containsKey(request.getServletPath())){
						HashMap<String, String> actionHM=systemInit.getUserRoleListHM().get(user.getUserRole().getRoleID());
						if(actionHM==null || !actionHM.containsKey(request.getServletPath())){
							flag = false;
							HashMap<String, Object> result = new HashMap<String, Object>();
							HashMap<String, Object> issue = new HashMap<String, Object>();
							issue.put("code", "123");
							issue.put("level", "fail");
							issue.put("detail", "没有权限调用 " + systemInit.getRoleTypeNameHM().get(request.getServletPath()));
							result.put("issue", issue);
							JsonUtil.writeObjToResponse(response, result);
						}
					}
				}
				
				//暂时注释投资组合的判断，之后通过其它方法去拦截。当前登陆功能会用在不同的场景
				//获取请求里面带有portfolio,ports,portnum字段的投资组合   然后查询该用户有没有权限调用该投资组合
				Object portObj=request.getParameter("portfolio");
				if(portObj==null){
					portObj=request.getParameter("ports");
				}
				if(portObj==null){
					portObj=request.getParameter("portnum");
				}
				if(portObj!=null){
					List<String> port_list=new ArrayList<String>();
					
					List<String> list=new ArrayList<String>();
					List<UserPortfolioMap> upflist=userPortfolioMapDao.loadUserPortMapListByUserID(user.getUserid());
					for(UserPortfolioMap upm:upflist){
						list.add(upm.getPortfolio());
					}
					
					if(!portObj.toString().contains(",")&&!portObj.toString().contains("[")){
						port_list.add(portObj.toString());
					}else{
						port_list = JSONObject.parseArray(portObj.toString(), String.class);
					}
					for (int i = 0; i <port_list.size(); i++) {
						if(!list.contains(port_list.get(i))){
							flag=false;
							break;
						}
					}
					if(!flag){
						HashMap<String, Object> result = new HashMap<String, Object>();
						HashMap<String, Object> issue = new HashMap<String, Object>();
						issue.put("code", "111");
						issue.put("level", "fail");
						issue.put("detail", "没有权限调用该投资组合 ");
						result.put("issue", issue);
						JsonUtil.writeObjToResponse(response, result);
					}
				}
			}
		}*/
		return flag;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
