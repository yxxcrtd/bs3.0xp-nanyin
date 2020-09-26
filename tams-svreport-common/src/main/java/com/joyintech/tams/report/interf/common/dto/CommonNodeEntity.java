/*
 * 文件名：CommonNodeEntity.java
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * 业务数据共通子节点
 * 
 * @author 刘溶容Dosgi.requiredJavaVersion=1.8
 * @version 1.0 2016年11月7日
 * @see CommonNodeEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"decimals", "unitRef", "contextRef", "value"})
public class CommonNodeEntity {

    /**
     * 属性
     */
    @XmlAttribute(name = "decimals")
    private String decimals;

    /**
     * 属性
     */
    @XmlAttribute(name = "contextRef")
    private String contextRef;

    /**
     * 属性
     */
    @XmlAttribute(name = "unitRef")
    private String unitRef;

    /**
     * 值
     */
    @XmlValue
    private String value;

    /**
     * 获取 contextRef
     * 
     * @return contextRef.
     */
    public String getContextRef() {
        return contextRef;
    }

    /**
     * 设置 contextRef
     * 
     * @param contextRef contextRef
     */
    public void setContextRef(String contextRef) {
        this.contextRef = contextRef;
    }

    /**
     * 获取 value
     * 
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     * 
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取 decimals
     * 
     * @return decimals.
     */
    public String getDecimals() {
        return decimals;
    }

    /**
     * 设置 decimals
     * 
     * @param decimals decimals
     */
    public void setDecimals(String decimals) {
        this.decimals = decimals;
    }

    /**
     * 获取 unitRef
     * 
     * @return unitRef.
     */
    public String getUnitRef() {
        return unitRef;
    }

    /**
     * 设置 unitRef
     * 
     * @param unitRef unitRef
     */
    public void setUnitRef(String unitRef) {
        this.unitRef = unitRef;
    }
}
