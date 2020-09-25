/*
 * 文件名：XbrliUnitEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘溶容-Dosgi.requiredJavaVersion=1.8
 * 修改时间：2016年11月3日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * xbrli:unit节点Entity
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月3日
 * @see XbrliUnitEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class XbrliUnitEntity {

    /**
     * 属性
     */
    @XmlAttribute(name = "id")
    private String id;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:measure")
    private String xbrliMeasure;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:divide")
    private XbrliDivide xbrliDivide;

    /**
     * 获取 xbrliMeasure
     * 
     * @return xbrliMeasure.
     */
    public String getXbrliMeasure() {
        return xbrliMeasure;
    }

    /**
     * 设置 xbrliMeasure
     * 
     * @param xbrliMeasure xbrliMeasure
     */
    public void setXbrliMeasure(String xbrliMeasure) {
        this.xbrliMeasure = xbrliMeasure;
    }

    /**
     * 获取 id
     * 
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 id
     * 
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 xbrliDivide
     * 
     * @return xbrliDivide.
     */
    public XbrliDivide getXbrliDivide() {
        return xbrliDivide;
    }

    /**
     * 设置 xbrliDivide
     * 
     * @param xbrliDivide xbrliDivide
     */
    public void setXbrliDivide(XbrliDivide xbrliDivide) {
        this.xbrliDivide = xbrliDivide;
    }

}
