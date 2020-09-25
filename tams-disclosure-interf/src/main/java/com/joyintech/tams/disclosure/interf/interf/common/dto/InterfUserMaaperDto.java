/*
 * 文件名：InterfUserMaaperDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年4月12日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

/**
 * 接口用户映射dto
 * 
 * @author lihuadong
 * @version 1.0 2017年4月12日
 * @see InterfUserMaaperDto
 * @since 1.0
 */
public class InterfUserMaaperDto {

    /**
     * 资管用户
     */
    private String userId;

    /**
     * xfund 用户
     */
    private String xfundUserId;

    /**
     * 本币用户
     */
    private String bbUserId;

    /**
     * 获取 userId
     * @return userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 userId
     * @param userId userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取 xfundUserId
     * @return xfundUserId.
     */
    public String getXfundUserId() {
        return xfundUserId;
    }

    /**
     * 设置 xfundUserId
     * @param xfundUserId xfundUserId
     */
    public void setXfundUserId(String xfundUserId) {
        this.xfundUserId = xfundUserId;
    }

    /**
     * 获取 bbUserId
     * @return bbUserId.
     */
    public String getBbUserId() {
        return bbUserId;
    }

    /**
     * 设置 bbUserId
     * @param bbUserId bbUserId
     */
    public void setBbUserId(String bbUserId) {
        this.bbUserId = bbUserId;
    }
}
