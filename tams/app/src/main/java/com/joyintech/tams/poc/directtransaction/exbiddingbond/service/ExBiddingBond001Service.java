package com.joyintech.tams.poc.directtransaction.exbiddingbond.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.exbiddingbond.dto.ExBiddingBond001Dto;

import org.springframework.stereotype.Service;

@Service
public class ExBiddingBond001Service extends BaseService<ExBiddingBond001Dto> {

	
	public ExBiddingBond001Dto search(String entrustId){
	  return	restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/Exbiddingbond/search?entrustId="+entrustId,
				ExBiddingBond001Dto.class);
	}
}
