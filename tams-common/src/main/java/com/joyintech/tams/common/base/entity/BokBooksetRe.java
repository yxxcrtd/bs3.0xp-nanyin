package com.joyintech.tams.common.base.entity;

public class BokBooksetRe extends BokBooksetReKey {
    /**
     * 子账套名称
     */
    private String booksetNameSon;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 核算方式
     */
    private String acctMode;

    /**
     * 核算主体代码
     */
    private String acctSubjectId;

    /**
     * 核算主体类别
     */
    private String acctSubjectType;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 取得 子账套名称
     * @return 子账套名称
     */
    public String getBooksetNameSon() {
        return booksetNameSon;
    }

    /**
     * 设置 子账套名称
     * @param booksetNameSon 子账套名称
     */
    public void setBooksetNameSon(String booksetNameSon) {
        this.booksetNameSon = booksetNameSon == null ? null : booksetNameSon.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置 通道代码
     * @param channelId 通道代码
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    /**
     * 取得 核算方式
     * @return 核算方式
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * 设置 核算方式
     * @param acctMode 核算方式
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode == null ? null : acctMode.trim();
    }

    /**
     * 取得 核算主体代码
     * @return 核算主体代码
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    /**
     * 设置 核算主体代码
     * @param acctSubjectId 核算主体代码
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId == null ? null : acctSubjectId.trim();
    }

    /**
     * 取得 核算主体类别
     * @return 核算主体类别
     */
    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    /**
     * 设置 核算主体类别
     * @param acctSubjectType 核算主体类别
     */
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType == null ? null : acctSubjectType.trim();
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     * @param standardCcy 本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy == null ? null : standardCcy.trim();
    }
}