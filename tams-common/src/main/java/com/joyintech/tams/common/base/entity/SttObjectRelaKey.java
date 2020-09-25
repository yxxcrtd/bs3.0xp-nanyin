package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttObjectRelaKey extends BaseEntity {
    /**
     * 对象组代码
     */
    private String objectGrpId;

    /**
     * 对象代码
     */
    private String objectId;

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
     * 取得 对象代码
     * @return 对象代码
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象代码
     * @param objectId 对象代码
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }
}