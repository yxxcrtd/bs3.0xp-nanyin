/*
 * 文件名：FundAccountQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.fundaccountquery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.trademanage.fundaccountquery.dto.FundAccountQuery001Dto;
import com.joyintech.service.valuation.trademanage.fundaccountquery.service.FundAccountQuery001Service;

/**
 * 资金账户查询-双击查看
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月12日
 * @see FundAccountQuery002Controller
 * @since 1.0
 */
@RestController(value="serviceFundAccountQuery002Controller")
@RequestMapping("/FundAccountQuery002")
public class FundAccountQuery002Controller extends BaseController {

    @Autowired
    private FundAccountQuery001Service fundAccountQuery001Service;

    /**
     * 资金账户查询-双击查看
     * 
     * @param acctNo
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("queryFundAccountInfo")
    public FundAccountQuery001Dto queryFundAccountInfo(String acctNo) {
        return fundAccountQuery001Service.queryFundAccountInfo(acctNo);
    }

}
