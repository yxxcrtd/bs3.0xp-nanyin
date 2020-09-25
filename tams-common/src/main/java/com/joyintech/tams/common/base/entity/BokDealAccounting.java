package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokDealAccounting extends BokDealAccountingKey {
    /**
     * 录入日
     */
    private Date booksetDate;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 实际发生日
     */
    private Date happenDate;

    /**
     * 是否已入账
     */
    private String isAccounted;

    /**
     * 入账凭证编号
     */
    private String bookId;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 投资目的获取方式
     */
    private String invAimMethod;

    /**
     * 取得 录入日
     * @return 录入日
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 录入日
     * @param booksetDate 录入日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 入账日
     * @return 入账日
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日
     * @param bookDate 入账日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 实际发生日
     * @return 实际发生日
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 实际发生日
     * @param happenDate 实际发生日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 是否已入账
     * @return 是否已入账
     */
    public String getIsAccounted() {
        return isAccounted;
    }

    /**
     * 设置 是否已入账
     * @param isAccounted 是否已入账
     */
    public void setIsAccounted(String isAccounted) {
        this.isAccounted = isAccounted == null ? null : isAccounted.trim();
    }

    /**
     * 取得 入账凭证编号
     * @return 入账凭证编号
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置 入账凭证编号
     * @param bookId 入账凭证编号
     */
    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 投资目的获取方式
     * @return 投资目的获取方式
     */
    public String getInvAimMethod() {
        return invAimMethod;
    }

    /**
     * 设置 投资目的获取方式
     * @param invAimMethod 投资目的获取方式
     */
    public void setInvAimMethod(String invAimMethod) {
        this.invAimMethod = invAimMethod == null ? null : invAimMethod.trim();
    }
}