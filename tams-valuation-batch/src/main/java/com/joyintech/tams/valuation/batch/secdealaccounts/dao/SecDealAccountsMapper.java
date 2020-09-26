/*
 * 文件名：SecDealAccountsMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.secdealaccounts.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.secdealaccounts.dto.StockCostDto;

/**
 * 债券交易账务处理 - 数据库数据获取
 * 
 * @author yangjian
 * @version 1.0 2018年5月24日
 * @see SecDealAccountsMapper
 * @since 1.0
 */
@Repository
public interface SecDealAccountsMapper {

    /**
     * 获取当前金融产品百元面值
     * 
     * @param finprodId  金融产品代码（资产代码）
     * @param happenDate 发生日
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    BigDecimal getFaceValueNow(@Param("finprodId") String finprodId, @Param("happenDate") Date happenDate);
    
    List<StockCostDto> queryStockCostNeedElement(String tradeId);
}
