package com.jixu.mvc.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.UserTmpID;
import com.jixu.common.types.CompanyType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.service.FetchBasicInfoService;
import com.orm.obj.PortfolioClient;
import com.orm.obj.Province;

@Controller
public class ClientController extends ExceptionControl{
	
	@Resource(name="basicinfoService")
	private FetchBasicInfoService basicinfoService;
	
	@RequestMapping(value="/client.do", method = RequestMethod.GET, name="客户-页面显示")
	public String client() {
		return "client";
	}
	
	@RequestMapping(value="/client/clientList.do", method = RequestMethod.POST, name="客户-查询")
	 public void clientList(HttpServletRequest request, HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		List<PortfolioClient> clients=null;
		if(JixuUserUtil.getUserNamebyRequest(request).toLowerCase().equals(UserTmpID.superUser) || !JixuUserUtil.jixuUserSystemFlag()){
			clients=basicinfoService.getClientList();
		}else{
			clients=basicinfoService.getClientListByUserID(JixuUserUtil.getUserIDbyRequest(request));
		}
		
		for(PortfolioClient client:clients){
			result.put(client.getClientid(), client.getShortName());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/client/clientTypes.do", method = RequestMethod.POST)
	 public void clientTypes(HttpServletResponse response){
		HashMap<Integer, String> result = CompanyType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/client/clientCreate.do", method = RequestMethod.POST, name="客户-创建")
	 public void clientCreate(@RequestParam(value = "params", required = true) String params,
			 			    HttpServletResponse response){
		List<Object> param_list = JsonUtil.stringToList(params);
		
		String clientName=param_list.get(0).toString();
		String clientShort=param_list.get(1).toString();
		int clientType=Integer.valueOf(param_list.get(2).toString());
		String country=param_list.get(3).toString();
		int province=Integer.valueOf(param_list.get(4).toString());
		String address=param_list.get(5).toString();
		String phoneNum=param_list.get(6).toString();
		String postCode=param_list.get(7).toString();
		String webAddress=param_list.get(8).toString();
		String emailAddress=param_list.get(9).toString();
		Date initDate=DateUtil.convertStr2Date2(param_list.get(10).toString());
		boolean active=Boolean.valueOf(param_list.get(11).toString());
		
		PortfolioClient client=new PortfolioClient();
		client.setClientName(clientName);
		client.setShortName(clientShort);
		client.setCompanyType(clientType);
		client.setCountry(country);
		client.setProvince(new Province(province));
		client.setAddress(address);
		client.setPhoneNumber(phoneNum);
		client.setPostCode(postCode);
		client.setWebAddress(webAddress);
		client.setEmailAddress(emailAddress);
		client.setInitDate(initDate);
		client.setActive(active);
		basicinfoService.createClient(client);
		
		JsonUtil.writeObjToResponse(response, client.getClientid());
	}
	
	@RequestMapping(value="/client/clientDetail.do", method = RequestMethod.POST, name="客户-详情")
	 public void clientDetail(@RequestParam(value = "id", required = true) int clientID,
			 			     HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "客户名称", "客户简称", "客户类型", "国家", "省份", "地址", "电话", "邮编", "公司主页", "公司邮箱", "服务开始时间", "是否有效");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		PortfolioClient client=basicinfoService.loadClientDetail(clientID);
		
		
		data.add(client.getClientid());
		data.add(client.getClientName());
		data.add(client.getShortName());
		data.add(client.getCompanyType());
		data.add(client.getCountry());
		data.add(client.getProvince().getProID());
		data.add(client.getAddress());
		data.add(client.getPhoneNumber());
		data.add(client.getPostCode());
		data.add(client.getWebAddress());
		data.add(client.getEmailAddress());
		data.add(DateUtil.convertD2YYYY_MM_DD(client.getInitDate()));
		data.add(client.isActive());
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/client/clientUpdate.do", method = RequestMethod.POST, name="客户-更新")
	 public void clientUpdate(@RequestParam(value = "id", required = true) int clientID,
			 				@RequestParam(value = "params", required = true) String params,
			 			    HttpServletResponse response){
		List<Object> param_list = JsonUtil.stringToList(params);
		String clientName=param_list.get(0).toString();
		String clientShort=param_list.get(1).toString();
		int clientType=Integer.valueOf(param_list.get(2).toString());
		String country=param_list.get(3).toString();
		int province=Integer.valueOf(param_list.get(4).toString());
		String address=param_list.get(5).toString();
		String phoneNum=param_list.get(6).toString();
		String postCode=param_list.get(7).toString();
		String webAddress=param_list.get(8).toString();
		String emailAddress=param_list.get(9).toString();
		Date initDate=DateUtil.convertStr2Date2(param_list.get(10).toString());
		boolean active=Boolean.valueOf(param_list.get(11).toString());
		
		PortfolioClient client=basicinfoService.loadClientDetail(clientID);
		client.setClientName(clientName);
		client.setShortName(clientShort);
		client.setCompanyType(clientType);
		client.setCountry(country);
		client.setProvince(new Province(province));
		client.setAddress(address);
		client.setPhoneNumber(phoneNum);
		client.setPostCode(postCode);
		client.setWebAddress(webAddress);
		client.setEmailAddress(emailAddress);
		client.setInitDate(initDate);
		client.setActive(active);
		basicinfoService.updateClient(client);
		JsonUtil.writeObjToResponse(response, "");
	}
}
