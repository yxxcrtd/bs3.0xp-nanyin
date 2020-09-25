/*
 * 文件名：BondTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.valuation.trademanage.investtradequery.dao.LendTradeQuery001Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.LendTradeDto;


/**
 *  拆借交易查询
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see LendTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceLendTradeQuery001Service")
public class LendTradeQuery001Service {

    @Autowired
    private LendTradeQuery001Mapper lendTradeQuery001Mapper;
    
    
    public LendTradeDto findByTradeId(Map<String, Object> params) {
        return lendTradeQuery001Mapper.selectByTradeId(params);
    }
    
}
