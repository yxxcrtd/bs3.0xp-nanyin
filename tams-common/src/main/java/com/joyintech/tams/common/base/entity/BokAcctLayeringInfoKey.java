package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokAcctLayeringInfoKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 分层代码
     */
    private String layeringId;

    /**
     * 业务明细类型
     */
    private String busiDetailType;

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 分层代码
     * @return 分层代码
     */
    public String getLayeringId() {
        return layeringId;
    }

    /**
     * 设置 分层代码
     * @param layeringId 分层代码
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId == null ? null : layeringId.trim();
    }

    /**
     * 取得 业务明细类型
     * @return 业务明细类型
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    /**
     * 设置 业务明细类型
     * @param busiDetailType 业务明细类型
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType == null ? null : busiDetailType.trim();
    }
}