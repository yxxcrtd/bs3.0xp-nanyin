package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InterfBondMarketInfoKey extends BaseEntity {
    /**
     * 债券ID：xxxx.IB/xxxx.SZ
     */
    private String secId;

    /**
     * 市值日
     */
    private Date valueDate;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 取得 债券ID：xxxx.IB/xxxx.SZ
     * @return 债券ID：xxxx.IB/xxxx.SZ
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 债券ID：xxxx.IB/xxxx.SZ
     * @param secId 债券ID：xxxx.IB/xxxx.SZ
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 市值日
     * @return 市值日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 市值日
     * @param valueDate 市值日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 数据来源
     * @param source 数据来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}