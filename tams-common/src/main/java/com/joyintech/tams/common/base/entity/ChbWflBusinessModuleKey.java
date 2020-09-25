package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbWflBusinessModuleKey extends BaseEntity {
    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 要素代码
     */
    private String elementId;

    /**
     * 取得 画面代码
     * @return 画面代码
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 画面代码
     * @param pageId 画面代码
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 要素代码
     * @return 要素代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 要素代码
     * @param elementId 要素代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }
}