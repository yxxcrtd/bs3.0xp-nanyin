package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysRoleKey extends BaseEntity {
    /**
     * 角色代码
     */
    private String roleId;

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
}