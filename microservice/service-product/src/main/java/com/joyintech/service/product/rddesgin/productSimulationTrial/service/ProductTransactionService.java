package com.joyintech.service.product.rddesgin.productSimulationTrial.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dao.ProductTransactionMapper;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductTransactionDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


/**
 * 模拟交易service
 * 
 * @author jiaobo
 * @version 1.0 2019年11月6日
 * @see ProductTransactionDto
 * @since 1.0
 */
@Service
public class ProductTransactionService extends BaseService<ProductTransactionDto> {

	@Autowired
	private ProductTransactionMapper productTransactionMapper;


	/**
	 * 模拟交易新建插入
	 * @param productTransactionDto
	 * @throws Exception
	 */
	public void insertproductTransaction(ProductTransactionDto productTransactionDto) {
		productTransactionMapper.insertproductTransaction(productTransactionDto);
		return;
	}



}
