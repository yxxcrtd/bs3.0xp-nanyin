package com.joyintech.tams.poc.directtransaction.exbiddingsec.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.exbiddingsec.dto.ExBiddingSec001Dto;

import org.springframework.stereotype.Service;

@Service
public class ExBiddingSec001Service extends BaseService<ExBiddingSec001Dto> {

	public ExBiddingSec001Dto search(String entrustId){
		  return	restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/Exbiddingsec/search?entrustId="+entrustId,
				  ExBiddingSec001Dto.class);
		}
}
