package com.joyintech.tams.invest.common.dto.period.trdbondlendtargetduration;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: 2.1.1
 * @description:
 * @author: 强成西
 * @create: 2019-11-07 19:30
 **/
public class TrdBondLendTargetDurationDto extends BaseEntity {

    /**
     * 可能的子组合
     */
    private String actPortfolioId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 业务编号
     */
    private String totleTradeId;

    /**
     * 原始交易编号
     */
    private String mpFinprodId;

    /**
     * 分支序号
     */
    private String branch;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 交易对手
     */
    private String counterName;

    /**
     * 证券托管户
     */
    private String secManageAcctId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 证券托管户名名称
     */
    private String secAcctName;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 计划代码
     */
    private String cashId;

    /**
     * 计息开始日
     */
    private String vdate;

    /**
     * 计息结束日
     */
    private String mdate;

    /**
     * 起息日
     */
    private String vDateWhole;

    /**
     * 到期日
     */
    private String mDateWhole;

    /**
     * 支付日
     */
    private String payDate;

    /**
     * 实付（收）日期
     */
    private String tradeDate;

    /**
     * 券面总额
     */
    private BigDecimal amount;

    /**
     * 实收本（付）息总额
     */
    private BigDecimal tradeAmt;

    /**
     * 区间票面利率
     */
    private BigDecimal rangeYield;

    /**
     * 实付利息
     */
    private BigDecimal intAmt;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 理论利息
     */
    private BigDecimal theoryInt;

    /**
     * 理论还本金额
     */
    private BigDecimal theoryPrinAmt;

    /**
     * 单位应记利息
     */
    private BigDecimal unitIntAmt;

    /**
     * 单位本金
     */
    private BigDecimal unitPrinAmt;

    /**
     * 百元还本面额
     */
    private BigDecimal prinAmt;

    /**
     * 理论本息总额
     */
    private BigDecimal theoryAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 撤销理由
     */
    private String revokeReason;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

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
     * 获取 totleTradeId
     *
     * @return totleTradeId
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 totleTradeId
     *
     * @param totleTradeId totleTradeId
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 mpFinprodId
     *
     * @return mpFinprodId
     */
    public String getMpFinprodId() {
        return mpFinprodId;
    }

    /**
     * 设置 mpFinprodId
     *
     * @param mpFinprodId mpFinprodId
     */
    public void setMpFinprodId(String mpFinprodId) {
        this.mpFinprodId = mpFinprodId;
    }

    /**
     * 获取 tradeType
     *
     * @return tradeType
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 tradeType
     *
     * @param tradeType tradeType
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
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
     * 获取 counterName
     *
     * @return counterName
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * 设置 counterName
     *
     * @param counterName counterName
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
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
     * 获取 secAcctName
     *
     * @return secAcctName
     */
    public String getSecAcctName() {
        return secAcctName;
    }

    /**
     * 设置 secAcctName
     *
     * @param secAcctName secAcctName
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
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
     * 获取 finprodAbbr
     *
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     *
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 vdate
     *
     * @return vdate
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     *
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     *
     * @return mdate
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     *
     * @param mdate mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
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
     * 获取 amount
     *
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 amount
     *
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     * 获取 workflowId
     *
     * @return workflowId
     */
    @Override
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 workflowId
     *
     * @param workflowId workflowId
     */
    @Override
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 theoryAmt
     *
     * @return theoryAmt
     */
    public BigDecimal getTheoryAmt() {
        return theoryAmt;
    }

    /**
     * 设置 theoryAmt
     *
     * @param theoryAmt theoryAmt
     */
    public void setTheoryAmt(BigDecimal theoryAmt) {
        this.theoryAmt = theoryAmt;
    }

    /**
     * 获取 theoryInt
     *
     * @return theoryInt
     */
    public BigDecimal getTheoryInt() {
        return theoryInt;
    }

    /**
     * 设置 theoryInt
     *
     * @param theoryInt theoryInt
     */
    public void setTheoryInt(BigDecimal theoryInt) {
        this.theoryInt = theoryInt;
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
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
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
     * 获取 rangeYield
     *
     * @return rangeYield
     */
    public BigDecimal getRangeYield() {
        return rangeYield;
    }

    /**
     * 设置 rangeYield
     *
     * @param rangeYield rangeYield
     */
    public void setRangeYield(BigDecimal rangeYield) {
        this.rangeYield = rangeYield;
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
     * 获取 unitPrinAmt
     *
     * @return unitPrinAmt
     */
    public BigDecimal getUnitPrinAmt() {
        return unitPrinAmt;
    }

    /**
     * 设置 unitPrinAmt
     *
     * @param unitPrinAmt unitPrinAmt
     */
    public void setUnitPrinAmt(BigDecimal unitPrinAmt) {
        this.unitPrinAmt = unitPrinAmt;
    }

    /**
     * 获取 counterId
     *
     * @return counterId
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 counterId
     *
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    /**
     * 获取 theoryPrinAmt
     *
     * @return theoryPrinAmt
     */
    public BigDecimal getTheoryPrinAmt() {
        return theoryPrinAmt;
    }

    /**
     * 设置 theoryPrinAmt
     *
     * @param theoryPrinAmt theoryPrinAmt
     */
    public void setTheoryPrinAmt(BigDecimal theoryPrinAmt) {
        this.theoryPrinAmt = theoryPrinAmt;
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
     * 获取 branch
     *
     * @return branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     *
     * @param branch branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
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
     * 获取 actPortfolioId
     *
     * @return actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     *
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 registOrg
     *
     * @return registOrg
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 registOrg
     *
     * @param registOrg registOrg
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }
}
