package com.jixu.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jixu.common.util.JsonUtil;

@Controller
public class VersionController extends ExceptionControl{
	
	
	
	@RequestMapping(value="/version.do", method = RequestMethod.GET)
	public void version(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/version/getVersion.do", method = RequestMethod.POST)
	 public void clientList(HttpServletRequest request, HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, "STABLE:5.4.21");
	}
}
