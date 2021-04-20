/*
 * 文件名：BusinessException.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月16日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.exception;

/**
 * 组件异常
 * // TODO 需要被细化
 * @author 何阳阳
 * @version 1.0
 */
public class ComponentException extends RuntimeException {

    /**
     * 版本唯一编码
     */
    private static final long serialVersionUID = 1L;

    public ComponentException() {}

    public ComponentException(String message) {
        super(message);
    }

    public ComponentException(Throwable cause) {
        super(cause);
    }

    public ComponentException(String message, Throwable cause) {
        super(message, cause);
    }

}
