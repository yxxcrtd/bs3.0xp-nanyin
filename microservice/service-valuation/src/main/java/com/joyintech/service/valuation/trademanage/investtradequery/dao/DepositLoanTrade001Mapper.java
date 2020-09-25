/*
 * 文件名：DepositLoanTrade001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.trademanage.investtradequery.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.trademanage.investtradequery.dto.DepositLoanTradeDto;

/**
 * 债券交易查询
 * @author zhangzhao
 * @version 1.0 2018年7月27日
 * @see DepositLoanTrade001Mapper
 * @since 1.0
 */
@Repository(value="serviceDepositLoanTrade001Mapper")
public interface DepositLoanTrade001Mapper {
    
    DepositLoanTradeDto selectByTradeId(Map<String, Object> params);

}
