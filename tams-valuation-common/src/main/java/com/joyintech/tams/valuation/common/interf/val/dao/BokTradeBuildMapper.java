/*
 * 文件名：BokTradeBuildMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.val.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.StlOrdersMapping;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;

/**
 * 交易入账
 * @author zhangwei
 * @version 1.0 2019年1月11日
 * @see BokTradeBuildMapper
 * @since 1.0
 */
@Repository
public interface BokTradeBuildMapper {

    /**
     * 删除入账账务明细表：根据原交易编号
     * @param pTradeId
     * @return
     */
    int deleteBokAcctDetail(String pTradeId);
    
    /**
     * 删除入账信息表：根据原交易编号
     * @param pTradeId
     * @return
     */
    int deleteBokAcct(String pTradeId);

    /**
     * 更加条件查询标的
     * @param finprodId
     * @param finprodType
     * @return
     */
    TargetFinProduct getTargetFinProductByCondition(@Param("finprodId") String finprodId, @Param("finprodType") String finprodType);

    /**
     * 根据交易查询结算指令类型
     * @param trdProductDeal
     * @return
     */
    List<StlOrdersMapping> findOrderTypeByTrade(TrdProductDeal trdProductDeal);
    
    
    /**
     * 根据投资组合代码查询收益类型
     * @param portfolidId
     * @return
     */
    String queryProfitType(String portfolidId);
    
    /**
     * 根据投资组合代码查询对应的理财产品信息
     * @param portfolidId
     * @return
     */
    FinProduct queryFinProductByPortfolidId(String portfolidId);
}
