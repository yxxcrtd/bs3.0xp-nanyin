package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class BokValSummaryKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 账套代码
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bookOrder;

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
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
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
     * 取得 账套代码
     * @return 账套代码
     */
    public BigDecimal getBookOrder() {
        return bookOrder;
    }

    /**
     * 设置 账套代码
     * @param bookOrder 账套代码
     */
    public void setBookOrder(BigDecimal bookOrder) {
        this.bookOrder = bookOrder;
    }
}