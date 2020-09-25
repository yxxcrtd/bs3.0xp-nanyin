package com.joyintech.tams.component.business.prod.impl;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.component.business.prod.BussFinProductInvestService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;


/**
 * 产品估值信息组件接口
 * @author yanya
 * @version 1.0 2019年8月22日
 * @see BussFinProductInvestImlService
 * @since 1.0
 */
@Service
public class BussFinProductInvestImlService  extends BaseService<BussFinProductInvestDto> implements BussFinProductInvestService {

	@Override
	public BussFinProductInvestDto getByProductId(String finprodId) {
		return restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/BussFinProductInvest/select?finprodId=" + finprodId, BussFinProductInvestDto.class);
	}

	@Override
	public void delete(String finprodId) {
		restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/BussFinProductInvest/delete?finprodId=" + finprodId, Integer.class);
	}

	@Override
	public void insert(BussFinProductInvestDto investInfo) {
		restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/BussFinProductInvest/insert", investInfo, Integer.class);

	}

	@Override
	public void update(BussFinProductInvestDto investInfo) {
		restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/BussFinProductInvest/update", investInfo, Integer.class);

	}

}
