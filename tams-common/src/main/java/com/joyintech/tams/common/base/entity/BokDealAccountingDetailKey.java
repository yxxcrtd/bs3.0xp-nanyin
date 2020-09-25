package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokDealAccountingDetailKey extends BaseEntity {
    /**
     * 业务编号
     */
    private String busiNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 业务表，指令表、交易表等
     */
    private String busiTable;

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置 业务编号
     * @param busiNo 业务编号
     */
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo == null ? null : busiNo.trim();
    }

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
     * 取得 业务表，指令表、交易表等
     * @return 业务表，指令表、交易表等
     */
    public String getBusiTable() {
        return busiTable;
    }

    /**
     * 设置 业务表，指令表、交易表等
     * @param busiTable 业务表，指令表、交易表等
     */
    public void setBusiTable(String busiTable) {
        this.busiTable = busiTable == null ? null : busiTable.trim();
    }
}