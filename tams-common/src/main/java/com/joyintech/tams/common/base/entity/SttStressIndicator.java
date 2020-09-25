package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressIndicator extends BaseEntity {
    /**
     * 压测指标代码
     */
    private String seqNo;

    /**
     * 业务代码
     */
    private String busiId;

    /**
     * 指标代码
     */
    private String indId;

    /**
     * 模型代码
     */
    private String modelId;

    /**
     * 压测业务类型，数据字典【压测业务类型】
     */
    private String sttBusiType;

    /**
     * 取得 压测指标代码
     * @return 压测指标代码
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 压测指标代码
     * @param seqNo 压测指标代码
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 业务代码
     * @return 业务代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务代码
     * @param busiId 业务代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 指标代码
     * @param indId 指标代码
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }

    /**
     * 取得 模型代码
     * @return 模型代码
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * 设置 模型代码
     * @param modelId 模型代码
     */
    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    /**
     * 取得 压测业务类型，数据字典【压测业务类型】
     * @return 压测业务类型，数据字典【压测业务类型】
     */
    public String getSttBusiType() {
        return sttBusiType;
    }

    /**
     * 设置 压测业务类型，数据字典【压测业务类型】
     * @param sttBusiType 压测业务类型，数据字典【压测业务类型】
     */
    public void setSttBusiType(String sttBusiType) {
        this.sttBusiType = sttBusiType == null ? null : sttBusiType.trim();
    }
}