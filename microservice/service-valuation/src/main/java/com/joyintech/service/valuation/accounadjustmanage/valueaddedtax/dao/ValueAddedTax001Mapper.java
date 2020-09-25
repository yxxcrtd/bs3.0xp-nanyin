/*
 * 文件名：ValueAddedTax001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dto.BooksetInfoDto;
import com.joyintech.service.valuation.accounadjustmanage.valueaddedtax.dto.ValueAddedTax001Dto;
import com.joyintech.tams.common.base.entity.TaxBokBalanceSum;
import com.joyintech.tams.common.base.entity.TaxBokDetail;
import com.joyintech.tams.common.base.entity.TaxDeclareSumInfo;

/**
 * 估值余额表查询Mapper
 * 
 * @author ruanhongming
 * @version 1.0 2018年9月17日
 * @see ValueAddedTax001Mapper
 * @since 1.0
 */
@Repository(value="serviceValueAddedTax001Mapper")
public interface ValueAddedTax001Mapper {
    
    /**
     * 查询增值税台账
     * @param pageParam
     * @return
     */
    List<ValueAddedTax001Dto> selectAll(Map<String, Object> pageParam);
//    List<BokBalanceStatementQuery001Dto> selectSum(Map<String, Object> pageParam);
    
    /**查询是否价税分离
     * 
     * @param dto
     * @return
     */
    List<BooksetInfoDto> checkPriceTaxSparation(Map<String, Object> pageParam);
    
    /**
     * 查询上一征期的纳税申报信息
     * @param pageParam
     * @return
     */
    List<TaxDeclareSumInfo> quryTaxDeclarSumInfoFore(Map<String, Object> pageParam);
    
    /**
     * 最新计税流水信息
     * @param pageParam
     * @return
     */
    List<TaxBokDetail> quryTaxBokDetail(Map<String, Object> pageParam);
    
    /**
     * 最新计税余额信息
     * @param pageParam
     * @return
     */
    List<TaxBokBalanceSum> quryTaxBokBalanceSum(Map<String, Object> pageParam);
    
    /**
     * 其他统计维度信息汇总
     * @param pageParam
     * @return
     */
    TaxDeclareSumInfo sumTaxDeclareSumInfo(Map<String, Object> pageParam);
    
    /**
     * 根据汇总维度查询汇总值
     * @param pageParam
     * @return
     */
    List<ValueAddedTax001Dto> selectSum(Map<String, Object> pageParam);
    
    /**
     * 查询纳税申报表中是否已有该维度信息
     * @param dto
     * @return
     */
    int checkSigleProduct(TaxDeclareSumInfo dto);
    
    /**
     * 
     * 删除纳税申报表中已有该维度信息
     * @param dto
     */
    void deleteSigleProduct(TaxDeclareSumInfo dto);
    
}
