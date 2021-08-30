package com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.ModStockdivitrateConfig;
import com.joyintech.tams.valuation.common.dto.modelDto.ModAssetInfoDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowCalRuleDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowPayRulesDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:资产信息实体类
 * @auther: yuzj
 * @date: 2021/7/3
 * @param:
 * @return:
 */
public class ProductAssetsIncomeMethodReaderDto extends NeedOperateEntity {
    private static final long serialVersionUID = 1L;

    private ModAssetInfoDto modAssetInfoDto;

    private ModStockdivitrateConfig modStockdivitrateConfig;

    /**
     * List<现金流计划>
     */
    private List<ModCashFlowDto> FinCashPlanDtoList;

    /**
     * List<现金流计算规则>
     */
    private List<ModCashFlowCalRuleDto> FinCashCalcRuleDtoList;

    /**
     * List<现金流支付规则>
     */
    private List<ModCashFlowPayRulesDto> modCashFlowPayRulesList;

    public ModAssetInfoDto getModAssetInfoDto() {
        return modAssetInfoDto;
    }

    public void setModAssetInfoDto(ModAssetInfoDto modAssetInfoDto) {
        this.modAssetInfoDto = modAssetInfoDto;
    }

    public List<ModCashFlowDto> getFinCashPlanDtoList() {
        return FinCashPlanDtoList;
    }

    public void setFinCashPlanDtoList(List<ModCashFlowDto> finCashPlanDtoList) {
        FinCashPlanDtoList = finCashPlanDtoList;
    }

    public List<ModCashFlowCalRuleDto> getFinCashCalcRuleDtoList() {
        return FinCashCalcRuleDtoList;
    }

    public void setFinCashCalcRuleDtoList(List<ModCashFlowCalRuleDto> finCashCalcRuleDtoList) {
        FinCashCalcRuleDtoList = finCashCalcRuleDtoList;
    }

    public List<ModCashFlowPayRulesDto> getModCashFlowPayRulesList() {
        return modCashFlowPayRulesList;
    }

    public void setModCashFlowPayRulesList(List<ModCashFlowPayRulesDto> modCashFlowPayRulesList) {
        this.modCashFlowPayRulesList = modCashFlowPayRulesList;
    }

    public ModStockdivitrateConfig getModStockdivitrateConfig() {
        return modStockdivitrateConfig;
    }

    public void setModStockdivitrateConfig(ModStockdivitrateConfig modStockdivitrateConfig) {
        this.modStockdivitrateConfig = modStockdivitrateConfig;
    }
}
