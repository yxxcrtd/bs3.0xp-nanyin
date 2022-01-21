package com.joyintech.tams.invest.common.dto.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @auther zhangnian
 * @date 2021/2/26
 */
public class PtlAllocationDto extends EtrEntrust {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8967384621506273L;

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;
    /**
     *  组合名称
     */
    private String portfolioName;
    /**
     * 期限天数，到期日-起息日
     */
    private Long termDays;

    /**
     * 剩余额度
     */
    private BigDecimal avaNum;

    /**
     * 全价总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shareCtAmt;

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

   //净价总额
   @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cPriceAmt;

    //应计利息
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    //价格模式
    private String priceModel;

    /**
     * 本方券券面
     */
    private BigDecimal ourShareAmt;

    /**
     * 融入券券面
     */
    private BigDecimal lendinShareAmt;

    /**
     * 到期结算金额(元)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mtradeAmt;

    /**
     * 交易费用LIST
     */
    private List<TrdTradeFeeDetail> tradeFeeList;

    public BigDecimal getOurShareAmt() {
        return ourShareAmt;
    }

    public void setOurShareAmt(BigDecimal ourShareAmt) {
        this.ourShareAmt = ourShareAmt;
    }

    public BigDecimal getLendinShareAmt() {
        return lendinShareAmt;
    }

    public void setLendinShareAmt(BigDecimal lendinShareAmt) {
        this.lendinShareAmt = lendinShareAmt;
    }

    public Long getTermDays() {
        return termDays;
    }

    public void setTermDays(Long termDays) {
        this.termDays = termDays;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public BigDecimal getAvaNum() {
        return avaNum;
    }

    public void setAvaNum(BigDecimal avaNum) {
        this.avaNum = avaNum;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getShareCtAmt() {
        return shareCtAmt;
    }

    public void setShareCtAmt(BigDecimal shareCtAmt) {
        this.shareCtAmt = shareCtAmt;
    }

    public List<TrdTradeFeeDetail> getTradeFeeList() {
        return tradeFeeList;
    }

    public void setTradeFeeList(List<TrdTradeFeeDetail> tradeFeeList) {
        this.tradeFeeList = tradeFeeList;
    }

    public BigDecimal getCbFpriceAmt() {
        return cbFpriceAmt;
    }

    public void setCbFpriceAmt(BigDecimal cbFpriceAmt) {
        this.cbFpriceAmt = cbFpriceAmt;
    }

    public BigDecimal getScFpriceAmt() {
        return scFpriceAmt;
    }

    public void setScFpriceAmt(BigDecimal scFpriceAmt) {
        this.scFpriceAmt = scFpriceAmt;
    }

    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    public BigDecimal getSettFeeAmt() {
        return settFeeAmt;
    }

    public void setSettFeeAmt(BigDecimal settFeeAmt) {
        this.settFeeAmt = settFeeAmt;
    }

    public BigDecimal getcPriceAmt() {
        return cPriceAmt;
    }

    public void setcPriceAmt(BigDecimal cPriceAmt) {
        this.cPriceAmt = cPriceAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    @Override
    public String getPriceModel() {
        return priceModel;
    }

    @Override
    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel;
    }

    public BigDecimal getMtradeAmt() {
        return mtradeAmt;
    }

    public void setMtradeAmt(BigDecimal mtradeAmt) {
        this.mtradeAmt = mtradeAmt;
    }

    public String getPriceModelStr(){
        if (StringUtil.isEmpty(this.getPriceModel())) {
            return null;
        }
        return SysDataUtil.getNameFromDataDict(DictCode.价格模式, this.getPriceModel());
    }
}

