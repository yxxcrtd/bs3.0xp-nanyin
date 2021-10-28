/*
 * 文件名：BussCombDistribtDto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.etrdispatcher.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

import java.math.BigDecimal;

/**
 * 场外指令分发
 * 指令列表分页查询DTO
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherTradeListResponse
 * @since 1.0
 */

public class CfetsEtrDispatcherTradeListResponse extends EtrEntrust {

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 交易方向（画面端用）
     */
    private String otherPs;

    /**
     * 回购/拆借方向
     */
    private String busiPs;

    /**
     * 完成额度
     */
    private BigDecimal finishAmt;

    /**
     * 委托指令分发 指定交易员
     */
    private String trader;

    /**
     * 证券账户
     */
    private String secAcctId;
    /**
     * 清算速度
     */
    private String settleSpeed;

    /**
     * 期限（天）
     */
    private String termDays;

    /**
     * 有效状态
     */
    private String effectFlag;

    /**
     * 指令价格
     */
    private String etrPrice;

    /**
     *执行进度%
     */
    private BigDecimal exeProgress;

    /**
     *成交进度%
     */
    private BigDecimal dealProgress;

    /**
     * 获取 otherEntrustStatus
     * @return otherEntrustStatus.
     */
    public String getOtherEntrustStatus() {
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }

    /**
     * 获取资产类型 otherFinprodType2
     * @return otherFinprodType2 资产类型对应name
     */
    public String getOtherFinprodType2() {
        if (StringUtil.equals(super.getFinprodType2(), BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, BussinessDictCodeList.平安资产类型_一级投标债券.getValue());
        }
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, super.getFinprodType2());
    }

    /**
     * 获取 otherTradeMarket
     * @return otherTradeMarket.
     */
    public String getOtherTradeMarket() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }

    /**
     * 获取 otherCcy
     * @return otherCcy.
     */
    public String getOtherCcy() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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

    public String getOtherPs() {
        return otherPs;
    }

    public void setOtherPs(String otherPs) {
        this.otherPs = otherPs;
    }

    public String getBusiPs() {
        return busiPs;
    }

    public void setBusiPs(String busiPs) {
        this.busiPs = busiPs;
    }

    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getTermDays() {
        return termDays;
    }

    public void setTermDays(String termDays) {
        this.termDays = termDays;
    }

    public String getEffectFlag() {
        return effectFlag;
    }

    public void setEffectFlag(String effectFlag) {
        this.effectFlag = effectFlag;
    }

    public String getEtrPrice() {
        return etrPrice;
    }

    public void setEtrPrice(String etrPrice) {
        this.etrPrice = etrPrice;
    }

    public BigDecimal getExeProgress() {
        return exeProgress;
    }

    public void setExeProgress(BigDecimal exeProgress) {
        this.exeProgress = exeProgress;
    }

    public BigDecimal getDealProgress() {
        return dealProgress;
    }

    public void setDealProgress(BigDecimal dealProgress) {
        this.dealProgress = dealProgress;
    }
}
