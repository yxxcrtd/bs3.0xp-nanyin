package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepCheckKey extends BaseEntity {
    /**
     * 校验编号
     */
    private String checkId;

    /**
     * 取得 校验编号
     * @return 校验编号
     */
    public String getCheckId() {
        return checkId;
    }

    /**
     * 设置 校验编号
     * @param checkId 校验编号
     */
    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }
}