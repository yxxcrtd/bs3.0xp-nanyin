package com.joyintech.service.report.poc.directtransaction.exbiddingbond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.poc.directtransaction.exbiddingbond.dto.ExbiddingbondDto;
import com.joyintech.service.report.poc.directtransaction.exbiddingbond.service.ExbiddingbondService;

@RestController("backExbiddingbondController")
@RequestMapping("/Exbiddingbond")
public class ExbiddingbondController {

	@Autowired
	private ExbiddingbondService exbiddingbondService;
	
	@GetMapping("/search")
	@ResponseBody
	public ExbiddingbondDto search(String entrustId){
		return exbiddingbondService.search(entrustId);
	}
}
