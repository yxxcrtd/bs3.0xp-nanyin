package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.LineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.TotalLineMonitorDailyDto;

@Service
public class TotalLineMonitorDailyService extends CommonExportService<TotalLineMonitorDailyDto> {
	 /**
     * 设置日期格式样式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
    	 if(("mdate".equals(propName)) && value != null){
             cell.setCellValue(DateUtil.format((Date)value));
         }
    	 if("ratio".equals(propName) && value != null){
    		 BigDecimal ratio = ((BigDecimal) value).divide(new BigDecimal(100),2);
    		 cell.setCellValue(ratio.toString()+"%");
    	 }
    	 if("busiType".equals(propName) && value != null){
    		 cell.setCellValue(SysDataUtil.getNameFromDataDict(DictCode.资产五级分类,(String)value));
    	 }
    }
    
    
	@PostMapping("queryAbsLineMonitor")
    public List<TotalLineMonitorDailyDto> querylineMonitorDailyList(LineMonitorDailyDto queryDto){
		//查询微服务参数
	//	Date searchDate = DateUtil.parse((String) selectParam.get("searchDate"));
    	return restTemplate.postForList(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/linemonitordaily/queryTatolLineMonitor",
	            queryDto, TotalLineMonitorDailyDto.class);
    }
}
