/*
 * 文件名：Interf448103InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448103.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 448103输入实体类
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月13日
 * @see Interf448103InEntity
 * @since 1.0
 */
public class Interf448103InEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4526130097496578673L;

    /**
     * 中文简称
     */
    private String shortName;

    /**
     * 净值
     */
    private String netValue;

    /**
     * 净值日期
     */
    private String navDate;

    /**
     * 生效日
     */
    private String useDate;

    /**
     * 累计净值
     */
    private String totalNetValue;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 年化收益率
     */
    private String yearRate;

    /**
     * 7日年化收益率
     */
    private String sevenYearRate;

    /**
     * 万份收益
     */
    private String fundIncome;

    /**
     * 销售费率
     */
    private String saleRate;

    /**
     * 产品类型
     */
    private String prodType;

    /**
     * 到期收益率
     */
    private String dueYield;

    /**
     * 实际到期日
     */
    private String dueDate;

    /**
     * 最低收益率
     */
    private String lowestYield;

    /**
     * 最高收益率
     */
    private String highestYield;

    /**
     * 月度收益率
     */
    private String monthYield;

    /**
     * 季度收益率
     */
    private String quarterYield;

    /**
     * 半年度收益率
     */
    private String semiAnnualYield;

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
    private String monthlyIncrease;

    /**
     * 年化月涨幅
     */
    private String monthlyYearIncrease;

    /**
     * 季涨幅
     */
    private String quarterIncrease;

    /**
     * 年化季涨幅
     */
    private String quarterYearIncrease;

    /**
     * 半年涨幅
     */
    private String semiAnnualIncrease;

    /**
     * 年化半年涨幅
     */
    private String semiAnnualYearIncrease;

    /**
     * 年涨幅
     */
    private String annualIncrease;

    /**
     * 年化年涨幅
     */
    private String annualYearIncrease;

    /**
     * 成立以来涨跌幅
     */
    private String sinceEstablishIncrease;

    /**
     * 历史收益率
     */
    private String hisRate;

    /**
     * 单位分红金额
     */
    private String divAmt;

    /**
     * 权益登记日
     */
    private String registerDate;

    /**
     * 红利下发日
     */
    private String dividendDate;

    /**
     * 获取中文简称
     * 
     * @return 中文简称
     */
    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    public String getShortName() {
        return shortName;
    }

    /**
     * 获取净值
     * 
     * @return 净值
     */
    @EsbName(value = "NetValue")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getNetValue() {
        return netValue;
    }

    /**
     * 获取净值日期
     * 
     * @return 净值日期
     */
    @EsbName(value = "NavDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getNavDate() {
        return navDate;
    }

    /**
     * 获取生效日
     * 
     * @return 生效日
     */
    @EsbName(value = "UseDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getUseDate() {
        return useDate;
    }

    /**
     * 获取累计净值
     * 
     * @return 累计净值
     */
    @EsbName(value = "TotalNetValue")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getTotalNetValue() {
        return totalNetValue;
    }

    /**
     * 获取 年化收益率
     * 
     * @return 年化收益率
     */
    @EsbName(value = "YearRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getYearRate() {
        return yearRate;
    }

    /**
     * 获取7日年化收益率
     * 
     * @return 7日年化收益率
     */
    @EsbName(value = "SevenYearRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getSevenYearRate() {
        return sevenYearRate;
    }

    /**
     * 获取万份收益
     * 
     * @return 万份收益
     */
    @EsbName(value = "FundIncome")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getFundIncome() {
        return fundIncome;
    }

    /**
     * 获取销售费率
     * 
     * @return 销售费率
     */
    @EsbName(value = "SaleRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getSaleRate() {
        return saleRate;
    }

    /**
     * 获取产品类型
     * 
     * @return 产品类型
     */
    @EsbName(value = "ProdType")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    public String getProdType() {
        return prodType;
    }

    /**
     * 获取到期收益率
     * 
     * @return 到期收益率
     */
    @EsbName(value = "DueYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getDueYield() {
        return dueYield;
    }

    /**
     * 获取实际到期日
     * 
     * @return 实际到期日
     */
    @EsbName(value = "DueDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getDueDate() {
        return dueDate;
    }

    /**
     * 获取最低收益率
     * 
     * @return 最低收益率
     */
    @EsbName(value = "LowestYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getLowestYield() {
        return lowestYield;
    }

    /**
     * 获取最高收益率
     * 
     * @return 最高收益率
     */
    @EsbName(value = "HighestYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getHighestYield() {
        return highestYield;
    }

    /**
     * 获取月度收益率
     * 
     * @return 月度收益率
     */
    @EsbName(value = "MonthYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getMonthYield() {
        return monthYield;
    }

    /**
     * 获取季度收益率
     * 
     * @return 季度收益率
     */
    @EsbName(value = "QuarterYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getQuarterYield() {
        return quarterYield;
    }

    /**
     * 获取半年度收益率
     * 
     * @return 半年度收益率
     */
    @EsbName(value = "SemiAnnualYield")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getSemiAnnualYield() {
        return semiAnnualYield;
    }

    /**
     * 获取日涨幅
     * 
     * @return 日涨幅
     */
    @EsbName(value = "DailyIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getDailyIncrease() {
        return dailyIncrease;
    }

    /**
     * 获取年化日涨幅
     * 
     * @return 年化日涨幅
     */
    @EsbName(value = "DailyYearIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getDailyYearIncrease() {
        return dailyYearIncrease;
    }

    /**
     * 获取周涨幅
     * 
     * @return 周涨幅
     */
    @EsbName(value = "WeeklyIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getWeeklyIncrease() {
        return weeklyIncrease;
    }

    /**
     * 获取年化周涨幅
     * 
     * @return 年化周涨幅
     */
    @EsbName(value = "WeeklyYearIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getWeeklyYearIncrease() {
        return weeklyYearIncrease;
    }

    /**
     * 获取月涨幅
     * 
     * @return 月涨幅
     */
    @EsbName(value = "MonthlyIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getMonthlyIncrease() {
        return monthlyIncrease;
    }

    /**
     * 获取年化月涨幅
     * 
     * @return 年化月涨幅
     */
    @EsbName(value = "MonthlyYearIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getMonthlyYearIncrease() {
        return monthlyYearIncrease;
    }

    /**
     * 获取季涨幅
     * 
     * @return 季涨幅
     */
    @EsbName(value = "QuarterIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getQuarterIncrease() {
        return quarterIncrease;
    }

    /**
     * 获取年化季涨幅
     * 
     * @return 年化季涨幅
     */
    @EsbName(value = "QuarterYearIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getQuarterYearIncrease() {
        return quarterYearIncrease;
    }

    /**
     * 获取半年涨幅
     * 
     * @return 半年涨幅
     */
    @EsbName(value = "SemiAnnualIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSemiAnnualIncrease() {
        return semiAnnualIncrease;
    }

    /**
     * 获取年化半年涨幅
     * 
     * @return 年化半年涨幅
     */
    @EsbName(value = "SemiAnnualYearIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSemiAnnualYearIncrease() {
        return semiAnnualYearIncrease;
    }

    /**
     * 获取年涨幅
     * 
     * @return 年涨幅
     */
    @EsbName(value = "AnnualIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getAnnualIncrease() {
        return annualIncrease;
    }

    /**
     * 获取年化年涨幅
     * 
     * @return 年化年涨幅
     */
    @EsbName(value = "AnnualYearIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getAnnualYearIncrease() {
        return annualYearIncrease;
    }

    /**
     * 获取成立以来涨跌幅
     * 
     * @return 成立以来涨跌幅
     */
    @EsbName(value = "SinceEstablishIncrease")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 11)
    public String getSinceEstablishIncrease() {
        return sinceEstablishIncrease;
    }

    /**
     * 获取历史收益率
     * 
     * @return 历史收益率
     */
    @EsbName(value = "HisRate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getHisRate() {
        return hisRate;
    }

    /**
     * 获取单位分红金额
     * 
     * @return 单位分红金额
     */
    @EsbName(value = "DivAmt")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getDivAmt() {
        return divAmt;
    }

    /**
     * 获取权益登记日
     * 
     * @return 权益登记日
     */
    @EsbName(value = "RegisterDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getRegisterDate() {
        return registerDate;
    }

    /**
     * 获取红利下发日
     * 
     * @return 红利下发日
     */
    @EsbName(value = "DividendDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getDividendDate() {
        return dividendDate;
    }

    /**
     * 设置中文简称
     * 
     * @param shortName 中文简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 设置净值
     * 
     * @param netValue 净值
     */
    public void setNetValue(String netValue) {
        this.netValue = netValue;
    }

    /**
     * 设置净值日期
     * 
     * @param navDate 净值日期
     */
    public void setNavDate(String navDate) {
        this.navDate = navDate;
    }

    /**
     * 设置生效日
     * 
     * @param useDate 生效日
     */
    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    /**
     * 设置累计净值
     * 
     * @param totalNetValue 累计净值
     */
    public void setTotalNetValue(String totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    /**
     * 设置年化收益率
     * 
     * @param yearRate 年化收益率
     */
    public void setYearRate(String yearRate) {
        this.yearRate = yearRate;
    }

    /**
     * 设置7日年化收益率
     * 
     * @param sevenYearRate 7日年化收益率
     */
    public void setSevenYearRate(String sevenYearRate) {
        this.sevenYearRate = sevenYearRate;
    }

    /**
     * 设置万份收益
     * 
     * @param fundIncome 万份收益
     */
    public void setFundIncome(String fundIncome) {
        this.fundIncome = fundIncome;
    }

    /**
     * 设置销售费率
     * 
     * @param saleRate 销售费率
     */
    public void setSaleRate(String saleRate) {
        this.saleRate = saleRate;
    }

    /**
     * 设置产品类型
     * 
     * @param prodType 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    /**
     * 设置到期收益率
     * 
     * @param dueYield 到期收益率
     */
    public void setDueYield(String dueYield) {
        this.dueYield = dueYield;
    }

    /**
     * 设置实际到期日
     * 
     * @param dueDate 实际到期日
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 设置最低收益率
     * 
     * @param lowestYield 最低收益率
     */
    public void setLowestYield(String lowestYield) {
        this.lowestYield = lowestYield;
    }

    /**
     * 设置最高收益率
     * 
     * @param highestYield 最高收益率
     */
    public void setHighestYield(String highestYield) {
        this.highestYield = highestYield;
    }

    /**
     * 设置月度收益率
     * 
     * @param monthYield 月度收益率
     */
    public void setMonthYield(String monthYield) {
        this.monthYield = monthYield;
    }

    /**
     * 设置季度收益率
     * 
     * @param quarterYield 季度收益率
     */
    public void setQuarterYield(String quarterYield) {
        this.quarterYield = quarterYield;
    }

    /**
     * 设置半年度收益率
     * 
     * @param semiAnnualYield 半年度收益率
     */
    public void setSemiAnnualYield(String semiAnnualYield) {
        this.semiAnnualYield = semiAnnualYield;
    }

    /**
     * 设置日涨幅
     * 
     * @param dailyIncrease 日涨幅
     */
    public void setDailyIncrease(String dailyIncrease) {
        this.dailyIncrease = dailyIncrease;
    }

    /**
     * 设置年化日涨幅
     * 
     * @param dailyYearIncrease 年化日涨幅
     */
    public void setDailyYearIncrease(String dailyYearIncrease) {
        this.dailyYearIncrease = dailyYearIncrease;
    }

    /**
     * 设置周涨幅
     * 
     * @param weeklyIncrease 周涨幅
     */
    public void setWeeklyIncrease(String weeklyIncrease) {
        this.weeklyIncrease = weeklyIncrease;
    }

    /**
     * 设置年化周涨幅
     * 
     * @param weeklyYearIncrease 年化周涨幅
     */
    public void setWeeklyYearIncrease(String weeklyYearIncrease) {
        this.weeklyYearIncrease = weeklyYearIncrease;
    }

    /**
     * 设置月涨幅
     * 
     * @param monthlyIncrease 月涨幅
     */
    public void setMonthlyIncrease(String monthlyIncrease) {
        this.monthlyIncrease = monthlyIncrease;
    }

    /**
     * 设置年化月涨幅
     * 
     * @param monthlyYearIncrease 年化月涨幅
     */
    public void setMonthlyYearIncrease(String monthlyYearIncrease) {
        this.monthlyYearIncrease = monthlyYearIncrease;
    }

    /**
     * 设置季涨幅
     * 
     * @param quarterIncrease 季涨幅
     */
    public void setQuarterIncrease(String quarterIncrease) {
        this.quarterIncrease = quarterIncrease;
    }

    /**
     * 设置年化季涨幅
     * 
     * @param quarterYearIncrease 年化季涨幅
     */
    public void setQuarterYearIncrease(String quarterYearIncrease) {
        this.quarterYearIncrease = quarterYearIncrease;
    }

    /**
     * 设置半年涨幅
     * 
     * @param semiAnnualIncrease 半年涨幅
     */
    public void setSemiAnnualIncrease(String semiAnnualIncrease) {
        this.semiAnnualIncrease = semiAnnualIncrease;
    }

    /**
     * 设置年化半年涨幅
     * 
     * @param semiAnnualYearIncrease 年化半年涨幅
     */
    public void setSemiAnnualYearIncrease(String semiAnnualYearIncrease) {
        this.semiAnnualYearIncrease = semiAnnualYearIncrease;
    }

    /**
     * 设置年涨幅
     * 
     * @param annualIncrease 年涨幅
     */
    public void setAnnualIncrease(String annualIncrease) {
        this.annualIncrease = annualIncrease;
    }

    /**
     * 设置年化年涨幅
     * 
     * @param annualYearIncrease 年化年涨幅
     */
    public void setAnnualYearIncrease(String annualYearIncrease) {
        this.annualYearIncrease = annualYearIncrease;
    }

    /**
     * 设置成立以来涨跌幅
     * 
     * @param sinceEstablishIncrease 成立以来涨跌幅
     */
    public void setSinceEstablishIncrease(String sinceEstablishIncrease) {
        this.sinceEstablishIncrease = sinceEstablishIncrease;
    }

    /**
     * 设置历史收益率
     * 
     * @param hisRate 历史收益率
     */
    public void setHisRate(String hisRate) {
        this.hisRate = hisRate;
    }

    /**
     * 设置单位分红金额
     * 
     * @param divAmt 单位分红金额
     */
    public void setDivAmt(String divAmt) {
        this.divAmt = divAmt;
    }

    /**
     * 设置权益登记日
     * 
     * @param registerDate 权益登记日
     */
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 设置红利下发日
     * 
     * @param dividendDate 红利下发日
     */
    public void setDividendDate(String dividendDate) {
        this.dividendDate = dividendDate;
    }

    /**
     * 获取 收益类型
     * 
     * @return 获取 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * 
     * @param profitType 设置 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

}
