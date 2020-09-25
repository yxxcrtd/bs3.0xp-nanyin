/*
 * 文件名：SocketEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月3日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * socket 通信用entity
 * 
 * @author lihuadong
 * @version 1.0 2017年2月3日
 * @see SocketEntity
 * @since 1.0
 */
public class SocketEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8698094360006283021L;

    /**
     * 发送消息
     */
    private String sendMsg;

    /**
     * 接收消息
     */
    private String returnMsg;

    /**
     * 异常信息
     */
    private String errMsg;

    /**
     * 接收方IP地址
     */
    private String ip;

    /**
     * 接收方端口号
     */
    private Integer port;

    /**
     * 通信编码方式
     */
    private String encode;

    /**
     * 通信超时时间
     */
    private Integer timeOut;

    /**
     * 获取 sendMsg
     * 
     * @return sendMsg.
     */
    public String getSendMsg() {
        return sendMsg;
    }

    /**
     * 设置 sendMsg
     * 
     * @param sendMsg sendMsg
     */
    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

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
     * 获取 ip
     * 
     * @return ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置 ip
     * 
     * @param ip ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取 port
     * 
     * @return port.
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置 port
     * 
     * @param port port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取 encode
     * 
     * @return encode.
     */
    public String getEncode() {
        return encode;
    }

    /**
     * 设置 encode
     * 
     * @param encode encode
     */
    public void setEncode(String encode) {
        this.encode = encode;
    }

    /**
     * 获取 timeOut
     * 
     * @return timeOut.
     */
    public Integer getTimeOut() {
        return timeOut;
    }

    /**
     * 设置 timeOut
     * 
     * @param timeOut timeOut
     */
    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
