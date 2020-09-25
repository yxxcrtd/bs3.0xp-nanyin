package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressSceneKey extends BaseEntity {
    /**
     * 业务代码
     */
    private String busiId;

    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 取得 业务代码
     * @return 业务代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务代码
     * @param busiId 业务代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

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
}