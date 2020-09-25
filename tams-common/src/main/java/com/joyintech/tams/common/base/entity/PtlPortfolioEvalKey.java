package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlPortfolioEvalKey extends BaseEntity {
    /**
     * 组合策略代码
     */
    private String ptlStgyId;

    /**
     * 评价指标
     */
    private String evalIndex;

    /**
     * 指标代码
     */
    private String indexId;

    /**
     * 取得 组合策略代码
     * @return 组合策略代码
     */
    public String getPtlStgyId() {
        return ptlStgyId;
    }

    /**
     * 设置 组合策略代码
     * @param ptlStgyId 组合策略代码
     */
    public void setPtlStgyId(String ptlStgyId) {
        this.ptlStgyId = ptlStgyId == null ? null : ptlStgyId.trim();
    }

    /**
     * 取得 评价指标
     * @return 评价指标
     */
    public String getEvalIndex() {
        return evalIndex;
    }

    /**
     * 设置 评价指标
     * @param evalIndex 评价指标
     */
    public void setEvalIndex(String evalIndex) {
        this.evalIndex = evalIndex == null ? null : evalIndex.trim();
    }

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 指标代码
     * @param indexId 指标代码
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }
}