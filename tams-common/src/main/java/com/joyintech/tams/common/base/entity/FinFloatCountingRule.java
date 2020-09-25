package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class FinFloatCountingRule extends FinFloatCountingRuleKey {
    /**
     * 计提类型
     */
    private String accrType;

    /**
     * 计提频率
     */
    private Long accrCycle;

    /**
     * 计提频率单位 
     */
    private String accrCycleUnit;

    /**
     * 计提频率类型
     */
    private String accrDayType;

    /**
     * 计提频率值
     */
    private String accrDayValue;

    /**
     * 营业日准则
     */
    private String accrTdayRule;

    /**
     * 首次计提日
     */
    private Date fAccrDate;

    /**
     * 计提结束日
     */
    private Date eAccrDate;

    /**
     * 区间规则
     */
    private String intervalRule;

    /**
     * 首个计提区间结束日
     */
    private Date fAccrEndDate;

    /**
     * 首次计费基数
     */
    private String firstBaseType;

    /**
     * 取得 计提类型
     * @return 计提类型
     */
    public String getAccrType() {
        return accrType;
    }

    /**
     * 设置 计提类型
     * @param accrType 计提类型
     */
    public void setAccrType(String accrType) {
        this.accrType = accrType == null ? null : accrType.trim();
    }

    /**
     * 取得 计提频率
     * @return 计提频率
     */
    public Long getAccrCycle() {
        return accrCycle;
    }

    /**
     * 设置 计提频率
     * @param accrCycle 计提频率
     */
    public void setAccrCycle(Long accrCycle) {
        this.accrCycle = accrCycle;
    }

    /**
     * 取得 计提频率单位 
     * @return 计提频率单位 
     */
    public String getAccrCycleUnit() {
        return accrCycleUnit;
    }

    /**
     * 设置 计提频率单位 
     * @param accrCycleUnit 计提频率单位 
     */
    public void setAccrCycleUnit(String accrCycleUnit) {
        this.accrCycleUnit = accrCycleUnit == null ? null : accrCycleUnit.trim();
    }

    /**
     * 取得 计提频率类型
     * @return 计提频率类型
     */
    public String getAccrDayType() {
        return accrDayType;
    }

    /**
     * 设置 计提频率类型
     * @param accrDayType 计提频率类型
     */
    public void setAccrDayType(String accrDayType) {
        this.accrDayType = accrDayType == null ? null : accrDayType.trim();
    }

    /**
     * 取得 计提频率值
     * @return 计提频率值
     */
    public String getAccrDayValue() {
        return accrDayValue;
    }

    /**
     * 设置 计提频率值
     * @param accrDayValue 计提频率值
     */
    public void setAccrDayValue(String accrDayValue) {
        this.accrDayValue = accrDayValue == null ? null : accrDayValue.trim();
    }

    /**
     * 取得 营业日准则
     * @return 营业日准则
     */
    public String getAccrTdayRule() {
        return accrTdayRule;
    }

    /**
     * 设置 营业日准则
     * @param accrTdayRule 营业日准则
     */
    public void setAccrTdayRule(String accrTdayRule) {
        this.accrTdayRule = accrTdayRule == null ? null : accrTdayRule.trim();
    }

    /**
     * 取得 首次计提日
     * @return 首次计提日
     */
    public Date getfAccrDate() {
        return fAccrDate;
    }

    /**
     * 设置 首次计提日
     * @param fAccrDate 首次计提日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setfAccrDate(Date fAccrDate) {
        this.fAccrDate = fAccrDate;
    }

    /**
     * 取得 计提结束日
     * @return 计提结束日
     */
    public Date geteAccrDate() {
        return eAccrDate;
    }

    /**
     * 设置 计提结束日
     * @param eAccrDate 计提结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void seteAccrDate(Date eAccrDate) {
        this.eAccrDate = eAccrDate;
    }

    /**
     * 取得 区间规则
     * @return 区间规则
     */
    public String getIntervalRule() {
        return intervalRule;
    }

    /**
     * 设置 区间规则
     * @param intervalRule 区间规则
     */
    public void setIntervalRule(String intervalRule) {
        this.intervalRule = intervalRule == null ? null : intervalRule.trim();
    }

    /**
     * 取得 首个计提区间结束日
     * @return 首个计提区间结束日
     */
    public Date getfAccrEndDate() {
        return fAccrEndDate;
    }

    /**
     * 设置 首个计提区间结束日
     * @param fAccrEndDate 首个计提区间结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setfAccrEndDate(Date fAccrEndDate) {
        this.fAccrEndDate = fAccrEndDate;
    }

    /**
     * 取得 首次计费基数
     * @return 首次计费基数
     */
    public String getFirstBaseType() {
        return firstBaseType;
    }

    /**
     * 设置 首次计费基数
     * @param firstBaseType 首次计费基数
     */
    public void setFirstBaseType(String firstBaseType) {
        this.firstBaseType = firstBaseType == null ? null : firstBaseType.trim();
    }
}