/*
 * <b>文件名：</b>TwoMenuDto<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/6 15:34<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.dto;

/**
 * <b>方法：</b>TwoMenuDto<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/6 15:34<br>
 */
public class TwoMenuDto {
    private int todoNum;
    private String color;
    private String icon;
    private String munuName;
    private String menuId;
    private String auditType;

    public int getTodoNum() {
        return todoNum;
    }

    public void setTodoNum(int todoNum) {
        this.todoNum = todoNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMunuName() {
        return munuName;
    }

    public void setMunuName(String munuName) {
        this.munuName = munuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }
}
