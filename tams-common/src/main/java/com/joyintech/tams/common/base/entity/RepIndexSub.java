package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepIndexSub extends BaseEntity {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 指标外键
     */
    private String priId;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 指标值
     */
    private String indexValue;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 生效日期
     */
    private Date indexDate;

    /**
     * null
     */
    private String indexFile;

    /**
     * 产品范围说明
     */
    private String indexDetail;

    /**
     * 审核状态
     */
    private String auditStatus;

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
     * 取得 指标外键
     * @return 指标外键
     */
    public String getPriId() {
        return priId;
    }

    /**
     * 设置 指标外键
     * @param priId 指标外键
     */
    public void setPriId(String priId) {
        this.priId = priId == null ? null : priId.trim();
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
     * 取得 指标值
     * @return 指标值
     */
    public String getIndexValue() {
        return indexValue;
    }

    /**
     * 设置 指标值
     * @param indexValue 指标值
     */
    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue == null ? null : indexValue.trim();
    }

    /**
     * 取得 指标类型
     * @return 指标类型
     */
    public String getIndexType() {
        return indexType;
    }

    /**
     * 设置 指标类型
     * @param indexType 指标类型
     */
    public void setIndexType(String indexType) {
        this.indexType = indexType == null ? null : indexType.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getIndexDate() {
        return indexDate;
    }

    /**
     * 设置 生效日期
     * @param indexDate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setIndexDate(Date indexDate) {
        this.indexDate = indexDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIndexFile() {
        return indexFile;
    }

    /**
     * 设置 null
     * @param indexFile null
     */
    public void setIndexFile(String indexFile) {
        this.indexFile = indexFile == null ? null : indexFile.trim();
    }

    /**
     * 取得 产品范围说明
     * @return 产品范围说明
     */
    public String getIndexDetail() {
        return indexDetail;
    }

    /**
     * 设置 产品范围说明
     * @param indexDetail 产品范围说明
     */
    public void setIndexDetail(String indexDetail) {
        this.indexDetail = indexDetail == null ? null : indexDetail.trim();
    }

    /**
     * 取得 审核状态
     * @return 审核状态
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置 审核状态
     * @param auditStatus 审核状态
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }
}