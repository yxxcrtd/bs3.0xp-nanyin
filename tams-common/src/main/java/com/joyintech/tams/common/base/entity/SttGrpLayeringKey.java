package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttGrpLayeringKey extends BaseEntity {
    /**
     * 对象组代码
     */
    private String objectGrpId;

    /**
     * 级别
     */
    private Long level;

    /**
     * 取得 对象组代码
     * @return 对象组代码
     */
    public String getObjectGrpId() {
        return objectGrpId;
    }

    /**
     * 设置 对象组代码
     * @param objectGrpId 对象组代码
     */
    public void setObjectGrpId(String objectGrpId) {
        this.objectGrpId = objectGrpId == null ? null : objectGrpId.trim();
    }

    /**
     * 取得 级别
     * @return 级别
     */
    public Long getLevel() {
        return level;
    }

    /**
     * 设置 级别
     * @param level 级别
     */
    public void setLevel(Long level) {
        this.level = level;
    }
}