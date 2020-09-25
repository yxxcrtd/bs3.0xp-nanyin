/*
 * 文件名：CommonAutoCouponController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.dto.InfChinamutualfunddescriptionDto;
import com.joyintech.tams.component.business.common.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.tams.component.business.common.service.CommonAutoCouponService;

/**
 * 交易自动加券
 * @author yangjm
 * @version 1.0 2019年3月28日
 * @see CommonAutoCouponController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/common/CommonAutoCoupon")
public class CommonAutoCouponController extends BaseController{
    
    @Autowired
    private CommonAutoCouponService service;

    /**
     * 
     * 基金交易自动加券
     * 投资委托页面选中资产代码，自动资产信息表维护数据
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("autoFundTradeCoupon")
    @ResponseBody
    public FinProduct autoFundTradeCoupon(@RequestParam("finprodMarketId") String finprodMarketId) {
        return service.autoFundTradeCoupon(finprodMarketId);
        
    }
    /**
     * 
     * 基金交易自动加券
     * 投资委托页面选中资产代码，自动资产信息表维护数据
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("autoBondTradeCoupon")
    @ResponseBody
    public FinProduct autoBondTradeCoupon(@RequestParam("finprodMarketId") String finprodMarketId) {
        return service.autoBondTradeCoupon(finprodMarketId);
        
    }
}
