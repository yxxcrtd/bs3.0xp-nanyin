/*
 * <b>文件名：</b>OneMenuDto<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/6 15:33<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.dto;

import java.util.List;

/**
 * <b>方法：</b>OneMenuDto<br>
 * <b>描述：</b>一级菜单Dto<br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/6 15:33<br>
 */
public class OneMenuDto {
    private String menuId;
    private String menuName;
    private String businessType;
    private List<TwoMenuDto> leaf;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public List<TwoMenuDto> getLeaf() {
        return leaf;
    }

    public void setLeaf(List<TwoMenuDto> leaf) {
        this.leaf = leaf;
    }
}
