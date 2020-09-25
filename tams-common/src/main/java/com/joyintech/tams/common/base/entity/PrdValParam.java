package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdValParam extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 单位净值小数位
     */
    private Long unitvalueDec;

    /**
     * 单位净值尾数处理方式
     */
    private String unitvalueMode;

    /**
     * 利率小数位
     */
    private Long rateDec;

    /**
     * 利率处理方式
     */
    private String ratevalueMode;

    /**
     * 万份收益小数位
     */
    private Long milionProfitDec;

    /**
     * 万份收益尾数处理方式
     */
    private String milionProfitDecMode;

    /**
     * 年化收益率小数位
     */
    private Long yearYieldDec;

    /**
     * 年化收益率尾数处理方式
     */
    private String yearYieldDecMode;

    /**
     * 是否计提活期利息
     */
    private String isCountingCurInt;

    /**
     * 亏损处理方式，缩减当前份额、延至下期处理
     */
    private String dealLossMode;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 单位净值小数位
     * @return 单位净值小数位
     */
    public Long getUnitvalueDec() {
        return unitvalueDec;
    }

    /**
     * 设置 单位净值小数位
     * @param unitvalueDec 单位净值小数位
     */
    public void setUnitvalueDec(Long unitvalueDec) {
        this.unitvalueDec = unitvalueDec;
    }

    /**
     * 取得 单位净值尾数处理方式
     * @return 单位净值尾数处理方式
     */
    public String getUnitvalueMode() {
        return unitvalueMode;
    }

    /**
     * 设置 单位净值尾数处理方式
     * @param unitvalueMode 单位净值尾数处理方式
     */
    public void setUnitvalueMode(String unitvalueMode) {
        this.unitvalueMode = unitvalueMode == null ? null : unitvalueMode.trim();
    }

    /**
     * 取得 利率小数位
     * @return 利率小数位
     */
    public Long getRateDec() {
        return rateDec;
    }

    /**
     * 设置 利率小数位
     * @param rateDec 利率小数位
     */
    public void setRateDec(Long rateDec) {
        this.rateDec = rateDec;
    }

    /**
     * 取得 利率处理方式
     * @return 利率处理方式
     */
    public String getRatevalueMode() {
        return ratevalueMode;
    }

    /**
     * 设置 利率处理方式
     * @param ratevalueMode 利率处理方式
     */
    public void setRatevalueMode(String ratevalueMode) {
        this.ratevalueMode = ratevalueMode == null ? null : ratevalueMode.trim();
    }

    /**
     * 取得 万份收益小数位
     * @return 万份收益小数位
     */
    public Long getMilionProfitDec() {
        return milionProfitDec;
    }

    /**
     * 设置 万份收益小数位
     * @param milionProfitDec 万份收益小数位
     */
    public void setMilionProfitDec(Long milionProfitDec) {
        this.milionProfitDec = milionProfitDec;
    }

    /**
     * 取得 万份收益尾数处理方式
     * @return 万份收益尾数处理方式
     */
    public String getMilionProfitDecMode() {
        return milionProfitDecMode;
    }

    /**
     * 设置 万份收益尾数处理方式
     * @param milionProfitDecMode 万份收益尾数处理方式
     */
    public void setMilionProfitDecMode(String milionProfitDecMode) {
        this.milionProfitDecMode = milionProfitDecMode == null ? null : milionProfitDecMode.trim();
    }

    /**
     * 取得 年化收益率小数位
     * @return 年化收益率小数位
     */
    public Long getYearYieldDec() {
        return yearYieldDec;
    }

    /**
     * 设置 年化收益率小数位
     * @param yearYieldDec 年化收益率小数位
     */
    public void setYearYieldDec(Long yearYieldDec) {
        this.yearYieldDec = yearYieldDec;
    }

    /**
     * 取得 年化收益率尾数处理方式
     * @return 年化收益率尾数处理方式
     */
    public String getYearYieldDecMode() {
        return yearYieldDecMode;
    }

    /**
     * 设置 年化收益率尾数处理方式
     * @param yearYieldDecMode 年化收益率尾数处理方式
     */
    public void setYearYieldDecMode(String yearYieldDecMode) {
        this.yearYieldDecMode = yearYieldDecMode == null ? null : yearYieldDecMode.trim();
    }

    /**
     * 取得 是否计提活期利息
     * @return 是否计提活期利息
     */
    public String getIsCountingCurInt() {
        return isCountingCurInt;
    }

    /**
     * 设置 是否计提活期利息
     * @param isCountingCurInt 是否计提活期利息
     */
    public void setIsCountingCurInt(String isCountingCurInt) {
        this.isCountingCurInt = isCountingCurInt == null ? null : isCountingCurInt.trim();
    }

    /**
     * 取得 亏损处理方式，缩减当前份额、延至下期处理
     * @return 亏损处理方式，缩减当前份额、延至下期处理
     */
    public String getDealLossMode() {
        return dealLossMode;
    }

    /**
     * 设置 亏损处理方式，缩减当前份额、延至下期处理
     * @param dealLossMode 亏损处理方式，缩减当前份额、延至下期处理
     */
    public void setDealLossMode(String dealLossMode) {
        this.dealLossMode = dealLossMode == null ? null : dealLossMode.trim();
    }
}