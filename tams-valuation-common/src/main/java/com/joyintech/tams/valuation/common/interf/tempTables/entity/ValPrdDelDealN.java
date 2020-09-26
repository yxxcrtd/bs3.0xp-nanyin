package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdDelDealN extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 成交编号
     */
    private String tradeNo;

    /**
     * 分红方式
     */
    private String bonusMode;

    /**
     * 分红方案
     */
    private String bonusPlanId;

    /**
     * 除权日
     */
    private Date delDate;

    /**
     * 分红日
     */
    private Date settDate;

    /**
     * 单位分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitBonusAmt;

    /**
     * 分红总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusTotalAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

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
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 成交编号
     * @return 成交编号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置 成交编号
     * @param tradeNo 成交编号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    /**
     * 取得 分红方式
     * @return 分红方式
     */
    public String getBonusMode() {
        return bonusMode;
    }

    /**
     * 设置 分红方式
     * @param bonusMode 分红方式
     */
    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode == null ? null : bonusMode.trim();
    }

    /**
     * 取得 分红方案
     * @return 分红方案
     */
    public String getBonusPlanId() {
        return bonusPlanId;
    }

    /**
     * 设置 分红方案
     * @param bonusPlanId 分红方案
     */
    public void setBonusPlanId(String bonusPlanId) {
        this.bonusPlanId = bonusPlanId == null ? null : bonusPlanId.trim();
    }

    /**
     * 取得 除权日
     * @return 除权日
     */
    public Date getDelDate() {
        return delDate;
    }

    /**
     * 设置 除权日
     * @param delDate 除权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    /**
     * 取得 分红日
     * @return 分红日
     */
    public Date getSettDate() {
        return settDate;
    }

    /**
     * 设置 分红日
     * @param settDate 分红日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }

    /**
     * 取得 单位分红金额
     * @return 单位分红金额
     */
    public BigDecimal getUnitBonusAmt() {
        return unitBonusAmt;
    }

    /**
     * 设置 单位分红金额
     * @param unitBonusAmt 单位分红金额
     */
    public void setUnitBonusAmt(BigDecimal unitBonusAmt) {
        this.unitBonusAmt = unitBonusAmt;
    }

    /**
     * 取得 分红总金额
     * @return 分红总金额
     */
    public BigDecimal getBonusTotalAmt() {
        return bonusTotalAmt;
    }

    /**
     * 设置 分红总金额
     * @param bonusTotalAmt 分红总金额
     */
    public void setBonusTotalAmt(BigDecimal bonusTotalAmt) {
        this.bonusTotalAmt = bonusTotalAmt;
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
     * 取得 有无清算指令
     * @return 有无清算指令
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 有无清算指令
     * @param isClean 有无清算指令
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean == null ? null : isClean.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 产品起息日
     * @return 产品起息日
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 产品起息日
     * @param prodVdate 产品起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }
}