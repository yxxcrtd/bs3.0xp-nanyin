/*
 * 文件名：FileDisposeResultEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

/**
 * 文件处理结果
 * 
 * @author lihuadong
 * @version 1.0 2017年2月6日
 * @see FileDisposeResultEntity
 * @since 1.0
 */
public class FileDisposeResultEntity {

    /**
     * 处理状态
     */
    private Boolean status;

    /**
     * 结果消息
     */
    private String message;

    /**
     * 获取 status
     * @return status.
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取 message
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置 message
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
