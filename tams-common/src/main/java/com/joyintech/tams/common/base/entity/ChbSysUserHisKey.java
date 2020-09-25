package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysUserHisKey extends BaseEntity {
    /**
     * ����
     */
    private String userId;

    /**
     * ����
     */
    private Integer hisNo;

    /**
     * 取得 ����
     * @return ����
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 ����
     * @param userId ����
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 ����
     * @param hisNo ����
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }
}