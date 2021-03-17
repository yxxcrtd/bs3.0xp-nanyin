package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class MockTest extends BaseEntity {
    /**
     * 模拟编号
     */
    private String mockId;

    /**
     * 试算日期
     */
    private Date mockDate;

    /**
     * 试算日期
     */
    private Date mockTime;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 业绩基准
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal baseRule;

    /**
     * 管理费率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mgrFeeRate;

    /**
     * 募集金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal raiseAmtPlan;

    /**
     * 前一日单位净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayUnitValue;

    /**
     * 前一日万份收益
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayPYield;

    /**
     * 前一日日终剩余现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayCashPosition;

    /**
     * 前一日净值涨跌幅
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayValChg;

    /**
     * 前一日七日年化收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDaySevenDayRate;

    /**
     * 前一日资产总值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayAssetValue;

    /**
     * 前一日资产净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayAssetNetvalue;

    /**
     * 前一日杠杆率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayLeverRate;

    /**
     * 前一日基点价值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayBpvalue;

    /**
     * 前一日平均剩余期限
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayAvgsurplustime;

    /**
     * 前一日平均剩余存续期限
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayAvgsurplustimeP;

    /**
     * 前一日组合久期
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preDayPtlMDuration;

    /**
     * 实时单位净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeUnitValue;

    /**
     * 实时万份收益
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimePYield;

    /**
     * 实时剩余现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeCashPosition;

    /**
     * 实时净值涨跌幅
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeValChg;

    /**
     * 实时七日年化收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeSevenDayRate;

    /**
     * 实时资产总值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeAssetValue;

    /**
     * 实时资产净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeAssetNetvalue;

    /**
     * 实时杠杆率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeLeverRate;

    /**
     * 实时基点价值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeBpvalue;

    /**
     * 实时平均剩余期限
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeAvgsurplustime;

    /**
     * 实时平均剩余存续期限
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimeAvgsurplustimeP;

    /**
     * 实时组合久期
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal realTimePtlMDuration;

    /**
     * 再投资收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal againInvestRate;

    /**
     * 融资成本
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal finanCost;

    /**
     * 试算后单位净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockUnitValue;

    /**
     * 试算后万份收益
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockPYield;

    /**
     * 试算后涨跌幅
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockValChg;

    /**
     * 试算后剩余现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockCashPosition;

    /**
     * 试算后资产总值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockAssetValue;

    /**
     * 试算后资产净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockAssetNetvalue;

    /**
     * 试算后杠杆率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockLeverRate;

    /**
     * 试算后基点价值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockBpvalue;

    /**
     * 试算后平均剩余期限
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockAvgsurplustime;

    /**
     * 试算后平均剩余存续期限
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockAvgsurplustimeP;

    /**
     * 试算后组合久期
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockPtlMDuration;

    /**
     * 试算后七日收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockSevenDayRate;

    /**
     * 试算后一月收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockOneMonthRate;

    /**
     * 试算后三月收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockThreeMonthRate;

    /**
     * 试算后至下一季报日收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockQuarterDayRate;

    /**
     * 试算后一年收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockOneYearRate;

    /**
     * 试算后到期收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockYield;

    /**
     * 试算后选择区间开始日
     */
    private Date mockRangeStartDate;

    /**
     * 试算后选择区间结束日
     */
    private Date mockRangeEndDate;

    /**
     * 试算后区间收益率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mockRangeRate;

    /**
     * 取得 模拟编号
     * @return 模拟编号
     */
    public String getMockId() {
        return mockId;
    }

    /**
     * 设置 模拟编号
     * @param mockId 模拟编号
     */
    public void setMockId(String mockId) {
        this.mockId = mockId == null ? null : mockId.trim();
    }

    /**
     * 取得 试算日期
     * @return 试算日期
     */
    public Date getMockDate() {
        return mockDate;
    }

    /**
     * 设置 试算日期
     * @param mockDate 试算日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMockDate(Date mockDate) {
        this.mockDate = mockDate;
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 业绩基准
     * @return 业绩基准
     */
    public BigDecimal getBaseRule() {
        return baseRule;
    }

    /**
     * 设置 业绩基准
     * @param baseRule 业绩基准
     */
    public void setBaseRule(BigDecimal baseRule) {
        this.baseRule = baseRule;
    }

    /**
     * 取得 管理费率
     * @return 管理费率
     */
    public BigDecimal getMgrFeeRate() {
        return mgrFeeRate;
    }

    /**
     * 设置 管理费率
     * @param mgrFeeRate 管理费率
     */
    public void setMgrFeeRate(BigDecimal mgrFeeRate) {
        this.mgrFeeRate = mgrFeeRate;
    }

    /**
     * 取得 募集金额
     * @return 募集金额
     */
    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    /**
     * 设置 募集金额
     * @param raiseAmtPlan 募集金额
     */
    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    /**
     * 取得 前一日单位净值
     * @return 前一日单位净值
     */
    public BigDecimal getPreDayUnitValue() {
        return preDayUnitValue;
    }

    /**
     * 设置 前一日单位净值
     * @param preDayUnitValue 前一日单位净值
     */
    public void setPreDayUnitValue(BigDecimal preDayUnitValue) {
        this.preDayUnitValue = preDayUnitValue;
    }

    /**
     * 取得 前一日万份收益
     * @return 前一日万份收益
     */
    public BigDecimal getPreDayPYield() {
        return preDayPYield;
    }

    /**
     * 设置 前一日万份收益
     * @param preDayPYield 前一日万份收益
     */
    public void setPreDayPYield(BigDecimal preDayPYield) {
        this.preDayPYield = preDayPYield;
    }

    /**
     * 取得 前一日日终剩余现金
     * @return 前一日日终剩余现金
     */
    public BigDecimal getPreDayCashPosition() {
        return preDayCashPosition;
    }

    /**
     * 设置 前一日日终剩余现金
     * @param preDayCashPosition 前一日日终剩余现金
     */
    public void setPreDayCashPosition(BigDecimal preDayCashPosition) {
        this.preDayCashPosition = preDayCashPosition;
    }

    /**
     * 取得 前一日净值涨跌幅
     * @return 前一日净值涨跌幅
     */
    public BigDecimal getPreDayValChg() {
        return preDayValChg;
    }

    /**
     * 设置 前一日净值涨跌幅
     * @param preDayValChg 前一日净值涨跌幅
     */
    public void setPreDayValChg(BigDecimal preDayValChg) {
        this.preDayValChg = preDayValChg;
    }

    /**
     * 取得 前一日七日年化收益率
     * @return 前一日七日年化收益率
     */
    public BigDecimal getPreDaySevenDayRate() {
        return preDaySevenDayRate;
    }

    /**
     * 设置 前一日七日年化收益率
     * @param preDaySevenDayRate 前一日七日年化收益率
     */
    public void setPreDaySevenDayRate(BigDecimal preDaySevenDayRate) {
        this.preDaySevenDayRate = preDaySevenDayRate;
    }

    /**
     * 取得 前一日资产总值
     * @return 前一日资产总值
     */
    public BigDecimal getPreDayAssetValue() {
        return preDayAssetValue;
    }

    /**
     * 设置 前一日资产总值
     * @param preDayAssetValue 前一日资产总值
     */
    public void setPreDayAssetValue(BigDecimal preDayAssetValue) {
        this.preDayAssetValue = preDayAssetValue;
    }

    /**
     * 取得 前一日资产净值
     * @return 前一日资产净值
     */
    public BigDecimal getPreDayAssetNetvalue() {
        return preDayAssetNetvalue;
    }

    /**
     * 设置 前一日资产净值
     * @param preDayAssetNetvalue 前一日资产净值
     */
    public void setPreDayAssetNetvalue(BigDecimal preDayAssetNetvalue) {
        this.preDayAssetNetvalue = preDayAssetNetvalue;
    }

    /**
     * 取得 前一日杠杆率
     * @return 前一日杠杆率
     */
    public BigDecimal getPreDayLeverRate() {
        return preDayLeverRate;
    }

    /**
     * 设置 前一日杠杆率
     * @param preDayLeverRate 前一日杠杆率
     */
    public void setPreDayLeverRate(BigDecimal preDayLeverRate) {
        this.preDayLeverRate = preDayLeverRate;
    }

    /**
     * 取得 前一日基点价值
     * @return 前一日基点价值
     */
    public BigDecimal getPreDayBpvalue() {
        return preDayBpvalue;
    }

    /**
     * 设置 前一日基点价值
     * @param preDayBpvalue 前一日基点价值
     */
    public void setPreDayBpvalue(BigDecimal preDayBpvalue) {
        this.preDayBpvalue = preDayBpvalue;
    }

    /**
     * 取得 前一日平均剩余期限
     * @return 前一日平均剩余期限
     */
    public BigDecimal getPreDayAvgsurplustime() {
        return preDayAvgsurplustime;
    }

    /**
     * 设置 前一日平均剩余期限
     * @param preDayAvgsurplustime 前一日平均剩余期限
     */
    public void setPreDayAvgsurplustime(BigDecimal preDayAvgsurplustime) {
        this.preDayAvgsurplustime = preDayAvgsurplustime;
    }

    /**
     * 取得 前一日平均剩余存续期限
     * @return 前一日平均剩余存续期限
     */
    public BigDecimal getPreDayAvgsurplustimeP() {
        return preDayAvgsurplustimeP;
    }

    /**
     * 设置 前一日平均剩余存续期限
     * @param preDayAvgsurplustimeP 前一日平均剩余存续期限
     */
    public void setPreDayAvgsurplustimeP(BigDecimal preDayAvgsurplustimeP) {
        this.preDayAvgsurplustimeP = preDayAvgsurplustimeP;
    }

    /**
     * 取得 前一日组合久期
     * @return 前一日组合久期
     */
    public BigDecimal getPreDayPtlMDuration() {
        return preDayPtlMDuration;
    }

    /**
     * 设置 前一日组合久期
     * @param preDayPtlMDuration 前一日组合久期
     */
    public void setPreDayPtlMDuration(BigDecimal preDayPtlMDuration) {
        this.preDayPtlMDuration = preDayPtlMDuration;
    }

    /**
     * 取得 实时单位净值
     * @return 实时单位净值
     */
    public BigDecimal getRealTimeUnitValue() {
        return realTimeUnitValue;
    }

    /**
     * 设置 实时单位净值
     * @param realTimeUnitValue 实时单位净值
     */
    public void setRealTimeUnitValue(BigDecimal realTimeUnitValue) {
        this.realTimeUnitValue = realTimeUnitValue;
    }

    /**
     * 取得 实时万份收益
     * @return 实时万份收益
     */
    public BigDecimal getRealTimePYield() {
        return realTimePYield;
    }

    /**
     * 设置 实时万份收益
     * @param realTimePYield 实时万份收益
     */
    public void setRealTimePYield(BigDecimal realTimePYield) {
        this.realTimePYield = realTimePYield;
    }

    /**
     * 取得 实时剩余现金
     * @return 实时剩余现金
     */
    public BigDecimal getRealTimeCashPosition() {
        return realTimeCashPosition;
    }

    /**
     * 设置 实时剩余现金
     * @param realTimeCashPosition 实时剩余现金
     */
    public void setRealTimeCashPosition(BigDecimal realTimeCashPosition) {
        this.realTimeCashPosition = realTimeCashPosition;
    }

    /**
     * 取得 实时净值涨跌幅
     * @return 实时净值涨跌幅
     */
    public BigDecimal getRealTimeValChg() {
        return realTimeValChg;
    }

    /**
     * 设置 实时净值涨跌幅
     * @param realTimeValChg 实时净值涨跌幅
     */
    public void setRealTimeValChg(BigDecimal realTimeValChg) {
        this.realTimeValChg = realTimeValChg;
    }

    /**
     * 取得 实时七日年化收益率
     * @return 实时七日年化收益率
     */
    public BigDecimal getRealTimeSevenDayRate() {
        return realTimeSevenDayRate;
    }

    /**
     * 设置 实时七日年化收益率
     * @param realTimeSevenDayRate 实时七日年化收益率
     */
    public void setRealTimeSevenDayRate(BigDecimal realTimeSevenDayRate) {
        this.realTimeSevenDayRate = realTimeSevenDayRate;
    }

    /**
     * 取得 实时资产总值
     * @return 实时资产总值
     */
    public BigDecimal getRealTimeAssetValue() {
        return realTimeAssetValue;
    }

    /**
     * 设置 实时资产总值
     * @param realTimeAssetValue 实时资产总值
     */
    public void setRealTimeAssetValue(BigDecimal realTimeAssetValue) {
        this.realTimeAssetValue = realTimeAssetValue;
    }

    /**
     * 取得 实时资产净值
     * @return 实时资产净值
     */
    public BigDecimal getRealTimeAssetNetvalue() {
        return realTimeAssetNetvalue;
    }

    /**
     * 设置 实时资产净值
     * @param realTimeAssetNetvalue 实时资产净值
     */
    public void setRealTimeAssetNetvalue(BigDecimal realTimeAssetNetvalue) {
        this.realTimeAssetNetvalue = realTimeAssetNetvalue;
    }

    /**
     * 取得 实时杠杆率
     * @return 实时杠杆率
     */
    public BigDecimal getRealTimeLeverRate() {
        return realTimeLeverRate;
    }

    /**
     * 设置 实时杠杆率
     * @param realTimeLeverRate 实时杠杆率
     */
    public void setRealTimeLeverRate(BigDecimal realTimeLeverRate) {
        this.realTimeLeverRate = realTimeLeverRate;
    }

    /**
     * 取得 实时基点价值
     * @return 实时基点价值
     */
    public BigDecimal getRealTimeBpvalue() {
        return realTimeBpvalue;
    }

    /**
     * 设置 实时基点价值
     * @param realTimeBpvalue 实时基点价值
     */
    public void setRealTimeBpvalue(BigDecimal realTimeBpvalue) {
        this.realTimeBpvalue = realTimeBpvalue;
    }

    /**
     * 取得 实时平均剩余期限
     * @return 实时平均剩余期限
     */
    public BigDecimal getRealTimeAvgsurplustime() {
        return realTimeAvgsurplustime;
    }

    /**
     * 设置 实时平均剩余期限
     * @param realTimeAvgsurplustime 实时平均剩余期限
     */
    public void setRealTimeAvgsurplustime(BigDecimal realTimeAvgsurplustime) {
        this.realTimeAvgsurplustime = realTimeAvgsurplustime;
    }

    /**
     * 取得 实时平均剩余存续期限
     * @return 实时平均剩余存续期限
     */
    public BigDecimal getRealTimeAvgsurplustimeP() {
        return realTimeAvgsurplustimeP;
    }

    /**
     * 设置 实时平均剩余存续期限
     * @param realTimeAvgsurplustimeP 实时平均剩余存续期限
     */
    public void setRealTimeAvgsurplustimeP(BigDecimal realTimeAvgsurplustimeP) {
        this.realTimeAvgsurplustimeP = realTimeAvgsurplustimeP;
    }

    /**
     * 取得 实时组合久期
     * @return 实时组合久期
     */
    public BigDecimal getRealTimePtlMDuration() {
        return realTimePtlMDuration;
    }

    /**
     * 设置 实时组合久期
     * @param realTimePtlMDuration 实时组合久期
     */
    public void setRealTimePtlMDuration(BigDecimal realTimePtlMDuration) {
        this.realTimePtlMDuration = realTimePtlMDuration;
    }

    /**
     * 取得 再投资收益率
     * @return 再投资收益率
     */
    public BigDecimal getAgainInvestRate() {
        return againInvestRate;
    }

    /**
     * 设置 再投资收益率
     * @param againInvestRate 再投资收益率
     */
    public void setAgainInvestRate(BigDecimal againInvestRate) {
        this.againInvestRate = againInvestRate;
    }

    /**
     * 取得 融资成本
     * @return 融资成本
     */
    public BigDecimal getFinanCost() {
        return finanCost;
    }

    /**
     * 设置 融资成本
     * @param finanCost 融资成本
     */
    public void setFinanCost(BigDecimal finanCost) {
        this.finanCost = finanCost;
    }

    /**
     * 取得 试算后单位净值
     * @return 试算后单位净值
     */
    public BigDecimal getMockUnitValue() {
        return mockUnitValue;
    }

    /**
     * 设置 试算后单位净值
     * @param mockUnitValue 试算后单位净值
     */
    public void setMockUnitValue(BigDecimal mockUnitValue) {
        this.mockUnitValue = mockUnitValue;
    }

    /**
     * 取得 试算后万份收益
     * @return 试算后万份收益
     */
    public BigDecimal getMockPYield() {
        return mockPYield;
    }

    /**
     * 设置 试算后万份收益
     * @param mockPYield 试算后万份收益
     */
    public void setMockPYield(BigDecimal mockPYield) {
        this.mockPYield = mockPYield;
    }

    /**
     * 取得 试算后涨跌幅
     * @return 试算后涨跌幅
     */
    public BigDecimal getMockValChg() {
        return mockValChg;
    }

    /**
     * 设置 试算后涨跌幅
     * @param mockValChg 试算后涨跌幅
     */
    public void setMockValChg(BigDecimal mockValChg) {
        this.mockValChg = mockValChg;
    }

    /**
     * 取得 试算后剩余现金
     * @return 试算后剩余现金
     */
    public BigDecimal getMockCashPosition() {
        return mockCashPosition;
    }

    /**
     * 设置 试算后剩余现金
     * @param mockCashPosition 试算后剩余现金
     */
    public void setMockCashPosition(BigDecimal mockCashPosition) {
        this.mockCashPosition = mockCashPosition;
    }

    /**
     * 取得 试算后资产总值
     * @return 试算后资产总值
     */
    public BigDecimal getMockAssetValue() {
        return mockAssetValue;
    }

    /**
     * 设置 试算后资产总值
     * @param mockAssetValue 试算后资产总值
     */
    public void setMockAssetValue(BigDecimal mockAssetValue) {
        this.mockAssetValue = mockAssetValue;
    }

    /**
     * 取得 试算后资产净值
     * @return 试算后资产净值
     */
    public BigDecimal getMockAssetNetvalue() {
        return mockAssetNetvalue;
    }

    /**
     * 设置 试算后资产净值
     * @param mockAssetNetvalue 试算后资产净值
     */
    public void setMockAssetNetvalue(BigDecimal mockAssetNetvalue) {
        this.mockAssetNetvalue = mockAssetNetvalue;
    }

    /**
     * 取得 试算后杠杆率
     * @return 试算后杠杆率
     */
    public BigDecimal getMockLeverRate() {
        return mockLeverRate;
    }

    /**
     * 设置 试算后杠杆率
     * @param mockLeverRate 试算后杠杆率
     */
    public void setMockLeverRate(BigDecimal mockLeverRate) {
        this.mockLeverRate = mockLeverRate;
    }

    /**
     * 取得 试算后基点价值
     * @return 试算后基点价值
     */
    public BigDecimal getMockBpvalue() {
        return mockBpvalue;
    }

    /**
     * 设置 试算后基点价值
     * @param mockBpvalue 试算后基点价值
     */
    public void setMockBpvalue(BigDecimal mockBpvalue) {
        this.mockBpvalue = mockBpvalue;
    }

    /**
     * 取得 试算后平均剩余期限
     * @return 试算后平均剩余期限
     */
    public BigDecimal getMockAvgsurplustime() {
        return mockAvgsurplustime;
    }

    /**
     * 设置 试算后平均剩余期限
     * @param mockAvgsurplustime 试算后平均剩余期限
     */
    public void setMockAvgsurplustime(BigDecimal mockAvgsurplustime) {
        this.mockAvgsurplustime = mockAvgsurplustime;
    }

    /**
     * 取得 试算后平均剩余存续期限
     * @return 试算后平均剩余存续期限
     */
    public BigDecimal getMockAvgsurplustimeP() {
        return mockAvgsurplustimeP;
    }

    /**
     * 设置 试算后平均剩余存续期限
     * @param mockAvgsurplustimeP 试算后平均剩余存续期限
     */
    public void setMockAvgsurplustimeP(BigDecimal mockAvgsurplustimeP) {
        this.mockAvgsurplustimeP = mockAvgsurplustimeP;
    }

    /**
     * 取得 试算后组合久期
     * @return 试算后组合久期
     */
    public BigDecimal getMockPtlMDuration() {
        return mockPtlMDuration;
    }

    /**
     * 设置 试算后组合久期
     * @param mockPtlMDuration 试算后组合久期
     */
    public void setMockPtlMDuration(BigDecimal mockPtlMDuration) {
        this.mockPtlMDuration = mockPtlMDuration;
    }

    /**
     * 取得 试算后七日收益率
     * @return 试算后七日收益率
     */
    public BigDecimal getMockSevenDayRate() {
        return mockSevenDayRate;
    }

    /**
     * 设置 试算后七日收益率
     * @param mockSevenDayRate 试算后七日收益率
     */
    public void setMockSevenDayRate(BigDecimal mockSevenDayRate) {
        this.mockSevenDayRate = mockSevenDayRate;
    }

    /**
     * 取得 试算后一月收益率
     * @return 试算后一月收益率
     */
    public BigDecimal getMockOneMonthRate() {
        return mockOneMonthRate;
    }

    /**
     * 设置 试算后一月收益率
     * @param mockOneMonthRate 试算后一月收益率
     */
    public void setMockOneMonthRate(BigDecimal mockOneMonthRate) {
        this.mockOneMonthRate = mockOneMonthRate;
    }

    /**
     * 取得 试算后三月收益率
     * @return 试算后三月收益率
     */
    public BigDecimal getMockThreeMonthRate() {
        return mockThreeMonthRate;
    }

    /**
     * 设置 试算后三月收益率
     * @param mockThreeMonthRate 试算后三月收益率
     */
    public void setMockThreeMonthRate(BigDecimal mockThreeMonthRate) {
        this.mockThreeMonthRate = mockThreeMonthRate;
    }

    /**
     * 取得 试算后至下一季报日收益率
     * @return 试算后至下一季报日收益率
     */
    public BigDecimal getMockQuarterDayRate() {
        return mockQuarterDayRate;
    }

    /**
     * 设置 试算后至下一季报日收益率
     * @param mockQuarterDayRate 试算后至下一季报日收益率
     */
    public void setMockQuarterDayRate(BigDecimal mockQuarterDayRate) {
        this.mockQuarterDayRate = mockQuarterDayRate;
    }

    /**
     * 取得 试算后一年收益率
     * @return 试算后一年收益率
     */
    public BigDecimal getMockOneYearRate() {
        return mockOneYearRate;
    }

    /**
     * 设置 试算后一年收益率
     * @param mockOneYearRate 试算后一年收益率
     */
    public void setMockOneYearRate(BigDecimal mockOneYearRate) {
        this.mockOneYearRate = mockOneYearRate;
    }

    /**
     * 取得 试算后到期收益率
     * @return 试算后到期收益率
     */
    public BigDecimal getMockYield() {
        return mockYield;
    }

    /**
     * 设置 试算后到期收益率
     * @param mockYield 试算后到期收益率
     */
    public void setMockYield(BigDecimal mockYield) {
        this.mockYield = mockYield;
    }

    /**
     * 取得 试算后选择区间开始日
     * @return 试算后选择区间开始日
     */
    public Date getMockRangeStartDate() {
        return mockRangeStartDate;
    }

    /**
     * 设置 试算后选择区间开始日
     * @param mockRangeStartDate 试算后选择区间开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMockRangeStartDate(Date mockRangeStartDate) {
        this.mockRangeStartDate = mockRangeStartDate;
    }

    /**
     * 取得 试算后选择区间结束日
     * @return 试算后选择区间结束日
     */
    public Date getMockRangeEndDate() {
        return mockRangeEndDate;
    }

    /**
     * 设置 试算后选择区间结束日
     * @param mockRangeEndDate 试算后选择区间结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMockRangeEndDate(Date mockRangeEndDate) {
        this.mockRangeEndDate = mockRangeEndDate;
    }

    /**
     * 取得 试算后区间收益率
     * @return 试算后区间收益率
     */
    public BigDecimal getMockRangeRate() {
        return mockRangeRate;
    }

    /**
     * 设置 试算后区间收益率
     * @param mockRangeRate 试算后区间收益率
     */
    public void setMockRangeRate(BigDecimal mockRangeRate) {
        this.mockRangeRate = mockRangeRate;
    }

    /**
     * 设置 试算时间，精确到时分秒
     * @return 试算时间，精确到时分秒
     */
    public Date getMockTime() {
        return mockTime;
    }

    /**
     * 设置 试算时间，精确到时分秒
     * @param mockTime 试算时间，精确到时分秒
     */
    public void setMockTime(Date mockTime) {
        this.mockTime = mockTime;
    }
}