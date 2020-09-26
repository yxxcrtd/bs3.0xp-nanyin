/*
 * 文件名：FinNCProductBuild002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年2月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.finncproductbuild.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 产品信息管理Service
 * @author tanxuan
 * @version 1.0 2020年2月12日
 * @see FinNCProductBuild002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinNCProductBuild002Service extends CommonExportService<FinNCProductBuild001Dto> implements BaseProcessService<FinNCProductBuild001Dto>{

    /**
     * 新建check
     * <一句话功能简述>
     * @param finprodId
     * @param paramCode
     * @return
     */
    public List<FinNCProductBuild001Dto> deleteCheck(String finprodId) throws Exception {
  	return restTemplate.postForList(
              ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/deleteCheck",
              finprodId, FinNCProductBuild001Dto.class);
  }
	
	@Override
	public void doComplete(FinNCProductBuild001Dto entity, OperationType type) {
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
	   	            ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/" + action, entity,
	   	            Integer.class);
	}
	
    /**
     * 母下拉框分页获取产品
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getpFinprodId(Map<String,Object> map) {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/getpFinprodId",
                map, SelectOptionDto.class);
    }
    
    /**
     * 获取机构信息
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCustomerId(Map<String,Object> map) {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/getCustomerId",
                map, SelectOptionDto.class);
    }
    
    /**
     * 唯一性验证
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    public FinNCProductBuild001Dto findByFinprodId(String finprodId) {
    	return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/FinNCProductBuild002/findByFinprodId",
                finprodId, FinNCProductBuild001Dto.class);
    }
	
}
