package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

public class ChbReportRegist extends ChbReportRegistKey {
    /**
     * null
     */
    private String prodName;

    /**
     * null
     */
    private String prodInsId;

    /**
     * null
     */
    private String prodBrand;

    /**
     * null
     */
    private Integer prodStage;

    /**
     * null
     */
    private String issuePartyId;

    /**
     * null
     */
    private String approverId;

    /**
     * null
     */
    private String approverName;

    /**
     * null
     */
    private String designerId;

    /**
     * null
     */
    private String designerName;

    /**
     * null
     */
    private String managerId;

    /**
     * null
     */
    private String managerName;

    /**
     * null
     */
    private String liaisonName;

    /**
     * null
     */
    private String liaisonTel;

    /**
     * null
     */
    private String liaisonMobile;

    /**
     * null
     */
    private String liaisonEmail;

    /**
     * null
     */
    private String profitType;

    /**
     * null
     */
    private String term;

    /**
     * null
     */
    private String investorType;

    /**
     * null
     */
    private String investArae;

    /**
     * null
     */
    private String investCountry;

    /**
     * null
     */
    private String finanSerModel;

    /**
     * null
     */
    private String operateMode;

    /**
     * null
     */
    private String bookMode;

    /**
     * null
     */
    private String cmmode;

    /**
     * null
     */
    private String pmmode;

    /**
     * null
     */
    private String mname;

    /**
     * null
     */
    private String pricedMode;

    /**
     * null
     */
    private String invAssetType;

    /**
     * null
     */
    private String conPrdTraget;

    /**
     * null
     */
    private String contMode;

    /**
     * null
     */
    private String cooperationName;

    /**
     * null
     */
    private String invTypeRat;

    /**
     * null
     */
    private String isYield;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highYield;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowYield;

    /**
     * null
     */
    private String isYieldMeasBasis;

    /**
     * null
     */
    private String riskType;

    /**
     * null
     */
    private String saleArea;

    /**
     * null
     */
    private String raiseCcy;

    /**
     * null
     */
    private String cashPrinCcy;

    /**
     * null
     */
    private String cashProfCcy;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleAmtStr;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseAmtPlan;

    /**
     * null
     */
    private Date raisePstrdate;

    /**
     * null
     */
    private Date raisePenddate;

    /**
     * null
     */
    private String investPrinDate;

    /**
     * null
     */
    private String investProfDate;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleFeeRate;

    /**
     * null
     */
    private String inDepName;

    /**
     * null
     */
    private String inDepCode;

    /**
     * null
     */
    private String outDepCtry;

    /**
     * null
     */
    private String outDepName;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal truFeeRate;

    /**
     * null
     */
    private String riskLevel;

    /**
     * null
     */
    private String termRightFlag;

    /**
     * null
     */
    private String cusRedeemFlag;

    /**
     * null
     */
    private String creditFlag;

    /**
     * null
     */
    private String creditType;

    /**
     * null
     */
    private String creditMode;

    /**
     * null
     */
    private String remark;

    /**
     * null
     */
    private String nineFile;

    /**
     * null
     */
    private String fileNo;

    /**
     * null
     */
    private String workflowId;

    /**
     * null
     */
    private Date preRegDate;

    /**
     * null
     */
    private Date preAppDate;

    /**
     * null
     */
    private String sendStatus;

    /**
     * null
     */
    private Date statusDate;

    /**
     * 取得 null
     * @return null
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 null
     * @param prodName null
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProdInsId() {
        return prodInsId;
    }

    /**
     * 设置 null
     * @param prodInsId null
     */
    public void setProdInsId(String prodInsId) {
        this.prodInsId = prodInsId == null ? null : prodInsId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProdBrand() {
        return prodBrand;
    }

    /**
     * 设置 null
     * @param prodBrand null
     */
    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand == null ? null : prodBrand.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Integer getProdStage() {
        return prodStage;
    }

    /**
     * 设置 null
     * @param prodStage null
     */
    public void setProdStage(Integer prodStage) {
        this.prodStage = prodStage;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 null
     * @param issuePartyId null
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getApproverId() {
        return approverId;
    }

    /**
     * 设置 null
     * @param approverId null
     */
    public void setApproverId(String approverId) {
        this.approverId = approverId == null ? null : approverId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * 设置 null
     * @param approverName null
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDesignerId() {
        return designerId;
    }

    /**
     * 设置 null
     * @param designerId null
     */
    public void setDesignerId(String designerId) {
        this.designerId = designerId == null ? null : designerId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDesignerName() {
        return designerName;
    }

    /**
     * 设置 null
     * @param designerName null
     */
    public void setDesignerName(String designerName) {
        this.designerName = designerName == null ? null : designerName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置 null
     * @param managerId null
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 设置 null
     * @param managerName null
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getLiaisonName() {
        return liaisonName;
    }

    /**
     * 设置 null
     * @param liaisonName null
     */
    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName == null ? null : liaisonName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getLiaisonTel() {
        return liaisonTel;
    }

    /**
     * 设置 null
     * @param liaisonTel null
     */
    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel == null ? null : liaisonTel.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getLiaisonMobile() {
        return liaisonMobile;
    }

    /**
     * 设置 null
     * @param liaisonMobile null
     */
    public void setLiaisonMobile(String liaisonMobile) {
        this.liaisonMobile = liaisonMobile == null ? null : liaisonMobile.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getLiaisonEmail() {
        return liaisonEmail;
    }

    /**
     * 设置 null
     * @param liaisonEmail null
     */
    public void setLiaisonEmail(String liaisonEmail) {
        this.liaisonEmail = liaisonEmail == null ? null : liaisonEmail.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 null
     * @param profitType null
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 null
     * @param term null
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvestorType() {
        return investorType;
    }

    /**
     * 设置 null
     * @param investorType null
     */
    public void setInvestorType(String investorType) {
        this.investorType = investorType == null ? null : investorType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvestArae() {
        return investArae;
    }

    /**
     * 设置 null
     * @param investArae null
     */
    public void setInvestArae(String investArae) {
        this.investArae = investArae == null ? null : investArae.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvestCountry() {
        return investCountry;
    }

    /**
     * 设置 null
     * @param investCountry null
     */
    public void setInvestCountry(String investCountry) {
        this.investCountry = investCountry == null ? null : investCountry.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getFinanSerModel() {
        return finanSerModel;
    }

    /**
     * 设置 null
     * @param finanSerModel null
     */
    public void setFinanSerModel(String finanSerModel) {
        this.finanSerModel = finanSerModel == null ? null : finanSerModel.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 null
     * @param operateMode null
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getBookMode() {
        return bookMode;
    }

    /**
     * 设置 null
     * @param bookMode null
     */
    public void setBookMode(String bookMode) {
        this.bookMode = bookMode == null ? null : bookMode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCmmode() {
        return cmmode;
    }

    /**
     * 设置 null
     * @param cmmode null
     */
    public void setCmmode(String cmmode) {
        this.cmmode = cmmode == null ? null : cmmode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getPmmode() {
        return pmmode;
    }

    /**
     * 设置 null
     * @param pmmode null
     */
    public void setPmmode(String pmmode) {
        this.pmmode = pmmode == null ? null : pmmode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getMname() {
        return mname;
    }

    /**
     * 设置 null
     * @param mname null
     */
    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getPricedMode() {
        return pricedMode;
    }

    /**
     * 设置 null
     * @param pricedMode null
     */
    public void setPricedMode(String pricedMode) {
        this.pricedMode = pricedMode == null ? null : pricedMode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvAssetType() {
        return invAssetType;
    }

    /**
     * 设置 null
     * @param invAssetType null
     */
    public void setInvAssetType(String invAssetType) {
        this.invAssetType = invAssetType == null ? null : invAssetType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getConPrdTraget() {
        return conPrdTraget;
    }

    /**
     * 设置 null
     * @param conPrdTraget null
     */
    public void setConPrdTraget(String conPrdTraget) {
        this.conPrdTraget = conPrdTraget == null ? null : conPrdTraget.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getContMode() {
        return contMode;
    }

    /**
     * 设置 null
     * @param contMode null
     */
    public void setContMode(String contMode) {
        this.contMode = contMode == null ? null : contMode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCooperationName() {
        return cooperationName;
    }

    /**
     * 设置 null
     * @param cooperationName null
     */
    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName == null ? null : cooperationName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvTypeRat() {
        return invTypeRat;
    }

    /**
     * 设置 null
     * @param invTypeRat null
     */
    public void setInvTypeRat(String invTypeRat) {
        this.invTypeRat = invTypeRat == null ? null : invTypeRat.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsYield() {
        return isYield;
    }

    /**
     * 设置 null
     * @param isYield null
     */
    public void setIsYield(String isYield) {
        this.isYield = isYield == null ? null : isYield.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getHighYield() {
        return highYield;
    }

    /**
     * 设置 null
     * @param highYield null
     */
    public void setHighYield(BigDecimal highYield) {
        this.highYield = highYield;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getLowYield() {
        return lowYield;
    }

    /**
     * 设置 null
     * @param lowYield null
     */
    public void setLowYield(BigDecimal lowYield) {
        this.lowYield = lowYield;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsYieldMeasBasis() {
        return isYieldMeasBasis;
    }

    /**
     * 设置 null
     * @param isYieldMeasBasis null
     */
    public void setIsYieldMeasBasis(String isYieldMeasBasis) {
        this.isYieldMeasBasis = isYieldMeasBasis == null ? null : isYieldMeasBasis.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * 设置 null
     * @param riskType null
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSaleArea() {
        return saleArea;
    }

    /**
     * 设置 null
     * @param saleArea null
     */
    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea == null ? null : saleArea.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 设置 null
     * @param raiseCcy null
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy == null ? null : raiseCcy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    /**
     * 设置 null
     * @param cashPrinCcy null
     */
    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy == null ? null : cashPrinCcy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCashProfCcy() {
        return cashProfCcy;
    }

    /**
     * 设置 null
     * @param cashProfCcy null
     */
    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy == null ? null : cashProfCcy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getSaleAmtStr() {
        return saleAmtStr;
    }

    /**
     * 设置 null
     * @param saleAmtStr null
     */
    public void setSaleAmtStr(BigDecimal saleAmtStr) {
        this.saleAmtStr = saleAmtStr;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    /**
     * 设置 null
     * @param raiseAmtPlan null
     */
    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getRaisePstrdate() {
        return raisePstrdate;
    }

    /**
     * 设置 null
     * @param raisePstrdate null
     */
    public void setRaisePstrdate(Date raisePstrdate) {
        this.raisePstrdate = raisePstrdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getRaisePenddate() {
        return raisePenddate;
    }

    /**
     * 设置 null
     * @param raisePenddate null
     */
    public void setRaisePenddate(Date raisePenddate) {
        this.raisePenddate = raisePenddate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvestPrinDate() {
        return investPrinDate;
    }

    /**
     * 设置 null
     * @param investPrinDate null
     */
    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate == null ? null : investPrinDate.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInvestProfDate() {
        return investProfDate;
    }

    /**
     * 设置 null
     * @param investProfDate null
     */
    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate == null ? null : investProfDate.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 设置 null
     * @param saleFeeRate null
     */
    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInDepName() {
        return inDepName;
    }

    /**
     * 设置 null
     * @param inDepName null
     */
    public void setInDepName(String inDepName) {
        this.inDepName = inDepName == null ? null : inDepName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInDepCode() {
        return inDepCode;
    }

    /**
     * 设置 null
     * @param inDepCode null
     */
    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode == null ? null : inDepCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOutDepCtry() {
        return outDepCtry;
    }

    /**
     * 设置 null
     * @param outDepCtry null
     */
    public void setOutDepCtry(String outDepCtry) {
        this.outDepCtry = outDepCtry == null ? null : outDepCtry.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOutDepName() {
        return outDepName;
    }

    /**
     * 设置 null
     * @param outDepName null
     */
    public void setOutDepName(String outDepName) {
        this.outDepName = outDepName == null ? null : outDepName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getTruFeeRate() {
        return truFeeRate;
    }

    /**
     * 设置 null
     * @param truFeeRate null
     */
    public void setTruFeeRate(BigDecimal truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置 null
     * @param riskLevel null
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTermRightFlag() {
        return termRightFlag;
    }

    /**
     * 设置 null
     * @param termRightFlag null
     */
    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag == null ? null : termRightFlag.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    /**
     * 设置 null
     * @param cusRedeemFlag null
     */
    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag == null ? null : cusRedeemFlag.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    /**
     * 设置 null
     * @param creditFlag null
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag == null ? null : creditFlag.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 null
     * @param creditType null
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCreditMode() {
        return creditMode;
    }

    /**
     * 设置 null
     * @param creditMode null
     */
    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode == null ? null : creditMode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 null
     * @param remark null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getNineFile() {
        return nineFile;
    }

    /**
     * 设置 null
     * @param nineFile null
     */
    public void setNineFile(String nineFile) {
        this.nineFile = nineFile == null ? null : nineFile.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置 null
     * @param fileNo null
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 null
     * @param workflowId null
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getPreRegDate() {
        return preRegDate;
    }

    /**
     * 设置 null
     * @param preRegDate null
     */
    public void setPreRegDate(Date preRegDate) {
        this.preRegDate = preRegDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getPreAppDate() {
        return preAppDate;
    }

    /**
     * 设置 null
     * @param preAppDate null
     */
    public void setPreAppDate(Date preAppDate) {
        this.preAppDate = preAppDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 null
     * @param sendStatus null
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 null
     * @param statusDate null
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}