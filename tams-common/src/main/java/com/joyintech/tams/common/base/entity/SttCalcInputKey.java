package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttCalcInputKey extends BaseEntity {
    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 结构代码
     */
    private String strId;

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
     * 取得 结构代码
     * @return 结构代码
     */
    public String getStrId() {
        return strId;
    }

    /**
     * 设置 结构代码
     * @param strId 结构代码
     */
    public void setStrId(String strId) {
        this.strId = strId == null ? null : strId.trim();
    }
}