package com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.service.CeOrg002Service;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.controller.LimitManage001Controller;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManage003Service
 * @since 1.0
 */
@Service
public class LimitManage003Service extends CommonExportService<CreditItemInfoDto>
		implements BaseProcessService<CreditItemInfoDto> {

   
    @Autowired(required = false)
    private CeOrg002Service ceOrg002Service;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CreditItemInfoDto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	            if(entity.getRiskRatio()==null){
	            	entity.setRiskRatio(new BigDecimal(100));        		
	        	}
	        }
	        else {
	            action = "update";
	            if(entity.getRiskRatio()==null){
	            	entity.setRiskRatio(new BigDecimal(100));       		
	        	}
	        }
	        restTemplate.postForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/LimitManage003/" + action, entity,
	            Integer.class);

	}
	
	 /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
	public Page<SelectOptionDto> getLimitLabel(Map<String, Object> map) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/CeLine001/getLimitLabel",
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
	public Page<CreditItemInfoDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/CeLine001/selectForPage",
				parameters, CreditItemInfoDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/creditlinemanage/conf/export_conf.xml")
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
	public Page<CreditItemInfoDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<CreditItemInfoDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
     * 根据授权额度编号查询额度信息
     * @param seqno 额度编号
     * @return 额度信息
     */
   public CreditItemInfoDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/LimitManage002/queryCreditItemInfoBySeqno",
            map, CreditItemInfoDto.class);
    }
	
   /**
    * 获取额度标签
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */
	public Page<SelectOptionDto> getCreditItemInfoDtoList(Map<String, Object> parameters) {
		
		Page<SelectOptionDto> tempList = restTemplate.postForPage(
               ServiceApplication.RISK_SERVICE_NAME + "/LimitManage002/getCreditItemInfoDtoList",
               parameters, SelectOptionDto.class);
       return tempList;
   }
	
   public boolean checkTag(CreditItemInfoDto dto){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagName", dto.getCreditTagName());
		map.put("tagId", dto.getCreditTagId());
		return restTemplate.postForObject(
				ServiceApplication.RISK_SERVICE_NAME + "/LimitManage003/checkTag",map,
				Boolean.class);	
	}

}
