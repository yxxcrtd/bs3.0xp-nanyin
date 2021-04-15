package com.joyintech.tams.invest.common.position.dto;

import com.joyintech.framework.base.BaseEntity;

public class PtlPositionFlowCfgKey extends BaseEntity {
    /**
     * 场景Id
     */
    private String sceneId;

    /**
     * 序号
     */
    private String cfgNumber;

    /**
     * 取得 场景Id
     * @return 场景Id
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 设置 场景Id
     * @param sceneId 场景Id
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    /**
     * 取得 序号
     * @return 序号
     */
    public String getCfgNumber() {
        return cfgNumber;
    }

    /**
     * 设置 序号
     * @param cfgNumber 序号
     */
    public void setCfgNumber(String cfgNumber) {
        this.cfgNumber = cfgNumber == null ? null : cfgNumber.trim();
    }
}