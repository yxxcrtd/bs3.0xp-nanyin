package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstBondMarketInfoKey extends BaseEntity {
    /**
     * 债券代码（市场代码）
     */
    private String secId;

    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 取得 债券代码（市场代码）
     * @return 债券代码（市场代码）
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 债券代码（市场代码）
     * @param secId 债券代码（市场代码）
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 估值日
     * @return 估值日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 估值日
     * @param valueDate 估值日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }
}