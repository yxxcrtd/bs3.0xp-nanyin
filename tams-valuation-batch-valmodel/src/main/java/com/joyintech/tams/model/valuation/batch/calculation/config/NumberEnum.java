package com.joyintech.tams.model.valuation.batch.calculation.config;

/**
 * @author zznn
 * @date 2021/7/1
 */
public enum NumberEnum {
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

