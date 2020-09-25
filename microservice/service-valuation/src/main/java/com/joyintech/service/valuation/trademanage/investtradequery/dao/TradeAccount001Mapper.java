/*
 * 文件名：TradeAccount001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.trademanage.investtradequery.dto.TradeAccountDto;

/**
 * 交易账户收划款双击查看
 * 
 * @author Administrator
 * @version 1.0 2018年8月13日
 * @see TradeAccount001Mapper
 * @since 1.0
 */
@Repository(value="serviceTradeAccount001Mapper")
public interface TradeAccount001Mapper {

    /**
     * 交易账户收划款双击查看
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    TradeAccountDto queryTradeAccount(Map<String, Object> map);

}
