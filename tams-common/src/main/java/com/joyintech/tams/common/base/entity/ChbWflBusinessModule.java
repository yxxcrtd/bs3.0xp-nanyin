package com.joyintech.tams.common.base.entity;

public class ChbWflBusinessModule extends ChbWflBusinessModuleKey {
    /**
     * 模板代码（ACT_GE_BYTEARRAY.ID_）
     */
    private String moduleId;

    /**
     * 取得 模板代码（ACT_GE_BYTEARRAY.ID_）
     * @return 模板代码（ACT_GE_BYTEARRAY.ID_）
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置 模板代码（ACT_GE_BYTEARRAY.ID_）
     * @param moduleId 模板代码（ACT_GE_BYTEARRAY.ID_）
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }
}