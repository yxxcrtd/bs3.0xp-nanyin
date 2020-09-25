/*
 * 文件名：InterfException.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.util;

/**
 * 异常
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月20日
 * @see InterfException
 * @since 1.0
 */
public class InterfException extends Exception {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5270185020150050728L;

    public InterfException() {
    }

    public InterfException(String msg) {
        super(msg);
    }
}
