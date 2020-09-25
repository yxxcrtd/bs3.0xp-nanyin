package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysMenu extends BaseEntity {
    /**
     * 菜单代码
     */
    private String menuId;

    /**
     * 父菜单代码
     */
    private String parentMenuId;

    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 排列顺序
     */
    private Long orderNo;

    /**
     * 备注
     */
    private String remark;

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
     * 取得 父菜单代码
     * @return 父菜单代码
     */
    public String getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 设置 父菜单代码
     * @param parentMenuId 父菜单代码
     */
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId == null ? null : parentMenuId.trim();
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

    /**
     * 取得 排列顺序
     * @return 排列顺序
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 排列顺序
     * @param orderNo 排列顺序
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
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