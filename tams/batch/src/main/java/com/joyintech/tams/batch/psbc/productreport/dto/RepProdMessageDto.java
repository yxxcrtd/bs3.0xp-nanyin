/*
 * 文件名：ProductInfoDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.batch.psbc.productreport.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 理财产品信息表dto
 *
 * @author jiangpeng
 * @version 1.0 2020/12/04
 * @see RepProdMessageDto
 * @since 1.0
 */
public class RepProdMessageDto extends BaseEntity {
    /**
     * 产品ID
     */
    private String finprodId;
    /**
     * 产品登记编码
     */
    private String prodRegistCode;
    /**
     * 理财产品代码
     */
    private String prodId;
    /**
     * 行内标识码
     */
    private String prodInsId;
    /**
     * 产品名称
     */
    private String finprodAbbr;
    /**
     * 产品品牌
     */
    private String prodBrand;
    /**
     * 发行机构代码
     */
    private String issuePartyId;
    /**
     * 发行机构名称
     */
    private String issuePartyName;
    /**
     * 发行机构类型 03 城商行
     */
    private String issuerType;
    /**
     * 产品期次
     */
    private String prodStage;
    /**
     * 产品收益类型
     */
    private String profitType;
    /**
     * 产品期限
     */
    private String termtype;
    /**
     * 产品风险等级
     */
    private String riskLevel;
    /**
     * 目标客户类型
     */
    private String saleTarget;
    /**
     * 资金投向地区
     */
    private String investArae;
    /**
     * 产品运作模式
     */
    private String operateMode;
    /**
     * 会计核算方式  01 表内/02 表外
     */
    private String repBookMode;
    /**
     * 产品资产配置方式
     */
    private String cmMode;
    /**
     * 产品管理模式
     */
    private String pmMode;
    /**
     * 实际管理人
     */
    private String mName;
    /**
     * 管理方式
     */
    private String manageType;
    /**
     * 产品审批人身份证号
     */
    private String approverId;
    /**
     * 产品审批人姓名
     */
    private String approverName;
    /**
     * 产品设计人身份证号
     */
    private String designerId;
    /**
     * 产品设计人姓名
     */
    private String designerName;
    /**
     * 投资经理身份证号
     */
    private String managerId;
    /**
     * 投资经理姓名
     */
    private String managerName;
    /**
     * 产品定价方式
     */
    private String pricedMode;
    /**
     * 投资资产类型
     */
    private String invAssetType;
    /**
     * 结构性产品挂钩标的
     */
    private String conPrdTraget;
    /**
     * 合作模式
     */
    private String contMode;
    /**
     * 合作机构名称
     */
    private String cooperationName;
    /**
     * 投资资产种类及比例
     */
    private String invTypeRat;
    /**
     * 是否有预期收益率
     */
    private String isYield;
    /**
     * 是否有收益率测算依据
     */
    private String isYieldMeasBasis;
    /**
     * 募集币种
     */
    private String raiseCcy;
    /**
     * 兑付本金币种
     */
    private String cashPrinCcy;
    /**
     * 兑付收益币种
     */
    private String cashProfCcy;
    /**
     * 投资本金到账日
     */
    private String investPrinDate;
    /**
     * 投资收益到账日
     */
    private String investProfDate;
    /**
     * 发行机构提前终止权标识
     */
    private String termRightFlag;
    /**
     * 客户赎回权标识
     */
    private String cusRedeemFlag;
    /**
     * 产品增信标识
     */
    private String creditFlag;
    /**
     * 产品增信形式
     */
    private String creditMode;
    /**
     * 是否为结构化（分级）产品
     */
    private String isConstracte;
    /**
     * 层级类别
     */
    private String gradeType;
    /**
     * 收益率分档说明
     */
    private String yieldComment;
    /**
     * 开放模式
     */
    private String openMode;
    /**
     * 规律开放周期
     */
    private String openCircle;
    /**
     * 其他规律开放周期 天数
     */
    private BigDecimal openCircleDays;
    /**
     * 节假日是否开放
     */
    private String holidayOnFlag;
    /**
     * 平均开放次数
     */
    private BigDecimal avgOpenCount;
    /**
     * 开放期业务
     */
    private String openBuss;
    /**
     * 募集起始日期
     */
    private Date raiseVDate;
    /**
     * 募集结束日期
     */
    private Date raiseEndDate;
    /**
     * 产品起始日期
     */
    private Date prdVDate;
    /**
     * 产品终止日期
     */
    private Date prdMDate;
    /**
     * 产品实际终止日期
     */
    private Date realMDate;
    /**
     * 报告登记日
     */
    private Date repDeclareDate;
    /**
     * 发行登记日
     */
    private Date repIssueDate;
    /**
     * 募集登记日
     */
    private Date submitTime;
    /**
     * 产品状态
     */
    private String durationFlag;
    /**
     * 产品销售区域
     */
    private String repSaleArea;
    /**
     * 是否有其他机构代销
     */
    private String isAgency;
    /**
     * 境内托管机构代码
     */
    private String inDepCode;
    /**
     * 境内托管机构名称
     */
    private String inDepName;
    /**
     * 境外托管机构国别
     */
    private String outDepCtry;
    /**
     * 境外托管机构名称
     */
    private String outDepName;
    /**
     * 理财产品资金托管账号
     */
    private String busiAcctNo;
    /**
     * 理财产品资金托管账户
     */
    private String acctName;
    /**
     * 是否新产品
     */
    private String isNew;
    /**
     * 产品募集方式
     */
    private String raiseType;
    /**
     * 产品投资性质
     */
    private String ivstorientation;
    /**
     * 是否设置最短持有期限
     */
    private String isShortTerm;
    /**
     * 最短持有期限（天）
     */
    private BigDecimal shortTerm;
    /**
     * 最短持有期后是否自由赎回
     */
    private String isFreeRedem;
    /**
     * 是否为现金管理类理财产品
     */
    private String cashManaCate;
    /**
     * 业绩比较基准上限
     */
    private BigDecimal yieldHigh;
    /**
     * 业绩比较基准下限
     */
    private BigDecimal yieldLow;
    /**
     * 业绩比较基准说明
     */
    private String yieldRemark;
    /**
     * 投资管理费率
     */
    private BigDecimal investFeeRate;
    /**
     * 是否金融同业专属
     */
    private String excFinSector;
    /**
     * 申报期预计客户最高年收益率
     */
    private BigDecimal highYield;
    /**
     * 申报期预计客户最低年收益率
     */
    private BigDecimal lowYield;
    /**
     * 发行期预计客户最高年收益率
     */
    private BigDecimal highYieldIssue;
    /**
     * 发行期预计客户最低年收益率
     */
    private BigDecimal lowYieldIssue;
    /**
     * 发行期预计平均收益率
     */
    private BigDecimal expAvgYield;
    /**
     * 托管费率
     */
    private BigDecimal truFeeRate;
    /**
     * 计划募集金额
     */
    private BigDecimal raiseAmtPlan;
    /**
     * 实际募集金额
     */
    private BigDecimal totalAmt;
    /**
     * 银行实际实现收入
     */
    private BigDecimal repActAchiProfit;
    /**
     * 兑付客户收益
     */
    private BigDecimal repCashCusProfit;
    /**
     * 兑付客户总金额
     */
    private BigDecimal repCashCusSum;
    /**
     * 客户实际年化收益率
     */
    private BigDecimal repCusActYearYield;
    /**
     * 产品实际年化收益率
     */
    private BigDecimal repProdActYearYield;
    /**
     * 产品统计日
     */
    private Date cDate;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getProdRegistCode() {
        return prodRegistCode;
    }

    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdInsId() {
        return prodInsId;
    }

    public void setProdInsId(String prodInsId) {
        this.prodInsId = prodInsId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getProdBrand() {
        return prodBrand;
    }

    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    public String getIssuePartyName() {
        return issuePartyName;
    }

    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName;
    }

    public String getIssuerType() {
        return issuerType;
    }

    public void setIssuerType(String issuerType) {
        this.issuerType = issuerType;
    }

    public String getProdStage() {
        return prodStage;
    }

    public void setProdStage(String prodStage) {
        this.prodStage = prodStage;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getTermtype() {
        return termtype;
    }

    public void setTermtype(String termtype) {
        this.termtype = termtype;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getSaleTarget() {
        return saleTarget;
    }

    public void setSaleTarget(String saleTarget) {
        this.saleTarget = saleTarget;
    }

    public String getInvestArae() {
        return investArae;
    }

    public void setInvestArae(String investArae) {
        this.investArae = investArae;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getRepBookMode() {
        return repBookMode;
    }

    public void setRepBookMode(String repBookMode) {
        this.repBookMode = repBookMode;
    }

    public String getCmMode() {
        return cmMode;
    }

    public void setCmMode(String cmMode) {
        this.cmMode = cmMode;
    }

    public String getPmMode() {
        return pmMode;
    }

    public void setPmMode(String pmMode) {
        this.pmMode = pmMode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPricedMode() {
        return pricedMode;
    }

    public void setPricedMode(String pricedMode) {
        this.pricedMode = pricedMode;
    }

    public String getInvAssetType() {
        return invAssetType;
    }

    public void setInvAssetType(String invAssetType) {
        this.invAssetType = invAssetType;
    }

    public String getConPrdTraget() {
        return conPrdTraget;
    }

    public void setConPrdTraget(String conPrdTraget) {
        this.conPrdTraget = conPrdTraget;
    }

    public String getContMode() {
        return contMode;
    }

    public void setContMode(String contMode) {
        this.contMode = contMode;
    }

    public String getCooperationName() {
        return cooperationName;
    }

    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName;
    }

    public String getInvTypeRat() {
        return invTypeRat;
    }

    public void setInvTypeRat(String invTypeRat) {
        this.invTypeRat = invTypeRat;
    }

    public String getIsYield() {
        return isYield;
    }

    public void setIsYield(String isYield) {
        this.isYield = isYield;
    }

    public String getIsYieldMeasBasis() {
        return isYieldMeasBasis;
    }

    public void setIsYieldMeasBasis(String isYieldMeasBasis) {
        this.isYieldMeasBasis = isYieldMeasBasis;
    }

    public String getRaiseCcy() {
        return raiseCcy;
    }

    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy;
    }

    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy;
    }

    public String getCashProfCcy() {
        return cashProfCcy;
    }

    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy;
    }

    public String getInvestPrinDate() {
        return investPrinDate;
    }

    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate;
    }

    public String getInvestProfDate() {
        return investProfDate;
    }

    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate;
    }

    public String getTermRightFlag() {
        return termRightFlag;
    }

    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag;
    }

    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag;
    }

    public String getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    public String getCreditMode() {
        return creditMode;
    }

    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode;
    }

    public String getIsConstracte() {
        return isConstracte;
    }

    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public String getYieldComment() {
        return yieldComment;
    }

    public void setYieldComment(String yieldComment) {
        this.yieldComment = yieldComment;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

    public String getOpenCircle() {
        return openCircle;
    }

    public void setOpenCircle(String openCircle) {
        this.openCircle = openCircle;
    }

    public BigDecimal getOpenCircleDays() {
        return openCircleDays;
    }

    public void setOpenCircleDays(BigDecimal openCircleDays) {
        this.openCircleDays = openCircleDays;
    }

    public String getHolidayOnFlag() {
        return holidayOnFlag;
    }

    public void setHolidayOnFlag(String holidayOnFlag) {
        this.holidayOnFlag = holidayOnFlag;
    }

    public BigDecimal getAvgOpenCount() {
        return avgOpenCount;
    }

    public void setAvgOpenCount(BigDecimal avgOpenCount) {
        this.avgOpenCount = avgOpenCount;
    }

    public String getOpenBuss() {
        return openBuss;
    }

    public void setOpenBuss(String openBuss) {
        this.openBuss = openBuss;
    }

    public Date getRaiseVDate() {
        return raiseVDate;
    }

    public void setRaiseVDate(Date raiseVDate) {
        this.raiseVDate = raiseVDate;
    }

    public Date getRaiseEndDate() {
        return raiseEndDate;
    }

    public void setRaiseEndDate(Date raiseEndDate) {
        this.raiseEndDate = raiseEndDate;
    }

    public Date getPrdVDate() {
        return prdVDate;
    }

    public void setPrdVDate(Date prdVDate) {
        this.prdVDate = prdVDate;
    }

    public Date getPrdMDate() {
        return prdMDate;
    }

    public void setPrdMDate(Date prdMDate) {
        this.prdMDate = prdMDate;
    }

    public Date getRealMDate() {
        return realMDate;
    }

    public void setRealMDate(Date realMDate) {
        this.realMDate = realMDate;
    }

    public Date getRepDeclareDate() {
        return repDeclareDate;
    }

    public void setRepDeclareDate(Date repDeclareDate) {
        this.repDeclareDate = repDeclareDate;
    }

    public Date getRepIssueDate() {
        return repIssueDate;
    }

    public void setRepIssueDate(Date repIssueDate) {
        this.repIssueDate = repIssueDate;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getDurationFlag() {
        return durationFlag;
    }

    public void setDurationFlag(String durationFlag) {
        this.durationFlag = durationFlag;
    }

    public String getRepSaleArea() {
        return repSaleArea;
    }

    public void setRepSaleArea(String repSaleArea) {
        this.repSaleArea = repSaleArea;
    }

    public String getIsAgency() {
        return isAgency;
    }

    public void setIsAgency(String isAgency) {
        this.isAgency = isAgency;
    }

    public String getInDepCode() {
        return inDepCode;
    }

    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode;
    }

    public String getInDepName() {
        return inDepName;
    }

    public void setInDepName(String inDepName) {
        this.inDepName = inDepName;
    }

    public String getOutDepCtry() {
        return outDepCtry;
    }

    public void setOutDepCtry(String outDepCtry) {
        this.outDepCtry = outDepCtry;
    }

    public String getOutDepName() {
        return outDepName;
    }

    public void setOutDepName(String outDepName) {
        this.outDepName = outDepName;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getIvstorientation() {
        return ivstorientation;
    }

    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation;
    }

    public String getIsShortTerm() {
        return isShortTerm;
    }

    public void setIsShortTerm(String isShortTerm) {
        this.isShortTerm = isShortTerm;
    }

    public BigDecimal getShortTerm() {
        return shortTerm;
    }

    public void setShortTerm(BigDecimal shortTerm) {
        this.shortTerm = shortTerm;
    }

    public String getIsFreeRedem() {
        return isFreeRedem;
    }

    public void setIsFreeRedem(String isFreeRedem) {
        this.isFreeRedem = isFreeRedem;
    }

    public String getCashManaCate() {
        return cashManaCate;
    }

    public void setCashManaCate(String cashManaCate) {
        this.cashManaCate = cashManaCate;
    }

    public BigDecimal getYieldHigh() {
        return yieldHigh;
    }

    public void setYieldHigh(BigDecimal yieldHigh) {
        this.yieldHigh = yieldHigh;
    }

    public BigDecimal getYieldLow() {
        return yieldLow;
    }

    public void setYieldLow(BigDecimal yieldLow) {
        this.yieldLow = yieldLow;
    }

    public String getYieldRemark() {
        return yieldRemark;
    }

    public void setYieldRemark(String yieldRemark) {
        this.yieldRemark = yieldRemark;
    }

    public BigDecimal getInvestFeeRate() {
        return investFeeRate;
    }

    public void setInvestFeeRate(BigDecimal investFeeRate) {
        this.investFeeRate = investFeeRate;
    }

    public String getExcFinSector() {
        return excFinSector;
    }

    public void setExcFinSector(String excFinSector) {
        this.excFinSector = excFinSector;
    }

    public BigDecimal getHighYield() {
        return highYield;
    }

    public void setHighYield(BigDecimal highYield) {
        this.highYield = highYield;
    }

    public BigDecimal getLowYield() {
        return lowYield;
    }

    public void setLowYield(BigDecimal lowYield) {
        this.lowYield = lowYield;
    }

    public BigDecimal getHighYieldIssue() {
        return highYieldIssue;
    }

    public void setHighYieldIssue(BigDecimal highYieldIssue) {
        this.highYieldIssue = highYieldIssue;
    }

    public BigDecimal getLowYieldIssue() {
        return lowYieldIssue;
    }

    public void setLowYieldIssue(BigDecimal lowYieldIssue) {
        this.lowYieldIssue = lowYieldIssue;
    }

    public BigDecimal getExpAvgYield() {
        return expAvgYield;
    }

    public void setExpAvgYield(BigDecimal expAvgYield) {
        this.expAvgYield = expAvgYield;
    }

    public BigDecimal getTruFeeRate() {
        return truFeeRate;
    }

    public void setTruFeeRate(BigDecimal truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getRepActAchiProfit() {
        return repActAchiProfit;
    }

    public void setRepActAchiProfit(BigDecimal repActAchiProfit) {
        this.repActAchiProfit = repActAchiProfit;
    }

    public BigDecimal getRepCashCusProfit() {
        return repCashCusProfit;
    }

    public void setRepCashCusProfit(BigDecimal repCashCusProfit) {
        this.repCashCusProfit = repCashCusProfit;
    }

    public BigDecimal getRepCashCusSum() {
        return repCashCusSum;
    }

    public void setRepCashCusSum(BigDecimal repCashCusSum) {
        this.repCashCusSum = repCashCusSum;
    }

    public BigDecimal getRepCusActYearYield() {
        return repCusActYearYield;
    }

    public void setRepCusActYearYield(BigDecimal repCusActYearYield) {
        this.repCusActYearYield = repCusActYearYield;
    }

    public BigDecimal getRepProdActYearYield() {
        return repProdActYearYield;
    }

    public void setRepProdActYearYield(BigDecimal repProdActYearYield) {
        this.repProdActYearYield = repProdActYearYield;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }
}
