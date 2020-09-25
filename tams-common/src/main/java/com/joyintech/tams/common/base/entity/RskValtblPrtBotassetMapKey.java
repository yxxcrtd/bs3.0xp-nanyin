package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblPrtBotassetMapKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 科目代码
     */
    private String subCode;

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 组合代码
     * @param ptlId 组合代码
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId == null ? null : ptlId.trim();
    }

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
}