package com.joyintech.service.product.rddesgin.productSimulationTrial.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductTransactionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductTransactionMapper {


	/**
	 * 新建模拟交易
	 * @param productTransactionDto
	 */
	void insertproductTransaction(ProductTransactionDto productTransactionDto);


}
