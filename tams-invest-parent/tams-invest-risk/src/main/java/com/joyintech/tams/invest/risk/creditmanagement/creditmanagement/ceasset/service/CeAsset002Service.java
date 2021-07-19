package com.joyintech.tams.invest.risk.creditmanagement.ceasset.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeAsset002Service
 * @since 1.0
 */
@Service
public class CeAsset002Service extends CommonExportService<CreditLinesAstDto>
		implements BaseProcessService<CreditLinesAstDto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CreditLinesAstDto entity, OperationType type) {
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
	            ServiceApplication.RISK_SERVICE_NAME + "/CeAsset002/" + action, entity,
	            Integer.class);

	}
	
	 /**
     * 根据授权额度编号查询额度信息
     * @param seqno 额度编号
     * @return 额度信息
     */
    public CreditLinesAstDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeAsset002/queryCeAssetBySeqno",
            map, CreditLinesAstDto.class);
    }
	
    /**
     * 查询资产在资产系列中是否已存在
     * @param name
     */
    public List<CreditLinesAstDto> searchSameAssetGroup(String customerId){
    	 return restTemplate.getForList(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CeAsset002/searchSameAssetGroup?customerId="+customerId,
    	            CreditLinesAstDto.class);
    }
    /**
     * 新建场合时查询监督指标名称是否重复
     * @param name
     */
    public int searchRepeatByIndName(String indId,String indName){
    	 return restTemplate.getForObject(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CeAsset002/searchRepeatByIndName?indId="+indId+"&indName="+indName,
    	             Integer.class);
    }
    
}
