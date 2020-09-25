package com.joyintech.service.report.poc.directtransaction.exbiddingbond.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.report.poc.directtransaction.exbiddingbond.dao.ExbiddingbondMapper;
import com.joyintech.service.report.poc.directtransaction.exbiddingbond.dto.ExbiddingbondDto;

@Service("backExbiddingbondService")
public class ExbiddingbondService extends BaseService<ExbiddingbondDto> {

	@Autowired
	private ExbiddingbondMapper exbiddingbondMapper;
	
	
	public ExbiddingbondDto search(String entrustId){
		Map<String, String> map = new HashMap<>();
		map.put("entrustId", entrustId);
		return exbiddingbondMapper.search(map);
	}
}
