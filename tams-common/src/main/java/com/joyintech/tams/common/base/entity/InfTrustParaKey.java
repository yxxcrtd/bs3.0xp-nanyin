package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InfTrustParaKey extends BaseEntity {
    /**
     * 内部机构代码
     */
    private String deptId;

    /**
     * 托管人
     */
    private String trusteeId;

    /**
     * 取得 内部机构代码
     * @return 内部机构代码
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置 内部机构代码
     * @param deptId 内部机构代码
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    /**
     * 取得 托管人
     * @return 托管人
     */
    public String getTrusteeId() {
        return trusteeId;
    }

    /**
     * 设置 托管人
     * @param trusteeId 托管人
     */
    public void setTrusteeId(String trusteeId) {
        this.trusteeId = trusteeId == null ? null : trusteeId.trim();
    }
}