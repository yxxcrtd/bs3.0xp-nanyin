package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbLoanDetailInfo extends BaseEntity {
    /**
     * 主键
     */
    private String assetUuid;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 基础资产投向分类
     */
    private String assetsInvest;

    /**
     * 各比例
     */
    private String eachProportion;

    /**
     * 贷款种类
     */
    private String loansType;

    /**
     * 原始合同币种代码
     */
    private String origCcy;

    /**
     * 原始合同金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal origContractAmount;

    /**
     * 原始合同金额折人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal origContractAmountCny;

    /**
     * 借款人类型
     */
    private String borrowerType;

    /**
     * 借款人代码
     */
    private String borrowerId;

    /**
     * 行业信息
     */
    private String industryInfo;

    /**
     * 借款人国别
     */
    private String borrowerNationality;

    /**
     * 借款人地区(省)
     */
    private String borrowerProvince;

    /**
     * 借款人地区(市)
     */
    private String borrowerCity;

    /**
     * 借款人地区(县)
     */
    private String borrowerCounty;

    /**
     * 贷款转让方机构代码
     */
    private String loanTransferorId;

    /**
     * 贷款转让方机构名称
     */
    private String loanTransferorName;

    /**
     * 贷款合同原始发放机构代码
     */
    private String loanContractOrgId;

    /**
     * 贷款合同原始发放机构名称
     */
    private String loanContractOrgName;

    /**
     * 贷款合同原始发放机构所在地(省)
     */
    private String loanContractOrgProvince;

    /**
     * 贷款合同原始发放机构所在地(市)
     */
    private String loanContractOrgCity;

    /**
     * 贷款合同原始发放机构所在地(县)
     */
    private String loanContractOrgCounty;

    /**
     * 企业出资人经济成分（一级）
     */
    private String enterpriseInvestorTypeFirst;

    /**
     * 企业出资人经济成分（二级）
     */
    private String enterpriseInvestorTypeSec;

    /**
     * 企业出资人经济成分（三级）
     */
    private String enterpriseInvestorTypeThird;

    /**
     * 企业规模
     */
    private String enterpriseScale;

    /**
     * 贷款借据编码
     */
    private String loanBasisId;

    /**
     * 贷款产品类别
     */
    private String loanPrdType;

    /**
     * 贷款实际投向
     */
    private String loanActualInvest;

    /**
     * 贷款发放日期
     */
    private Date loanVdate;

    /**
     * 贷款到期日期
     */
    private Date loanMdate;

    /**
     * 贷款展期到期日期
     */
    private Date loanExtensionDueDate;

    /**
     * 利率是否固定
     */
    private String rateFixed;

    /**
     * 利率水平(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateLevel;

    /**
     * 贷款担保方式
     */
    private String loanGuaranteeMode;

    /**
     * 贷款质量
     */
    private String loanQuality;

    /**
     * 贷款状态
     */
    private String loanState;

    /**
     * 贷款转让折扣率(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal loanTransferorRate;

    /**
     * 部门代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getAssetUuid() {
        return assetUuid;
    }

    /**
     * 设置 主键
     * @param assetUuid 主键
     */
    public void setAssetUuid(String assetUuid) {
        this.assetUuid = assetUuid == null ? null : assetUuid.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 基础资产投向分类
     * @return 基础资产投向分类
     */
    public String getAssetsInvest() {
        return assetsInvest;
    }

    /**
     * 设置 基础资产投向分类
     * @param assetsInvest 基础资产投向分类
     */
    public void setAssetsInvest(String assetsInvest) {
        this.assetsInvest = assetsInvest == null ? null : assetsInvest.trim();
    }

    /**
     * 取得 各比例
     * @return 各比例
     */
    public String getEachProportion() {
        return eachProportion;
    }

    /**
     * 设置 各比例
     * @param eachProportion 各比例
     */
    public void setEachProportion(String eachProportion) {
        this.eachProportion = eachProportion == null ? null : eachProportion.trim();
    }

    /**
     * 取得 贷款种类
     * @return 贷款种类
     */
    public String getLoansType() {
        return loansType;
    }

    /**
     * 设置 贷款种类
     * @param loansType 贷款种类
     */
    public void setLoansType(String loansType) {
        this.loansType = loansType == null ? null : loansType.trim();
    }

    /**
     * 取得 原始合同币种代码
     * @return 原始合同币种代码
     */
    public String getOrigCcy() {
        return origCcy;
    }

    /**
     * 设置 原始合同币种代码
     * @param origCcy 原始合同币种代码
     */
    public void setOrigCcy(String origCcy) {
        this.origCcy = origCcy == null ? null : origCcy.trim();
    }

    /**
     * 取得 原始合同金额
     * @return 原始合同金额
     */
    public BigDecimal getOrigContractAmount() {
        return origContractAmount;
    }

    /**
     * 设置 原始合同金额
     * @param origContractAmount 原始合同金额
     */
    public void setOrigContractAmount(BigDecimal origContractAmount) {
        this.origContractAmount = origContractAmount;
    }

    /**
     * 取得 原始合同金额折人民币
     * @return 原始合同金额折人民币
     */
    public BigDecimal getOrigContractAmountCny() {
        return origContractAmountCny;
    }

    /**
     * 设置 原始合同金额折人民币
     * @param origContractAmountCny 原始合同金额折人民币
     */
    public void setOrigContractAmountCny(BigDecimal origContractAmountCny) {
        this.origContractAmountCny = origContractAmountCny;
    }

    /**
     * 取得 借款人类型
     * @return 借款人类型
     */
    public String getBorrowerType() {
        return borrowerType;
    }

    /**
     * 设置 借款人类型
     * @param borrowerType 借款人类型
     */
    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType == null ? null : borrowerType.trim();
    }

    /**
     * 取得 借款人代码
     * @return 借款人代码
     */
    public String getBorrowerId() {
        return borrowerId;
    }

    /**
     * 设置 借款人代码
     * @param borrowerId 借款人代码
     */
    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId == null ? null : borrowerId.trim();
    }

    /**
     * 取得 行业信息
     * @return 行业信息
     */
    public String getIndustryInfo() {
        return industryInfo;
    }

    /**
     * 设置 行业信息
     * @param industryInfo 行业信息
     */
    public void setIndustryInfo(String industryInfo) {
        this.industryInfo = industryInfo == null ? null : industryInfo.trim();
    }

    /**
     * 取得 借款人国别
     * @return 借款人国别
     */
    public String getBorrowerNationality() {
        return borrowerNationality;
    }

    /**
     * 设置 借款人国别
     * @param borrowerNationality 借款人国别
     */
    public void setBorrowerNationality(String borrowerNationality) {
        this.borrowerNationality = borrowerNationality == null ? null : borrowerNationality.trim();
    }

    /**
     * 取得 借款人地区(省)
     * @return 借款人地区(省)
     */
    public String getBorrowerProvince() {
        return borrowerProvince;
    }

    /**
     * 设置 借款人地区(省)
     * @param borrowerProvince 借款人地区(省)
     */
    public void setBorrowerProvince(String borrowerProvince) {
        this.borrowerProvince = borrowerProvince == null ? null : borrowerProvince.trim();
    }

    /**
     * 取得 借款人地区(市)
     * @return 借款人地区(市)
     */
    public String getBorrowerCity() {
        return borrowerCity;
    }

    /**
     * 设置 借款人地区(市)
     * @param borrowerCity 借款人地区(市)
     */
    public void setBorrowerCity(String borrowerCity) {
        this.borrowerCity = borrowerCity == null ? null : borrowerCity.trim();
    }

    /**
     * 取得 借款人地区(县)
     * @return 借款人地区(县)
     */
    public String getBorrowerCounty() {
        return borrowerCounty;
    }

    /**
     * 设置 借款人地区(县)
     * @param borrowerCounty 借款人地区(县)
     */
    public void setBorrowerCounty(String borrowerCounty) {
        this.borrowerCounty = borrowerCounty == null ? null : borrowerCounty.trim();
    }

    /**
     * 取得 贷款转让方机构代码
     * @return 贷款转让方机构代码
     */
    public String getLoanTransferorId() {
        return loanTransferorId;
    }

    /**
     * 设置 贷款转让方机构代码
     * @param loanTransferorId 贷款转让方机构代码
     */
    public void setLoanTransferorId(String loanTransferorId) {
        this.loanTransferorId = loanTransferorId == null ? null : loanTransferorId.trim();
    }

    /**
     * 取得 贷款转让方机构名称
     * @return 贷款转让方机构名称
     */
    public String getLoanTransferorName() {
        return loanTransferorName;
    }

    /**
     * 设置 贷款转让方机构名称
     * @param loanTransferorName 贷款转让方机构名称
     */
    public void setLoanTransferorName(String loanTransferorName) {
        this.loanTransferorName = loanTransferorName == null ? null : loanTransferorName.trim();
    }

    /**
     * 取得 贷款合同原始发放机构代码
     * @return 贷款合同原始发放机构代码
     */
    public String getLoanContractOrgId() {
        return loanContractOrgId;
    }

    /**
     * 设置 贷款合同原始发放机构代码
     * @param loanContractOrgId 贷款合同原始发放机构代码
     */
    public void setLoanContractOrgId(String loanContractOrgId) {
        this.loanContractOrgId = loanContractOrgId == null ? null : loanContractOrgId.trim();
    }

    /**
     * 取得 贷款合同原始发放机构名称
     * @return 贷款合同原始发放机构名称
     */
    public String getLoanContractOrgName() {
        return loanContractOrgName;
    }

    /**
     * 设置 贷款合同原始发放机构名称
     * @param loanContractOrgName 贷款合同原始发放机构名称
     */
    public void setLoanContractOrgName(String loanContractOrgName) {
        this.loanContractOrgName = loanContractOrgName == null ? null : loanContractOrgName.trim();
    }

    /**
     * 取得 贷款合同原始发放机构所在地(省)
     * @return 贷款合同原始发放机构所在地(省)
     */
    public String getLoanContractOrgProvince() {
        return loanContractOrgProvince;
    }

    /**
     * 设置 贷款合同原始发放机构所在地(省)
     * @param loanContractOrgProvince 贷款合同原始发放机构所在地(省)
     */
    public void setLoanContractOrgProvince(String loanContractOrgProvince) {
        this.loanContractOrgProvince = loanContractOrgProvince == null ? null : loanContractOrgProvince.trim();
    }

    /**
     * 取得 贷款合同原始发放机构所在地(市)
     * @return 贷款合同原始发放机构所在地(市)
     */
    public String getLoanContractOrgCity() {
        return loanContractOrgCity;
    }

    /**
     * 设置 贷款合同原始发放机构所在地(市)
     * @param loanContractOrgCity 贷款合同原始发放机构所在地(市)
     */
    public void setLoanContractOrgCity(String loanContractOrgCity) {
        this.loanContractOrgCity = loanContractOrgCity == null ? null : loanContractOrgCity.trim();
    }

    /**
     * 取得 贷款合同原始发放机构所在地(县)
     * @return 贷款合同原始发放机构所在地(县)
     */
    public String getLoanContractOrgCounty() {
        return loanContractOrgCounty;
    }

    /**
     * 设置 贷款合同原始发放机构所在地(县)
     * @param loanContractOrgCounty 贷款合同原始发放机构所在地(县)
     */
    public void setLoanContractOrgCounty(String loanContractOrgCounty) {
        this.loanContractOrgCounty = loanContractOrgCounty == null ? null : loanContractOrgCounty.trim();
    }

    /**
     * 取得 企业出资人经济成分（一级）
     * @return 企业出资人经济成分（一级）
     */
    public String getEnterpriseInvestorTypeFirst() {
        return enterpriseInvestorTypeFirst;
    }

    /**
     * 设置 企业出资人经济成分（一级）
     * @param enterpriseInvestorTypeFirst 企业出资人经济成分（一级）
     */
    public void setEnterpriseInvestorTypeFirst(String enterpriseInvestorTypeFirst) {
        this.enterpriseInvestorTypeFirst = enterpriseInvestorTypeFirst == null ? null : enterpriseInvestorTypeFirst.trim();
    }

    /**
     * 取得 企业出资人经济成分（二级）
     * @return 企业出资人经济成分（二级）
     */
    public String getEnterpriseInvestorTypeSec() {
        return enterpriseInvestorTypeSec;
    }

    /**
     * 设置 企业出资人经济成分（二级）
     * @param enterpriseInvestorTypeSec 企业出资人经济成分（二级）
     */
    public void setEnterpriseInvestorTypeSec(String enterpriseInvestorTypeSec) {
        this.enterpriseInvestorTypeSec = enterpriseInvestorTypeSec == null ? null : enterpriseInvestorTypeSec.trim();
    }

    /**
     * 取得 企业出资人经济成分（三级）
     * @return 企业出资人经济成分（三级）
     */
    public String getEnterpriseInvestorTypeThird() {
        return enterpriseInvestorTypeThird;
    }

    /**
     * 设置 企业出资人经济成分（三级）
     * @param enterpriseInvestorTypeThird 企业出资人经济成分（三级）
     */
    public void setEnterpriseInvestorTypeThird(String enterpriseInvestorTypeThird) {
        this.enterpriseInvestorTypeThird = enterpriseInvestorTypeThird == null ? null : enterpriseInvestorTypeThird.trim();
    }

    /**
     * 取得 企业规模
     * @return 企业规模
     */
    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    /**
     * 设置 企业规模
     * @param enterpriseScale 企业规模
     */
    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale == null ? null : enterpriseScale.trim();
    }

    /**
     * 取得 贷款借据编码
     * @return 贷款借据编码
     */
    public String getLoanBasisId() {
        return loanBasisId;
    }

    /**
     * 设置 贷款借据编码
     * @param loanBasisId 贷款借据编码
     */
    public void setLoanBasisId(String loanBasisId) {
        this.loanBasisId = loanBasisId == null ? null : loanBasisId.trim();
    }

    /**
     * 取得 贷款产品类别
     * @return 贷款产品类别
     */
    public String getLoanPrdType() {
        return loanPrdType;
    }

    /**
     * 设置 贷款产品类别
     * @param loanPrdType 贷款产品类别
     */
    public void setLoanPrdType(String loanPrdType) {
        this.loanPrdType = loanPrdType == null ? null : loanPrdType.trim();
    }

    /**
     * 取得 贷款实际投向
     * @return 贷款实际投向
     */
    public String getLoanActualInvest() {
        return loanActualInvest;
    }

    /**
     * 设置 贷款实际投向
     * @param loanActualInvest 贷款实际投向
     */
    public void setLoanActualInvest(String loanActualInvest) {
        this.loanActualInvest = loanActualInvest == null ? null : loanActualInvest.trim();
    }

    /**
     * 取得 贷款发放日期
     * @return 贷款发放日期
     */
    public Date getLoanVdate() {
        return loanVdate;
    }

    /**
     * 设置 贷款发放日期
     * @param loanVdate 贷款发放日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLoanVdate(Date loanVdate) {
        this.loanVdate = loanVdate;
    }

    /**
     * 取得 贷款到期日期
     * @return 贷款到期日期
     */
    public Date getLoanMdate() {
        return loanMdate;
    }

    /**
     * 设置 贷款到期日期
     * @param loanMdate 贷款到期日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLoanMdate(Date loanMdate) {
        this.loanMdate = loanMdate;
    }

    /**
     * 取得 贷款展期到期日期
     * @return 贷款展期到期日期
     */
    public Date getLoanExtensionDueDate() {
        return loanExtensionDueDate;
    }

    /**
     * 设置 贷款展期到期日期
     * @param loanExtensionDueDate 贷款展期到期日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLoanExtensionDueDate(Date loanExtensionDueDate) {
        this.loanExtensionDueDate = loanExtensionDueDate;
    }

    /**
     * 取得 利率是否固定
     * @return 利率是否固定
     */
    public String getRateFixed() {
        return rateFixed;
    }

    /**
     * 设置 利率是否固定
     * @param rateFixed 利率是否固定
     */
    public void setRateFixed(String rateFixed) {
        this.rateFixed = rateFixed == null ? null : rateFixed.trim();
    }

    /**
     * 取得 利率水平(%)
     * @return 利率水平(%)
     */
    public BigDecimal getRateLevel() {
        return rateLevel;
    }

    /**
     * 设置 利率水平(%)
     * @param rateLevel 利率水平(%)
     */
    public void setRateLevel(BigDecimal rateLevel) {
        this.rateLevel = rateLevel;
    }

    /**
     * 取得 贷款担保方式
     * @return 贷款担保方式
     */
    public String getLoanGuaranteeMode() {
        return loanGuaranteeMode;
    }

    /**
     * 设置 贷款担保方式
     * @param loanGuaranteeMode 贷款担保方式
     */
    public void setLoanGuaranteeMode(String loanGuaranteeMode) {
        this.loanGuaranteeMode = loanGuaranteeMode == null ? null : loanGuaranteeMode.trim();
    }

    /**
     * 取得 贷款质量
     * @return 贷款质量
     */
    public String getLoanQuality() {
        return loanQuality;
    }

    /**
     * 设置 贷款质量
     * @param loanQuality 贷款质量
     */
    public void setLoanQuality(String loanQuality) {
        this.loanQuality = loanQuality == null ? null : loanQuality.trim();
    }

    /**
     * 取得 贷款状态
     * @return 贷款状态
     */
    public String getLoanState() {
        return loanState;
    }

    /**
     * 设置 贷款状态
     * @param loanState 贷款状态
     */
    public void setLoanState(String loanState) {
        this.loanState = loanState == null ? null : loanState.trim();
    }

    /**
     * 取得 贷款转让折扣率(%)
     * @return 贷款转让折扣率(%)
     */
    public BigDecimal getLoanTransferorRate() {
        return loanTransferorRate;
    }

    /**
     * 设置 贷款转让折扣率(%)
     * @param loanTransferorRate 贷款转让折扣率(%)
     */
    public void setLoanTransferorRate(BigDecimal loanTransferorRate) {
        this.loanTransferorRate = loanTransferorRate;
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 部门代码
     * @param orgCode 部门代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
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

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
}