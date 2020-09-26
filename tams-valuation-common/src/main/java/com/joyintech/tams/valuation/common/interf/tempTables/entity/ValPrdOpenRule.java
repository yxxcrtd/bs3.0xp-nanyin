package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ValPrdOpenRule extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 开放模式
     */
    private String openMode;

    /**
     * 开放天数
     */
    private String openDays;

    /**
     * 开放周期
     */
    private String openCycle;

    /**
     * 推算方法
     */
    private String calculation;

    /**
     * 营业日准则
     */
    private String tdayRule;

    /**
     * 首次开放日
     */
    private Date firstOpenDate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 开放模式
     * @return 开放模式
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 设置 开放模式
     * @param openMode 开放模式
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode == null ? null : openMode.trim();
    }

    /**
     * 取得 开放天数
     * @return 开放天数
     */
    public String getOpenDays() {
        return openDays;
    }

    /**
     * 设置 开放天数
     * @param openDays 开放天数
     */
    public void setOpenDays(String openDays) {
        this.openDays = openDays == null ? null : openDays.trim();
    }

    /**
     * 取得 开放周期
     * @return 开放周期
     */
    public String getOpenCycle() {
        return openCycle;
    }

    /**
     * 设置 开放周期
     * @param openCycle 开放周期
     */
    public void setOpenCycle(String openCycle) {
        this.openCycle = openCycle == null ? null : openCycle.trim();
    }

    /**
     * 取得 推算方法
     * @return 推算方法
     */
    public String getCalculation() {
        return calculation;
    }

    /**
     * 设置 推算方法
     * @param calculation 推算方法
     */
    public void setCalculation(String calculation) {
        this.calculation = calculation == null ? null : calculation.trim();
    }

    /**
     * 取得 营业日准则
     * @return 营业日准则
     */
    public String getTdayRule() {
        return tdayRule;
    }

    /**
     * 设置 营业日准则
     * @param tdayRule 营业日准则
     */
    public void setTdayRule(String tdayRule) {
        this.tdayRule = tdayRule == null ? null : tdayRule.trim();
    }

    /**
     * 取得 首次开放日
     * @return 首次开放日
     */
    public Date getFirstOpenDate() {
        return firstOpenDate;
    }

    /**
     * 设置 首次开放日
     * @param firstOpenDate 首次开放日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstOpenDate(Date firstOpenDate) {
        this.firstOpenDate = firstOpenDate;
    }
}