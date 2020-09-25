/*
 * 文件名：XMLHandler.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：恒丰银行资管系统 修改人：renjian 修改时间：2017年2月27日 修改内容：新建 系统名称：恒丰银行资管系统
 */
package com.joyintech.tams.disclosure.interf.interf.message.socketclient;



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
    private static Logger logger = LoggerFactory.getLogger(com.joyintech.tams.disclosure.interf.interf.common.util.SocketClient.class);

    public  static final Element service = new Element(SendHeardUtil.service);//创建根节点School节点

    public static final Document document = new Document(service);//把根节点添加打文档中

    public  static final Element SYS_HEAD = new Element(SendHeardUtil.SYS_HEAD);

    public  static final Element APP_HEAD = new Element(SendHeardUtil.APP_HEAD);
    //创建BODY节点
    public  static final Element BODY = new Element(SendHeardUtil.BODY);


    public static Map<Element, String> Sys_Head_Body() {
        Map<Element, String> Sys_Head_Body = new HashMap<Element, String>();
        //服务Id
        Sys_Head_Body.put(SvcId, "");
        //服务场景Id
        Sys_Head_Body.put(SvcScnId, "");
        //调用方系统Id
        Sys_Head_Body.put(CnsmSysId, "");
        //渠道类型
        Sys_Head_Body.put(ChnlType, "");
        //源发起系统Id
        Sys_Head_Body.put(SrcSysId, "");
        //	调用方系统流水号
        Sys_Head_Body.put(CnsmSysSeqNo, "");
        //	源发起系统流水号
        Sys_Head_Body.put(SrcSysSeqNo, "");
        //MAC字段
        Sys_Head_Body.put(Mac, "");
        //	MAC机构号
        Sys_Head_Body.put(MacOrgId, "");
        //	交易模式
        Sys_Head_Body.put(TranMode, "");
        //	交易日期
        Sys_Head_Body.put(TranDate, "");
        //	交易时间
        Sys_Head_Body.put(TranTime, "");
        //	终端号
        Sys_Head_Body.put(TmnlNo, "");
        //	源发起系统终端号
        Sys_Head_Body.put(SrcSySysnlNo, "");
        //	调用方系统服务器Id
        Sys_Head_Body.put(CnsmSysSvrId, "");
        //	源发起系统服务器Id
        Sys_Head_Body.put(SrcSysSvrId, "");
        return Sys_Head_Body;
    }

    public static Map<Element, String> App_Head_Body() {
        Map<Element, String> App_Head_Body = new HashMap<Element, String>();
      //柜员Id
        App_Head_Body.put(TellerId, "");
     //机构Id
        App_Head_Body.put(BranchId, "");
        //柜员密码
        App_Head_Body.put(TlrPswd, "");
        //	柜员级别
        App_Head_Body.put(TlrLvl, "");
        //柜员类别
        App_Head_Body.put(TlrType, "");
        //复核标志
        App_Head_Body.put(ChkFlag, "");
        //授权标志
        App_Head_Body.put(AuthFlag, "");
        //授权柜员Id
        App_Head_Body.put(AuthTlrId, "");
        //授权柜员密码
        App_Head_Body.put(AuthTlrPswd, "");
        //	授权机构Id
        App_Head_Body.put(AuthBrchId, "");
        return App_Head_Body;
    }

    /**
     * 生成XML
     */
    public static String BuildXML() {
        String XML = null;
        XMLOutputter XMLOut = new XMLOutputter(FormatXML());  //生成xml文件
        try {
            XML = XMLOut.outputString(document);
        } catch (Exception e) {
            logger.error("生成xml文件失败！");
        }
        return XML;
    }

    /**
     * 格式化生成的xml文件
     *
     * @return
     */
    public static Format FormatXML() {
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
