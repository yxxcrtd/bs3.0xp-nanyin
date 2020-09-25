package com.joyintech.tams.invest.common.dto.settle.fundback;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * @program : tams-invest-parent
 * @description : 打印划款单数据DTO
 * @author : 强成西
 * @create : 2018-11-27 09:42
 **/
public class FundBackPrintDataBaseDto extends BaseEntity {

    /**
     * 资产提供行
     */
    private String assetSupplyBank;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 本金
     */
    private BigDecimal prinAmt;

    /**
     * 交易日
     */
    private Date tradeDate;

    /**
     * 交割日期
     */
    private Date settleDate;

    /**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;

    /**
     * 金融产品全称
     */
    private String finprodName;

    /**
     * 起息日
     */
    private Date vDate;

    /**
     * 到期日
     */
    private Date mDate;

    /**
     * 期限
     */
    private String termDays;

    /**
     * 利率
     */
    private BigDecimal yield;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 付息频率
     */
    private String payCycle;

    /**
     * 利息
     */
    private BigDecimal interest;

    /**
     * 交易经办人名称
     */
    private String tradeOperator;

    /**
     * 交易复核人名称
     */
    private String tradeChecker;

    /**
     * 总集经办人名称
     */
    private String backOperator;

    /**
     * 总集经办时间
     */
    private String backOperateTime;

    /**
     * 总集复核人名称
     */
    private String backChecker;

    /**
     * 总集复核时间
     */
    private String backCheckTime;

    /**
     * 是否保本
     */
    private String isGuarantee;

    /**
     * 存款类别
     */
    private String type6;

    /**
     * 产品名称
     */
    private String portfolioName;

    /**
     * 业务编号
     */
    private String bizCode;

    /**
     * 付款方账号
     */
    private String payAcctNo;

    /**
     * 付款方名称
     */
    private String payAcctName;

    /**
     * 付款方开户行
     */
    private String payBankDept;

    /**
     * 金额
     */
    private BigDecimal settleAmt;

    /**
     * 收款方账号
     */
    private String receiveAcctNo;

    /**
     * 收款方名称
     */
    private String receiveAcctName;

    /**
     * 开户行
     */
    private String bankDep;

    /**
     * 联行行号
     */
    private String corrNo;

    /**
     * 交易对手名称
     */
    private String counterName;

    /**
     * 大额支付系统号/网点号
     */
    private String largePayno;

    /**
     * 金融产品类型（投管分类）
     */
    private String finprodType2;

    /**
     * 条款
     */
    private List<String> textList;
    /**
     * 到期本息和
     */
    private BigDecimal mTradeAmt;

    // 产品相关 start
    private String repFinprodStage;/* 期次 */


    private String saleDepartment;

    private String dealMode;/* 处理模式 */

    private String finprodMarketId;/* 产品代码 */

    private BigDecimal lastAmtSub;/* 上期滚入金额 */

    private BigDecimal currentSub;/* 本期认购 */

    private String laySub;/* 本期认购 */

    private BigDecimal term;/* 期限 */

    private String entrustType;/* 发行方式 */

    private String noLayYield;/* 预期收益率 */

    private String layYield;/* 分层收益率 */

    private String c06Yield;/* 销售费率 */

    private String c05Yield;/* 托管费率 */

    private String c03Yield;/* 管理费率 */

    private String c99Yield;/* 其他费率合计 */
    
    private String saleDepartmentString;/* 目标客户类型 */
    
    /**
     * 获取 assetSupplyBank
     *
     * @return assetSupplyBank
     */
    public String getAssetSupplyBank() {
        if (assetSupplyBank == null || "".equals(assetSupplyBank))
            return "";
        return assetSupplyBank;
    }

    /**
     * 设置 assetSupplyBank
     *
     * @param assetSupplyBank assetSupplyBank
     */
    public void setAssetSupplyBank(String assetSupplyBank) {
        this.assetSupplyBank = assetSupplyBank;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        if (ccy == null || "".equals(ccy))
            return "";
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
     * 获取 prinAmt
     *
     * @return prinAmt
     */
    public BigDecimal getPrinAmt() {
        if (prinAmt == null)
            return BigDecimal.ZERO;
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
     * 获取 tradeDate
     *
     * @return tradeDate
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 tradeDate
     *
     * @param tradeDate tradeDate
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 settleDate
     *
     * @return settleDate
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     *
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 ps
     *
     * @return ps
     */
    public String getPs() {
        if (ps == null || "".equals(ps))
            return "";
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
     * 获取 finprodName
     *
     * @return finprodName
     */
    public String getFinprodName() {
        if (finprodName == null || "".equals(finprodName))
            return "";
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
     * 获取 vDate
     *
     * @return vDate
     */
    public Date getvDate() {
        return vDate;
    }

    /**
     * 设置 vDate
     *
     * @param vDate vDate
     */
    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 mDate
     *
     * @return mDate
     */
    public Date getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     *
     * @param mDate mDate
     */
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 termDays
     *
     * @return termDays
     */
    public String getTermDays() {
        if (termDays == null || "".equals(termDays))
            return "";
        return termDays;
    }

    /**
     * 设置 termDays
     *
     * @param termDays termDays
     */
    public void setTermDays(String termDays) {
        this.termDays = termDays;
    }

    /**
     * 获取 yield
     *
     * @return yield
     */
    public BigDecimal getYield() {
        if (yield == null)
            return BigDecimal.ZERO;
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
     * 获取 basis
     *
     * @return basis
     */
    public String getBasis() {
        if (basis == null || "".equals(basis))
            return "";
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 payCycle
     *
     * @return payCycle
     */
    public String getPayCycle() {
        if (payCycle == null || "".equals(payCycle))
            return "";
        return payCycle;
    }

    /**
     * 设置 payCycle
     *
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    /**
     * 获取 interest
     *
     * @return interest
     */
    public BigDecimal getInterest() {
        if (interest == null)
            return BigDecimal.ZERO;
        return interest;
    }

    /**
     * 设置 interest
     *
     * @param interest interest
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取 tradeOperator
     *
     * @return tradeOperator
     */
    public String getTradeOperator() {
        if (tradeOperator == null || "".equals(tradeOperator))
            return "";
        return tradeOperator;
    }

    /**
     * 设置 tradeOperator
     *
     * @param tradeOperator tradeOperator
     */
    public void setTradeOperator(String tradeOperator) {
        this.tradeOperator = tradeOperator;
    }

    /**
     * 获取 tradeChecker
     *
     * @return tradeChecker
     */
    public String getTradeChecker() {
        if (tradeChecker == null || "".equals(tradeChecker))
            return "";
        return tradeChecker;
    }

    /**
     * 设置 tradeChecker
     *
     * @param tradeChecker tradeChecker
     */
    public void setTradeChecker(String tradeChecker) {
        this.tradeChecker = tradeChecker;
    }

    /**
     * 获取 backOperator
     *
     * @return backOperator
     */
    public String getBackOperator() {
        if (backOperator == null || "".equals(backOperator))
            return "";
        return backOperator;
    }

    /**
     * 设置 backOperator
     *
     * @param backOperator backOperator
     */
    public void setBackOperator(String backOperator) {
        this.backOperator = backOperator;
    }

    /**
     * 获取 backOperateTime
     *
     * @return backOperateTime
     */
    public String getBackOperateTime() {
        if (backOperateTime == null || "".equals(backOperateTime))
            return "";
        return backOperateTime;
    }

    /**
     * 设置 backOperateTime
     *
     * @param backOperateTime backOperateTime
     */
    public void setBackOperateTime(String backOperateTime) {
        this.backOperateTime = backOperateTime;
    }

    /**
     * 获取 backChecker
     *
     * @return backChecker
     */
    public String getBackChecker() {
        if (backChecker == null || "".equals(backChecker))
            return "";
        return backChecker;
    }

    /**
     * 设置 backChecker
     *
     * @param backChecker backChecker
     */
    public void setBackChecker(String backChecker) {
        this.backChecker = backChecker;
    }

    /**
     * 获取 backCheckTime
     *
     * @return backCheckTime
     */
    public String getBackCheckTime() {
        if (backCheckTime == null || "".equals(backCheckTime))
            return "";
        return backCheckTime;
    }

    /**
     * 设置 backCheckTime
     *
     * @param backCheckTime backCheckTime
     */
    public void setBackCheckTime(String backCheckTime) {
        this.backCheckTime = backCheckTime;
    }

    /**
     * 获取 isGuarantee
     *
     * @return isGuarantee
     */
    public String getIsGuarantee() {
        if (isGuarantee == null || "".equals(isGuarantee))
            return "";
        return isGuarantee;
    }

    /**
     * 设置 isGuarantee
     *
     * @param isGuarantee isGuarantee
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    /**
     * 获取 type6
     *
     * @return type6
     */
    public String getType6() {
        if (type6 == null || "".equals(type6))
            return "";
        return type6;
    }

    /**
     * 设置 type6
     *
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName
     */
    public String getPortfolioName() {
        if (portfolioName == null || "".equals(portfolioName))
            return "";
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
     * 获取 bizCode
     *
     * @return bizCode
     */
    public String getBizCode() {
        if (bizCode == null || "".equals(bizCode))
            return "";
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
     * 获取 payAcctNo
     *
     * @return payAcctNo
     */
    public String getPayAcctNo() {
        if (payAcctNo == null || "".equals(payAcctNo))
            return "";
        return payAcctNo;
    }

    /**
     * 设置 payAcctNo
     *
     * @param payAcctNo payAcctNo
     */
    public void setPayAcctNo(String payAcctNo) {
        this.payAcctNo = payAcctNo;
    }

    /**
     * 获取 payAcctName
     *
     * @return payAcctName
     */
    public String getPayAcctName() {
        if (payAcctName == null || "".equals(payAcctName))
            return "";
        return payAcctName;
    }

    /**
     * 设置 payAcctName
     *
     * @param payAcctName payAcctName
     */
    public void setPayAcctName(String payAcctName) {
        this.payAcctName = payAcctName;
    }

    /**
     * 获取 payBankDept
     *
     * @return payBankDept
     */
    public String getPayBankDept() {
        if (payBankDept == null || "".equals(payBankDept))
            return "";
        return payBankDept;
    }

    /**
     * 设置 payBankDept
     *
     * @param payBankDept payBankDept
     */
    public void setPayBankDept(String payBankDept) {
        this.payBankDept = payBankDept;
    }

    /**
     * 获取 settleAmt
     *
     * @return settleAmt
     */
    public BigDecimal getSettleAmt() {
        if (settleAmt == null)
            return BigDecimal.ZERO;
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
     * 获取 receiveAcctNo
     *
     * @return receiveAcctNo
     */
    public String getReceiveAcctNo() {
        if (receiveAcctNo == null || "".equals(receiveAcctNo))
            return "";
        return receiveAcctNo;
    }

    /**
     * 设置 receiveAcctNo
     *
     * @param receiveAcctNo receiveAcctNo
     */
    public void setReceiveAcctNo(String receiveAcctNo) {
        this.receiveAcctNo = receiveAcctNo;
    }

    /**
     * 获取 receiveAcctName
     *
     * @return receiveAcctName
     */
    public String getReceiveAcctName() {
        if (receiveAcctName == null || "".equals(receiveAcctName))
            return "";
        return receiveAcctName;
    }

    /**
     * 设置 receiveAcctName
     *
     * @param receiveAcctName receiveAcctName
     */
    public void setReceiveAcctName(String receiveAcctName) {
        this.receiveAcctName = receiveAcctName;
    }

    /**
     * 获取 bankDep
     *
     * @return bankDep
     */
    public String getBankDep() {
        if (bankDep == null || "".equals(bankDep))
            return "";
        return bankDep;
    }

    /**
     * 设置 bankDep
     *
     * @param bankDep bankDep
     */
    public void setBankDep(String bankDep) {
        this.bankDep = bankDep;
    }

    /**
     * 获取 corrNo
     *
     * @return corrNo
     */
    public String getCorrNo() {
        if (corrNo == null || "".equals(corrNo))
            return "";
        return corrNo;
    }

    /**
     * 设置 corrNo
     *
     * @param corrNo corrNo
     */
    public void setCorrNo(String corrNo) {
        this.corrNo = corrNo;
    }

    /**
     * 获取 counterName
     *
     * @return counterName
     */
    public String getCounterName() {
        if (counterName == null || "".equals(counterName))
            return "";
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
     * 获取 largePayno
     *
     * @return largePayno
     */
    public String getLargePayno() {
        if (largePayno == null || "".equals(largePayno))
            return "";
        return largePayno;
    }

    /**
     * 设置 largePayno
     *
     * @param largePayno largePayno
     */
    public void setLargePayno(String largePayno) {
        this.largePayno = largePayno;
    }

    /**
     * 获取 finprodType2
     *
     * @return finprodType2
     */
    public String getFinprodType2() {
        if (finprodType2 == null || "".equals(finprodType2))
            return "";
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

    /**
     * 获取 mTradeAmt
     *
     * @return mTradeAmt
     */
    public BigDecimal getmTradeAmt() {
        if (mTradeAmt == null)
            return BigDecimal.ZERO;
        return mTradeAmt;
    }

    /**
     * 设置 mTradeAmt
     *
     * @param mTradeAmt mTradeAmt
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    /**
     * 获取 repFinprodStage
     *
     * @return repFinprodStage
     */
    public String getRepFinprodStage() {
        if (repFinprodStage == null || "".equals(repFinprodStage))
            return "";
        return repFinprodStage;
    }

    /**
     * 设置 repFinprodStage
     *
     * @param repFinprodStage repFinprodStage
     */
    public void setRepFinprodStage(String repFinprodStage) {
        this.repFinprodStage = repFinprodStage;
    }

    /**
     * 获取 saleDepartment
     *
     * @return saleDepartment
     */
    public String getSaleDepartment() {
        if (saleDepartment == null || "".equals(saleDepartment))
            return "";
        return saleDepartment;
    }

    /**
     * 设置 saleDepartment
     *
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    /**
     * 获取 dealMode
     *
     * @return dealMode
     */
    public String getDealMode() {
        if (dealMode == null || "".equals(dealMode))
            return "";
        return dealMode;
    }

    /**
     * 设置 dealMode
     *
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        if (finprodMarketId == null || "".equals(finprodMarketId))
            return "";
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
     * 获取 lastAmtSub
     *
     * @return lastAmtSub
     */
    public BigDecimal getLastAmtSub() {
        if (lastAmtSub == null)
            return BigDecimal.ZERO;
        return lastAmtSub;
    }

    /**
     * 设置 lastAmtSub
     *
     * @param lastAmtSub lastAmtSub
     */
    public void setLastAmtSub(BigDecimal lastAmtSub) {
        this.lastAmtSub = lastAmtSub;
    }

    /**
     * 获取 currentSub
     *
     * @return currentSub
     */
    public BigDecimal getCurrentSub() {
        if (currentSub == null)
            return BigDecimal.ZERO;
        return currentSub;
    }

    /**
     * 设置 currentSub
     *
     * @param currentSub currentSub
     */
    public void setCurrentSub(BigDecimal currentSub) {
        this.currentSub = currentSub;
    }

    /**
     * 获取 laySub
     *
     * @return laySub
     */
    public String getLaySub() {
        if (laySub == null || "".equals(laySub))
            return "";
        return laySub;
    }

    /**
     * 设置 laySub
     *
     * @param laySub laySub
     */
    public void setLaySub(String laySub) {
        this.laySub = laySub;
    }

    /**
     * 获取 term
     *
     * @return term
     */
    public BigDecimal getTerm() {
        if (term == null)
            return BigDecimal.ZERO;
        return term;
    }

    /**
     * 设置 term
     *
     * @param term term
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 获取 entrustType
     *
     * @return entrustType
     */
    public String getEntrustType() {
        if (entrustType == null || "".equals(entrustType))
            return "";
        return entrustType;
    }

    /**
     * 设置 entrustType
     *
     * @param entrustType entrustType
     */
    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    /**
     * 获取 noLayYield
     *
     * @return noLayYield
     */
    public String getNoLayYield() {
        if (noLayYield == null || "".equals(noLayYield))
            return "";
        return noLayYield;
    }

    /**
     * 设置 noLayYield
     *
     * @param noLayYield noLayYield
     */
    public void setNoLayYield(String noLayYield) {
        this.noLayYield = noLayYield;
    }

    /**
     * 获取 layYield
     *
     * @return layYield
     */
    public String getLayYield() {
        if (layYield == null || "".equals(layYield))
            return "";
        return layYield;
    }

    /**
     * 设置 layYield
     *
     * @param layYield layYield
     */
    public void setLayYield(String layYield) {
        this.layYield = layYield;
    }

    /**
     * 获取 c06Yield
     *
     * @return c06Yield
     */
    public String getC06Yield() {
        if (c06Yield == null || "".equals(c06Yield))
            return "";
        return c06Yield;
    }

    /**
     * 设置 c06Yield
     *
     * @param c06Yield c06Yield
     */
    public void setC06Yield(String c06Yield) {
        this.c06Yield = c06Yield;
    }

    /**
     * 获取 c05Yield
     *
     * @return c05Yield
     */
    public String getC05Yield() {
        if (c05Yield == null || "".equals(c05Yield))
            return "";
        return c05Yield;
    }

    /**
     * 设置 c05Yield
     *
     * @param c05Yield c05Yield
     */
    public void setC05Yield(String c05Yield) {
        this.c05Yield = c05Yield;
    }

    /**
     * 获取 c03Yield
     *
     * @return c03Yield
     */
    public String getC03Yield() {
        if (c03Yield == null || "".equals(c03Yield))
            return "";
        return c03Yield;
    }

    /**
     * 设置 c03Yield
     *
     * @param c03Yield c03Yield
     */
    public void setC03Yield(String c03Yield) {
        this.c03Yield = c03Yield;
    }

    /**
     * 获取 c99Yield
     *
     * @return c99Yield
     */
    public String getC99Yield() {
        if (c99Yield == null || "".equals(c99Yield))
            return "";
        return c99Yield;
    }

    /**
     * 设置 c99Yield
     *
     * @param c99Yield c99Yield
     */
    public void setC99Yield(String c99Yield) {
        this.c99Yield = c99Yield;
    }

    /**
     * 获取 textList
     *
     * @return textList
     */
    public List<String> getTextList() {
        return textList;
    }

    /**
     * 设置 textList
     *
     * @param textList textList
     */
    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

	public String getSaleDepartmentString() {
		String temp = "";
		if (StringUtil.isNotEmpty(saleDepartmentString)){
			List<String> temps = StringUtil.splitToList(saleDepartmentString, ",");
			for (int i = 0; i < temps.size(); i++) {
				if (i==0) {
					temp=SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, temps.get(i));
				}else{
					temp="，"+SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, temps.get(i));
				}
				
			}
        }
		return temp;
	}

	public void setSaleDepartmentString(String saleDepartmentString) {
		this.saleDepartmentString = saleDepartmentString;
	}
    
}
