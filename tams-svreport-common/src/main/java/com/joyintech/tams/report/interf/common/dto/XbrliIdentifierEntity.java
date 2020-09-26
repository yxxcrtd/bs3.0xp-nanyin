/*
 * 文件名：XbrliIdentifierEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘蓉蓉
 * 修改时间：2016年11月2日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * 中债直连报文共通部分
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月2日
 * @see XbrliIdentifierEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class XbrliIdentifierEntity {

    /**
     * 值
     */
    @XmlValue
    private String xbrliIdentifier;

    /**
     * schema 属性
     */
    @XmlAttribute(name = "scheme")
    private String scheme;

    /**
     * 获取 entityXbrliIdentifier
     * 
     * @return entityXbrliIdentifier.
     */
    public String getXbrliIdentifier() {
        return xbrliIdentifier;
    }

    /**
     * 设置 entityXbrliIdentifier
     * 
     * @param entityXbrliIdentifier entityXbrliIdentifier
     */
    public void setXbrliIdentifier(String entityXbrliIdentifier) {
        this.xbrliIdentifier = entityXbrliIdentifier;
    }

    /**
     * 获取 scheme
     * 
     * @return scheme.
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * 设置 scheme
     * 
     * @param scheme scheme
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
