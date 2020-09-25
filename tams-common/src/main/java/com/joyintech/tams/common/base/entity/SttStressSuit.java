package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressSuit extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 业务代码
     */
    private String busiId;

    /**
     * 压测业务类型，数据字典【压测业务类型】
     */
    private String sttBusiType;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 级别
     */
    private Long level;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
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

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 取得 参数值
     * @return 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 参数值
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 级别
     * @return 级别
     */
    public Long getLevel() {
        return level;
    }

    /**
     * 设置 级别
     * @param level 级别
     */
    public void setLevel(Long level) {
        this.level = level;
    }

    /**
     * 取得 字典代码
     * @return 字典代码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 字典代码
     * @param dictCode 字典代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }
}