package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class BokStatFeePosiitonKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 分层代码
     */
    private String layeringId;

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
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 费用类型
     * @return 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
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
}