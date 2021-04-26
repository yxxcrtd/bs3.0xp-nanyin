package com.joyintech.tams.valuation.valapi.constant;

import com.joyintech.framework.util.StringUtil;

/**
 * @author yanya
 * @create 2021-03-03 16:26
 */
public enum DictCodeValApi {

    /** modify by yanya on 20210203 估值对外api数据字典 start**/
    API字段注解类型("VAL_API_FIELD_TYPE"),
    /** modify by yanya on 20210203 估值对外api数据字典 end**/
    ;
    /**
     * 对应于数据字典code
     */
    private String value;

    DictCodeValApi(String value) {
        this.value = value;
    }

    /**
     * @param value 数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static DictCodeValApi get(String value) {
        DictCodeValApi[] values = DictCodeValApi.values();
        for (DictCodeValApi code : values) {
            if (StringUtil.equals(value, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    public String getValue() {
        return value;
    }


    /**
     * 通过value生成枚举
     *
     * @param value value值
     * @return DictCode
     */
    public static DictCodeValApi fromValue(String value) {
        for (DictCodeValApi dictCode : DictCodeValApi.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }
}
