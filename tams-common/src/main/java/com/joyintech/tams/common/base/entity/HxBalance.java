package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

public class HxBalance extends BaseEntity implements Serializable {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 月份
     */
    private String crtMon;

    /**
     * 投资组合
     */
    private String pbcAssetscode;

    /**
     * 币种
     */
    private String currType;

    /**
     * 期初期末标志
     */
    private String qcQmFlag;

    /**
     * 银行存款
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal deposit;

    /**
     * 货币市场类投资
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currInvest;

    /**
     * 其他标准债权类投资
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal credInvest;

    /**
     * 债券类投资
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bondInvest;

    /**
     * 非标项目类投资
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feibiaoInvest;

    /**
     * 权益类投资
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal quanyiInvest;

    /**
     * 衍生品投资
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal derivaInvest;

    /**
     * 其它投资
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal otherInvest;

    /**
     * 应收利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal interAccr;

    /**
     * 拆入资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal inFund;

    /**
     * 卖出回购资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal huigouFund;

    /**
     * 衍生金融负债
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal derivaDebt;

    /**
     * 应付利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accrProfit;

    /**
     * 理财产品本金-收益型
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capitalShouyi;

    /**
     * 理财产品本金-净值型
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capitalJingzhi;

    /**
     * 理财产品应付收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accrIncome;

    /**
     * 应交税费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxPay;

    /**
     * 预计负债
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yujiFuzhai;

    /**
     * 其它应付款
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal otherPay;

    /**
     * 未分配利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal undisProfit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务日期
     */
    private String busiDate;

    private static final long serialVersionUID = 1L;

    public HxBalance(Integer id, String crtMon, String pbcAssetscode, String currType, String qcQmFlag, BigDecimal deposit, BigDecimal currInvest, BigDecimal credInvest, BigDecimal bondInvest, BigDecimal feibiaoInvest, BigDecimal quanyiInvest, BigDecimal derivaInvest, BigDecimal otherInvest, BigDecimal interAccr, BigDecimal inFund, BigDecimal huigouFund, BigDecimal derivaDebt, BigDecimal accrProfit, BigDecimal capitalShouyi, BigDecimal capitalJingzhi, BigDecimal accrIncome, BigDecimal taxPay, BigDecimal yujiFuzhai, BigDecimal otherPay, BigDecimal undisProfit, String remark, String busiDate) {
        this.id = id;
        this.crtMon = crtMon;
        this.pbcAssetscode = pbcAssetscode;
        this.currType = currType;
        this.qcQmFlag = qcQmFlag;
        this.deposit = deposit;
        this.currInvest = currInvest;
        this.credInvest = credInvest;
        this.bondInvest = bondInvest;
        this.feibiaoInvest = feibiaoInvest;
        this.quanyiInvest = quanyiInvest;
        this.derivaInvest = derivaInvest;
        this.otherInvest = otherInvest;
        this.interAccr = interAccr;
        this.inFund = inFund;
        this.huigouFund = huigouFund;
        this.derivaDebt = derivaDebt;
        this.accrProfit = accrProfit;
        this.capitalShouyi = capitalShouyi;
        this.capitalJingzhi = capitalJingzhi;
        this.accrIncome = accrIncome;
        this.taxPay = taxPay;
        this.yujiFuzhai = yujiFuzhai;
        this.otherPay = otherPay;
        this.undisProfit = undisProfit;
        this.remark = remark;
        this.busiDate = busiDate;
    }

    public HxBalance() {
        super();
    }

    /**
     * 获取 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 月份
     */
    public String getCrtMon() {
        return crtMon;
    }

    /**
     * 设置 月份
     */
    public void setCrtMon(String crtMon) {
        this.crtMon = crtMon == null ? null : crtMon.trim();
    }

    /**
     * 获取 投资组合
     */
    public String getPbcAssetscode() {
        return pbcAssetscode;
    }

    /**
     * 设置 投资组合
     */
    public void setPbcAssetscode(String pbcAssetscode) {
        this.pbcAssetscode = pbcAssetscode == null ? null : pbcAssetscode.trim();
    }

    /**
     * 获取 币种
     */
    public String getCurrType() {
        return currType;
    }

    /**
     * 设置 币种
     */
    public void setCurrType(String currType) {
        this.currType = currType == null ? null : currType.trim();
    }

    /**
     * 获取 期初期末标志
     */
    public String getQcQmFlag() {
        return qcQmFlag;
    }

    /**
     * 设置 期初期末标志
     */
    public void setQcQmFlag(String qcQmFlag) {
        this.qcQmFlag = qcQmFlag == null ? null : qcQmFlag.trim();
    }

    /**
     * 获取 银行存款
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * 设置 银行存款
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    /**
     * 获取 货币市场类投资
     */
    public BigDecimal getCurrInvest() {
        return currInvest;
    }

    /**
     * 设置 货币市场类投资
     */
    public void setCurrInvest(BigDecimal currInvest) {
        this.currInvest = currInvest;
    }

    /**
     * 获取 其他标准债权类投资
     */
    public BigDecimal getCredInvest() {
        return credInvest;
    }

    /**
     * 设置 其他标准债权类投资
     */
    public void setCredInvest(BigDecimal credInvest) {
        this.credInvest = credInvest;
    }

    /**
     * 获取 债券类投资
     */
    public BigDecimal getBondInvest() {
        return bondInvest;
    }

    /**
     * 设置 债券类投资
     */
    public void setBondInvest(BigDecimal bondInvest) {
        this.bondInvest = bondInvest;
    }

    /**
     * 获取 非标项目类投资
     */
    public BigDecimal getFeibiaoInvest() {
        return feibiaoInvest;
    }

    /**
     * 设置 非标项目类投资
     */
    public void setFeibiaoInvest(BigDecimal feibiaoInvest) {
        this.feibiaoInvest = feibiaoInvest;
    }

    /**
     * 获取 权益类投资
     */
    public BigDecimal getQuanyiInvest() {
        return quanyiInvest;
    }

    /**
     * 设置 权益类投资
     */
    public void setQuanyiInvest(BigDecimal quanyiInvest) {
        this.quanyiInvest = quanyiInvest;
    }

    /**
     * 获取 衍生品投资
     */
    public BigDecimal getDerivaInvest() {
        return derivaInvest;
    }

    /**
     * 设置 衍生品投资
     */
    public void setDerivaInvest(BigDecimal derivaInvest) {
        this.derivaInvest = derivaInvest;
    }

    /**
     * 获取 其它投资
     */
    public BigDecimal getOtherInvest() {
        return otherInvest;
    }

    /**
     * 设置 其它投资
     */
    public void setOtherInvest(BigDecimal otherInvest) {
        this.otherInvest = otherInvest;
    }

    /**
     * 获取 应收利息
     */
    public BigDecimal getInterAccr() {
        return interAccr;
    }

    /**
     * 设置 应收利息
     */
    public void setInterAccr(BigDecimal interAccr) {
        this.interAccr = interAccr;
    }

    /**
     * 获取 拆入资金
     */
    public BigDecimal getInFund() {
        return inFund;
    }

    /**
     * 设置 拆入资金
     */
    public void setInFund(BigDecimal inFund) {
        this.inFund = inFund;
    }

    /**
     * 获取 卖出回购资金
     */
    public BigDecimal getHuigouFund() {
        return huigouFund;
    }

    /**
     * 设置 卖出回购资金
     */
    public void setHuigouFund(BigDecimal huigouFund) {
        this.huigouFund = huigouFund;
    }

    /**
     * 获取 衍生金融负债
     */
    public BigDecimal getDerivaDebt() {
        return derivaDebt;
    }

    /**
     * 设置 衍生金融负债
     */
    public void setDerivaDebt(BigDecimal derivaDebt) {
        this.derivaDebt = derivaDebt;
    }

    /**
     * 获取 应付利息
     */
    public BigDecimal getAccrProfit() {
        return accrProfit;
    }

    /**
     * 设置 应付利息
     */
    public void setAccrProfit(BigDecimal accrProfit) {
        this.accrProfit = accrProfit;
    }

    /**
     * 获取 理财产品本金-收益型
     */
    public BigDecimal getCapitalShouyi() {
        return capitalShouyi;
    }

    /**
     * 设置 理财产品本金-收益型
     */
    public void setCapitalShouyi(BigDecimal capitalShouyi) {
        this.capitalShouyi = capitalShouyi;
    }

    /**
     * 获取 理财产品本金-净值型
     */
    public BigDecimal getCapitalJingzhi() {
        return capitalJingzhi;
    }

    /**
     * 设置 理财产品本金-净值型
     */
    public void setCapitalJingzhi(BigDecimal capitalJingzhi) {
        this.capitalJingzhi = capitalJingzhi;
    }

    /**
     * 获取 理财产品应付收益
     */
    public BigDecimal getAccrIncome() {
        return accrIncome;
    }

    /**
     * 设置 理财产品应付收益
     */
    public void setAccrIncome(BigDecimal accrIncome) {
        this.accrIncome = accrIncome;
    }

    /**
     * 获取 应交税费
     */
    public BigDecimal getTaxPay() {
        return taxPay;
    }

    /**
     * 设置 应交税费
     */
    public void setTaxPay(BigDecimal taxPay) {
        this.taxPay = taxPay;
    }

    /**
     * 获取 预计负债
     */
    public BigDecimal getYujiFuzhai() {
        return yujiFuzhai;
    }

    /**
     * 设置 预计负债
     */
    public void setYujiFuzhai(BigDecimal yujiFuzhai) {
        this.yujiFuzhai = yujiFuzhai;
    }

    /**
     * 获取 其它应付款
     */
    public BigDecimal getOtherPay() {
        return otherPay;
    }

    /**
     * 设置 其它应付款
     */
    public void setOtherPay(BigDecimal otherPay) {
        this.otherPay = otherPay;
    }

    /**
     * 获取 未分配利润
     */
    public BigDecimal getUndisProfit() {
        return undisProfit;
    }

    /**
     * 设置 未分配利润
     */
    public void setUndisProfit(BigDecimal undisProfit) {
        this.undisProfit = undisProfit;
    }

    /**
     * 获取 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 业务日期
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 业务日期
     */
    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate == null ? null : busiDate.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HxBalance other = (HxBalance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCrtMon() == null ? other.getCrtMon() == null : this.getCrtMon().equals(other.getCrtMon()))
                && (this.getPbcAssetscode() == null ? other.getPbcAssetscode() == null : this.getPbcAssetscode().equals(other.getPbcAssetscode()))
                && (this.getCurrType() == null ? other.getCurrType() == null : this.getCurrType().equals(other.getCurrType()))
                && (this.getQcQmFlag() == null ? other.getQcQmFlag() == null : this.getQcQmFlag().equals(other.getQcQmFlag()))
                && (this.getDeposit() == null ? other.getDeposit() == null : this.getDeposit().equals(other.getDeposit()))
                && (this.getCurrInvest() == null ? other.getCurrInvest() == null : this.getCurrInvest().equals(other.getCurrInvest()))
                && (this.getCredInvest() == null ? other.getCredInvest() == null : this.getCredInvest().equals(other.getCredInvest()))
                && (this.getBondInvest() == null ? other.getBondInvest() == null : this.getBondInvest().equals(other.getBondInvest()))
                && (this.getFeibiaoInvest() == null ? other.getFeibiaoInvest() == null : this.getFeibiaoInvest().equals(other.getFeibiaoInvest()))
                && (this.getQuanyiInvest() == null ? other.getQuanyiInvest() == null : this.getQuanyiInvest().equals(other.getQuanyiInvest()))
                && (this.getDerivaInvest() == null ? other.getDerivaInvest() == null : this.getDerivaInvest().equals(other.getDerivaInvest()))
                && (this.getOtherInvest() == null ? other.getOtherInvest() == null : this.getOtherInvest().equals(other.getOtherInvest()))
                && (this.getInterAccr() == null ? other.getInterAccr() == null : this.getInterAccr().equals(other.getInterAccr()))
                && (this.getInFund() == null ? other.getInFund() == null : this.getInFund().equals(other.getInFund()))
                && (this.getHuigouFund() == null ? other.getHuigouFund() == null : this.getHuigouFund().equals(other.getHuigouFund()))
                && (this.getDerivaDebt() == null ? other.getDerivaDebt() == null : this.getDerivaDebt().equals(other.getDerivaDebt()))
                && (this.getAccrProfit() == null ? other.getAccrProfit() == null : this.getAccrProfit().equals(other.getAccrProfit()))
                && (this.getCapitalShouyi() == null ? other.getCapitalShouyi() == null : this.getCapitalShouyi().equals(other.getCapitalShouyi()))
                && (this.getCapitalJingzhi() == null ? other.getCapitalJingzhi() == null : this.getCapitalJingzhi().equals(other.getCapitalJingzhi()))
                && (this.getAccrIncome() == null ? other.getAccrIncome() == null : this.getAccrIncome().equals(other.getAccrIncome()))
                && (this.getTaxPay() == null ? other.getTaxPay() == null : this.getTaxPay().equals(other.getTaxPay()))
                && (this.getYujiFuzhai() == null ? other.getYujiFuzhai() == null : this.getYujiFuzhai().equals(other.getYujiFuzhai()))
                && (this.getOtherPay() == null ? other.getOtherPay() == null : this.getOtherPay().equals(other.getOtherPay()))
                && (this.getUndisProfit() == null ? other.getUndisProfit() == null : this.getUndisProfit().equals(other.getUndisProfit()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getBusiDate() == null ? other.getBusiDate() == null : this.getBusiDate().equals(other.getBusiDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCrtMon() == null) ? 0 : getCrtMon().hashCode());
        result = prime * result + ((getPbcAssetscode() == null) ? 0 : getPbcAssetscode().hashCode());
        result = prime * result + ((getCurrType() == null) ? 0 : getCurrType().hashCode());
        result = prime * result + ((getQcQmFlag() == null) ? 0 : getQcQmFlag().hashCode());
        result = prime * result + ((getDeposit() == null) ? 0 : getDeposit().hashCode());
        result = prime * result + ((getCurrInvest() == null) ? 0 : getCurrInvest().hashCode());
        result = prime * result + ((getCredInvest() == null) ? 0 : getCredInvest().hashCode());
        result = prime * result + ((getBondInvest() == null) ? 0 : getBondInvest().hashCode());
        result = prime * result + ((getFeibiaoInvest() == null) ? 0 : getFeibiaoInvest().hashCode());
        result = prime * result + ((getQuanyiInvest() == null) ? 0 : getQuanyiInvest().hashCode());
        result = prime * result + ((getDerivaInvest() == null) ? 0 : getDerivaInvest().hashCode());
        result = prime * result + ((getOtherInvest() == null) ? 0 : getOtherInvest().hashCode());
        result = prime * result + ((getInterAccr() == null) ? 0 : getInterAccr().hashCode());
        result = prime * result + ((getInFund() == null) ? 0 : getInFund().hashCode());
        result = prime * result + ((getHuigouFund() == null) ? 0 : getHuigouFund().hashCode());
        result = prime * result + ((getDerivaDebt() == null) ? 0 : getDerivaDebt().hashCode());
        result = prime * result + ((getAccrProfit() == null) ? 0 : getAccrProfit().hashCode());
        result = prime * result + ((getCapitalShouyi() == null) ? 0 : getCapitalShouyi().hashCode());
        result = prime * result + ((getCapitalJingzhi() == null) ? 0 : getCapitalJingzhi().hashCode());
        result = prime * result + ((getAccrIncome() == null) ? 0 : getAccrIncome().hashCode());
        result = prime * result + ((getTaxPay() == null) ? 0 : getTaxPay().hashCode());
        result = prime * result + ((getYujiFuzhai() == null) ? 0 : getYujiFuzhai().hashCode());
        result = prime * result + ((getOtherPay() == null) ? 0 : getOtherPay().hashCode());
        result = prime * result + ((getUndisProfit() == null) ? 0 : getUndisProfit().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getBusiDate() == null) ? 0 : getBusiDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", crtMon=").append(crtMon);
        sb.append(", pbcAssetscode=").append(pbcAssetscode);
        sb.append(", currType=").append(currType);
        sb.append(", qcQmFlag=").append(qcQmFlag);
        sb.append(", deposit=").append(deposit);
        sb.append(", currInvest=").append(currInvest);
        sb.append(", credInvest=").append(credInvest);
        sb.append(", bondInvest=").append(bondInvest);
        sb.append(", feibiaoInvest=").append(feibiaoInvest);
        sb.append(", quanyiInvest=").append(quanyiInvest);
        sb.append(", derivaInvest=").append(derivaInvest);
        sb.append(", otherInvest=").append(otherInvest);
        sb.append(", interAccr=").append(interAccr);
        sb.append(", inFund=").append(inFund);
        sb.append(", huigouFund=").append(huigouFund);
        sb.append(", derivaDebt=").append(derivaDebt);
        sb.append(", accrProfit=").append(accrProfit);
        sb.append(", capitalShouyi=").append(capitalShouyi);
        sb.append(", capitalJingzhi=").append(capitalJingzhi);
        sb.append(", accrIncome=").append(accrIncome);
        sb.append(", taxPay=").append(taxPay);
        sb.append(", yujiFuzhai=").append(yujiFuzhai);
        sb.append(", otherPay=").append(otherPay);
        sb.append(", undisProfit=").append(undisProfit);
        sb.append(", remark=").append(remark);
        sb.append(", busiDate=").append(busiDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}