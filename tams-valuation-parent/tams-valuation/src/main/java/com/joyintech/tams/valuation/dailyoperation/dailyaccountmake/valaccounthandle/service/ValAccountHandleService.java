package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValAccountHandleService extends CommonExportService<ValAccountHandle001Dto>{

	@DataSource(DataSourceType.MASERT)
	public List<ValBooksetDateSwitch001Dto> findBooksetDate(Map<String, Object> paramMap) {
//		return valAccountHandle001Mapper.findBooksetDate(paramMap);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValTransactionDataImport001/findBooksetDate",
                paramMap, ValBooksetDateSwitch001Dto.class);
	}
	
	
}
