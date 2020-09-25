package com.joyintech.service.disclosure.information.reportpower.dto;

import com.joyintech.framework.base.BaseEntity;

public class SysRole extends BaseEntity {
    /**
     * 角色代码
     */
    private String roleId;

    /**
     * 系统代码
     */
    private String systemCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色岗位
     */
    private String rolePos;

    /**
     * 备注
     */
    private String remark;

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
     * 取得 系统代码
     * @return 系统代码
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 设置 系统代码
     * @param systemCode 系统代码
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    /**
     * 取得 角色名称
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 取得 角色岗位
     * @return 角色岗位
     */
    public String getRolePos() {
        return rolePos;
    }

    /**
     * 设置 角色岗位
     * @param rolePos 角色岗位
     */
    public void setRolePos(String rolePos) {
        this.rolePos = rolePos == null ? null : rolePos.trim();
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