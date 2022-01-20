package com.joyintech.background.component.uplink.interbank.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class IbuQuoteEtrRe extends IbuQuoteEtrReKey {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 交易本金(中债登)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbFpriceAmt;

    /**
     * 交易本金(上清所)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scFpriceAmt;

    /**
     * 交易手续费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealFeeAmt;

    /**
     * 结算服务费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settFeeAmt;

    /**
     * 分配方式
     */
    private String dipType;

    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 业务模式
     */
    private String invAim;

    /**
     * 本方券交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ourShareAmt;

    /**
     * 融入券交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lendinShareAmt;

    /**
     * 价格模式
     */
    private String priceModel;

    /**
     * 到期结算金额(元)
     */
    private BigDecimal mtradeAmt;

    /**
     * 折算券面(元)
     */
    private BigDecimal shareCtAmt;

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
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
     * 取得 全价总额
     * @return 全价总额
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 全价总额
     * @param fpriceAmt 全价总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
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
     * 取得 交易本金(中债登)
     * @return 交易本金(中债登)
     */
    public BigDecimal getCbFpriceAmt() {
        return cbFpriceAmt;
    }

    /**
     * 设置 交易本金(中债登)
     * @param cbFpriceAmt 交易本金(中债登)
     */
    public void setCbFpriceAmt(BigDecimal cbFpriceAmt) {
        this.cbFpriceAmt = cbFpriceAmt;
    }

    /**
     * 取得 交易本金(上清所)
     * @return 交易本金(上清所)
     */
    public BigDecimal getScFpriceAmt() {
        return scFpriceAmt;
    }

    /**
     * 设置 交易本金(上清所)
     * @param scFpriceAmt 交易本金(上清所)
     */
    public void setScFpriceAmt(BigDecimal scFpriceAmt) {
        this.scFpriceAmt = scFpriceAmt;
    }

    /**
     * 取得 交易手续费
     * @return 交易手续费
     */
    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    /**
     * 设置 交易手续费
     * @param dealFeeAmt 交易手续费
     */
    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    /**
     * 取得 结算服务费
     * @return 结算服务费
     */
    public BigDecimal getSettFeeAmt() {
        return settFeeAmt;
    }

    /**
     * 设置 结算服务费
     * @param settFeeAmt 结算服务费
     */
    public void setSettFeeAmt(BigDecimal settFeeAmt) {
        this.settFeeAmt = settFeeAmt;
    }

    /**
     * 取得 分配方式
     * @return 分配方式
     */
    public String getDipType() {
        return dipType;
    }

    /**
     * 设置 分配方式
     * @param dipType 分配方式
     */
    public void setDipType(String dipType) {
        this.dipType = dipType == null ? null : dipType.trim();
    }

    /**
     * 取得 净价总额
     * @return 净价总额
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 净价总额
     * @param cpriceAmt 净价总额
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 取得 应计利息
     * @return 应计利息
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 应计利息
     * @param intAmt 应计利息
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 业务模式
     * @return 业务模式
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 业务模式
     * @param invAim 业务模式
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 本方券交易份额
     * @return 本方券交易份额
     */
    public BigDecimal getOurShareAmt() {
        return ourShareAmt;
    }

    /**
     * 设置 本方券交易份额
     * @param ourShareAmt 本方券交易份额
     */
    public void setOurShareAmt(BigDecimal ourShareAmt) {
        this.ourShareAmt = ourShareAmt;
    }

    /**
     * 取得 融入券交易份额
     * @return 融入券交易份额
     */
    public BigDecimal getLendinShareAmt() {
        return lendinShareAmt;
    }

    /**
     * 设置 融入券交易份额
     * @param lendinShareAmt 融入券交易份额
     */
    public void setLendinShareAmt(BigDecimal lendinShareAmt) {
        this.lendinShareAmt = lendinShareAmt;
    }

    /**
     * 取得 价格模式
     * @return 价格模式
     */
    public String getPriceModel() {
        return priceModel;
    }

    /**
     * 设置 价格模式
     * @param priceModel 价格模式
     */
    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel == null ? null : priceModel.trim();
    }

    public BigDecimal getMtradeAmt() {
        return mtradeAmt;
    }

    public void setMtradeAmt(BigDecimal mtradeAmt) {
        this.mtradeAmt = mtradeAmt;
    }

    public BigDecimal getShareCtAmt() {
        return shareCtAmt;
    }

    public void setShareCtAmt(BigDecimal shareCtAmt) {
        this.shareCtAmt = shareCtAmt;
    }
}