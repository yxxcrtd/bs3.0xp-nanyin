/*
 * 文件名：FinAssetRateAdjMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.asset.dao;

import com.joyintech.service.valuation.component.asset.dto.AssetCommonInfoDto;
import com.joyintech.service.valuation.component.asset.dto.FinAssetRateAdjDto;
import com.joyintech.service.valuation.component.asset.dto.FinCashPayRuleDto;
import com.joyintech.service.valuation.component.asset.dto.FinCashPlanDto;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    
    List<FinCashCalcRule> getFinAssetRateAdjByCashId(@Param("finprodId") String finprodId, @Param("finprodType") String finprodType, @Param("cashId") String cashId, @Param("cashType") String cashType, @Param("isInitial") String isInitial);


    List<FinCashCalcRule> getMstRateInfo(@Param("indexId")String indexId);

    List<FinAssetRateAdjDto> getfinAssetRateAdjByProductId(Map<String, Object> pageParam);

    List<FinAssetRateAdjDto> queryForPage(Map<String, Object> pageParam);

    AssetCommonInfoDto getAssetInfoToRateAdj(Map<String, Object> param);

    AssetCommonInfoDto getMdateByFinproId(@Param("finprodId")String finprodId);

    int deleteCashCalcRuleByCashId(FinCashCalcRuleKey key);


    AssetCommonInfoDto getAssetBasicInfoByFinprodId(Map<String,Object> param);

    List<FinCashPlanDto> getFinCashPlanByProductId(Map<String,Object> param);

    FinCashPayRuleDto getFinPayInterestStdInfoByProductId(Map<String,Object> param);

    List<String> getDealCalendarHoliday(Map<String, Object> paraMap);

}
