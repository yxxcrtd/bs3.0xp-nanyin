/*
 * 文件名：InvestTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.InvestTradeDto;

/**
 * 投资交易查询-service
 * 
 * @author yangjian
 * @version 1.0 2018年7月25日
 * @see InvestTradeQuery001Service
 * @since 1.0
 */
@Service
public class InvestTradeQuery001Service extends CommonExportService<InvestTradeDto> {

    /**
     * 投资交易分页查询
     * 
     * @param params 参数
     * @return 投资交易分页数据
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<InvestTradeDto> findPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/InvestTradeQuery001/findPage",
            params, InvestTradeDto.class);
    }

    /**
     * 投资交易分页查询
     * 
     * @param params 参数
     * @return 投资交易分页数据
     * @throws Exception
     * @see
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    public List<InvestTradeDto> findList(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/InvestTradeQuery001/findList",
            params, InvestTradeDto.class);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public InvestTradeDto queryFinProductDealBytradeId(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/InvestTradeQuery001/queryFinProductDealBytradeId", map,
            InvestTradeDto.class);
    }
}
