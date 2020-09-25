package com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto;

import com.joyintech.framework.base.BaseEntity;

public class ReconcileProductColResultKey extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 对账结果代码
     */
    private String compareId;

    /**
     * 对比列
映射结构映射表的本方列ID
     */
    private String compareCol;

    /**
     * 取得 对账结果代码
     * @return 对账结果代码
     */
    public String getCompareId() {
        return compareId;
    }

    /**
     * 设置 对账结果代码
     * @param compareId 对账结果代码
     */
    public void setCompareId(String compareId) {
        this.compareId = compareId == null ? null : compareId.trim();
    }

    /**
     * 取得 对比列
映射结构映射表的本方列ID
     * @return 对比列
映射结构映射表的本方列ID
     */
    public String getCompareCol() {
        return compareCol;
    }

    /**
     * 设置 对比列
映射结构映射表的本方列ID
     * @param compareCol 对比列
映射结构映射表的本方列ID
     */
    public void setCompareCol(String compareCol) {
        this.compareCol = compareCol == null ? null : compareCol.trim();
    }
}