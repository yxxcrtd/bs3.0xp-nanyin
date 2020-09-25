package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BidOrder extends BaseEntity {
    /**
     * 投标编号
     */
    private String bidId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 投标日期
     */
    private Date bidDate;

    /**
     * 缴款方式，一级分销、一级缴款、二级交易
     */
    private String payType;

    /**
     * 缴款日
     */
    private Date payDate;

    /**
     * 是否中标
     */
    private String isWin;

    /**
     * 中标券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal winPrinAmt;

    /**
     * 中标标位
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal winPrice;

    /**
     * 中标方式，单标位、多标位
     */
    private String winType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 投标编号
     * @return 投标编号
     */
    public String getBidId() {
        return bidId;
    }

    /**
     * 设置 投标编号
     * @param bidId 投标编号
     */
    public void setBidId(String bidId) {
        this.bidId = bidId == null ? null : bidId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 投标日期
     * @return 投标日期
     */
    public Date getBidDate() {
        return bidDate;
    }

    /**
     * 设置 投标日期
     * @param bidDate 投标日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    /**
     * 取得 缴款方式，一级分销、一级缴款、二级交易
     * @return 缴款方式，一级分销、一级缴款、二级交易
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 缴款方式，一级分销、一级缴款、二级交易
     * @param payType 缴款方式，一级分销、一级缴款、二级交易
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    /**
     * 取得 缴款日
     * @return 缴款日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 缴款日
     * @param payDate 缴款日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 是否中标
     * @return 是否中标
     */
    public String getIsWin() {
        return isWin;
    }

    /**
     * 设置 是否中标
     * @param isWin 是否中标
     */
    public void setIsWin(String isWin) {
        this.isWin = isWin == null ? null : isWin.trim();
    }

    /**
     * 取得 中标券面总额
     * @return 中标券面总额
     */
    public BigDecimal getWinPrinAmt() {
        return winPrinAmt;
    }

    /**
     * 设置 中标券面总额
     * @param winPrinAmt 中标券面总额
     */
    public void setWinPrinAmt(BigDecimal winPrinAmt) {
        this.winPrinAmt = winPrinAmt;
    }

    /**
     * 取得 中标标位
     * @return 中标标位
     */
    public BigDecimal getWinPrice() {
        return winPrice;
    }

    /**
     * 设置 中标标位
     * @param winPrice 中标标位
     */
    public void setWinPrice(BigDecimal winPrice) {
        this.winPrice = winPrice;
    }

    /**
     * 取得 中标方式，单标位、多标位
     * @return 中标方式，单标位、多标位
     */
    public String getWinType() {
        return winType;
    }

    /**
     * 设置 中标方式，单标位、多标位
     * @param winType 中标方式，单标位、多标位
     */
    public void setWinType(String winType) {
        this.winType = winType == null ? null : winType.trim();
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
}