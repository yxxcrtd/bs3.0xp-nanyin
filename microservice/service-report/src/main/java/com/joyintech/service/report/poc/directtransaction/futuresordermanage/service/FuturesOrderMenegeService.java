package com.joyintech.service.report.poc.directtransaction.futuresordermanage.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.report.poc.directtransaction.futuresordermanage.dao.FuturesOrderMenegeMapper;
import com.joyintech.service.report.poc.directtransaction.futuresordermanage.dto.FuturesOrderMenegeDto;

@Service("backFuturesOrderMenegeService")
public class FuturesOrderMenegeService extends BaseService<FuturesOrderMenegeDto> {

	@Autowired
	private FuturesOrderMenegeMapper futuresOrderMenegeMapper;
	
	public FuturesOrderMenegeDto search(String entrustId){
		Map<String, String> map = new HashMap<>();
		map.put("entrustId", entrustId);
		return futuresOrderMenegeMapper.search(map);
	}
}
