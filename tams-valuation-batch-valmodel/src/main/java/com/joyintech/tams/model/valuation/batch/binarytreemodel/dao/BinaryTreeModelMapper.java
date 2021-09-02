package com.joyintech.tams.model.valuation.batch.binarytreemodel.dao;

import com.joyintech.tams.common.base.entity.ModBintreeConfig;
import com.joyintech.tams.common.base.entity.ModCurveResult;
import com.joyintech.tams.common.base.entity.ModRiskPremium;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.valuation.common.dto.modelDto.ModAssetInfoDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModAssetInfoParamsDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
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

    void deleteMstBondValuationInfo(List<MstBondValuationInfo> listInsert);

    void insertMstBondValuationInfo(List<MstBondValuationInfo> listInsert);
}
