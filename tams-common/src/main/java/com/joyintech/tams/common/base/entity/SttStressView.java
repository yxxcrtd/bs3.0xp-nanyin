package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressView extends BaseEntity {
    /**
     * 模板代码
     */
    private String tmplId;

    /**
     * 画面ID
     */
    private String pageId;

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTmplId() {
        return tmplId;
    }

    /**
     * 设置 模板代码
     * @param tmplId 模板代码
     */
    public void setTmplId(String tmplId) {
        this.tmplId = tmplId == null ? null : tmplId.trim();
    }

    /**
     * 取得 画面ID
     * @return 画面ID
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 画面ID
     * @param pageId 画面ID
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }
}