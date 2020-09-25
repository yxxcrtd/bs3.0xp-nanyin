package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlIncomeIndexFloatKey extends BaseEntity {
    /**
     * null
     */
    private String indexId;

    /**
     * null
     */
    private Long lev;

    /**
     * 取得 null
     * @return null
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 null
     * @param indexId null
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getLev() {
        return lev;
    }

    /**
     * 设置 null
     * @param lev null
     */
    public void setLev(Long lev) {
        this.lev = lev;
    }
}