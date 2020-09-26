/*
 * 文件名：linkSchemaRefEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘溶容
 * 修改时间：2016年11月24日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 中债报文共通部分
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月24日
 * @see linkSchemaRefEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LinkSchemaRefEntity {

    /**
     * 属性
     */
    @XmlAttribute(name = "xlink:type")
    private String xlinkType;

    /**
     * 属性
     */
    @XmlAttribute(name = "xlink:href")
    private String xlinkHref;

    /**
     * 获取 xlinkType
     * 
     * @return xlinkType.
     */
    public String getXlinkType() {
        return xlinkType;
    }

    /**
     * 设置 xlinkType
     * 
     * @param xlinkType xlinkType
     */
    public void setXlinkType(String xlinkType) {
        this.xlinkType = xlinkType;
    }

    /**
     * 获取 xlinkHref
     * 
     * @return xlinkHref.
     */
    public String getXlinkHref() {
        return xlinkHref;
    }

    /**
     * 设置 xlinkHref
     * 
     * @param xlinkHref xlinkHref
     */
    public void setXlinkHref(String xlinkHref) {
        this.xlinkHref = xlinkHref;
    }
}
