/*
 * 文件名：CfetsTrdConfirmRegInfoListResponse.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.trdconfirm.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行间交易成交确认
 * 查询交易登记列表返回数据DTO
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmRegInfoListResponse
 * @since 1.0
 */
@ApiModel(value="查询交易登记列表返回数据",description="查询交易登记列表返回数据")
public class CfetsTrdConfirmRegInfoListResponse{

    @ApiModelProperty(name="effectiveStatus",value="有效状态Code",example="A1")
    private String effectiveStatus;

    @ApiModelProperty(name="effectiveStatusName",value="有效状态",example="A1")
    private String effectiveStatusName;

    @ApiModelProperty(name="finprodType2",value="资产类型Code",example="A1")
    private String  finprodType2;

    @ApiModelProperty(name="finprodType2Name",value="资产类型",example="A1")
    private String  finprodType2Name;

    @ApiModelProperty(name="finprodId",value="资产代码Id",example="ZC1")
    private String finprodId;

    @ApiModelProperty(name="finprodMarketId",value="资产代码",example="ZC1")
    private String finprodMarketId;

    @ApiModelProperty(name="finprodAbbr",value="资产简称",example="ZC名")
    private String finprodAbbr;

    @ApiModelProperty(name="ps",value="交易方向Code",example="T01")
    private String ps;

    @ApiModelProperty(name="psName",value="交易方向",example="T01")
    private String psName;

    @ApiModelProperty(name="tradeMarket",value="交易场所Code",example="T01")
    private String tradeMarket;

    @ApiModelProperty(name="psName",value="交易场所",example="T01")
    private String tradeMarketName;

    @ApiModelProperty(name="settleSpeed",value="清算速度Code",example="1")
    private String settleSpeed;

    @ApiModelProperty(name="settleSpeedName",value="清算速度",example="1")
    private String settleSpeedName;

    @ApiModelProperty(name="secManageAcctId",value="证券账户ID",example="1")
    private String secManageAcctId;

    @ApiModelProperty(name="secManageAcctIdName",value="证券账户",example="1")
    private String secManageAcctIdName;

    @ApiModelProperty(name="faceValue",value="券面总额",example="1")
    private BigDecimal faceValue;

    @ApiModelProperty(name="tradeYield",value="成交价",example="1")
    private BigDecimal tradeYield;

    @ApiModelProperty(name="termDays",value="期限（天）",example="1")
    private BigDecimal termDays;

    @ApiModelProperty(name="tradeAmt",value="交易金额",example="1")
    private BigDecimal tradeAmt;

    @ApiModelProperty(name="busiCode",value="业务编号",example="1")
    private String busiCode;

    @ApiModelProperty(name="trader",value="交易员",example="admin")
    private String trader;

    @ApiModelProperty(name="tradeDate",value="登记日期",example="admin")
    private Date tradeDate;

    @ApiModelProperty(name="counterTrader",value="对手方交易员",example="admin")
    private String counterTrader;

    @ApiModelProperty(name="portfolioIds",value="组合代码",example="字符串1")
    private String portfolioIds;

    @ApiModelProperty(name="portfolioNames",value="组合名称",example="字符串1")
    private String portfolioNames;

    @ApiModelProperty(name="quoteType",value="报价方式",example="字符串1")
    private String quoteType;

    @ApiModelProperty(name="exportNum",value="导出次数",example="1")
    private BigDecimal exportNum;

    @ApiModelProperty(name="remark",value="备注",example="字符串1")
    private String remark;

    @ApiModelProperty(name="totleTradeId",value="汇总登记编号",example="字符串1")
    private String totleTradeId;

    @ApiModelProperty(name="settleDate",value="结算日期",example="字符串1")
    private Date settleDate;

    @ApiModelProperty(name="cpriceAmt",value="净价总额",example="字符串1")
    private BigDecimal cpriceAmt;

    @ApiModelProperty(name="yield",value="到期收益率",example="字符串1")
    private BigDecimal yield;

    @ApiModelProperty(name="exerYield",value="到期收益率",example="字符串1")
    private BigDecimal exerYield;

    @ApiModelProperty(name="ouPartyId",value="本方银行间机构",example="字符串1")
    private String ouPartyId;

    @ApiModelProperty(name="ouTraderId",value="本方银行间交易员",example="字符串1")
    private String ouTraderId;

    @ApiModelProperty(name="counterId",value="交易对手代码",example="字符串1")
    private String counterId;

    @ApiModelProperty(name="ourShareAmt",value="本方券券面总额",example="字符串1")
    private BigDecimal ourShareAmt;

    @ApiModelProperty(name="lendinShareAmt",value="融入券券面总额",example="字符串1")
    private BigDecimal lendinShareAmt;

    @ApiModelProperty(name="ourFpriceAmt",value="本方券全价总额",example="字符串1")
    private BigDecimal ourFpriceAmt;

    @ApiModelProperty(name="lendinFpriceAmt",value="融入券全价总额",example="字符串1")
    private BigDecimal lendinFpriceAmt;

    @ApiModelProperty(name="lendinFpriceAmt",value="融入券净价总额",example="字符串1")
    private BigDecimal lendinCpriceAmt;


    @ApiModelProperty(name="ownIntAmt",value="本方券应计利息",example="字符串1")
    private BigDecimal ownIntAmt;


    @ApiModelProperty(name="lendinIntAmt",value="融入券应计利息",example="字符串1")
    private BigDecimal lendinIntAmt;


    @ApiModelProperty(name="ccy",value="交易币种",example="字符串1")
    private String ccy;

    @ApiModelProperty(name="unitCprice",value="单位净价",example="字符串1")
    private BigDecimal unitCprice;
    @ApiModelProperty(name="unitInt",value="单位利息",example="字符串1")
    private BigDecimal unitInt;
    @ApiModelProperty(name="unitFprice",value="单位利息",example="字符串1")
    private BigDecimal unitFprice;
    @ApiModelProperty(name="parValue",value="单位面值",example="字符串1")
    private BigDecimal parValue;
    @ApiModelProperty(name="intAmt",value="利息总额",example="字符串1")
    private BigDecimal intAmt;
    @ApiModelProperty(name="fpriceAmt",value="全价总额",example="字符串1")
    private BigDecimal fpriceAmt;
    @ApiModelProperty(name="shareAmt",value="交易份额",example="字符串1")
    private BigDecimal shareAmt;
    @ApiModelProperty(name="deliveryType",value="结算方式",example="字符串1")
    private String  deliveryType;
    @ApiModelProperty(name="mDeliveryType",value="到期结算方式",example="字符串1")
    private String  mDeliveryType;
    @ApiModelProperty(name="settCcy",value="结算币种",example="字符串1")
    private String  settCcy;
    @ApiModelProperty(name="clearType",value="清算类型",example="字符串1")
    private String  clearType;
    @ApiModelProperty(name="acctId",value="本方资金账户",example="字符串1")
    private String  acctId;
    @ApiModelProperty(name="invAim",value="投资目的",example="字符串1")
    private String  invAim;
    @ApiModelProperty(name="tradeMarketMode",value="交易市场",example="字符串1")
    private String  tradeMarketMode;
    @ApiModelProperty(name="rate",value="利率",example="字符串1")
    private BigDecimal  rate;
    @ApiModelProperty(name="repoTerm",value="回购期限(天)",example="字符串1")
    private BigDecimal   repoTerm;
    @ApiModelProperty(name="termType",value="期限品种",example="字符串1")
    private String   termType;
    @ApiModelProperty(name="disFaceValue",value="质押券折算总额",example="字符串1")
    private BigDecimal   disFaceValue;
    @ApiModelProperty(name="repoType",value="回购方式",example="字符串1")
    private String   repoType;
    @ApiModelProperty(name="vSettleDate",value="首期结算日",example="字符串1")
    private Date   vSettleDate;
    @ApiModelProperty(name="mSettleDate",value="到期结算日",example="字符串1")
    private Date   mSettleDate;
    @ApiModelProperty(name="mIntAmt",value="到期利息",example="字符串1")
    private BigDecimal   mIntAmt;
    @ApiModelProperty(name="mTradeAmt",value="到期结算金额",example="字符串1")
    private BigDecimal   mTradeAmt;
    @ApiModelProperty(name="actRepoTerm",value="实际占款天数",example="字符串1")
    private BigDecimal   actRepoTerm;
    @ApiModelProperty(name="tgBusiType",value="投管业务类型",example="字符串1")
    private String   tgBusiType;
    @ApiModelProperty(name="tradeStatus",value="交易状态",example="字符串1")
    private String   tradeStatus;
    @ApiModelProperty(name="pTradeId",value="原始交易编号",example="字符串1")
    private String   pTradeId;
    @ApiModelProperty(name="isCancel",value="是否已执行",example="字符串1")
    private String   isCancel;
    @ApiModelProperty(name="isOutTrade",value="是否外部交易",example="字符串1")
    private String   isOutTrade;
    @ApiModelProperty(name="fTradeId",value="关联系统编号",example="字符串1")
    private String   fTradeId;

    public BigDecimal getParValue() {
        return parValue;
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
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

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getmDeliveryType() {
        return mDeliveryType;
    }

    public void setmDeliveryType(String mDeliveryType) {
        this.mDeliveryType = mDeliveryType;
    }

    public String getSettCcy() {
        return settCcy;
    }

    public void setSettCcy(String settCcy) {
        this.settCcy = settCcy;
    }

    public String getClearType() {
        return clearType;
    }

    public void setClearType(String clearType) {
        this.clearType = clearType;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRepoTerm() {
        return repoTerm;
    }

    public void setRepoTerm(BigDecimal repoTerm) {
        this.repoTerm = repoTerm;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public BigDecimal getDisFaceValue() {
        return disFaceValue;
    }

    public void setDisFaceValue(BigDecimal disFaceValue) {
        this.disFaceValue = disFaceValue;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public Date getvSettleDate() {
        return vSettleDate;
    }

    public void setvSettleDate(Date vSettleDate) {
        this.vSettleDate = vSettleDate;
    }

    public Date getmSettleDate() {
        return mSettleDate;
    }

    public void setmSettleDate(Date mSettleDate) {
        this.mSettleDate = mSettleDate;
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

    public BigDecimal getActRepoTerm() {
        return actRepoTerm;
    }

    public void setActRepoTerm(BigDecimal actRepoTerm) {
        this.actRepoTerm = actRepoTerm;
    }

    public String getTgBusiType() {
        return tgBusiType;
    }

    public void setTgBusiType(String tgBusiType) {
        this.tgBusiType = tgBusiType;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getpTradeId() {
        return pTradeId;
    }

    public void setpTradeId(String pTradeId) {
        this.pTradeId = pTradeId;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public String getIsOutTrade() {
        return isOutTrade;
    }

    public void setIsOutTrade(String isOutTrade) {
        this.isOutTrade = isOutTrade;
    }

    public String getfTradeId() {
        return fTradeId;
    }

    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId;
    }

    public String getDipType() {
        return dipType;
    }

    public void setDipType(String dipType) {
        this.dipType = dipType;
    }

    @ApiModelProperty(name="dipType",value="分配方式",example="字符串1")
    private String   dipType;


    public String getEffectiveStatus() {
        return effectiveStatus;
    }

    public void setEffectiveStatus(String effectiveStatus) {
        this.effectiveStatus = effectiveStatus;
    }

    public String getEffectiveStatusName() {
        return effectiveStatusName;
    }

    public void setEffectiveStatusName(String effectiveStatusName) {
        this.effectiveStatusName = effectiveStatusName;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getFinprodType2Name() {
        return finprodType2Name;
    }

    public void setFinprodType2Name(String finprodType2Name) {
        this.finprodType2Name = finprodType2Name;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradeMarketName() {
        return tradeMarketName;
    }

    public void setTradeMarketName(String tradeMarketName) {
        this.tradeMarketName = tradeMarketName;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getSettleSpeedName() {
        return settleSpeedName;
    }

    public void setSettleSpeedName(String settleSpeedName) {
        this.settleSpeedName = settleSpeedName;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    public String getSecManageAcctIdName() {
        return secManageAcctIdName;
    }

    public void setSecManageAcctIdName(String secManageAcctIdName) {
        this.secManageAcctIdName = secManageAcctIdName;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public BigDecimal getTradeYield() {
        return tradeYield;
    }

    public void setTradeYield(BigDecimal tradeYield) {
        this.tradeYield = tradeYield;
    }

    public BigDecimal getTermDays() {
        return termDays;
    }

    public void setTermDays(BigDecimal termDays) {
        this.termDays = termDays;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getPortfolioIds() {
        return portfolioIds;
    }

    public void setPortfolioIds(String portfolioIds) {
        this.portfolioIds = portfolioIds;
    }

    public String getPortfolioNames() {
        return portfolioNames;
    }

    public void setPortfolioNames(String portfolioNames) {
        this.portfolioNames = portfolioNames;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public BigDecimal getExportNum() {
        return exportNum;
    }

    public void setExportNum(BigDecimal exportNum) {
        this.exportNum = exportNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }


    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    public String getOuPartyId() {
        return ouPartyId;
    }

    public void setOuPartyId(String ouPartyId) {
        this.ouPartyId = ouPartyId;
    }

    public String getOuTraderId() {
        return ouTraderId;
    }

    public void setOuTraderId(String ouTraderId) {
        this.ouTraderId = ouTraderId;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

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

    public BigDecimal getOurFpriceAmt() {
        return ourFpriceAmt;
    }

    public void setOurFpriceAmt(BigDecimal ourFpriceAmt) {
        this.ourFpriceAmt = ourFpriceAmt;
    }

    public BigDecimal getLendinFpriceAmt() {
        return lendinFpriceAmt;
    }

    public void setLendinFpriceAmt(BigDecimal lendinFpriceAmt) {
        this.lendinFpriceAmt = lendinFpriceAmt;
    }

    public BigDecimal getLendinCpriceAmt() {
        return lendinCpriceAmt;
    }

    public void setLendinCpriceAmt(BigDecimal lendinCpriceAmt) {
        this.lendinCpriceAmt = lendinCpriceAmt;
    }

    public BigDecimal getOwnIntAmt() {
        return ownIntAmt;
    }

    public void setOwnIntAmt(BigDecimal ownIntAmt) {
        this.ownIntAmt = ownIntAmt;
    }

    public BigDecimal getLendinIntAmt() {
        return lendinIntAmt;
    }

    public void setLendinIntAmt(BigDecimal lendinIntAmt) {
        this.lendinIntAmt = lendinIntAmt;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public BigDecimal getUnitInt() {
        return unitInt;
    }

    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }
}
