package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SysActivereminderSet extends BaseEntity {
    /**
     * 提醒类型
     */
    private String remType;

    /**
     * 提醒天数
     */
    private Short days;

    /**
     * 处理链接
     */
    private String url;

    /**
     * 标题
     */
    private String lable;

    /**
     * 配置处理按钮Y是N否
     */
    private String dealf;

    /**
     * 配置关闭按钮Y是N否
     */
    private String closf;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 取得 提醒类型
     * @return 提醒类型
     */
    public String getRemType() {
        return remType;
    }

    /**
     * 设置 提醒类型
     * @param remType 提醒类型
     */
    public void setRemType(String remType) {
        this.remType = remType == null ? null : remType.trim();
    }

    /**
     * 取得 提醒天数
     * @return 提醒天数
     */
    public Short getDays() {
        return days;
    }

    /**
     * 设置 提醒天数
     * @param days 提醒天数
     */
    public void setDays(Short days) {
        this.days = days;
    }

    /**
     * 取得 处理链接
     * @return 处理链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 处理链接
     * @param url 处理链接
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 取得 标题
     * @return 标题
     */
    public String getLable() {
        return lable;
    }

    /**
     * 设置 标题
     * @param lable 标题
     */
    public void setLable(String lable) {
        this.lable = lable == null ? null : lable.trim();
    }

    /**
     * 取得 配置处理按钮Y是N否
     * @return 配置处理按钮Y是N否
     */
    public String getDealf() {
        return dealf;
    }

    /**
     * 设置 配置处理按钮Y是N否
     * @param dealf 配置处理按钮Y是N否
     */
    public void setDealf(String dealf) {
        this.dealf = dealf == null ? null : dealf.trim();
    }

    /**
     * 取得 配置关闭按钮Y是N否
     * @return 配置关闭按钮Y是N否
     */
    public String getClosf() {
        return closf;
    }

    /**
     * 设置 配置关闭按钮Y是N否
     * @param closf 配置关闭按钮Y是N否
     */
    public void setClosf(String closf) {
        this.closf = closf == null ? null : closf.trim();
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

    /**
     * 取得 菜单ID
     * @return 菜单ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置 菜单ID
     * @param menuId 菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}