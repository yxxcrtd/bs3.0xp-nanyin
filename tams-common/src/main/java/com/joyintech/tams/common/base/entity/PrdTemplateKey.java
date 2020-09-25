package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdTemplateKey extends BaseEntity {
    /**
     * 模板代码
     */
    private String templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * 设置 模板代码
     * @param templateId 模板代码
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    /**
     * 取得 模板名称
     * @return 模板名称
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 设置 模板名称
     * @param templateName 模板名称
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }
}