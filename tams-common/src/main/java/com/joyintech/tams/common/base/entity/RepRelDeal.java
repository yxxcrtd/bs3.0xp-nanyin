package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepRelDeal extends BaseEntity {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 产品主代码
     */
    private String prodId;

    /**
     * 日期
     */
    private Date pubDate;

    /**
     * 投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditorAmt;

    /**
     * 投资于关联方承销的证券交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bondAmt;

    /**
     * 与关联方作为交易对手开展的投融资业务交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investAmt;

    /**
     * 投资关联方作为管理人的资产管理产品交易金额
     */
    private String assetAmt;

    /**
     * 支付关联方托管费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trustFee;

    /**
     * 支付关联方代销费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sellFee;

    /**
     * 发生其他关联交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal otherFee;

    /**
     * 生效日期
     */
    private Date effdate;

    /**
     * 生效截止日期
     */
    private Date effdelidate;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 主键
     * @param uuid 主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 产品主代码
     * @return 产品主代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品主代码
     * @param prodId 产品主代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getPubDate() {
        return pubDate;
    }

    /**
     * 设置 日期
     * @param pubDate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 取得 投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额
     * @return 投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额
     */
    public BigDecimal getCreditorAmt() {
        return creditorAmt;
    }

    /**
     * 设置 投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额
     * @param creditorAmt 投资于关联方发行的证券、关联方作为融资人的非标准化债权类资产业务交易金额
     */
    public void setCreditorAmt(BigDecimal creditorAmt) {
        this.creditorAmt = creditorAmt;
    }

    /**
     * 取得 投资于关联方承销的证券交易金额
     * @return 投资于关联方承销的证券交易金额
     */
    public BigDecimal getBondAmt() {
        return bondAmt;
    }

    /**
     * 设置 投资于关联方承销的证券交易金额
     * @param bondAmt 投资于关联方承销的证券交易金额
     */
    public void setBondAmt(BigDecimal bondAmt) {
        this.bondAmt = bondAmt;
    }

    /**
     * 取得 与关联方作为交易对手开展的投融资业务交易金额
     * @return 与关联方作为交易对手开展的投融资业务交易金额
     */
    public BigDecimal getInvestAmt() {
        return investAmt;
    }

    /**
     * 设置 与关联方作为交易对手开展的投融资业务交易金额
     * @param investAmt 与关联方作为交易对手开展的投融资业务交易金额
     */
    public void setInvestAmt(BigDecimal investAmt) {
        this.investAmt = investAmt;
    }

    /**
     * 取得 投资关联方作为管理人的资产管理产品交易金额
     * @return 投资关联方作为管理人的资产管理产品交易金额
     */
    public String getAssetAmt() {
        return assetAmt;
    }

    /**
     * 设置 投资关联方作为管理人的资产管理产品交易金额
     * @param assetAmt 投资关联方作为管理人的资产管理产品交易金额
     */
    public void setAssetAmt(String assetAmt) {
        this.assetAmt = assetAmt == null ? null : assetAmt.trim();
    }

    /**
     * 取得 支付关联方托管费
     * @return 支付关联方托管费
     */
    public BigDecimal getTrustFee() {
        return trustFee;
    }

    /**
     * 设置 支付关联方托管费
     * @param trustFee 支付关联方托管费
     */
    public void setTrustFee(BigDecimal trustFee) {
        this.trustFee = trustFee;
    }

    /**
     * 取得 支付关联方代销费
     * @return 支付关联方代销费
     */
    public BigDecimal getSellFee() {
        return sellFee;
    }

    /**
     * 设置 支付关联方代销费
     * @param sellFee 支付关联方代销费
     */
    public void setSellFee(BigDecimal sellFee) {
        this.sellFee = sellFee;
    }

    /**
     * 取得 发生其他关联交易金额
     * @return 发生其他关联交易金额
     */
    public BigDecimal getOtherFee() {
        return otherFee;
    }

    /**
     * 设置 发生其他关联交易金额
     * @param otherFee 发生其他关联交易金额
     */
    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffdate() {
        return effdate;
    }

    /**
     * 设置 生效日期
     * @param effdate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffdate(Date effdate) {
        this.effdate = effdate;
    }

    /**
     * 取得 生效截止日期
     * @return 生效截止日期
     */
    public Date getEffdelidate() {
        return effdelidate;
    }

    /**
     * 设置 生效截止日期
     * @param effdelidate 生效截止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffdelidate(Date effdelidate) {
        this.effdelidate = effdelidate;
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * 设置 错误信息
     * @param errormsg 错误信息
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg == null ? null : errormsg.trim();
    }
}