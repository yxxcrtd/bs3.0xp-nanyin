/**
 * 文件名：AppException.java<br>
 * 作成时间：2011-11-28<br>
 * 修改时间：2011-11-28<br>
 * 版本：V1.0
 */
package com.joyintech.tams.utils.poi;

import java.io.Serializable;

/**
 * 类名：AppException.java <br>
 * 说明：系统应用异常<br>
 * 备注：<br>
 * 作者：xyli
 */
public class PoiException extends Exception implements Serializable {

    /**
     *默认的序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误代码
     */
    protected String errorCode;

    /**
     * 是否为系统异常
     */
    private final boolean isSystemException;

    /**
     * 说明：默认构建函数<br>
     * 备注：<br>
     */
    public PoiException() {
        super();
        isSystemException = false;
    }

    /**
     * 说明：带错误代码,参数的构建函数<br>
     * 备注：<br>
     * @param errorCode 错误代码
     * @param args 参数
     */
    public PoiException(final String errorCode, String... args) {
        this.errorCode = errorCode;
        isSystemException = false;
    }

    /**
     * 说明：带错误代码,参数,原因的构建函数<br>
     * 备注：<br>
     * @param errorCode 错误代码
     * @param args 参数
     * @param cause 原因
     */
    public PoiException(final String errorCode, Throwable cause, String... args) {
        this.errorCode = errorCode;
        if (cause != null) {
            isSystemException = true;
        } else {
            isSystemException = false;
        }
    }

//    /**
//     * 说明：带错误代码,参数,原因的构建函数<br>
//     * 备注：<br>
//     * @param errorCode 错误代码
//     * @param args 参数
//     * @param cause 原因
//     */
//    public PoiException(final String errorCode, String args, Throwable cause) {
//        super(MessageEnv.get(errorCode, args), cause);
//        this.errorCode = errorCode;
//        if (cause != null) {
//            isSystemException = true;
//        } else {
//            isSystemException = false;
//        }
//    }

    /**
     * 说明：带消息和错误原因的构建函数<br>
     * 备注：<br>
     * @param message 消息
     * @param cause 错误原因
     */
    public PoiException(final String message, Throwable cause) {
        super(message, cause);
        if (cause != null) {
            isSystemException = true;
        } else {
            isSystemException = false;
        }
    }

//    /**
//     * 说明：带消息,错误代码和错误原因的构建函数<br>
//     * 备注：<br>
//     * @param message 消息
//     * @param errorCode 错误代码
//     * @param cause 错误原因
//     */
//    public PoiException(final String message, Throwable cause, String errorCode) {
//        super(message, cause);
//        this.errorCode = errorCode;
//        if (cause != null) {
//            isSystemException = true;
//        } else {
//            isSystemException = false;
//        }
//    }

    /**
     * 说明：带错误原因的构建函数<br>
     * 备注：<br>
     * @param cause 错误原因
     */
    public PoiException(final Throwable cause) {
        super(cause);
        if (cause != null) {
            isSystemException = true;
        } else {
            isSystemException = false;
        }
    }

    /**
     * 说明：获取错误代码<br>
     * 备注：<br>
     * @return 错误代码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 说明：获取异常标识<br>
     * 备注：<br>
     * @return 异常标识
     */
    public boolean isSystemException() {
        return isSystemException;
    }

}