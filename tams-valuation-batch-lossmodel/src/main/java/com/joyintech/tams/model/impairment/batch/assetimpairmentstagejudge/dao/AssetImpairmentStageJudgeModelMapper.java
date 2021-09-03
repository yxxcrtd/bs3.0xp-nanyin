package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dao;

import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.*;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRepayAndInterestDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 资产减值阶段判断模型map
 */
@Repository
public interface AssetImpairmentStageJudgeModelMapper {

    /**
     * 查询减值阶段参数Map
     * @return
     */
    List<ModStageParaDto> modStageParaList();

    /**
     * 查询减值结果过程表
     * @param params
     * @return
     */
    List<ModEclResultProcess> assetImpairmentStageJudgeList(Map<String, Object> params);

    /**
     * 查询还本收息交易列表
     * @param assetId
     * @return
     */
    List<ModRepayAndInterestDto> assetImpairmentStageJudgeT2322List(String assetId);

    /**
     * 删除
     * @param params
     * @return
     */
    int deleteResult(Map<String, Object> params);

    //获取资产现金流计划集合
    List<ModCashFlowDto> getCashFlowPlanInfoByFinprodId(String finprodId);

}
