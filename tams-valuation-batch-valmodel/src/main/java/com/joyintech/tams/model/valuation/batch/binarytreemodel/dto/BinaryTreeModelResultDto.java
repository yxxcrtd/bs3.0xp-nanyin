package com.joyintech.tams.model.valuation.batch.binarytreemodel.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRiskPremium;

public class BinaryTreeModelResultDto extends NeedToWriteEntity {

    /**
     * 模型估值结果
     */
    private ModCurveResult modCurveResult;
    /**
     * 估值模型风险溢价数据
     */
    private ModRiskPremium modRiskPremium;

    public ModCurveResult getModCurveResult() {
        return modCurveResult;
    }

    public void setModCurveResult(ModCurveResult modCurveResult) {
        this.modCurveResult = modCurveResult;
    }

    public ModRiskPremium getModRiskPremium() {
        return modRiskPremium;
    }

    public void setModRiskPremium(ModRiskPremium modRiskPremium) {
        this.modRiskPremium = modRiskPremium;
    }
}
