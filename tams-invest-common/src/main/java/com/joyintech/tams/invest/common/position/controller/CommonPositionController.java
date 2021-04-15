package com.joyintech.tams.invest.common.position.controller;

import com.joyintech.tams.invest.common.position.dto.PostionParameter;
import com.joyintech.tams.invest.common.position.service.CommonPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/commonPosition")
public class CommonPositionController {
	
	@Autowired
	private CommonPositionService commonPositionService;

	@RequestMapping("/init")
	@ResponseBody
	public Map<String,Object> initMap(){
		return commonPositionService.init();
	}
	
	@RequestMapping("/process")
	@ResponseBody
	public Map<String,Object> process(@RequestBody PostionParameter parameter){
		commonPositionService.process(parameter);
		return new HashMap<String,Object>();
	}
}
