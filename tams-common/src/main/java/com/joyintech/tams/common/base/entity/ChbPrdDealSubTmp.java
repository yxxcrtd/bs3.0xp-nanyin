package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdDealSubTmp extends BaseEntity {
    /**
     * 理财产品代码
     */
    private String prodId;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 净值币种
     */
    private String valueCcy;

    /**
     * 银行实现收益(元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bankImpInt;

    /**
     * 业务起始日
     */
    private Date vdate;

    /**
     * 业务结束日
     */
    private Date mdate;

    /**
     * 产品余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balanceAmt;

    /**
     * 币种及申购兑付金额
     */
    private String ccyPurchase;

    /**
     * 备注
     */
    private String remark;

    /**
     * 初始净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal originalValue;

    /**
     * 产品净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prdValue;

    /**
     * 累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalValue;

    /**
     * 人民币初始净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cnyOrlValue;

    /**
     * 折算人民币净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cnyPrdValue;

    /**
     * 人民币累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cnyTalValue;

    /**
     * 累计申购份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal purchaseQty;

    /**
     * 累计赎回份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redeemQty;

    /**
     * 产品份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prdQty;

    /**
     * 实现收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal reaYield;

    /**
     * 最新预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal newExpYield;

    /**
     * 每万份份额分红
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmtWan;

    /**
     * 每万份现金分红
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashAmtWan;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据日期
     */
    private Date rowDate;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 理财产品代码
     * @return 理财产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品代码
     * @param prodId 理财产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
    }

    /**
     * 取得 净值币种
     * @return 净值币种
     */
    public String getValueCcy() {
        return valueCcy;
    }

    /**
     * 设置 净值币种
     * @param valueCcy 净值币种
     */
    public void setValueCcy(String valueCcy) {
        this.valueCcy = valueCcy == null ? null : valueCcy.trim();
    }

    /**
     * 取得 银行实现收益(元)
     * @return 银行实现收益(元)
     */
    public BigDecimal getBankImpInt() {
        return bankImpInt;
    }

    /**
     * 设置 银行实现收益(元)
     * @param bankImpInt 银行实现收益(元)
     */
    public void setBankImpInt(BigDecimal bankImpInt) {
        this.bankImpInt = bankImpInt;
    }

    /**
     * 取得 业务起始日
     * @return 业务起始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 业务起始日
     * @param vdate 业务起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 业务结束日
     * @return 业务结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 业务结束日
     * @param mdate 业务结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 产品余额
     * @return 产品余额
     */
    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }

    /**
     * 设置 产品余额
     * @param balanceAmt 产品余额
     */
    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    /**
     * 取得 币种及申购兑付金额
     * @return 币种及申购兑付金额
     */
    public String getCcyPurchase() {
        return ccyPurchase;
    }

    /**
     * 设置 币种及申购兑付金额
     * @param ccyPurchase 币种及申购兑付金额
     */
    public void setCcyPurchase(String ccyPurchase) {
        this.ccyPurchase = ccyPurchase == null ? null : ccyPurchase.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 初始净值
     * @return 初始净值
     */
    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    /**
     * 设置 初始净值
     * @param originalValue 初始净值
     */
    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    /**
     * 取得 产品净值
     * @return 产品净值
     */
    public BigDecimal getPrdValue() {
        return prdValue;
    }

    /**
     * 设置 产品净值
     * @param prdValue 产品净值
     */
    public void setPrdValue(BigDecimal prdValue) {
        this.prdValue = prdValue;
    }

    /**
     * 取得 累计净值
     * @return 累计净值
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * 设置 累计净值
     * @param totalValue 累计净值
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * 取得 人民币初始净值
     * @return 人民币初始净值
     */
    public BigDecimal getCnyOrlValue() {
        return cnyOrlValue;
    }

    /**
     * 设置 人民币初始净值
     * @param cnyOrlValue 人民币初始净值
     */
    public void setCnyOrlValue(BigDecimal cnyOrlValue) {
        this.cnyOrlValue = cnyOrlValue;
    }

    /**
     * 取得 折算人民币净值
     * @return 折算人民币净值
     */
    public BigDecimal getCnyPrdValue() {
        return cnyPrdValue;
    }

    /**
     * 设置 折算人民币净值
     * @param cnyPrdValue 折算人民币净值
     */
    public void setCnyPrdValue(BigDecimal cnyPrdValue) {
        this.cnyPrdValue = cnyPrdValue;
    }

    /**
     * 取得 人民币累计净值
     * @return 人民币累计净值
     */
    public BigDecimal getCnyTalValue() {
        return cnyTalValue;
    }

    /**
     * 设置 人民币累计净值
     * @param cnyTalValue 人民币累计净值
     */
    public void setCnyTalValue(BigDecimal cnyTalValue) {
        this.cnyTalValue = cnyTalValue;
    }

    /**
     * 取得 累计申购份额
     * @return 累计申购份额
     */
    public BigDecimal getPurchaseQty() {
        return purchaseQty;
    }

    /**
     * 设置 累计申购份额
     * @param purchaseQty 累计申购份额
     */
    public void setPurchaseQty(BigDecimal purchaseQty) {
        this.purchaseQty = purchaseQty;
    }

    /**
     * 取得 累计赎回份额
     * @return 累计赎回份额
     */
    public BigDecimal getRedeemQty() {
        return redeemQty;
    }

    /**
     * 设置 累计赎回份额
     * @param redeemQty 累计赎回份额
     */
    public void setRedeemQty(BigDecimal redeemQty) {
        this.redeemQty = redeemQty;
    }

    /**
     * 取得 产品份额
     * @return 产品份额
     */
    public BigDecimal getPrdQty() {
        return prdQty;
    }

    /**
     * 设置 产品份额
     * @param prdQty 产品份额
     */
    public void setPrdQty(BigDecimal prdQty) {
        this.prdQty = prdQty;
    }

    /**
     * 取得 实现收益率
     * @return 实现收益率
     */
    public BigDecimal getReaYield() {
        return reaYield;
    }

    /**
     * 设置 实现收益率
     * @param reaYield 实现收益率
     */
    public void setReaYield(BigDecimal reaYield) {
        this.reaYield = reaYield;
    }

    /**
     * 取得 最新预期收益率
     * @return 最新预期收益率
     */
    public BigDecimal getNewExpYield() {
        return newExpYield;
    }

    /**
     * 设置 最新预期收益率
     * @param newExpYield 最新预期收益率
     */
    public void setNewExpYield(BigDecimal newExpYield) {
        this.newExpYield = newExpYield;
    }

    /**
     * 取得 每万份份额分红
     * @return 每万份份额分红
     */
    public BigDecimal getShareAmtWan() {
        return shareAmtWan;
    }

    /**
     * 设置 每万份份额分红
     * @param shareAmtWan 每万份份额分红
     */
    public void setShareAmtWan(BigDecimal shareAmtWan) {
        this.shareAmtWan = shareAmtWan;
    }

    /**
     * 取得 每万份现金分红
     * @return 每万份现金分红
     */
    public BigDecimal getCashAmtWan() {
        return cashAmtWan;
    }

    /**
     * 设置 每万份现金分红
     * @param cashAmtWan 每万份现金分红
     */
    public void setCashAmtWan(BigDecimal cashAmtWan) {
        this.cashAmtWan = cashAmtWan;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRowDate() {
        return rowDate;
    }

    /**
     * 设置 数据日期
     * @param rowDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRowDate(Date rowDate) {
        this.rowDate = rowDate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}