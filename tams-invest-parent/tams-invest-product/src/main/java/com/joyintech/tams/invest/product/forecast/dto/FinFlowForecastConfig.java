package com.joyintech.tams.invest.product.forecast.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.forecast.validator.FinFlowForecastConfigValidator;

@ValidLogic(targetClass = FinFlowForecastConfigValidator.class)
public class FinFlowForecastConfig extends FinFlowForecastConfigKey {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 指标值1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimeone;

    /**
     * 指标值2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimetwo;

    /**
     * 指标值3
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimethree;

    /**
     * 指标值4
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefour;

    /**
     * 指标值5
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefive;

    /**
     * 指标值6
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimesix;
    
    /**
     * 指标值1标题名称
     */
    private String targetName1;
    
    /**
     * 指标值2标题名称
     */
    private String targetName2;
    
    /**
     * 指标值3标题名称
     */
    private String targetName3;
    
    /**
     * 指标值4标题名称
     */
    private String targetName4;
    
    /**
     * 指标值5标题名称
     */
    private String targetName5;
    
    /**
     * 指标值6标题名称
     */
    private String targetName6;
    
    
    /**
     * 流动性储备情况列表
     */
    private List<FinFlowForecastConfig> reserveDetailList = new ArrayList<FinFlowForecastConfig>();
    /**
     * 流动性预测列表
     */
    private List<FinFlowForecastConfig> forecastList = new ArrayList<FinFlowForecastConfig>();
    
    /**
     * 取得 指标值1
     * @return 指标值1
     */
    public BigDecimal getTargetTimeone() {
        return targetTimeone;
    }

    /**
     * 设置 指标值1
     * @param targetTimeone 指标值1
     */
    public void setTargetTimeone(BigDecimal targetTimeone) {
        this.targetTimeone = targetTimeone;
    }

    /**
     * 取得 指标值2
     * @return 指标值2
     */
    public BigDecimal getTargetTimetwo() {
        return targetTimetwo;
    }

    /**
     * 设置 指标值2
     * @param targetTimetwo 指标值2
     */
    public void setTargetTimetwo(BigDecimal targetTimetwo) {
        this.targetTimetwo = targetTimetwo;
    }

    /**
     * 取得 指标值3
     * @return 指标值3
     */
    public BigDecimal getTargetTimethree() {
        return targetTimethree;
    }

    /**
     * 设置 指标值3
     * @param targetTimethree 指标值3
     */
    public void setTargetTimethree(BigDecimal targetTimethree) {
        this.targetTimethree = targetTimethree;
    }

    /**
     * 取得 指标值4
     * @return 指标值4
     */
    public BigDecimal getTargetTimefour() {
        return targetTimefour;
    }

    /**
     * 设置 指标值4
     * @param targetTimefour 指标值4
     */
    public void setTargetTimefour(BigDecimal targetTimefour) {
        this.targetTimefour = targetTimefour;
    }

    /**
     * 取得 指标值5
     * @return 指标值5
     */
    public BigDecimal getTargetTimefive() {
        return targetTimefive;
    }

    /**
     * 设置 指标值5
     * @param targetTimefive 指标值5
     */
    public void setTargetTimefive(BigDecimal targetTimefive) {
        this.targetTimefive = targetTimefive;
    }

    /**
     * 取得 指标值6
     * @return 指标值6
     */
    public BigDecimal getTargetTimesix() {
        return targetTimesix;
    }

    /**
     * 设置 指标值6
     * @param targetTimesix 指标值6
     */
    public void setTargetTimesix(BigDecimal targetTimesix) {
        this.targetTimesix = targetTimesix;
    }

    /**
     * 获取 reserveDetailList
     * @return reserveDetailList.
     */
    public List<FinFlowForecastConfig> getReserveDetailList() {
        return reserveDetailList;
    }

    /**
     * 设置 reserveDetailList
     * @param reserveDetailList reserveDetailList
     */
    public void setReserveDetailList(List<FinFlowForecastConfig> reserveDetailList) {
        this.reserveDetailList = reserveDetailList;
    }

    /**
     * 获取 forecastList
     * @return forecastList.
     */
    public List<FinFlowForecastConfig> getForecastList() {
        return forecastList;
    }

    /**
     * 设置 forecastList
     * @param forecastList forecastList
     */
    public void setForecastList(List<FinFlowForecastConfig> forecastList) {
        this.forecastList = forecastList;
    }
    
    /**
     * 获取 targetName1
     * @return targetName1.
     */
    public String getTargetName1() {
        return targetName1;
    }

    
    /**
     * 设置 targetName1
     * @param targetName1 targetName1
     */
    public void setTargetName1(String targetName1) {
        this.targetName1 = targetName1;
    }

    
    /**
     * 获取 targetName2
     * @return targetName2.
     */
    public String getTargetName2() {
        return targetName2;
    }

    
    /**
     * 设置 targetName2
     * @param targetName2 targetName2
     */
    public void setTargetName2(String targetName2) {
        this.targetName2 = targetName2;
    }

    
    /**
     * 获取 targetName3
     * @return targetName3.
     */
    public String getTargetName3() {
        return targetName3;
    }

    
    /**
     * 设置 targetName3
     * @param targetName3 targetName3
     */
    public void setTargetName3(String targetName3) {
        this.targetName3 = targetName3;
    }

    
    /**
     * 获取 targetName4
     * @return targetName4.
     */
    public String getTargetName4() {
        return targetName4;
    }

    
    /**
     * 设置 targetName4
     * @param targetName4 targetName4
     */
    public void setTargetName4(String targetName4) {
        this.targetName4 = targetName4;
    }

    
    /**
     * 获取 targetName5
     * @return targetName5.
     */
    public String getTargetName5() {
        return targetName5;
    }

    
    /**
     * 设置 targetName5
     * @param targetName5 targetName5
     */
    public void setTargetName5(String targetName5) {
        this.targetName5 = targetName5;
    }

    
    /**
     * 获取 targetName6
     * @return targetName6.
     */
    public String getTargetName6() {
        return targetName6;
    }

    
    /**
     * 设置 targetName6
     * @param targetName6 targetName6
     */
    public void setTargetName6(String targetName6) {
        this.targetName6 = targetName6;
    }
    
    public String getTargetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.流动性预测维护_指标种类, super.getTargetType());
    }
}