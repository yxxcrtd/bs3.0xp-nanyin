package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfAptmtBuyRedemInfo extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 现金流日期
     */
    private String cdate;

    /**
     * 募集/预约申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyAmt;

    /**
     * 募集/预约申购份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyLot;

    /**
     * 预约赎回份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redemLot;

    /**
     * 预约赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redemAmt;

    /**
     * 净申赎金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netBuyRedemAmt;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 现金流日期
     * @return 现金流日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 现金流日期
     * @param cdate 现金流日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    /**
     * 取得 募集/预约申购金额
     * @return 募集/预约申购金额
     */
    public BigDecimal getBuyAmt() {
        return buyAmt;
    }

    /**
     * 设置 募集/预约申购金额
     * @param buyAmt 募集/预约申购金额
     */
    public void setBuyAmt(BigDecimal buyAmt) {
        this.buyAmt = buyAmt;
    }

    /**
     * 取得 募集/预约申购份额
     * @return 募集/预约申购份额
     */
    public BigDecimal getBuyLot() {
        return buyLot;
    }

    /**
     * 设置 募集/预约申购份额
     * @param buyLot 募集/预约申购份额
     */
    public void setBuyLot(BigDecimal buyLot) {
        this.buyLot = buyLot;
    }

    /**
     * 取得 预约赎回份额
     * @return 预约赎回份额
     */
    public BigDecimal getRedemLot() {
        return redemLot;
    }

    /**
     * 设置 预约赎回份额
     * @param redemLot 预约赎回份额
     */
    public void setRedemLot(BigDecimal redemLot) {
        this.redemLot = redemLot;
    }

    /**
     * 取得 预约赎回金额
     * @return 预约赎回金额
     */
    public BigDecimal getRedemAmt() {
        return redemAmt;
    }

    /**
     * 设置 预约赎回金额
     * @param redemAmt 预约赎回金额
     */
    public void setRedemAmt(BigDecimal redemAmt) {
        this.redemAmt = redemAmt;
    }

    /**
     * 取得 净申赎金额
     * @return 净申赎金额
     */
    public BigDecimal getNetBuyRedemAmt() {
        return netBuyRedemAmt;
    }

    /**
     * 设置 净申赎金额
     * @param netBuyRedemAmt 净申赎金额
     */
    public void setNetBuyRedemAmt(BigDecimal netBuyRedemAmt) {
        this.netBuyRedemAmt = netBuyRedemAmt;
    }
}