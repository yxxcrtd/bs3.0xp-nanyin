/*
 * 文件名：FinProductLedgerController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.ledger.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.service.product.productelement.ledger.service.FinProductLedgerService;

/**
 * 理财产品信息管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinProductLedgerController
 * @since 1.0
 */
@RestController("FinProductLedger_dtx")
@RequestMapping("/FinProductLedger")
public class FinProductLedgerController {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinProductLedgerService finProductLedgerService;
	
	@PostMapping("selectForLedger/{page}/{length}")
	public Page<FinProductBuild001Dto> selectForLedger(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return finProductLedgerService.selectForLedger(page, length, param);
	}

}
