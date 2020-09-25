/*
 * 文件名：TrdLendInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.trdrepotrade.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TrdRepoTradeExportMailDto {

    /**
     * 最多质押品个数
     */
    private int maxPledgeCount;

    /**
     * 交易对手
     */
    private String counter;

    /**
     * 回购期限
     */
    private String termDay;

    /**
     * 回购利率
     */
    private String rebackPrice;

    /**
     * 交易本金
     */
    private String prinAmt;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 质押券信息List
     */
    private List<String> lstFinDealPledgeResult;

    /**
     * 获取 maxPledgeCount
     *
     * @rturn maxPledgeCount
     */
    public int getMaxPledgeCount() {
        return this.maxPledgeCount;
    }

    /**
     * 设置 maxPledgeCount
     *
     * @param maxPledgeCount maxPledgeCount
     */
    public void setMaxPledgeCount(int maxPledgeCount) {
        this.maxPledgeCount = maxPledgeCount;
    }

    /**
     * 获取 counter
     *
     * @rturn counter
     */
    public String getCounter() {
        return this.counter;
    }

    /**
     * 设置 counter
     *
     * @param counter counter
     */
    public void setCounter(String counter) {
        this.counter = counter;
    }

    /**
     * 获取 termDay
     *
     * @rturn termDay
     */
    public String getTermDay() {
        return this.termDay;
    }

    /**
     * 设置 termDay
     *
     * @param termDay termDay
     */
    public void setTermDay(String termDay) {
        this.termDay = termDay;
    }

    /**
     * 获取 rebackPrice
     *
     * @rturn rebackPrice
     */
    public String getRebackPrice() {
        return this.rebackPrice;
    }

    /**
     * 设置 rebackPrice
     *
     * @param rebackPrice rebackPrice
     */
    public void setRebackPrice(String rebackPrice) {
        this.rebackPrice = rebackPrice;
    }

    /**
     * 获取 prinAmt
     *
     * @rturn prinAmt
     */
    public String getPrinAmt() {
        return this.prinAmt;
    }

    /**
     * 设置 prinAmt
     *
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(String prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 vdate
     *
     * @rturn vdate
     */
    public String getVdate() {
        return this.vdate;
    }

    /**
     * 设置 vdate
     *
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     *
     * @rturn mdate
     */
    public String getMdate() {
        return this.mdate;
    }

    /**
     * 设置 mdate
     *
     * @param mdate mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 lstFinDealPledgeResult
     *
     * @rturn lstFinDealPledgeResult
     */
    public List<String> getLstFinDealPledgeResult() {
        return this.lstFinDealPledgeResult;
    }

    /**
     * 设置 lstFinDealPledgeResult
     *
     * @param lstFinDealPledgeResult lstFinDealPledgeResult
     */
    public void setLstFinDealPledgeResult(List<String> lstFinDealPledgeResult) {
        this.lstFinDealPledgeResult = lstFinDealPledgeResult;
    }
}
