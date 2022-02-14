package com.joyintech.tams.cfets.etrdispatcher.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsPledgeBondInfoDto;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 场外指令分发
 * 指令成交返回数据
 * @author sjj
 * @version 1.0 2021年11月4日
 * @see CfetsEtrDispatcherDealListInfoResponse
 * @since 1.0
 */
@ApiModel(value="指令成交返回数据",description="指令成交返回数据")
public class CfetsEtrDispatcherDealListInfoResponse extends CfetsEtrDispatcherBaseInfo{

    @ApiModelProperty(name="effectFlag",value="有效状态code",example="01")
    private String effectFlag;

    @ApiModelProperty(name="totleTradeId",value="汇总交易编号",example="A1")
    private String totleTradeId;

    @ApiModelProperty(name="tradeId",value="业务编号",example="A1")
    private String tradeId;

    @ApiModelProperty(name="secAcctId",value="证券账户code",example="XXX证券账户")
    private String secAcctId;

    @ApiModelProperty(name="secAcctIdStr",value="证券账户",example="XXX证券账户")
    private String secAcctIdStr;

    @ApiModelProperty(name="tradeDate",value="成交日期",example="2021-01-01")
    private String tradeDate;

    @ApiModelProperty(name="settleSpeed",value="清算速度code",example="T+0")
    private String settleSpeed;

    @ApiModelProperty(name="settleSpeedStr",value="清算速度",example="T+0")
    private String settleSpeedStr;

    @ApiModelProperty(name="settleDate",value="结算日期",example="2021-01-01")
    private String settleDate;

    @ApiModelProperty(name="unitPrice",value="净价",example="100")
    private BigDecimal unitPrice;

    @ApiModelProperty(name="matureYield",value="到期收益率",example="1.11")
    private BigDecimal matureYield;

    @ApiModelProperty(name="exerYield",value="行权收益率",example="1.11")
    private BigDecimal exerYield;

    @ApiModelProperty(name="shareNum",value="券面总额",example="1000000")
    private BigDecimal shareNum;

    @ApiModelProperty(name="unitInt",value="应计利息（元）",example="100")
    private BigDecimal unitInt;

    @ApiModelProperty(name="allPrice",value="全价",example="100")
    private BigDecimal allPrice;

    @ApiModelProperty(name="cpriceAmt",value="净价总额",example="100")
    private BigDecimal cpriceAmt;

    @ApiModelProperty(name="intAmt",value="应计利息总额（元）",example="100")
    private BigDecimal intAmt;

    @ApiModelProperty(name="fpriceAmt",value="全价总额",example="1000000")
    private BigDecimal fpriceAmt;

    @ApiModelProperty(name="jyFeeAmt",value="交易手续费",example="1000000")
    private BigDecimal jyFeeAmt;

    @ApiModelProperty(name="jsFeeAmt",value="结算手续费",example="1000000")
    private BigDecimal jsFeeAmt;

    @ApiModelProperty(name="counterName",value="交易对手",example="A1")
    private String counterName;

    @ApiModelProperty(name="remark",value="备注",example="A1")
    private String remark;

    @ApiModelProperty(name="termType",value="期限",example="1Y/1M")
    private String termType;

    @ApiModelProperty(name="repoTerm",value="回购期限(天)",example="5")
    private String repoTerm;

    @ApiModelProperty(name="vpayDate",value="到期结算日",example="2021-01-01")
    private String vpayDate;

    @ApiModelProperty(name="mpayDate",value="到期结算日",example="2021-01-01")
    private String mpayDate;

    @ApiModelProperty(name="actCapDays",value="实际占款天数（天）",example="5")
    private Long actCapDays;

    @ApiModelProperty(name="rebackPrice",value="回购利率%",example="3")
    private BigDecimal rebackPrice;

    @ApiModelProperty(name="tradeAmt",value="交易金额（元）",example="10000")
    private BigDecimal tradeAmt;

    @ApiModelProperty(name="mIntAmt",value="回购利息",example="1.1")
    private BigDecimal mIntAmt;

    @ApiModelProperty(name="mTradeAmt",value="到期结算金额",example="10001")
    private BigDecimal mTradeAmt;

    @ApiModelProperty(name="fCleanPrice",value="首次净价",example="99")
    private BigDecimal fCleanPrice;

    @ApiModelProperty(name="mCprice",value="到期净价",example="99")
    private BigDecimal mCprice;

    @ApiModelProperty(name="mYield",value="到期收益率%",example="1.1")
    private BigDecimal mYield;

    @ApiModelProperty(name="secTotalPrin",value="券面总额（万元）",example="100")
    private BigDecimal secTotalPrin;

    @ApiModelProperty(name="pledgeStockName",value="质押券信息",example="100")
    private String pledgeStockName;

    @ApiModelProperty(name="detailList",value="质押券明细",example="100")
    private List<CfetsPledgeBondInfoDto> detailList;

    public String getEffectFlag() {
        return effectFlag;
    }

    public void setEffectFlag(String effectFlag) {
        this.effectFlag = effectFlag;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getSecAcctIdStr() {
        return secAcctIdStr;
    }

    public void setSecAcctIdStr(String secAcctIdStr) {
        this.secAcctIdStr = secAcctIdStr;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getSettleSpeedStr() {
        return settleSpeedStr;
    }

    public void setSettleSpeedStr(String settleSpeedStr) {
        this.settleSpeedStr = settleSpeedStr;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public BigDecimal getShareNum() {
        return shareNum;
    }

    public void setShareNum(BigDecimal shareNum) {
        this.shareNum = shareNum;
    }

    public BigDecimal getUnitInt() {
        return unitInt;
    }

    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getJyFeeAmt() {
        return jyFeeAmt;
    }

    public void setJyFeeAmt(BigDecimal jyFeeAmt) {
        this.jyFeeAmt = jyFeeAmt;
    }

    public BigDecimal getJsFeeAmt() {
        return jsFeeAmt;
    }

    public void setJsFeeAmt(BigDecimal jsFeeAmt) {
        this.jsFeeAmt = jsFeeAmt;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getRepoTerm() {
        return repoTerm;
    }

    public void setRepoTerm(String repoTerm) {
        this.repoTerm = repoTerm;
    }

    public String getVpayDate() {
        return vpayDate;
    }

    public void setVpayDate(String vpayDate) {
        this.vpayDate = vpayDate;
    }

    public String getMpayDate() {
        return mpayDate;
    }

    public void setMpayDate(String mpayDate) {
        this.mpayDate = mpayDate;
    }

    public Long getActCapDays() {
        return actCapDays;
    }

    public void setActCapDays(Long actCapDays) {
        this.actCapDays = actCapDays;
    }

    public BigDecimal getRebackPrice() {
        return rebackPrice;
    }

    public void setRebackPrice(BigDecimal rebackPrice) {
        this.rebackPrice = rebackPrice;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    public BigDecimal getfCleanPrice() {
        return fCleanPrice;
    }

    public void setfCleanPrice(BigDecimal fCleanPrice) {
        this.fCleanPrice = fCleanPrice;
    }

    public BigDecimal getmCprice() {
        return mCprice;
    }

    public void setmCprice(BigDecimal mCprice) {
        this.mCprice = mCprice;
    }

    public BigDecimal getmYield() {
        return mYield;
    }

    public void setmYield(BigDecimal mYield) {
        this.mYield = mYield;
    }

    public BigDecimal getSecTotalPrin() {
        return secTotalPrin;
    }

    public void setSecTotalPrin(BigDecimal secTotalPrin) {
        this.secTotalPrin = secTotalPrin;
    }

    public String getOtherEffectFlag() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getEffectFlag());
    }

    public String getOtherCcy() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    public String getOtherPs() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public String getOtherSettleSpeed() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpeed());
    }

    public List<CfetsPledgeBondInfoDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<CfetsPledgeBondInfoDto> detailList) {
        this.detailList = detailList;
    }

    public String getPledgeStockName() {
        return pledgeStockName;
    }

    public void setPledgeStockName(String pledgeStockName) {
        this.pledgeStockName = pledgeStockName;
    }
}
