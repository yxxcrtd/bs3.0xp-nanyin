package com.joyintech.service.report.poc.directtransaction.exbiddingbond.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.directtransaction.exbiddingbond.dto.ExbiddingbondDto;

@Repository
public interface ExbiddingbondMapper {

	
	ExbiddingbondDto search(Map<String, String> map);
	
	
}
