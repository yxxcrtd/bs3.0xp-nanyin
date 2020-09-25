package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbUserTableStyleKey extends BaseEntity {
    /**
     * 用户代码
     */
    private String userId;

    /**
     * 列表代码
     */
    private String elementId;

    /**
     * 取得 用户代码
     * @return 用户代码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 用户代码
     * @param userId 用户代码
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 列表代码
     * @return 列表代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 列表代码
     * @param elementId 列表代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }
}