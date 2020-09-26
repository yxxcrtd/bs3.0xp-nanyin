/*
 * 文件名：ImportFlgEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;

/**
 * 导入结果枚举
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ImportFlgEnum
 * @since 1.0
 */
public enum ImportFlgEnum {
    /**
     * 导入成功
     */
    导入成功("01", "导入成功"),
    /**
     * 导入失败
     */
    导入失败("02", "导入失败"),
    /**
     * 部分成功
     */
    部分成功("03", "部分成功");
    ;

    ImportFlgEnum(String code, String name) {
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
