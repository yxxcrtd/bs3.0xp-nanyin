package com.joyintech.tams.invest.batch.assetintdatecarryover.dao;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.batch.assetintdatecarryover.dto.AssetIntdateCarryForwardDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 交易类标的生成批处理mapper
 */
@Repository
public interface AssetIntdateCarryForwardMapper {
    //查询待处理现金流计划
    List<AssetIntdateCarryForwardDto> queryAssetIntdateCarryForwardDtoList(Map<String, Object> params);

}
