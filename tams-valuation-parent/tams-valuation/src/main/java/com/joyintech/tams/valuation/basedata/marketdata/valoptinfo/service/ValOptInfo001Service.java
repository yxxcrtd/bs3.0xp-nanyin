/*
 * 文件名：ValOptInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年2月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.basedata.marketdata.valoptinfo.dto.ValOptInfo001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 场外期权标的Service
 * @author tanxuan
 * @version 1.0 2020年2月12日
 * @see ValOptInfo001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValOptInfo001Service extends CommonExportService<ValOptInfo001Dto> implements BaseProcessService<ValOptInfo001Dto>{
	
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValOptInfo001Dto> findPage(Map<String, Object> params) throws Exception {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValOptInfo001/findPage",
                params, ValOptInfo001Dto.class);
    }
    
    /**
     * 根据标的代码获取信息
     * 
     * @param params 筛选条件
     * @return 信息
     * @throws Exception 
     */
    public List<ValOptInfo001Dto> findByTargetId(Map<String, Object> params) throws Exception {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValOptInfo001/findByTargetId",
                params, ValOptInfo001Dto.class);
    }
    
    /**
     * 交易编号联想框
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> findFinprodId(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValOptInfo001/findFinprodId",
                params, SuggestDto.class);
    }
    
	@Override
	public void doComplete(ValOptInfo001Dto entity, OperationType type) {
		String action = StringUtil.EMPTY;
	   	 if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	        }
	        else if(OperationType.MODIFY.equals(type)){
	            action = "update";
	        }
	   	  restTemplate.postForObject(
	   	            ServiceApplication.VALUATION_SERVICE_NAME + "/ValOptInfo001/" + action, entity,
	   	            Integer.class);
	}
	
}
