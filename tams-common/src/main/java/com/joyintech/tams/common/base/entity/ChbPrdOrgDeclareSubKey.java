package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbPrdOrgDeclareSubKey extends BaseEntity {
    /**
     * 登记序列
     */
    private String registSeqno;

    /**
     * 取得 登记序列
     * @return 登记序列
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 登记序列
     * @param registSeqno 登记序列
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }
}