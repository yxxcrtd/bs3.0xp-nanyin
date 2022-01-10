package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepRelorg extends BaseEntity {
    /**
     * 关联机构代码
     */
    private String relorgId;

    /**
     * 取得 关联机构代码
     * @return 关联机构代码
     */
    public String getRelorgId() {
        return relorgId;
    }

    /**
     * 设置 关联机构代码
     * @param relorgId 关联机构代码
     */
    public void setRelorgId(String relorgId) {
        this.relorgId = relorgId == null ? null : relorgId.trim();
    }
}