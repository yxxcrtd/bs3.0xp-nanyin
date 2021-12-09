/*
 * 文件名：CfetsTrdConfirmRegInfoListResponse.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.trdquery.dto;


import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行间交易成交确认
 * 查询Cstp下行列表返回数据DTO
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdQueryCstpInfoListResponse
 * @since 1.0
 */
@ApiModel(value="查询Cstp下行列表返回数据",description="查询Cstp下行列表返回数据")
public class CfetsTrdQueryCstpInfoListResponse {

    @ApiModelProperty(name="matchStatus",value="匹配状态",example="字符串1")
    private String matchStatus;

    @ApiModelProperty(name="matchStatusStr",value="匹配状态Str",example="字符串1")
    private String matchStatusStr;

    @ApiModelProperty(name="execid",value="成交编号",example="1")
    private String execid;

    @ApiModelProperty(name="businesstypeStr",value="资产类型",example="A1")
    private String businesstypeStr;

    @ApiModelProperty(name="businesstype",value="资产类型Code",example="A1")
    private String  businesstype;

    @ApiModelProperty(name="finprodId",value="资产代码Id",example="ZC1")
    private String finprodId;

    @ApiModelProperty(name="finprodMarketId",value="资产代码",example="ZC1")
    private String finprodMarketId;

    @ApiModelProperty(name="finprodAbbr",value="资产简称",example="ZC名")
    private String finprodAbbr;

    @ApiModelProperty(name="ps",value="交易方向Code",example="T01")
    private String ps;

    @ApiModelProperty(name="psStr",value="交易方向",example="T01")
    private String psStr;

    @ApiModelProperty(name="settleSpeed",value="清算速度Code",example="1")
    private String settleSpeed;

    @ApiModelProperty(name="settleSpeedStr",value="清算速度",example="1")
    private String settleSpeedStr;

    @ApiModelProperty(name="secManageAcctId",value="证券账户ID",example="1")
    private String secManageAcctId;

    @ApiModelProperty(name="secManageAcctIdName",value="证券账户",example="1")
    private String secManageAcctIdStr;

    @ApiModelProperty(name="faceValue",value="券面总额",example="1")
    private BigDecimal faceValue;
    @ApiModelProperty(name="tradeYield",value="成交价/回购利率",example="1")
    private BigDecimal tradeYield;

    @ApiModelProperty(name="termDays",value="期限（天）",example="1")
    private BigDecimal termDays;

    @ApiModelProperty(name="tradeAmt",value="交易金额",example="1")
    private BigDecimal tradeAmt;

    @ApiModelProperty(name="quoteId",value="报价编号")
    private String quoteId;


    @ApiModelProperty(name="traderid",value="本方id",example="字符串1")
    private String traderid;

    @ApiModelProperty(name="traderidStr",value="本方",example="字符串1")
    private String traderidStr;

    @ApiModelProperty(name="trader",value="交易员",example="admin")
    private String trader;

    @ApiModelProperty(name="counterId",value="对手方Id",example="admin")
    private String counterId;

    @ApiModelProperty(name="counterIdStr",value="对手方",example="admin")
    private String counterIdStr;

    @ApiModelProperty(name="counterTrader",value="对手方交易员",example="字符串1")
    private String counterTrader;

    @ApiModelProperty(name="clearTypeStr",value="清算类型",example="字符串1")
    private String clearTypeStr;

    @ApiModelProperty(name="clearType",value="清算类型Code",example="字符串1")
    private String clearType;

    @ApiModelProperty(name="tradeDate",value="成交日期")
    private Date tradeDate;

    @ApiModelProperty(name="remark",value="备注",example="字符串1")
    private String remark;

    @ApiModelProperty(name="buyId",value="买方",example="字符串1")
    private String buyId;
    @ApiModelProperty(name="buyStr",value="买方Str",example="字符串1")
    private String buyStr;

    @ApiModelProperty(name="sellId",value="卖方",example="字符串1")
    private String sellId;

    @ApiModelProperty(name="sellStr",value="卖方Str",example="字符串1")
    private String sellStr;

    @ApiModelProperty(name="unitCprice",value="单位净价",example="字符串1")
    private BigDecimal unitCprice;

    @ApiModelProperty(name="yield",value="到期收益率",example="字符串1")
    private BigDecimal yield;

    @ApiModelProperty(name="exerYield",value="行权收益率",example="字符串1")
    private BigDecimal exerYield;

    @ApiModelProperty(name="settleDate",value="结算日期",example="字符串1")
    private Date settleDate;

    @ApiModelProperty(name="deliveryTypeStr",value="结算方式",example="字符串1")
    private String deliveryTypeStr;

    @ApiModelProperty(name="deliveryType",value="结算方式Code",example="字符串1")
    private String deliveryType;

    @ApiModelProperty(name="unitFprice",value="全价(元)",example="字符串1")
    private BigDecimal unitFprice;

    @ApiModelProperty(name="unitInt",value="应计利息(元)",example="字符串1")
    private BigDecimal unitInt;

    @ApiModelProperty(name="intAmt",value="应计利息总额（元）",example="字符串1")
    private BigDecimal intAmt;

    @ApiModelProperty(name="institutionb",value="机构",example="字符串1")
    private String institutionb;
    @ApiModelProperty(name="institutionbStr",value="机构Str",example="字符串1")
    private String institutionbStr;

    @ApiModelProperty(name="institutions",value="机构",example="字符串1")
    private String institutions;
    @ApiModelProperty(name="institutionsStr",value="机构",example="字符串1")
    private String institutionsStr;

    @ApiModelProperty(name="traderb",value="交易员",example="字符串1")
    private String traderb;
    @ApiModelProperty(name="traders",value="交易员",example="字符串1")
    private String traders;

    @ApiModelProperty(name="capitalacctName",value="资金账户户名",example="字符串1")
    private String capitalacctNameb;

    @ApiModelProperty(name="capitalacctNumber",value="资金账号",example="字符串1")
    private String capitalacctNumberb;

    @ApiModelProperty(name="depositAcctName",value="托管账户户名",example="字符串1")
    private String depositAcctNameb;

    @ApiModelProperty(name="depositAcctNumber",value="托管账号",example="字符串1")
    private String depositAcctNumberb;

    @ApiModelProperty(name="depositAcctOrg",value="托管机构",example="字符串1")
    private String depositAcctOrgb;

    @ApiModelProperty(name="capitalacctName",value="资金账户户名",example="字符串1")
    private String capitalacctNames;

    @ApiModelProperty(name="capitalacctNumber",value="资金账号",example="字符串1")
    private String capitalacctNumbers;

    @ApiModelProperty(name="depositAcctName",value="托管账户户名",example="字符串1")
    private String depositAcctNames;

    @ApiModelProperty(name="depositAcctNumber",value="托管账号",example="字符串1")
    private String depositAcctNumbers;

    @ApiModelProperty(name="depositAcctOrg",value="托管机构",example="字符串1")
    private String depositAcctOrgs;

    @ApiModelProperty(name="rsRepo",value="正回购方",example="字符串1")
    private String rsRepo;

    @ApiModelProperty(name="rbRepo",value="逆回购方",example="字符串1")
    private String rbRepo;
    @ApiModelProperty(name="rsRepo",value="正回购方Str",example="字符串1")
    private String rsRepoStr;

    @ApiModelProperty(name="rbRepo",value="逆回购方Str",example="字符串1")
    private String rbRepoStr;

    @ApiModelProperty(name="repoTerm",value="回购期限（天）",example="1")
    private BigDecimal repoTerm;

    @ApiModelProperty(name="mtradeAmt",value="到期结算金额",example="字符串1")
    private BigDecimal   mtradeAmt;

    @ApiModelProperty(name="repoType",value="回购方式",example="字符串1")
    private String repoType;

    @ApiModelProperty(name="vSettleDate",value="首次结算日",example="字符串1")
    private Date   vsettleDate;

    @ApiModelProperty(name="mSettleDate",value="到期结算日",example="字符串1")
    private Date   msettleDate;

    @ApiModelProperty(name="actRepoTerm",value="实际占款天数",example="字符串1")
    private BigDecimal   actRepoTerm;

    @ApiModelProperty(name="termType",value="期限品种",example="ZC1")
    private String termType;

    @ApiModelProperty(name="mdeliveryTypeStr",value="到期结算方式",example="字符串1")
    private String mdeliveryTypeStr;






    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.CSTP匹配状态, getMatchStatus());
    }

    public void setMatchStatusStr(String matchStatusStr) {
        this.matchStatusStr = matchStatusStr;
    }

    public String getExecid() {
        return execid;
    }

    public void setExecid(String execid) {
        this.execid = execid;
    }

    public String getBusinesstypeStr() {
        if(StringUtil.equals("BOND",getBusinesstype())){
            return "现券买卖";
        }else{
            return "质押式回购";
        }
        //return SysDataUtil.getNameFromDataDict(DictCode.CSTP资产类型, getBusinesstype());
    }

    public void setBusinesstypeStr(String businesstypeStr) {
        this.businesstypeStr = businesstypeStr;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
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

    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getPs());
    }

    public void setPsStr(String psStr) {
        this.psStr = psStr;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getSettleSpeedStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, getSettleSpeed());
    }

    public void setSettleSpeedStr(String settleSpeedStr) {
        this.settleSpeedStr = settleSpeedStr;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    public String getSecManageAcctIdStr() {
        return secManageAcctIdStr;
    }

    public void setSecManageAcctIdStr(String secManageAcctIdStr) {
        this.secManageAcctIdStr = secManageAcctIdStr;
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

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getTraderid() {
        return traderid;
    }

    public void setTraderid(String traderid) {
        this.traderid = traderid;
    }

    public String getTraderidStr() {
        return traderidStr;
    }

    public void setTraderidStr(String traderidStr) {
        this.traderidStr = traderidStr;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getCounterIdStr() {
        return counterIdStr;
    }

    public void setCounterIdStr(String counterIdStr) {
        this.counterIdStr = counterIdStr;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getClearTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算类型, getClearType());
    }

    public void setClearTypeStr(String clearTypeStr) {
        this.clearTypeStr = clearTypeStr;
    }

    public String getClearType() {
        return clearType;
    }

    public void setClearType(String clearType) {
        this.clearType = clearType;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getBuyStr() {
        return buyStr;
    }

    public void setBuyStr(String buyStr) {
        this.buyStr = buyStr;
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }

    public String getSellStr() {
        return sellStr;
    }

    public void setSellStr(String sellStr) {
        this.sellStr = sellStr;
    }
}
