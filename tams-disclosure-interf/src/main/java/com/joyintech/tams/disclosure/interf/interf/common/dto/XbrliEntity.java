/*
 * 文件名：XbrliEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘溶容
 * 修改时间：2016年11月2日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 中债直连报文共通部分
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月2日
 * @see XbrliEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class XbrliEntity {

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:identifier")
    private XbrliIdentifierEntity entityXbrliIdentifier;

    /**
     * 获取 xbrliIdentifier
     * 
     * @return xbrliIdentifier.
     */
    public XbrliIdentifierEntity getEntityXbrliIdentifier() {
        return entityXbrliIdentifier;
    }

    /**
     * 设置 xbrliIdentifier
     * 
     * @param entityXbrliIdentifier xbrliIdentifier
     */

    public void setEntityXbrliIdentifier(XbrliIdentifierEntity entityXbrliIdentifier) {
        this.entityXbrliIdentifier = entityXbrliIdentifier;
    }
}
