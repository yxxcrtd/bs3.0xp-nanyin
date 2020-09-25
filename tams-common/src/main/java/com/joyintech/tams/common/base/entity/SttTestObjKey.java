package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttTestObjKey extends BaseEntity {
    /**
     * 模板代码
     */
    private String testId;

    /**
     * 压测对象
     */
    private String objectId;

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTestId() {
        return testId;
    }

    /**
     * 设置 模板代码
     * @param testId 模板代码
     */
    public void setTestId(String testId) {
        this.testId = testId == null ? null : testId.trim();
    }

    /**
     * 取得 压测对象
     * @return 压测对象
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 压测对象
     * @param objectId 压测对象
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }
}