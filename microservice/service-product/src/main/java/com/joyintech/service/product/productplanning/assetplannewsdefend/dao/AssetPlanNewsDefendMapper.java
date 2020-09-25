package com.joyintech.service.product.productplanning.assetplannewsdefend.dao;

import com.joyintech.service.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsDefend;
import com.joyintech.tams.common.base.entity.AssetPlanDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AssetPlanNewsDefendMapper {

    List<AssetPlanNewsDefend> selectForPage(Map<String, Object> params);

    /**
     * 查询时间冲突的数量
     * @param params
     * @return
     */
    int getConflictCount(Map<String, Object> params);

    /**
     * 查询时间重叠的数量
     * @param params
     * @return
     */
    int getOverlapCount(Map<String, Object> params);

    /**
     * 产品系列规划目标
     * @param params
     * @return
     */
    List<AssetPlanNewsDefend>queryProductTarget(Map<String, Object> params);

    int deleteAssetPlan(Map<String, Object> params);

    int deleteAssetPlanDetail(Map<String, Object> params);

    List<AssetPlanDetail>getDetailData(String assetPlanCode);
}
