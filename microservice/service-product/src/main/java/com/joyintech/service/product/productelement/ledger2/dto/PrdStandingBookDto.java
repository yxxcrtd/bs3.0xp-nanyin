package com.joyintech.service.product.productelement.ledger2.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class PrdStandingBookDto extends BaseEntity {
    private String finprodId;  //    金融产品代码
    private String cdate;       //    日期
    private String bonusMode;  //    分红方式
    private String intpayPeriod;  //    付息频率
    private Date rate;  //    预期收益率/业绩比较基准
    private String floatFeeProportion;  //    浮动管理费分成比例（%）
    private String fixFeeProportion;  //    固定管理费（%）
    private String trustFeeProportion;  //    托管费率（%）
    private String actYield;  //    客户实际收益率（%）
    private String firstPrinAmt;  //    首次认购总额（元）
    private String shareAmt;  //    存续份额（元）
    private String custProfit;  //    兑付客户收益（元）
    private String trustFee;  //    托管费（元）
    private String saleFee;  //    销售手续费（元）
    private String fixFee;  //    固定管理费（元）
    private String proxyFee;  //    代销费（元）
    private String addTax;  //    增值附加税费（元）
    private String floatFee;  //    浮动管理费（元）
    private String otherFee;  //    其他费用（元）
    private String actMidIncome;  //    实现中间业务收入（元）
    private String monthIssuePrinamt;  //    月发行产品金额（元）
    private String monthDuePrinamt;  //    月到期产品金额（元）
    private String monthIssueShareamt;  //    月发行产品份额
    private String monthDueShareamt;  //    月到期产品份额

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getBonusMode() {
        return bonusMode;
    }

    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode;
    }

    public String getIntpayPeriod() {
        return intpayPeriod;
    }

    public void setIntpayPeriod(String intpayPeriod) {
        this.intpayPeriod = intpayPeriod;
    }

    public Date getRate() {
        return rate;
    }

    public void setRate(Date rate) {
        this.rate = rate;
    }

    public String getFloatFeeProportion() {
        return floatFeeProportion;
    }

    public void setFloatFeeProportion(String floatFeeProportion) {
        this.floatFeeProportion = floatFeeProportion;
    }

    public String getFixFeeProportion() {
        return fixFeeProportion;
    }

    public void setFixFeeProportion(String fixFeeProportion) {
        this.fixFeeProportion = fixFeeProportion;
    }

    public String getTrustFeeProportion() {
        return trustFeeProportion;
    }

    public void setTrustFeeProportion(String trustFeeProportion) {
        this.trustFeeProportion = trustFeeProportion;
    }

    public String getActYield() {
        return actYield;
    }

    public void setActYield(String actYield) {
        this.actYield = actYield;
    }

    public String getFirstPrinAmt() {
        return firstPrinAmt;
    }

    public void setFirstPrinAmt(String firstPrinAmt) {
        this.firstPrinAmt = firstPrinAmt;
    }

    public String getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(String shareAmt) {
        this.shareAmt = shareAmt;
    }

    public String getCustProfit() {
        return custProfit;
    }

    public void setCustProfit(String custProfit) {
        this.custProfit = custProfit;
    }

    public String getTrustFee() {
        return trustFee;
    }

    public void setTrustFee(String trustFee) {
        this.trustFee = trustFee;
    }

    public String getSaleFee() {
        return saleFee;
    }

    public void setSaleFee(String saleFee) {
        this.saleFee = saleFee;
    }

    public String getFixFee() {
        return fixFee;
    }

    public void setFixFee(String fixFee) {
        this.fixFee = fixFee;
    }

    public String getProxyFee() {
        return proxyFee;
    }

    public void setProxyFee(String proxyFee) {
        this.proxyFee = proxyFee;
    }

    public String getAddTax() {
        return addTax;
    }

    public void setAddTax(String addTax) {
        this.addTax = addTax;
    }

    public String getFloatFee() {
        return floatFee;
    }

    public void setFloatFee(String floatFee) {
        this.floatFee = floatFee;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public String getActMidIncome() {
        return actMidIncome;
    }

    public void setActMidIncome(String actMidIncome) {
        this.actMidIncome = actMidIncome;
    }

    public String getMonthIssuePrinamt() {
        return monthIssuePrinamt;
    }

    public void setMonthIssuePrinamt(String monthIssuePrinamt) {
        this.monthIssuePrinamt = monthIssuePrinamt;
    }

    public String getMonthDuePrinamt() {
        return monthDuePrinamt;
    }

    public void setMonthDuePrinamt(String monthDuePrinamt) {
        this.monthDuePrinamt = monthDuePrinamt;
    }

    public String getMonthIssueShareamt() {
        return monthIssueShareamt;
    }

    public void setMonthIssueShareamt(String monthIssueShareamt) {
        this.monthIssueShareamt = monthIssueShareamt;
    }

    public String getMonthDueShareamt() {
        return monthDueShareamt;
    }

    public void setMonthDueShareamt(String monthDueShareamt) {
        this.monthDueShareamt = monthDueShareamt;
    }
}
