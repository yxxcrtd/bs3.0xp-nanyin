package com.joyintech.service.report.poc.directtransaction.exbiddingdepo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dto.ExBiddingDepoDto;
import com.joyintech.service.report.poc.directtransaction.exbiddingdepo.service.ExBiddingDepoService;

@RestController("backExBiddingDepoController")
@RequestMapping("/Exbiddingdepo")
public class ExBiddingDepoController {

	@Autowired
	private ExBiddingDepoService exBiddingDepoService;
	
	@GetMapping("/search")
	@ResponseBody
	public ExBiddingDepoDto search(String entrustId){
		return exBiddingDepoService.search(entrustId);
	}
}
