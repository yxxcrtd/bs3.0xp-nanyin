/*
 * 文件名：ValSubjectBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.dao.ValSubjectBalanceQuery001Mapper;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectbalancequery.dto.ValSubjectBalanceQuery001Dto;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.service.ValFinprodTradeQuery001Service;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
public class ValSubjectBalanceQuery001Service extends CommonExportService<ValSubjectBalanceQuery001Dto> {
	
    /**
     * ValFinprodTradeQuery001Mapper
     */
    @Autowired
    private ValSubjectBalanceQuery001Mapper mapper;
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValSubjectBalanceQuery001Dto> findPage(Map<String, Object> params) throws Exception {
        
    	 return restTemplate.postForPage(
	                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectBalanceQuery001/findPage",
	                params, ValSubjectBalanceQuery001Dto.class);
    }
    
    public List<ValSubjectBalanceQuery001Dto> getAllList(Map<String, Object> params){
    	
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectBalanceQuery001/getAllList",
                params, ValSubjectBalanceQuery001Dto.class);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param hashMap
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryValBokSubject(Map<String, Object> map) {
    	
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectBalanceQuery001/getSubjectNo",
                map, SelectOptionDto.class);
    }
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetId() {
    	return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValSubjectBalanceQuery001/getBooksetId",
            null,SelectOptionDto.class);
    	}

}
