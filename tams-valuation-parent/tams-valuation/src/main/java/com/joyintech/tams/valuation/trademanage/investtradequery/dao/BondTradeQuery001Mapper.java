/*
 * 文件名：BondTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.trademanage.investtradequery.dto.BondTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.TradeFeeDetailDto;

/**
 * 债券交易查询
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see BondTradeQuery001Mapper
 * @since 1.0
 */
public interface BondTradeQuery001Mapper {
    
    BondTradeDto selectByTradeId(Map<String, Object> params);
    
    
    List<TradeFeeDetailDto> selectFeeList(@Param("tradeId")String tradeId, @Param("feeTypes")String feeTypes);

}
