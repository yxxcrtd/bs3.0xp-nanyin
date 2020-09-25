package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbEqudetSub extends ChbEqudetSubKey {
    /**
     * 产品名称
     */
    private String accountName;

    /**
     * 产品代码
     */
    private String account;

    /**
     * 权益确认日期
     */
    private Date equityConfDate;

    /**
     * 购买地
     */
    private String buyPlace;

    /**
     * 业务种类
     */
    private String busiType;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 银行资金账号
     */
    private String fundAcc;

    /**
     * 银行资金账号开户行
     */
    private String fundAccBank;

    /**
     * 是否能代销
     */
    private String isConsign;

    /**
     * 数据类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 报送截止日
     */
    private Date submitDeadline;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 折算人民币金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal converCnyAmount;

    /**
     * 份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shares;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置 产品名称
     * @param accountName 产品名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 产品代码
     * @param account 产品代码
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 权益确认日期
     * @return 权益确认日期
     */
    public Date getEquityConfDate() {
        return equityConfDate;
    }

    /**
     * 设置 权益确认日期
     * @param equityConfDate 权益确认日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEquityConfDate(Date equityConfDate) {
        this.equityConfDate = equityConfDate;
    }

    /**
     * 取得 购买地
     * @return 购买地
     */
    public String getBuyPlace() {
        return buyPlace;
    }

    /**
     * 设置 购买地
     * @param buyPlace 购买地
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace == null ? null : buyPlace.trim();
    }

    /**
     * 取得 业务种类
     * @return 业务种类
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务种类
     * @param busiType 业务种类
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 渠道
     * @return 渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置 渠道
     * @param channel 渠道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * 取得 银行资金账号
     * @return 银行资金账号
     */
    public String getFundAcc() {
        return fundAcc;
    }

    /**
     * 设置 银行资金账号
     * @param fundAcc 银行资金账号
     */
    public void setFundAcc(String fundAcc) {
        this.fundAcc = fundAcc == null ? null : fundAcc.trim();
    }

    /**
     * 取得 银行资金账号开户行
     * @return 银行资金账号开户行
     */
    public String getFundAccBank() {
        return fundAccBank;
    }

    /**
     * 设置 银行资金账号开户行
     * @param fundAccBank 银行资金账号开户行
     */
    public void setFundAccBank(String fundAccBank) {
        this.fundAccBank = fundAccBank == null ? null : fundAccBank.trim();
    }

    /**
     * 取得 是否能代销
     * @return 是否能代销
     */
    public String getIsConsign() {
        return isConsign;
    }

    /**
     * 设置 是否能代销
     * @param isConsign 是否能代销
     */
    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign == null ? null : isConsign.trim();
    }

    /**
     * 取得 数据类型
     * @return 数据类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 数据类型
     * @param type 数据类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送日期
     * @param submitTime 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 报送截止日
     * @return 报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 报送截止日
     * @param submitDeadline 报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 取得 中债报送截止日
     * @return 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 设置 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 取得 报送流水号
     * @return 报送流水号
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 报送流水号
     * @param sumitSeq 报送流水号
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
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
     * 取得 折算人民币金额（元）
     * @return 折算人民币金额（元）
     */
    public BigDecimal getConverCnyAmount() {
        return converCnyAmount;
    }

    /**
     * 设置 折算人民币金额（元）
     * @param converCnyAmount 折算人民币金额（元）
     */
    public void setConverCnyAmount(BigDecimal converCnyAmount) {
        this.converCnyAmount = converCnyAmount;
    }

    /**
     * 取得 份额
     * @return 份额
     */
    public BigDecimal getShares() {
        return shares;
    }

    /**
     * 设置 份额
     * @param shares 份额
     */
    public void setShares(BigDecimal shares) {
        this.shares = shares;
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
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRegCode() {
        return regCode;
    }

    /**
     * 设置 产品登记编码
     * @param regCode 产品登记编码
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }
}