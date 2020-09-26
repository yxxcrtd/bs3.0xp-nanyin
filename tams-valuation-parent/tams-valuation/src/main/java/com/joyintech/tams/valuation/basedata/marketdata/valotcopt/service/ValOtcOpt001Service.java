/*
 * 文件名：ValOtcOpt001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年2月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.valotcopt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.basedata.marketdata.valotcopt.dto.ValOtcOpt001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 
 * 场外期权交易Service
 * @author tanxuan
 * @version 1.0 2020年2月12日
 * @see ValOtcOpt001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValOtcOpt001Service extends CommonExportService<ValOtcOpt001Dto>{
	
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValOtcOpt001Dto> findPage(Map<String, Object> params) throws Exception {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValOtcOpt001/findPage",
                params, ValOtcOpt001Dto.class);
    }
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public List<ValOtcOpt001Dto> findAll(Map<String, Object> params) throws Exception {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValOtcOpt001/findAll",
                params, ValOtcOpt001Dto.class);
    }
    
    /**
     * 下拉框分页获取账套
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(Map<String,Object> map) {
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValOtcOpt001/getBooksetId",
                map, SelectOptionDto.class);
    }
    
    /**
     * 下拉框分页获取交易编号
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getTotleTradeId(Map<String,Object> map) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValOtcOpt001/getTotleTradeId",
                map, SuggestDto.class);
    }
    
    /**
     * 下拉框分页获取合约标的
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBenchmarkId(Map<String,Object> map) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValOtcOpt001/getBenchmarkId",
                map, SuggestDto.class);
    }

}
