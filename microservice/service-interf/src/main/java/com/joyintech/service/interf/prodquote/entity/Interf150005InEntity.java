/*
 * 文件名：Interf150005InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodquote.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 报价接口150005-输入
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see Interf150005InEntity
 * @since 1.0
 */
public class Interf150005InEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8934447792822495030L;

    /**
     * 中文简称
     */
    private String prodId;

    /**
     * 净值
     */
    private String netValue;

    /**
     * 净值日期
     */
    private String netValueDate;

    /**
     * 生效日
     */
    private String effectDate;

    /**
     * 累计净值
     */
    private String totalNetValue;

    /**
     * 年化收益率
     */
    private String yearYield;

    /**
     * 七日年化收益率
     */
    private String sevenDaysYield;

    /**
     * 万份收益
     */
    private String unitIncome;

    /**
     * 销售费率
     */
    private String saleFeeRate;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 到期收益率
     */
    private String expiryYield;

    /**
     * 实际到期日
     */
    private String actMatureDate;

    /**
     * 最低收益率
     */
    private String minYield;

    /**
     * 最高收益率
     */
    private String maxYield;

    /**
     * 月度收益率
     */
    private String monthYield;

    /**
     * 季度收益率
     */
    private String seasonYield;

    /**
     * 半年度收益率
     */
    private String halfYearYield;

    /**
     * 日涨幅
     */
    private String dailyIncrease;

    /**
     * 年化日涨幅
     */
    private String dailyYearIncrease;

    /**
     * 周涨幅
     */
    private String weeklyIncrease;

    /**
     * 年化周涨幅
     */
    private String weeklyYearIncrease;

    /**
     * 月涨幅
     */
    private String monthIncrease;

    /**
     * 年化月涨幅
     */
    private String monthYearIncrease;

    /**
     * 季涨幅
     */
    private String seasonIncrease;

    /**
     * 年化季涨幅
     */
    private String seasonYearIncrease;

    /**
     * 半年涨幅
     */
    private String semiannualIncrease;

    /**
     * 年化半年涨幅
     */
    private String semiYearIncrease;

    /**
     * 年涨幅
     */
    private String annaulIncrease;

    /**
     * 年化年涨幅
     */
    private String annaulYearIncrease;

    /**
     * 成立以来涨跌幅
     */
    private String establishUpDown;

    /**
     * 历史收益率
     */
    private String hisYield;

    /**
     * 单位分红金额
     */
    private String unitBonusAmt;

    /**
     * 权益登记日
     */
    private String rightsRegDate;

    /**
     * 红利下发日
     */
    private String dividendStDate;

    /**
     * 收益率开始时间
     */
    private String yieldStTime;

    /**
     * 收益率结束时间
     */
    private String yieldEdTime;

    /**
     * 收益类型
     */
    private String incomeType;

    /**
     * 分层信息
     */
    private List<Interf150005InLvlEntity> lvlList;

    /**
     * 取得 中文简称
     * 
     * @return 中文简称
     */
    @EsbName(value = "CHN_SHORT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    public String getProdId() {
        return prodId;
    }

    /**
     * 取得 净值
     * 
     * @return 净值
     */
    @EsbName(value = "NET_VALUE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getNetValue() {
        return netValue;
    }

    /**
     * 取得 净值日期
     * 
     * @return 净值日期
     */
    @EsbName(value = "NET_VALUE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getNetValueDate() {
        return netValueDate;
    }

    /**
     * 取得 生效日
     * 
     * @return 生效日
     */
    @EsbName(value = "EFFECT_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getEffectDate() {
        return effectDate;
    }

    /**
     * 取得 累计净值
     * 
     * @return 累计净值
     */
    @EsbName(value = "TOTAL_NET_VALUE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getTotalNetValue() {
        return totalNetValue;
    }

    /**
     * 取得 年化收益率
     * 
     * @return 年化收益率
     */
    @EsbName(value = "YEAR_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getYearYield() {
        return yearYield;
    }

    /**
     * 取得 七日年化收益率
     * 
     * @return 七日年化收益率
     */
    @EsbName(value = "SEVEN_DAYS_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getSevenDaysYield() {
        return sevenDaysYield;
    }

    /**
     * 取得 万份收益
     * 
     * @return 万份收益
     */
    @EsbName(value = "UNIT_INCOME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getUnitIncome() {
        return unitIncome;
    }

    /**
     * 取得 销售费率
     * 
     * @return 销售费率
     */
    @EsbName(value = "SALE_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 取得 产品类型
     * 
     * @return 产品类型
     */
    @EsbName(value = "PRODUCT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 24)
    public String getProductType() {
        return productType;
    }

    /**
     * 取得 到期收益率
     * 
     * @return 到期收益率
     */
    @EsbName(value = "EXPIRY_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getExpiryYield() {
        return expiryYield;
    }

    /**
     * 取得 实际到期日
     * 
     * @return 实际到期日
     */
    @EsbName(value = "ACT_MATURE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getActMatureDate() {
        return actMatureDate;
    }

    /**
     * 取得 最低收益率
     * 
     * @return 最低收益率
     */
    @EsbName(value = "MIN_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getMinYield() {
        return minYield;
    }

    /**
     * 取得 最高收益率
     * 
     * @return 最高收益率
     */
    @EsbName(value = "MAX_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getMaxYield() {
        return maxYield;
    }

    /**
     * 取得 月度收益率
     * 
     * @return 月度收益率
     */
    @EsbName(value = "MONTH_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getMonthYield() {
        return monthYield;
    }

    /**
     * 取得 季度收益率
     * 
     * @return 季度收益率
     */
    @EsbName(value = "SEASON_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSeasonYield() {
        return seasonYield;
    }

    /**
     * 取得 半年度收益率
     * 
     * @return 半年度收益率
     */
    @EsbName(value = "HALF_YEAR_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getHalfYearYield() {
        return halfYearYield;
    }

    /**
     * 取得 日涨幅
     * 
     * @return 日涨幅
     */
    @EsbName(value = "DAILY_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getDailyIncrease() {
        return dailyIncrease;
    }

    /**
     * 取得 年化日涨幅
     * 
     * @return 年化日涨幅
     */
    @EsbName(value = "DAILY_YEAR_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getDailyYearIncrease() {
        return dailyYearIncrease;
    }

    /**
     * 取得 周涨幅
     * 
     * @return 周涨幅
     */
    @EsbName(value = "WEEKLY_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getWeeklyIncrease() {
        return weeklyIncrease;
    }

    /**
     * 取得 年化周涨幅
     * 
     * @return 年化周涨幅
     */
    @EsbName(value = "WEEKLY_YEAR_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getWeeklyYearIncrease() {
        return weeklyYearIncrease;
    }

    /**
     * 取得 月涨幅
     * 
     * @return 月涨幅
     */
    @EsbName(value = "MONTH_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getMonthIncrease() {
        return monthIncrease;
    }

    /**
     * 取得 年化月涨幅
     * 
     * @return 年化月涨幅
     */
    @EsbName(value = "MONTH_YEAR_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getMonthYearIncrease() {
        return monthYearIncrease;
    }

    /**
     * 取得 季涨幅
     * 
     * @return 季涨幅
     */
    @EsbName(value = "SEASON_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSeasonIncrease() {
        return seasonIncrease;
    }

    /**
     * 取得 年化季涨幅
     * 
     * @return 年化季涨幅
     */
    @EsbName(value = "SEASON_YEAR_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSeasonYearIncrease() {
        return seasonYearIncrease;
    }

    /**
     * 取得 半年涨幅
     * 
     * @return 半年涨幅
     */
    @EsbName(value = "SEMIANNUAL_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSemiannualIncrease() {
        return semiannualIncrease;
    }

    /**
     * 取得 年化半年涨幅
     * 
     * @return 年化半年涨幅
     */
    @EsbName(value = "SEMIANNUAL_YEAR_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSemiYearIncrease() {
        return semiYearIncrease;
    }

    /**
     * 取得 年涨幅
     * 
     * @return 年涨幅
     */
    @EsbName(value = "ANNUAL_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getAnnaulIncrease() {
        return annaulIncrease;
    }

    /**
     * 取得 年化年涨幅
     * 
     * @return 年化年涨幅
     */
    @EsbName(value = "ANNUAL_YEAR_INCREASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getAnnaulYearIncrease() {
        return annaulYearIncrease;
    }

    /**
     * 取得 成立以来涨跌幅
     * 
     * @return 成立以来涨跌幅
     */
    @EsbName(value = "ESTABLISH_UP_DOWN_SPREAD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getEstablishUpDown() {
        return establishUpDown;
    }

    /**
     * 取得 历史收益率
     * 
     * @return 历史收益率
     */
    @EsbName(value = "HIS_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getHisYield() {
        return hisYield;
    }

    /**
     * 取得 单位分红金额
     * 
     * @return 单位分红金额
     */
    @EsbName(value = "UNIT_BONUS_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getUnitBonusAmt() {
        return unitBonusAmt;
    }

    /**
     * 取得 权益登记日
     * 
     * @return 权益登记日
     */
    @EsbName(value = "RIGHTS_REGISTER_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getRightsRegDate() {
        return rightsRegDate;
    }

    /**
     * 取得 红利下发日
     * 
     * @return 红利下发日
     */
    @EsbName(value = "DIVIDEND_START_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getDividendStDate() {
        return dividendStDate;
    }

    /**
     * 取得 收益率开始时间
     * 
     * @return 收益率开始时间
     */
    @EsbName(value = "YIELD_START_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getYieldStTime() {
        return yieldStTime;
    }

    /**
     * 取得 收益率结束时间
     * 
     * @return 收益率结束时间
     */
    @EsbName(value = "YIELD_END_TIME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getYieldEdTime() {
        return yieldEdTime;
    }

    /**
     * 取得 收益类型
     * 
     * @return 收益类型
     */
    @EsbName(value = "INCOME_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * 设置 中文简称
     * 
     * @param prodId 中文简称
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 设置 净值
     * 
     * @param netValue 净值
     */
    public void setNetValue(String netValue) {
        this.netValue = netValue;
    }

    /**
     * 设置 净值日期
     * 
     * @param netValueDate 净值日期
     */
    public void setNetValueDate(String netValueDate) {
        this.netValueDate = netValueDate;
    }

    /**
     * 设置 生效日
     * 
     * @param effectDate 生效日
     */
    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    /**
     * 设置 累计净值
     * 
     * @param totalNetValue 累计净值
     */
    public void setTotalNetValue(String totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    /**
     * 设置 年化收益率
     * 
     * @param yearYield 年化收益率
     */
    public void setYearYield(String yearYield) {
        this.yearYield = yearYield;
    }

    /**
     * 设置 七日年化收益率
     * 
     * @param sevenDaysYield 七日年化收益率
     */
    public void setSevenDaysYield(String sevenDaysYield) {
        this.sevenDaysYield = sevenDaysYield;
    }

    /**
     * 设置 万份收益
     * 
     * @param unitIncome 万份收益
     */
    public void setUnitIncome(String unitIncome) {
        this.unitIncome = unitIncome;
    }

    /**
     * 设置 销售费率
     * 
     * @param saleFeeRate 销售费率
     */
    public void setSaleFeeRate(String saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 设置 产品类型
     * 
     * @param productType 产品类型
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * 设置 到期收益率
     * 
     * @param expiryYield 到期收益率
     */
    public void setExpiryYield(String expiryYield) {
        this.expiryYield = expiryYield;
    }

    /**
     * 设置 实际到期日
     * 
     * @param actMatureDate 实际到期日
     */
    public void setActMatureDate(String actMatureDate) {
        this.actMatureDate = actMatureDate;
    }

    /**
     * 设置 最低收益率
     * 
     * @param minYield 最低收益率
     */
    public void setMinYield(String minYield) {
        this.minYield = minYield;
    }

    /**
     * 设置 最高收益率
     * 
     * @param maxYield 最高收益率
     */
    public void setMaxYield(String maxYield) {
        this.maxYield = maxYield;
    }

    /**
     * 设置 月度收益率
     * 
     * @param monthYield 月度收益率
     */
    public void setMonthYield(String monthYield) {
        this.monthYield = monthYield;
    }

    /**
     * 设置 季度收益率
     * 
     * @param seasonYield 季度收益率
     */
    public void setSeasonYield(String seasonYield) {
        this.seasonYield = seasonYield;
    }

    /**
     * 设置 半年度收益率
     * 
     * @param halfYearYield 半年度收益率
     */
    public void setHalfYearYield(String halfYearYield) {
        this.halfYearYield = halfYearYield;
    }

    /**
     * 设置 日涨幅
     * 
     * @param dailyIncrease 日涨幅
     */
    public void setDailyIncrease(String dailyIncrease) {
        this.dailyIncrease = dailyIncrease;
    }

    /**
     * 设置 年化日涨幅
     * 
     * @param dailyYearIncrease 年化日涨幅
     */
    public void setDailyYearIncrease(String dailyYearIncrease) {
        this.dailyYearIncrease = dailyYearIncrease;
    }

    /**
     * 设置 周涨幅
     * 
     * @param weeklyIncrease 周涨幅
     */
    public void setWeeklyIncrease(String weeklyIncrease) {
        this.weeklyIncrease = weeklyIncrease;
    }

    /**
     * 设置 年化周涨幅
     * 
     * @param weeklyYearIncrease 年化周涨幅
     */
    public void setWeeklyYearIncrease(String weeklyYearIncrease) {
        this.weeklyYearIncrease = weeklyYearIncrease;
    }

    /**
     * 设置 月涨幅
     * 
     * @param monthIncrease 月涨幅
     */
    public void setMonthIncrease(String monthIncrease) {
        this.monthIncrease = monthIncrease;
    }

    /**
     * 设置 年化月涨幅
     * 
     * @param monthYearIncrease 年化月涨幅
     */
    public void setMonthYearIncrease(String monthYearIncrease) {
        this.monthYearIncrease = monthYearIncrease;
    }

    /**
     * 设置 季涨幅
     * 
     * @param seasonIncrease 季涨幅
     */
    public void setSeasonIncrease(String seasonIncrease) {
        this.seasonIncrease = seasonIncrease;
    }

    /**
     * 设置 年化季涨幅
     * 
     * @param seasonYearIncrease 年化季涨幅
     */
    public void setSeasonYearIncrease(String seasonYearIncrease) {
        this.seasonYearIncrease = seasonYearIncrease;
    }

    /**
     * 设置 半年涨幅
     * 
     * @param semiannualIncrease 半年涨幅
     */
    public void setSemiannualIncrease(String semiannualIncrease) {
        this.semiannualIncrease = semiannualIncrease;
    }

    /**
     * 设置 年化半年涨幅
     * 
     * @param semiYearIncrease 年化半年涨幅
     */
    public void setSemiYearIncrease(String semiYearIncrease) {
        this.semiYearIncrease = semiYearIncrease;
    }

    /**
     * 设置 年涨幅
     * 
     * @param annaulIncrease 年涨幅
     */
    public void setAnnaulIncrease(String annaulIncrease) {
        this.annaulIncrease = annaulIncrease;
    }

    /**
     * 设置 年化年涨幅
     * 
     * @param annaulYearIncrease 年化年涨幅
     */
    public void setAnnaulYearIncrease(String annaulYearIncrease) {
        this.annaulYearIncrease = annaulYearIncrease;
    }

    /**
     * 设置 成立以来涨跌幅
     * 
     * @param establishUpDown 成立以来涨跌幅
     */
    public void setEstablishUpDown(String establishUpDown) {
        this.establishUpDown = establishUpDown;
    }

    /**
     * 设置 历史收益率
     * 
     * @param hisYield 历史收益率
     */
    public void setHisYield(String hisYield) {
        this.hisYield = hisYield;
    }

    /**
     * 设置 单位分红金额
     * 
     * @param unitBonusAmt 单位分红金额
     */
    public void setUnitBonusAmt(String unitBonusAmt) {
        this.unitBonusAmt = unitBonusAmt;
    }

    /**
     * 设置 权益登记日
     * 
     * @param rightsRegDate 权益登记日
     */
    public void setRightsRegDate(String rightsRegDate) {
        this.rightsRegDate = rightsRegDate;
    }

    /**
     * 设置 红利下发日
     * 
     * @param dividendStDate 红利下发日
     */
    public void setDividendStDate(String dividendStDate) {
        this.dividendStDate = dividendStDate;
    }

    /**
     * 设置 收益率开始时间
     * 
     * @param yieldStTime 收益率开始时间
     */
    public void setYieldStTime(String yieldStTime) {
        this.yieldStTime = yieldStTime;
    }

    /**
     * 设置 收益率结束时间
     * 
     * @param yieldEdTime 收益率结束时间
     */
    public void setYieldEdTime(String yieldEdTime) {
        this.yieldEdTime = yieldEdTime;
    }

    /**
     * 设置 收益类型
     * 
     * @param incomeType 收益类型
     */
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    /**
     * 获取 分层信息
     * 
     * @return 分层信息.
     */
    @EsbName(value = "YIELD_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public List<Interf150005InLvlEntity> getLvlList() {
        return lvlList;
    }

    /**
     * 设置 分层信息
     * 
     * @param lvlList 分层信息
     */
    public void setLvlList(List<Interf150005InLvlEntity> lvlList) {
        this.lvlList = lvlList;
    }
}
