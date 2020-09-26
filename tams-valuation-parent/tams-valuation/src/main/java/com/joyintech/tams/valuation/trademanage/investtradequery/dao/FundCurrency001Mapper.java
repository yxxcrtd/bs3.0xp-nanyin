/*
 * 文件名：FundCurrency001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.trademanage.investtradequery.dto.FundCurrencyDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年7月30日
 * @see FundCurrency001Mapper
 * @since 1.0
 */
@Repository
public interface FundCurrency001Mapper {
    
    /**
     * 
     * 双击查看基金类资产(货币型)
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    FundCurrencyDto queryFundCurrencyList(Map<String, Object> params);

}
