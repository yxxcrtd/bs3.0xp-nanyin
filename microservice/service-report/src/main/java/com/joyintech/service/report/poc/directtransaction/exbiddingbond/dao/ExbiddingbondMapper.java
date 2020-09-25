package com.joyintech.service.report.poc.directtransaction.exbiddingbond.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.joyintech.service.report.poc.directtransaction.exbiddingbond.dto.ExbiddingbondDto;

@Component
public interface ExbiddingbondMapper {

	
	ExbiddingbondDto search(Map<String, String> map);
	
	
}
