package com.joyintech.tams.model.valuation.batch.binarytreemodel.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.ModBintreeConfig;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.valuation.common.dto.modelDto.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 二叉树模型-reader
 */
public class BinaryTreeModelReaderEntity extends NeedOperateEntity {

    /**
     * 资产基本信息
     */
    private ModAssetInfoDto modAssetInfoDto;

    /**
     * 资产配置信息
     */
    private ModBintreeConfig modBintreeConfig;

    /**
     * 资产现金流计划集合
     */
    private List<ModCashFlowDto> modCashFlowList;

    /**
     * 现金流计算规则集合
     */
    private List<ModCashFlowCalRuleDto> modCashFlowCalRuleList;

    /**
     * 现金流支付规则集合
     */
    private List<ModCashFlowPayRulesDto> modCashFlowPayRulesList;

    /**
     * 股票行情集合
     */
    private List<MstSharePrice> modStockMarketInfoList;

    /**
     * 曲线行情集合
     */
    private List<ModYieldCurveInfoDto> modYieldCurveInfoList;

    public ModBintreeConfig getModBintreeConfig() {
        return modBintreeConfig;
    }

    public void setModBintreeConfig(ModBintreeConfig modBintreeConfig) {
        this.modBintreeConfig = modBintreeConfig;
    }

    public List<ModCashFlowDto> getModCashFlowList() {
        return modCashFlowList;
    }

    public void setModCashFlowList(List<ModCashFlowDto> modCashFlowList) {
        this.modCashFlowList = modCashFlowList;
    }

    public List<ModCashFlowCalRuleDto> getModCashFlowCalRuleList() {
        return modCashFlowCalRuleList;
    }

    public void setModCashFlowCalRuleList(List<ModCashFlowCalRuleDto> modCashFlowCalRuleList) {
        this.modCashFlowCalRuleList = modCashFlowCalRuleList;
    }

    public List<ModCashFlowPayRulesDto> getModCashFlowPayRulesList() {
        return modCashFlowPayRulesList;
    }

    public void setModCashFlowPayRulesList(List<ModCashFlowPayRulesDto> modCashFlowPayRulesList) {
        this.modCashFlowPayRulesList = modCashFlowPayRulesList;
    }

    public List<MstSharePrice> getModStockMarketInfoList() {
        return modStockMarketInfoList;
    }

    public void setModStockMarketInfoList(List<MstSharePrice> modStockMarketInfoList) {
        this.modStockMarketInfoList = modStockMarketInfoList;
    }

    public List<ModYieldCurveInfoDto> getModYieldCurveInfoList() {
        return modYieldCurveInfoList;
    }

    public void setModYieldCurveInfoList(List<ModYieldCurveInfoDto> modYieldCurveInfoList) {
        this.modYieldCurveInfoList = modYieldCurveInfoList;
    }

    public ModAssetInfoDto getModAssetInfoDto() {
        return modAssetInfoDto;
    }

    public void setModAssetInfoDto(ModAssetInfoDto modAssetInfoDto) {
        this.modAssetInfoDto = modAssetInfoDto;
    }
}
