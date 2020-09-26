/*
 * 文件名：AsynSysXcptnEntity.java
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
import javax.xml.bind.annotation.XmlType;

/**
 * 报文
 * 
 * @author 刘蓉蓉
 * @version 1.0 2016年11月7日
 * @see AsynSysXcptnEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"xcptnRtrCd", "fileId", "xcptnRtrTx"})
public class AsynSysXcptnEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "XcptnRtrCd")
    private CommonNodeEntity xcptnRtrCd;

    /**
     * 子节点
     */
    @XmlElement(name = "FileId")
    private CommonNodeEntity fileId;

    /**
     * 子节点
     */
    @XmlElement(name = "XcptnRtrTx")
    private CommonNodeEntity xcptnRtrTx;

    /**
     * 获取 xcptnRtrCd
     * 
     * @return xcptnRtrCd.
     */
    public CommonNodeEntity getXcptnRtrCd() {
        return xcptnRtrCd;
    }

    /**
     * 设置 xcptnRtrCd
     * 
     * @param xcptnRtrCd xcptnRtrCd
     */
    public void setXcptnRtrCd(CommonNodeEntity xcptnRtrCd) {
        this.xcptnRtrCd = xcptnRtrCd;
    }

    /**
     * 获取 fileId
     * 
     * @return fileId.
     */
    public CommonNodeEntity getFileId() {
        return fileId;
    }

    /**
     * 设置 fileId
     * 
     * @param fileId fileId
     */
    public void setFileId(CommonNodeEntity fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取 xcptnRtrTx
     * 
     * @return xcptnRtrTx.
     */
    public CommonNodeEntity getXcptnRtrTx() {
        return xcptnRtrTx;
    }

    /**
     * 设置 xcptnRtrTx
     * 
     * @param xcptnRtrTx xcptnRtrTx
     */
    public void setXcptnRtrTx(CommonNodeEntity xcptnRtrTx) {
        this.xcptnRtrTx = xcptnRtrTx;
    }
}
