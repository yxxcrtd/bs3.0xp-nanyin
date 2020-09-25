package com.joyintech.tams.poc.directtransaction.futuresordermanage.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.futuresordermanage.dto.FuturesOrderMenegeDto;

import org.springframework.stereotype.Service;

@Service
public class FuturesOrderMenegeService extends BaseService<FuturesOrderMenegeDto> {

	public FuturesOrderMenegeDto search(String entrustId){
		  return	restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/FuturesOrderMenege/search?entrustId="+entrustId,
				  FuturesOrderMenegeDto.class);
		}
}
