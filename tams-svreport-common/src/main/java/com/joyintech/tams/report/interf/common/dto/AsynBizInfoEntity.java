/*
 * 文件名：AsynBizInfoEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘蓉蓉
 * 修改时间：2016年11月8日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 报文
 * 
 * @author 刘蓉蓉
 * @version 1.0 2016年11月8日
 * @see AsynBizInfoEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"sndCustomerId", "rcvCustomerId", "logicalDIId", "bizGroup", "msgType",
    "msgId", "origMsgId", "syncType", "procResult"})
public class AsynBizInfoEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "SndCustomerId")
    private CommonNodeEntity sndCustomerId;

    /**
     * 子节点
     */
    @XmlElement(name = "RcvCustomerId")
    private CommonNodeEntity rcvCustomerId;

    /**
     * 子节点
     */
    @XmlElement(name = "LogicalDIId")
    private CommonNodeEntity logicalDIId;

    /**
     * 子节点
     */
    @XmlElement(name = "BizGroup")
    private CommonNodeEntity bizGroup;

    /**
     * 子节点
     */
    @XmlElement(name = "MsgType")
    private CommonNodeEntity msgType;

    /**
     * 子节点
     */
    @XmlElement(name = "MsgId")
    private CommonNodeEntity msgId;

    /**
     * 子节点
     */
    @XmlElement(name = "OrigMsgId")
    private CommonNodeEntity origMsgId;

    /**
     * 子节点
     */
    @XmlElement(name = "SyncType")
    private CommonNodeEntity syncType;

    /**
     * 子节点
     */
    @XmlElement(name = "ProcResult")
    private CommonNodeEntity procResult;

    /**
     * 获取 sndCustomerId
     * 
     * @return sndCustomerId.
     */
    public CommonNodeEntity getSndCustomerId() {
        return sndCustomerId;
    }

    /**
     * 设置 sndCustomerId
     * 
     * @param sndCustomerId sndCustomerId
     */
    public void setSndCustomerId(CommonNodeEntity sndCustomerId) {
        this.sndCustomerId = sndCustomerId;
    }

    /**
     * 获取 rcvCustomerId
     * 
     * @return rcvCustomerId.
     */
    public CommonNodeEntity getRcvCustomerId() {
        return rcvCustomerId;
    }

    /**
     * 设置 rcvCustomerId
     * 
     * @param rcvCustomerId rcvCustomerId
     */
    public void setRcvCustomerId(CommonNodeEntity rcvCustomerId) {
        this.rcvCustomerId = rcvCustomerId;
    }

    /**
     * 获取 logicalDIId
     * 
     * @return logicalDIId.
     */
    public CommonNodeEntity getLogicalDIId() {
        return logicalDIId;
    }

    /**
     * 设置 logicalDIId
     * 
     * @param logicalDIId logicalDIId
     */
    public void setLogicalDIId(CommonNodeEntity logicalDIId) {
        this.logicalDIId = logicalDIId;
    }

    /**
     * 获取 bizGroup
     * 
     * @return bizGroup.
     */
    public CommonNodeEntity getBizGroup() {
        return bizGroup;
    }

    /**
     * 设置 bizGroup
     * 
     * @param bizGroup bizGroup
     */
    public void setBizGroup(CommonNodeEntity bizGroup) {
        this.bizGroup = bizGroup;
    }

    /**
     * 获取 msgType
     * 
     * @return msgType.
     */
    public CommonNodeEntity getMsgType() {
        return msgType;
    }

    /**
     * 设置 msgType
     * 
     * @param msgType msgType
     */
    public void setMsgType(CommonNodeEntity msgType) {
        this.msgType = msgType;
    }

    /**
     * 获取 msgId
     * 
     * @return msgId.
     */
    public CommonNodeEntity getMsgId() {
        return msgId;
    }

    /**
     * 设置 msgId
     * 
     * @param msgId msgId
     */
    public void setMsgId(CommonNodeEntity msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取 origMsgId
     * 
     * @return origMsgId.
     */
    public CommonNodeEntity getOrigMsgId() {
        return origMsgId;
    }

    /**
     * 设置 origMsgId
     * 
     * @param origMsgId origMsgId
     */
    public void setOrigMsgId(CommonNodeEntity origMsgId) {
        this.origMsgId = origMsgId;
    }

    /**
     * 获取 syncType
     * 
     * @return syncType.
     */
    public CommonNodeEntity getSyncType() {
        return syncType;
    }

    /**
     * 设置 syncType
     * 
     * @param syncType syncType
     */
    public void setSyncType(CommonNodeEntity syncType) {
        this.syncType = syncType;
    }

    /**
     * 获取 procResult
     * 
     * @return procResult.
     */
    public CommonNodeEntity getProcResult() {
        return procResult;
    }

    /**
     * 设置 procResult
     * 
     * @param procResult procResult
     */
    public void setProcResult(CommonNodeEntity procResult) {
        this.procResult = procResult;
    }

}
