/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 利率互换估值信息
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo001Service
 * @since 1.0
 */
@Service
public class ValRateSwapValuationInfo001Service extends CommonExportService<ValRateSwapValuationInfo001Dto> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<ValRateSwapValuationInfo001Dto> queryPageList(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ValRateSwapValuationInfo001/findPage",
    			params, ValRateSwapValuationInfo001Dto.class);
    }

    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<ValRateSwapValuationInfo001Dto> queryAllList(Map<String, Object> params){
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValRateSwapValuationInfo001/queryAllList",
                params, ValRateSwapValuationInfo001Dto.class);
    }

	/**
     * 交易编号获取
     *
     * @return 返回的数据
     */
	public List<SelectOptionDto> getFinprodId2(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ValRateSwapValuationInfo001/getFinprodId2",
                params, SelectOptionDto.class);
	}


}
