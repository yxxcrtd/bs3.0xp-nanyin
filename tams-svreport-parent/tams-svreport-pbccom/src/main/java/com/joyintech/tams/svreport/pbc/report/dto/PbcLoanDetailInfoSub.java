package com.joyintech.tams.svreport.pbc.report.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 贷款明细基本信息报送表 实体类
 * PBC_LOAN_DETAIL_INFO_SUB
 *
 */
public class PbcLoanDetailInfoSub extends BaseEntity{
	
	//企业出资人经济成分
	private String enterpriseInvestorType;
	
	public String getEnterpriseInvestorType() {
		return enterpriseInvestorType;
	}
	public void setEnterpriseInvestorType(String enterpriseInvestorType) {
		this.enterpriseInvestorType = enterpriseInvestorType;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//贷款明细基本信息报送表 PBC_LOAN_DETAIL_INFO_SUB 
	//资产池代码
	private String pbcAccountId;
	//内部产品代码
	private String prodId;
	//资产代码
	private String assetCode;
	//资产名称
	private String assetName;
	//贷款种类
	private String loansType;
	//贷款转让方机构代码
	private String loanTransferorId;
	//贷款转让方机构名称
	private String loanTransferorName;
	//贷款合同原始发放机构代码
	private String loanContractOrgId;
	//贷款合同原始发放机构名称
	private String loanContractOrgName;
	//贷款合同原始发放机构所在地(县)
	private String loanContractOrgCounty;
	//借款人类型
	private String borrowerType;
	//地区代码
	private String borrowerCounty;
	//借款人代码
	private String borrowerId;
	//借款人国别
	private String borrowerNationality;
	//行业信息
	private String industryInfo;
	//企业出资人经济成分（一级）
	private String enterpriseInvestorTypeFirst;
	//企业出资人经济成分（二级）
	private String enterpriseInvestorTypeSec;
	//企业出资人经济成分（三级）
	private String enterpriseInvestorTypeThird;
	//企业规模
	private String enterpriseScale;
	//贷款借据编码
	private String loanBasisId;
	//贷款产品类别
	private String loanPrdType;
	//贷款实际投向
	private String loanActualInvest;
	//贷款发放日期
	private Date loanVdate;
	private String loanVdateStr;
	//贷款到期日期
	private Date loanMdate;
	private String loanMdateStr;
	//贷款展期到期日期
	private Date loanExtensionDueDate;
	private String loanExtensionDueDateStr;
	//利率是否固定
	private String rateFixed;
	//利率水平(%)
	private BigDecimal rateLevel;
	//贷款担保方式
	private String loanGuaranteeMode;
	//贷款质量
	private String loanQuality;
	//贷款状态
	private String loanState;
	//贷款转让折扣率(%)
	private BigDecimal loanTransferorRate;
	//原始合同币种代码
	private String origCcy;
	//原始合同金额
	private BigDecimal origContractAmount;
	//原始合同金额折人民币
	private BigDecimal origContractAmountCny;
	//贷款余额币种代码
	private String amtCcy;
	//贷款余额
	private BigDecimal contractAmount;
	//贷款余额折人民币
	private BigDecimal contractAmountCny;
	
	//资金信托类资产投资明细信息表 PBC_CAPITAL_TRUST_DETAIL_SUB
	//股权种类
	private String stockType;
	//产品发起机构编码
	private String capitalTrustOrgCode;
	//人行资金信托代码
	private String pobcassetid;
	//币种代码
	private String ccy;
	//资金信托产品余额
	private BigDecimal capitalTrustAmount;
	//资金信托产品余额折人民币
	private BigDecimal capitalTrustAmountCny;
	
	public String getPbcAccountId() {
		return pbcAccountId;
	}
	public void setPbcAccountId(String pbcAccountId) {
		this.pbcAccountId = pbcAccountId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getLoansType() {
		return loansType;
	}
	public void setLoansType(String loansType) {
		this.loansType = loansType;
	}
	public String getLoanTransferorId() {
		return loanTransferorId;
	}
	public void setLoanTransferorId(String loanTransferorId) {
		this.loanTransferorId = loanTransferorId;
	}
	public String getLoanTransferorName() {
		return loanTransferorName;
	}
	public void setLoanTransferorName(String loanTransferorName) {
		this.loanTransferorName = loanTransferorName;
	}
	public String getLoanContractOrgId() {
		return loanContractOrgId;
	}
	public void setLoanContractOrgId(String loanContractOrgId) {
		this.loanContractOrgId = loanContractOrgId;
	}
	public String getLoanContractOrgName() {
		return loanContractOrgName;
	}
	public void setLoanContractOrgName(String loanContractOrgName) {
		this.loanContractOrgName = loanContractOrgName;
	}
	public String getLoanContractOrgCounty() {
		return loanContractOrgCounty;
	}
	public void setLoanContractOrgCounty(String loanContractOrgCounty) {
		this.loanContractOrgCounty = loanContractOrgCounty;
	}
	public String getBorrowerType() {
		return borrowerType;
	}
	public void setBorrowerType(String borrowerType) {
		this.borrowerType = borrowerType;
	}
	public String getBorrowerCounty() {
		return borrowerCounty;
	}
	public void setBorrowerCounty(String borrowerCounty) {
		this.borrowerCounty = borrowerCounty;
	}
	public String getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	public String getBorrowerNationality() {
		return borrowerNationality;
	}
	public void setBorrowerNationality(String borrowerNationality) {
		this.borrowerNationality = borrowerNationality;
	}
	public String getIndustryInfo() {
		return industryInfo;
	}
	public void setIndustryInfo(String industryInfo) {
		this.industryInfo = industryInfo;
	}
	public String getEnterpriseInvestorTypeFirst() {
		return enterpriseInvestorTypeFirst;
	}
	public void setEnterpriseInvestorTypeFirst(String enterpriseInvestorTypeFirst) {
		this.enterpriseInvestorTypeFirst = enterpriseInvestorTypeFirst;
	}
	public String getEnterpriseInvestorTypeSec() {
		return enterpriseInvestorTypeSec;
	}
	public void setEnterpriseInvestorTypeSec(String enterpriseInvestorTypeSec) {
		this.enterpriseInvestorTypeSec = enterpriseInvestorTypeSec;
	}
	public String getEnterpriseInvestorTypeThird() {
		return enterpriseInvestorTypeThird;
	}
	public void setEnterpriseInvestorTypeThird(String enterpriseInvestorTypeThird) {
		this.enterpriseInvestorTypeThird = enterpriseInvestorTypeThird;
	}
	public String getEnterpriseScale() {
		return enterpriseScale;
	}
	public void setEnterpriseScale(String enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}
	public String getLoanBasisId() {
		return loanBasisId;
	}
	public void setLoanBasisId(String loanBasisId) {
		this.loanBasisId = loanBasisId;
	}
	public String getLoanPrdType() {
		return loanPrdType;
	}
	public void setLoanPrdType(String loanPrdType) {
		this.loanPrdType = loanPrdType;
	}
	public String getLoanActualInvest() {
		return loanActualInvest;
	}
	public void setLoanActualInvest(String loanActualInvest) {
		this.loanActualInvest = loanActualInvest;
	}
	public Date getLoanVdate() {
		return loanVdate;
	}
	public void setLoanVdate(Date loanVdate) {
		this.loanVdate = loanVdate;
	}
	public Date getLoanMdate() {
		return loanMdate;
	}
	public void setLoanMdate(Date loanMdate) {
		this.loanMdate = loanMdate;
	}
	public Date getLoanExtensionDueDate() {
		return loanExtensionDueDate;
	}
	public void setLoanExtensionDueDate(Date loanExtensionDueDate) {
		this.loanExtensionDueDate = loanExtensionDueDate;
	}
	public String getRateFixed() {
		return rateFixed;
	}
	public void setRateFixed(String rateFixed) {
		this.rateFixed = rateFixed;
	}
	public BigDecimal getRateLevel() {
		return rateLevel;
	}
	public void setRateLevel(BigDecimal rateLevel) {
		this.rateLevel = rateLevel;
	}
	public String getLoanGuaranteeMode() {
		return loanGuaranteeMode;
	}
	public void setLoanGuaranteeMode(String loanGuaranteeMode) {
		this.loanGuaranteeMode = loanGuaranteeMode;
	}
	public String getLoanQuality() {
		return loanQuality;
	}
	public void setLoanQuality(String loanQuality) {
		this.loanQuality = loanQuality;
	}
	public String getLoanState() {
		return loanState;
	}
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	public BigDecimal getLoanTransferorRate() {
		return loanTransferorRate;
	}
	public void setLoanTransferorRate(BigDecimal loanTransferorRate) {
		this.loanTransferorRate = loanTransferorRate;
	}
	public String getOrigCcy() {
		return origCcy;
	}
	public void setOrigCcy(String origCcy) {
		this.origCcy = origCcy;
	}
	public BigDecimal getOrigContractAmount() {
		return origContractAmount;
	}
	public void setOrigContractAmount(BigDecimal origContractAmount) {
		this.origContractAmount = origContractAmount;
	}
	public BigDecimal getOrigContractAmountCny() {
		return origContractAmountCny;
	}
	public void setOrigContractAmountCny(BigDecimal origContractAmountCny) {
		this.origContractAmountCny = origContractAmountCny;
	}
	public String getAmtCcy() {
		return amtCcy;
	}
	public void setAmtCcy(String amtCcy) {
		this.amtCcy = amtCcy;
	}
	public BigDecimal getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	public BigDecimal getContractAmountCny() {
		return contractAmountCny;
	}
	public void setContractAmountCny(BigDecimal contractAmountCny) {
		this.contractAmountCny = contractAmountCny;
	}
	public String getStockType() {
		return stockType;
	}
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	public String getCapitalTrustOrgCode() {
		return capitalTrustOrgCode;
	}
	public void setCapitalTrustOrgCode(String capitalTrustOrgCode) {
		this.capitalTrustOrgCode = capitalTrustOrgCode;
	}
	public String getPobcassetid() {
		return pobcassetid;
	}
	public void setPobcassetid(String pobcassetid) {
		this.pobcassetid = pobcassetid;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public BigDecimal getCapitalTrustAmount() {
		return capitalTrustAmount;
	}
	public void setCapitalTrustAmount(BigDecimal capitalTrustAmount) {
		this.capitalTrustAmount = capitalTrustAmount;
	}
	public BigDecimal getCapitalTrustAmountCny() {
		return capitalTrustAmountCny;
	}
	public void setCapitalTrustAmountCny(BigDecimal capitalTrustAmountCny) {
		this.capitalTrustAmountCny = capitalTrustAmountCny;
	}
	public String getLoanVdateStr() {
		return loanVdateStr;
	}
	public void setLoanVdateStr(String loanVdateStr) {
		this.loanVdateStr = loanVdateStr;
	}
	public String getLoanMdateStr() {
		return loanMdateStr;
	}
	public void setLoanMdateStr(String loanMdateStr) {
		this.loanMdateStr = loanMdateStr;
	}
	public String getLoanExtensionDueDateStr() {
		return loanExtensionDueDateStr;
	}
	public void setLoanExtensionDueDateStr(String loanExtensionDueDateStr) {
		this.loanExtensionDueDateStr = loanExtensionDueDateStr;
	}
	
}
