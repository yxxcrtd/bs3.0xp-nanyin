package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class FilterDetailKey extends BaseEntity {
    /**
     * 过滤器代码
     */
    private String filterCode;

    /**
     * 指标明细代码
     */
    private String taregetCode;

    /**
     * 取得 过滤器代码
     * @return 过滤器代码
     */
    public String getFilterCode() {
        return filterCode;
    }

    /**
     * 设置 过滤器代码
     * @param filterCode 过滤器代码
     */
    public void setFilterCode(String filterCode) {
        this.filterCode = filterCode == null ? null : filterCode.trim();
    }

    /**
     * 取得 指标明细代码
     * @return 指标明细代码
     */
    public String getTaregetCode() {
        return taregetCode;
    }

    /**
     * 设置 指标明细代码
     * @param taregetCode 指标明细代码
     */
    public void setTaregetCode(String taregetCode) {
        this.taregetCode = taregetCode == null ? null : taregetCode.trim();
    }
}