package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysPageDialog extends BaseEntity {
    /**
     * 画面代码
     */
    private String pageId;

    /**
     * URL
     */
    private String pageUrl;

    /**
     * 菜单代码
     */
    private String menuId;

    /**
     * 宽度
     */
    private String width;

    /**
     * 高度
     */
    private String height;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 取得 URL
     * @return URL
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * 设置 URL
     * @param pageUrl URL
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    /**
     * 取得 菜单代码
     * @return 菜单代码
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置 菜单代码
     * @param menuId 菜单代码
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 取得 宽度
     * @return 宽度
     */
    public String getWidth() {
        return width;
    }

    /**
     * 设置 宽度
     * @param width 宽度
     */
    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    /**
     * 取得 高度
     * @return 高度
     */
    public String getHeight() {
        return height;
    }

    /**
     * 设置 高度
     * @param height 高度
     */
    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}