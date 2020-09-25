/*
 * 文件名：BussBenchMarketInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.component.business.prod.BussFinProductInvestPeriodService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestPeriodDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 产品开放计划实现类
 * 
 * @author 陈卓
 * @version 1.0 2018年9月3日
 * @see BussBenchMarketInfoImplService2
 * @since 1.0
 */
@Service
public class BussFinProductInvestPeriodImplService extends BaseService<BussFinProductInvestPeriodDto> implements BussFinProductInvestPeriodService {

	@Override
	public List<BussFinProductInvestPeriodDto> queryPeriodList(Map<String, Object> map) {
		return restTemplate.postForList(
	            ServiceApplication.PRODUCT_SERVICE_NAME + "/BussFinProductInvestPeriod/queryPeriodList",
	            map,BussFinProductInvestPeriodDto.class);
	}

	
}
