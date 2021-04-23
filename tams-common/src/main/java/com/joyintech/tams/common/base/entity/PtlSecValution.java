package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlSecValution extends PtlSecValutionKey {
    /**
     * 币种
     */
    private String ccy;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 公允价值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngpl;

    /**
     * 摊销总成本，券面+利息调整
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dscCostAmt;

    /**
     * 摊销成本净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dscCleanPrice;

    /**
     * 当日应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    /**
     * 买入总成本，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyCostAmt;

    /**
     * 买入成本净价，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyCleanPrice;

    /**
     * 市值，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marketValue;

    /**
     * 市价净价，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marketCleanPrice;

    /**
     * 当日发生摊销收入，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyDscincexpAdd;

    /**
     * 当日发生利息收入，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAdd;

    /**
     * 当日发生价差收入，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyDsclossAdd;

    /**
     * 当日发生浮动盈亏，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngplAdd;

    /**
     * 当日发生费用支出，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFeeAdd;

    /**
     * 累计单位净值，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accuNetValue;

    /**
     * 实际日利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actDYield;

    /**
     * 待清算资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal delayPayAmt;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 公允价值变动_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngplB;

    /**
     * 摊销总成本_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dscCostAmtB;

    /**
     * 摊销成本净价_本位币，摊销总成本/数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dscCleanPriceB;

    /**
     * 待清算资金_本位币， 还本/付息区间后未支付的本金/利息，T+1交易，资金清算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal delayPayAmtB;

    /**
     * 当日应计利息_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpB;

    /**
     * 收入端应计利息_原币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal inTdyIntincexp;

    /**
     * 支出端应计利息_原币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal outTdyIntincexp;

    /**
     * 剩余期限，下一个付息日行权日-计算日
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays1;

    /**
     * 剩余存续期限,到期日-计算日
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays2;

    /**
     * 计提利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 当日价差收入_本币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyDsclossAddB;

    /**
     * 生成方式
     */
    private String genType;

    /**
     * 减值余额_本币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyLossAmtB;

    /**
     * 减值余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyLossAmt;

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
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 数量
     * @param shareAmt 数量
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 公允价值变动
     * @return 公允价值变动
     */
    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    /**
     * 设置 公允价值变动
     * @param tdyFloatIngpl 公允价值变动
     */
    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

    /**
     * 取得 摊销总成本，券面+利息调整
     * @return 摊销总成本，券面+利息调整
     */
    public BigDecimal getDscCostAmt() {
        return dscCostAmt;
    }

    /**
     * 设置 摊销总成本，券面+利息调整
     * @param dscCostAmt 摊销总成本，券面+利息调整
     */
    public void setDscCostAmt(BigDecimal dscCostAmt) {
        this.dscCostAmt = dscCostAmt;
    }

    /**
     * 取得 摊销成本净价
     * @return 摊销成本净价
     */
    public BigDecimal getDscCleanPrice() {
        return dscCleanPrice;
    }

    /**
     * 设置 摊销成本净价
     * @param dscCleanPrice 摊销成本净价
     */
    public void setDscCleanPrice(BigDecimal dscCleanPrice) {
        this.dscCleanPrice = dscCleanPrice;
    }

    /**
     * 取得 当日应计利息
     * @return 当日应计利息
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 设置 当日应计利息
     * @param tdyIntincexp 当日应计利息
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 取得 买入总成本，暂时不用
     * @return 买入总成本，暂时不用
     */
    public BigDecimal getBuyCostAmt() {
        return buyCostAmt;
    }

    /**
     * 设置 买入总成本，暂时不用
     * @param buyCostAmt 买入总成本，暂时不用
     */
    public void setBuyCostAmt(BigDecimal buyCostAmt) {
        this.buyCostAmt = buyCostAmt;
    }

    /**
     * 取得 买入成本净价，暂时不用
     * @return 买入成本净价，暂时不用
     */
    public BigDecimal getBuyCleanPrice() {
        return buyCleanPrice;
    }

    /**
     * 设置 买入成本净价，暂时不用
     * @param buyCleanPrice 买入成本净价，暂时不用
     */
    public void setBuyCleanPrice(BigDecimal buyCleanPrice) {
        this.buyCleanPrice = buyCleanPrice;
    }

    /**
     * 取得 市值，暂时不用
     * @return 市值，暂时不用
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 市值，暂时不用
     * @param marketValue 市值，暂时不用
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 取得 市价净价，暂时不用
     * @return 市价净价，暂时不用
     */
    public BigDecimal getMarketCleanPrice() {
        return marketCleanPrice;
    }

    /**
     * 设置 市价净价，暂时不用
     * @param marketCleanPrice 市价净价，暂时不用
     */
    public void setMarketCleanPrice(BigDecimal marketCleanPrice) {
        this.marketCleanPrice = marketCleanPrice;
    }

    /**
     * 取得 当日发生摊销收入，暂时不用
     * @return 当日发生摊销收入，暂时不用
     */
    public BigDecimal getTdyDscincexpAdd() {
        return tdyDscincexpAdd;
    }

    /**
     * 设置 当日发生摊销收入，暂时不用
     * @param tdyDscincexpAdd 当日发生摊销收入，暂时不用
     */
    public void setTdyDscincexpAdd(BigDecimal tdyDscincexpAdd) {
        this.tdyDscincexpAdd = tdyDscincexpAdd;
    }

    /**
     * 取得 当日发生利息收入，暂时不用
     * @return 当日发生利息收入，暂时不用
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 设置 当日发生利息收入，暂时不用
     * @param tdyIntincexpAdd 当日发生利息收入，暂时不用
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
    }

    /**
     * 取得 当日发生价差收入，暂时不用
     * @return 当日发生价差收入，暂时不用
     */
    public BigDecimal getTdyDsclossAdd() {
        return tdyDsclossAdd;
    }

    /**
     * 设置 当日发生价差收入，暂时不用
     * @param tdyDsclossAdd 当日发生价差收入，暂时不用
     */
    public void setTdyDsclossAdd(BigDecimal tdyDsclossAdd) {
        this.tdyDsclossAdd = tdyDsclossAdd;
    }

    /**
     * 取得 当日发生浮动盈亏，暂时不用
     * @return 当日发生浮动盈亏，暂时不用
     */
    public BigDecimal getTdyFloatIngplAdd() {
        return tdyFloatIngplAdd;
    }

    /**
     * 设置 当日发生浮动盈亏，暂时不用
     * @param tdyFloatIngplAdd 当日发生浮动盈亏，暂时不用
     */
    public void setTdyFloatIngplAdd(BigDecimal tdyFloatIngplAdd) {
        this.tdyFloatIngplAdd = tdyFloatIngplAdd;
    }

    /**
     * 取得 当日发生费用支出，暂时不用
     * @return 当日发生费用支出，暂时不用
     */
    public BigDecimal getTdyFeeAdd() {
        return tdyFeeAdd;
    }

    /**
     * 设置 当日发生费用支出，暂时不用
     * @param tdyFeeAdd 当日发生费用支出，暂时不用
     */
    public void setTdyFeeAdd(BigDecimal tdyFeeAdd) {
        this.tdyFeeAdd = tdyFeeAdd;
    }

    /**
     * 取得 累计单位净值，暂时不用
     * @return 累计单位净值，暂时不用
     */
    public BigDecimal getAccuNetValue() {
        return accuNetValue;
    }

    /**
     * 设置 累计单位净值，暂时不用
     * @param accuNetValue 累计单位净值，暂时不用
     */
    public void setAccuNetValue(BigDecimal accuNetValue) {
        this.accuNetValue = accuNetValue;
    }

    /**
     * 取得 实际日利率
     * @return 实际日利率
     */
    public BigDecimal getActDYield() {
        return actDYield;
    }

    /**
     * 设置 实际日利率
     * @param actDYield 实际日利率
     */
    public void setActDYield(BigDecimal actDYield) {
        this.actDYield = actDYield;
    }

    /**
     * 取得 待清算资金
     * @return 待清算资金
     */
    public BigDecimal getDelayPayAmt() {
        return delayPayAmt;
    }

    /**
     * 设置 待清算资金
     * @param delayPayAmt 待清算资金
     */
    public void setDelayPayAmt(BigDecimal delayPayAmt) {
        this.delayPayAmt = delayPayAmt;
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getbCcy() {
        return bCcy;
    }

    /**
     * 设置 本位币
     * @param bCcy 本位币
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy == null ? null : bCcy.trim();
    }

    /**
     * 取得 公允价值变动_本位币
     * @return 公允价值变动_本位币
     */
    public BigDecimal getTdyFloatIngplB() {
        return tdyFloatIngplB;
    }

    /**
     * 设置 公允价值变动_本位币
     * @param tdyFloatIngplB 公允价值变动_本位币
     */
    public void setTdyFloatIngplB(BigDecimal tdyFloatIngplB) {
        this.tdyFloatIngplB = tdyFloatIngplB;
    }

    /**
     * 取得 摊销总成本_本位币
     * @return 摊销总成本_本位币
     */
    public BigDecimal getDscCostAmtB() {
        return dscCostAmtB;
    }

    /**
     * 设置 摊销总成本_本位币
     * @param dscCostAmtB 摊销总成本_本位币
     */
    public void setDscCostAmtB(BigDecimal dscCostAmtB) {
        this.dscCostAmtB = dscCostAmtB;
    }

    /**
     * 取得 摊销成本净价_本位币，摊销总成本/数量
     * @return 摊销成本净价_本位币，摊销总成本/数量
     */
    public BigDecimal getDscCleanPriceB() {
        return dscCleanPriceB;
    }

    /**
     * 设置 摊销成本净价_本位币，摊销总成本/数量
     * @param dscCleanPriceB 摊销成本净价_本位币，摊销总成本/数量
     */
    public void setDscCleanPriceB(BigDecimal dscCleanPriceB) {
        this.dscCleanPriceB = dscCleanPriceB;
    }

    /**
     * 取得 待清算资金_本位币， 还本/付息区间后未支付的本金/利息，T+1交易，资金清算金额
     * @return 待清算资金_本位币， 还本/付息区间后未支付的本金/利息，T+1交易，资金清算金额
     */
    public BigDecimal getDelayPayAmtB() {
        return delayPayAmtB;
    }

    /**
     * 设置 待清算资金_本位币， 还本/付息区间后未支付的本金/利息，T+1交易，资金清算金额
     * @param delayPayAmtB 待清算资金_本位币， 还本/付息区间后未支付的本金/利息，T+1交易，资金清算金额
     */
    public void setDelayPayAmtB(BigDecimal delayPayAmtB) {
        this.delayPayAmtB = delayPayAmtB;
    }

    /**
     * 取得 当日应计利息_本位币
     * @return 当日应计利息_本位币
     */
    public BigDecimal getTdyIntincexpB() {
        return tdyIntincexpB;
    }

    /**
     * 设置 当日应计利息_本位币
     * @param tdyIntincexpB 当日应计利息_本位币
     */
    public void setTdyIntincexpB(BigDecimal tdyIntincexpB) {
        this.tdyIntincexpB = tdyIntincexpB;
    }

    /**
     * 取得 收入端应计利息_原币
     * @return 收入端应计利息_原币
     */
    public BigDecimal getInTdyIntincexp() {
        return inTdyIntincexp;
    }

    /**
     * 设置 收入端应计利息_原币
     * @param inTdyIntincexp 收入端应计利息_原币
     */
    public void setInTdyIntincexp(BigDecimal inTdyIntincexp) {
        this.inTdyIntincexp = inTdyIntincexp;
    }

    /**
     * 取得 支出端应计利息_原币
     * @return 支出端应计利息_原币
     */
    public BigDecimal getOutTdyIntincexp() {
        return outTdyIntincexp;
    }

    /**
     * 设置 支出端应计利息_原币
     * @param outTdyIntincexp 支出端应计利息_原币
     */
    public void setOutTdyIntincexp(BigDecimal outTdyIntincexp) {
        this.outTdyIntincexp = outTdyIntincexp;
    }

    /**
     * 取得 剩余期限，下一个付息日行权日-计算日
     * @return 剩余期限，下一个付息日行权日-计算日
     */
    public BigDecimal getEndDays1() {
        return endDays1;
    }

    /**
     * 设置 剩余期限，下一个付息日行权日-计算日
     * @param endDays1 剩余期限，下一个付息日行权日-计算日
     */
    public void setEndDays1(BigDecimal endDays1) {
        this.endDays1 = endDays1;
    }

    /**
     * 取得 剩余存续期限,到期日-计算日
     * @return 剩余存续期限,到期日-计算日
     */
    public BigDecimal getEndDays2() {
        return endDays2;
    }

    /**
     * 设置 剩余存续期限,到期日-计算日
     * @param endDays2 剩余存续期限,到期日-计算日
     */
    public void setEndDays2(BigDecimal endDays2) {
        this.endDays2 = endDays2;
    }

    /**
     * 取得 计提利率
     * @return 计提利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 计提利率
     * @param intRate 计提利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 当日价差收入_本币
     * @return 当日价差收入_本币
     */
    public BigDecimal getTdyDsclossAddB() {
        return tdyDsclossAddB;
    }

    /**
     * 设置 当日价差收入_本币
     * @param tdyDsclossAddB 当日价差收入_本币
     */
    public void setTdyDsclossAddB(BigDecimal tdyDsclossAddB) {
        this.tdyDsclossAddB = tdyDsclossAddB;
    }

    /**
     * 取得 生成方式
     * @return 生成方式
     */
    public String getGenType() {
        return genType;
    }

    /**
     * 设置 生成方式
     * @param genType 生成方式
     */
    public void setGenType(String genType) {
        this.genType = genType == null ? null : genType.trim();
    }

    /**
     * 取得 减值余额_本币
     * @return 减值余额_本币
     */
    public BigDecimal getTdyLossAmtB() {
        return tdyLossAmtB;
    }

    /**
     * 设置 减值余额_本币
     * @param tdyLossAmtB 减值余额_本币
     */
    public void setTdyLossAmtB(BigDecimal tdyLossAmtB) {
        this.tdyLossAmtB = tdyLossAmtB;
    }

    /**
     * 取得 减值余额
     * @return 减值余额
     */
    public BigDecimal getTdyLossAmt() {
        return tdyLossAmt;
    }

    /**
     * 设置 减值余额
     * @param tdyLossAmt 减值余额
     */
    public void setTdyLossAmt(BigDecimal tdyLossAmt) {
        this.tdyLossAmt = tdyLossAmt;
    }
}