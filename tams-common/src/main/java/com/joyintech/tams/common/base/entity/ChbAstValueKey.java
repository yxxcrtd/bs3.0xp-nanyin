package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbAstValueKey extends BaseEntity {
    /**
     * 主键
     */
    private String valId;
    
    /**
     * 主键
     * @return VAL_ID 主键
     */
    public String getValId() {
        return valId;
    }

    /**
     * 主键
     * @param valId 主键
     */
    public void setValId(String valId) {
        this.valId = valId == null ? null : valId.trim();
    }
}