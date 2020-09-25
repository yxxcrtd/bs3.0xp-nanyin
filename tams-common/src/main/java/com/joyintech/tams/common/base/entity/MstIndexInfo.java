package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstIndexInfo extends BaseEntity {
    /**
     * 基准编号
     */
    private String indexId;

    /**
     * 基准类型，SAA、复合等
     */
    private String indexType;

    /**
     * 基准名称
     */
    private String indexName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 基准编号
     * @return 基准编号
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 基准编号
     * @param indexId 基准编号
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    /**
     * 取得 基准类型，SAA、复合等
     * @return 基准类型，SAA、复合等
     */
    public String getIndexType() {
        return indexType;
    }

    /**
     * 设置 基准类型，SAA、复合等
     * @param indexType 基准类型，SAA、复合等
     */
    public void setIndexType(String indexType) {
        this.indexType = indexType == null ? null : indexType.trim();
    }

    /**
     * 取得 基准名称
     * @return 基准名称
     */
    public String getIndexName() {
        return indexName;
    }

    /**
     * 设置 基准名称
     * @param indexName 基准名称
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
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