package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysUserRoleKey extends BaseEntity {
    /**
     * 角色代码
     */
    private String roleId;

    /**
     * 用户代码
     */
    private String userId;

    /**
     * 取得 角色代码
     * @return 角色代码
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置 角色代码
     * @param roleId 角色代码
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

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
}