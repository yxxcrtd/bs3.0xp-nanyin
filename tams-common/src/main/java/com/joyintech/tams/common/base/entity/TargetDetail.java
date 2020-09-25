package com.joyintech.tams.common.base.entity;

public class TargetDetail extends TargetDetailKey {
    /**
     * 指标明细名称
     */
    private String taregetName;

    /**
     * 是否默认
     */
    private String isDefault;

    /**
     * 取得 指标明细名称
     * @return 指标明细名称
     */
    public String getTaregetName() {
        return taregetName;
    }

    /**
     * 设置 指标明细名称
     * @param taregetName 指标明细名称
     */
    public void setTaregetName(String taregetName) {
        this.taregetName = taregetName == null ? null : taregetName.trim();
    }

    /**
     * 取得 是否默认
     * @return 是否默认
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 是否默认
     * @param isDefault 是否默认
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }
}