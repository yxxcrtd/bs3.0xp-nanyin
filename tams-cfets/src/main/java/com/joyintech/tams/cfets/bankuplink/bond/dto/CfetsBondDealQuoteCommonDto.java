package com.joyintech.tams.cfets.bankuplink.bond.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zznn
 * @date 2021/11/10
 */
@ApiModel(value="债券报价实体(输入输出共用)",description="债券报价实体(输入输出共用)")
public class CfetsBondDealQuoteCommonDto {


    @ApiModelProperty(name="operateType",value="操作类型" ,example = "save")
    private String operateType;

    @ApiModelProperty(name="quoteType",value="报价方式")
    private String quoteType;

    @ApiModelProperty(name="ps",value="交易方向")
    private String ps;

    @ApiModelProperty(name="finprodId",value="资产代码")
    private String finprodId;

    @ApiModelProperty(name="mySide",value="本方")
    private String mySide;

    @ApiModelProperty(name="mySideTrader",value="本方交易员")
    private String mySideTrader;

    @ApiModelProperty(name="counterId",value="对手方")
    private String counterId;

    @ApiModelProperty(name="counterTrader",value="对手方交易员")
    private String counterTrader;

    @ApiModelProperty(name="ccy",value="币种")
    private String ccy;

    @ApiModelProperty(name="settleSpreed",value="清算速度")
    private String settleSpreed;

    @ApiModelProperty(name="tradeDate",value="交易日期")
    private Date tradeDate;

    @ApiModelProperty(name="settleDate",value="结算日期")
    private Date settleDate;

    @ApiModelProperty(name="calculateField",value="锚定值(01表示净价) 02表示到期收益率 03表示行权收益率")
    private String calculateField;

    @ApiModelProperty(name="price",value="净价")
    private BigDecimal price;

    @ApiModelProperty(name="matureYield",value="到期收益率")
    private BigDecimal matureYield;

    @ApiModelProperty(name="exerYield",value="行权收益率")
    private BigDecimal exerYield;

    @ApiModelProperty(name="shareAmt",value="券面总额")
    private BigDecimal shareAmt;

    @ApiModelProperty(name="outPartySec",value="本方券券面总额")
    private BigDecimal ourEntNum;

    @ApiModelProperty(name="inSec",value="融入券券面总额")
    private BigDecimal lendinEntNum;

    @ApiModelProperty(name="sellRepurchaseSec",value="卖空购回券面总额")
    private BigDecimal sellAbleNum;

    @ApiModelProperty(name="deliveryType",value="结算方式")
    private String  deliveryType;

    @ApiModelProperty(name="settType",value="清算类型")
    private String  settType;

    @ApiModelProperty(name="dealFeeAmt",value="交易手续费")
    private BigDecimal  dealFeeAmt;

    @ApiModelProperty(name="settFeeamt",value="结算服务费")
    private BigDecimal  settFeeamt;

    @ApiModelProperty(name="remark",value="备注")
    private String  remark;

    @ApiModelProperty(name="ptlAllocatios",value="组合分配")
    private List<PtlAllocationDto> ptlAllocatios;

    @ApiModelProperty(name="theoreticalExerciseDate",value="理论行权日")
    private Date theoreticalExerciseDate;

    @ApiModelProperty(name="actSetteDate",value="实际行权日")
    private Date actSetteDate;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getMySide() {
        return mySide;
    }

    public void setMySide(String mySide) {
        this.mySide = mySide;
    }

    public String getMySideTrader() {
        return mySideTrader;
    }

    public void setMySideTrader(String mySideTrader) {
        this.mySideTrader = mySideTrader;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getSettleSpreed() {
        return settleSpreed;
    }

    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getCalculateField() {
        return calculateField;
    }

    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMatureYield() {
        return matureYield;
    }

    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getOurEntNum() {
        return ourEntNum;
    }

    public void setOurEntNum(BigDecimal ourEntNum) {
        this.ourEntNum = ourEntNum;
    }

    public BigDecimal getLendinEntNum() {
        return lendinEntNum;
    }

    public void setLendinEntNum(BigDecimal lendinEntNum) {
        this.lendinEntNum = lendinEntNum;
    }

    public BigDecimal getSellAbleNum() {
        return sellAbleNum;
    }

    public void setSellAbleNum(BigDecimal sellAbleNum) {
        this.sellAbleNum = sellAbleNum;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getSettType() {
        return settType;
    }

    public void setSettType(String settType) {
        this.settType = settType;
    }

    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    public BigDecimal getSettFeeamt() {
        return settFeeamt;
    }

    public void setSettFeeamt(BigDecimal settFeeamt) {
        this.settFeeamt = settFeeamt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PtlAllocationDto> getPtlAllocatios() {
        return ptlAllocatios;
    }

    public void setPtlAllocatios(List<PtlAllocationDto> ptlAllocatios) {
        this.ptlAllocatios = ptlAllocatios;
    }

    public Date getTheoreticalExerciseDate() {
        return theoreticalExerciseDate;
    }

    public void setTheoreticalExerciseDate(Date theoreticalExerciseDate) {
        this.theoreticalExerciseDate = theoreticalExerciseDate;
    }

    public Date getActSetteDate() {
        return actSetteDate;
    }

    public void setActSetteDate(Date actSetteDate) {
        this.actSetteDate = actSetteDate;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
}

