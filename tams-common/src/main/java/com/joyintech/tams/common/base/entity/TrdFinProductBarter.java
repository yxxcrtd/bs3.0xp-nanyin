package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TrdFinProductBarter extends TrdFinProductBarterKey {
    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 单位净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 交易本金，债券券面等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 投资目的
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 单位净价
     * @return 单位净价
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 单位净价
     * @param unitCprice 单位净价
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 取得 交易本金，债券券面等
     * @return 交易本金，债券券面等
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 交易本金，债券券面等
     * @param prinAmt 交易本金，债券券面等
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 取得 交易份额，债券数量等
     * @return 交易份额，债券数量等
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额，债券数量等
     * @param shareAmt 交易份额，债券数量等
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 投资目的
     * @param intAmt 投资目的
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }
}