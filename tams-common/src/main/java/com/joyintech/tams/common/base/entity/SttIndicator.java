package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttIndicator extends BaseEntity {
    /**
     * 指标代码
     */
    private String indId;

    /**
     * 指标名称
     */
    private String indName;

    /**
     * 指标类型，数据字典【指标类型】
     */
    private String indYpe;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 是否展示
     */
    private String isVisible;

    /**
     * 启用状态
     */
    private String effStatus;

    /**
     * 是否全可用
     */
    private String isAllUseable;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 指标代码
     * @param indId 指标代码
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }

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
     * 取得 指标类型，数据字典【指标类型】
     * @return 指标类型，数据字典【指标类型】
     */
    public String getIndYpe() {
        return indYpe;
    }

    /**
     * 设置 指标类型，数据字典【指标类型】
     * @param indYpe 指标类型，数据字典【指标类型】
     */
    public void setIndYpe(String indYpe) {
        this.indYpe = indYpe == null ? null : indYpe.trim();
    }

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 取得 是否展示
     * @return 是否展示
     */
    public String getIsVisible() {
        return isVisible;
    }

    /**
     * 设置 是否展示
     * @param isVisible 是否展示
     */
    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible == null ? null : isVisible.trim();
    }

    /**
     * 取得 启用状态
     * @return 启用状态
     */
    public String getEffStatus() {
        return effStatus;
    }

    /**
     * 设置 启用状态
     * @param effStatus 启用状态
     */
    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus == null ? null : effStatus.trim();
    }

    /**
     * 取得 是否全可用
     * @return 是否全可用
     */
    public String getIsAllUseable() {
        return isAllUseable;
    }

    /**
     * 设置 是否全可用
     * @param isAllUseable 是否全可用
     */
    public void setIsAllUseable(String isAllUseable) {
        this.isAllUseable = isAllUseable == null ? null : isAllUseable.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}