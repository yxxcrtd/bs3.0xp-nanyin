package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class WflAgentKey extends BaseEntity {
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 有效开始日
     */
    private Date startDate;

    /**
     * 取得 用户名称
     * @return 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名称
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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