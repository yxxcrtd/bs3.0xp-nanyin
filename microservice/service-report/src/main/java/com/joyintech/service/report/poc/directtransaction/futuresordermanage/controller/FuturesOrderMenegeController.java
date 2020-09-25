package com.joyintech.service.report.poc.directtransaction.futuresordermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.poc.directtransaction.futuresordermanage.dto.FuturesOrderMenegeDto;
import com.joyintech.service.report.poc.directtransaction.futuresordermanage.service.FuturesOrderMenegeService;

@RestController("backFuturesOrderMenegeController")
@RequestMapping("/FuturesOrderMenege")
public class FuturesOrderMenegeController {

	@Autowired
	private FuturesOrderMenegeService futuresOrderMenegeService;
	
	@GetMapping("/search")
	@ResponseBody
	public FuturesOrderMenegeDto search(String entrustId){
		return futuresOrderMenegeService.search(entrustId);
	}
}
