package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ChbPeriodicBonusDeal
 * @Description: 定期报告产品分红明细表
 * @Author WanShiWei
 * @Date 2021-05-07
 * @Version V1.0
 **/
public class ChbPeriodicBonusDeal extends BaseEntity {

    private static final long serialVersionUID = 109399923067388230L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 权益登记日/计息结束日
     */
    private Date regDate;

    /**
     * 红利再投金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 权益登记日份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal regTradeQty;

    /**
     * 现金分红金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getRegTradeQty() {
        return regTradeQty;
    }

    public void setRegTradeQty(BigDecimal regTradeQty) {
        this.regTradeQty = regTradeQty;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }
}
