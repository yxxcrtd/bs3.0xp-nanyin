/*
 * 文件名：CePreOccupancyManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.dto.CePreOccupancyManagementDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年1月8日
 * @see CePreOccupancyManagement001Service
 * @since 1.0
 */
@Service
public class CePreOccupancyManagement001Service extends CommonExportService<CePreOccupancyManagementDto>
implements BaseProcessService<CePreOccupancyManagementDto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CePreOccupancyManagementDto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	        }
	        else {
	            action = "update";
	        }
	        restTemplate.postForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/" + action, entity,
	            Integer.class);

	}
	
	 /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SelectOptionDto> getCustomerId(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/getCustomerId",
            map, SelectOptionDto.class);
    }
    
	/**
	 * 画面查询 <功能详细描述>
	 * 
	 * @param parameters
	 *            parameters
	 * @return RskSupervisionIndicatorDto
	 * @see
	 * @since 1.0
	 */
	public Page<CePreOccupancyManagementDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/selectForPage",
				parameters, CePreOccupancyManagementDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/preoccupancymanagement/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	 /**
     * 设置预警与违规格式
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
	public Page<CePreOccupancyManagementDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<CePreOccupancyManagementDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
