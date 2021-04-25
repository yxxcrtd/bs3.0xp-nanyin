package com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dao;


import com.joyintech.tams.common.base.entity.HxBalance;
import com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dto.HXAssetLiabilityStatementQuery001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品资产负债表查询Mapper
 *
 * @author WanShiWei
 * @version 1.0 2021年4月19日
 * @see HXAssetLiabilityStatementQuery001Mapper
 * @since 1.0
 */
@Repository
public interface HXAssetLiabilityStatementQuery001Mapper {

    /**
     * 根据条件查询华夏资产负债表画面数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<HXAssetLiabilityStatementQuery001Dto> selectAllData(Map<String, Object> map);

    /**
     * 查询华夏资产负债表数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<HxBalance> selectHxAssetLiabilityData(Map<String, Object> map);

}
