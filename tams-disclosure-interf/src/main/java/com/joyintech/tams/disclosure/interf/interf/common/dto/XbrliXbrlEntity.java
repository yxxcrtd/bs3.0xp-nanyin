/*
 * 文件名：XbrliXbrlEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘溶容
 * 修改时间：2016年11月2日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * XbrliXbrlEntity 中债报文共通部分
 * 
 * @author 刘溶容
 * @version 1.0 2016年11月2日
 * @see XbrliXbrlEntity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class XbrliXbrlEntity {

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:xsi")
    private String xmlnsXsi;

    /**
     * 属性
     */
    @XmlAttribute(name = "xsi:schemaLocation")
    private String xsiSchemaLocation;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:chinawealth_entry_point")
    private String xmlnsChinawealthEntryPoint;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:link")
    private String xmlnsLink;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:cas")
    private String xmlnsCas;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:num")
    private String xmlnsNum;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:nonnum")
    private String xmlnsNonnum;
    
    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:xbrldt")
    private String xmlnsXbrldt;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:ifrs-full")
    private String xmlnsIfrsFull;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:rol_chinawealth_2016-04-30")
    private String rolChinawealth;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:wemax")
    private String xmlnsWemax;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:xsi")
    private String xmlnsXsi1;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:xbrldi")
    private String xmlnsXbrldi;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:xbrli")
    private String xmlnsXbrli;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:wei")
    private String xmlnsWei;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:iso4217")
    private String xmlnsIso4217;

    /**
     * 属性
     */
    @XmlAttribute(name = "xmlns:xlink")
    private String xmlnsXlink;

    /**
     * 子节点
     */
    @XmlElement(name = "link:schemaRef")
    private LinkSchemaRefEntity linkSchemaRef;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:context")
    private List<XbrliContextEntity> xbrliContextEntities;

    /**
     * 子节点
     */
    @XmlElement(name = "xbrli:unit")
    private List<XbrliUnitEntity> xbrliUnitEntities;

    /**
     * 获取 xmlnsXsi
     * 
     * @return xmlnsXsi.
     */
    @XmlTransient
    public String getXmlnsXsi() {
        return xmlnsXsi;
    }

    /**
     * 设置 xmlnsXsi
     * 
     * @param xmlnsXsi xmlnsXsi
     */

    public void setXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
    }

    /**
     * 获取 xsiSchemaLocation
     * 
     * @return xsiSchemaLocation.
     */
    @XmlTransient
    public String getXsiSchemaLocation() {
        return xsiSchemaLocation;
    }

    /**
     * 设置 xsiSchemaLocation
     * 
     * @param xsiSchemaLocation xsiSchemaLocation
     */

    public void setXsiSchemaLocation(String xsiSchemaLocation) {
        this.xsiSchemaLocation = xsiSchemaLocation;
    }

    /**
     * 获取 xmlnsChinawealthEntryPoint
     * 
     * @return xmlnsChinawealthEntryPoint.
     */
    @XmlTransient
    public String getXmlnsChinawealthEntryPoint() {
        return xmlnsChinawealthEntryPoint;
    }

    /**
     * 设置 xmlnsChinawealthEntryPoint
     * 
     * @param xmlnsChinawealthEntryPoint xmlnsChinawealthEntryPoint
     */

    public void setXmlnsChinawealthEntryPoint(String xmlnsChinawealthEntryPoint) {
        this.xmlnsChinawealthEntryPoint = xmlnsChinawealthEntryPoint;
    }

    /**
     * 获取 xmlnsLink
     * 
     * @return xmlnsLink.
     */
    @XmlTransient
    public String getXmlnsLink() {
        return xmlnsLink;
    }

    /**
     * 设置 xmlnsLink
     * 
     * @param xmlnsLink xmlnsLink
     */

    public void setXmlnsLink(String xmlnsLink) {
        this.xmlnsLink = xmlnsLink;
    }

    /**
     * 获取 xmlnsCas
     * 
     * @return xmlnsCas.
     */
    @XmlTransient
    public String getXmlnsCas() {
        return xmlnsCas;
    }

    /**
     * 设置 xmlnsCas
     * 
     * @param xmlnsCas xmlnsCas
     */

    public void setXmlnsCas(String xmlnsCas) {
        this.xmlnsCas = xmlnsCas;
    }

    /**
     * 获取 xmlnsNum
     * 
     * @return xmlnsNum.
     */
    @XmlTransient
    public String getXmlnsNum() {
        return xmlnsNum;
    }

    /**
     * 设置 xmlnsNum
     * 
     * @param xmlnsNum xmlnsNum
     */

    public void setXmlnsNum(String xmlnsNum) {
        this.xmlnsNum = xmlnsNum;
    }

    /**
     * 获取 xmlnsNonnum
     * 
     * @return xmlnsNonnum.
     */
    @XmlTransient
    public String getXmlnsNonnum() {
        return xmlnsNonnum;
    }

    /**
     * 设置 xmlnsNonnum
     * 
     * @param xmlnsNonnum xmlnsNonnum
     */

    public void setXmlnsNonnum(String xmlnsNonnum) {
        this.xmlnsNonnum = xmlnsNonnum;
    }

    /**
     * 获取 xmlnsIfrsFull
     * 
     * @return xmlnsIfrsFull.
     */
    @XmlTransient
    public String getXmlnsIfrsFull() {
        return xmlnsIfrsFull;
    }

    /**
     * 设置 xmlnsIfrsFull
     * 
     * @param xmlnsIfrsFull xmlnsIfrsFull
     */

    public void setXmlnsIfrsFull(String xmlnsIfrsFull) {
        this.xmlnsIfrsFull = xmlnsIfrsFull;
    }

    /**
     * 获取 xmlnsXbrldt
     * 
     * @return xmlnsXbrldt.
     */
    @XmlTransient
    public String getXmlnsXbrldt() {
        return xmlnsXbrldt;
    }

    /**
     * 设置 xmlnsXbrldt
     * 
     * @param xmlnsXbrldt xmlnsXbrldt
     */

    public void setXmlnsXbrldt(String xmlnsXbrldt) {
        this.xmlnsXbrldt = xmlnsXbrldt;
    }

    /**
     * 获取 rolChinawealth
     * 
     * @return rolChinawealth.
     */
    @XmlTransient
    public String getRolChinawealth() {
        return rolChinawealth;
    }

    /**
     * 设置 rolChinawealth
     * 
     * @param rolChinawealth rolChinawealth
     */

    public void setRolChinawealth(String rolChinawealth) {
        this.rolChinawealth = rolChinawealth;
    }

    /**
     * 获取 xmlnsWemax
     * 
     * @return xmlnsWemax.
     */
    @XmlTransient
    public String getXmlnsWemax() {
        return xmlnsWemax;
    }

    /**
     * 设置 xmlnsWemax
     * 
     * @param xmlnsWemax xmlnsWemax
     */

    public void setXmlnsWemax(String xmlnsWemax) {
        this.xmlnsWemax = xmlnsWemax;
    }

    /**
     * 获取 xmlnsXbrldi
     * 
     * @return xmlnsXbrldi.
     */
    @XmlTransient
    public String getXmlnsXbrldi() {
        return xmlnsXbrldi;
    }

    /**
     * 设置 xmlnsXbrldi
     * 
     * @param xmlnsXbrldi xmlnsXbrldi
     */

    public void setXmlnsXbrldi(String xmlnsXbrldi) {
        this.xmlnsXbrldi = xmlnsXbrldi;
    }

    /**
     * 获取 xmlnsXbrli
     * 
     * @return xmlnsXbrli.
     */
    @XmlTransient
    public String getXmlnsXbrli() {
        return xmlnsXbrli;
    }

    /**
     * 设置 xmlnsXbrli
     * 
     * @param xmlnsXbrli xmlnsXbrli
     */

    public void setXmlnsXbrli(String xmlnsXbrli) {
        this.xmlnsXbrli = xmlnsXbrli;
    }

    /**
     * 获取 xmlnsWei
     * 
     * @return xmlnsWei.
     */
    @XmlTransient
    public String getXmlnsWei() {
        return xmlnsWei;
    }

    /**
     * 设置 xmlnsWei
     * 
     * @param xmlnsWei xmlnsWei
     */

    public void setXmlnsWei(String xmlnsWei) {
        this.xmlnsWei = xmlnsWei;
    }

    /**
     * 获取 xmlnsIso4217
     * 
     * @return xmlnsIso4217.
     */
    @XmlTransient
    public String getXmlnsIso4217() {
        return xmlnsIso4217;
    }

    /**
     * 设置 xmlnsIso4217
     * 
     * @param xmlnsIso4217 xmlnsIso4217
     */

    public void setXmlnsIso4217(String xmlnsIso4217) {
        this.xmlnsIso4217 = xmlnsIso4217;
    }

    /**
     * 获取 xmlnsXlink
     * 
     * @return xmlnsXlink.
     */
    @XmlTransient
    public String getXmlnsXlink() {
        return xmlnsXlink;
    }

    /**
     * 设置 xmlnsXlink
     * 
     * @param xmlnsXlink xmlnsXlink
     */

    public void setXmlnsXlink(String xmlnsXlink) {
        this.xmlnsXlink = xmlnsXlink;
    }

    /**
     * 获取 linkSchemaRef
     * 
     * @return linkSchemaRef.
     */
    @XmlTransient
    public LinkSchemaRefEntity getLinkSchemaRef() {
        return linkSchemaRef;
    }

    /**
     * 设置 linkSchemaRef
     * 
     * @param linkSchemaRef linkSchemaRef
     */

    public void setLinkSchemaRef(LinkSchemaRefEntity linkSchemaRef) {
        this.linkSchemaRef = linkSchemaRef;
    }

    /**
     * 获取 xbrliContextEntity
     * 
     * @return xbrliContextEntity.
     */
    public List<XbrliContextEntity> getXbrliContextEntities() {
        if (xbrliContextEntities == null) {
            xbrliContextEntities = new ArrayList<XbrliContextEntity>();
        }
        return xbrliContextEntities;
    }

    /**
     * 设置 xbrliContextEntity
     * 
     * @param xbrliContextEntity xbrliContextEntity
     */

    public void setXbrliContextEntity(List<XbrliContextEntity> xbrliContextEntity) {
        this.xbrliContextEntities = xbrliContextEntity;
    }

    /**
     * 获取 xbrliUnitEntities
     * 
     * @return xbrliUnitEntities.
     */
    public List<XbrliUnitEntity> getXbrliUnitEntities() {
        return xbrliUnitEntities;
    }

    /**
     * 设置 xbrliUnitEntities
     * 
     * @param xbrliUnitEntities xbrliUnitEntities
     */
    public void setXbrliUnitEntities(List<XbrliUnitEntity> xbrliUnitEntities) {
        this.xbrliUnitEntities = xbrliUnitEntities;
    }

    /**
     * 获取 xmlnsXsi1
     * 
     * @return xmlnsXsi1.
     */
    public String getXmlnsXsi1() {
        return xmlnsXsi1;
    }

    /**
     * 设置 xmlnsXsi1
     * 
     * @param xmlnsXsi1 xmlnsXsi1
     */
    public void setXmlnsXsi1(String xmlnsXsi1) {
        this.xmlnsXsi1 = xmlnsXsi1;
    }

}
