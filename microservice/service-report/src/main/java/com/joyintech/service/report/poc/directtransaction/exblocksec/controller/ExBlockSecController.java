package com.joyintech.service.report.poc.directtransaction.exblocksec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.poc.directtransaction.exblocksec.dto.ExBlockSecDto;
import com.joyintech.service.report.poc.directtransaction.exblocksec.service.ExBlockSecService;

@RestController("backExBlockSecController")
@RequestMapping("/ExBlockSec")
public class ExBlockSecController {

	@Autowired
	private ExBlockSecService exBlockSecService;
	
	@GetMapping("/search")
	@ResponseBody
	public ExBlockSecDto search(String entrustId){
		return exBlockSecService.search(entrustId);
	}
}
