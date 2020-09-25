package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InterfStdsecConRadioKey extends BaseEntity {
    /**
     * 债券ID：xxxx.IB/xxxx.SZ
     */
    private String secId;

    /**
     * 开始适用日
     */
    private Date vdate;

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
     * 取得 开始适用日
     * @return 开始适用日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始适用日
     * @param vdate 开始适用日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
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