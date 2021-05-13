/*
 * 文件名：XmlParseUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tools.util.FileUtil;

/**
 * 解析xml为实体类
 *
 * @author 杨静敏
 * @version 1.0 2018年11月23日
 * @see XmlParseUtil
 * @since 1.0
 */
public class XmlParseUtil {
    
    private XmlParseUtil() {
        throw new IllegalStateException();
    }

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(XmlParseUtil.class);

    /**
     * 获取xml文件的文本内容
     *
     * @param pathname 文件路径
     * @return xml文件的文本内容
     * @see
     * @since 1.0
     */
    public static String getXmlString(String pathname) {
        String xmlString = null;
        try {
            xmlString = StringUtil.joinString(FileUtil.getFile(pathname, "utf-8"), "");
        }
        catch (IOException e) {
            logger.error(pathname + "文件不存在", e);
        }
        return xmlString;
    }

    /**
     * xml-->bean
     *
     * @param xml 报文字符串
     * @param c bean类型
     * @return T bean对象
     * @param <T> bean class
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
//            t = (T)unmarshaller.unmarshal(new StringReader(xml));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //------start---------2020-12-24 马数凯 添加
            //禁用DTDs (doctypes),几乎可以防御所有xml实体攻击
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); //首选
            //如果不能禁用DTDs,可以使用下两项，必须两项同时存在
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);        //防止外部实体POC
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);   //防止参数实体POC
            //------end---------
            dbf.setExpandEntityReferences(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(xml);
            t = (T)unmarshaller.unmarshal(document);
        }
        catch (Exception e) {
            logger.info(xml);
            logger.error("转换bean异常", e);
            return null;
        }
        return t;
    }

    /**
     * 解析xml文件为bean
     *
     * @param fileName 文件全路径
     * @return bean bean
     * @see
     * @since 1.0
     */
    public static <T> T readXmlToJavaBean(String fileName, Class<T> c) {
        String xmlContent = getXmlString(fileName);
        if (StringUtil.isBlank(xmlContent)) {
            return null;
        }
        return  converyToJavaBean(xmlContent,c);
    }

}
