/*
 * 文件名：XbrliIdentifierEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：鍒樻憾瀹?-Dosgi.requiredJavaVersion=1.8
 * 修改时间：2016年11月2日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 中债报文共通部分
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月2日
 * @see XbrliPeriodEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"xbrliStartDate", "xbrliEndDate", "xbrliInstant"})
public class XbrliPeriodEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:startDate")
    private String xbrliStartDate;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:endDate")
    private String xbrliEndDate;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:instant")
    private String xbrliInstant;

    /**
     * 获取 xbrliStartDate
     * 
     * @return xbrliStartDate.
     */
    public String getXbrliStartDate() {
        return xbrliStartDate;
    }

    /**
     * 设置 xbrliStartDate
     * 
     * @param xbrliStartDate xbrliStartDate
     */
    public void setXbrliStartDate(String xbrliStartDate) {
        this.xbrliStartDate = xbrliStartDate;
    }

    /**
     * 获取 xbrliEndDate
     * 
     * @return xbrliEndDate.
     */
    public String getXbrliEndDate() {
        return xbrliEndDate;
    }

    /**
     * 设置 xbrliEndDate
     * 
     * @param xbrliEndDate xbrliEndDate
     */
    public void setXbrliEndDate(String xbrliEndDate) {
        this.xbrliEndDate = xbrliEndDate;
    }

    /**
     * 获取 xbrliInstant
     * 
     * @return xbrliInstant.
     */
    public String getXbrliInstant() {
        return xbrliInstant;
    }

    /**
     * 设置 xbrliInstant
     * 
     * @param xbrliInstant xbrliInstant
     */
    public void setXbrliInstant(String xbrliInstant) {
        this.xbrliInstant = xbrliInstant;
    }
}
