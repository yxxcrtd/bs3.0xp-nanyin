/*
 * 文件名：NumberEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年6月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.busicommon.common.config;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年6月2日
 * @see NumberEnum
 * @since 1.0
 */
public enum NumberEnum {
    /**
     * 金额
     */
    MONEY(2),
    /**
     * 数量
     */
    QTY(2),
    /**
     * 单位净值
     */
    UNIT_VALUE(4),
    /**
     * 高精度数字
     */
    HIGH_PREC_NUM(14),
    /**
     * 全精度
     */
    ALL_PRECISION(20),
    ;
    /**
     * 值
     */
    private int code;

    /**
     * 构造方法
     * 
     * @param code 值
     */
    NumberEnum(int code) {
        this.code = code;
    }

    /**
     * 获取 code
     * 
     * @return code.
     */
    public int getCode() {
        return code;
    }
}
