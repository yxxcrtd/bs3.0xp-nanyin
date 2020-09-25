package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dao.ValAccountHandle001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;

@Service(value="serviceValAccountHandleService")
@Transactional(rollbackFor = Exception.class)
public class ValAccountHandleService extends CommonExportService<ValAccountHandle001Dto>{

	@Autowired
	private ValAccountHandle001Mapper valAccountHandle001Mapper;
	
	@DataSource(DataSourceType.MASERT)
	public List<ValBooksetDateSwitch001Dto> findBooksetDate(Map<String, Object> paramMap) {
		return valAccountHandle001Mapper.findBooksetDate(paramMap);
	}
	
	
}
