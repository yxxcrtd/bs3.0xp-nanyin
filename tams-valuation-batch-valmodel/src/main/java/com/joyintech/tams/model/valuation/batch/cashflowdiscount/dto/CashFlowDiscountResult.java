package com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.ModCurveResult;
import com.joyintech.tams.common.base.entity.ModRiskPremium;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

import java.util.List;

/**
 * @author zznn
 * @date 2021/7/7
 */
public class CashFlowDiscountResult extends NeedToWriteEntity {

    /**
     * 最终的债券估值行情数据
     */
    private MstBondValuationInfo mstBondValuationInfo;
    /**
     * 最终的其他债权类估值行情数据
     */
    private FinProductValuation finProductValuation;

    /**
     * 贴现估值结果
     */
    private ModCurveResult modCurveResult;
    /**
     * 贴现估值结果明细集合
     */
    private List<ModCurveResultDetail> modCurveResultDetails;

    /**
     * 资产定价数据
     */
    private ModRiskPremium modRiskPremium;

    public MstBondValuationInfo getMstBondValuationInfo() {
        return mstBondValuationInfo;
    }

    public void setMstBondValuationInfo(MstBondValuationInfo mstBondValuationInfo) {
        this.mstBondValuationInfo = mstBondValuationInfo;
    }

    public FinProductValuation getFinProductValuation() {
        return finProductValuation;
    }

    public void setFinProductValuation(FinProductValuation finProductValuation) {
        this.finProductValuation = finProductValuation;
    }

    public ModCurveResult getModCurveResult() {
        return modCurveResult;
    }

    public void setModCurveResult(ModCurveResult modCurveResult) {
        this.modCurveResult = modCurveResult;
    }

    public List<ModCurveResultDetail> getModCurveResultDetails() {
        return modCurveResultDetails;
    }

    public void setModCurveResultDetails(List<ModCurveResultDetail> modCurveResultDetails) {
        this.modCurveResultDetails = modCurveResultDetails;
    }

    public ModRiskPremium getModRiskPremium() {
        return modRiskPremium;
    }

    public void setModRiskPremium(ModRiskPremium modRiskPremium) {
        this.modRiskPremium = modRiskPremium;
    }
}

