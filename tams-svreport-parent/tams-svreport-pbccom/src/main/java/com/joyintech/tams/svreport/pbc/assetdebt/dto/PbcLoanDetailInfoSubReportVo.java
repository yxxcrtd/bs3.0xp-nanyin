package com.joyintech.tams.svreport.pbc.assetdebt.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产负债信息贷款以及资金信托份额导出Vo
 *
 * @author kx
 */
public class PbcLoanDetailInfoSubReportVo extends BaseEntity {

    /**
     * 行业要素
     */
    private String industryFactorName;

    /**
     * 地区代码
     */
    private String areaCodeName;

    /**
     * 借款人类型
     */
    private String typeOfBorrowerName;

    /**
     * 借款人代码
     */
    private String borrowerCode;

    /**
     * 原始合同币种
     */
    private String originalCcyName;

    /**
     * 原始合同金额
     */
    private BigDecimal originalContractAmount;

    /**
     * 原始合同转人民币
     */
    private BigDecimal originalContractAmountRmb;

    /**
     * 贷款币种
     */
    private String loanCcy;

    /**
     * 贷款余额
     */
    private BigDecimal loanBalance;

    /**
     * 贷款余额转人民币
     */
    private BigDecimal loanBalanceRmb;

    /**
     * 转让方机构
     */
    private String transferorAgency;

    /**
     * 原始发起机构
     */
    private String originalIssuer;


    /**
     * 原始发起机构所在地
     */
    private String originalIssuerLocation;


    /**
     * 企业出资人经济成分
     */
    private String economicComponent;


    /**
     * 企业规模
     */
    private String enterpriseScale;


    /**
     * 贷款借据编码
     */
    private String loanIouCode;

    /**
     * 贷款产品类别
     */
    private String loanProductCategory;

    /**
     * 贷款实际投向
     */
    private String actualInvestmentLoans;


    /**
     * 贷款发放日期
     */
    private Date dateLoanIssuance;
    
    
    private String dateLoanIssuanceStr;
    

    /**
     * 贷款到期日期
     */
    private Date loanMaturityDate;
    
    private String loanMaturityDateStr;

    /**
     * 贷款展期到期日期
     */
    private Date maturityDateLoanRollover;
    
    private String maturityDateLoanRolloverStr;

    /**
     * 利率是否固定
     */
    private String isTheInterestRate;

    /**
     * 利率水平
     */
    private BigDecimal interestRates;

    /**
     * 贷款担保方式
     */
    private String loanGuaranteeMethod;

    /**
     * 贷款质量
     */
    private String paymentForGoodsQuality;

    /**
     * 贷款状态
     */
    private String paymentStatus;

    /**
     * 贷款转让折扣率
     */
    private BigDecimal transferDiscountRate;

    /**
     * 投向产品代码
     */
    private String inputProductCode;

    /**
     * 币种代码
     */
    private String inputProductCcy;

    /**
     * 金额
     */
    private BigDecimal inputProductAmount;

    /**
     * 转人民币金额
     */
    private BigDecimal inputProductCcyRmb;

    /**
     * 资产池代码
     */
    private String pbcAccountId;
    

    /**
     * 贷款种类名称
     */
    private String loanTypeName;

    public String getIndustryFactorName() {
        return industryFactorName;
    }

    public void setIndustryFactorName(String industryFactorName) {
        this.industryFactorName = industryFactorName;
    }

    public String getAreaCodeName() {
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
    }

    public String getTypeOfBorrowerName() {
        return typeOfBorrowerName;
    }

    public void setTypeOfBorrowerName(String typeOfBorrowerName) {
        this.typeOfBorrowerName = typeOfBorrowerName;
    }

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getOriginalCcyName() {
        return originalCcyName;
    }

    public void setOriginalCcyName(String originalCcyName) {
        this.originalCcyName = originalCcyName;
    }

    public BigDecimal getOriginalContractAmount() {
        return originalContractAmount;
    }

    public void setOriginalContractAmount(BigDecimal originalContractAmount) {
        this.originalContractAmount = originalContractAmount;
    }

    public BigDecimal getOriginalContractAmountRmb() {
        return originalContractAmountRmb;
    }

    public void setOriginalContractAmountRmb(BigDecimal originalContractAmountRmb) {
        this.originalContractAmountRmb = originalContractAmountRmb;
    }

    public String getLoanCcy() {
        return loanCcy;
    }

    public void setLoanCcy(String loanCcy) {
        this.loanCcy = loanCcy;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }

    public BigDecimal getLoanBalanceRmb() {
        return loanBalanceRmb;
    }

    public void setLoanBalanceRmb(BigDecimal loanBalanceRmb) {
        this.loanBalanceRmb = loanBalanceRmb;
    }

    public String getTransferorAgency() {
        return transferorAgency;
    }

    public void setTransferorAgency(String transferorAgency) {
        this.transferorAgency = transferorAgency;
    }

    public String getOriginalIssuer() {
        return originalIssuer;
    }

    public void setOriginalIssuer(String originalIssuer) {
        this.originalIssuer = originalIssuer;
    }

    public String getOriginalIssuerLocation() {
        return originalIssuerLocation;
    }

    public void setOriginalIssuerLocation(String originalIssuerLocation) {
        this.originalIssuerLocation = originalIssuerLocation;
    }

    public String getEconomicComponent() {
        return economicComponent;
    }

    public void setEconomicComponent(String economicComponent) {
        this.economicComponent = economicComponent;
    }

    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale;
    }

    public String getLoanIouCode() {
        return loanIouCode;
    }

    public void setLoanIouCode(String loanIouCode) {
        this.loanIouCode = loanIouCode;
    }

    public String getLoanProductCategory() {
        return loanProductCategory;
    }

    public void setLoanProductCategory(String loanProductCategory) {
        this.loanProductCategory = loanProductCategory;
    }

    public String getActualInvestmentLoans() {
        return actualInvestmentLoans;
    }

    public void setActualInvestmentLoans(String actualInvestmentLoans) {
        this.actualInvestmentLoans = actualInvestmentLoans;
    }

    public Date getDateLoanIssuance() {
        return dateLoanIssuance;
    }

    public void setDateLoanIssuance(Date dateLoanIssuance) {
        this.dateLoanIssuance = dateLoanIssuance;
    }

    public Date getLoanMaturityDate() {
        return loanMaturityDate;
    }

    public void setLoanMaturityDate(Date loanMaturityDate) {
        this.loanMaturityDate = loanMaturityDate;
    }

    public Date getMaturityDateLoanRollover() {
        return maturityDateLoanRollover;
    }

    public void setMaturityDateLoanRollover(Date maturityDateLoanRollover) {
        this.maturityDateLoanRollover = maturityDateLoanRollover;
    }

    public String getIsTheInterestRate() {
        return isTheInterestRate;
    }

    public void setIsTheInterestRate(String isTheInterestRate) {
        this.isTheInterestRate = isTheInterestRate;
    }

    public BigDecimal getInterestRates() {
        return interestRates;
    }

    public void setInterestRates(BigDecimal interestRates) {
        this.interestRates = interestRates;
    }

    public String getLoanGuaranteeMethod() {
        return loanGuaranteeMethod;
    }

    public void setLoanGuaranteeMethod(String loanGuaranteeMethod) {
        this.loanGuaranteeMethod = loanGuaranteeMethod;
    }

    public String getPaymentForGoodsQuality() {
        return paymentForGoodsQuality;
    }

    public void setPaymentForGoodsQuality(String paymentForGoodsQuality) {
        this.paymentForGoodsQuality = paymentForGoodsQuality;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getTransferDiscountRate() {
        return transferDiscountRate;
    }

    public void setTransferDiscountRate(BigDecimal transferDiscountRate) {
        this.transferDiscountRate = transferDiscountRate;
    }

    public String getInputProductCode() {
        return inputProductCode;
    }

    public void setInputProductCode(String inputProductCode) {
        this.inputProductCode = inputProductCode;
    }

    public String getInputProductCcy() {
    	return SvrDataUtil.getNameFromDataDict(DictCode.币种_中债, inputProductCcy);
       // return inputProductCcy;
    }

    public void setInputProductCcy(String inputProductCcy) {
        this.inputProductCcy = inputProductCcy;
    }

    public BigDecimal getInputProductAmount() {
        return inputProductAmount;
    }

    public void setInputProductAmount(BigDecimal inputProductAmount) {
        this.inputProductAmount = inputProductAmount;
    }

    public BigDecimal getInputProductCcyRmb() {
        return inputProductCcyRmb;
    }

    public void setInputProductCcyRmb(BigDecimal inputProductCcyRmb) {
        this.inputProductCcyRmb = inputProductCcyRmb;
    }

    public String getPbcAccountId() {
        return pbcAccountId;
    }

    public void setPbcAccountId(String pbcAccountId) {
        this.pbcAccountId = pbcAccountId;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

	public String getDateLoanIssuanceStr() {
		return dateLoanIssuanceStr;
	}

	public void setDateLoanIssuanceStr(String dateLoanIssuanceStr) {
		this.dateLoanIssuanceStr = dateLoanIssuanceStr;
	}

	public String getLoanMaturityDateStr() {
		return loanMaturityDateStr;
	}

	public void setLoanMaturityDateStr(String loanMaturityDateStr) {
		this.loanMaturityDateStr = loanMaturityDateStr;
	}

	public String getMaturityDateLoanRolloverStr() {
		return maturityDateLoanRolloverStr;
	}

	public void setMaturityDateLoanRolloverStr(String maturityDateLoanRolloverStr) {
		this.maturityDateLoanRolloverStr = maturityDateLoanRolloverStr;
	}
}
