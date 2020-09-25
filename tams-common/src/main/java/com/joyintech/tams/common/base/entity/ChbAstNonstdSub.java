package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstNonstdSub extends ChbAstNonstdSubKey {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 二级资产类别 参照数据字典
     */
    private String assetType2;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 交易流通场所 参照数据字典
     */
    private String tradeMarket;

    /**
     * 名称
     */
    private String name;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 收/受益权类型 参照数据字典
     */
    private String profitType;

    /**
     * 是否属于买入返售 参照数据字典
     */
    private String isResale;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 是否有预期收益率 参照数据字典
     */
    private String rateFlag;

    /**
     * 项目收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal projYield;

    /**
     * 付息频率(个月/次)
     */
    private Short intpayCycle;

    /**
     * 还本付息情况说明
     */
    private String repayCmt;

    /**
     * 计息基础 参照数据字典
     */
    private String basis;

    /**
     * 基准利率种类 参照数据字典
     */
    private String basisType;

    /**
     * 浮动因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal floatFactor;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 融资人
     */
    private String financier;

    /**
     * 融资人内部信用评级
     */
    private String fcrGradeIn;

    /**
     * 外部评级机构及对融资人评级
     */
    private String fcrGradeOut;

    /**
     * 融资人类型（按规模划分） 参照数据字典
     */
    private String financierType1;

    /**
     * 融资人类型（按经济类型划分） 参照数据字典
     */
    private String financierType2;

    /**
     * 融资人类型（按技术领域划分）参照数据字典
     */
    private String financierType3;

    /**
     * 融资项目
     */
    private String financeProj;

    /**
     * 融资人所属行业 参照数据字典
     */
    private String industry1;

    /**
     * 融资人所属行业二级分类 参照数据字典
     */
    private String industryLevel2;

    /**
     * 融资项目所属行业 参照数据字典
     */
    private String proIndustry;

    /**
     * 融资项目所属行业二级分类 参照数据字典
     */
    private String proIndustryLevel2;

    /**
     * 是否属于重点监控行业和领域 参照数据字典
     */
    private String industryFlag;

    /**
     * 重点监控行业和领域类别 参照数据字典
     */
    private String monitType;

    /**
     * 重点监控行业和领域类别
     */
    private String monTypeComm;

    /**
     * 担保方式
     */
    private String guaType;

    /**
     * 担保方式说明
     */
    private String guaTypeRemark;

    /**
     * 融资人主体评级 参照数据字典
     */
    private String financierGrade;

    /**
     * 资产内部评级
     */
    private String inGrade;

    /**
     * 资产外部评级 参照数据字典
     */
    private String outGrade;

    /**
     * 选择权
     */
    private String chose;

    /**
     * 行权条件说明：默认“无”
     */
    private String conditionCmm;

    /**
     * 融资人所属地区 参照数据字典
     */
    private String finArea;

    /**
     * 融资总费率(%),为方便导出，直接取百分位上的数字,不需要除100  CHB3
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalFeeRate;

    /**
     * 融资人组织机构代码 CHB3
     */
    private String finOrgCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 二级资产类别 参照数据字典
     * @return 二级资产类别 参照数据字典
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 二级资产类别 参照数据字典
     * @param assetType2 二级资产类别 参照数据字典
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易流通场所 参照数据字典
     * @return 交易流通场所 参照数据字典
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易流通场所 参照数据字典
     * @param tradeMarket 交易流通场所 参照数据字典
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 名称
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 金额
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 取得 收/受益权类型 参照数据字典
     * @return 收/受益权类型 参照数据字典
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收/受益权类型 参照数据字典
     * @param profitType 收/受益权类型 参照数据字典
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 是否属于买入返售 参照数据字典
     * @return 是否属于买入返售 参照数据字典
     */
    public String getIsResale() {
        return isResale;
    }

    /**
     * 设置 是否属于买入返售 参照数据字典
     * @param isResale 是否属于买入返售 参照数据字典
     */
    public void setIsResale(String isResale) {
        this.isResale = isResale == null ? null : isResale.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 是否有预期收益率 参照数据字典
     * @return 是否有预期收益率 参照数据字典
     */
    public String getRateFlag() {
        return rateFlag;
    }

    /**
     * 设置 是否有预期收益率 参照数据字典
     * @param rateFlag 是否有预期收益率 参照数据字典
     */
    public void setRateFlag(String rateFlag) {
        this.rateFlag = rateFlag == null ? null : rateFlag.trim();
    }

    /**
     * 取得 项目收益率
     * @return 项目收益率
     */
    public BigDecimal getProjYield() {
        return projYield;
    }

    /**
     * 设置 项目收益率
     * @param projYield 项目收益率
     */
    public void setProjYield(BigDecimal projYield) {
        this.projYield = projYield;
    }

    /**
     * 取得 付息频率(个月/次)
     * @return 付息频率(个月/次)
     */
    public Short getIntpayCycle() {
        return intpayCycle;
    }

    /**
     * 设置 付息频率(个月/次)
     * @param intpayCycle 付息频率(个月/次)
     */
    public void setIntpayCycle(Short intpayCycle) {
        this.intpayCycle = intpayCycle;
    }

    /**
     * 取得 还本付息情况说明
     * @return 还本付息情况说明
     */
    public String getRepayCmt() {
        return repayCmt;
    }

    /**
     * 设置 还本付息情况说明
     * @param repayCmt 还本付息情况说明
     */
    public void setRepayCmt(String repayCmt) {
        this.repayCmt = repayCmt == null ? null : repayCmt.trim();
    }

    /**
     * 取得 计息基础 参照数据字典
     * @return 计息基础 参照数据字典
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础 参照数据字典
     * @param basis 计息基础 参照数据字典
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 基准利率种类 参照数据字典
     * @return 基准利率种类 参照数据字典
     */
    public String getBasisType() {
        return basisType;
    }

    /**
     * 设置 基准利率种类 参照数据字典
     * @param basisType 基准利率种类 参照数据字典
     */
    public void setBasisType(String basisType) {
        this.basisType = basisType == null ? null : basisType.trim();
    }

    /**
     * 取得 浮动因子
     * @return 浮动因子
     */
    public BigDecimal getFloatFactor() {
        return floatFactor;
    }

    /**
     * 设置 浮动因子
     * @param floatFactor 浮动因子
     */
    public void setFloatFactor(BigDecimal floatFactor) {
        this.floatFactor = floatFactor;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 融资人
     * @return 融资人
     */
    public String getFinancier() {
        return financier;
    }

    /**
     * 设置 融资人
     * @param financier 融资人
     */
    public void setFinancier(String financier) {
        this.financier = financier == null ? null : financier.trim();
    }

    /**
     * 取得 融资人内部信用评级
     * @return 融资人内部信用评级
     */
    public String getFcrGradeIn() {
        return fcrGradeIn;
    }

    /**
     * 设置 融资人内部信用评级
     * @param fcrGradeIn 融资人内部信用评级
     */
    public void setFcrGradeIn(String fcrGradeIn) {
        this.fcrGradeIn = fcrGradeIn == null ? null : fcrGradeIn.trim();
    }

    /**
     * 取得 外部评级机构及对融资人评级
     * @return 外部评级机构及对融资人评级
     */
    public String getFcrGradeOut() {
        return fcrGradeOut;
    }

    /**
     * 设置 外部评级机构及对融资人评级
     * @param fcrGradeOut 外部评级机构及对融资人评级
     */
    public void setFcrGradeOut(String fcrGradeOut) {
        this.fcrGradeOut = fcrGradeOut == null ? null : fcrGradeOut.trim();
    }

    /**
     * 取得 融资人类型（按规模划分） 参照数据字典
     * @return 融资人类型（按规模划分） 参照数据字典
     */
    public String getFinancierType1() {
        return financierType1;
    }

    /**
     * 设置 融资人类型（按规模划分） 参照数据字典
     * @param financierType1 融资人类型（按规模划分） 参照数据字典
     */
    public void setFinancierType1(String financierType1) {
        this.financierType1 = financierType1 == null ? null : financierType1.trim();
    }

    /**
     * 取得 融资人类型（按经济类型划分） 参照数据字典
     * @return 融资人类型（按经济类型划分） 参照数据字典
     */
    public String getFinancierType2() {
        return financierType2;
    }

    /**
     * 设置 融资人类型（按经济类型划分） 参照数据字典
     * @param financierType2 融资人类型（按经济类型划分） 参照数据字典
     */
    public void setFinancierType2(String financierType2) {
        this.financierType2 = financierType2 == null ? null : financierType2.trim();
    }

    /**
     * 取得 融资人类型（按技术领域划分）参照数据字典
     * @return 融资人类型（按技术领域划分）参照数据字典
     */
    public String getFinancierType3() {
        return financierType3;
    }

    /**
     * 设置 融资人类型（按技术领域划分）参照数据字典
     * @param financierType3 融资人类型（按技术领域划分）参照数据字典
     */
    public void setFinancierType3(String financierType3) {
        this.financierType3 = financierType3 == null ? null : financierType3.trim();
    }

    /**
     * 取得 融资项目
     * @return 融资项目
     */
    public String getFinanceProj() {
        return financeProj;
    }

    /**
     * 设置 融资项目
     * @param financeProj 融资项目
     */
    public void setFinanceProj(String financeProj) {
        this.financeProj = financeProj == null ? null : financeProj.trim();
    }

    /**
     * 取得 融资人所属行业 参照数据字典
     * @return 融资人所属行业 参照数据字典
     */
    public String getIndustry1() {
        return industry1;
    }

    /**
     * 设置 融资人所属行业 参照数据字典
     * @param industry1 融资人所属行业 参照数据字典
     */
    public void setIndustry1(String industry1) {
        this.industry1 = industry1 == null ? null : industry1.trim();
    }

    /**
     * 取得 融资人所属行业二级分类 参照数据字典
     * @return 融资人所属行业二级分类 参照数据字典
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 设置 融资人所属行业二级分类 参照数据字典
     * @param industryLevel2 融资人所属行业二级分类 参照数据字典
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
    }

    /**
     * 取得 融资项目所属行业 参照数据字典
     * @return 融资项目所属行业 参照数据字典
     */
    public String getProIndustry() {
        return proIndustry;
    }

    /**
     * 设置 融资项目所属行业 参照数据字典
     * @param proIndustry 融资项目所属行业 参照数据字典
     */
    public void setProIndustry(String proIndustry) {
        this.proIndustry = proIndustry == null ? null : proIndustry.trim();
    }

    /**
     * 取得 融资项目所属行业二级分类 参照数据字典
     * @return 融资项目所属行业二级分类 参照数据字典
     */
    public String getProIndustryLevel2() {
        return proIndustryLevel2;
    }

    /**
     * 设置 融资项目所属行业二级分类 参照数据字典
     * @param proIndustryLevel2 融资项目所属行业二级分类 参照数据字典
     */
    public void setProIndustryLevel2(String proIndustryLevel2) {
        this.proIndustryLevel2 = proIndustryLevel2 == null ? null : proIndustryLevel2.trim();
    }

    /**
     * 取得 是否属于重点监控行业和领域 参照数据字典
     * @return 是否属于重点监控行业和领域 参照数据字典
     */
    public String getIndustryFlag() {
        return industryFlag;
    }

    /**
     * 设置 是否属于重点监控行业和领域 参照数据字典
     * @param industryFlag 是否属于重点监控行业和领域 参照数据字典
     */
    public void setIndustryFlag(String industryFlag) {
        this.industryFlag = industryFlag == null ? null : industryFlag.trim();
    }

    /**
     * 取得 重点监控行业和领域类别 参照数据字典
     * @return 重点监控行业和领域类别 参照数据字典
     */
    public String getMonitType() {
        return monitType;
    }

    /**
     * 设置 重点监控行业和领域类别 参照数据字典
     * @param monitType 重点监控行业和领域类别 参照数据字典
     */
    public void setMonitType(String monitType) {
        this.monitType = monitType == null ? null : monitType.trim();
    }

    /**
     * 取得 重点监控行业和领域类别
     * @return 重点监控行业和领域类别
     */
    public String getMonTypeComm() {
        return monTypeComm;
    }

    /**
     * 设置 重点监控行业和领域类别
     * @param monTypeComm 重点监控行业和领域类别
     */
    public void setMonTypeComm(String monTypeComm) {
        this.monTypeComm = monTypeComm == null ? null : monTypeComm.trim();
    }

    /**
     * 取得 担保方式
     * @return 担保方式
     */
    public String getGuaType() {
        return guaType;
    }

    /**
     * 设置 担保方式
     * @param guaType 担保方式
     */
    public void setGuaType(String guaType) {
        this.guaType = guaType == null ? null : guaType.trim();
    }

    /**
     * 取得 担保方式说明
     * @return 担保方式说明
     */
    public String getGuaTypeRemark() {
        return guaTypeRemark;
    }

    /**
     * 设置 担保方式说明
     * @param guaTypeRemark 担保方式说明
     */
    public void setGuaTypeRemark(String guaTypeRemark) {
        this.guaTypeRemark = guaTypeRemark == null ? null : guaTypeRemark.trim();
    }

    /**
     * 取得 融资人主体评级 参照数据字典
     * @return 融资人主体评级 参照数据字典
     */
    public String getFinancierGrade() {
        return financierGrade;
    }

    /**
     * 设置 融资人主体评级 参照数据字典
     * @param financierGrade 融资人主体评级 参照数据字典
     */
    public void setFinancierGrade(String financierGrade) {
        this.financierGrade = financierGrade == null ? null : financierGrade.trim();
    }

    /**
     * 取得 资产内部评级
     * @return 资产内部评级
     */
    public String getInGrade() {
        return inGrade;
    }

    /**
     * 设置 资产内部评级
     * @param inGrade 资产内部评级
     */
    public void setInGrade(String inGrade) {
        this.inGrade = inGrade == null ? null : inGrade.trim();
    }

    /**
     * 取得 资产外部评级 参照数据字典
     * @return 资产外部评级 参照数据字典
     */
    public String getOutGrade() {
        return outGrade;
    }

    /**
     * 设置 资产外部评级 参照数据字典
     * @param outGrade 资产外部评级 参照数据字典
     */
    public void setOutGrade(String outGrade) {
        this.outGrade = outGrade == null ? null : outGrade.trim();
    }

    /**
     * 取得 选择权
     * @return 选择权
     */
    public String getChose() {
        return chose;
    }

    /**
     * 设置 选择权
     * @param chose 选择权
     */
    public void setChose(String chose) {
        this.chose = chose == null ? null : chose.trim();
    }

    /**
     * 取得 行权条件说明：默认“无”
     * @return 行权条件说明：默认“无”
     */
    public String getConditionCmm() {
        return conditionCmm;
    }

    /**
     * 设置 行权条件说明：默认“无”
     * @param conditionCmm 行权条件说明：默认“无”
     */
    public void setConditionCmm(String conditionCmm) {
        this.conditionCmm = conditionCmm == null ? null : conditionCmm.trim();
    }

    /**
     * 取得 融资人所属地区 参照数据字典
     * @return 融资人所属地区 参照数据字典
     */
    public String getFinArea() {
        return finArea;
    }

    /**
     * 设置 融资人所属地区 参照数据字典
     * @param finArea 融资人所属地区 参照数据字典
     */
    public void setFinArea(String finArea) {
        this.finArea = finArea == null ? null : finArea.trim();
    }

    /**
     * 取得 融资总费率(%),为方便导出，直接取百分位上的数字,不需要除100  CHB3
     * @return 融资总费率(%),为方便导出，直接取百分位上的数字,不需要除100  CHB3
     */
    public BigDecimal getTotalFeeRate() {
        return totalFeeRate;
    }

    /**
     * 设置 融资总费率(%),为方便导出，直接取百分位上的数字,不需要除100  CHB3
     * @param totalFeeRate 融资总费率(%),为方便导出，直接取百分位上的数字,不需要除100  CHB3
     */
    public void setTotalFeeRate(BigDecimal totalFeeRate) {
        this.totalFeeRate = totalFeeRate;
    }

    /**
     * 取得 融资人组织机构代码 CHB3
     * @return 融资人组织机构代码 CHB3
     */
    public String getFinOrgCode() {
        return finOrgCode;
    }

    /**
     * 设置 融资人组织机构代码 CHB3
     * @param finOrgCode 融资人组织机构代码 CHB3
     */
    public void setFinOrgCode(String finOrgCode) {
        this.finOrgCode = finOrgCode == null ? null : finOrgCode.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}