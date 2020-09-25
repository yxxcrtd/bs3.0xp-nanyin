package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TilterInfo extends BaseEntity {
    /**
     * 过滤器代码
     */
    private String filterCode;

    /**
     * 过滤器名称
     */
    private String filterName;

    /**
     * 过滤器权限
     */
    private String filterRight;

    /**
     * 所属用户
     */
    private String userId;

    /**
     * 是否默认
     */
    private String isDefault;

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
     * 取得 过滤器名称
     * @return 过滤器名称
     */
    public String getFilterName() {
        return filterName;
    }

    /**
     * 设置 过滤器名称
     * @param filterName 过滤器名称
     */
    public void setFilterName(String filterName) {
        this.filterName = filterName == null ? null : filterName.trim();
    }

    /**
     * 取得 过滤器权限
     * @return 过滤器权限
     */
    public String getFilterRight() {
        return filterRight;
    }

    /**
     * 设置 过滤器权限
     * @param filterRight 过滤器权限
     */
    public void setFilterRight(String filterRight) {
        this.filterRight = filterRight == null ? null : filterRight.trim();
    }

    /**
     * 取得 所属用户
     * @return 所属用户
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 所属用户
     * @param userId 所属用户
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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