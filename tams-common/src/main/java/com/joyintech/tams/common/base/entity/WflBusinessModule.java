package com.joyintech.tams.common.base.entity;

public class WflBusinessModule extends WflBusinessModuleKey {
    /**
     * 模板代码
     */
    private String moduleId;

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置 模板代码
     * @param moduleId 模板代码
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }
}