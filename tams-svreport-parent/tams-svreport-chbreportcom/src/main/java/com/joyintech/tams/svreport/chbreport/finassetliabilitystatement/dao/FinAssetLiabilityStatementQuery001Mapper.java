package com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.PrdBalance;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品资产负债表查询Mapper
 *
 * @author WanShiWei
 * @version 1.0 2021年4月19日
 * @see FinAssetLiabilityStatementQuery001Mapper
 * @since 1.0
 */
@Repository
public interface FinAssetLiabilityStatementQuery001Mapper {

    /**
     * 查询产品代码和产品名称
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectAllPrdBaseInfo(Map<String, Object> map);

    /**
     * 根据条件查询理财产品资产负债表画面数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<FinAssetLiabilityStatementQuery001Dto> selectAllData(Map<String, Object> map);

    /**
     * 查询导出数据
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<PrdBalance> selectFinAssetLiabilityData(Map<String, Object> map);
}
