package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdPositionTotal extends BaseEntity {
    /**
     * 生成日
     */
    private Date cdate;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 新增金额(赎回、提前到期为负，其他为正)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdaddAmt;

    /**
     * 新增成本(赎回、提前到期为负，其他为正)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdaddCost;

    /**
     * 今日新增收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdaddIntamt;

    /**
     * 累计收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalIntamt;

    /**
     * 实际募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseamt;

    /**
     * 兑付客户金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal custamt;

    /**
     * 兑付客户份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal custqty;

    /**
     * 新增份额(赎回、提前到期为负，其他为正)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdaddQty;

    /**
     * 剩余成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balanceAmt;

    /**
     * 剩余份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balanceQty;

    /**
     * 取得 生成日
     * @return 生成日
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 生成日
     * @param cdate 生成日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

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
     * 取得 新增金额(赎回、提前到期为负，其他为正)
     * @return 新增金额(赎回、提前到期为负，其他为正)
     */
    public BigDecimal getTdaddAmt() {
        return tdaddAmt;
    }

    /**
     * 设置 新增金额(赎回、提前到期为负，其他为正)
     * @param tdaddAmt 新增金额(赎回、提前到期为负，其他为正)
     */
    public void setTdaddAmt(BigDecimal tdaddAmt) {
        this.tdaddAmt = tdaddAmt;
    }

    /**
     * 取得 新增成本(赎回、提前到期为负，其他为正)
     * @return 新增成本(赎回、提前到期为负，其他为正)
     */
    public BigDecimal getTdaddCost() {
        return tdaddCost;
    }

    /**
     * 设置 新增成本(赎回、提前到期为负，其他为正)
     * @param tdaddCost 新增成本(赎回、提前到期为负，其他为正)
     */
    public void setTdaddCost(BigDecimal tdaddCost) {
        this.tdaddCost = tdaddCost;
    }

    /**
     * 取得 今日新增收益
     * @return 今日新增收益
     */
    public BigDecimal getTdaddIntamt() {
        return tdaddIntamt;
    }

    /**
     * 设置 今日新增收益
     * @param tdaddIntamt 今日新增收益
     */
    public void setTdaddIntamt(BigDecimal tdaddIntamt) {
        this.tdaddIntamt = tdaddIntamt;
    }

    /**
     * 取得 累计收益
     * @return 累计收益
     */
    public BigDecimal getTotalIntamt() {
        return totalIntamt;
    }

    /**
     * 设置 累计收益
     * @param totalIntamt 累计收益
     */
    public void setTotalIntamt(BigDecimal totalIntamt) {
        this.totalIntamt = totalIntamt;
    }

    /**
     * 取得 实际募集金额
     * @return 实际募集金额
     */
    public BigDecimal getRaiseamt() {
        return raiseamt;
    }

    /**
     * 设置 实际募集金额
     * @param raiseamt 实际募集金额
     */
    public void setRaiseamt(BigDecimal raiseamt) {
        this.raiseamt = raiseamt;
    }

    /**
     * 取得 兑付客户金额
     * @return 兑付客户金额
     */
    public BigDecimal getCustamt() {
        return custamt;
    }

    /**
     * 设置 兑付客户金额
     * @param custamt 兑付客户金额
     */
    public void setCustamt(BigDecimal custamt) {
        this.custamt = custamt;
    }

    /**
     * 取得 兑付客户份额
     * @return 兑付客户份额
     */
    public BigDecimal getCustqty() {
        return custqty;
    }

    /**
     * 设置 兑付客户份额
     * @param custqty 兑付客户份额
     */
    public void setCustqty(BigDecimal custqty) {
        this.custqty = custqty;
    }

    /**
     * 取得 新增份额(赎回、提前到期为负，其他为正)
     * @return 新增份额(赎回、提前到期为负，其他为正)
     */
    public BigDecimal getTdaddQty() {
        return tdaddQty;
    }

    /**
     * 设置 新增份额(赎回、提前到期为负，其他为正)
     * @param tdaddQty 新增份额(赎回、提前到期为负，其他为正)
     */
    public void setTdaddQty(BigDecimal tdaddQty) {
        this.tdaddQty = tdaddQty;
    }

    /**
     * 取得 剩余成本
     * @return 剩余成本
     */
    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }

    /**
     * 设置 剩余成本
     * @param balanceAmt 剩余成本
     */
    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    /**
     * 取得 剩余份额
     * @return 剩余份额
     */
    public BigDecimal getBalanceQty() {
        return balanceQty;
    }

    /**
     * 设置 剩余份额
     * @param balanceQty 剩余份额
     */
    public void setBalanceQty(BigDecimal balanceQty) {
        this.balanceQty = balanceQty;
    }
}