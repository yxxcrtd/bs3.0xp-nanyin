package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.util.Date;

public class MockTestRsk extends MockTestRskKey {
    /**
     * 指标名称
     */
    private String indName;

    /**
     * 日期
     */
    private Date riskDate;

    /**
     * 预警阈值
     */
    private String wInvValue;

    /**
     * 审批阈值
     */
    private String aInvValue;

    /**
     * 违规阈值
     */
    private String eInvValue;

    /**
     * 当前实际值
     */
    private String currValue;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 指标状态
     */
    private String kriStatus;

    /**
     * 展示顺序序号
     */
    private Long orderId;

    /**
     * 取得 指标名称
     * @return 指标名称
     */
    public String getIndName() {
        return indName;
    }

    /**
     * 设置 指标名称
     * @param indName 指标名称
     */
    public void setIndName(String indName) {
        this.indName = indName == null ? null : indName.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getRiskDate() {
        return riskDate;
    }

    /**
     * 设置 日期
     * @param riskDate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRiskDate(Date riskDate) {
        this.riskDate = riskDate;
    }

    /**
     * 取得 预警阈值
     * @return 预警阈值
     */
    public String getwInvValue() {
        return wInvValue;
    }

    /**
     * 设置 预警阈值
     * @param wInvValue 预警阈值
     */
    public void setwInvValue(String wInvValue) {
        this.wInvValue = wInvValue == null ? null : wInvValue.trim();
    }

    /**
     * 取得 审批阈值
     * @return 审批阈值
     */
    public String getaInvValue() {
        return aInvValue;
    }

    /**
     * 设置 审批阈值
     * @param aInvValue 审批阈值
     */
    public void setaInvValue(String aInvValue) {
        this.aInvValue = aInvValue == null ? null : aInvValue.trim();
    }

    /**
     * 取得 违规阈值
     * @return 违规阈值
     */
    public String geteInvValue() {
        return eInvValue;
    }

    /**
     * 设置 违规阈值
     * @param eInvValue 违规阈值
     */
    public void seteInvValue(String eInvValue) {
        this.eInvValue = eInvValue == null ? null : eInvValue.trim();
    }

    /**
     * 取得 当前实际值
     * @return 当前实际值
     */
    public String getCurrValue() {
        return currValue;
    }

    /**
     * 设置 当前实际值
     * @param currValue 当前实际值
     */
    public void setCurrValue(String currValue) {
        this.currValue = currValue == null ? null : currValue.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 指标状态
     * @return 指标状态
     */
    public String getKriStatus() {
        return kriStatus;
    }

    /**
     * 设置 指标状态
     * @param kriStatus 指标状态
     */
    public void setKriStatus(String kriStatus) {
        this.kriStatus = kriStatus == null ? null : kriStatus.trim();
    }

    /**
     * 取得 展示顺序序号
     * @return 展示顺序序号
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置 展示顺序序号
     * @param orderId 展示顺序序号
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}