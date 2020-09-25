package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PaDocTextConfigKey extends BaseEntity {
    /**
     * 编码
     */
    private String code;

    /**
     * 排序
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal num;

    /**
     * 取得 编码
     * @return 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 取得 排序
     * @return 排序
     */
    public BigDecimal getNum() {
        return num;
    }

    /**
     * 设置 排序
     * @param num 排序
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }
}