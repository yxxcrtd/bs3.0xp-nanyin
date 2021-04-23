package com.joyintech.tams.invest.batch.cfetsmanager.cfetscustomer.util;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.InterfCfetsCustomerTmp;

/**
 * xml文件解析，转为javabean集合
 * @author EX-ANJIAJUN001
 * @see XmlCustomerUtil
 * @since 1.0
 */
public class XmlCustomerUtil extends DefaultHandler{

	/**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(XmlCustomerUtil.class);
	/**
     * 根节点
     */
    private static final String NODE_ROOT = "IMIXML";
    /**
     * 子类节点
     */
    private static final String NODE_RECORD = "PtyDetlListRpt";
    /**
     * 节点 Instrmt
     */
    private static final String NODE_PARTYLISTGRP = "PartyListGrp";
    /**
     * 节点 MessagingControl
     */
    private static final String NODE_PTYSSUBGRP = "PtysSubGrp";
    /**
     * 节点属性 MarketIndicator
     */
    private static final String ATTR_ID = "ID";
    /**
     * 节点属性ApplErrorCode
     */
    private static final String ATTR_TYP = "Typ";
    
    private List<InterfCfetsCustomerTmp> customerInfoList;
    
    private InterfCfetsCustomerTmp customerInfo;
    
    /**
     * 开始解析xml文件
     */
    @Override
    public void startDocument() throws SAXException {
    	logger.info("XmlCustomerUtil ---   开始解析xml文件");
        super.startDocument();
    }

    /**
     * 解析xml文件结束
     */
    @Override
    public void endDocument() throws SAXException {
    	logger.info("XmlCustomerUtil ---   解析xml文件结束");
        super.endDocument();
    }
    /**
     * 转换规则：
     * 1、IMIXML为根节点  创建javabean对象集合
     * 2、PtyDetlListRpt为子节点  创建javabean对象
     * 3、PartyListGrp标签的id属性值为交易成员id
     * 4、PtysSubGrp标签的Typ属性值为124--ID属性为交易成员全称；Typ属性值为125--id属性为交易成员简称；
     *     Typ属性值为5--id属性为交易成员英文全称；Typ属性值为102--id属性为交易成员英文简称；Typ属性值为2--id属性为交易机构id
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
    	super.startElement(uri, localName, qName, attributes);

    	 if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
    		 customerInfoList = new ArrayList<InterfCfetsCustomerTmp>();
         }else if(StringUtil.equalsIgnoreCase(NODE_RECORD, qName)) {
        	 customerInfo = new InterfCfetsCustomerTmp();
         }else if(StringUtil.equalsIgnoreCase(NODE_PARTYLISTGRP, qName)) {
        	 customerInfo.setPartyId(attributes.getValue(ATTR_ID));
         }else if(StringUtil.equalsIgnoreCase(NODE_PTYSSUBGRP, qName)) {
        	 String typ = attributes.getValue(ATTR_TYP);
        	 switch(typ) {
        	 	case "124" : customerInfo.setPartyName(attributes.getValue(ATTR_ID)); break;
        	 	case "125" : customerInfo.setPartyAbbr(attributes.getValue(ATTR_ID)); break;
        	 	case "5" : customerInfo.setPartyNameEn(attributes.getValue(ATTR_ID)); break;
        	 	case "102" : customerInfo.setPartyAbbrEn(attributes.getValue(ATTR_ID)); break;
        	 	case "2" : customerInfo.setPartyCustomerId(attributes.getValue(ATTR_ID)); break;
        	 	default : break;
        	 }
         }
    }
    
    /**
     * 转换结束规则
     * 读取到/PtyDetlListRpt之后将转换的对象添加入集合
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
    	super.endElement(uri, localName, qName);

    	if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
        }
        else if (StringUtil.equalsIgnoreCase(NODE_RECORD, qName)) {
        	customerInfoList.add(customerInfo);
        }
    }
    
    /**
     * 
     * 返回转换之后的对象集合
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfCfetsCustomerTmp> getInfoList() {
    	return customerInfoList;
    }
    
}
