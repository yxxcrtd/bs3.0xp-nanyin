/*
 * 文件名：AsynMsgHeaderEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘蓉蓉
 * 修改时间：2016年11月7日
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
 * @version 1.0 2016年11月7日
 * @see AsynMsgHeaderEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"bizInfo", "basicInfo", "xtnInfo"})
public class AsynMsgHeaderEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "BizInfo")
    private AsynBizInfoEntity bizInfo;

    /**
     * 子节点
     */
    @XmlElement(name = "BasicInfo")
    private AsynBasicInfoEntity basicInfo;

    /**
     * 子节点
     */
    @XmlElement(name = "XtnInfo", required = true)
    private String xtnInfo;

    /**
     * 获取 xtnInfo
     * 
     * @return xtnInfo.
     */
    public String getXtnInfo() {
        return xtnInfo;
    }

    /**
     * 设置 xtnInfo
     * 
     * @param xtnInfo xtnInfo
     */
    public void setXtnInfo(String xtnInfo) {
        this.xtnInfo = xtnInfo;
    }

    /**
     * 获取 bizInfo
     * 
     * @return bizInfo.
     */
    public AsynBizInfoEntity getBizInfo() {
        return bizInfo;
    }

    /**
     * 设置 bizInfo
     * 
     * @param bizInfo bizInfo
     */
    public void setBizInfo(AsynBizInfoEntity bizInfo) {
        this.bizInfo = bizInfo;
    }

    /**
     * 获取 basicInfo
     * 
     * @return basicInfo.
     */
    public AsynBasicInfoEntity getBasicInfo() {
        return basicInfo;
    }

    /**
     * 设置 basicInfo
     * 
     * @param basicInfo basicInfo
     */
    public void setBasicInfo(AsynBasicInfoEntity basicInfo) {
        this.basicInfo = basicInfo;
    }
}
