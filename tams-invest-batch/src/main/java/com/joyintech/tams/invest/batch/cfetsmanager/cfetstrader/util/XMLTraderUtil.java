package com.joyintech.tams.invest.batch.cfetsmanager.cfetstrader.util;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.InterfCfetsTraderTmp;
import com.joyintech.tams.invest.batch.cfetsmanager.cfetscustomerrela.util.XMLRelaUtil;

public class XMLTraderUtil extends DefaultHandler {

	/**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(XMLRelaUtil.class);
	/**
     * 根节点
     */
    private static final String NODE_ROOT = "IMIXML";
    /**
     * 子类节点
     */
    private static final String NODE_RECORD = "PtyDetlListRpt";
    /**
     * 节点 PartyListGrp
     */
    private static final String NODE_PARTYLISTGRP = "PartyListGrp";
    /**
     * 节点 PartyListGrp
     */
    private static final String NODE_PTYSSUBGRP = "PtysSubGrp";
    /**
     * 节点 PartyListGrp
     */
    private static final String NODE_CONTACTINFOGRP = "ContactInfoGrp";
    /**
     * 节点属性 ID
     */
    private static final String ATTR_ID = "ID";
    /**
     * 节点属性R
     */
    private static final String ATTR_TYP = "Typ";
    /**
     * 节点属性 ID
     */
    private static final String ATTR_CONTACTINFOID = "ContactInfoID";
    /**
     * 节点属性R
     */
    private static final String ATTR_CONTACTINFOIDTYPE = "ContactInfoIDType";
    
    private List<InterfCfetsTraderTmp> customerTraderList;
    
    private InterfCfetsTraderTmp customerTrader;
    /**
     * 开始解析xml文件
     */
    @Override
    public void startDocument() throws SAXException {
    	logger.info("XMLTraderUtil ---   开始解析文件");
        super.startDocument();
    }

    /**
     * 解析xml文件结束
     */
    @Override
    public void endDocument() throws SAXException {
    	logger.info("XMLTraderUtil ---   解析文件结束");
        super.endDocument();
    }
    
    /**
     * 转换规则：
     * 1、IMIXML标签创建对象集合
     * 2、PartyListGrp标签创建对象，id属性值为交易机构id
     * 3、PtysSubGrp标签中Typ属性值为2时id属性值为交易员id；Typ属性值为2时id属性值为交易员姓名；
     * 4、ContactInfoGrp标签中ContactInfoIDType属性值为8时，ContactInfoID属性值为传真信息；ContactInfoIDType属性值为6时，ContactInfoID属性值为座机电话信息；
     * 		ContactInfoIDType属性值为10时，ContactInfoID属性值为邮件信息；ContactInfoIDType属性值为11时，ContactInfoID属性值为MSN信息；
     * 		ContactInfoIDType属性值为0时，ContactInfoID属性值为地址信息；ContactInfoIDType属性值为5时，ContactInfoID属性值为邮编信息；
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
    	super.startElement(uri, localName, qName, attributes);

    	 if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
    		 customerTraderList = new ArrayList<InterfCfetsTraderTmp>();
         }else if(StringUtil.equalsIgnoreCase(NODE_PARTYLISTGRP, qName)) {
        	 customerTrader = new InterfCfetsTraderTmp();
        	 customerTrader.setPartyCustomerId(attributes.getValue(ATTR_ID));
         }else if(StringUtil.equalsIgnoreCase(NODE_PTYSSUBGRP, qName)) {
        	 if(StringUtil.equals(attributes.getValue(ATTR_TYP), "2")){
        		 customerTrader.setTraderId(attributes.getValue(ATTR_ID));
        	 }else if(StringUtil.equals(attributes.getValue(ATTR_TYP), "126")){
        		 customerTrader.setTraderName(attributes.getValue(ATTR_ID));
        	 }
         }else if(StringUtil.equalsIgnoreCase(NODE_CONTACTINFOGRP, qName)) {
        	 String typ = attributes.getValue(ATTR_CONTACTINFOIDTYPE);
        	 switch(typ) {
        	 	case "8" : customerTrader.setFaxNo(attributes.getValue(ATTR_CONTACTINFOID)); break; 
        	 	case "6" : customerTrader.setTelNo(attributes.getValue(ATTR_CONTACTINFOID)); break;
        	 	case "10" : customerTrader.setEmail(attributes.getValue(ATTR_CONTACTINFOID)); break;
        	 	case "0" : customerTrader.setAddress(attributes.getValue(ATTR_CONTACTINFOID)); break;
        	 	case "11" : customerTrader.setMsn(attributes.getValue(ATTR_CONTACTINFOID)); break;
        	 	case "5" : customerTrader.setPostCode(attributes.getValue(ATTR_CONTACTINFOID)); break;
        	 	default : break;
        	 }
         }
    }
    
    /**
     * 读取标签为/PartyListGrp时，将对象添加进集合
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
    	super.endElement(uri, localName, qName);

    	if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
        }
        else if (StringUtil.equalsIgnoreCase(NODE_PARTYLISTGRP, qName)) {
        	customerTraderList.add(customerTrader);
        }
    }
    
    /**
     * 返回转换结束的对象集合
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfCfetsTraderTmp> getTraderList() {
    	return customerTraderList;
    }
   
}
