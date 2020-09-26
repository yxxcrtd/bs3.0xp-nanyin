/*
 * 文件名：BondLend001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年12月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.investtradequery.dao;

import java.util.Map;

import com.joyintech.tams.valuation.trademanage.investtradequery.dto.BondLendDto;

/**
 * 债券借贷交易查询
 * 
 * @author niuqianyu
 * @version 1.0 2018年12月10日
 * @see BondLend001Mapper
 * @since 1.0
 */
public interface BondLend001Mapper {

    /**
     * 双击查看
     * 
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    BondLendDto selectByTradeId(Map<String, Object> params);
}
