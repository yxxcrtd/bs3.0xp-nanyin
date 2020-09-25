package com.jixu.mvc.control;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.mvc.service.UserControlService;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.service.UserMapControlService;
import com.orm.obj.PortfolioClient;
import com.orm.obj.PortfolioHdr;

@Controller
public class UserMapController extends ExceptionControl{
	
	@Resource(name = "jixuUserMapService")
	private UserMapControlService jixuUserMapService;
	
	@Resource(name = "jixuUserService")
	private UserControlService userService;
	
	@RequestMapping(value = "/user/clientDetail.do", method = RequestMethod.POST, name="用户-客户配置详情")
	public void clientDetail(
			@RequestParam(value = "id", required = true) int userID,
			HttpServletResponse response) {
		//已选择的客户
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		List<PortfolioClient> clientList=jixuUserMapService.loadClientListByUserID(userID);
		for(PortfolioClient client:clientList){
			result.put(client.getClientid(), client.getClientName());
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/clientUpdate.do", method = RequestMethod.POST, name="用户-客户配置更新")
	public void clientUpdate(
			@RequestParam(value = "id", required = true) int userID,
			@RequestParam(value = "params", required = true) String params,
			HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		jixuUserMapService.createUserClientMap(userID, DataConvert.convert2IntList(param_list));
		JsonUtil.writeObjToResponse(response, "");
	}

	@RequestMapping(value = "/user/portDetail.do", method = RequestMethod.POST, name="用户-组合配置详情")
	public void portDetail(
			@RequestParam(value = "id", required = true) int userID,
			HttpServletResponse response) {
		HashMap<String, String> result = new HashMap<String, String>();
		List<PortfolioHdr> portList=jixuUserMapService.loadPortfoliosByUserID(userID);
		
		for(PortfolioHdr port:portList){
			result.put(port.getId(), port.getShortName());
		}
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/user/portUpdate.do", method = RequestMethod.POST, name="用户-组合配置更新")
	public void portUpdate(
			@RequestParam(value = "id", required = true) int userID,
			@RequestParam(value = "params", required = true) String params,
			HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		jixuUserMapService.createUserPortMap(userID, DataConvert.convert2StringList(param_list));
		
		userService.reflushRoleMapListHM();
		JsonUtil.writeObjToResponse(response, "");
	}

}
