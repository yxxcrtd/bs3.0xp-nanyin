package com.joyintech.tams.invest.common.dto.period.trdexchangeduration;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 外汇远期掉期到期交易自动生成Dto
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @Project: pingan-parent
 * @Package com.joyintech.period.trdexchangeduration.dto
 * @Description: 页面查询参数
 */
public class TrdExchangeDurationDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -987161215779376712L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 结清状态
     */
    private String cleanStatus;

    /**
     * 交易编号
     */
    private String busiCode;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 根据投资目的排序的券面
     */
    private String amountList;

    /**
     * 平安业务类型
     */
    private String paBussType;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 证券托管户
     */
    private String secManageAcctId;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 产品代码
     */
    private String finprodMarketName;

    /**
     * 产品起息日
     */
    private String vDateWhole;

    /**
     * 产品到期日
     */
    private String mDateWhole;

    /**
     * 计息开始日
     */
    private String vDate;

    /**
     * 计息结束日
     */
    private String mDate;

    /**
     * 是否需要跳过插入流程表数据
     */
    private Boolean needSkipInsertWfl;

    /**
     * 资金到账日
     */
    private String tradeDate;

    /**
     * 券面总额
     */
    private BigDecimal qty;

    /**
     * 全价总额 理论本息总额
     */
    private BigDecimal fpriceAmt;

    /**
     * 实收本息总额
     */
    private BigDecimal tradeAmt;

    /**
     * 实收利息总额
     */
    private BigDecimal intAmt;

    /**
     * 还本金额
     */
    private BigDecimal prinAmt;

    /**
     * 理论应计利息
     */
    private BigDecimal intAmtTheory;

    /**
     * 理论还本金额
     */
    private BigDecimal prinAmtTheory;

    /**
     * 现金流计划表.计息本金 单位本金,
     */
    private BigDecimal intPrinAmt;

    /**
     * 单位应计利息,
     */
    private BigDecimal unitIntAmt;

    /**
     * 区间票面利率
     */
    private BigDecimal yield;

    /**
     * 支付日
     */
    private String payDate;

    /**
     * 现金流代码（多条时，以<code>,</code>分割）
     */
    private String cashId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易备注
     */
    private String remark;

    /**
     * 撤销理由
     */
    private String revokeReason;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    // 资产逾期功能新增 start
    /**
     * 是否结清，收息付费时用
     */
    private String isClean;

    /**
     * 获取 isClean
     *
     * @return isClean
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 isClean
     *
     * @param isClean isClean
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

    // 资产逾期功能新增 end

    /**
     * 交易场所
     */
    private String tradeMarket;

    private TrdExchangeDurDbInertDto trdExchangeDurDbInertDto;

    /**
     * 获取 issuePartyId
     *
     * @return issuePartyId
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 issuePartyId
     *
     * @param issuePartyId issuePartyId
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    /**
     * 获取 trdExchangeDurDbInertDto
     *
     * @return trdExchangeDurDbInertDto
     */
    public TrdExchangeDurDbInertDto getTrdExchangeDurDbInertDto() {
        return trdExchangeDurDbInertDto;
    }

    /**
     * 设置 trdExchangeDurDbInertDto
     *
     * @param trdExchangeDurDbInertDto trdExchangeDurDbInertDto
     */
    public void setTrdExchangeDurDbInertDto(TrdExchangeDurDbInertDto trdExchangeDurDbInertDto) {
        this.trdExchangeDurDbInertDto = trdExchangeDurDbInertDto;
    }

    /**
     * 获取 revokeReason
     *
     * @return revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     *
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 statusValid
     *
     * @return statusValid
     */
    public String getStatusValid() {
        return statusValid;
    }

    /**
     * 设置 statusValid
     *
     * @param statusValid statusValid
     */
    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    /**
     * 获取 busiCode
     *
     * @return busiCode
     */
    public String getBusiCode() {
        return busiCode;
    }

    /**
     * 设置 busiCode
     *
     * @param busiCode busiCode
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * 获取 cashType
     *
     * @return cashType
     */
    public String getCashType() {
        return cashType;
    }

    /**
     * 设置 cashType
     *
     * @param cashType cashType
     */
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 secManageAcctId
     *
     * @return secManageAcctId
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    /**
     * 设置 secManageAcctId
     *
     * @param secManageAcctId secManageAcctId
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 finprodMarketName
     *
     * @return finprodMarketName
     */
    public String getFinprodMarketName() {
        return finprodMarketName;
    }

    /**
     * 设置 finprodMarketName
     *
     * @param finprodMarketName finprodMarketName
     */
    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }

    /**
     * 获取 vDate
     *
     * @return vDate
     */
    public String getvDate() {
        return vDate;
    }

    /**
     * 设置 vDate
     *
     * @param vDate vDate
     */
    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 mDate
     *
     * @return mDate
     */
    public String getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     *
     * @param mDate mDate
     */
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 tradeDate
     *
     * @return tradeDate
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 tradeDate
     *
     * @param tradeDate tradeDate
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 qty
     *
     * @return qty
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 qty
     *
     * @param qty qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 获取 fpriceAmt
     *
     * @return fpriceAmt
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 fpriceAmt
     *
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 获取 tradeAmt
     *
     * @return tradeAmt
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     *
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 intAmt
     *
     * @return intAmt
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     *
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 prinAmt
     *
     * @return prinAmt
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     *
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 intPrinAmt
     *
     * @return intPrinAmt
     */
    public BigDecimal getIntPrinAmt() {
        return intPrinAmt;
    }

    /**
     * 设置 intPrinAmt
     *
     * @param intPrinAmt intPrinAmt
     */
    public void setIntPrinAmt(BigDecimal intPrinAmt) {
        this.intPrinAmt = intPrinAmt;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 vDateWhole
     *
     * @return vDateWhole
     */
    public String getvDateWhole() {
        return vDateWhole;
    }

    /**
     * 设置 vDateWhole
     *
     * @param vDateWhole vDateWhole
     */
    public void setvDateWhole(String vDateWhole) {
        this.vDateWhole = vDateWhole;
    }

    /**
     * 获取 mDateWhole
     *
     * @return mDateWhole
     */
    public String getmDateWhole() {
        return mDateWhole;
    }

    /**
     * 设置 mDateWhole
     *
     * @param mDateWhole mDateWhole
     */
    public void setmDateWhole(String mDateWhole) {
        this.mDateWhole = mDateWhole;
    }

    /**
     * 获取 payDate
     *
     * @return payDate
     */
    public String getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     *
     * @param payDate payDate
     */
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 unitIntAmt
     *
     * @return unitIntAmt
     */
    public BigDecimal getUnitIntAmt() {
        return unitIntAmt;
    }

    /**
     * 设置 unitIntAmt
     *
     * @param unitIntAmt unitIntAmt
     */
    public void setUnitIntAmt(BigDecimal unitIntAmt) {
        this.unitIntAmt = unitIntAmt;
    }

    /**
     * 获取 tradeMarket
     *
     * @return tradeMarket
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     *
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 yield
     *
     * @return yield
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     *
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 prinAmtTheory
     *
     * @return prinAmtTheory
     */
    public BigDecimal getPrinAmtTheory() {
        return prinAmtTheory;
    }

    /**
     * 设置 prinAmtTheory
     *
     * @param prinAmtTheory prinAmtTheory
     */
    public void setPrinAmtTheory(BigDecimal prinAmtTheory) {
        this.prinAmtTheory = prinAmtTheory;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 intAmtTheory
     *
     * @return intAmtTheory
     */
    public BigDecimal getIntAmtTheory() {
        return intAmtTheory;
    }

    /**
     * 设置 intAmtTheory
     *
     * @param intAmtTheory intAmtTheory
     */
    public void setIntAmtTheory(BigDecimal intAmtTheory) {
        this.intAmtTheory = intAmtTheory;
    }

    /**
     * 获取 cashId
     *
     * @return cashId
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     *
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 paBussType
     *
     * @return paBussType
     */
    public String getPaBussType() {
        return paBussType;
    }

    /**
     * 设置 paBussType
     *
     * @param paBussType paBussType
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    /**
     * 获取 needSkipInsertWfl
     *
     * @return needSkipInsertWfl
     */
    public Boolean getNeedSkipInsertWfl() {
        return needSkipInsertWfl;
    }

    /**
     * 设置 needSkipInsertWfl
     *
     * @param needSkipInsertWfl needSkipInsertWfl
     */
    public void setNeedSkipInsertWfl(Boolean needSkipInsertWfl) {
        this.needSkipInsertWfl = needSkipInsertWfl;
    }

    /**
     * 获取 invAim
     *
     * @return invAim
     */
    public String getInvAim() {
        if (invAim == null || "".equals(invAim))
            return "";
        return invAim;
    }

    /**
     * 设置 invAim
     *
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 amountList
     *
     * @return amountList
     */
    public String getAmountList() {
        if (amountList == null || "".equals(amountList))
            return "";
        return amountList;
    }

    /**
     * 设置 amountList
     *
     * @param amountList amountList
     */
    public void setAmountList(String amountList) {
        this.amountList = amountList;
    }

    /**
     * 获取 cleanStatus
     *
     * @return cleanStatus
     */
    public String getCleanStatus() {
        return cleanStatus;
    }

    /**
     * 设置 cleanStatus
     *
     * @param cleanStatus cleanStatus
     */
    public void setCleanStatus(String cleanStatus) {
        this.cleanStatus = cleanStatus;
    }
}
