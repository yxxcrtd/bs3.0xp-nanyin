/*
 * 文件名：HttpResult.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
package com.joyintech.service.riskrdsinf.common.dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lihuadong
 * @version 1.0 2017年2月28日
 * @see HttpResult
 * @since 1.0
 */
public class HttpResult {

    /**
     * 接收消息
     */
    private String returnMsg;

    /**
     * 异常信息
     */
    private String errMsg;

    /**
     * 异常信息
     */
    private String status;

    /**
     * 获取 returnMsg
     * 
     * @return returnMsg.
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    /**
     * 设置 returnMsg
     * 
     * @param returnMsg returnMsg
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * 获取 errMsg
     * 
     * @return errMsg.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 errMsg
     * 
     * @param errMsg errMsg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 获取 status
     * 
     * @return status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置 status
     * 
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
