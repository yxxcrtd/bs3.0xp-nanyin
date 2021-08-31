/*
 * 文件名：ChbSendMsgInfo.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年09月27日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.component.svrreport.chb.msgsend.dto;

/**
 * 中债发送报文信息
 * @author 王波
 * @date 2020年09月27日
 * @see ChbSendMsgInfo
 * @since 1.0
 */
public class ChbSendMsgInfo {

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 报文类型
     */
    private String msgType;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
