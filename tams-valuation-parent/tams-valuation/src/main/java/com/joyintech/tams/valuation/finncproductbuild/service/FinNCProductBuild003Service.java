/*
 * 文件名：FinNCProductBuild003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.finncproductbuild.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.finncproductbuild.dto.ValProductInfoFeeDto;

@Service
public class FinNCProductBuild003Service extends BaseService<ValProductInfoFeeDto>{
	
	/**
     * 费率调整
     * @param dto
     * @return
	 * @throws Exception 
     */
	public void adjustRate(ValProductInfoFeeDto dto) throws Exception {
		restTemplate.postForObject(
   	            ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild001/adjustRate", dto,
   	            Integer.class);
	}
	
}
