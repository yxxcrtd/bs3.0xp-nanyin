package com.joyintech.service.report.poc.directtransaction.exbiddingsec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.poc.directtransaction.exbiddingsec.dto.ExBiddingSecDto;
import com.joyintech.service.report.poc.directtransaction.exbiddingsec.service.ExBiddingSecService;

@RestController("backExBiddingSecController")
@RequestMapping("/Exbiddingsec")
public class ExBiddingSecController {

	@Autowired
	private ExBiddingSecService exBiddingSecService;
	@GetMapping("/search")
	@ResponseBody
	public ExBiddingSecDto search(String entrustId){
		return exBiddingSecService.search(entrustId);
	}
}
