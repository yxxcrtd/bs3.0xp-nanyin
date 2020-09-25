package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysUserRoleHis extends BaseEntity {
    /**
     * ����
     */
    private String userId;

    /**
     * ����
     */
    private Integer hisNo;

    /**
     * ����
     */
    private String roleId;

    /**
     * 取得 ����
     * @return ����
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 ����
     * @param userId ����
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 ����
     * @param hisNo ����
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置 ����
     * @param roleId ����
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}