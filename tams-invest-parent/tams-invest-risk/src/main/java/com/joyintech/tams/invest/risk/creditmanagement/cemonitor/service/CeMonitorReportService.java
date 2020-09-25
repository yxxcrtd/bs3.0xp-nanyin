package com.joyintech.tams.invest.risk.creditmanagement.cemonitor.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.cemonitor.dto.CreditLinesDealDto;

/**
 * @description: CeMonitorReportService
 * @author: 裴家俊
 * @create: 2019-01-28 
 **/
@Service
public class CeMonitorReportService extends CommonExportService<CreditLinesDealDto> {
    /**
     * 查询界面询事件
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<CreditLinesDealDto> selectForPage(Map<String, Object> map){
    	if(("ORG").equals(map.get("sheetFlag"))){
	        Page<CreditLinesDealDto> page = restTemplate.postForPage(
	                ServiceApplication.RISK_SERVICE_NAME + "/CeMonitorReport/findOrgPage",
	                map, CreditLinesDealDto.class);
	        return page;
    	}
    	if(("ASSET").equals(map.get("sheetFlag"))){
    		 Page<CreditLinesDealDto> page = restTemplate.postForPage(
 	                ServiceApplication.RISK_SERVICE_NAME + "/CeMonitorReport/findAssetPage",
 	                map, CreditLinesDealDto.class);
    		 return page;
    	}
    	if(("ASSETGROUP").equals(map.get("sheetFlag"))){
   		 Page<CreditLinesDealDto> page = restTemplate.postForPage(
	                ServiceApplication.RISK_SERVICE_NAME + "/CeMonitorReport/findAssetGroupPage",
	                map, CreditLinesDealDto.class);
   		 return page;
   	}
        return null;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	Map<String, Object> selectParam=exportParam.getSelectParam();
    	if("ORG".equals(selectParam.get("sheetFlag").toString())){
    		 exportParam.config(
    	                "/com/joyintech/tams/invest/risk/creditmanagement/cemonitor/conf/export_conf.xml").dataSource(
    	                DataSourceType.MASERT);
    	}
    	if("ASSET".equals(selectParam.get("sheetFlag").toString())){
   		 exportParam.config(
   	                "/com/joyintech/tams/invest/risk/creditmanagement/cemonitor/conf/export_ast_conf.xml").dataSource(
   	                DataSourceType.MASERT);
    	}
    	if("ASSETGROUP".equals(selectParam.get("sheetFlag").toString())){
   		 exportParam.config(
   	                "/com/joyintech/tams/invest/risk/creditmanagement/cemonitor/conf/export_astgp_conf.xml").dataSource(
   	                DataSourceType.MASERT);
    	}
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("vdate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
        if(("mdate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CreditLinesDealDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

}
