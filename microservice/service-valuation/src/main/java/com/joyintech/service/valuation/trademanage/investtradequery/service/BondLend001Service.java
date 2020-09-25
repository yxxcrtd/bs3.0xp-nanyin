/*
 * 文件名：BondLend001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.valuation.trademanage.investtradequery.dao.BondLend001Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.BondLendDto;

/**
 * 债券借贷交易
 * 
 * @author niuqianyu
 * @version 1.0 2018年12月11日
 * @see BondLend001Service
 * @since 1.0
 */
@Service(value="serviceBondLend001Service")
public class BondLend001Service {

    @Autowired
    private BondLend001Mapper bondLend001Mapper;

    public BondLendDto findByTradeId(Map<String, Object> params) {
        return bondLend001Mapper.selectByTradeId(params);
    }

}
