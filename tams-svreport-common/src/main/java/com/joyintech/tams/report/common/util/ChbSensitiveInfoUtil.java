/*
 * 文件名：ChbSensitiveInfoUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib;

/**
 * 中债报送信息敏感信息处理util
 * @author etliu
 * @version 1.0 2019年11月14日
 * @see ChbSensitiveInfoUtil
 * @since 1.0
 */
public class ChbSensitiveInfoUtil {
    /**
     * 所有汉字
     */
    public static final String CHINESE_REGEX = "[\\u4e00-\\u9fef\\u3400-\\u4db5\\x{00020000}-\\x{0002a6d6}\\x{0002a700}-\\x{0002b734}\\x{0002b740}-\\x{0002b81d}\\x{0002b820}-\\x{0002cea1}\\x{0002ceb0}-\\x{0002ebe0}\\uf900-\\ufad9]+";
    
    /**
     * hexArray
     */
    private final static String[] hexArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 变形处理
     * 针对客户姓名
     * 对于三个汉字以内（包含三个汉字）的名称保留最后一个字，其余长度的名称不做变形
     * 例如，对于“张三”保留“三”，对于“李小二”保留“二”，对于“曙光太阳能公司”全部保留。
     * @param source 原字符串
     * @return 处理后字符串
     */
    public static String handle汉字变形(String source) {
        int nameItemChineseTranLength = StringUtil.toInt(
                CacheUtil.getSysParam(ReportGlobalConstant.NAME_ITEM_CHINESE_TRAN_LENGTH));
            if (nameItemChineseTranLength <= 0) {
                nameItemChineseTranLength = 4;
            }
            if (StringUtil.isNotBlank(source) && source.length() <= nameItemChineseTranLength) {
                if (source.matches(CHINESE_REGEX)) {
                    if(source.substring(source.length() - 1).matches(CHINESE_REGEX)){
                        return source.substring(source.length() - 1);   
                    }
                    return source.substring(source.length() - 2);
                }
            }
            return source;
        }
    
    /**
     * 包含个人身份证件号码的客户统一编号
     * ※这里的个人身份证件号码不单单指身份证，包含个人客户所有的证件类型
     * 若身份证件号码中有英文字母，应先统一转换成大写英文字母
     * 将身份证件号码部分用身份证件号码全文UTF-8编码的MD5杂凑值替换，其余部分不变。
     * （的MD5杂凑值: 32字符，英文按小写输出）
     * @param source 原字符串
     * @param idNo 身份证件号码
     * @return 处理后字符串
     * @throws UnsupportedEncodingException 
     */
    public static String replaceIdNoToMd5(String source, String idNo) throws UnsupportedEncodingException {
        if (StringUtil.isBlank(source) || StringUtil.isBlank(idNo)) {
            return source;
        }

        if (StringUtil.contains(source, idNo)) {
            if(source.startsWith(idNo)){
                source="JOYIN"+source;
                return StringUtil.replaceAll(source, idNo, encrypByMD5(idNo.toUpperCase())).substring(5);
            }else{
                return StringUtil.replaceAll(source, idNo, encrypByMD5(idNo.toUpperCase()));
            }
        }
        
        return source;
    }
    
    /**
     * 生僻字特殊处理
     * 当投资者姓名中出现生僻字无法录入系统时， 则对生僻字采用“拼音[Unicode码]”的形式进行登记。
     * 例如，投资者名称为“zhāng张 d í頔” ， “頔”无法正常录入系统，
     * 则投资者名称正确录入方式为“张 DI[0E43E]” 。
     * 其中 Unicode码不足5 位的，应在该码前方以“0”补足。
     * @param source source
     * @return 处理后字符串
     */
    public static String handle生僻字(String source) {
        // 若原字符串为空或者在白名单内，则不处理
        if (StringUtil.isBlank(source) || BaseCheckLib.inWhitelistAll(source)) {
            return source;
        }

        Map<String, String> convetMap = new HashMap<String, String>();
        for (int i = 0; i < source.length(); i++ ) {
            String currStr = StringUtil.substring(source, i, i + 1);
            if (!currStr.matches(BaseCheckLib.PATTERN_ALL)) {
                // 获取生僻字对应拼音
                
            }
        }
        
        return source;
    }
    
    public static void main(String[] args) {
        String str = "鿕";
        System.out.println(unicodeEncode(str));
        System.out.println(str.matches(CHINESE_REGEX));
        System.out.println(str.matches(BaseCheckLib.PATTERN_ALL));
        
        String dataStr = "\\u9fd5";
        System.out.println(decodeUnicode(dataStr));
        
        String teString = "(";
        System.out.println(teString.matches(BaseCheckLib.PATTERN_ALL));
        
        String testStr = "刘二二二通";
        System.out.println(handle汉字变形(testStr));
        try {
            String ss=replaceIdNoToMd5("131000000104150829XX","131000000104150829");
            System.out.println(ss);
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    /***
     * 对指定的字符串进行MD5加密
     * @throws UnsupportedEncodingException 
     */
    private static String encrypByMD5(String originString) throws UnsupportedEncodingException {
        try {
            //创建具有MD5算法的信息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
            byte[] bytes = md.digest(originString.getBytes("UTF-8"));
            //将得到的字节数组变成字符串返回
            String s = byteArrayToHex(bytes);
            return s.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将字节数组转换成十六进制，并以字符串的形式返回
     * 128位是指二进制位。二进制太长，所以一般都改写成16进制，
     * 每一位16进制数可以代替4位二进制数，所以128位二进制数写成16进制就变成了128/4=32位。
     */
    private static String byteArrayToHex(byte[] b){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byteToHex(b[i]));
        }
        return sb.toString();
    }
    /**
     * 将一个字节转换成十六进制，并以字符串的形式返回
     */
    private static String byteToHex(byte b) {
        int n = b;
        if (n < 0) {
            n = n + 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexArray[d1] + hexArray[d2];
    }
    
    /**
     * @Title: unicodeEncode 
     * @Description: unicode编码
     * @param string
     * @return
     */
    private static String unicodeEncode(final String string) {
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + hexB;
        }
        return unicodeBytes;
    }
    
    /*
     * unicode编码转中文
     */
    private static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
