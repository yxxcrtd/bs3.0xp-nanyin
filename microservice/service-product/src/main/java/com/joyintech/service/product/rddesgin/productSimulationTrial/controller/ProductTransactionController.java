package com.joyintech.service.product.rddesgin.productSimulationTrial.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductHistoricalDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductTransactionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.service.ProductTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


/**
 * 模拟交易controller
 * 
 * @author jiaobo
 * @version 1.0 2019年11月6日
 * @see ProductHistoricalDto
 * @since 1.0
 */
@RestController("productTransactionController_dtx")
@PageId("ProductSiulationTrial004")
@RequestMapping("/ProductTransaction")
public class ProductTransactionController {

	@Autowired
	private ProductTransactionService productTransactionService;

	@PostMapping("/insertproductTransaction")
	public void insertproductTransaction(@RequestBody ProductTransactionDto productTransactionDto) throws Exception{
		productTransactionService.insertproductTransaction(productTransactionDto);
		return;
	}



}
