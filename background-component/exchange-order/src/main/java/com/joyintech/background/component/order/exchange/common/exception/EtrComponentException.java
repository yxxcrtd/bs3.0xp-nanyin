/*
 * 文件名：EtrComponentException.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.exception;


/**
 * 投资指令组件异常
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月9日
 * @see EtrComponentException
 * @since 1.0
 */
public class EtrComponentException extends RuntimeException {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -71389970758716302L;

    public EtrComponentException() {
        super();
    }

    public EtrComponentException(String message) {
        super(message);
    }

    public EtrComponentException(String message, Throwable e) {
        super(message, e);
    }
}
