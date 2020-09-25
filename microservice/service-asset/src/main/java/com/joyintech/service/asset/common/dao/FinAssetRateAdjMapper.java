/*
 * 文件名：FinAssetRateAdjMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRuleKey;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.RateAdjDto;

/**
 *  利率调整
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see FinAssetRateAdjMapper
 * @since 1.0
 */
@Repository
public interface FinAssetRateAdjMapper {
    
    List<RateAdjDto> getfinAssetRateAdjByProductId(Map<String, Object> pageParam);
    
    List<SelectOptionDto> queryFeeInfoSel(Map<String, Object> param);
    
    List<RateAdjDto> queryForPage(Map<String, Object> pageParam);
    
    /**
     * 根据cashId删除计算规则
     * <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
    int deleteCashCalcRuleByCashId(FinCashCalcRuleKey key);
    
    /**
     * 根据cashId删除计算规则扩展
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
    int deleteCashCalcRuleAddByCashId(PaFinCashCalcRuleKey key);
    
    
    List<FinCashCalcRule> getFinAssetRateAdjByCashId(@Param("finprodId")String finprodId,@Param("finprodType")String finprodType,@Param("cashId")String cashId,@Param("cashType")String cashType,@Param("isInitial")String isInitial);
    
    List<FinCashCalcRule> getMstRateInfo(@Param("indexId")String indexId);
    
    AssetCommonInfoDto getAssetBasicInfoByFinprodId(Map<String,Object> map);
}
