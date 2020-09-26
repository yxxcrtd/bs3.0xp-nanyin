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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.producttradedeal.dao.ProductTradeDealQuery001Mapper;
import com.joyintech.tams.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery001Dto;

/**
 * TA交易-汇总查询
 * 
 * @author zhangwei
 * @version 1.0 2018年8月7日
 * @see ProductTradeDealQuery001Service
 * @since 1.0
 */
@Service
public class ProductTradeDealQuery001Service extends CommonExportService<ProductTradeDealQuery001Dto> {

    @Autowired
    private ProductTradeDealQuery001Mapper productTradeDealQuery001Mapper;

    /**
     * 根据条件分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public Page<ProductTradeDealQuery001Dto> findByPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ProductTradeDealQuery001/findPage", params,
            ProductTradeDealQuery001Dto.class);
    }

    /**
     * 根据条件查询所有
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public List<ProductTradeDealQuery001Dto> findAll(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ProductTradeDealQuery001/findAll",
            params, ProductTradeDealQuery001Dto.class);
    }

    /**
     * 下拉框获取：资产
     * 
     * @param params
     * @return select节点对象
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssets(Map<String, Object> params) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ProductTradeDealQuery001/getAssets", params,
            SelectOptionDto.class);
    }

    /**
     * 检索账套代码列表
     * 
     * @param param 过滤参数
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprod(Map<String, Object> params) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ProductTradeDealQuery001/getFinprod", params,
            SuggestDto.class);
    }

    /**
     * 获取TA交易查询中 产品下拉框数据（通道和理财产品）
     *
     * @param param 过滤参数
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodWithTd(Map<String,Object> params) {

        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ProductTradeDealQuery001/getFinprodWithTd", params,
                SuggestDto.class);
    }
}
