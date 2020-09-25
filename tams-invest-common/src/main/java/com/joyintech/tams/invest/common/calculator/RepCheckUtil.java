/*
 * 文件名：RepCheckUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.calculator;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.joyintech.framework.util.StringUtil;

/**
 * 中债报送信息check工具类
 * 
 * @author zhangliang
 * @version 1.0 2018年11月15日
 * @see RepCheckUtil
 * @since 1.0
 */
public class RepCheckUtil {

    /**
     * 检查字节数是否超长（GBK编码，一个汉字两个字节）
     *
     * @param str 检查项目值
     * @param maxLength 最大长度
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public static boolean checkByteLength(String str, int maxLength) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                return str.getBytes("GBK").length > maxLength;
            }
            catch (UnsupportedEncodingException e) {
            }
        }
        return false;
    }

    /**
     * 获取字节数（GBK编码，一个汉字两个字节）
     *
     * @param str str
     * @return 字节数
     * @see
     * @since 1.0
     */
    public static int getByteLength(String str) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                return str.getBytes("GBK").length;
            }
            catch (UnsupportedEncodingException e) {
            }
        }
        return 0;
    }
    /**
     * check 手机号码是否是11位数字
     * @param value
     * @param context
     * @return
     */
    public static boolean checkNum11(String value) {
    	/**
         * 验证规则
         */
        Pattern pattern = Pattern.compile("^1\\d{10}$");
        if (StringUtil.isEmpty(value)) {
            return true;
        }
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
