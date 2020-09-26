/*
 * 文件名：ResultCode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.common;

/**
 * 处理结果代码
 * @author zhangwei
 * @version 1.0 2019年1月10日
 * @see ResultCode
 * @since 1.0
 */
public enum ResultCode {

    
    SUCCESS("0000", "成功"),
    
    WARNING("1111", "警告"),
    
    FAILURE("2222", "失败");
    
    /**
     * 代码
     */
    String code;

    /**
     * 含义
     */
    String name;
    
    ResultCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    public String getCode() {
        return code;
    }

    
    public String getName() {
        return name;
    }
    
    
}
