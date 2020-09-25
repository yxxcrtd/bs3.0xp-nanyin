package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepJobExcludePro extends BaseEntity {
    /**
     * ID
     */
    private String id;

    /**
     * 作业编号
     */
    private String jobId;

    /**
     * 产品编号
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 取得 ID
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 作业编号
     * @return 作业编号
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * 设置 作业编号
     * @param jobId 作业编号
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * 取得 产品编号
     * @return 产品编号
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品编号
     * @param prodId 产品编号
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }
}