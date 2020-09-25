package com.joyintech.service.report.poc.directtransaction.exblocksec.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.report.poc.directtransaction.exblocksec.dao.ExBlockSecMapper;
import com.joyintech.service.report.poc.directtransaction.exblocksec.dto.ExBlockSecDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("backExBlockSecService")
public class ExBlockSecService extends BaseService<ExBlockSecDto> {

	@Autowired
	private ExBlockSecMapper exBlockSecMapper;
	
	public ExBlockSecDto search(String entrustId){
		Map<String, String> map = new HashMap<>();
		map.put("entrustId", entrustId);
		return exBlockSecMapper.search(map);
	}
}
