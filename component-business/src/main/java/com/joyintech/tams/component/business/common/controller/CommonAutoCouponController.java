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

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.validation.ValidMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.ArrayList;
import java.util.List;

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
    public AjaxResult autoBondTradeCoupon(@RequestParam("finprodMarketId") String finprodMarketId) {
        AjaxResult result = new AjaxResult();
        FinProduct finProduct = service.autoBondTradeCoupon(finprodMarketId);
        if(StringUtil.isNotEmpty(finProduct)) {
            result.setData(finProduct);
        }else{
            result.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMessage("资产未通过中债要素校验,自动加券失败,需在对应页面新建资产信息", MessageType.错误消息);
        }
        return result;
    }
}
