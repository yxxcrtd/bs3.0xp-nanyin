package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepIndexPrimary extends BaseEntity {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 指标描述
     */
    private String indexDescribe;

    /**
     * 取得 主键ID
     * @return 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键ID
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 指标名称
     * @return 指标名称
     */
    public String getIndexName() {
        return indexName;
    }

    /**
     * 设置 指标名称
     * @param indexName 指标名称
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    /**
     * 取得 指标描述
     * @return 指标描述
     */
    public String getIndexDescribe() {
        return indexDescribe;
    }

    /**
     * 设置 指标描述
     * @param indexDescribe 指标描述
     */
    public void setIndexDescribe(String indexDescribe) {
        this.indexDescribe = indexDescribe == null ? null : indexDescribe.trim();
    }
}