package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokProductColResultKey extends BaseEntity {
    /**
     * null
     */
    private String compareId;

    /**
     * null
     */
    private String compareCol;

    /**
     * 取得 null
     * @return null
     */
    public String getCompareId() {
        return compareId;
    }

    /**
     * 设置 null
     * @param compareId null
     */
    public void setCompareId(String compareId) {
        this.compareId = compareId == null ? null : compareId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCompareCol() {
        return compareCol;
    }

    /**
     * 设置 null
     * @param compareCol null
     */
    public void setCompareCol(String compareCol) {
        this.compareCol = compareCol == null ? null : compareCol.trim();
    }
}