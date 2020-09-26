/*
 * 文件名：ColumnTypeNameEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.system.excelimphandle.dto;


/**
 * 表的列类型
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ColumnTypeNameEnum
 * @since 1.0
 */
public enum ColumnTypeNameEnum {
    /**
     * CHARACTER
     */
    CHARACTER("CHARACTER"),
    /**
     * VARCHAR
     */
    VARCHAR("VARCHAR"),
    /**
     * DATE
     */
    DATE("DATE"),
    /**
     * TIMESTAMP
     */
    TIMESTAMP("TIMESTAMP"),
    /**
     * DECIMAL
     */
    DECIMAL("DECIMAL"),
    /**
     * INTEGER
     */
    INTEGER("INTEGER")
    ;

    ColumnTypeNameEnum(String code) {
        this.code = code;
    }

    /**
     * 值
     */
    private String code;

    /**
     * 获取 code
     *
     * @return code.
     */
    public String getCode() {
        return code;
    }

}
