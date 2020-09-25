package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfPayhsrccbvltKey extends BaseEntity {
    /**
     * 债券ID：xxxx.IB/xxxx.SZ
     */
    private String secid;

    /**
     * 估值日
     */
    private String cdate;

    /**
     * 估值来源
     */
    private String valueSource;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 取得 债券ID：xxxx.IB/xxxx.SZ
     * @return 债券ID：xxxx.IB/xxxx.SZ
     */
    public String getSecid() {
        return secid;
    }

    /**
     * 设置 债券ID：xxxx.IB/xxxx.SZ
     * @param secid 债券ID：xxxx.IB/xxxx.SZ
     */
    public void setSecid(String secid) {
        this.secid = secid == null ? null : secid.trim();
    }

    /**
     * 取得 估值日
     * @return 估值日
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 估值日
     * @param cdate 估值日
     */
    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    /**
     * 取得 估值来源
     * @return 估值来源
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
     * 设置 估值来源
     * @param valueSource 估值来源
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource == null ? null : valueSource.trim();
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