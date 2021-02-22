/**  
 * @Project     service-customer-report  
 * @File        InvestmentsInfoController.java  
 * @Package     com.joyintech.service.report.regulatoryreporting.investmentsinfo.controller  
 * @Version     V1.0  
 * @Date        2019年8月7日 下午1:54:30  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.investmentsinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto.InvestmentBusinessCounterDetailsDto;
import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto.InvestmentBusinessDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.regulatoryreporting.investmentsinfo.service.InvestmentsInfoService;

/**  
 * @Description TODO  
 * @ClassName   InvestmentsInfoController  
 * @Date        2019年8月7日 下午1:54:30  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@RestController
@RequestMapping("/InvestmentsInfo")
public class InvestmentsInfoController {
	@Autowired
	private InvestmentsInfoService investmentsInfoService;
	
	
	@PostMapping("createData")
	public Map<String, Object> createData(@RequestBody Map<String, Object> pageParam) {
		return investmentsInfoService.createData(pageParam);
	}
	@PostMapping("createTable3AddData")
	public List<InvestmentBusinessDetailsDto> createTable3AddData(@RequestBody Map<String, Object> pageParam) {
		return investmentsInfoService.createTable3AddData(pageParam);
	}
	@PostMapping("createTable7OtherInfo")
	public List<InvestmentBusinessCounterDetailsDto> createTable7OtherInfo(@RequestBody Map<String, Object> pageParam) {
		return investmentsInfoService.createTable7OtherInfo(pageParam);
	}
	
}
