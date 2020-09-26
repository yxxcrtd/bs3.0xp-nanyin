/*
 * 文件名：ColumnNullableEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;


/**
 * 列字段是否为空
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ColumnNullableEnum
 * @since 1.0
 */
public enum ColumnNullableEnum {
    /**
     * 可以为空
     */
    可以为空("Y", "可以为空"),
    /**
     * 不可为空
     */
    不可为空("N", "不可为空")
    ;

    ColumnNullableEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 值
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 获取 code
     *
     * @return code.
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取 name
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

}
