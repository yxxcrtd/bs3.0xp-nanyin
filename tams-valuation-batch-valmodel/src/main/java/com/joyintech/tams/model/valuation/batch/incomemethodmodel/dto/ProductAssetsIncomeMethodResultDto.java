package com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.ModCurveResult;
import com.joyintech.tams.common.base.entity.ModRiskPremium;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

/**
 * @Description:收益法估值批处理返回结果
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
public class ProductAssetsIncomeMethodResultDto extends NeedToWriteEntity {
    private static final long serialVersionUID = 1L;
//    /**
//     * 资产定价dto
//     */
//    private ModRiskPremium modRiskPremium;

    /**
     * 估值模型结果数据
     */
    private ModCurveResult modCurveResult;

    /**
     * 最终的债券估值行情数据
     */
    private MstBondValuationInfo mstBondValuationInfo;

//    public ModRiskPremium getModRiskPremium() {
//        return modRiskPremium;
//    }
//
//    public void setModRiskPremium(ModRiskPremium modRiskPremium) {
//        this.modRiskPremium = modRiskPremium;
//    }

    public ModCurveResult getModCurveResult() {
        return modCurveResult;
    }

    public void setModCurveResult(ModCurveResult modCurveResult) {
        this.modCurveResult = modCurveResult;
    }

    public MstBondValuationInfo getMstBondValuationInfo() {
        return mstBondValuationInfo;
    }

    public void setMstBondValuationInfo(MstBondValuationInfo mstBondValuationInfo) {
        this.mstBondValuationInfo = mstBondValuationInfo;
    }
}
