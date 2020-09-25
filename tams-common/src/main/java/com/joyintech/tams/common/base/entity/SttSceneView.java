package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttSceneView extends BaseEntity {
    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 画面ID
     */
    private String pageId;

    /**
     * 取得 情景代码
     * @return 情景代码
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 设置 情景代码
     * @param sceneId 情景代码
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
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