package com.joyintech.service.report.poc.directtransaction.exbiddingdepo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dao.ExBiddingDepoMapper;
import com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dto.ExBiddingDepoDto;

@Service("backExBiddingDepoService")
public class ExBiddingDepoService extends BaseService<ExBiddingDepoDto> {

	@Autowired
	private ExBiddingDepoMapper exBiddingDepoMapper;
	
	public ExBiddingDepoDto search(String entrustId){
		Map<String, String> map = new HashMap<>();
		map.put("entrustId", entrustId);
		return exBiddingDepoMapper.search(map);
	}
}
