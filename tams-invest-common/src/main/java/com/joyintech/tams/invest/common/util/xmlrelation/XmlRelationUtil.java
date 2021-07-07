/*
 * 文件名：XmlRelationUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.util.xmlrelation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationInputDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationInputParaDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationOutputDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationOutputResultDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationRecordDto;

/**
 * 数据字典转换规则 金融产品分类表字段映射关系
 *
 * @author etliu
 * @version 1.0 2018年12月5日
 * @see XmlRelationUtil
 * @since 1.0
 */
public class XmlRelationUtil {

    /**
     * root
     */
    private static final String NODE_ROOT = "root";

    /**
     * record
     */
    private static final String NODE_RECORD = "record";

    /**
     * input
     */
    private static final String NODE_INPUT = "input";

    /**
     * output
     */
    private static final String NODE_OUTPUT = "output";

    /**
     * para
     */
    private static final String NODE_PARA = "para";

    /**
     * result
     */
    private static final String NODE_RESULT = "result";

    /**
     * id
     */
    private static final String ATTR_ID = "id";
    
    /**
     * sysCode
     */
    private static final String SYSCODE = "sysCode";
    
    /**
     * dictCode
     */
    private static final String DICTCODE = "dictCode";
    
    /**
     * itemCode
     */
    private static final String ITEMCODE = "itemCode";

    /**
     * CACHE_KEY_XMLRELATION
     */
    private static final String CACHE_KEY_XMLRELATION = "cache_key_xmlrelation";

    /**
     * SYSCODE_VAL
     */
    public static final String SYSCODE_VAL = "VAL";

    /**
     * 根据input获取对应result
     *
     * @param inputParaList input
     * @return result
     * @see
     * @since 1.0
     */
    public static XmlRelationOutputDto getOutPut(List<XmlRelationInputParaDto> inputParamList) {
        if (StringUtil.isEmptyList(inputParamList)) {
            return null;
        }

        List<XmlRelationRecordDto> xmlRelationDataList = getXmlRelationData();
        XmlRelationInputDto inputParam = new XmlRelationInputDto();
        inputParam.setParaDtoList(inputParamList);

        for (XmlRelationRecordDto xmlRelationRecordDto : xmlRelationDataList) {
            if (xmlRelationRecordDto.getInput().equals(inputParam)) {
                return xmlRelationRecordDto.getOutput();
            }
        }

        return null;
    }

    /**
     * 根据input获取对应result
     *
     * @param dictCodeList dictCodeList
     * @return result
     * @see
     * @since 1.0
     */
    public static XmlRelationOutputDto getOutPut(BussinessDictCodeList... dictCodeList) {
        List<XmlRelationInputParaDto> inputParamList = new ArrayList<XmlRelationInputParaDto>();
        for (BussinessDictCodeList bussinessDictCodeList : dictCodeList) {
            if (bussinessDictCodeList != null) {
                inputParamList.add(new XmlRelationInputParaDto(bussinessDictCodeList));
            }
        }

        return getOutPut(inputParamList);
    }

    /**
     * 获取itemCode对应的值
     * 系统代码默认估值核算VAL
     *
     * @param inputParamList inputParamList
     * @param sysCode sysCode
     * @param resultDictCode resultDictCode
     * @return itemCode对应的值
     * @see
     * @since 1.0
     */
    public static String getVALOutPutResultItemCode(List<XmlRelationInputParaDto> inputParamList,
                                                    String resultDictCode) {
        return getVALOutPutResultItemCode(getOutPut(inputParamList), resultDictCode);
    }

    /**
     * 获取itemCode对应的值
     * 系统代码默认估值核算VAL
     *
     * @param inputParamList inputParamList
     * @param sysCode sysCode
     * @param resultDictCode resultDictCode
     * @return itemCode对应的值
     * @see
     * @since 1.0
     */
    public static String getVALOutPutResultItemCode(String resultDictCode, BussinessDictCodeList... dictCodeList) {
        List<XmlRelationInputParaDto> inputParamList = new ArrayList<XmlRelationInputParaDto>();
        for (BussinessDictCodeList bussinessDictCodeList : dictCodeList) {
            if (bussinessDictCodeList != null) {
                inputParamList.add(new XmlRelationInputParaDto(bussinessDictCodeList));
            }
        }
        return getVALOutPutResultItemCode(getOutPut(inputParamList), resultDictCode);
    }

    /**
     * 获取itemCode对应的值
     * 系统代码默认估值核算VAL
     *
     * @param outputDto outputDto
     * @param resultDictCode dictCode
     * @return itemCode对应的值
     * @see
     * @since 1.0
     */
    public static String getVALOutPutResultItemCode(XmlRelationOutputDto outputDto,
                                                    String resultDictCode) {
        if (outputDto == null || StringUtil.isEmptyList(outputDto.getResultDtoList())) {
            return null;
        }

        for (XmlRelationOutputResultDto resultDto : outputDto.getResultDtoList()) {
            if (StringUtil.equals(SYSCODE_VAL, resultDto.getSysCode())
                && StringUtil.equals(resultDictCode, resultDto.getDictCode())) {
                return resultDto.getItemCode();
            }
        }

        return null;
    }

    /**
     * 获取itemCode对应的值
     * 系统代码默认估值核算VAL
     *
     * @param outputDto outputDto
     * @param sysCode sysCode
     * @param resultDictCode dictCode
     * @return itemCode对应的值
     * @see
     * @since 1.0
     */
    public static String getOutPutResultItemCode(XmlRelationOutputDto outputDto,
                                                    String sysCode,
                                                    String resultDictCode) {
        if (outputDto == null || StringUtil.isEmptyList(outputDto.getResultDtoList())) {
            return null;
        }

        for (XmlRelationOutputResultDto resultDto : outputDto.getResultDtoList()) {
            if (StringUtil.equals(sysCode, resultDto.getSysCode())
                && StringUtil.equals(resultDictCode, resultDto.getDictCode())) {
                return resultDto.getItemCode();
            }
        }

        return null;
    }

    /**
     * 解析XML输入流
     *
     * @param inStream 输入流
     * @return 解析结果
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    private static List<XmlRelationRecordDto> parse(InputStream inStream)
        throws ParserConfigurationException, SAXException, IOException {
        if (null != inStream) {
            XmlRelationSAXHandler handler = new XmlRelationUtil.XmlRelationSAXHandler();
            InputSource is = new InputSource(inStream);
//            SAXParser sax = SAXParserFactory.newInstance().newSAXParser();
            SAXParserFactory spf = SAXParserFactory.newInstance();
            //------start---------2020-11-25 周敏 添加 
            spf.setValidating(false);
            spf.setFeature("http://xml.org/sax/features/validation",false);
            spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            spf.setFeature("http://xml.org/sax/features/use-entity-resolver2",false);
            SAXParser sax = spf.newSAXParser();
            //------end---------
            sax.parse(is, handler);
            return handler.getRecordList();
        }
        return null;
    }

    /**
     * 获取关系配置数据
     *
     * @return 关系配置数据
     * @see
     * @since 1.0
     */
    public synchronized static List<XmlRelationRecordDto> getXmlRelationData() {
        // 从系统缓存中获取，如果获取不到，则重新解析xml，并放至缓存
        @SuppressWarnings("unchecked")
        List<XmlRelationRecordDto> resultList = CacheUtil.getAppCache(CACHE_KEY_XMLRELATION,
            List.class);
        if (StringUtil.isEmptyList(resultList)) {
            InputStream xmlFileInStream = XmlRelationUtil.class.getClassLoader().getResourceAsStream(
                "data_dict_convert_rule.xml");
            try {
                resultList = XmlRelationUtil.parse(xmlFileInStream);
                CacheUtil.putAppCache(CACHE_KEY_XMLRELATION, resultList);
            }
            catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            catch (SAXException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (xmlFileInStream != null){
                    try {
                        xmlFileInStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return resultList;
    }

//    /**
//     * 根据input获取对应result列表
//     *
//     * @param inputParaList input
//     * @return result列表
//     * @see
//     * @since 1.0
//     */
//    public static List<XmlRelationOutputDto> getOutPutList(List<XmlRelationInputParaDto> inputParamList) {
//        if (StringUtil.isEmptyList(inputParamList)) {
//            return null;
//        }
//
//        List<XmlRelationRecordDto> xmlRelationDataList = getXmlRelationData();
//        XmlRelationInputDto inputParam = new XmlRelationInputDto();
//        inputParam.setParaDtoList(inputParamList);
//
//        List<XmlRelationOutputDto> resultList = new ArrayList<XmlRelationOutputDto>();
//
//        for (XmlRelationRecordDto xmlRelationRecordDto : xmlRelationDataList) {
//            if (xmlRelationRecordDto.getInput().equals(inputParam)) {
//                resultList.add(xmlRelationRecordDto.getOutput());
//            }
//        }
//
//        return resultList;
//    }

    static class XmlRelationSAXHandler extends DefaultHandler {

        /**
         * recordList
         */
        private List<XmlRelationRecordDto> recordList;

        /**
         * record
         */
        private XmlRelationRecordDto record;

        /**
         * inputParaList
         */
        private List<XmlRelationInputParaDto> inputParaList;

        /**
         * outputResultList
         */
        private List<XmlRelationOutputResultDto> outputResultList;

        /**
         * para
         */
        private XmlRelationInputParaDto para;

        /**
         * result
         */
        private XmlRelationOutputResultDto result;

        /**
         * isParaFlag
         */
        private boolean isParaFlag;

        /**
         * preNode
         */
        private String preNode;
        
        private StringBuffer paraValue = new StringBuffer(); 
        
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
            writeLog("[startElement] uri: " + uri);
            writeLog("[startElement] localName: " + localName);
            writeLog("[startElement] qName: " + qName);

            if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
                recordList = new ArrayList<XmlRelationRecordDto>();
            }
            else if (StringUtil.equalsIgnoreCase(NODE_RECORD, qName)) {
                record = new XmlRelationRecordDto();
                record.setId(attributes.getValue(ATTR_ID));
            }
            else if (StringUtil.equalsIgnoreCase(NODE_INPUT, qName)) {
                inputParaList = new ArrayList<XmlRelationInputParaDto>();
            }
            else if (StringUtil.equalsIgnoreCase(NODE_OUTPUT, qName)) {
                outputResultList = new ArrayList<XmlRelationOutputResultDto>();
            }
            else if (StringUtil.startsWithIgnoreCase(qName, NODE_PARA)) {
                para = new XmlRelationInputParaDto();
                isParaFlag = true;
            }
            else if (StringUtil.startsWithIgnoreCase(qName, NODE_RESULT)) {
                result = new XmlRelationOutputResultDto();
                isParaFlag = false;
            }
            preNode = qName;
        }

        /**
         * 解析xml元素结束
         */
        @Override
        public void endElement(String uri, String localName, String qName)
            throws SAXException {
            super.endElement(uri, localName, qName);
            writeLog("[endElement] uri: " + uri);
            writeLog("[endElement] localName: " + localName);
            writeLog("[endElement] qName: " + qName);

            if (StringUtil.equalsIgnoreCase(NODE_ROOT, qName)) {
            }
            else if (StringUtil.equalsIgnoreCase(NODE_RECORD, qName)) {
                recordList.add(record);
            }
            else if (StringUtil.equalsIgnoreCase(NODE_INPUT, qName)) {
                XmlRelationInputDto inputDto = new XmlRelationInputDto();
                inputDto.setParaDtoList(inputParaList);
                record.setInput(inputDto);
            }
            else if (StringUtil.equalsIgnoreCase(NODE_OUTPUT, qName)) {
                XmlRelationOutputDto outputDto = new XmlRelationOutputDto();
                outputDto.setResultDtoList(outputResultList);
                record.setOutput(outputDto);
            }
            else if (StringUtil.startsWithIgnoreCase(qName, NODE_PARA)) {
                inputParaList.add(para);
            }
            else if (StringUtil.startsWithIgnoreCase(qName, NODE_RESULT)) {
                outputResultList.add(result);
            }
            else if (StringUtil.startsWithIgnoreCase(qName, SYSCODE)) {
            	if(isParaFlag) {
            		para.setSysCode(paraValue.toString());
            	}
            	else {
            		result.setSysCode(paraValue.toString());
            	}
            }
            else if (StringUtil.startsWithIgnoreCase(qName, DICTCODE)) {
            	if(isParaFlag) {
            		para.setDictCode(paraValue.toString());
            	}
            	else {
            		result.setDictCode(paraValue.toString());
            	}
            }
            else if (StringUtil.startsWithIgnoreCase(qName, ITEMCODE)) {
            	if(isParaFlag) {
            		para.setItemCode(paraValue.toString());
            	}
            	else {
            		result.setItemCode(paraValue.toString());
            	}
            }
        	// 清空
        	paraValue.setLength(0);
        }

        @Override
        public void characters(char[] content, int start, int length)
            throws SAXException {
            super.characters(content, start, length);
            // content为整个xml文档的内容，start为当前标签内容所在位置，length为当前标签内容的长度
            // 需要过滤掉空格和换行，SAX解析也会把空格和换行当做xml文档的内容

            String valueStr = new String(content, start, length);
            valueStr = valueStr.trim();
            writeLog("[characters] str: " + valueStr);

            if (StringUtil.isNotBlank(valueStr) && StringUtil.isNotBlank(preNode)
                && !(StringUtil.equalsIgnoreCase(NODE_ROOT, preNode)
                     || StringUtil.equalsIgnoreCase(NODE_RECORD,
                         preNode)
                     || StringUtil.equalsIgnoreCase(NODE_INPUT, preNode)
                     || StringUtil.equalsIgnoreCase(NODE_OUTPUT, preNode)
                     || StringUtil.startsWithIgnoreCase(preNode, NODE_PARA)
                     || StringUtil.startsWithIgnoreCase(preNode, NODE_RESULT))) {
            	paraValue.append(valueStr);
//                if (isParaFlag) {
//                    try {
//                        Field field = para.getClass().getDeclaredField(preNode);
//                        field.setAccessible(true);
//                        field.set(para, valueStr);
//                    }
//                    catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    try {
//                        Field field = result.getClass().getDeclaredField(preNode);
//                        field.setAccessible(true);
//                        field.set(result, valueStr);
//                    }
//                    catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }

        /**
         * 获取 recordList
         *
         * @return recordList.
         */
        public List<XmlRelationRecordDto> getRecordList() {
            return recordList;
        }
    }

    // public static void main(String[] args)
    // throws ParserConfigurationException, SAXException {
    // String xmlPath = "F:/project/平安投管_SVN/项目库/04.系统设计/功能设计/详细设计/数据字典转换规则.xml";
    //
    // File xmlFile = new File(xmlPath);
    //
    // FileInputStream fis = null;
    // List<Map<String, String>> list = null;
    // try {
    // fis = new FileInputStream(xmlFile);
    //
    // List<XmlRelationRecordDto> recordList = XmlRelationUtil.parse(fis);
    //
    // fis.close();
    // }
    // catch (IOException e) {
    // e.printStackTrace();
    // }
    // finally {
    // if (fis != null) {
    // try {
    // fis.close();
    // }
    // catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // }

    private static void writeLog(String log) {
        // String logPath = "F:/project/平安投管_SVN/项目库/04.系统设计/功能设计/详细设计/etliu.log";
        //
        // File logFile = new File(logPath);
        // try {
        // FileWriter fileWriter = new FileWriter(logFile, true);
        // fileWriter.write("\r\n");
        // fileWriter.write(log);
        // fileWriter.flush();
        //
        // fileWriter.close();
        // }
        // catch (IOException e) {
        // e.printStackTrace();
        // }

    }
}
