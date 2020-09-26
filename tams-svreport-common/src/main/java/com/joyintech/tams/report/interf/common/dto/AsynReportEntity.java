/*
 * 文件名：AsynReportEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘蓉蓉
 * 修改时间：2016年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 异步文件返回报文Entity
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月7日
 * @see AsynReportEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Root")
public class AsynReportEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "MsgHeader")
    private AsynMsgHeaderEntity msgHeader;

    /**
     * 子节点
     */
    @XmlElement(name = "Document")
    private AsynDocumentEntity document;

    /**
     * 获取 msgHeader
     * 
     * @return msgHeader.
     */
    public AsynMsgHeaderEntity getMsgHeader() {
        return msgHeader;
    }

    /**
     * 设置 msgHeader
     * 
     * @param msgHeader msgHeader
     */
    public void setMsgHeader(AsynMsgHeaderEntity msgHeader) {
        this.msgHeader = msgHeader;
    }

    /**
     * 获取 document
     * 
     * @return document.
     */
    public AsynDocumentEntity getDocument() {
        return document;
    }

    /**
     * 设置 document
     * 
     * @param document document
     */
    public void setDocument(AsynDocumentEntity document) {
        this.document = document;
    }
}
