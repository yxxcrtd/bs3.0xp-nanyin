/*
 * 文件名：ValidationConstant.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月5日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.validation;

/**
 * 存放校验规则的常量
 * 
 * @author 何阳阳
 * @version 2018年1月16日
 * @see ValidationConstant
 * @since 1.0
 */
public class ValidationConstant {

    /**
     * 整数位最大位数
     */
    public static final String FIELD_MAX_INT_BIT = "maxIntBit";

    /**
     * 小数为最大位数
     */
    public static final String FIELD_MAX_DEC_BIT = "maxDecBit";

    /**
     * 用户名验证规则
     */
    public static final String PATTERN_USERNAME = "^[a-zA-Z]{1}[a-zA-Z0-9_]{1,19}$";

    /**
     * 密码验证规则
     */
    public static final String PATTERN_PASSWORD = "^[a-zA-Z0-9_]{1,16}$";

    /**
     * 手机号效验规则
     */
    public static final String PATTERN_MOBILE_PHONE = "^1[3,4,5,7,8]\\d{9}$";

    /**
     * 固定电话验证
     */
    public static final String PATTERN_OFFICE_PHONE = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,8})?$";
    
    /**
     * 固定电话验证（区号必须输入，中债报送用）
     */
    public static final String PATTERN_OFFICE_PHONE_CHB = "^(0[0-9]{2,3}\\-)([2-9][0-9]{6,7})+(\\-[0-9]{1,8})?$";
    
    /**
     * IP地址效验规则
     */
    public static final String PATTERN_IP = "^((25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    /**
     * 网址URL验证规则
     */
    public static final String PATTERN_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 汉字效验规则
     */
//    public static final String PATTERN_CHINESE = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
    
    /**
     * 文字效验规则（需要完善）
     * TODO 所有文字类型的check需要参考中债进行修改
     */
    public static final String PATTERN_WORD = "[^?!@#$%\\^*]+";//"[\\S\r\n]+";

    /**
     * 地址效验规则
     */
    public static final String PATTERN_ADDRESS = "^([a-zA-Z0-9\u4e00-\u9fa5]).+$";

    /**
     * 日期验证规则
     */
    public static final String PATTERN_DATE = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})"
                                              + "-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))"
                                              + "|(02-(0[1-9]|[1][0-9]|2[0-8]))))"
                                              + "|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    /**
     * 英数字验证规则
     */
    public static final String PATTERN_EN_NUMBER = "^[a-zA-Z0-9]+$";
    
    /**
     * 英数字验证规则
     */
    public static final String PATTERN_EN_NUMBER_CROSS = "^[a-zA-Z0-9\\-]+$";

    /**
     * 英文字符验证规则
     */
    public static final String PATTERN_ENGLISH = "^[a-zA-Z]+$";

    /**
     * 数字验证规则
     */
    public static final String PATTERN_NUMBER = "^\\d+$";

    /**
     * 电子邮箱验证规则
     */
    public static final String PATTERN_EMAIL = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";

    /**
     * 数值型验证规则（带小数位和整数位）
     */
    public static final String PATTERN_NUMBER_DECIMAL = "^[-]?\\d{1,maxIntBit}(\\.\\d{1,maxDecBit})?$";
}
