/*
 * 文件名：EmailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.interf.iemail.dto;

import java.io.Serializable;


/**
 * 邮件发送结果DTO
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月12日
 * @see SendEmailDto
 * @since 1.0
 */
public class SendEmailDto implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -3157602485408277167L;

    /**
     * 邮件序号
     */
    private String serialNo;

    /**
     * 处理结果
     */
    private boolean isHandleOK;

    /**
     * 出错信息
     */
    private String errMsg;

    /**
     * 获取 邮件序号
     * @return 邮件序号.
     */
    public String getSerialNo() {
        return serialNo;
    }
    
    /**
     * 设置 邮件序号
     * @param serialNo 邮件序号
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * 获取 处理结果
     * @return 处理结果.
     */
    public boolean isHandleOK() {
        return isHandleOK;
    }

    /**
     * 设置 处理结果
     * @param isHandleOK 处理结果
     */
    public void setHandleOK(boolean isHandleOK) {
        this.isHandleOK = isHandleOK;
    }

    /**
     * 获取 出错信息
     * @return 出错信息.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 出错信息
     * @param errMsg 出错信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
