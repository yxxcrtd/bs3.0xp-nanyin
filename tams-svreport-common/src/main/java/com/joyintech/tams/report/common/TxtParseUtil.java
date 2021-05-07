/*
 * 文件名：XmlParseUtil.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年10月26日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.joyintech.framework.util.StringUtil;


/**
 * TXT解析工具类
 *
 * @author 何阳阳
 * @version 1.0 2017年10月26日
 * @see TxtParseUtil
 * @since 1.0
 */
public final class TxtParseUtil {

    /**
     *
     */
    private static final String SPLIT_STR = "|";

//    /**
//     * 解析TXT文件，以MAP的形式返回解析后的结果
//     *
//     * @param fileName TXT文件名（绝对路径）
//     * @return 解析结果
//     * @throws IOException IOException
//     * @throws SAXException SAXException
//     * @throws ParserConfigurationException ParserConfigurationException
//     */
//    public static List<Map<String, String>> parse(String fileName)
//        throws ParserConfigurationException, SAXException, IOException {
//        return parse(new File(fileName));
//    }

//    /**
//     * 解析XML文件，以MAP的形式返回解析后的结果
//     * <p>
//     * 解析XML文件中的每个节点，将ROW节点作为一个MAP对象
//     * </p>
//     *
//     * @param file XML文件
//     * @return 解析结果
//     * @throws SAXException SAXException
//     * @throws ParserConfigurationException ParserConfigurationException
//     * @throws IOException IOException
//     */
//    public static List<Map<String, String>> parse(File file)
//        throws ParserConfigurationException, SAXException, IOException {
//        if (isTXT(file.getName())) {
//            return parse(new FileInputStream(file));
//        }
//        return null;
//    }

    /**
     * 解析XML输入流
     *
     * @param inStream 输入流
     * @param reportInterfFileEncoding 文件编码
     * @param reportInterfSplitCharacter 解析分隔符
     * @return 解析结果
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException SAXException
     * @throws IOException IOException
     */
    public static List<Map<String, String>> parse(InputStream inStream, String reportInterfFileEncoding,String reportInterfSplitCharacter)
        throws ParserConfigurationException, SAXException, IOException {
        if (null != inStream) {
            return parseTxt(inStream, reportInterfFileEncoding, reportInterfSplitCharacter);
        }
        return null;
    }

//    /**
//     * 是否为txt文件
//     * <p>
//     * 判断文件名是否以.txt结尾，如果是则返回true，否则返回false
//     * </p>
//     *
//     * @param fileName 文件名
//     * @return txt文件判断结果
//     */
//    private static boolean isTXT(String fileName) {
//        if (null != fileName && fileName.toLowerCase().endsWith(".txt")) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 解析txt文件<br>
     * 该方法只能解析符合特定格式的txt文件，第一行作为属性名定义行
     * @param inStream txt文件输入流
     * @param reportInterfFileEncoding 文件编码
     * @param reportInterfSplitCharacter 分割符
     * @return 解析结果
     * @throws IOException 异常
     */
    private static List<Map<String, String>> parseTxt(InputStream inStream, String reportInterfFileEncoding, String reportInterfSplitCharacter)
        throws IOException {
        List<Map<String, String>> resultList = new ArrayList<>();
        List<String> propList = new ArrayList<>();
        if(null == inStream) {
            return resultList;
        }
        try {
            String fileEncoding = StringUtil.getOrElse(reportInterfFileEncoding, "UTF-8");

            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, fileEncoding));
            String line = reader.readLine();
            Map<String, String> result = null;
            do{
                //List<String> columns = StringUtil.splitToList(SPLIT_STR, line);
            	List<String> columns = splitToList(reportInterfSplitCharacter, line);
                if(propList.size() == 0) {
                    propList.addAll(columns);
                }
                else {
                    result = new HashMap<>();
                    resultList.add(result);
                    for(int i=0;i<columns.size();i++) {
                        String key = propList.get(i);
                        if(StringUtil.isEmpty(key)) {
                            continue;
                        }
                        //result.put(key, columns.get(i));//delete by lingxq on 2021-05-07 for 空字符串插入数据时，mysql用ifnull函数无效
                        //add by lingxq on 2021-05-07 for 空字符串插入数据时，mysql用ifnull函数无效
                        result.put(key, (StringUtil.isBlank(columns.get(i)) == true ? null :columns.get(i)));
                    }
                }
                line = reader.readLine();
            }while(StringUtil.isNotEmpty(line));
        }
        finally {
            if (null != inStream) {
                inStream.close();
            }
        }
        return resultList;
    }
    /**
     * 阜新（恒生）接口 解析文件（无后缀名）
     * @param inStream
     * @param reportInterfFileEncoding
     * @param reportInterfSplitCharacter
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> parseTxtFx(InputStream inStream, String reportInterfFileEncoding, String reportInterfSplitCharacter)
            throws IOException {
            List<Map<String, String>> resultList = new ArrayList<>();
            List<String> propList = new ArrayList<>();
            propList=Arrays.asList("PUB_DATE", "PROD_ID","LEGAL_LINE", "BLN_AREA", "CUSTOMER_GROUP_ID", "BUY_PLACE", "CCY",	"SUB_AMT",	"SUB_AMT_CNY",	"SUB_QTY",	"PUR_AMT",	"PUR_AMT_CNY",	"PUR_QTY",
            		"RED_AMT","RED_AMT_CNY",	"RED_PRIN",	"RED_PRIN_CNY",	"RED_QTY",	"MAT_AMT",	"MAT_AMT_CNY",	"MAT_PRIN",	"MAT_PRIN_CNY",	"MAT_QTY",	"PBON_AMT",	"PBON_AMT_CNY",	"PBON_QTY",	
            		"CBON_AMT", "CBON_AMT_CNY",	"SADD_AMT",	"SADD_AMT_CNY",	"SADD_QTY",	"SRED_PRIN", "SRED_PRIN_CNY", "SRED_QTY", "INV_PRIN", "CUST_COUNTS", "INV_AMT_CNY",	"INV_QTY", "RATE",	
            		"CUSTOMER_TYPE");
                   

            if(null == inStream) {
                return resultList;
            }
            try {
                String fileEncoding = StringUtil.getOrElse(reportInterfFileEncoding, "UTF-8");

                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, fileEncoding));
                //第一行开始标识 BTATOGZDAT
                String line = reader.readLine();
                //第二行 10
                 line = reader.readLine();
                //第三行 数据日期
                 line = reader.readLine();
                //第三行 数据条数
                 line = reader.readLine();
                //第四行 正式数据
                 line = reader.readLine();
                Map<String, String> result = null;
                do{
                    //List<String> columns = StringUtil.splitToList(SPLIT_STR, line);
                	List<String> columns = splitToList(reportInterfSplitCharacter, line);
//                    if(propList.size() == 0) {
//                        propList.addAll(columns);
//                    }
//                    else {
                        result = new HashMap<>();
                        resultList.add(result);
                        for(int i=0;i<propList.size();i++) {
                            String key = propList.get(i);
                            if(StringUtil.isEmpty(key)) {
                                continue;
                            }
                            result.put(key, columns.get(i));
                        }
//                    }
                    line = reader.readLine();
                }while(!"BTATOGZEND".equals(line));
            }
            finally {
                if (null != inStream) {
                    inStream.close();
                }
            }
            return resultList;
        }
    /**
     * 按照传入的分割符分割字符串
     * @param split
     * @param src
     * @return
     */
    private static List<String> splitToList(String split, String src) {
        if (StringUtil.isEmpty(src)) {
            return new ArrayList<>();
        }
        //默认值
        String sp = "|";
        if (split != null) {
            sp = split;
        }
        int len = sp.length();
        List<String> r = new ArrayList<String>();
        int lastIndex = -1;
        int index = src.indexOf(sp);
        if (-1 == index && src != null) {
            r.add(src);
            return r;
        }
        while (index >= 0) {
        	if(lastIndex ==-1){
        		r.add(src.substring(lastIndex + 1, index));
        	}
        	else if (index > lastIndex) {
                r.add(src.substring(lastIndex + len, index));
            }
            else {
                r.add("");
            }

            lastIndex = index;
            index = src.indexOf(sp, index + len);
            if (index == -1) {
                r.add(src.substring(lastIndex + len, src.length()));
            }
        }
        return r;
    }
}
