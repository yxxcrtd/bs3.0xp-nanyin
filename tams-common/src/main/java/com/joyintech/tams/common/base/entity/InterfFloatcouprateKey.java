package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InterfFloatcouprateKey extends BaseEntity {
    /**
     * 债券ID：xxxx.IB/xxxx.SZ
     */
    private String secId;

    /**
     * 变动日期
     */
    private Date changeDt;

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
     * 取得 变动日期
     * @return 变动日期
     */
    public Date getChangeDt() {
        return changeDt;
    }

    /**
     * 设置 变动日期
     * @param changeDt 变动日期
     */
    public void setChangeDt(Date changeDt) {
        this.changeDt = changeDt;
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