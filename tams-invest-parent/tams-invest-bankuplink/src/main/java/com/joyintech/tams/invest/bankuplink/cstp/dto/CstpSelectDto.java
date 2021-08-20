/*
 * 文件名：CstpSelectDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.bankuplink.cstp.dto;

import com.joyintech.tams.common.base.entity.InterfCstpTrade;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;

/**
 * cstp下行交易Dto
 * @author fanshuanghu
 * @version 1.0 2018年12月4日
 * @see CstpSelectDto
 * @since 1.0
 */
public class CstpSelectDto extends InterfCstpTrade{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7604289208626153062L;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 匹配状态
     */
    private String matchStatus;
    /**
     * 证券托管户
     */
    private String acctNo;
    /**
     * 交割日期
     */
    private Date settleDate;
    /**
     * 获取资产类型字符串
     * @return 资产类型
     * @see
     * @since 1.0
     */
    public String getBusinesstypeStr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易类型_CSTP, super.getBusinesstype());
    }
    /**
     * 获取交易方向中文
     * @return 交易方向
     * @see
     * @since 1.0
     */
    public String getPsStr(){
        if(StringUtil.isNotEmpty(super.getSide()) && super.getSide().startsWith("T")){
            return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, super.getSide());
        }else if(StringUtil.isNotEmpty(super.getSide()) && (super.getSide().equals("01")|| super.getSide().equals("02"))){
        	return SysDataUtil.getNameFromDataDict(BussinessDictCode.互换方式, super.getSide());
        }else{
            return SysDataUtil.getNameFromDataDict(BussinessDictCode.回购拆借方向, super.getSide());
        }
    }
    /**
     * 获取匹配状态字符串
     * @return 匹配状态
     * @see
     * @since 1.0
     */
    public String getMatchStatusStr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.匹配状态, this.getMatchStatus());
    }
    /**
     * 获取日期yyyy-mm-dd格式
     * @return 交易日期字符串
     * @see
     * @since 1.0
     */
    public String getTradeDateStr(){
        if(StringUtil.isNotEmpty(super.getTradedate()) && super.getTradedate().length()>=8){
            return super.getTradedate().substring(0, 4) + "-" + super.getTradedate().substring(4, 6) + "-" + super.getTradedate().substring(6, 8);
        }
        return super.getTradedate();
    }
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }
    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    /**
     * 获取 matchStatus
     * @return matchStatus.
     */
    public String getMatchStatus() {
        return matchStatus;
    }
    
    /**
     * 设置 matchStatus
     * @param matchStatus matchStatus
     */
    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }
    
    /**
     * 获取 acctNo
     * @return acctNo.
     */
    public String getAcctNo() {
        return acctNo;
    }
    
    /**
     * 设置 acctNo
     * @param acctNo acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
    
    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }
    
    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getSettleDateStr(){
        return DateUtil.format(this.getSettleDate());
    }
    
    /**
     * 获取 tradelimitdaysStr
     * @return tradelimitdaysStr.
     */
    public String getTradelimitdaysStr() {
        if(super.getTradelimitdays() != null) {
            if(StringUtils.equals(super.getTradelimitdays().toString(), "1000000")) {
                return BussinessDictCodeList.期限品种_利率互换_1Y.getValue();
            }
            else if(StringUtils.equals(super.getTradelimitdays().toString(), "2000000")) {
                return BussinessDictCodeList.期限品种_利率互换_2Y.getValue();
            }
            else if(StringUtils.equals(super.getTradelimitdays().toString(), "6000")) {
                return BussinessDictCodeList.期限品种_利率互换_6M.getValue();
            }
            else if(StringUtils.equals(super.getTradelimitdays().toString(), "3000")) {
                return BussinessDictCodeList.期限品种_利率互换_3M.getValue();
            }
            else if(StringUtils.equals(super.getTradelimitdays().toString(), "1000")) {
                return BussinessDictCodeList.期限品种_利率互换_1M.getValue();
            }
            else if(StringUtils.equals(super.getTradelimitdays().toString(), "9000")) {
                return BussinessDictCodeList.期限品种_利率互换_9M.getValue();
            }
            return super.getTradelimitdays().toString();
        }
        return "";
    }
    
    
    /**
     * 获取 AccruedinterestamtStr
     * @return AccruedinterestamtStr.
     */
    public String getAccruedinterestamtStr() {
        if(super.getAccruedinterestamt() != null) {
        	return super.getAccruedinterestamt().setScale(5, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
        return "";
    }
    
    /**
     * 获取 getLastqty
     * @return getLastqty.
     */
    public String getLastqtyStr() {
        if(super.getLastqty() != null) {
        	DecimalFormat df4 = new DecimalFormat("#,##0.00");
        	return df4.format(super.getLastqty());
        }
        return "";
    }
    
    /**
     * 获取 getPrice
     * @return getPrice.
     */
    public String getPriceStr() {
        if(super.getPrice() != null) {
        	return super.getPrice().setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
        return "";
    }
    
    /**
     * 获取 getPrice
     * @return getPrice.
     */
    public String getPriceTxt() {
        if(super.getPrice() != null) {
        	return super.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
        return "";
    }
    
    /**
     * 获取 TradecashamtStr
     * @return TradecashamtStr.
     */
    public String getTradecashamtStr() {
        if(super.getTradecashamt() != null) {
        	DecimalFormat df4 = new DecimalFormat("#,##0.00");
        	return df4.format(super.getTradecashamt());
        }
        return "";
    }
    
 // add by etliu 20210319 银行间上下行多组合 start
    /**
     * 结算金额
     * @return 结算金额
     */
    public String getSettlcurramtStr() {
        if(super.getSettlcurramt() != null) {
            DecimalFormat df4 = new DecimalFormat("#,##0.00");
            return df4.format(super.getSettlcurramt());
        }
        return "";
    }
 // add by etliu 20210319 银行间上下行多组合 end

    //add by chenjiaxing 质押式回购改造 start
    /**
     * 对手方交易员名称
     */
    private String counterTraderName;

    /**
     * 首次结算方式
     */
    private Integer deliveryType;

    /**
     * 到期结算方式
     */
    private Integer deliveryType2;

    /**
     * 交易方式
     */
    private String tradeMethod;


    /**
     * 成交状态
     */
    private String execType;

    /**
     * 债券币种
     */
    private String ccy;

    /**
     * 清算类型
     */
    private String settType;

    /**
     * 净额清算状态
     */
    private String settleStatus;

    /**
     * 回购方式
     */
    private String repoMethod;

    /**
     * 结算状态
     */
    private String settleStatus2;

    /**
     * 汇率
     */
    private BigDecimal exchangerate;

    /**
     * 回复编号
     */
    private BigDecimal shareId;
    /**
     * 对手方机构名
     */
    private String cpPartyName;
    /**
     * 折算比例%
     */
    private BigDecimal pledgeRate;

    /**
     * 代理方交易员名称
     */
    private String otherTraderName;

    public String getOtherTraderName() {
        return otherTraderName;
    }

    public void setOtherTraderName(String otherTraderName) {
        this.otherTraderName = otherTraderName;
    }

    public BigDecimal getPledgeRate() {
        return pledgeRate;
    }

    public BigDecimal getPledgeRateStr() {
        return pledgeRate.multiply(new BigDecimal("100"));
    }

    public void setPledgeRate(BigDecimal pledgeRate) {
        this.pledgeRate = pledgeRate;
    }

    public String getCpPartyName() {
        return cpPartyName;
    }

    public void setCpPartyName(String cpPartyName) {
        this.cpPartyName = cpPartyName;
    }

    public String getCounterTraderName() {
        return counterTraderName;
    }

    public void setCounterTraderName(String counterTraderName) {
        this.counterTraderName = counterTraderName;
    }


    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getDeliveryType2() {
        return deliveryType2;
    }

    public void setDeliveryType2(Integer deliveryType2) {
        this.deliveryType2 = deliveryType2;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }


    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getSettType() {
        return settType;
    }

    public void setSettType(String settType) {
        this.settType = settType;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getRepoMethod() {
        return repoMethod;
    }

    public void setRepoMethod(String repoMethod) {
        this.repoMethod = repoMethod;
    }

    public String getSettleStatus2() {
        return settleStatus2;
    }

    public void setSettleStatus2(String settleStatus2) {
        this.settleStatus2 = settleStatus2;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    public BigDecimal getShareId() {
        return shareId;
    }

    public void setShareId(BigDecimal shareId) {
        this.shareId = shareId;
    }
    /**
     * 清算速度
     */
    private String settleSpeed;

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 清算速度名称
     * @return settleSpeedStr
     */
    public String getSettleSpeedStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpeed());
    }
    /**
     * 获取 清算速度名称
     * @return settleSpeedStr
     */
    public String getDeliveryTypeStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.结算方式, this.getDeliveryType().toString());
    }
    public String getDeliveryType2Str() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.结算方式, this.getDeliveryType2().toString());
    }
    public String getExecTypeStr() {
        if(StringUtil.equals(this.getExecType(),"F")){
            return "成交";
        }
        if(StringUtil.equals(this.getExecType(),"4")){
            return "撤销";
        }
        if(StringUtil.equals(this.getExecType(),"5")){
            return "修改";
        }
        if(StringUtil.equals(this.getExecType(),"101")){
            return "应急";
        }
        return execType;
    }
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }
    /**
     * 取得 结算币种
     * @return 结算币种
     */
    public String getSettlcurrencyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getSettlcurrency());
    }
    public String getRepoMethodStr() {
        return "双边回购";
    }
    public String getSettTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算类型, this.getSettType());
    }

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 成交编号
     */
    private String fTradeId;
    /**
     * 反向交易编号
     */
    private String rTradeId;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getfTradeId() {
        return fTradeId;
    }

    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId;
    }

    public String getrTradeId() {
        return rTradeId;
    }

    public void setrTradeId(String rTradeId) {
        this.rTradeId = rTradeId;
    }

    //add by chenjiaxing 质押式回购改造 end
}
