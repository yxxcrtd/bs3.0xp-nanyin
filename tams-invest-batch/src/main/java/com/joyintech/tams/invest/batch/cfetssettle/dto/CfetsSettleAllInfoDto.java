/*
 * 文件名：MstIntRateInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.cfetssettle.dto;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 *  自动交收
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2021年11月19日
 * @see CfetsSettleAllInfoDto
 * @since 1.0
 */
public class CfetsSettleAllInfoDto {

    //原业务编号，汇总交易编号
    private String totalTradeId;
    //交易日期
    private String tradeDate;
    //交收日期
    private String settleDate;
    //金融产品代码
    private String finprodId;
    //资产简称
    private String finprodAbbr;
    //资产类型CODE
    private String finprodType2;
    //交易方向CODE
    private String ps;
    //汇总交收数量
    private BigDecimal settleNumT;
    //汇总交收金额
    private BigDecimal settleAmtT;
    //清算状态CODE
    private String cleanStatus;
    //交易市场CODE
    private String tradeMarket;
    //交易平台
    private String tradePlat;
    //证券账户
    private String secAcctId;
    //交收编号
    private String settleSeqNo;
    //投管业务类型
    private String tgBusiType;

    @Valid
    private List<CfetsSettleDetailInfoDto> cfetsSettleDetailInfoDtoList;

//    //交易编号
//    private String tradeId;
//    //组合交收数量
//    private BigDecimal settleNum;
//    //组合交收金额
//    private BigDecimal settleAmt;
//    //组合代码
//    private String portfolioId;

//
//    @ApiModelProperty(name="finprodMarketId",value="资产简称",example="字符串1")
//    private String finprodMarketId;
//
//
//
//    @ApiModelProperty(name="finprodType2Str",value="资产类型",example="A1")
//    private String finprodType2Str;
//
//    @ApiModelProperty(name="tgBusiType",value="业务类型CODE",example="A1")
//    private String tgBusiType;
//
//    @ApiModelProperty(name="tgBusiTypeStr",value="业务类型",example="A1")
//    private String tgBusiTypeStr;
//
//
//    @ApiModelProperty(name="psStr",value="交易方向",example="A1")
//    private String psStr;
//
//
//
//    @ApiModelProperty(name="portfolioId",value="组合名称集合",example="字符串1")
//    private String portfolioId;
//
//    @ApiModelProperty(name="portfolioName",value="组合名称",example="字符串1")
//    private String portfolioName;
//
//
//
//    @ApiModelProperty(name="cleanStatusStr",value="清算状态",example="A1,A2")
//    private String cleanStatusStr;
//
//    @ApiModelProperty(name="tradePlat",value="交易场所CODE",example="A1")
//    private String tradePlat;
//
//    @ApiModelProperty(name="tradePlatStr",value="交易场所",example="银行间")
//    private String tradePlatStr;
//
//
//
//    @ApiModelProperty(name="tradeMarketStr",value="交易市场",example="银行间")
//    private String tradeMarketStr;
//
//
//
//    @ApiModelProperty(name="counterName",value="交易对手",example="admin")
//    private String counterName;
//
//    @ApiModelProperty(name="cleanStlStatus",value="结算指令状态CODE",example="A1")
//    private String cleanStlStatus;
//
//    @ApiModelProperty(name="cleanStlStatusStr",value="结算指令状态",example="A1")
//    private String cleanStlStatusStr;
//
//    @ApiModelProperty(name="cleanArgStatus",value="结算合同状态CODE",example="A1")
//    private String cleanArgStatus;
//
//    @ApiModelProperty(name="cleanArgStatusStr",value="结算合同状态",example="A1")
//    private String cleanArgStatusStr;
//
//    @ApiModelProperty(name="settleSeqNo",value="交收编号",example="A1")
//    private String settleSeqNo;
//
//
//
//    @ApiModelProperty(name="ccy",value="币种",example="不展示仅供后台使用")
//    private String ccy;
//
//    @ApiModelProperty(name="seqNo",value="交收流水序号",example="不展示仅供后台使用")
//    private String seqNo;


    public String getTotalTradeId() {
        return totalTradeId;
    }

    public void setTotalTradeId(String totalTradeId) {
        this.totalTradeId = totalTradeId;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public BigDecimal getSettleNumT() {
        return settleNumT;
    }

    public void setSettleNumT(BigDecimal settleNumT) {
        this.settleNumT = settleNumT;
    }

    public BigDecimal getSettleAmtT() {
        return settleAmtT;
    }

    public void setSettleAmtT(BigDecimal settleAmtT) {
        this.settleAmtT = settleAmtT;
    }

    public String getCleanStatus() {
        return cleanStatus;
    }

    public void setCleanStatus(String cleanStatus) {
        this.cleanStatus = cleanStatus;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getSettleSeqNo() {
        return settleSeqNo;
    }

    public void setSettleSeqNo(String settleSeqNo) {
        this.settleSeqNo = settleSeqNo;
    }

    public String getTgBusiType() {
        return tgBusiType;
    }

    public void setTgBusiType(String tgBusiType) {
        this.tgBusiType = tgBusiType;
    }

    public List<CfetsSettleDetailInfoDto> getCfetsSettleDetailInfoDtoList() {
        return cfetsSettleDetailInfoDtoList;
    }

    public void setCfetsSettleDetailInfoDtoList(List<CfetsSettleDetailInfoDto> cfetsSettleDetailInfoDtoList) {
        this.cfetsSettleDetailInfoDtoList = cfetsSettleDetailInfoDtoList;
    }
}
