package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowCalRuleDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowPayRulesDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EadCalculationReaderDto extends NeedOperateEntity {

    /**
     * 投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）
     */
    private String portfolioId;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 模型资产类型
     */
    private String modelAssetType;
    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 息票品种
     */
    private String couponSpecies;

    /**
     * 发行价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal issuePrice;

    /**
     * 买入交易列表
     */
    private List<EadModEclResultProcess> tradeInList;

    /**
     * 卖出交易列表
     */
    private List<EadModEclResultProcess>  tradeOutList;

    /**
     * 现金流计划列表
     */
    private List<ModCashFlowDto> cashFlowList;

    /**
     * 现金流计算规则
     */
    private List<ModCashFlowCalRuleDto> cashFlowCalRuleList;

    /**
     * 现金流支付规则
     * @return
     */
    private List<ModCashFlowPayRulesDto>  modCashFlowPayRulesList;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    @Override
    public String getAssetId() {
        return assetId;
    }

    @Override
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getModelAssetType() {
        return modelAssetType;
    }

    public void setModelAssetType(String modelAssetType) {
        this.modelAssetType = modelAssetType;
    }

    public List<EadModEclResultProcess> getTradeInList() {
        return tradeInList;
    }

    public void setTradeInList(List<EadModEclResultProcess> tradeInList) {
        this.tradeInList = tradeInList;
    }

    public List<EadModEclResultProcess> getTradeOutList() {
        return tradeOutList;
    }

    public void setTradeOutList(List<EadModEclResultProcess> tradeOutList) {
        this.tradeOutList = tradeOutList;
    }

    public List<ModCashFlowDto> getCashFlowList() {
        return cashFlowList;
    }

    public void setCashFlowList(List<ModCashFlowDto> cashFlowList) {
        this.cashFlowList = cashFlowList;
    }

    public List<ModCashFlowCalRuleDto> getCashFlowCalRuleList() {
        return cashFlowCalRuleList;
    }

    public void setCashFlowCalRuleList(List<ModCashFlowCalRuleDto> cashFlowCalRuleList) {
        this.cashFlowCalRuleList = cashFlowCalRuleList;
    }

    public List<ModCashFlowPayRulesDto> getModCashFlowPayRulesList() {
        return modCashFlowPayRulesList;
    }

    public void setModCashFlowPayRulesList(List<ModCashFlowPayRulesDto> modCashFlowPayRulesList) {
        this.modCashFlowPayRulesList = modCashFlowPayRulesList;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getCouponSpecies() {
        return couponSpecies;
    }

    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies;
    }

    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }
}
