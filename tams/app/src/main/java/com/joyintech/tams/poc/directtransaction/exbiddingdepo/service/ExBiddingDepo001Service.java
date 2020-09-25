package com.joyintech.tams.poc.directtransaction.exbiddingdepo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.exbiddingdepo.dto.ExBiddingDepo001Dto;

import org.springframework.stereotype.Service;

@Service
public class ExBiddingDepo001Service extends BaseService<ExBiddingDepo001Dto> {

	public ExBiddingDepo001Dto search(String entrustId){
		  return	restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/Exbiddingdepo/search?entrustId="+entrustId,
				  ExBiddingDepo001Dto.class);
		}
}
