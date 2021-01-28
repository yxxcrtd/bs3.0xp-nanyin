package com.joyintech.tams.svreport.chb.monthreport.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import com.joyintech.framework.util.IDGenerator;

/**
 * 描述:CHB_MONTH_REPORT_DETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-24
 */
public class ChbMonthReportDetail {
    /**
     * 明细uuid
     */
    private String detailuuid;
    /**
     * 主表uuid
     */
    private String reportuuid;

    /**
     * 期数
     */
    private String termid;

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    private String reporttype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 本期产品只数
     */
    private Integer prodcounts;

    /**
     * 本期总募集金额
     */
    private BigDecimal prodamount;

    /**
     * 本期净募集金额
     */
    private BigDecimal prodnetamount;

    /**
     * 期末存续产品只数
     */
    private Integer durationprodcounts;

    /**
     * 期末余额
     */
    private BigDecimal balance;

    /**
     * 期末余额（穿透后）
     */
    private BigDecimal bottombalance;

    /**
     * 本期客户收益
     */
    private BigDecimal customerprofit;

    /**
     * 本期银行收益
     */
    private BigDecimal bankprofit;

    /**
     * 本期产品收益率
     */
    private BigDecimal prodyield;

    private Set<String> prodSet;
    
    private Set<String> durationProdSet;

    private Set<String> xgdurationprodcountsSet;

    /**
     * 本期兑付金额
     */
    private BigDecimal prodpayamount;


    /**
     * 合规新产品期末存续只数
     */
    private  Integer    xgdurationprodcounts;

    /**
     * 合规新产品期末余额
     */
    private   BigDecimal   xgbalance;

    /**
     * 合规新产品期末余额（穿透后）
     */
    private  BigDecimal     xgbottombalance;


    public BigDecimal getProdpayamount() {
        return prodpayamount;
    }

    public void setProdpayamount(BigDecimal prodpayamount) {
        this.prodpayamount = prodpayamount;
    }

    public Integer getXgdurationprodcounts() {
        return xgdurationprodcounts;
    }

    public void setXgdurationprodcounts(Integer xgdurationprodcounts) {
        this.xgdurationprodcounts = xgdurationprodcounts;
    }

    public BigDecimal getXgbalance() {
        return xgbalance;
    }

    public void setXgbalance(BigDecimal xgbalance) {
        this.xgbalance = xgbalance;
    }

    public BigDecimal getXgbottombalance() {
        return xgbottombalance;
    }

    public void setXgbottombalance(BigDecimal xgbottombalance) {
        this.xgbottombalance = xgbottombalance;
    }
    
    /**
     * null
     * @return DETAILUUID null
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * null
     * @param detailuuid null
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    public String getReportuuid() {
		return reportuuid;
	}

	public void setReportuuid(String reportuuid) {
		this.reportuuid = reportuuid;
	}

	/**
     * 期数
     * @return TERMID 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 期数
     * @param termid 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @return REPORTTYPE 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @param reporttype 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 属性代码
     * @return PROPERTYCODE 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 属性代码
     * @param propertycode 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 本期产品只数
     * @return PRODCOUNTS 本期产品只数
     */
    public Integer getProdcounts() {
        return prodcounts;
    }

    /**
     * 本期产品只数
     * @param prodcounts 本期产品只数
     */
    public void setProdcounts(Integer prodcounts) {
        this.prodcounts = prodcounts;
    }

    /**
     * 本期总募集金额
     * @return PRODAMOUNT 本期总募集金额
     */
    public BigDecimal getProdamount() {
        return prodamount;
    }

    /**
     * 本期总募集金额
     * @param prodamount 本期总募集金额
     */
    public void setProdamount(BigDecimal prodamount) {
        this.prodamount = prodamount;
    }

    /**
     * 本期净募集金额
     * @return PRODNETAMOUNT 本期净募集金额
     */
    public BigDecimal getProdnetamount() {
        return prodnetamount;
    }

    /**
     * 本期净募集金额
     * @param prodnetamount 本期净募集金额
     */
    public void setProdnetamount(BigDecimal prodnetamount) {
        this.prodnetamount = prodnetamount;
    }

    /**
     * 期末存续产品只数
     * @return DURATIONPRODCOUNTS 期末存续产品只数
     */
    public Integer getDurationprodcounts() {
        return durationprodcounts;
    }

    /**
     * 期末存续产品只数
     * @param durationprodcounts 期末存续产品只数
     */
    public void setDurationprodcounts(Integer durationprodcounts) {
        this.durationprodcounts = durationprodcounts;
    }

    /**
     * 期末余额
     * @return BALANCE 期末余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 期末余额
     * @param balance 期末余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 期末余额（穿透后）
     * @return BOTTOMBALANCE 期末余额（穿透后）
     */
    public BigDecimal getBottombalance() {
        return bottombalance;
    }

    /**
     * 期末余额（穿透后）
     * @param bottombalance 期末余额（穿透后）
     */
    public void setBottombalance(BigDecimal bottombalance) {
        this.bottombalance = bottombalance;
    }

    /**
     * 本期客户收益
     * @return CUSTOMERPROFIT 本期客户收益
     */
    public BigDecimal getCustomerprofit() {
        return customerprofit;
    }

    /**
     * 本期客户收益
     * @param customerprofit 本期客户收益
     */
    public void setCustomerprofit(BigDecimal customerprofit) {
        this.customerprofit = customerprofit;
    }

    /**
     * 本期银行收益
     * @return BANKPROFIT 本期银行收益
     */
    public BigDecimal getBankprofit() {
        return bankprofit;
    }

    /**
     * 本期银行收益
     * @param bankprofit 本期银行收益
     */
    public void setBankprofit(BigDecimal bankprofit) {
        this.bankprofit = bankprofit;
    }

    /**
     * 本期产品收益率
     * @return PRODYIELD 本期产品收益率
     */
    public BigDecimal getProdyield() {
        return prodyield;
    }

    /**
     * 本期产品收益率
     * @param prodyield 本期产品收益率
     */
    public void setProdyield(BigDecimal prodyield) {
        this.prodyield = prodyield;
    }
    
    public int calcProdcounts(String prodid) {
        prodSet.add(prodid);
        prodcounts = prodSet.size();
        return prodcounts.intValue();
    }
    
    public int calcDurationprodcounts(String prodid) {
        durationProdSet.add(prodid);
        xgdurationprodcounts = durationProdSet.size();
        return xgdurationprodcounts.intValue();
    }


    public int calcxgdurationprodcounts(String prodid) {
        xgdurationprodcountsSet.add(prodid);
        durationprodcounts = xgdurationprodcountsSet.size();
        return durationprodcounts.intValue();
    }

    public Set<String> getProdSet() {
        return prodSet;
    }
    
    public ChbMonthReportDetail() {
        detailuuid =IDGenerator.generate(36);
        termid="";
        reporttype="";
        propertycode="";
        prodcounts=0;
        prodamount=BigDecimal.ZERO;
        prodnetamount=BigDecimal.ZERO;
        durationprodcounts=0;
        balance=BigDecimal.ZERO;
        bottombalance=BigDecimal.ZERO;
        customerprofit=BigDecimal.ZERO;
        bankprofit=BigDecimal.ZERO;
        prodyield=BigDecimal.ZERO;
        prodSet = new TreeSet<String>();
        durationProdSet  = new TreeSet<String>();
        prodpayamount=BigDecimal.ZERO;
        xgdurationprodcounts=0;
        xgbalance=BigDecimal.ZERO;
        xgbottombalance=BigDecimal.ZERO;
        xgdurationprodcountsSet = new TreeSet<String>();
    }
}