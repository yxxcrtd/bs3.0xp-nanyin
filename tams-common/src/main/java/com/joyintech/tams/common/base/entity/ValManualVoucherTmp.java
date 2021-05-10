package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValManualVoucherTmp extends BaseEntity {
    /**
     * 模板编号
     */
    private String tmpCode;

    /**
     * 模板名称
     */
    private String tmpName;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 分录类型
     */
    private String recordType;

    /**
     * 摘要
     */
    private String remark;

    /**
     * 取得 模板编号
     * @return 模板编号
     */
    public String getTmpCode() {
        return tmpCode;
    }

    /**
     * 设置 模板编号
     * @param tmpCode 模板编号
     */
    public void setTmpCode(String tmpCode) {
        this.tmpCode = tmpCode == null ? null : tmpCode.trim();
    }

    /**
     * 取得 模板名称
     * @return 模板名称
     */
    public String getTmpName() {
        return tmpName;
    }

    /**
     * 设置 模板名称
     * @param tmpName 模板名称
     */
    public void setTmpName(String tmpName) {
        this.tmpName = tmpName == null ? null : tmpName.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 分录类型
     * @return 分录类型
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * 设置 分录类型
     * @param recordType 分录类型
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType == null ? null : recordType.trim();
    }

    /**
     * 取得 摘要
     * @return 摘要
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 摘要
     * @param remark 摘要
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}