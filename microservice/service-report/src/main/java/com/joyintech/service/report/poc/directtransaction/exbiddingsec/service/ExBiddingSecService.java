package com.joyintech.service.report.poc.directtransaction.exbiddingsec.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.report.poc.directtransaction.exbiddingbond.dto.ExbiddingbondDto;
import com.joyintech.service.report.poc.directtransaction.exbiddingsec.dao.ExBiddingSecMapper;
import com.joyintech.service.report.poc.directtransaction.exbiddingsec.dto.ExBiddingSecDto;

@Service("backExBiddingSecService")
public class ExBiddingSecService extends BaseService<ExbiddingbondDto> {

	@Autowired
	private ExBiddingSecMapper exBiddingSecMapper;
	
	
	public ExBiddingSecDto search(String entrustId){
		Map<String, String> map = new HashMap<>();
		map.put("entrustId", entrustId);
		return exBiddingSecMapper.search(map);
	}
}
