/*
 * 文件名：EtrExchangeTradeQuotation001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrexchangetrade.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 *  行情信息(左上)
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2020年7月6日
 * @see EtrExchangeTradeQuotation001Dto
 * @since 1.0
 */
public class EtrExchangeTradeQuotation001Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3535451000355136182L;
   
    /**
     * 证券代码
     */
    private String secId;
    
    /**
     * 证券名称
     */
    private String secName;
    
    /**
     * 行情类型
     */
    private String quotationType;

    /**
     * 方向(枚举：买；卖)
     */
    private String entrustPs;
    
    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustNum;
    
    /**
     * 价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustPrice;
    
    /**
     * 对方席位(深交所显示)
     */
    private String oppositeSeat;
    
    /**
     * 约定号(深交所显示)
     */
    private String agreeNum;
    
    /**
     * 联系人
     */
    private String contacts;
    
    /**
     * 联系号码
     */
    private String contactNumber;
    
    /**
     * 收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closingPrice;
    
    /**
     * 日均价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dailyAvgPrice;
    
    /**
     * 净价价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal nPrice;
    
    /**
     * 全价价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fPrice;


    /**
     * 收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 订单编号
     */
    private String orderId;
    
    //质押式协议回购

    /**
     * 请求号
     */
    private String requestId;
    
    /**
     * 请求类型
     */
    private String requestType;
    
    /**
     * 质押券代码
     */
    private String standardBondId;
    /**
     * 质押券名称
     */
    private String standardBondName;
    
    /**
     * 原质押代码
     */
    private String pStandardBondName;
    
    /**
     * 质押数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal standardBondNum;
    
    /**
     * 质押券面总额合计
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal standardBondTotal;
    
    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeRatio;
    
    /**
     * 回购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repoAmt;
    
    /**
     * 回购利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repoRate;
    
    /**
     * 回购期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repoTerm;

    /**
     * 回购期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actCapDays;


    /**
     * 交易场所代码
     */
    private String market;

    
    
    /**
     * 获取 quotationType
     * @return quotationType.
     */
    public String getQuotationType() {
        return quotationType;
    }

    
    /**
     * 设置 quotationType
     * @param quotationType quotationType
     */
    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }

    
    /**
     * 获取 entrustPs
     * @return entrustPs.
     */
    public String getEntrustPs() {
        return entrustPs;
    }

    
    /**
     * 设置 entrustPs
     * @param entrustPs entrustPs
     */
    public void setEntrustPs(String entrustPs) {
        this.entrustPs = entrustPs;
    }


    
    /**
     * 获取 secId
     * @return secId.
     */
    public String getSecId() {
        return secId;
    }


    
    /**
     * 设置 secId
     * @param secId secId
     */
    public void setSecId(String secId) {
        this.secId = secId;
    }


    
    /**
     * 获取 secName
     * @return secName.
     */
    public String getSecName() {
        return secName;
    }


    
    /**
     * 设置 secName
     * @param secName secName
     */
    public void setSecName(String secName) {
        this.secName = secName;
    }


    
    /**
     * 获取 entrustNum
     * @return entrustNum.
     */
    public BigDecimal getEntrustNum() {
        return entrustNum;
    }


    
    /**
     * 设置 entrustNum
     * @param entrustNum entrustNum
     */
    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }


    
    /**
     * 获取 entrustPrice
     * @return entrustPrice.
     */
    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }


    
    /**
     * 设置 entrustPrice
     * @param entrustPrice entrustPrice
     */
    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }


    
    /**
     * 获取 oppositeSeat
     * @return oppositeSeat.
     */
    public String getOppositeSeat() {
        return oppositeSeat;
    }


    
    /**
     * 设置 oppositeSeat
     * @param oppositeSeat oppositeSeat
     */
    public void setOppositeSeat(String oppositeSeat) {
        this.oppositeSeat = oppositeSeat;
    }


    
    /**
     * 获取 agreeNum
     * @return agreeNum.
     */
    public String getAgreeNum() {
        return agreeNum;
    }


    
    /**
     * 设置 agreeNum
     * @param agreeNum agreeNum
     */
    public void setAgreeNum(String agreeNum) {
        this.agreeNum = agreeNum;
    }


    
    /**
     * 获取 contacts
     * @return contacts.
     */
    public String getContacts() {
        return contacts;
    }


    
    /**
     * 设置 contacts
     * @param contacts contacts
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }


    
    /**
     * 获取 contactNumber
     * @return contactNumber.
     */
    public String getContactNumber() {
        return contactNumber;
    }


    
    /**
     * 设置 contactNumber
     * @param contactNumber contactNumber
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    
    /**
     * 获取 closingPrice
     * @return closingPrice.
     */
    public BigDecimal getClosingPrice() {
        return closingPrice;
    }


    
    /**
     * 设置 closingPrice
     * @param closingPrice closingPrice
     */
    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }


    
    /**
     * 获取 dailyAvgPrice
     * @return dailyAvgPrice.
     */
    public BigDecimal getDailyAvgPrice() {
        return dailyAvgPrice;
    }


    
    /**
     * 设置 dailyAvgPrice
     * @param dailyAvgPrice dailyAvgPrice
     */
    public void setDailyAvgPrice(BigDecimal dailyAvgPrice) {
        this.dailyAvgPrice = dailyAvgPrice;
    }


    
    /**
     * 获取 nPrice
     * @return nPrice.
     */
    public BigDecimal getnPrice() {
        return nPrice;
    }


    
    /**
     * 设置 nPrice
     * @param nPrice nPrice
     */
    public void setnPrice(BigDecimal nPrice) {
        this.nPrice = nPrice;
    }


    


    
    /**
     * 获取 yield
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }


    
    /**
     * 设置 yield
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }


    
    /**
     * 获取 orderId
     * @return orderId.
     */
    public String getOrderId() {
        return orderId;
    }


    
    /**
     * 设置 orderId
     * @param orderId orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    
    
    /**
     * 获取 fPrice
     * @return fPrice.
     */
    public BigDecimal getfPrice() {
        return fPrice;
    }


    
    /**
     * 设置 fPrice
     * @param fPrice fPrice
     */
    public void setfPrice(BigDecimal fPrice) {
        this.fPrice = fPrice;
    }


    /**
     * 获取 requestId
     * @return requestId.
     */
    public String getRequestId() {
        return requestId;
    }


    /**
     * 设置 requestId
     * @param requestId requestId
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    
    /**
     * 获取 requestType
     * @return requestType.
     */
    public String getRequestType() {
        return requestType;
    }


    
    /**
     * 设置 requestType
     * @param requestType requestType
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }


    
    /**
     * 获取 standardBondId
     * @return standardBondId.
     */
    public String getStandardBondId() {
        return standardBondId;
    }


    
    /**
     * 设置 standardBondId
     * @param standardBondId standardBondId
     */
    public void setStandardBondId(String standardBondId) {
        this.standardBondId = standardBondId;
    }


    
    /**
     * 获取 standardBondName
     * @return standardBondName.
     */
    public String getStandardBondName() {
        return standardBondName;
    }


    
    /**
     * 设置 standardBondName
     * @param standardBondName standardBondName
     */
    public void setStandardBondName(String standardBondName) {
        this.standardBondName = standardBondName;
    }


    
    /**
     * 获取 pStandardBondName
     * @return pStandardBondName.
     */
    public String getpStandardBondName() {
        return pStandardBondName;
    }


    
    /**
     * 设置 pStandardBondName
     * @param pStandardBondName pStandardBondName
     */
    public void setpStandardBondName(String pStandardBondName) {
        this.pStandardBondName = pStandardBondName;
    }


    
    /**
     * 获取 standardBondNum
     * @return standardBondNum.
     */
    public BigDecimal getStandardBondNum() {
        return standardBondNum;
    }


    
    /**
     * 设置 standardBondNum
     * @param standardBondNum standardBondNum
     */
    public void setStandardBondNum(BigDecimal standardBondNum) {
        this.standardBondNum = standardBondNum;
    }


    
    /**
     * 获取 standardBondTotal
     * @return standardBondTotal.
     */
    public BigDecimal getStandardBondTotal() {
        return standardBondTotal;
    }


    
    /**
     * 设置 standardBondTotal
     * @param standardBondTotal standardBondTotal
     */
    public void setStandardBondTotal(BigDecimal standardBondTotal) {
        this.standardBondTotal = standardBondTotal;
    }


    
    /**
     * 获取 pledgeRatio
     * @return pledgeRatio.
     */
    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }


    
    /**
     * 设置 pledgeRatio
     * @param pledgeRatio pledgeRatio
     */
    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }


    
    /**
     * 获取 repoAmt
     * @return repoAmt.
     */
    public BigDecimal getRepoAmt() {
        return repoAmt;
    }


    
    /**
     * 设置 repoAmt
     * @param repoAmt repoAmt
     */
    public void setRepoAmt(BigDecimal repoAmt) {
        this.repoAmt = repoAmt;
    }


    
    /**
     * 获取 repoRate
     * @return repoRate.
     */
    public BigDecimal getRepoRate() {
        return repoRate;
    }


    
    /**
     * 设置 repoRate
     * @param repoRate repoRate
     */
    public void setRepoRate(BigDecimal repoRate) {
        this.repoRate = repoRate;
    }


    
    /**
     * 获取 repoTerm
     * @return repoTerm.
     */
    public BigDecimal getRepoTerm() {
        return repoTerm;
    }


    
    /**
     * 设置 repoTerm
     * @param repoTerm repoTerm
     */
    public void setRepoTerm(BigDecimal repoTerm) {
        this.repoTerm = repoTerm;
    }


    
    /**
     * 获取 actCapDays
     * @return actCapDays.
     */
    public BigDecimal getActCapDays() {
        return actCapDays;
    }


    
    /**
     * 设置 actCapDays
     * @param actCapDays actCapDays
     */
    public void setActCapDays(BigDecimal actCapDays) {
        this.actCapDays = actCapDays;
    }

    /**
     * 获取 quotationType
     * @return quotationType.
     */
    public String getQuotationTypeStr() {
        if(StringUtil.isNotBlank(this.getQuotationType())) {
            return SysDataUtil.getNameFromDataDict(DictCode.价格模式, this.getQuotationType());
        }else {
            return "";
        }
    }


    public String getEntrustPsStr() {
        if(StringUtil.isNotBlank(this.getEntrustPs())) {
            return SysDataUtil.getNameFromDataDict(DictCode.交易方向, this.getEntrustPs());
        }else {
            return "";
        }
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
