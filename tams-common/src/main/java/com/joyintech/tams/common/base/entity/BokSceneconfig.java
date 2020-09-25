package com.joyintech.tams.common.base.entity;

public class BokSceneconfig extends BokSceneconfigKey {
    /**
     * 场景分支父节点
     */
    private String fsceneNodeid;

    /**
     * 级别
     */
    private Long level;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 场景分支父节点
     * @return 场景分支父节点
     */
    public String getFsceneNodeid() {
        return fsceneNodeid;
    }

    /**
     * 设置 场景分支父节点
     * @param fsceneNodeid 场景分支父节点
     */
    public void setFsceneNodeid(String fsceneNodeid) {
        this.fsceneNodeid = fsceneNodeid == null ? null : fsceneNodeid.trim();
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

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}