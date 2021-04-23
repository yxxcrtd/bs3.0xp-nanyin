/*
 * 文件名：resultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.dto;

/**
 * 处理结果
 * 
 * @author caoguo
 * @version 1.0 2020年5月7日
 * @see ResultBaseDto
 * @since 1.0
 */
public class ResultBaseDto {

    /**
     * 处理状态 ，true正常，false异常
     */
    private boolean status;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    public ResultBaseDto() {
        
    }
    
    public ResultBaseDto(boolean status) {
        this.status = status;
    }
    
    /**
     * 获取 status
     * @return status.
     */
    public boolean isStatus() {
        return status;
    }

    
    /**
     * 设置 status
     * @param status status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * 获取 errCode
     * 
     * @return errCode.
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * 设置 errCode
     * 
     * @param errCode errCode
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
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

}
