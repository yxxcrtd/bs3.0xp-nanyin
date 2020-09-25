package com.joyintech.service.product.productevaluate.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productevaluate.dto.FinAssetConfigDto;
import com.joyintech.service.product.productevaluate.dto.FinBokPositionPrdDto;
import com.joyintech.service.product.productevaluate.dto.FinProductVarDto;
import com.joyintech.service.product.productevaluate.dto.FinproductEvaluateDto;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FinproductEvaluateMapper {

    /**
     * 存续产品查询
     * @param params
     * @return
     */
    List<SelectOptionDto> getProNames(Map<String, Object> params);

    /**
     * 存续产品的发行前信息查询
     * @param params
     * @return
     */
    List<FinproductEvaluateDto> queryBeforeIssue(Map<String, Object> params);

    /**
     * 产品净值表现查询
     * @param params
     * @return
     */
    List<FinBokPositionPrdDto> queryProPerformance(Map<String, Object> params);

    /**
     * 根据产品代码查询产品信息
     * @param params
     * @return
     */
    FinProductVarDto queryProduct(Map<String, Object> params);

    List<FinAssetConfigDto> queryAssetConfigHead(Map<String, Object> params);

    /**
     * 评价日资产配置情况查询
     * @param params
     * @return
     */
    List<FinAssetConfigDto> queryAssetConfig(Map<String, Object> params);

    /**
     * 组合份额查询
     * @param params
     * @return
     */
    List<FinAssetConfigDto>queryCombinedShare(Map<String, Object> params);

    /**
     * 根据产品代码查询子组合代码
     * @param params
     * @return
     */
    List<PtlPortfolio>subCombination(Map<String, Object> params);

}
