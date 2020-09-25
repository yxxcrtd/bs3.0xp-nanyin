package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PaFinProduct extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 处理模式，“普通”、“滚动”、“日添利”、“智能日添利”等
     */
    private String dealMode;

    /**
     * 资产来源，行内、行外
     */
    private String assetSource;

    /**
     * 资产提供行，多选，逗号分隔
     */
    private String assetProvider;

    /**
     * CMS出账编号
     */
    private String cashOutNo;

    /**
     * 资产来源条线
     */
    private String assetSourceUnit;

    /**
     * 放款分行
     */
    private String loadBank;

    /**
     * 清算时效（工作日）
     */
    private Long settleDays;

    /**
     * 项目投向行业
     */
    private String projInvestIndustry;

    /**
     * 项目投向行业二级分类
     */
    private String projInvestIndustry2;

    /**
     * 人行信托代码
     */
    private String bankTrustCode;

    /**
     * 实际授信主体
     */
    private String actualCreditProvid;

    /**
     * null
     */
    private String investmentAttributes;

    /**
     * 产品系列
     */
    private String prodSeries;

    /**
     * 产品分类
     */
    private String prodClass;

    /**
     * 产品特点
     */
    private String prodFeature;

    /**
     * 专属产品
     */
    private String exclusiveProd;

    /**
     * 产品经理
     */
    private String prodManager;

    /**
     * 销售类型
     */
    private String saleType;

    /**
     * 同业机构
     */
    private String sameOrg;

    /**
     * 投资周期 单位：天
     */
    private String investmentCycle;

    /**
     * 目标客户一级分类
     */
    private String targetCustomer1;

    /**
     * 目标客户二级分类
     */
    private String targetCustomer2;

    /**
     * 资产授信名称
     */
    private String assetCreditName;

    /**
     * 股息是否累计
     */
    private String isInterest;

    /**
     * 法定到期日
     */
    private Date legalMdate;

    /**
     * 是否委外
     */
    private String isOuts;

    /**
     * 系列名称
     */
    private String seriesName;

    /**
     * 资产分类
     */
    private String assetClass;

    /**
     * 实际管理人
     */
    private String actualManager;

    /**
     * 人行投向产品代码
     */
    private String bankInvestCode;

    /**
     * 资管计划属性
     */
    private String repPlanProp;

    /**
     * 利率类型，存款类前台只支持固定利率，新增字段记录浮动利率资产
     */
    private String intType;

    /**
     * 法审编号
     */
    private String trialNo;

    /**
     * 业绩基准
     */
    private String baseRule;

    /**
     * 超额分成方式
     */
    private String overallocation;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 增值税税率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vatTaxRate;

    /**
     * 附加税税率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal adtTaxRate;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 处理模式，“普通”、“滚动”、“日添利”、“智能日添利”等
     * @return 处理模式，“普通”、“滚动”、“日添利”、“智能日添利”等
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置 处理模式，“普通”、“滚动”、“日添利”、“智能日添利”等
     * @param dealMode 处理模式，“普通”、“滚动”、“日添利”、“智能日添利”等
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode == null ? null : dealMode.trim();
    }

    /**
     * 取得 资产来源，行内、行外
     * @return 资产来源，行内、行外
     */
    public String getAssetSource() {
        return assetSource;
    }

    /**
     * 设置 资产来源，行内、行外
     * @param assetSource 资产来源，行内、行外
     */
    public void setAssetSource(String assetSource) {
        this.assetSource = assetSource == null ? null : assetSource.trim();
    }

    /**
     * 取得 资产提供行，多选，逗号分隔
     * @return 资产提供行，多选，逗号分隔
     */
    public String getAssetProvider() {
        return assetProvider;
    }

    /**
     * 设置 资产提供行，多选，逗号分隔
     * @param assetProvider 资产提供行，多选，逗号分隔
     */
    public void setAssetProvider(String assetProvider) {
        this.assetProvider = assetProvider == null ? null : assetProvider.trim();
    }

    /**
     * 取得 CMS出账编号
     * @return CMS出账编号
     */
    public String getCashOutNo() {
        return cashOutNo;
    }

    /**
     * 设置 CMS出账编号
     * @param cashOutNo CMS出账编号
     */
    public void setCashOutNo(String cashOutNo) {
        this.cashOutNo = cashOutNo == null ? null : cashOutNo.trim();
    }

    /**
     * 取得 资产来源条线
     * @return 资产来源条线
     */
    public String getAssetSourceUnit() {
        return assetSourceUnit;
    }

    /**
     * 设置 资产来源条线
     * @param assetSourceUnit 资产来源条线
     */
    public void setAssetSourceUnit(String assetSourceUnit) {
        this.assetSourceUnit = assetSourceUnit == null ? null : assetSourceUnit.trim();
    }

    /**
     * 取得 放款分行
     * @return 放款分行
     */
    public String getLoadBank() {
        return loadBank;
    }

    /**
     * 设置 放款分行
     * @param loadBank 放款分行
     */
    public void setLoadBank(String loadBank) {
        this.loadBank = loadBank == null ? null : loadBank.trim();
    }

    /**
     * 取得 清算时效（工作日）
     * @return 清算时效（工作日）
     */
    public Long getSettleDays() {
        return settleDays;
    }

    /**
     * 设置 清算时效（工作日）
     * @param settleDays 清算时效（工作日）
     */
    public void setSettleDays(Long settleDays) {
        this.settleDays = settleDays;
    }

    /**
     * 取得 项目投向行业
     * @return 项目投向行业
     */
    public String getProjInvestIndustry() {
        return projInvestIndustry;
    }

    /**
     * 设置 项目投向行业
     * @param projInvestIndustry 项目投向行业
     */
    public void setProjInvestIndustry(String projInvestIndustry) {
        this.projInvestIndustry = projInvestIndustry == null ? null : projInvestIndustry.trim();
    }

    /**
     * 取得 项目投向行业二级分类
     * @return 项目投向行业二级分类
     */
    public String getProjInvestIndustry2() {
        return projInvestIndustry2;
    }

    /**
     * 设置 项目投向行业二级分类
     * @param projInvestIndustry2 项目投向行业二级分类
     */
    public void setProjInvestIndustry2(String projInvestIndustry2) {
        this.projInvestIndustry2 = projInvestIndustry2 == null ? null : projInvestIndustry2.trim();
    }

    /**
     * 取得 人行信托代码
     * @return 人行信托代码
     */
    public String getBankTrustCode() {
        return bankTrustCode;
    }

    /**
     * 设置 人行信托代码
     * @param bankTrustCode 人行信托代码
     */
    public void setBankTrustCode(String bankTrustCode) {
        this.bankTrustCode = bankTrustCode == null ? null : bankTrustCode.trim();
    }

    /**
     * 取得 实际授信主体
     * @return 实际授信主体
     */
    public String getActualCreditProvid() {
        return actualCreditProvid;
    }

    /**
     * 设置 实际授信主体
     * @param actualCreditProvid 实际授信主体
     */
    public void setActualCreditProvid(String actualCreditProvid) {
        this.actualCreditProvid = actualCreditProvid == null ? null : actualCreditProvid.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvestmentAttributes() {
        return investmentAttributes;
    }

    /**
     * 设置 null
     * @param investmentAttributes null
     */
    public void setInvestmentAttributes(String investmentAttributes) {
        this.investmentAttributes = investmentAttributes == null ? null : investmentAttributes.trim();
    }

    /**
     * 取得 产品系列
     * @return 产品系列
     */
    public String getProdSeries() {
        return prodSeries;
    }

    /**
     * 设置 产品系列
     * @param prodSeries 产品系列
     */
    public void setProdSeries(String prodSeries) {
        this.prodSeries = prodSeries == null ? null : prodSeries.trim();
    }

    /**
     * 取得 产品分类
     * @return 产品分类
     */
    public String getProdClass() {
        return prodClass;
    }

    /**
     * 设置 产品分类
     * @param prodClass 产品分类
     */
    public void setProdClass(String prodClass) {
        this.prodClass = prodClass == null ? null : prodClass.trim();
    }

    /**
     * 取得 产品特点
     * @return 产品特点
     */
    public String getProdFeature() {
        return prodFeature;
    }

    /**
     * 设置 产品特点
     * @param prodFeature 产品特点
     */
    public void setProdFeature(String prodFeature) {
        this.prodFeature = prodFeature == null ? null : prodFeature.trim();
    }

    /**
     * 取得 专属产品
     * @return 专属产品
     */
    public String getExclusiveProd() {
        return exclusiveProd;
    }

    /**
     * 设置 专属产品
     * @param exclusiveProd 专属产品
     */
    public void setExclusiveProd(String exclusiveProd) {
        this.exclusiveProd = exclusiveProd == null ? null : exclusiveProd.trim();
    }

    /**
     * 取得 产品经理
     * @return 产品经理
     */
    public String getProdManager() {
        return prodManager;
    }

    /**
     * 设置 产品经理
     * @param prodManager 产品经理
     */
    public void setProdManager(String prodManager) {
        this.prodManager = prodManager == null ? null : prodManager.trim();
    }

    /**
     * 取得 销售类型
     * @return 销售类型
     */
    public String getSaleType() {
        return saleType;
    }

    /**
     * 设置 销售类型
     * @param saleType 销售类型
     */
    public void setSaleType(String saleType) {
        this.saleType = saleType == null ? null : saleType.trim();
    }

    /**
     * 取得 同业机构
     * @return 同业机构
     */
    public String getSameOrg() {
        return sameOrg;
    }

    /**
     * 设置 同业机构
     * @param sameOrg 同业机构
     */
    public void setSameOrg(String sameOrg) {
        this.sameOrg = sameOrg == null ? null : sameOrg.trim();
    }

    /**
     * 取得 投资周期 单位：天
     * @return 投资周期 单位：天
     */
    public String getInvestmentCycle() {
        return investmentCycle;
    }

    /**
     * 设置 投资周期 单位：天
     * @param investmentCycle 投资周期 单位：天
     */
    public void setInvestmentCycle(String investmentCycle) {
        this.investmentCycle = investmentCycle == null ? null : investmentCycle.trim();
    }

    /**
     * 取得 目标客户一级分类
     * @return 目标客户一级分类
     */
    public String getTargetCustomer1() {
        return targetCustomer1;
    }

    /**
     * 设置 目标客户一级分类
     * @param targetCustomer1 目标客户一级分类
     */
    public void setTargetCustomer1(String targetCustomer1) {
        this.targetCustomer1 = targetCustomer1 == null ? null : targetCustomer1.trim();
    }

    /**
     * 取得 目标客户二级分类
     * @return 目标客户二级分类
     */
    public String getTargetCustomer2() {
        return targetCustomer2;
    }

    /**
     * 设置 目标客户二级分类
     * @param targetCustomer2 目标客户二级分类
     */
    public void setTargetCustomer2(String targetCustomer2) {
        this.targetCustomer2 = targetCustomer2 == null ? null : targetCustomer2.trim();
    }

    /**
     * 取得 资产授信名称
     * @return 资产授信名称
     */
    public String getAssetCreditName() {
        return assetCreditName;
    }

    /**
     * 设置 资产授信名称
     * @param assetCreditName 资产授信名称
     */
    public void setAssetCreditName(String assetCreditName) {
        this.assetCreditName = assetCreditName == null ? null : assetCreditName.trim();
    }

    /**
     * 取得 股息是否累计
     * @return 股息是否累计
     */
    public String getIsInterest() {
        return isInterest;
    }

    /**
     * 设置 股息是否累计
     * @param isInterest 股息是否累计
     */
    public void setIsInterest(String isInterest) {
        this.isInterest = isInterest == null ? null : isInterest.trim();
    }

    /**
     * 取得 法定到期日
     * @return 法定到期日
     */
    public Date getLegalMdate() {
        return legalMdate;
    }

    /**
     * 设置 法定到期日
     * @param legalMdate 法定到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLegalMdate(Date legalMdate) {
        this.legalMdate = legalMdate;
    }

    /**
     * 取得 是否委外
     * @return 是否委外
     */
    public String getIsOuts() {
        return isOuts;
    }

    /**
     * 设置 是否委外
     * @param isOuts 是否委外
     */
    public void setIsOuts(String isOuts) {
        this.isOuts = isOuts == null ? null : isOuts.trim();
    }

    /**
     * 取得 系列名称
     * @return 系列名称
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * 设置 系列名称
     * @param seriesName 系列名称
     */
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }

    /**
     * 取得 资产分类
     * @return 资产分类
     */
    public String getAssetClass() {
        return assetClass;
    }

    /**
     * 设置 资产分类
     * @param assetClass 资产分类
     */
    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass == null ? null : assetClass.trim();
    }

    /**
     * 取得 实际管理人
     * @return 实际管理人
     */
    public String getActualManager() {
        return actualManager;
    }

    /**
     * 设置 实际管理人
     * @param actualManager 实际管理人
     */
    public void setActualManager(String actualManager) {
        this.actualManager = actualManager == null ? null : actualManager.trim();
    }

    /**
     * 取得 人行投向产品代码
     * @return 人行投向产品代码
     */
    public String getBankInvestCode() {
        return bankInvestCode;
    }

    /**
     * 设置 人行投向产品代码
     * @param bankInvestCode 人行投向产品代码
     */
    public void setBankInvestCode(String bankInvestCode) {
        this.bankInvestCode = bankInvestCode == null ? null : bankInvestCode.trim();
    }

    /**
     * 取得 资管计划属性
     * @return 资管计划属性
     */
    public String getRepPlanProp() {
        return repPlanProp;
    }

    /**
     * 设置 资管计划属性
     * @param repPlanProp 资管计划属性
     */
    public void setRepPlanProp(String repPlanProp) {
        this.repPlanProp = repPlanProp == null ? null : repPlanProp.trim();
    }

    /**
     * 取得 利率类型，存款类前台只支持固定利率，新增字段记录浮动利率资产
     * @return 利率类型，存款类前台只支持固定利率，新增字段记录浮动利率资产
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型，存款类前台只支持固定利率，新增字段记录浮动利率资产
     * @param intType 利率类型，存款类前台只支持固定利率，新增字段记录浮动利率资产
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * 取得 法审编号
     * @return 法审编号
     */
    public String getTrialNo() {
        return trialNo;
    }

    /**
     * 设置 法审编号
     * @param trialNo 法审编号
     */
    public void setTrialNo(String trialNo) {
        this.trialNo = trialNo == null ? null : trialNo.trim();
    }

    /**
     * 取得 业绩基准
     * @return 业绩基准
     */
    public String getBaseRule() {
        return baseRule;
    }

    /**
     * 设置 业绩基准
     * @param baseRule 业绩基准
     */
    public void setBaseRule(String baseRule) {
        this.baseRule = baseRule == null ? null : baseRule.trim();
    }

    /**
     * 取得 超额分成方式
     * @return 超额分成方式
     */
    public String getOverallocation() {
        return overallocation;
    }

    /**
     * 设置 超额分成方式
     * @param overallocation 超额分成方式
     */
    public void setOverallocation(String overallocation) {
        this.overallocation = overallocation == null ? null : overallocation.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 取得 增值税税率
     * @return 增值税税率
     */
    public BigDecimal getVatTaxRate() {
        return vatTaxRate;
    }

    /**
     * 设置 增值税税率
     * @param vatTaxRate 增值税税率
     */
    public void setVatTaxRate(BigDecimal vatTaxRate) {
        this.vatTaxRate = vatTaxRate;
    }

    /**
     * 取得 附加税税率
     * @return 附加税税率
     */
    public BigDecimal getAdtTaxRate() {
        return adtTaxRate;
    }

    /**
     * 设置 附加税税率
     * @param adtTaxRate 附加税税率
     */
    public void setAdtTaxRate(BigDecimal adtTaxRate) {
        this.adtTaxRate = adtTaxRate;
    }
}