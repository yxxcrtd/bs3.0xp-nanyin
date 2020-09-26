/*
 * 文件名：TaxBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.taxbalancequery.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.taxbalancequery.dto.TaxBalanceQuery001Dto;

/**
 * 简易计税余额查询
 * 
 * @author leipeng
 * @version 1.0 2018年10月9日
 * @see TaxBalanceQuery001Service
 * @since 1.0
 */
@Service
public class TaxBalanceQuery001Service extends CommonExportService<TaxBalanceQuery001Dto> {

    /**
     * 根据条件分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public Page<TaxBalanceQuery001Dto> findByPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/TaxBalanceQuery001/findPage",
            params, TaxBalanceQuery001Dto.class);
    }

    /**
     * 根据条件分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public List<TaxBalanceQuery001Dto> findByList(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/TaxBalanceQuery001/findAll", params,
            TaxBalanceQuery001Dto.class);
    }

	public Page<SelectOptionDto> getAssetId(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/TaxBalanceQuery001/getAssetId",
        		pageParam, SelectOptionDto.class);
	}
    
}
