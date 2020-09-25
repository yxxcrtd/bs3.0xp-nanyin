/*
 * 文件名：SaleToAdapterController.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：GQQ
 * 修改时间：2019/7/25 
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.sale.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.interf.bankname.sale.dto.CallClassParamsDto;
import com.joyintech.interf.bankname.sale.constant.SaleCodeList;
import com.joyintech.interf.bankname.sale.dao.SaleInterfMapper;
import com.joyintech.interf.bankname.sale.service.SaleSystemInterfService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;


/**
 * @author GQQ
 * @version 1.0 2019年07月25日
 * @see
 * @since 1.0
 */
@RestController
@RequestMapping("/interf/SaleToAdapter")
public class SaleToAdapterController {

	@Autowired
	private SaleInterfMapper saleSystemInterfMapper;
	
	@Autowired
	private SaleSystemInterfService saleSystemInterfService;
	
	// 平台日期
	@Autowired(required = false)
	private IPlatformDate iPlatformDate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("executeAfterProcess")
    public void executeAfterProcess(@RequestBody CallClassParamsDto dto) {
		//查询接口分类确定调用指定适配器
		String interfClass = saleSystemInterfMapper.queryAdapterInfo(dto.getAdapterID());
		try {
			if(SaleCodeList.销售接口_认购申购接口.equals(interfClass)){
				saleSystemInterfService.receiveAlloFromSale(iPlatformDate.getSysDateStr());
			}else if(SaleCodeList.销售接口_到期赎回接口.equals(interfClass)){
				saleSystemInterfService.receiveRedeemFromSale(iPlatformDate.getSysDateStr());
			}else if(SaleCodeList.销售接口_份额增减接口.equals(interfClass)){
				saleSystemInterfService.receiveAddReduceSale(iPlatformDate.getSysDateStr());
			}else if(SaleCodeList.销售接口_分红汇总接口.equals(interfClass)){
				saleSystemInterfService.receiveBonusCollectSale(iPlatformDate.getSysDateStr());
			}else if(SaleCodeList.销售接口_日提利息接口.equals(interfClass)){
				saleSystemInterfService.receiveProdAccrueSale(iPlatformDate.getSysDateStr());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
	}
    
}
