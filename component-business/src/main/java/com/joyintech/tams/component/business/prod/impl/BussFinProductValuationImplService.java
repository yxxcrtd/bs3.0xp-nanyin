/*
 * 文件名：BussFinProductValuationImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.impl;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PrdValParam;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.prod.BussFinProductValuationService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductValuationDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * @author 何阳阳
 * @version 1.0 2018年9月17日
 * @see BussFinProductValuationImplService
 * @since 1.0
 */
@Service
public class BussFinProductValuationImplService extends BaseService<PrdValParam>
		implements BussFinProductValuationService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrdValParam getByProductId(String prudoctId) {
		return restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductValuation/select?finprodId=" + prudoctId,
				PrdValParam.class);
	}

	@Override
	public void delete(String prudoctId) {
		restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductValuation/delete?finprodId=" + prudoctId,
				Integer.class);
	}

	@Override
	public void insert(BussFinProductValuationDto valuationInfo) {
		restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductValuation/insert",
				valuationInfo, Integer.class);
	}

	@Override
	public void update(BussFinProductValuationDto valuationInfo) {
		restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductValuation/update",
				valuationInfo, Integer.class);
	}

}
