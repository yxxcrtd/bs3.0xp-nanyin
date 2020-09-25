package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressLayeringKey extends BaseEntity {
    /**
     * 模板代码
     */
    private String testId;

    /**
     * 级别
     */
    private Long level;

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