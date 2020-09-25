/*
 * 文件名：CurrentDeposit001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.trademanage.investtradequery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.CurrentDepositDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.CurrentDeposit001Service;

/**
 * 资金账户绑定查看活期存款收息交易
 * @author Administrator
 * @version 1.0 2018年8月2日
 * @see CurrentDeposit002Controller
 * @since 1.0
 */
@RestController(value="serviceCurrentDeposit002Controller")
@RequestMapping("/CurrentDeposit002")
public class CurrentDeposit002Controller extends BaseController{

    @Autowired
    private CurrentDeposit001Service currentDeposit001Service;
//    /**
//     * 查看页面初始化
//     * {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        this.setSelectOptionToModel(model, DictCode.交易方向);
//        this.setSelectOptionToModel(model, DictCode.币种);
//        this.setSelectOptionToModel(model, DictCode.交易对手类型);
//        
//        return super.init(model);
//    }
    
    /**
     * 查看明细列表
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage")
    public List<CurrentDepositDto> findPage(@RequestBody Map<String, Object> param)  throws Exception {
        String tradeId= (String) param.get("tradeId");
        return currentDeposit001Service.selectByTradeId4(tradeId);
    }
    /**
     * 对应投资主体收息信息列表
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/findPage2")
    @ResponseBody
    public List<CurrentDepositDto> findPage2(@RequestBody Map<String, Object> param)  throws Exception {
        String tradeId= (String) param.get("tradeId");
        return currentDeposit001Service.selectByTradeId2(tradeId);
    }
}
