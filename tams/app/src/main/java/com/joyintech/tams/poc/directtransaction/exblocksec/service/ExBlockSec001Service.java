package com.joyintech.tams.poc.directtransaction.exblocksec.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.directtransaction.exblocksec.dto.ExBlockSec001Dto;

import org.springframework.stereotype.Service;

@Service
public class ExBlockSec001Service extends BaseService<ExBlockSec001Dto> {

	public ExBlockSec001Dto search(String entrustId){
		  return	restTemplate.getForObject(ServiceApplication.REPORT_SERVICE_NAME + "/ExBlockSec/search?entrustId="+entrustId,
				  ExBlockSec001Dto.class);
		}
}
