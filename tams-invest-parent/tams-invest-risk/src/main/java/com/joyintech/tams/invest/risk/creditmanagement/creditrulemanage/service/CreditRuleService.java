package com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.dto.CreditRuleDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 额度校验规则管理
 * @author gejiaao
 * @version 1.0 2020年02月15日
 * @see CreditRuleService
 * @since 1.0
 */
@Service
public class CreditRuleService extends CommonExportService<CreditRuleDto>
		implements BaseProcessService<CreditRuleDto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CreditRuleDto entity, OperationType type) {
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
	            ServiceApplication.RISK_SERVICE_NAME + "/CreditRule/" + action, entity,
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
	public Page<CreditRuleDto> selectForPage(Map<String, Object> parameters) {
		return restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/CreditRule/selectForPage",
				parameters, CreditRuleDto.class);
	}

	/**
	 * 导出
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/creditmanagement/creditrulemanage/conf/export_conf.xml")
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
	public Page<CreditRuleDto> selectPageData(Map<String, Object> selectParam) {
		try {
			Page<CreditRuleDto> pageList=selectForPage(selectParam);
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 /**
     * 根据编号查询额度规则信息
     * @param seqno 额度编号
     * @return 额度信息
     */
   public CreditRuleDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CreditRule/queryCreditRuleBySeqno",
            map, CreditRuleDto.class);
    }

   /**
    * 获取额度标签
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */
	public Page<SelectOptionDto> getCreditTagInfoList(Map<String, Object> parameters) {
		
		Page<SelectOptionDto> tempList = restTemplate.postForPage(
               ServiceApplication.RISK_SERVICE_NAME + "/CeLine001/getCreditTagInfoList",
               parameters, SelectOptionDto.class);
       return tempList;
   }

   public List<CreditRuleDto> checkRule(CreditRuleDto dto){
	   return restTemplate.postForList(
			   ServiceApplication.RISK_SERVICE_NAME + "/CreditRule/checkRule",
			   dto, CreditRuleDto.class);
   }
}
