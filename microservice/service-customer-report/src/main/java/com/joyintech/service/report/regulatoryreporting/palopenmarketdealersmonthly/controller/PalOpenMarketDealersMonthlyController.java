/**  
 * @Project     service-customer-report  
 * @File        PalOpenMarketDealersMonthlyController.java  
 * @Package     com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.controller  
 * @Version     V1.0  
 * @Date        2019年8月3日 下午3:15:36  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.dto.ChbBankMonthReportModelDto;
import com.joyintech.service.report.regulatoryreporting.palopenmarketdealersmonthly.service.PalOpenMarketDealersMonthlyService;

/**  
 * @Description 一级交易商月报表微服务控制层  
 * @ClassName   PalOpenMarketDealersMonthlyController  
 * @Date        2019年8月3日 下午3:15:36  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@RestController
@RequestMapping("/PalOpenMarketDealersMonthly")
public class PalOpenMarketDealersMonthlyController {

	@Autowired
	private PalOpenMarketDealersMonthlyService palOpenMarketDealersMonthlyService;
	
	/**  
	 * @Description 查询一级交易商月报表数据  
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:21:34  
	 * @param @param pageParam
	 * @param @return
	 * @param @throws Exception 参数  
	 * @return ChbBankMonthReportModelDto 返回类型   
	 * @throws  
	 */  
	@PostMapping("queryOpenMarketDealersMonthlyData")
	public ChbBankMonthReportModelDto queryOpenMarketDealersMonthlyData(@RequestBody Map<String, Object> pageParam) throws Exception{
		return palOpenMarketDealersMonthlyService.queryOpenMarketDealersMonthlyData(pageParam);
	}
}
