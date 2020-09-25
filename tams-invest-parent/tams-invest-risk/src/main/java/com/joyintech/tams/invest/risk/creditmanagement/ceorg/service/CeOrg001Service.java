package com.joyintech.tams.invest.risk.creditmanagement.ceorg.service;

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
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 机构授信
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeOrg001Service
 * @since 1.0
 */
@Service
public class CeOrg001Service extends CommonExportService<CreditLinesOrgDto>
		implements BaseProcessService<CreditLinesOrgDto> {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CreditLinesOrgDto entity, OperationType type) {
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
	            ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/" + action, entity,
	            Integer.class);

	}
	
	 /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
	public Page<SelectOptionDto> getCustomerId(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/CeOrg001/getCustomerId",
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
	public Page<CreditLinesOrgDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/CeOrg001/selectForPage",
				parameters, CreditLinesOrgDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/ceorg/conf/export_conf.xml")
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
	public Page<CreditLinesOrgDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<CreditLinesOrgDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
