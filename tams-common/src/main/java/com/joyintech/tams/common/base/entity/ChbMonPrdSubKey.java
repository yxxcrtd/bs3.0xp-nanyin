package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbMonPrdSubKey extends BaseEntity {
    /**
     * 报送期数
     */
    private String prodId;

    /**
     * 取得 报送期数
     * @return 报送期数
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 报送期数
     * @param prodId 报送期数
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }
}