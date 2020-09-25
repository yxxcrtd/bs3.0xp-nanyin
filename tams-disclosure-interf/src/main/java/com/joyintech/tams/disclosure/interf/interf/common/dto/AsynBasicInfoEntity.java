/*
 * 文件名：AsynBasicInfoEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘蓉蓉
 * 修改时间：2016年11月8日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 报文
 * 
 * @author 刘蓉蓉
 * @version 1.0 2016年11月8日
 * @see AsynBasicInfoEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"vrsnId", "senderSignature", "msgSndDateTime", "msgDirection"})
public class AsynBasicInfoEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "VrsnId")
    private CommonNodeEntity vrsnId;

    /**
     * 子节点
     */
    @XmlElement(name = "SenderSignature")
    private CommonNodeEntity senderSignature;

    /**
     * 子节点
     */
    @XmlElement(name = "MsgSndDateTime")
    private CommonNodeEntity msgSndDateTime;

    /**
     * 子节点
     */
    @XmlElement(name = "MsgDirection")
    private CommonNodeEntity msgDirection;

    /**
     * 获取 vrsnId
     * 
     * @return vrsnId.
     */
    public CommonNodeEntity getVrsnId() {
        return vrsnId;
    }

    /**
     * 设置 vrsnId
     * 
     * @param vrsnId vrsnId
     */
    public void setVrsnId(CommonNodeEntity vrsnId) {
        this.vrsnId = vrsnId;
    }

    /**
     * 获取 senderSignature
     * 
     * @return senderSignature.
     */
    public CommonNodeEntity getSenderSignature() {
        return senderSignature;
    }

    /**
     * 设置 senderSignature
     * 
     * @param senderSignature senderSignature
     */
    public void setSenderSignature(CommonNodeEntity senderSignature) {
        this.senderSignature = senderSignature;
    }

    /**
     * 获取 msgSndDateTime
     * 
     * @return msgSndDateTime.
     */
    public CommonNodeEntity getMsgSndDateTime() {
        return msgSndDateTime;
    }

    /**
     * 设置 msgSndDateTime
     * 
     * @param msgSndDateTime msgSndDateTime
     */
    public void setMsgSndDateTime(CommonNodeEntity msgSndDateTime) {
        this.msgSndDateTime = msgSndDateTime;
    }

    /**
     * 获取 msgDirection
     * 
     * @return msgDirection.
     */
    public CommonNodeEntity getMsgDirection() {
        return msgDirection;
    }

    /**
     * 设置 msgDirection
     * 
     * @param msgDirection msgDirection
     */
    public void setMsgDirection(CommonNodeEntity msgDirection) {
        this.msgDirection = msgDirection;
    }
}
