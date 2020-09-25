/*
 * 文件名：XbrliContextEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘溶容-Dosgi.requiredJavaVersion=1.8
 * 修改时间：2016年11月2日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * 中债报文共通部分
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月2日
 * @see XbrliContextEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"xbrliEntity", "xbrliPeriodEntity"})
public class XbrliContextEntity {

    /**
     * 属性
     */
    @XmlAttribute(name = "id")
    private String xbrliContextEntityId;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:entity")
    private XbrliEntity xbrliEntity;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:period")
    private XbrliPeriodEntity xbrliPeriodEntity;

    /**
     * 获取 id
     * 
     * @return id.
     */
    public String getXbrliContextEntityId() {
        return xbrliContextEntityId;
    }

    /**
     * 设置 id
     * 
     * @param id id
     */

    public void setXbrliContextEntityId(String id) {
        this.xbrliContextEntityId = id;
    }

    /**
     * 获取 xbrliEntity
     * 
     * @return xbrliEntity.
     */
    @XmlTransient
    public XbrliEntity getXbrliEntity() {
        return xbrliEntity;
    }

    /**
     * 设置 xbrliEntity
     * 
     * @param xbrliEntity xbrliEntity
     */

    public void setXbrliEntity(XbrliEntity xbrliEntity) {
        this.xbrliEntity = xbrliEntity;
    }

    /**
     * 获取 xbrliPeriodEntity
     * 
     * @return xbrliPeriodEntity.
     */
    @XmlTransient
    public XbrliPeriodEntity getXbrliPeriodEntity() {
        return xbrliPeriodEntity;
    }

    /**
     * 设置 xbrliPeriodEntity
     * 
     * @param xbrliPeriodEntity xbrliPeriodEntity
     */
    public void setXbrliPeriodEntity(XbrliPeriodEntity xbrliPeriodEntity) {
        this.xbrliPeriodEntity = xbrliPeriodEntity;
    }
}
