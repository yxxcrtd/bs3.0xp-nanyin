package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValTempColCheckInfoKey extends BaseEntity {
    /**
     * null
     */
    private String tmpEName;

    /**
     * null
     */
    private String colEName;

    /**
     * 取得 null
     * @return null
     */
    public String getTmpEName() {
        return tmpEName;
    }

    /**
     * 设置 null
     * @param tmpEName null
     */
    public void setTmpEName(String tmpEName) {
        this.tmpEName = tmpEName == null ? null : tmpEName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getColEName() {
        return colEName;
    }

    /**
     * 设置 null
     * @param colEName null
     */
    public void setColEName(String colEName) {
        this.colEName = colEName == null ? null : colEName.trim();
    }
}