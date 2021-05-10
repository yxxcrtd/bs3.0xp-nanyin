/*
 * 文件名：ValTrdAccountmfConfig001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2021年3月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valuationparametermanage.valtrdaccountmfconfig.dto.ValTrdAccountmfConfig001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 费用预提配置
 * @author tanxuan
 * @version 1.0 2021年3月18日
 * @see ValTrdAccountmfConfig001Service
 * @since 1.0
 */
@Service
public class ValTrdAccountmfConfig001Service extends CommonExportService<ValTrdAccountmfConfig001Dto> implements BaseProcessService<ValTrdAccountmfConfig001Dto> {

	/**
     * 页面查询
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Page<ValTrdAccountmfConfig001Dto> queryPageList(Map<String, Object> params) throws Exception{
    	return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValTrdAccountmfConfig001/findPage",
    			params, ValTrdAccountmfConfig001Dto.class);
    }
    
	/**
     * 根据页面条件查询所有结果
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValTrdAccountmfConfig001Dto> queryAllList(Map<String, Object> params) throws Exception{
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValTrdAccountmfConfig001/queryAllList",
                params, ValTrdAccountmfConfig001Dto.class);
    }
    
    /**
     * 检索账套代码列表（只取理财账套）
     * 
     * @param param 过滤参数
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTrdAccountmfConfig001/getBooksetId", 
        		params, SelectOptionDto.class);     
    }
    
    /**
     * 根据账套代码获取账套日期
     * 
     * @param param 过滤参数
     * @return 账套日期
     * @see
     * @since 1.0
     */
    public BokBooksetDate getBooksetDate(Map<String, Object> params) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTrdAccountmfConfig001/getBooksetDate", 
        		params, BokBooksetDate.class);     
    }
    
    /**
     * 根据账套代码获取账套结束日
     * 
     * @param param 过滤参数
     * @return 账套结束日
     * @see
     * @since 1.0
     */
    public BokBookset getBooksetEndDate(Map<String, Object> params) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTrdAccountmfConfig001/getBooksetEndDate", 
        		params, BokBookset.class);     
    }
    
    /**
     * 根据流水号查询预提费用配置信息
     * 
     * @param param 过滤参数
     * @return 预提费用配置信息
     * @see
     * @since 1.0
     */
    public ValTrdAccountmfConfig001Dto findBySeqNo(Map<String, Object> params) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTrdAccountmfConfig001/findBySeqNo", 
        		params, ValTrdAccountmfConfig001Dto.class);     
    }

    @Override
    public void doComplete(ValTrdAccountmfConfig001Dto valBondMarketInfo001Dto, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
//        else if (OperationType.NEW.equals(type)) {
//            action = "insert";
//        }
//        else {
//            action = "update";
//        }
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValTrdAccountmfConfig001/" + action, valBondMarketInfo001Dto,
                Integer.class);
    }

}
