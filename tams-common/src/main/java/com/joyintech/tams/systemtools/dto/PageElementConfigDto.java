/*
 * 文件名：PageElementConfigDto.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王波
 * 修改时间：2019/6/27 15:54
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.systemtools.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @author 王波
 * @version 1.0 2019年06月27日
 * @see
 * @since 1.0
 */
public class PageElementConfigDto extends BaseEntity {
    // 流水号
    private String seqNo;

    // 画面ID
    private String pageId;

    // 元素ID
    private String elementId;

    // 角色ID
    private String roleId;

    // 用户ID
    private String userId;

    // 是否显示
    private String isShow;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
}
