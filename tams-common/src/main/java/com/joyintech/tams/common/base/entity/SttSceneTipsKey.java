package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttSceneTipsKey extends BaseEntity {
    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 参数代码
     */
    private String tipsCode;

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
     * 取得 参数代码
     * @return 参数代码
     */
    public String getTipsCode() {
        return tipsCode;
    }

    /**
     * 设置 参数代码
     * @param tipsCode 参数代码
     */
    public void setTipsCode(String tipsCode) {
        this.tipsCode = tipsCode == null ? null : tipsCode.trim();
    }
}