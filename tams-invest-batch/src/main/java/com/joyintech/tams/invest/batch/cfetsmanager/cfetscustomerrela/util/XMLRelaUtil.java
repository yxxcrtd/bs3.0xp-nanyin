package com.joyintech.tams.invest.batch.cfetsmanager.cfetscustomerrela.util;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.InterfCfetsPartyRelaTmp;

/**
 * xml解析
 * @author EX-ANJIAJUN001
 * @see XMLRelaUtil
 * @since 1.0
 */
public class XMLRelaUtil extends DefaultHandler {

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
     * 节点属性 ID
     */
    private static final String ATTR_ID = "ID";
    /**
     * 节点属性R
     */
    private static final String ATTR_R = "R";
    
    private List<InterfCfetsPartyRelaTmp> customerRelaList;
    
    private InterfCfetsPartyRelaTmp customerRela;
    
    /**
     * 开始解析xml文件
     */
    @Override
    public void startDocument() throws SAXException {
    	logger.info("XMLRelaUtil ---   开始解析文件");
        super.startDocument();
    }

    /**
     * 解析xml文件结束
     */
    @Override
    public void endDocument() throws SAXException {
    	logger.info("XMLRelaUtil ---   解析xml文件结束");
        super.endDocument();
    }
    
    /**
     * 解析转换规则
     * 1、IMIXML标签创建对象集合
     * 2、PtyDetlListRpt标签创建对象，生成主键id
     * 3、PartyListGrp标签，R为1，id属性值为主交易机构id否则为从交易机构id
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
    	super.startElement(uri, localName, qName, attributes);

    	 if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
    		 customerRelaList = new ArrayList<InterfCfetsPartyRelaTmp>();
         }else if(StringUtil.equalsIgnoreCase(NODE_RECORD, qName)) {
        	 customerRela = new InterfCfetsPartyRelaTmp();
        	 //生成随机ID
        	 customerRela.setRelaId(IDGenerator.generate());
         }else if(StringUtil.equalsIgnoreCase(NODE_PARTYLISTGRP, qName)) {
        	 if(StringUtil.equals(attributes.getValue(ATTR_R), "1")) {
        		 customerRela.setMpartyCustomerId(attributes.getValue(ATTR_ID));
        	 }else {
        		 customerRela.setSpartyCustomerId(attributes.getValue(ATTR_ID));
        	 }
         }
    }
    /**
     * 解析读取到/PtyDetlListRpt标签时，将对象添加如集合
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
    	super.endElement(uri, localName, qName);

    	if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
        }
        else if (StringUtil.equalsIgnoreCase(NODE_RECORD, qName)) {
        	customerRelaList.add(customerRela);
        }
    }
    
    /**
     * 返回转换完的集合
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfCfetsPartyRelaTmp> getRelaList() {
    	return customerRelaList;
    }
    
}
