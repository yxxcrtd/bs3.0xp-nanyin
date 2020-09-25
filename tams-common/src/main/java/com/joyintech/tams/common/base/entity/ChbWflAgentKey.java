package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbWflAgentKey extends BaseEntity {
    /**
     * 用户代码
     */
    private String userId;

    /**
     * 有效开始日
     */
    private Date startDate;

    /**
     * 取得 用户代码
     * @return 用户代码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 用户代码
     * @param userId 用户代码
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 有效开始日
     * @return 有效开始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 有效开始日
     * @param startDate 有效开始日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}