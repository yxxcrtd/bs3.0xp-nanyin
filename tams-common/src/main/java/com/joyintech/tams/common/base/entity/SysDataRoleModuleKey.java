package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SysDataRoleModuleKey extends BaseEntity {
    /**
     * 角色代码
     */
    private String roleId;

    /**
     * 机构代码
     */
    private String institutionCode;

    /**
     * 部门代码（没有部门的时候，99固定）
     */
    private String deptCode;

    /**
     * 数据权限类型
     */
    private String roleDataType;

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
     * 取得 机构代码
     * @return 机构代码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 机构代码
     * @param institutionCode 机构代码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 部门代码（没有部门的时候，99固定）
     * @return 部门代码（没有部门的时候，99固定）
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码（没有部门的时候，99固定）
     * @param deptCode 部门代码（没有部门的时候，99固定）
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 数据权限类型
     * @return 数据权限类型
     */
    public String getRoleDataType() {
        return roleDataType;
    }

    /**
     * 设置 数据权限类型
     * @param roleDataType 数据权限类型
     */
    public void setRoleDataType(String roleDataType) {
        this.roleDataType = roleDataType == null ? null : roleDataType.trim();
    }
}