package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysRoleDeptKey extends BaseEntity {
    /**
     * 角色代码
     */
    private String roleId;

    /**
     * 部门代码:DEPT_CODE
     */
    private String deptCode;

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
     * 取得 部门代码:DEPT_CODE
     * @return 部门代码:DEPT_CODE
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码:DEPT_CODE
     * @param deptCode 部门代码:DEPT_CODE
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
}