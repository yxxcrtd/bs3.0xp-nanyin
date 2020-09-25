package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblSubjectKey extends BaseEntity {
    /**
     * 科目代码
     */
    private String subCode;

    /**
     * 科目类型
     */
    private String subType;

    /**
     * 取得 科目代码
     * @return 科目代码
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * 设置 科目代码
     * @param subCode 科目代码
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode == null ? null : subCode.trim();
    }

    /**
     * 取得 科目类型
     * @return 科目类型
     */
    public String getSubType() {
        return subType;
    }

    /**
     * 设置 科目类型
     * @param subType 科目类型
     */
    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }
}