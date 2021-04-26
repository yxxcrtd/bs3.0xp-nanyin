package com.joyintech.tams.valuation.valapi.constant;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanya
 * @create 2021-03-03 16:29
 */
public enum DictCodeValApiList {
    API字段注解类型_默认(DictCodeValApi.API字段注解类型, "01", "默认"),
    API字段注解类型_条件(DictCodeValApi.API字段注解类型, "02", "条件"),
    ;
    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private DictCodeValApi dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeValApiList(DictCodeValApi dictCode, String value, String codeName) {
        this.dictCode = dictCode;
        this.value = value;
        this.codeName = codeName;
    }

    /**
     * 获得数据字典详细类
     *
     * @param value 数据字典值
     * @param dictCode 数据字典DictCode
     * @return DictCodeList
     * @see
     * @since 1.0
     */
    public static DictCodeValApiList from(DictCodeValApi dictCode, String value) {
        DictCodeValApiList[] values = DictCodeValApiList.values();
        for (DictCodeValApiList code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == dictCode) {
                return code;
            }
        }
        return null;
    }

    /**
     * 获取数据字典明细List
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<SelectOptionDto> fromDictCode(DictCodeValApi dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        DictCodeValApiList[] values = DictCodeValApiList.values();
        for (DictCodeValApiList code : values) {
            if (code.getDictCode() == dictCode) {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(code.getValue());
                option.setText(code.getCodeName());
                result.add(option);
            }
        }
        return result;
    }

    /**
     * 获取数据字典明细valueList
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<String> valuesfromDictCode(DictCodeValApi dictCode) {
        List<String> result = new ArrayList<>();
        DictCodeValApiList[] values = DictCodeValApiList.values();
        for (DictCodeValApiList code : values) {
            if (code.getDictCode() == dictCode) {
                result.add(code.getValue());
            }
        }
        return result;
    }

    /**
     * 获取 DictCode
     *
     * @return value.
     */
    public DictCodeValApi getDictCode() {
        return dictCode;
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
     * 获取 codeName
     *
     * @return codeName.
     */
    public String getCodeName() {
        return codeName;
    }
    /**
     * 获取 带有引号的名称
     *
     * @return 带有引号的名称.
     */
    public String getQuotesCodeName() {
        return "“" + codeName + "”";
    }
    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public Integer getIntegerCodeName() {
        Integer result = null;
        try {
            result = Integer.parseInt(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }
    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public BigDecimal getBigDecimalCodeName() {
        BigDecimal result = null;
        try {
            result = new BigDecimal(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }

}
