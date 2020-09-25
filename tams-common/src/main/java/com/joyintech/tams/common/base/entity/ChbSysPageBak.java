package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysPageBak extends BaseEntity {
    /**
     * null
     */
    private String pageId;

    /**
     * null
     */
    private String pageUrl;

    /**
     * null
     */
    private String menuId;

    /**
     * null
     */
    private String remark;

    /**
     * 取得 null
     * @return null
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 null
     * @param pageId null
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * 设置 null
     * @param pageUrl null
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置 null
     * @param menuId null
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 null
     * @param remark null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}