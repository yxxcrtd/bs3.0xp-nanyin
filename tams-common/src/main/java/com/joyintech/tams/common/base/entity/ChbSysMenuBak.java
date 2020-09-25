package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysMenuBak extends BaseEntity {
    /**
     * null
     */
    private String menuId;

    /**
     * null
     */
    private String parentMenuId;

    /**
     * null
     */
    private String pageId;

    /**
     * null
     */
    private Long orderNo;

    /**
     * null
     */
    private String remark;

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
    public String getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 设置 null
     * @param parentMenuId null
     */
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId == null ? null : parentMenuId.trim();
    }

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
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 null
     * @param orderNo null
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
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