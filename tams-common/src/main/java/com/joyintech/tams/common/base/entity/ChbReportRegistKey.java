package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbReportRegistKey extends BaseEntity {
    /**
     * null
     */
    private String registSeqno;

    /**
     * 取得 null
     * @return null
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 null
     * @param registSeqno null
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }
}