package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdBonusDeal extends BaseEntity {
    /**
     * 主键
     */
    private String seqno;

    /**
     * 上游系统交易编码
     */
    private String tradeId;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 权益登记日/计息结束日
     */
    private Date regDate;

    /**
     * 确认日
     */
    private Date settdateDate;

    /**
     * 现金流发生日
     */
    private Date payDate;

    /**
     * 权益登记日份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal regTradeQty;

    /**
     * 红利再投份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeQty;

    /**
     * 红利再投金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 现金分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 主键
     * @param seqno 主键
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 上游系统交易编码
     * @return 上游系统交易编码
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 上游系统交易编码
     * @param tradeId 上游系统交易编码
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 父产品代码
     * @return 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
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
     * 取得 交易类型
     * @return 交易类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 交易类型
     * @param tradeType 交易类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 权益登记日/计息结束日
     * @return 权益登记日/计息结束日
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 设置 权益登记日/计息结束日
     * @param regDate 权益登记日/计息结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * 取得 确认日
     * @return 确认日
     */
    public Date getSettdateDate() {
        return settdateDate;
    }

    /**
     * 设置 确认日
     * @param settdateDate 确认日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettdateDate(Date settdateDate) {
        this.settdateDate = settdateDate;
    }

    /**
     * 取得 现金流发生日
     * @return 现金流发生日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 现金流发生日
     * @param payDate 现金流发生日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 权益登记日份额
     * @return 权益登记日份额
     */
    public BigDecimal getRegTradeQty() {
        return regTradeQty;
    }

    /**
     * 设置 权益登记日份额
     * @param regTradeQty 权益登记日份额
     */
    public void setRegTradeQty(BigDecimal regTradeQty) {
        this.regTradeQty = regTradeQty;
    }

    /**
     * 取得 红利再投份额
     * @return 红利再投份额
     */
    public BigDecimal getTradeQty() {
        return tradeQty;
    }

    /**
     * 设置 红利再投份额
     * @param tradeQty 红利再投份额
     */
    public void setTradeQty(BigDecimal tradeQty) {
        this.tradeQty = tradeQty;
    }

    /**
     * 取得 红利再投金额
     * @return 红利再投金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 红利再投金额
     * @param tradeAmt 红利再投金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 现金分红金额
     * @return 现金分红金额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 现金分红金额
     * @param intAmt 现金分红金额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 撤销日
     * @return 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 设置 撤销日
     * @param revDate 撤销日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
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
}