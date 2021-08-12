package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.AbsLineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.LineMonitorDailyDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 额度监测日报
 * @author gejiaao
 * @version 1.0 2020年03月09日
 * @see AbsLineMonitorDailyService
 * @since 1.0
 */
@Service
public class AbsLineMonitorDailyService extends CommonExportService<AbsLineMonitorDailyDto>{

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
    }
	
	
	@PostMapping("queryAbsLineMonitor")
    public List<AbsLineMonitorDailyDto> querylineMonitorDailyList(LineMonitorDailyDto queryDto){
		//查询微服务参数
	//	Date searchDate = DateUtil.parse((String) selectParam.get("searchDate"));
    	return restTemplate.postForList(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/linemonitordaily/queryAbsLineMonitor",
	            queryDto, AbsLineMonitorDailyDto.class);
    }
	
	 public List<AbsLineMonitorDailyDto> querylineMonitorDaily(LineMonitorDailyDto queryDto){
			//查询微服务参数
		//	Date searchDate = DateUtil.parse((String) selectParam.get("searchDate"));
	    	Map<String,Object> map = restTemplate.postForObject(
		            ServiceApplication.RISK_SERVICE_NAME +
		            "/linemonitordaily/queryAbsLineMonitorTest",
		            queryDto, Map.class);
	    	
	    	return null;
	    }
	
	
	/**
	 * 导出管理，由于监测日报模板根据日报类型不一样可能出现多种样式，故重写导出方法
	 */
/*    @Override
    public ResponseEntity<byte[]> export(String pageId, Map<String, Object> selectParam){
		//判断导出日报类型
		String dailyType = (String)selectParam.get("dailyType");
		Date searchDate = DateUtil.parse((String) selectParam.get("searchDate"));
		//文件名称
		Map<String,String> dictMap = SysDatadictUtil.getSysDictDataMap(DictCode.额度监测日报类型);
		String fileName = dictMap.get(dailyType)+selectParam.get("searchDate")+".xls";
		//查询微服务参数
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("cdate", searchDate);
		File depositfile = null;
		String exceltemplate = null;
		if("05".equals(dailyType)){
			exceltemplate = "AbsLineMonitorDailyReport";
			//同业ABS限额监测日报
			@SuppressWarnings("unchecked")
			Map<String,Object> resultMap = restTemplate.postForObject(
		            ServiceApplication.RISK_SERVICE_NAME +
		            "/linemonitordaily/",
		            param, Map.class);
			depositfile =  RiskExcelUtil.createFile(resultMap, exceltemplate, fileName);
		}else if("06".equals(dailyType)){
			
		}else if("07".equals(dailyType)){
			
		}
		//最终修改为 
        return ExportUtil.export(fileName, depositfile, true);
    
    }*/

}
