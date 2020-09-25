package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfLc0009 extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 产品简称
     */
    private String shortname;

    /**
     * 产品名称
     */
    private String prodname;

    /**
     * 产品处理模式
     */
    private String prodmode;

    /**
     * 赎回受理模式
     */
    private String redemode;

    /**
     * 管理模式
     */
    private String incomemode;

    /**
     * 分销产品代码
     */
    private String proxysaleprodid;

    /**
     * 分行
     */
    private String branch;

    /**
     * 产品成立日
     */
    private String vdate;

    /**
     * 产品到期日
     */
    private String mdate;

    /**
     * 产品赎回日
     */
    private String rdate;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 客户账户
     */
    private String accountid;

    /**
     * 赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redeamt;

    /**
     * 赎回收益率（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 赎回收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal reinterest;

    /**
     * 币种
     */
    private String subccy;

    /**
     * 备注
     */
    private String remark;

    /**
     * 日期
     */
    private String cdate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 产品简称
     * @return 产品简称
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * 设置 产品简称
     * @param shortname 产品简称
     */
    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdname() {
        return prodname;
    }

    /**
     * 设置 产品名称
     * @param prodname 产品名称
     */
    public void setProdname(String prodname) {
        this.prodname = prodname == null ? null : prodname.trim();
    }

    /**
     * 取得 产品处理模式
     * @return 产品处理模式
     */
    public String getProdmode() {
        return prodmode;
    }

    /**
     * 设置 产品处理模式
     * @param prodmode 产品处理模式
     */
    public void setProdmode(String prodmode) {
        this.prodmode = prodmode == null ? null : prodmode.trim();
    }

    /**
     * 取得 赎回受理模式
     * @return 赎回受理模式
     */
    public String getRedemode() {
        return redemode;
    }

    /**
     * 设置 赎回受理模式
     * @param redemode 赎回受理模式
     */
    public void setRedemode(String redemode) {
        this.redemode = redemode == null ? null : redemode.trim();
    }

    /**
     * 取得 管理模式
     * @return 管理模式
     */
    public String getIncomemode() {
        return incomemode;
    }

    /**
     * 设置 管理模式
     * @param incomemode 管理模式
     */
    public void setIncomemode(String incomemode) {
        this.incomemode = incomemode == null ? null : incomemode.trim();
    }

    /**
     * 取得 分销产品代码
     * @return 分销产品代码
     */
    public String getProxysaleprodid() {
        return proxysaleprodid;
    }

    /**
     * 设置 分销产品代码
     * @param proxysaleprodid 分销产品代码
     */
    public void setProxysaleprodid(String proxysaleprodid) {
        this.proxysaleprodid = proxysaleprodid == null ? null : proxysaleprodid.trim();
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
     * 取得 产品成立日
     * @return 产品成立日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 产品成立日
     * @param vdate 产品成立日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate == null ? null : vdate.trim();
    }

    /**
     * 取得 产品到期日
     * @return 产品到期日
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 产品到期日
     * @param mdate 产品到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate == null ? null : mdate.trim();
    }

    /**
     * 取得 产品赎回日
     * @return 产品赎回日
     */
    public String getRdate() {
        return rdate;
    }

    /**
     * 设置 产品赎回日
     * @param rdate 产品赎回日
     */
    public void setRdate(String rdate) {
        this.rdate = rdate == null ? null : rdate.trim();
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 客户账户
     * @return 客户账户
     */
    public String getAccountid() {
        return accountid;
    }

    /**
     * 设置 客户账户
     * @param accountid 客户账户
     */
    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    /**
     * 取得 赎回金额
     * @return 赎回金额
     */
    public BigDecimal getRedeamt() {
        return redeamt;
    }

    /**
     * 设置 赎回金额
     * @param redeamt 赎回金额
     */
    public void setRedeamt(BigDecimal redeamt) {
        this.redeamt = redeamt;
    }

    /**
     * 取得 赎回收益率（年化）
     * @return 赎回收益率（年化）
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 赎回收益率（年化）
     * @param yield 赎回收益率（年化）
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 赎回收益
     * @return 赎回收益
     */
    public BigDecimal getReinterest() {
        return reinterest;
    }

    /**
     * 设置 赎回收益
     * @param reinterest 赎回收益
     */
    public void setReinterest(BigDecimal reinterest) {
        this.reinterest = reinterest;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getSubccy() {
        return subccy;
    }

    /**
     * 设置 币种
     * @param subccy 币种
     */
    public void setSubccy(String subccy) {
        this.subccy = subccy == null ? null : subccy.trim();
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
     * 取得 日期
     * @return 日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }
}