package com.joyintech.tams.common.base.entity;

public class RepProdBasicChange extends RepProdBasicChangeKey {
    /**
     * 指数权重：存储结构为a%
     */
    private String proportion;

    /**
     * 组ID
     */
    private String groupId;

    /**
     * 取得 指数权重：存储结构为a%
     * @return 指数权重：存储结构为a%
     */
    public String getProportion() {
        return proportion;
    }

    /**
     * 设置 指数权重：存储结构为a%
     * @param proportion 指数权重：存储结构为a%
     */
    public void setProportion(String proportion) {
        this.proportion = proportion == null ? null : proportion.trim();
    }

    /**
     * 取得 组ID
     * @return 组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 组ID
     * @param groupId 组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }
}