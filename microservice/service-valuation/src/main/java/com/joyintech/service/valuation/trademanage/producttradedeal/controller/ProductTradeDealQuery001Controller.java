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

import com.joyintech.framework.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.service.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery001Dto;
import com.joyintech.service.valuation.trademanage.producttradedeal.service.ProductTradeDealQuery001Service;

/**
 * TA交易-汇总查询
 * 
 * @author zhangwei
 * @version 1.0 2018年8月6日
 * @see ProductTradeDealQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceProductTradeDealQuery001Controller")
@RequestMapping("/ProductTradeDealQuery001")
public class ProductTradeDealQuery001Controller extends BaseController {

    @Autowired
    private ProductTradeDealQuery001Service productTradeDealQuery001Service;

    @Autowired
    private ValAccountHandle001Service valAccountHandle001Service;

    @PostMapping("/findPage/{page}/{length}")
    public Page<ProductTradeDealQuery001Dto> findPage(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        return productTradeDealQuery001Service.findByPage(page, length, param);
    }

    @PostMapping("/findAll")
    public List<ProductTradeDealQuery001Dto> findAll(@RequestBody Map<String, Object> param)
        throws Exception {
        return productTradeDealQuery001Service.findAll(param);
    }

    /**
     * 下拉框获取：资产
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getAssets")
    public List<SelectOptionDto> getAssets(@RequestBody Map<String, Object> param)
        throws Exception {
        return productTradeDealQuery001Service.getAssets(param);
    }

    /**
     * 套账关联文本框数据选择
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/getFinprod")
    public List<SuggestDto> getFinprod(@RequestBody Map<String, Object> param) {
        return productTradeDealQuery001Service.getFinprod(param);
    }
    
    /**
     * 获取TA交易查询中 产品下拉框数据（通道和理财产品）
     * 
     * @param param 过滤参数
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    @PostMapping("/getFinprodWithTd")
    public List<SuggestDto> getFinprodWithTd(@RequestBody Map<String,Object> params) {
            return productTradeDealQuery001Service.getFinprodWithTd(params);
    }


}
