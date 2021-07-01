package com.joyintech.tams.invest.common.dto.period.trdbondduration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: 2.1.2
 * @description: 行权赎回存续期
 * @author: 强成西
 * @create: 2020-01-20 11:37
 **/
public class TrdBondExerciseProcessDto extends BaseEntity {

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 保存、提交、撤销
     */
    private String operateType;

    /**
     * 通道费
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal channelFee;

    /**
     * 通道性质
     */
    private String payType;

    /**
     * 通道收取方式
     */
    private String collectType;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 汇总交易编号
     */
    private String bizCode;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 资产代码
     */
    private String finprodMarketId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 理论应计利息
     */
    private BigDecimal intAmtTheory;

    /**
     * 实收利息总额
     */
    private BigDecimal intAmtReal;

    /**
     * 券面总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 行权净价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal netPrice;

    /**
     * 净价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cPriceAmt;

    /**
     * 到账金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal fPriceAmt;

    /**
     * 资金到账日
     */
    private Date settleDate;

    private String remark;

    private String revokeReason;

    private TrdFinProductDealTotal trdFinProductDealTotal;

    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    private List<TrdProductDeal> trdProductDeals;

    private List<TrdProductDealAdd> trdProductDealAdds;

    private List<PaTrdProductDeal> paTrdProductDeals;

    private TrdTradeFeeTotal trdTradeFeeTotal;

    private List<TrdTradeFeeDetail> trdTradeFeeDetails;

    private List<PtlTrade> ptlTrades;

    private List<StlPayOrders> stlPayOrders;

    /**
     * 金额明细信息
     */
    private List<TrdBondPtlSecExerciseInfo> trdBondPtlSecExerciseInfos;

    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 获取 elementId
     *
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 channelFee
     *
     * @return channelFee
     */
    public BigDecimal getChannelFee() {
        return channelFee;
    }

    /**
     * 设置 channelFee
     *
     * @param channelFee channelFee
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    /**
     * 获取 payType
     *
     * @return payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 payType
     *
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取 collectType
     *
     * @return collectType
     */
    public String getCollectType() {
        return collectType;
    }

    /**
     * 设置 collectType
     *
     * @param collectType collectType
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    /**
     * 获取 chlId
     *
     * @return chlId
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     *
     * @param chlId chlId
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    /**
     * 获取 bizCode
     *
     * @return bizCode
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 设置 bizCode
     *
     * @param bizCode bizCode
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * 获取 finprodMarketId
     *
     * @return finprodMarketId
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     *
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 finprodName
     *
     * @return finprodName
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     *
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 ps
     *
     * @return ps
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     *
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 vdate
     *
     * @return vdate
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     *
     * @param vdate vdate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     *
     * @return mdate
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     *
     * @param mdate mdate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 payDate
     *
     * @return payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     *
     * @param payDate payDate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 qty
     *
     * @return qty
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 qty
     *
     * @param qty qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 获取 netPrice
     *
     * @return netPrice
     */
    public BigDecimal getNetPrice() {
        return netPrice;
    }

    /**
     * 设置 netPrice
     *
     * @param netPrice netPrice
     */
    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    /**
     * 获取 cPriceAmt
     *
     * @return cPriceAmt
     */
    public BigDecimal getcPriceAmt() {
        return cPriceAmt;
    }

    /**
     * 设置 cPriceAmt
     *
     * @param cPriceAmt cPriceAmt
     */
    public void setcPriceAmt(BigDecimal cPriceAmt) {
        this.cPriceAmt = cPriceAmt;
    }

    /**
     * 获取 fPriceAmt
     *
     * @return fPriceAmt
     */
    public BigDecimal getfPriceAmt() {
        return fPriceAmt;
    }

    /**
     * 设置 fPriceAmt
     *
     * @param fPriceAmt fPriceAmt
     */
    public void setfPriceAmt(BigDecimal fPriceAmt) {
        this.fPriceAmt = fPriceAmt;
    }

    /**
     * 获取 settleDate
     *
     * @return settleDate
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 settleDate
     *
     * @param settleDate settleDate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 获取 trdFinProductDealTotal
     *
     * @return trdFinProductDealTotal
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 trdFinProductDealTotal
     *
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 获取 paTrdFinProductDealTotal
     *
     * @return paTrdFinProductDealTotal
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }

    /**
     * 设置 paTrdFinProductDealTotal
     *
     * @param paTrdFinProductDealTotal paTrdFinProductDealTotal
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    /**
     * 获取 trdProductDeals
     *
     * @return trdProductDeals
     */
    public List<TrdProductDeal> getTrdProductDeals() {
        return trdProductDeals;
    }

    /**
     * 设置 trdProductDeals
     *
     * @param trdProductDeals trdProductDeals
     */
    public void setTrdProductDeals(List<TrdProductDeal> trdProductDeals) {
        this.trdProductDeals = trdProductDeals;
    }

    /**
     * 获取 trdProductDealAdds
     *
     * @return trdProductDealAdds
     */
    public List<TrdProductDealAdd> getTrdProductDealAdds() {
        return trdProductDealAdds;
    }

    /**
     * 设置 trdProductDealAdds
     *
     * @param trdProductDealAdds trdProductDealAdds
     */
    public void setTrdProductDealAdds(List<TrdProductDealAdd> trdProductDealAdds) {
        this.trdProductDealAdds = trdProductDealAdds;
    }

    /**
     * 获取 paTrdProductDeals
     *
     * @return paTrdProductDeals
     */
    public List<PaTrdProductDeal> getPaTrdProductDeals() {
        return paTrdProductDeals;
    }

    /**
     * 设置 paTrdProductDeals
     *
     * @param paTrdProductDeals paTrdProductDeals
     */
    public void setPaTrdProductDeals(List<PaTrdProductDeal> paTrdProductDeals) {
        this.paTrdProductDeals = paTrdProductDeals;
    }

    /**
     * 获取 ptlTrades
     *
     * @return ptlTrades
     */
    public List<PtlTrade> getPtlTrades() {
        return ptlTrades;
    }

    /**
     * 设置 ptlTrades
     *
     * @param ptlTrades ptlTrades
     */
    public void setPtlTrades(List<PtlTrade> ptlTrades) {
        this.ptlTrades = ptlTrades;
    }

    /**
     * 获取 stlPayOrders
     *
     * @return stlPayOrders
     */
    public List<StlPayOrders> getStlPayOrders() {
        return stlPayOrders;
    }

    /**
     * 设置 stlPayOrders
     *
     * @param stlPayOrders stlPayOrders
     */
    public void setStlPayOrders(List<StlPayOrders> stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }

    /**
     * 获取 trdBondPtlSecExerciseInfos
     *
     * @return trdBondPtlSecExerciseInfos
     */
    public List<TrdBondPtlSecExerciseInfo> getTrdBondPtlSecExerciseInfos() {
        return trdBondPtlSecExerciseInfos;
    }

    /**
     * 设置 trdBondPtlSecExerciseInfos
     *
     * @param trdBondPtlSecExerciseInfos trdBondPtlSecExerciseInfos
     */
    public void setTrdBondPtlSecExerciseInfos(List<TrdBondPtlSecExerciseInfo> trdBondPtlSecExerciseInfos) {
        this.trdBondPtlSecExerciseInfos = trdBondPtlSecExerciseInfos;
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 tradeMarket
     *
     * @return tradeMarket
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 tradeMarket
     *
     * @param tradeMarket tradeMarket
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 trdTradeFeeTotal
     *
     * @return trdTradeFeeTotal
     */
    public TrdTradeFeeTotal getTrdTradeFeeTotal() {
        return trdTradeFeeTotal;
    }

    /**
     * 设置 trdTradeFeeTotal
     *
     * @param trdTradeFeeTotal trdTradeFeeTotal
     */
    public void setTrdTradeFeeTotal(TrdTradeFeeTotal trdTradeFeeTotal) {
        this.trdTradeFeeTotal = trdTradeFeeTotal;
    }

    /**
     * 获取 trdTradeFeeDetails
     *
     * @return trdTradeFeeDetails
     */
    public List<TrdTradeFeeDetail> getTrdTradeFeeDetails() {
        return trdTradeFeeDetails;
    }

    /**
     * 设置 trdTradeFeeDetails
     *
     * @param trdTradeFeeDetails trdTradeFeeDetails
     */
    public void setTrdTradeFeeDetails(List<TrdTradeFeeDetail> trdTradeFeeDetails) {
        this.trdTradeFeeDetails = trdTradeFeeDetails;
    }

    /**
     * 获取 revokeReason
     *
     * @return revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     *
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }


    public BigDecimal getIntAmtTheory() {
        return intAmtTheory;
    }

    public void setIntAmtTheory(BigDecimal intAmtTheory) {
        this.intAmtTheory = intAmtTheory;
    }

    public BigDecimal getIntAmtReal() {
        return intAmtReal;
    }

    public void setIntAmtReal(BigDecimal intAmtReal) {
        this.intAmtReal = intAmtReal;
    }
}
