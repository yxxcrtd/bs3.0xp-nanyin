package com.joyintech.tams.invest.batch.assetintdatecarryover.dao;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.batch.assetintdatecarryover.dto.AssetIntdateCarryForwardDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AssetIntdateCarryForwardMapper {
    //查询待处理现金流计划
    List<AssetIntdateCarryForwardDto> queryAssetIntdateCarryForwardDtoList(Map<String, Object> params);

    /**
     * 根据金融产品代码，现金流一级类型查询现金流计划
     * @param finprodId
     * @param cashType1
     * @return
     */
    List<FinCashPlan> queryCashPlan(@Param("finprodId") String finprodId,@Param("cashType1") String cashType1);
}
