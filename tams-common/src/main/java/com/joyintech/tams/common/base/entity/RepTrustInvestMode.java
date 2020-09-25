package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepTrustInvestMode extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 受托人
     */
    private String repTrustee;

    /**
     * 实际管理人
     */
    private String repActualManager;

    /**
     * 托管人
     */
    private String repCustodian;

    /**
     * 委托投资金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repEntrustInvestMoney;

    /**
     * 资金实际投向
     */
    private String repCapiInvest;

    /**
     * 资金运用方式
     */
    private String repApplicationMode;

    /**
     * 资金运用行业
     */
    private String repCapUseIndu;

    /**
     * 资金运用行业二级分类
     */
    private String repCapUseInduLevel2;

    /**
     * 投资运作起始日期
     */
    private Date repInvestStartdate;

    /**
     * 投资运作终止日期
     */
    private Date repInvestEnddate;

    /**
     * 委外投资属性_中债
     */
    private String repTrustInvestAttribute;

    /**
     * 是否有预期收益率
     */
    private String repContainExpectedYield;

    /**
     * 预期最高收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxExpectedYield;

    /**
     * 预期最低收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinExpectedYield;

    /**
     * 管理费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repManagementRate;

    /**
     * 托管费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTrustRate;

    /**
     * 交易相关合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTradeSumRate;

    /**
     * 中介服务机构合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repIntermediarySumRate;

    /**
     * 其他合计费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherSumRate;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 受托人
     * @return 受托人
     */
    public String getRepTrustee() {
        return repTrustee;
    }

    /**
     * 设置 受托人
     * @param repTrustee 受托人
     */
    public void setRepTrustee(String repTrustee) {
        this.repTrustee = repTrustee == null ? null : repTrustee.trim();
    }

    /**
     * 取得 实际管理人
     * @return 实际管理人
     */
    public String getRepActualManager() {
        return repActualManager;
    }

    /**
     * 设置 实际管理人
     * @param repActualManager 实际管理人
     */
    public void setRepActualManager(String repActualManager) {
        this.repActualManager = repActualManager == null ? null : repActualManager.trim();
    }

    /**
     * 取得 托管人
     * @return 托管人
     */
    public String getRepCustodian() {
        return repCustodian;
    }

    /**
     * 设置 托管人
     * @param repCustodian 托管人
     */
    public void setRepCustodian(String repCustodian) {
        this.repCustodian = repCustodian == null ? null : repCustodian.trim();
    }

    /**
     * 取得 委托投资金额
     * @return 委托投资金额
     */
    public BigDecimal getRepEntrustInvestMoney() {
        return repEntrustInvestMoney;
    }

    /**
     * 设置 委托投资金额
     * @param repEntrustInvestMoney 委托投资金额
     */
    public void setRepEntrustInvestMoney(BigDecimal repEntrustInvestMoney) {
        this.repEntrustInvestMoney = repEntrustInvestMoney;
    }

    /**
     * 取得 资金实际投向
     * @return 资金实际投向
     */
    public String getRepCapiInvest() {
        return repCapiInvest;
    }

    /**
     * 设置 资金实际投向
     * @param repCapiInvest 资金实际投向
     */
    public void setRepCapiInvest(String repCapiInvest) {
        this.repCapiInvest = repCapiInvest == null ? null : repCapiInvest.trim();
    }

    /**
     * 取得 资金运用方式
     * @return 资金运用方式
     */
    public String getRepApplicationMode() {
        return repApplicationMode;
    }

    /**
     * 设置 资金运用方式
     * @param repApplicationMode 资金运用方式
     */
    public void setRepApplicationMode(String repApplicationMode) {
        this.repApplicationMode = repApplicationMode == null ? null : repApplicationMode.trim();
    }

    /**
     * 取得 资金运用行业
     * @return 资金运用行业
     */
    public String getRepCapUseIndu() {
        return repCapUseIndu;
    }

    /**
     * 设置 资金运用行业
     * @param repCapUseIndu 资金运用行业
     */
    public void setRepCapUseIndu(String repCapUseIndu) {
        this.repCapUseIndu = repCapUseIndu == null ? null : repCapUseIndu.trim();
    }

    /**
     * 取得 资金运用行业二级分类
     * @return 资金运用行业二级分类
     */
    public String getRepCapUseInduLevel2() {
        return repCapUseInduLevel2;
    }

    /**
     * 设置 资金运用行业二级分类
     * @param repCapUseInduLevel2 资金运用行业二级分类
     */
    public void setRepCapUseInduLevel2(String repCapUseInduLevel2) {
        this.repCapUseInduLevel2 = repCapUseInduLevel2 == null ? null : repCapUseInduLevel2.trim();
    }

    /**
     * 取得 投资运作起始日期
     * @return 投资运作起始日期
     */
    public Date getRepInvestStartdate() {
        return repInvestStartdate;
    }

    /**
     * 设置 投资运作起始日期
     * @param repInvestStartdate 投资运作起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepInvestStartdate(Date repInvestStartdate) {
        this.repInvestStartdate = repInvestStartdate;
    }

    /**
     * 取得 投资运作终止日期
     * @return 投资运作终止日期
     */
    public Date getRepInvestEnddate() {
        return repInvestEnddate;
    }

    /**
     * 设置 投资运作终止日期
     * @param repInvestEnddate 投资运作终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepInvestEnddate(Date repInvestEnddate) {
        this.repInvestEnddate = repInvestEnddate;
    }

    /**
     * 取得 委外投资属性_中债
     * @return 委外投资属性_中债
     */
    public String getRepTrustInvestAttribute() {
        return repTrustInvestAttribute;
    }

    /**
     * 设置 委外投资属性_中债
     * @param repTrustInvestAttribute 委外投资属性_中债
     */
    public void setRepTrustInvestAttribute(String repTrustInvestAttribute) {
        this.repTrustInvestAttribute = repTrustInvestAttribute == null ? null : repTrustInvestAttribute.trim();
    }

    /**
     * 取得 是否有预期收益率
     * @return 是否有预期收益率
     */
    public String getRepContainExpectedYield() {
        return repContainExpectedYield;
    }

    /**
     * 设置 是否有预期收益率
     * @param repContainExpectedYield 是否有预期收益率
     */
    public void setRepContainExpectedYield(String repContainExpectedYield) {
        this.repContainExpectedYield = repContainExpectedYield == null ? null : repContainExpectedYield.trim();
    }

    /**
     * 取得 预期最高收益率%
     * @return 预期最高收益率%
     */
    public BigDecimal getRepMaxExpectedYield() {
        return repMaxExpectedYield;
    }

    /**
     * 设置 预期最高收益率%
     * @param repMaxExpectedYield 预期最高收益率%
     */
    public void setRepMaxExpectedYield(BigDecimal repMaxExpectedYield) {
        this.repMaxExpectedYield = repMaxExpectedYield;
    }

    /**
     * 取得 预期最低收益率%
     * @return 预期最低收益率%
     */
    public BigDecimal getRepMinExpectedYield() {
        return repMinExpectedYield;
    }

    /**
     * 设置 预期最低收益率%
     * @param repMinExpectedYield 预期最低收益率%
     */
    public void setRepMinExpectedYield(BigDecimal repMinExpectedYield) {
        this.repMinExpectedYield = repMinExpectedYield;
    }

    /**
     * 取得 管理费率
     * @return 管理费率
     */
    public BigDecimal getRepManagementRate() {
        return repManagementRate;
    }

    /**
     * 设置 管理费率
     * @param repManagementRate 管理费率
     */
    public void setRepManagementRate(BigDecimal repManagementRate) {
        this.repManagementRate = repManagementRate;
    }

    /**
     * 取得 托管费率%
     * @return 托管费率%
     */
    public BigDecimal getRepTrustRate() {
        return repTrustRate;
    }

    /**
     * 设置 托管费率%
     * @param repTrustRate 托管费率%
     */
    public void setRepTrustRate(BigDecimal repTrustRate) {
        this.repTrustRate = repTrustRate;
    }

    /**
     * 取得 交易相关合计费率%
     * @return 交易相关合计费率%
     */
    public BigDecimal getRepTradeSumRate() {
        return repTradeSumRate;
    }

    /**
     * 设置 交易相关合计费率%
     * @param repTradeSumRate 交易相关合计费率%
     */
    public void setRepTradeSumRate(BigDecimal repTradeSumRate) {
        this.repTradeSumRate = repTradeSumRate;
    }

    /**
     * 取得 中介服务机构合计费率%
     * @return 中介服务机构合计费率%
     */
    public BigDecimal getRepIntermediarySumRate() {
        return repIntermediarySumRate;
    }

    /**
     * 设置 中介服务机构合计费率%
     * @param repIntermediarySumRate 中介服务机构合计费率%
     */
    public void setRepIntermediarySumRate(BigDecimal repIntermediarySumRate) {
        this.repIntermediarySumRate = repIntermediarySumRate;
    }

    /**
     * 取得 其他合计费率%
     * @return 其他合计费率%
     */
    public BigDecimal getRepOtherSumRate() {
        return repOtherSumRate;
    }

    /**
     * 设置 其他合计费率%
     * @param repOtherSumRate 其他合计费率%
     */
    public void setRepOtherSumRate(BigDecimal repOtherSumRate) {
        this.repOtherSumRate = repOtherSumRate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}