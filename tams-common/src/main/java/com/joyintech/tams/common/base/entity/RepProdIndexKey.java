package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepProdIndexKey extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 日期
     */
    private Date valueDate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 日期
     * @param valueDate 日期
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }
}