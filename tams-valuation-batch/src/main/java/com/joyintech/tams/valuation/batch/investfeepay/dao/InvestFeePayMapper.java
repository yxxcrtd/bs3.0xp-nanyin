/*
 * 文件名：FeeTableSetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年9月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.investfeepay.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokFeeTableSet;

/**
 * 查询费用配置信息
 * 
 * @author HuangJian
 * @version 1.0 2018年9月27日
 * @see InvestFeePayMapper
 * @since 1.0
 */
@Repository
public interface InvestFeePayMapper {

    /**
     * 
     * 查询费用配置信息
     * @param ps 交易方向
     * @return List<FeeTableSet>
     * @see
     * @since 1.0
     */
    List<BokFeeTableSet> getFeeTableSetList(String ps);
    
    /**
     * 通过费用查询对应的场景代码
     * @param ps 交易方向
     * @param finprodType 金融产品类型
     * @param feeType 费用类型
     * @return
     * @see
     * @since 1.0
     */
    List<String> getTableIdByFee(@Param("ps")String ps,
                                         @Param("finprodType")String finprodType,
                                         @Param("feeType")String feeType);
    /**
     * 根据交易信息，查询理论金额
     */
    BigDecimal queryTheoryInt(String tradeId);

}
