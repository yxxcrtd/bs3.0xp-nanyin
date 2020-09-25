package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysRoleHisKey extends BaseEntity {
    /**
     * 角色代码
     */
    private String roleId;

    /**
     * 履历番号（流水号单位采番）
     */
    private Integer hisNo;

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
     * 取得 履历番号（流水号单位采番）
     * @return 履历番号（流水号单位采番）
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 履历番号（流水号单位采番）
     * @param hisNo 履历番号（流水号单位采番）
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }
}