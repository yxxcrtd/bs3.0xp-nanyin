/*
 * 文件名：XbrliDivide.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年5月22日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * xbrli:divide 节点
 * 
 * @author lihuadong
 * @version 1.0 2017年5月22日
 * @see XbrliDivide
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class XbrliDivide {

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:unitNumerator")
    private XbrliuUnitDenominator xbrliUnitNumerator;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:unitDenominator")
    private XbrliuUnitDenominator xbrliuUnitDenominator;

    /**
     * 获取 xbrliuUnitDenominator
     * 
     * @return xbrliuUnitDenominator.
     */
    public XbrliuUnitDenominator getXbrliuUnitDenominator() {
        return xbrliuUnitDenominator;
    }

    /**
     * 设置 xbrliuUnitDenominator
     * 
     * @param xbrliuUnitDenominator xbrliuUnitDenominator
     */
    public void setXbrliuUnitDenominator(XbrliuUnitDenominator xbrliuUnitDenominator) {
        this.xbrliuUnitDenominator = xbrliuUnitDenominator;
    }

    /**
     * 获取 xbrliUnitNumerator
     * 
     * @return xbrliUnitNumerator.
     */
    public XbrliuUnitDenominator getXbrliUnitNumerator() {
        return xbrliUnitNumerator;
    }

    /**
     * 设置 xbrliUnitNumerator
     * 
     * @param xbrliUnitNumerator xbrliUnitNumerator
     */
    public void setXbrliUnitNumerator(XbrliuUnitDenominator xbrliUnitNumerator) {
        this.xbrliUnitNumerator = xbrliUnitNumerator;
    }

}
