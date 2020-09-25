/*
 * 文件名：InterfException.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.invest.interf.common.exception;

/**
 * 接口异常
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月8日
 * @see InterfException
 * @since 1.0
 */
public class InterfException extends Exception {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2850377747486146140L;

    /**
     * @param message
     * @param cause
     */
    public InterfException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public InterfException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InterfException(Throwable cause) {
        super(cause);
    }
}
