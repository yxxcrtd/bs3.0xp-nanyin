package com.joyintech.tams.model.valuation.batch.binarytreemodel.dao;

import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 二叉树模型map
 */
@Repository
public interface BinaryTreeModelMapper {

    /**
     * 查询配置了资产代码
     * @return
     */
    List<ModBintreeConfig> findModBintreeConfig(@Param("finprodIds") List<String> finprodIds);

    /**
     * 查询映射
     * @param params
     * @return
     */
    List<String> findOutPut5(Map<String, Object> params);

    /**
     * 资产定价数据查询
     * @param params
     * @return
     */
    ModRiskPremium findModRiskPremium(Map<String, Object> params);

    /**
     * 删除历史模型估值结果
     * @param listInsert
     * @return
     */
    int deleteModCurveResult(List<ModCurveResult> listInsert);

    /**
     * 插入历史模型估值结果
     * @param listInsert
     * @return
     */
    int insertModCurveResult(List<ModCurveResult> listInsert);

    /**
     * 删除定价信息
     * @param listInsert
     * @return
     */
    int deleteModRiskPremium(List<ModRiskPremium> listInsert);

    /**
     * 插入定价信息
     * @param listInsert
     * @return
     */
    int insertModRiskPremium(List<ModRiskPremium> listInsert);

    //获取资产信息集合
    List<ModAssetInfoDto> selectAssetInfo(ModAssetInfoParamsDto paraMap);

    //获取资产现金流计划集合
    List<ModCashFlowDto> getCashFlowPlanInfoByFinprodId(String finprodId);
}
