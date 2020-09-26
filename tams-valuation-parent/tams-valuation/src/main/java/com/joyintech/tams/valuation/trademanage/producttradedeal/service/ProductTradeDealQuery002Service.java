/*
 * 文件名：ProductTradeDealQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.producttradedeal.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery002Dto;

/**
 * TA交易-明细查询
 * 
 * @author zhangwei
 * @version 1.0 2018年8月7日
 * @see ProductTradeDealQuery002Service
 * @since 1.0
 */
@Service
public class ProductTradeDealQuery002Service extends CommonExportService<ProductTradeDealQuery002Dto> {

    /**
     * 根据条件查询： TA交易-明细查询 分页
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public Page<ProductTradeDealQuery002Dto> findPageByCondition(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ProductTradeDealQuery002/findPage", params,
            ProductTradeDealQuery002Dto.class);
    }

    /**
     * 根据条件查询所有： TA交易-明细查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public List<ProductTradeDealQuery002Dto> findAllByCondition(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ProductTradeDealQuery002/findAllByCondition", params,
            ProductTradeDealQuery002Dto.class);
    }

}
