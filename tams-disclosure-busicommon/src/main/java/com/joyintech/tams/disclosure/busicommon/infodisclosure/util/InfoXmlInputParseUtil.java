package com.joyintech.tams.disclosure.busicommon.infodisclosure.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.joyintech.framework.util.StringUtil;


public final class InfoXmlInputParseUtil {
	/**
    *
    */
   private static final String NODE_FIRST = "inputControls";

   /**
    *
    */
   private static final String NODE_ROW = "inputControl";
   private static Logger logger = LoggerFactory.getLogger(InfoXmlInputParseUtil.class);
   /**
    * 解析XML文件，以MAP的形式返回解析后的结果
    * <p>
    * 解析XML文件中的每个节点，将ROW节点作为一个MAP对象
    * </p>
    *
    * @param fileName XML文件名（绝对路径）
    * @return 解析结果
    * @throws IOException IOException
    * @throws SAXException SAXException
    * @throws ParserConfigurationException ParserConfigurationException
    */
   public static List<Map<String, String>> parse(String fileName)
       throws ParserConfigurationException, SAXException, IOException {
       return parse(new File(fileName));
   }

   /**
    * 解析XML文件，以MAP的形式返回解析后的结果
    * <p>
    * 解析XML文件中的每个节点，将ROW节点作为一个MAP对象
    * </p>
    *
    * @param file XML文件
    * @return 解析结果
    * @throws SAXException SAXException
    * @throws ParserConfigurationException ParserConfigurationException
    * @throws IOException IOException
    */
   public static List<Map<String, String>> parse(File file)
       throws ParserConfigurationException, SAXException{
       if (isXML(file.getName())) {
           // 代码安全扫描对应start
           FileInputStream fis = null;
           List<Map<String, String>> list = null;
           try {
               fis = new FileInputStream(file);
               list = parse(fis);
               fis.close();
           }
           catch (IOException e) {
        	   logger.error("error:",e);
           }
           finally {
               if (fis != null) {
                   try {
                       fis.close();
                   }
                   catch (IOException e) {
                	   logger.error("error:",e);
                   }
               }
           }
           return list;
           // 代码安全扫描对应end
       }
       return null;
   }

   /**
    * 解析XML输入流
    * @param inStream 输入流
    * @return 解析结果
    * @throws ParserConfigurationException ParserConfigurationException
    * @throws SAXException SAXException
    * @throws IOException IOException
    */
   public static List<Map<String, String>> parse(InputStream inStream)
       throws ParserConfigurationException, SAXException, IOException {
       if (null != inStream) {
           SAXHandler handler = new InfoXmlInputParseUtil.SAXHandler();
           InputSource is = new InputSource(inStream);
           
           SAXParserFactory spf = SAXParserFactory.newInstance();
           spf.setValidating(false);
           spf.setFeature("http://xml.org/sax/features/validation",false);
           spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
           spf.setFeature("http://xml.org/sax/features/use-entity-resolver2",false);

           SAXParser sax = spf.newSAXParser();
           sax.parse(is, handler);
           return handler.getDataList();
       }
       return null;
   }

   /**
    * 是否为xml文件
    * <p>
    * 判断文件名是否以.xml结尾，如果是则返回true，否则返回false
    * </p>
    *
    * @param fileName 文件名
    * @return xml文件判断结果
    */
   private static boolean isXML(String fileName) {
       if (null != fileName && fileName.toLowerCase().endsWith(".xml")) {
           return true;
       }
       return false;
   }

   static class SAXHandler extends DefaultHandler {

       /**
        * 解析后的数据列表
        */
       private List<Map<String, String>> dataList;

       /**
        *
        */
       private Map<String, String> data;

       /**
        *
        */
       private String key;

       /**
        *
        */
       private String preNode;
       /**
        *
        */
       private StringBuffer sb ;

       /**
        * 开始解析xml文件
        */
       @Override
       public void startDocument()
           throws SAXException {
           super.startDocument();
       }

       /**
        * 解析xml文件结束
        */
       @Override
       public void endDocument()
           throws SAXException {
           super.endDocument();
       }

       /**
        * 开始解析xml元素
        */
       @Override
       public void startElement(String uri, String localName, String qName, Attributes attributes)
           throws SAXException {
           super.startElement(uri, localName, qName, attributes);
           sb = new StringBuffer();
           // qName为标签名称
           if (StringUtil.equalsIgnoreCase(NODE_ROW, qName)) {
               data = new HashMap<>();
               preNode = qName;
               if (null != dataList) {
                   dataList.add(data);
               }
           }
           else if (StringUtil.equalsIgnoreCase(NODE_FIRST, qName)) {
               dataList = new ArrayList<>();
               preNode = qName;
           }
           else {
               // 前一个节点为Data，则可以判断当前节点在Data内部，可认为是有效的数据内容
               if(StringUtil.equalsIgnoreCase(preNode, NODE_ROW)) {
                   key = qName;
               }
           }
       }

       /**
        * 解析xml元素结束
        */
       @Override
       public void endElement(String uri, String localName, String qName)
           throws SAXException {
           super.endElement(uri, localName, qName);
           // 如果为空节点，则赋值为空字符串
           if(null != data && null == data.get(key)&&StringUtil.isNotEmpty(key)) {
               data.put(key, StringUtil.EMPTY);
           }
           key = null;
           if (StringUtil.equalsIgnoreCase(NODE_ROW, qName)) {
               data = null;
           }
           else if (StringUtil.equalsIgnoreCase(NODE_FIRST, qName)) {
               preNode = null;
           }
       }

       @Override
       public void characters(char[] content, int start, int length)
           throws SAXException {
           super.characters(content, start, length);
           // content为整个xml文档的内容，start为当前标签内容所在位置，length为当前标签内容的长度
           // 需要过滤掉空格和换行，SAX解析也会把空格和换行当做xml文档的内容
           if (StringUtil.isNotEmpty(key) && null != data) {
               String str = new String(content, start, length);
               str=str.trim();
               sb.append(str);
               if (StringUtil.isNotEmpty(sb.toString())) {
                   data.put(key, sb.toString());
               }
           }
       }

       public List<Map<String, String>> getDataList() {
           return dataList;
       }

   }
}
