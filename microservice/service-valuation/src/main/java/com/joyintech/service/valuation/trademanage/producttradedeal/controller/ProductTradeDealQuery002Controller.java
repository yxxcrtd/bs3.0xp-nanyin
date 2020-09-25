/*
 * 文件名：ProductTradeDealQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.producttradedeal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery002Dto;
import com.joyintech.service.valuation.trademanage.producttradedeal.service.ProductTradeDealQuery002Service;

/**
 * TA交易-明细查询
 * 
 * @author zhangwei
 * @version 1.0 2018年8月6日
 * @see ProductTradeDealQuery002Controller
 * @since 1.0
 */
@RestController(value="serviceProductTradeDealQuery002Controller")
@RequestMapping("/ProductTradeDealQuery002")
public class ProductTradeDealQuery002Controller extends BaseController {

    @Autowired
    private ProductTradeDealQuery002Service productTradeDealQuery002Service;

    /**
     * 根据条件分页查询
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ProductTradeDealQuery002Dto> findPage(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        return productTradeDealQuery002Service.findPageByCondition(page, length, param);
    }

    /**
     * 根据条件分页查询
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findAllByCondition")
    public List<ProductTradeDealQuery002Dto> findAllByCondition(@RequestBody Map<String, Object> param)
        throws Exception {
        return productTradeDealQuery002Service.findAllByCondition(param);
    }

}
