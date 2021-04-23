package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CfetsDatadictConvert extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 字段代码
     */
    private String fieldCode;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 系统字典代码
     */
    private String systemDictCode;

    /**
     * 系统数据代码(业务系统)
     */
    private String systemItemCode;

    /**
     * Cfets数据代码
     */
    private String cfetsItemCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 字段代码
     * @return 字段代码
     */
    public String getFieldCode() {
        return fieldCode;
    }

    /**
     * 设置 字段代码
     * @param fieldCode 字段代码
     */
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    /**
     * 取得 字段名称
     * @return 字段名称
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 设置 字段名称
     * @param fieldName 字段名称
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**
     * 取得 系统字典代码
     * @return 系统字典代码
     */
    public String getSystemDictCode() {
        return systemDictCode;
    }

    /**
     * 设置 系统字典代码
     * @param systemDictCode 系统字典代码
     */
    public void setSystemDictCode(String systemDictCode) {
        this.systemDictCode = systemDictCode == null ? null : systemDictCode.trim();
    }

    /**
     * 取得 系统数据代码(业务系统)
     * @return 系统数据代码(业务系统)
     */
    public String getSystemItemCode() {
        return systemItemCode;
    }

    /**
     * 设置 系统数据代码(业务系统)
     * @param systemItemCode 系统数据代码(业务系统)
     */
    public void setSystemItemCode(String systemItemCode) {
        this.systemItemCode = systemItemCode == null ? null : systemItemCode.trim();
    }

    /**
     * 取得 Cfets数据代码
     * @return Cfets数据代码
     */
    public String getCfetsItemCode() {
        return cfetsItemCode;
    }

    /**
     * 设置 Cfets数据代码
     * @param cfetsItemCode Cfets数据代码
     */
    public void setCfetsItemCode(String cfetsItemCode) {
        this.cfetsItemCode = cfetsItemCode == null ? null : cfetsItemCode.trim();
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