package com.jixu.mvc.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.mvc.service.UserControlService;
import com.jixu.common.pojo.PreVerify;
import com.jixu.common.pojo.UserRoleGroupMap;
import com.jixu.common.pojo.Users;
import com.jixu.common.pojo.VerifyConfig;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.MsgLogDao;
import com.jixu.dao.intf.UserPortfolioMapDao;
import com.jixu.framework.dao.intf.PreVerifyDao;
import com.jixu.framework.dao.intf.UserRoleGroupMapDao;
import com.jixu.framework.dao.intf.UsersDao;
import com.jixu.framework.dao.intf.VerifyConfigDao;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.UserPortfolioMap;


@Controller
public class VerifyController extends ExceptionControl{
	
	@Resource(name = "jixuUserService")
	private UserControlService userService;
	
	@Resource(name="commfuncService")
	protected CommFuncService commService;
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Autowired
	private UserPortfolioMapDao userPortfolioMapDao;
	
	@Autowired
	private PreVerifyDao preVerifyDao;
	
	@Autowired
	private MsgLogDao msgLogDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private VerifyConfigDao verifyConfigDao;
	
	@Autowired
	private UserRoleGroupMapDao userRoleGroupMapDao;
	
	
	@RequestMapping(value = "/verify.do", method = RequestMethod.GET, name="审核操作-页面显示")
	public String client() {
		return "verify";
	}
	
	@RequestMapping(value="/verify/submitVerifyUserList.do", method = RequestMethod.POST)
	public void submitVerifyUserList(HttpServletRequest request, HttpServletResponse response){
		//TODO 审核人
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		Users u=usersDao.loadUserDetail(userID);//当前登录用户
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(u.getUserid(),u.getUsername());
		
		int roleid=u.getUserRole().getRoleID();//当前登录用户角色id
		
		List<Integer> data = new ArrayList<Integer>();
		Set<Integer> ints = new HashSet<Integer>();
		List<Integer> subID = new ArrayList<Integer>();
		
		List<UserRoleGroupMap> list=userRoleGroupMapDao.loadUserRoleGroupMap(roleid);//根据用户角色id查找用户所在的组
		if(list.size()>0){
			for(UserRoleGroupMap urgm:list){
				data.add(urgm.getUserRoleGroup().getGroupID());
			}
			for (int i = 0; i < data.size(); i++) {
				List<VerifyConfig> vlist=verifyConfigDao.loadVerifyConfig(data.get(i));//根据用户所在的组统计该用户所拥有的权限
				for (VerifyConfig vc : vlist) {
					ints.add(vc.getId());
				}
			}
			for (int i : ints) {
				List<PreVerify> plist=preVerifyDao.loadPreVerifyByVConfigID(i);//根据该用户所拥有的权限到待审核表中查询 
				for (PreVerify pv : plist) {
					subID.add(pv.getSubmitterID());
				}
			}
			for (int i = 0; i < subID.size(); i++) {
				Users user=usersDao.loadUserDetail(subID.get(i));
				result.put(user.getUserid(),user.getUsername());//如果该条待审核记录在该用户权限内 就取出该条记录的提交人
			}
		}
		result.put(0, "全部");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/verify/toVerifyMsgList.do", method = RequestMethod.POST, name="审核操作-待审核消息查询")
	public void toVerifyMsgList(@RequestParam(value = "user", required = true) int user,
								  @RequestParam(value = "startDate", required = true) String startDate,
								  @RequestParam(value = "endDate", required = true) String endDate,
			 				      HttpServletRequest request,
			 				      HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","日期", "操作名称", "操作类型", "操作所属组合", "审核提交人", "当前状态");
		result.put("titles", titles);
		
		Date start=null;
		if(startDate.equals("")){
			start=DateUtil.convertStr2Date("20000101");
		}else{
			start=DateUtil.convertStr2Date2(startDate);
		}
		
		Date end=null;
		if(endDate.equals("")){
			end=DateUtil.convertStr2Date("29991231");
		}else{
			end=DateUtil.convertStr2Date2(endDate);
		}
		List<Object> data = new ArrayList<Object>();
		int userid=JixuUserUtil.getUserIDbyRequest(request);
		Users u=usersDao.loadUserDetail(userid);//当前登录用户
		
		List<UserPortfolioMap> upflist=userPortfolioMapDao.loadUserPortMapListByUserID(userid);//当前登录用户的投资组合
		String[] portfolios=new String[upflist.size()];
		for(int i=0;i<upflist.size();i++){
			portfolios[i]=upflist.get(i).getPortfolio();
		}
		
		if(user==0){//查看该用户权限下所有操作(全部)
			List<PreVerify> list=preVerifyDao.loadPreVerifyByAll(portfolios, userid, start, end);
			for (PreVerify pv : list) {
				if(pv.getStatus()==0){
					List<Object> item = new ArrayList<Object>();
					item.add(pv.getId());
					item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
					VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());
					if(vc!=null){
						item.add(vc.getName());
						item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
					}
					item.add(pv.getPortfolio());
					Users u1=usersDao.loadUserDetail(pv.getSubmitterID());
					item.add(u1.getUsername());
					item.add("待审核");
					data.add(item);
					result.put("data", data);
				}
			}
		}else{
			int roleid=u.getUserRole().getRoleID();
			List<Integer> datas = new ArrayList<Integer>();
			List<String> str = new ArrayList<String>();
			
			List<UserRoleGroupMap> ulist=userRoleGroupMapDao.loadUserRoleGroupMap(roleid);//根据用户角色id查找用户所在的组
			if(ulist.size()>0){
				for(UserRoleGroupMap urgm:ulist){
					datas.add(urgm.getUserRoleGroup().getGroupID());
				}
				for (int i = 0; i < datas.size(); i++) {//根据用户所在的组统计该用户所拥有的权限
					List<VerifyConfig> vlist=verifyConfigDao.loadVerifyConfig(datas.get(i));
					for (VerifyConfig vc : vlist) {
						str.add(vc.getName());
					}
				}
			}
			List<PreVerify> list=preVerifyDao.loadPreVerifyBySubmitterID(portfolios,user,start,end);
			if(userid==user){//查看自己做的操作
				for (PreVerify pv : list) {
					if(pv.getStatus()==0){
						List<Object> item = new ArrayList<Object>();
						item.add(pv.getId());
						item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
						VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());//通过PreVerify中的verifyconfigID到VerifyConfig表中查询具体的信息在页面展示
						if(vc!=null){
							item.add(vc.getName());
							item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
						}
						item.add(pv.getPortfolio());
						Users u1=usersDao.loadUserDetail(pv.getSubmitterID());//通过PreVerify中的submitterID到User表中查询用户具体姓名并在页面展示
						item.add(u1.getUsername());
						item.add("待审核");
						data.add(item);
						result.put("data", data);
					}
				}
			}else{
				for (PreVerify pv : list) {//查看自己权限下某个人的操作
					VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());
					if(vc!=null){
						if(str.contains(vc.getName())){//根据该用户所拥有的权限到待审核表中查询 
							if(pv.getStatus()==0){
								List<Object> item = new ArrayList<Object>();
								item.add(pv.getId());
								item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
								item.add(vc.getName());
								item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
								item.add(pv.getPortfolio());
								Users u1=usersDao.loadUserDetail(pv.getSubmitterID());
								item.add(u1.getUsername());
								item.add("待审核");
								data.add(item);
								result.put("data", data);
							}
						}
					}
				}
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/verify/verifiedMsgList.do", method = RequestMethod.POST, name="审核操作-已审核消息查询")
	public void verifiedMsgList(@RequestParam(value = "user", required = true) int user,
								  @RequestParam(value = "startDate", required = true) String startDate,
								  @RequestParam(value = "endDate", required = true) String endDate,
			 				      HttpServletRequest request,
			 				      HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","日期", "操作名称", "操作类型", "操作所属组合", "审核提交人", "当前状态");
		result.put("titles", titles);
		Date start=null;
		if(startDate.equals("")){
			start=DateUtil.convertStr2Date("20000101");
		}else{
			start=DateUtil.convertStr2Date2(startDate);
		}
		
		Date end=null;
		if(endDate.equals("")){
			end=DateUtil.convertStr2Date("29991231");
		}else{
			end=DateUtil.convertStr2Date2(endDate);
		}
		List<Object> data = new ArrayList<Object>();
		
		int userid=JixuUserUtil.getUserIDbyRequest(request);
		Users u=usersDao.loadUserDetail(userid);//当前登录用户
		
		List<UserPortfolioMap> upflist=userPortfolioMapDao.loadUserPortMapListByUserID(userid);//根据用户id查询投资组合
		String[] portfolios=new String[upflist.size()];
		for(int i=0;i<upflist.size();i++){
			portfolios[i]=upflist.get(i).getPortfolio();
		}
		if(user==0){//查看该用户权限下所有操作
			List<PreVerify> list=preVerifyDao.loadPreVerifyByAll(portfolios, userid, start, end);
			for (PreVerify pv : list) {
				if(pv.getStatus()==1&&pv.getVerifyPersonID()==userid||pv.getStatus()==1&&pv.getSubmitterID()==userid){
					List<Object> item = new ArrayList<Object>();
					item.add(pv.getId());
					item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
					VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());
					if(vc!=null){
						item.add(vc.getName());
						item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
					}
					item.add(pv.getPortfolio());
					Users u1=usersDao.loadUserDetail(pv.getSubmitterID());
					item.add(u1.getUsername());
					item.add("已审核");
					data.add(item);
					result.put("data", data);
				}
			}
		}else{
			int roleid=u.getUserRole().getRoleID();
			List<Integer> datas = new ArrayList<Integer>();
			List<String> str = new ArrayList<String>();
			
			List<UserRoleGroupMap> ulist=userRoleGroupMapDao.loadUserRoleGroupMap(roleid);//根据用户角色id查找用户所在的组
			if(ulist.size()>0){
				for(UserRoleGroupMap urgm:ulist){
					datas.add(urgm.getUserRoleGroup().getGroupID());
				}
				for (int i = 0; i < datas.size(); i++) {//根据用户所在的组统计该用户所拥有的权限
					List<VerifyConfig> vlist=verifyConfigDao.loadVerifyConfig(datas.get(i));
					for (VerifyConfig vc : vlist) {
						str.add(vc.getName());
					}
				}
			}
			List<PreVerify> list=preVerifyDao.loadPreVerifyBySubmitterID(portfolios,user,start,end);
			if(userid==user){//查看自己做的操作
				for (PreVerify pv : list) {
					if(pv.getStatus()==1){
						List<Object> item = new ArrayList<Object>();
						item.add(pv.getId());
						item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
						VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());//通过PreVerify中的verifyconfigID到VerifyConfig表中查询具体的信息在页面展示
						if(vc!=null){
							item.add(vc.getName());
							item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
						}
						item.add(pv.getPortfolio());
						Users u1=usersDao.loadUserDetail(pv.getSubmitterID());//通过PreVerify中的submitterID到User表中查询用户具体姓名并在页面展示
						item.add(u1.getUsername());
						item.add("已审核");
						data.add(item);
						result.put("data", data);
					}
				}
			}else{
				for (PreVerify pv : list) {//查看自己权限下某个人的操作
					VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());
					if(vc!=null){
						if(str.contains(vc.getName())){//根据该用户所拥有的权限到待审核表中查询 
							if(pv.getStatus()==1&&pv.getVerifyPersonID()==userid){
								List<Object> item = new ArrayList<Object>();
								item.add(pv.getId());
								item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
								item.add(vc.getName());
								item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
								item.add(pv.getPortfolio());
								Users u1=usersDao.loadUserDetail(pv.getSubmitterID());
								item.add(u1.getUsername());
								item.add("已经审核");
								data.add(item);
								result.put("data", data);
							}
						}
					}
				}
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/verify/rejectedMsgList.do", method = RequestMethod.POST, name="审核操作-已拒绝消息查询")
	public void rejectedMsgList(@RequestParam(value = "user", required = true) int user,
								  @RequestParam(value = "startDate", required = true) String startDate,
								  @RequestParam(value = "endDate", required = true) String endDate,
			 				      HttpServletRequest request,
			 				      HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","日期", "操作名称", "操作类型", "操作所属组合", "审核提交人", "当前状态");
		result.put("titles", titles);
		Date start=null;
		if(startDate.equals("")){
			start=DateUtil.convertStr2Date("20000101");
		}else{
			start=DateUtil.convertStr2Date2(startDate);
		}
		
		Date end=null;
		if(endDate.equals("")){
			end=DateUtil.convertStr2Date("29991231");
		}else{
			end=DateUtil.convertStr2Date2(endDate);
		}
		List<Object> data = new ArrayList<Object>();
		
		int userid=JixuUserUtil.getUserIDbyRequest(request);
		Users u=usersDao.loadUserDetail(userid);//当前登录用户
		
		List<UserPortfolioMap> upflist=userPortfolioMapDao.loadUserPortMapListByUserID(userid);
		String[] portfolios=new String[upflist.size()];
		for(int i=0;i<upflist.size();i++){
			portfolios[i]=upflist.get(i).getPortfolio();
		}
		
		if(user==0){//查看该用户权限下所有操作
			List<PreVerify> list=preVerifyDao.loadPreVerifyByAll(portfolios, userid, start, end);
			for (PreVerify pv : list) {
				if(pv.getStatus()==2&&pv.getVerifyPersonID()==userid||pv.getStatus()==2&&pv.getSubmitterID()==userid){
					List<Object> item = new ArrayList<Object>();
					item.add(pv.getId());
					item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
					VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());
					if(vc!=null){
						item.add(vc.getName());
						item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
					}
					item.add(pv.getPortfolio());
					Users u1=usersDao.loadUserDetail(pv.getSubmitterID());
					item.add(u1.getUsername());
					item.add("已拒绝");
					data.add(item);
					result.put("data", data);
				}
			}
		}else{
			int roleid=u.getUserRole().getRoleID();
			List<Integer> datas = new ArrayList<Integer>();
			List<String> str = new ArrayList<String>();
			
			List<UserRoleGroupMap> ulist=userRoleGroupMapDao.loadUserRoleGroupMap(roleid);//根据用户角色id查找用户所在的组
			if(ulist.size()>0){
				for(UserRoleGroupMap urgm:ulist){
					datas.add(urgm.getUserRoleGroup().getGroupID());
				}
				for (int i = 0; i < datas.size(); i++) {//根据用户所在的组统计该用户所拥有的权限
					List<VerifyConfig> vlist=verifyConfigDao.loadVerifyConfig(datas.get(i));
					for (VerifyConfig vc : vlist) {
						str.add(vc.getName());
					}
				}
			}
			List<PreVerify> list=preVerifyDao.loadPreVerifyBySubmitterID(portfolios,user,start,end);
			if(userid==user){//查看自己做的操作
				for (PreVerify pv : list) {
					if(pv.getStatus()==2){
						List<Object> item = new ArrayList<Object>();
						item.add(pv.getId());
						item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
						VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());//通过PreVerify中的verifyconfigID到VerifyConfig表中查询具体的信息在页面展示
						if(vc!=null){
							item.add(vc.getName());
							item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
						}
						item.add(pv.getPortfolio());
						Users u1=usersDao.loadUserDetail(pv.getSubmitterID());//通过PreVerify中的submitterID到User表中查询用户具体姓名并在页面展示
						item.add(u1.getUsername());
						item.add("已拒绝");
						data.add(item);
						result.put("data", data);
					}
				}
			}else{
				for (PreVerify pv : list) {//查看自己权限下某个人的操作
					VerifyConfig vc=verifyConfigDao.loadVerifyConfigDetail(pv.getVerifyconfigID());
					if(vc!=null){
						if(str.contains(vc.getName())){//根据该用户所拥有的权限到待审核表中查询 
							if(pv.getStatus()==2&&pv.getVerifyPersonID()==userid){
								List<Object> item = new ArrayList<Object>();
								item.add(pv.getId());
								item.add(DateUtil.convertD2YYYY_MM_DD(pv.getOperationDate()));
								item.add(vc.getName());
								item.add(vc.getName().substring(vc.getName().indexOf("-")+1));
								item.add(pv.getPortfolio());
								Users u1=usersDao.loadUserDetail(pv.getSubmitterID());
								item.add(u1.getUsername());
								item.add("已拒绝");
								data.add(item);
								result.put("data", data);
							}
						}
					}
				}
			}
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	@RequestMapping(value="/verify/verifyAction.do", method = RequestMethod.POST, name="审核操作-审核")
	public void verifyAction(@RequestParam(value = "id", required = true) int id, HttpServletRequest request,HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);//当前登录用户
		PreVerify pv=preVerifyDao.loadPreVerify(id);
		if(userID==pv.getSubmitterID()){//自己是否审核自己
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", "9001");
			issue.put("level", "fail");
			issue.put("detail", ReturnResult.getResultHM().get(ReturnResult.verify_yourself));
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}else{
			//TODO进行审核
			String msg=msgLogDao.loadMsg(pv.getMsglogid());//消息内容
			String str=msg.substring(1, msg.length()-1);//去掉左右大括号
			String[] strs=str.split(",");//转为字符型数组

			HashMap<String, Object> data = new HashMap<String, Object>();
			for (String st : strs) {
				String s=st.replace(" ", "");//去掉空格
				String[] ms=s.split("=");//去掉=号
				if(ms.length>1){  //totalexpense没有相对应的value
					data.put(ms[0],ms[1]);
				}
			}
			MsgContainer container=new MsgContainer();
			container.setVerifyFlag(true);
			container.setUserID(userID);
			container.setFieldsHM(data);
			//container.setObjID(pv.getMsglogid());
			container=tradeService.operateTran(container);
			
			if(container.isCorrectFlag()){
				pv.setStatus(1);
				pv.setVerifyPersonID(userID);
				userService.updatePreVerify(pv);
				JsonUtil.writeObjToResponse(response, true);
			}else{
				JsonUtil.writeObjToResponse(response, genErrorResult(container));
			}
			
		}
	}
	
	@RequestMapping(value="/verify/rejectAction.do", method = RequestMethod.POST, name="审核操作-拒绝")
	public void rejectAction(@RequestParam(value = "id", required = true) int id, HttpServletRequest request,HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);//当前登录用户
		PreVerify pv=preVerifyDao.loadPreVerify(id);
		if(userID==pv.getSubmitterID()){//自己是否拒绝 自己？
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", "9001");
			issue.put("level", "fail");
			issue.put("detail", ReturnResult.getResultHM().get(ReturnResult.verify_yourself));
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}else{
			pv.setStatus(2);
			pv.setVerifyPersonID(userID);
			userService.updatePreVerify(pv);
			JsonUtil.writeObjToResponse(response, true);
		}
	}
	@RequestMapping(value="/verify/verifyMsgDetail.do", method = RequestMethod.POST, name="审核操作-查询审核详细内容")
	public void verifyMsgDetail(@RequestParam(value = "id", required = true) int id, HttpServletRequest request, HttpServletResponse response){
		PreVerify pv=preVerifyDao.loadPreVerify(id);
		String msg=msgLogDao.loadMsg(pv.getMsglogid());//消息内容
		String str=msg.substring(1, msg.length()-1);//去掉左右大括号
		String[] strs=str.split(",");//转为字符型数组

		HashMap<String, Object> data = new HashMap<String, Object>();
		for (String st : strs) {
			String s=st.replace(" ", "");//去掉空格
			String[] ms=s.split("=");//去掉=号
			if(ms.length>1){  //totalexpense没有相对应的value
				data.put(ms[0],ms[1]);
			}
		}
		data.put("id", id);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		int trantype=Integer.valueOf(data.get(MsgFields.tradeType).toString());
		String urlform=SecurityCoreFactory.getTranTypeFormMap().get(trantype);
		
		result.put("form",urlform);
		result.put("data",data);
		JsonUtil.writeObjToResponse(response,result);
	}
	
	private HashMap<String, Object> genErrorResult(ResultContainer container){
    	HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> issue = new HashMap<String, Object>();
		issue.put("code", container.getHeader());
		issue.put("level", "fail");
		issue.put("detail", container.getErrorMsg());
		result.put("issue", issue);
		return result;
    }
}
