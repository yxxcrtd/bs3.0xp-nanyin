package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbUserWindowKey extends BaseEntity {
    /**
     * 用户代码
     */
    private String userId;

    /**
     * 画面代码
     */
    private String pageId;

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
     * 取得 画面代码
     * @return 画面代码
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 画面代码
     * @param pageId 画面代码
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }
}