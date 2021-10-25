package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlSecReconPara extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 券商代码
     */
    private String customerId;

    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 参数值
     */
    private String paraValue;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 序列号
     * @param seqNo 序列号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 券商代码
     * @return 券商代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 券商代码
     * @param customerId 券商代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    /**
     * 取得 参数值
     * @return 参数值
     */
    public String getParaValue() {
        return paraValue;
    }

    /**
     * 设置 参数值
     * @param paraValue 参数值
     */
    public void setParaValue(String paraValue) {
        this.paraValue = paraValue == null ? null : paraValue.trim();
    }
}