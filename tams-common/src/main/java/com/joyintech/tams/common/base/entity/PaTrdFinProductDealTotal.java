package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PaTrdFinProductDealTotal extends BaseEntity {
    /**
     * 汇总交易编号
     */
    private String totleTradeId;

    /**
     * 分行
     */
    private String branch;

    /**
     * 单位应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 单位本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitPrin;

    /**
     * 还本面额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prin;

    /**
     * 区间票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 理论本息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoryAmt;

    /**
     * 理论应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoryInt;

    /**
     * 理论还本金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoryPrin;

    /**
     * 理论支付日
     */
    private Date theoryPayDate;

    /**
     * 应付利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal interest;

    /**
     * 平安业务类型，产品成立、强增强减、产品到期、产品存续期、普通拆借、特定目的载体等
     */
    private String paBussType;

    /**
     * 本期认购
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currentSub;

    /**
     * 上期余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastAmt;

    /**
     * 上期赎回
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastRed;

    /**
     * 上期滚入金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastAmtSub;

    /**
     * 到账资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accountingAmt;

    /**
     * 剩余本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainPrinAmt;

    /**
     * 权益登记日
     */
    private Date regDate;

    /**
     * 预计分红日
     */
    private Date bonusCfmDate;

    /**
     * 支付类型，交易方向为分红除权时填写，红利再投、现金分红
     */
    private String payType;

    /**
     * 是否违约，Y违约，N没违约
     */
    private String isBreak;

    /**
     * 随交易回款前扣除的费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeamt;

    /**
     * 昨日中债估值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yesterdayDebtValuation;

    /**
     * 成交价格脱离中债估值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal priceOutdebtValuation;

    /**
     * 理论罚息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoryPenaltyInt;

    /**
     * 交易所属部门，内部职能部门的主键
     */
    private String deptCode;

    /**
     * 取得 汇总交易编号
     * @return 汇总交易编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总交易编号
     * @param totleTradeId 汇总交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId == null ? null : totleTradeId.trim();
    }

    /**
     * 取得 分行
     * @return 分行
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 分行
     * @param branch 分行
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * 取得 单位应计利息
     * @return 单位应计利息
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 单位应计利息
     * @param unitInt 单位应计利息
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 取得 单位本金
     * @return 单位本金
     */
    public BigDecimal getUnitPrin() {
        return unitPrin;
    }

    /**
     * 设置 单位本金
     * @param unitPrin 单位本金
     */
    public void setUnitPrin(BigDecimal unitPrin) {
        this.unitPrin = unitPrin;
    }

    /**
     * 取得 还本面额
     * @return 还本面额
     */
    public BigDecimal getPrin() {
        return prin;
    }

    /**
     * 设置 还本面额
     * @param prin 还本面额
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
    }

    /**
     * 取得 区间票面利率
     * @return 区间票面利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 区间票面利率
     * @param rate 区间票面利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 理论本息总额
     * @return 理论本息总额
     */
    public BigDecimal getTheoryAmt() {
        return theoryAmt;
    }

    /**
     * 设置 理论本息总额
     * @param theoryAmt 理论本息总额
     */
    public void setTheoryAmt(BigDecimal theoryAmt) {
        this.theoryAmt = theoryAmt;
    }

    /**
     * 取得 理论应计利息
     * @return 理论应计利息
     */
    public BigDecimal getTheoryInt() {
        return theoryInt;
    }

    /**
     * 设置 理论应计利息
     * @param theoryInt 理论应计利息
     */
    public void setTheoryInt(BigDecimal theoryInt) {
        this.theoryInt = theoryInt;
    }

    /**
     * 取得 理论还本金额
     * @return 理论还本金额
     */
    public BigDecimal getTheoryPrin() {
        return theoryPrin;
    }

    /**
     * 设置 理论还本金额
     * @param theoryPrin 理论还本金额
     */
    public void setTheoryPrin(BigDecimal theoryPrin) {
        this.theoryPrin = theoryPrin;
    }

    /**
     * 取得 理论支付日
     * @return 理论支付日
     */
    public Date getTheoryPayDate() {
        return theoryPayDate;
    }

    /**
     * 设置 理论支付日
     * @param theoryPayDate 理论支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTheoryPayDate(Date theoryPayDate) {
        this.theoryPayDate = theoryPayDate;
    }

    /**
     * 取得 应付利息
     * @return 应付利息
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置 应付利息
     * @param interest 应付利息
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 取得 平安业务类型，产品成立、强增强减、产品到期、产品存续期、普通拆借、特定目的载体等
     * @return 平安业务类型，产品成立、强增强减、产品到期、产品存续期、普通拆借、特定目的载体等
     */
    public String getPaBussType() {
        return paBussType;
    }

    /**
     * 设置 平安业务类型，产品成立、强增强减、产品到期、产品存续期、普通拆借、特定目的载体等
     * @param paBussType 平安业务类型，产品成立、强增强减、产品到期、产品存续期、普通拆借、特定目的载体等
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType == null ? null : paBussType.trim();
    }

    /**
     * 取得 本期认购
     * @return 本期认购
     */
    public BigDecimal getCurrentSub() {
        return currentSub;
    }

    /**
     * 设置 本期认购
     * @param currentSub 本期认购
     */
    public void setCurrentSub(BigDecimal currentSub) {
        this.currentSub = currentSub;
    }

    /**
     * 取得 上期余额
     * @return 上期余额
     */
    public BigDecimal getLastAmt() {
        return lastAmt;
    }

    /**
     * 设置 上期余额
     * @param lastAmt 上期余额
     */
    public void setLastAmt(BigDecimal lastAmt) {
        this.lastAmt = lastAmt;
    }

    /**
     * 取得 上期赎回
     * @return 上期赎回
     */
    public BigDecimal getLastRed() {
        return lastRed;
    }

    /**
     * 设置 上期赎回
     * @param lastRed 上期赎回
     */
    public void setLastRed(BigDecimal lastRed) {
        this.lastRed = lastRed;
    }

    /**
     * 取得 上期滚入金额
     * @return 上期滚入金额
     */
    public BigDecimal getLastAmtSub() {
        return lastAmtSub;
    }

    /**
     * 设置 上期滚入金额
     * @param lastAmtSub 上期滚入金额
     */
    public void setLastAmtSub(BigDecimal lastAmtSub) {
        this.lastAmtSub = lastAmtSub;
    }

    /**
     * 取得 到账资金
     * @return 到账资金
     */
    public BigDecimal getAccountingAmt() {
        return accountingAmt;
    }

    /**
     * 设置 到账资金
     * @param accountingAmt 到账资金
     */
    public void setAccountingAmt(BigDecimal accountingAmt) {
        this.accountingAmt = accountingAmt;
    }

    /**
     * 取得 剩余本金
     * @return 剩余本金
     */
    public BigDecimal getRemainPrinAmt() {
        return remainPrinAmt;
    }

    /**
     * 设置 剩余本金
     * @param remainPrinAmt 剩余本金
     */
    public void setRemainPrinAmt(BigDecimal remainPrinAmt) {
        this.remainPrinAmt = remainPrinAmt;
    }

    /**
     * 取得 权益登记日
     * @return 权益登记日
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 设置 权益登记日
     * @param regDate 权益登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * 取得 预计分红日
     * @return 预计分红日
     */
    public Date getBonusCfmDate() {
        return bonusCfmDate;
    }

    /**
     * 设置 预计分红日
     * @param bonusCfmDate 预计分红日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBonusCfmDate(Date bonusCfmDate) {
        this.bonusCfmDate = bonusCfmDate;
    }

    /**
     * 取得 支付类型，交易方向为分红除权时填写，红利再投、现金分红
     * @return 支付类型，交易方向为分红除权时填写，红利再投、现金分红
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 支付类型，交易方向为分红除权时填写，红利再投、现金分红
     * @param payType 支付类型，交易方向为分红除权时填写，红利再投、现金分红
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 取得 交易所属部门，内部职能部门的主键
     * @return 交易所属部门，内部职能部门的主键
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 交易所属部门，内部职能部门的主键
     * @param deptCode 交易所属部门，内部职能部门的主键
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 是否违约，Y违约，N没违约
     * @return 是否违约，Y违约，N没违约
     */
    public String getIsBreak() {
        return isBreak;
    }

    /**
     * 设置 是否违约，Y违约，N没违约
     * @param isBreak 是否违约，Y违约，N没违约
     */
    public void setIsBreak(String isBreak) {
        this.isBreak = isBreak == null ? null : isBreak.trim();
    }

    /**
     * 取得 随交易回款前扣除的费用
     * @return 随交易回款前扣除的费用
     */
    public BigDecimal getFeeamt() {
        return feeamt;
    }

    /**
     * 设置 随交易回款前扣除的费用
     * @param feeamt 随交易回款前扣除的费用
     */
    public void setFeeamt(BigDecimal feeamt) {
        this.feeamt = feeamt;
    }

    /**
     * 取得 昨日中债估值
     * @return 昨日中债估值
     */
    public BigDecimal getYesterdayDebtValuation() {
        return yesterdayDebtValuation;
    }

    /**
     * 设置 昨日中债估值
     * @param yesterdayDebtValuation 昨日中债估值
     */
    public void setYesterdayDebtValuation(BigDecimal yesterdayDebtValuation) {
        this.yesterdayDebtValuation = yesterdayDebtValuation;
    }

    /**
     * 取得 成交价格脱离中债估值
     * @return 成交价格脱离中债估值
     */
    public BigDecimal getPriceOutdebtValuation() {
        return priceOutdebtValuation;
    }

    /**
     * 设置 成交价格脱离中债估值
     * @param priceOutdebtValuation 成交价格脱离中债估值
     */
    public void setPriceOutdebtValuation(BigDecimal priceOutdebtValuation) {
        this.priceOutdebtValuation = priceOutdebtValuation;
    }

    /**
     * 取得 理论罚息金额
     * @return 理论罚息金额
     */
    public BigDecimal getTheoryPenaltyInt() {
        return theoryPenaltyInt;
    }

    /**
     * 设置 理论罚息金额
     * @param theoryPenaltyInt 理论罚息金额
     */
    public void setTheoryPenaltyInt(BigDecimal theoryPenaltyInt) {
        this.theoryPenaltyInt = theoryPenaltyInt;
    }
}