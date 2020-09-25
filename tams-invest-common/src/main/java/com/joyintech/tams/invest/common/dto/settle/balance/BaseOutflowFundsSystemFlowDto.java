package com.joyintech.tams.invest.common.dto.settle.balance;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * @program: tams-invest-parent
 * @description: 系统流出流水基础类
 * @author: 强成西
 * @create: 2018-12-13 10:26
 **/
public class BaseOutflowFundsSystemFlowDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3397669824347402903L;

    /**
     * 标识系统流水
     */
    public static final String SYS = "SYS";

    /**
     * 标识托管流水
     */
    public static final String EN = "EN";


    /**
     * 系统流水还是托管流水
     */
    private String sysOrEn;
    /**
     * 是否是待办事项
     */
    private boolean todo;
    /**
     * 证券管理账户
     */
    private String secManageAcctId;

    /**
     * 是否汇总
     */
    private String isTotalTrade;

    /**
     * 系统流水币种
     */
    private String ccy;

    /**
     * 系统流水号
     */
    private String sysSeqNo;

    /**
     * 托管流水号
     */
    private String enSeqNo;

    /**
     * 匹配状态
     */
    private String matchStatus;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 业务编号
     */
    private String bizCode;

    /**
     * 业务类型
     */
    private String paBizType;

    /**
     * 投管业务类型
     */
    private String finprodType2;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 产品/资产代码
     */
    private String finprodMarketId;

    /**
     * 产品/资产名称
     */
    private String finprodName;

    /**
     * 理论本息总额
     */
    private BigDecimal settleAmtTheory;

    /**
     * 实际本息总额/到账金额
     */
    private BigDecimal settleAmt;

    /**
     * 理论到账日
     */
    private String settleDateTheory;

    /**
     * 现金流日期/到账日
     */
    private String settleDate;

    /**
     * 账号
     */
    private String account;

    /**
     * 交易对手
     */
    private String counter;

    /**
     * 对手方账户
     */
    private String counterAcctNo;

    /**
     * 对手方账户名称
     */
    private String counterAcctName;

    /**
     * 用途
     */
    private String usage;

    /**
     * 借贷方向
     */
    private String ps;

    /**
     * 备注
     */
    private String remark;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * activiti 工作流代码
     */
    private String wflWorkflowId;

    /**
     * 金融产品收益类型（净值型产品和收益型产品认申购地址不同，需要区分）
     */
    private String profitType;

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
     * 获取 bizCode
     *
     * @return bizCode
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 设置 bizCode
     *
     * @param bizCode bizCode
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * 获取 isTotalTrade
     *
     * @return isTotalTrade
     */
    public String getIsTotalTrade() {
        return isTotalTrade;
    }

    /**
     * 设置 isTotalTrade
     *
     * @param isTotalTrade isTotalTrade
     */
    public void setIsTotalTrade(String isTotalTrade) {
        this.isTotalTrade = isTotalTrade;
    }

    /**
     * 获取 paBizType
     *
     * @return paBizType
     */
    public String getPaBizType() {
        return paBizType;
    }

    /**
     * 设置 paBizType
     *
     * @param paBizType paBizType
     */
    public void setPaBizType(String paBizType) {
        this.paBizType = paBizType;
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
     * 获取 finprodName
     *
     * @return finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     *
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 settleDateTheory
     *
     * @return settleDateTheory
     */
    public String getSettleDateTheory() {
        return settleDateTheory;
    }

    /**
     * 设置 settleDateTheory
     *
     * @param settleDateTheory settleDateTheory
     */
    public void setSettleDateTheory(String settleDateTheory) {
        this.settleDateTheory = settleDateTheory;
    }

    /**
     * 获取 settleDate
     *
     * @return settleDate
     */
    public String getSettleDate() {
        return settleDate;
    }

    /**
     * 获取 settleAmtTheory
     *
     * @return settleAmtTheory
     */
    public BigDecimal getSettleAmtTheory() {
        return settleAmtTheory;
    }

    /**
     * 设置 settleAmtTheory
     *
     * @param settleAmtTheory settleAmtTheory
     */
    public void setSettleAmtTheory(BigDecimal settleAmtTheory) {
        this.settleAmtTheory = settleAmtTheory;
    }

    /**
     * 获取 settleAmt
     *
     * @return settleAmt
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 设置 settleAmt
     *
     * @param settleAmt settleAmt
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 设置 settleDate
     *
     * @param settleDate settleDate
     */
    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 counter
     *
     * @return counter
     */
    public String getCounter() {
        return counter;
    }

    /**
     * 设置 counter
     *
     * @param counter counter
     */
    public void setCounter(String counter) {
        this.counter = counter;
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
     * 获取 sysSeqNo
     *
     * @return sysSeqNo
     */
    public String getSysSeqNo() {
        return sysSeqNo;
    }

    /**
     * 设置 sysSeqNo
     *
     * @param sysSeqNo sysSeqNo
     */
    public void setSysSeqNo(String sysSeqNo) {
        this.sysSeqNo = sysSeqNo;
    }

    /**
     * 获取 matchStatus
     *
     * @return matchStatus
     */
    public String getMatchStatus() {
        return matchStatus;
    }

    /**
     * 设置 matchStatus
     *
     * @param matchStatus matchStatus
     */
    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
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
     * 获取 enSeqNo
     *
     * @return enSeqNo
     */
    public String getEnSeqNo() {
        return enSeqNo;
    }

    /**
     * 设置 enSeqNo
     *
     * @param enSeqNo enSeqNo
     */
    public void setEnSeqNo(String enSeqNo) {
        this.enSeqNo = enSeqNo;
    }

    /**
     * 获取 counterAcctNo
     *
     * @return counterAcctNo
     */
    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    /**
     * 设置 counterAcctNo
     *
     * @param counterAcctNo counterAcctNo
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }

    /**
     * 获取 counterAcctName
     *
     * @return counterAcctName
     */
    public String getCounterAcctName() {
        return counterAcctName;
    }

    /**
     * 设置 counterAcctName
     *
     * @param counterAcctName counterAcctName
     */
    public void setCounterAcctName(String counterAcctName) {
        this.counterAcctName = counterAcctName;
    }

    /**
     * 获取 usage
     *
     * @return usage
     */
    public String getUsage() {
        return usage;
    }

    /**
     * 设置 usage
     *
     * @param usage usage
     */
    public void setUsage(String usage) {
        this.usage = usage;
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
     * 获取 sysOrEn
     *
     * @return sysOrEn
     */
    public String getSysOrEn() {
        return sysOrEn;
    }

    /**
     * 设置 sysOrEn
     *
     * @param sysOrEn sysOrEn
     */
    public void setSysOrEn(String sysOrEn) {
        this.sysOrEn = sysOrEn;
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
     * 获取 ps
     *
     * @return ps
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 wflWorkflowId
     *
     * @return wflWorkflowId
     */
    public String getWflWorkflowId() {
        if (wflWorkflowId == null || "".equals(wflWorkflowId))
            return "";
        return wflWorkflowId;
    }

    /**
     * 设置 wflWorkflowId
     *
     * @param wflWorkflowId wflWorkflowId
     */
    public void setWflWorkflowId(String wflWorkflowId) {
        this.wflWorkflowId = wflWorkflowId;
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
     * 获取 account
     *
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 account
     *
     * @param account account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取 todo
     *
     * @return todo
     */
    public boolean isTodo() {
        return todo;
    }

    /**
     * 设置 todo
     *
     * @param todo todo
     */
    public void setTodo(boolean todo) {
        this.todo = todo;
    }

    /**
     * 获取 profitType
     *
     * @return profitType
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 finprodType2
     *
     * @return finprodType2
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 finprodType2
     *
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
}
