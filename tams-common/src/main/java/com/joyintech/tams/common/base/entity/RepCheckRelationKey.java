package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepCheckRelationKey extends BaseEntity {
    /**
     * 关系编号
     */
    private String id;

    /**
     * 取得 关系编号
     * @return 关系编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 关系编号
     * @param id 关系编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}