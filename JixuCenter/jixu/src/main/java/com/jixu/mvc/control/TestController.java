package com.jixu.mvc.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jixu.common.controller.ExceptionControl;


@Controller
public class TestController extends ExceptionControl{
	
	@RequestMapping(value="/TPort.do", method = RequestMethod.GET)
	public String TPort() {
		return "TPort";
	}
}
