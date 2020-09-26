/*
 * 文件名：XMLHandler.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：恒丰银行资管系统 修改人：renjian 修改时间：2017年2月27日 修改内容：新建 系统名称：恒丰银行资管系统
 */
package com.joyintech.tams.report.interf.message.socketclient;



import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class XMLHandler extends SendHeardUtil{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(com.joyintech.tams.report.interf.common.util.SocketClient.class);

    public  static final Element SERVICE = new Element(SendHeardUtil.SERVICE);//创建根节点School节点

    public static final Document DOCUMENT = new Document(SERVICE);//把根节点添加打文档中

    public  static final Element SYS_HEAD = new Element(SendHeardUtil.SYS_HEAD);

    public  static final Element APP_HEAD = new Element(SendHeardUtil.APP_HEAD);
    //创建BODY节点
    public  static final Element BODY = new Element(SendHeardUtil.BODY);


    public static Map<Element, String> sysHeadBody() {
        Map<Element, String> sysHeadBody = new HashMap<Element, String>();
        //服务Id
        sysHeadBody.put(SVCID, "");
        //服务场景Id
        sysHeadBody.put(SVCSCNID, "");
        //调用方系统Id
        sysHeadBody.put(CNSMSYSID, "");
        //渠道类型
        sysHeadBody.put(CHNLTYPE, "");
        //源发起系统Id
        sysHeadBody.put(SRCSYSID, "");
        //	调用方系统流水号
        sysHeadBody.put(CNSMSYSSEQNO, "");
        //	源发起系统流水号
        sysHeadBody.put(SRCSYSSEQNO, "");
        //MAC字段
        sysHeadBody.put(MAC, "");
        //	MAC机构号
        sysHeadBody.put(MACORGID, "");
        //	交易模式
        sysHeadBody.put(TRANMODE, "");
        //	交易日期
        sysHeadBody.put(TRANDATE, "");
        //	交易时间
        sysHeadBody.put(TRANTIME, "");
        //	终端号
        sysHeadBody.put(TMNLNO, "");
        //	源发起系统终端号
        sysHeadBody.put(SRCSYSYSNLNO, "");
        //	调用方系统服务器Id
        sysHeadBody.put(CNSMSYSSVRID, "");
        //	源发起系统服务器Id
        sysHeadBody.put(SRCSYSSVRID, "");
        return sysHeadBody;
    }

    public static Map<Element, String> appHeadBody() {
        Map<Element, String> appHeadBody = new HashMap<Element, String>();
      //柜员Id
        appHeadBody.put(TELLERID, "");
     //机构Id
        appHeadBody.put(BRANCHID, "");
        //柜员密码
        appHeadBody.put(TLRPSWD, "");
        //	柜员级别
        appHeadBody.put(TLRLVL, "");
        //柜员类别
        appHeadBody.put(TLRTYPE, "");
        //复核标志
        appHeadBody.put(CHKFLAG, "");
        //授权标志
        appHeadBody.put(AUTHFLAG, "");
        //授权柜员Id
        appHeadBody.put(AUTHTLRID, "");
        //授权柜员密码
        appHeadBody.put(AUTHTLRPSWD, "");
        //	授权机构Id
        appHeadBody.put(AUTHBRCHID, "");
        return appHeadBody;
    }

    /**
     * 生成XML
     */
    public static String buildXML() {
        String xml = null;
        XMLOutputter xmlout = new XMLOutputter(formatXML());  //生成xml文件
        try {
        	xml = xmlout.outputString(DOCUMENT);
        } catch (Exception e) {
            logger.error("生成xml文件失败！");
            logger.error(e.getMessage(),e);
        }
        return xml;
    }

    /**
     * 格式化生成的xml文件
     *
     * @return
     */
    public static Format formatXML() {
        Format format = Format.getCompactFormat();
        format.setEncoding("utf-8");
        format.setIndent(" ");
        return format;
    }

    /**
     * 通过JDOM解析XML字符串
     *
     * @author java4lyvee@gmail.com
     */
    public  static Map<String, Object> parseXML(String xml, String xxx) throws JDOMException, IOException, JDOMException {
        /** *用于存放节点的信息** */
        Map<String, Object> map = new HashMap<String, Object>();
        /** *创建一个新的字符串*** */
        StringReader xmlReader = new StringReader(xml);
        /** **创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入 */
        InputSource xmlSource = new InputSource(xmlReader);
        /** *创建一个SAXBuilder* */
        SAXBuilder builder = new SAXBuilder();
        /** *通过输入源SAX构造一个Document** */
        Document doc = builder.build(xmlSource);
        /** *获得根节点** */
        Element elt = doc.getRootElement();
        /** *获得BODY节点** */
        Element body = elt.getChild("BODY");
        /** *获得body节点下面的所有子节点*** */
        List<Element> child = body.getChildren();
        /** *遍历出body节点下面所有的子节点，节点名称和内容用put到map* */
        for (Element childEle : child) {
            map.put(childEle.getName(), childEle.getText());
        }
        return map;
    }
}
