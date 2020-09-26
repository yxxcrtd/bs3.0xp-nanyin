/*
 * 文件名：XbrliuUnitDenominator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年5月22日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * 节点 xbrli:unitDenominator
 * 
 * @author lihuadong
 * @version 1.0 2017年5月22日
 * @see XbrliuUnitDenominator
 * @since 1.0
 */
public class XbrliuUnitDenominator {

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:measure")
    private String xbrliMeasures;

    /**
     * 获取 xbrliMeasure
     * 
     * @return xbrliMeasure.
     */
    public String getXbrliMeasure() {
        return xbrliMeasures;
    }

    /**
     * 设置 xbrliMeasure
     * 
     * @param xbrliMeasure xbrliMeasure
     */
    public void setXbrliMeasure(String xbrliMeasure) {
        this.xbrliMeasures = xbrliMeasure;
    }

}
