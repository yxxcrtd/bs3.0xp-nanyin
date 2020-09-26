/*
 * 文件名：VatDrawMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.batch.vatdraw.dto.BooksetTaxConfigDto;
import com.joyintech.tams.valuation.batch.vatdraw.dto.TaxRuleDto;
import org.springframework.stereotype.Repository;

/**
 * 增值税计提批处理Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年8月1日
 * @see VatDrawCommonMapper
 * @since 1.0
 */
@Repository
public interface VatDrawCommonMapper {

    /**
     * 取计税方式信息,税则共通处理
     * 
     * @param listBokDetailId 账务
     * @param effDate 生效日
     * @param booksetId, @Param("schemeType")String schemeType 
     * @return List税则Dto
     * @see
     * @since 1.0
     */
    List<TaxRuleDto> queryTaxRules(@Param("listBokDetailId") List<String> listBokDetailId,
                                   @Param("effDate") Date effDate, @Param("booksetId")String booksetId, @Param("schemeType")String schemeType);

    /**
     * 读取账套税务配置
     * 
     * @param booksetId 账套代码
     * @param schemeType 方案配置方案类型
     * @param configId 配置项代码
     * @return
     * @see
     * @since 1.0
     */
    List<BooksetTaxConfigDto> queryBooksetTaxConfig(@Param("booksetId") String booksetId,
                                                    @Param("schemeType") String schemeType,
                                                    @Param("configId") String configId);

    /**
     * 增值税账务明细按账套日期每日删除
     * 
     * @param bookSetIds 账套代码集合
     * @see
     * @since 1.0
     */
    void deleteTaxBokDetail(Map<String, Object> param);

    /**
     * 增值税账务明细余额按账套日期每日删除
     * 
     * @param bookSetIds 账套代码集合
     * @see
     * @since 1.0
     */
    void deleteTaxBokBalance(Map<String, Object> param);

    /**
     * 增值税汇总余额按账套日期每日删除
     * 
     * @param bookSetIds 账套代码集合
     * @see
     * @since 1.0
     */
    void deleteTaxBokBalanceSum(Map<String, Object> param);
    
    /**
     * 增值税已抵扣增值额按账套日期每日删除
     * 
     * @param bookSetIds 账套代码集合
     * @see
     * @since 1.0
     */
    void deleteTaxDeductedBalance(Map<String, Object> param);
}
