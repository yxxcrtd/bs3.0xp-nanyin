package com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.valuation.common.dto.modelDto.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zznn
 * @date 2021/7/6
 */
public class CashFlowDiscountReaderResult extends NeedOperateEntity {
    /**
     * 待处理资产信息
     */
    private ModAssetInfoDto modAssetInfoDto;
    /**
     * 贴现法曲线配置
     */
    private ModCurveConfig modCurveConfig;

    /**
     * 曲线代码集合
     */
    private List<String> curveCodes;

    /**
     * 现金流计划信息
     */
    private List<ModCashFlowDto> modCashFlows;
    /**
     * 现金流计算规则
     */
    private List<ModCashFlowCalRuleDto> modCashFlowCalRules;

    /**
     * 现金流支付规则
     */
    private List<ModCashFlowPayRulesDto> modCashFlowPayRules;

    /**
     * 评级信息
     */
    private ModRateDataDto modRateData;

    public ModAssetInfoDto getModAssetInfoDto() {
        return modAssetInfoDto;
    }

    public void setModAssetInfoDto(ModAssetInfoDto modAssetInfoDto) {
        this.modAssetInfoDto = modAssetInfoDto;
    }

    public ModCurveConfig getModCurveConfig() {
        return modCurveConfig;
    }

    public void setModCurveConfig(ModCurveConfig modCurveConfig) {
        this.modCurveConfig = modCurveConfig;
    }

    public List<String> getCurveCodes() {
        return curveCodes;
    }

    public void setCurveCodes(List<String> curveCodes) {
        this.curveCodes = curveCodes;
    }

    public List<ModCashFlowDto> getModCashFlows() {
        return modCashFlows;
    }

    public void setModCashFlows(List<ModCashFlowDto> modCashFlows) {
        this.modCashFlows = modCashFlows;
    }

    public List<ModCashFlowCalRuleDto> getModCashFlowCalRules() {
        return modCashFlowCalRules;
    }

    public void setModCashFlowCalRules(List<ModCashFlowCalRuleDto> modCashFlowCalRules) {
        this.modCashFlowCalRules = modCashFlowCalRules;
    }

    public List<ModCashFlowPayRulesDto> getModCashFlowPayRules() {
        return modCashFlowPayRules;
    }

    public void setModCashFlowPayRules(List<ModCashFlowPayRulesDto> modCashFlowPayRules) {
        this.modCashFlowPayRules = modCashFlowPayRules;
    }

    public ModRateDataDto getModRateData() {
        return modRateData;
    }

    public void setModRateData(ModRateDataDto modRateData) {
        this.modRateData = modRateData;
    }
}


