package com.jixu.adapter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.adapter.service.parent.AfterCallClassesAbstract;
import com.jixu.adapter.types.CallClassParamsDto;
import com.jixu.common.util.ServiceApplication;

/**
 * 适配器调用下游
 * 
 * @author zhuruqing 2019-01-20
 *
 */

@Service
public class AfterCallClassesAutoBondService extends AfterCallClassesAbstract {

	private static final Logger logger = LoggerFactory.getLogger(AfterCallClassesAutoBondService.class);

	/**
	 * 该方法在适配器运行成功后会被调用
	 * 
	 * @param dto
	 *            适配器传到下游的参数
	 */
	@Override
	public void executeAfterProcess(CallClassParamsDto dto) {

		logger.info(dto.getAdapterID() + ":" + " 开始" + "---");
		restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/AutoBondCoupon/update", null,
				Integer.class);
		logger.info(dto.getAdapterID() + ": " + " 结束");
	}

	@Override
	public String callClassName() {
		return "调用投管自动加券功能";
	}

}
