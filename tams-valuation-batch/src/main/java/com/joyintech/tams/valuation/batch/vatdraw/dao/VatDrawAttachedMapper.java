/*
 * 文件名：VatDrawAttachedMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.TaxBokBalanceSum;
import com.joyintech.tams.valuation.batch.vatdraw.dto.AddTaxRuleDto;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author weipeng
 * @version 1.0 2018年8月7日
 * @see VatDrawAttachedMapper
 * @since 1.0
 */
@Repository
public interface VatDrawAttachedMapper {

    /**
     * 取附加税计税方式信息
     * 
     * @param booksetId 账套代码
     * @param happenDate 发生日
     * @param schemeType 方案类型
     * @return
     * @see
     * @since 1.0
     */
    List<AddTaxRuleDto> getAddTaxCalType(@Param("booksetId") String booksetId,
                                         @Param("happenDate") Date happenDate,
                                         @Param("schemeType") String schemeType);

    /**
     * 查询账务明细最大的处理序列
     * 
     * @param happenDate 发生日
     * @param bookDate 入账日
     * @param booksetId 账套代码
     * @param vatType 增值税类型
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal querySumMaxBookOrders(@Param("happenDate") Date happenDate,
                                     @Param("bookDate") Date bookDate,
                                     @Param("booksetId") String booksetId,
                                     @Param("vatType") String vatType);
    /**
     * 查询发生日增值税余额
     * @param happenDate 发生日期
     * @param bookDate 入账日期
     * @param bookOrder 处理序列
     * @param booksetId 账套代码
     * @return 发生日增值税余额
     * @see
     * @since 1.0
     */
    List<TaxBokBalanceSum> queryTdyTaxBalanceSum(@Param("happenDate") Date happenDate,
                                                 @Param("bookDate") Date bookDate,
                                                 @Param("bookOrder") BigDecimal bookOrder,
                                                 @Param("booksetId") String booksetId);

    List<AddTaxRuleDto> getAddTaxCalTypeByMultiLevel(@Param("booksetId") String booksetId,
                                                     @Param("happenDate") Date happenDate,
                                                     @Param("schemeType") String schemeType,
                                                     @Param("createInst") String createInst,
                                                     @Param("generalInst") String generalInst);

}
