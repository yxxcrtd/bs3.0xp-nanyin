package com.jixu.common.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jixu.common.type.BookNDaysType;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.WkndValuationCloseType;
import com.jixu.common.util.JsonUtil;

@Controller
public class ConfigController extends ExceptionControl {
	@RequestMapping(value = "/config/lotSelectList.do", method = RequestMethod.POST)
	public void lotSelectList(HttpServletResponse response) {
		HashMap<Integer, String> result = LotSelectionType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/weekendAccrualList.do", method = RequestMethod.POST)
	public void weekendAccrualList(HttpServletResponse response) {
		HashMap<Integer, String> result = BookNDaysType.bookNDaysHM;
		JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/config/weekendValuationList.do", method = RequestMethod.POST)
	public void weekendValuationList(HttpServletResponse response) {
		HashMap<Integer, String> result = WkndValuationCloseType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
}
