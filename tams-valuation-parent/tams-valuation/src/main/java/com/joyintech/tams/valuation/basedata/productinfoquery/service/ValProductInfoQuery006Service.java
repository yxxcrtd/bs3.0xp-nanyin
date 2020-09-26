/*
 * 文件名：ValProductInfoQuery005Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.productinfoquery.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoAcctDto;
import com.joyintech.tams.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
public class ValProductInfoQuery006Service extends BaseService<ValProductInfoFeeDto>{
	
	/**
     * 查看费用调节 - 分页
     * @param pageParam
     * @return
	 * @throws Exception 
     */
	public Page<ValProductInfoAcctDto> findBankdesc(Map<String, Object> pageParam) throws Exception {
		return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValProductInfoQuery006/findBankdesc",
                pageParam, ValProductInfoAcctDto.class);
	}
	
}
