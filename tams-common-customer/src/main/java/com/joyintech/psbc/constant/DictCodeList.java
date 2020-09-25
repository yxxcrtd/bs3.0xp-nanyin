package com.joyintech.psbc.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil; 

/**
 * 数据字典明细枚举
 *
 * @author wangyang1
 * @version 1.0 2019年8月13日
 * @see DictCodeList
 * @since 1.0
 */
public enum DictCodeList implements BaseDictCodeList  { 
    借贷方向_贷(DictCode.借贷方向,"C","贷"),
    借贷方向_借(DictCode.借贷方向,"D","借"),
    计提区分_计提(DictCode.计提区分,"01","计提"),
    计提区分_交易(DictCode.计提区分,"02","交易"), 
    是否_是(DictCode.是否,"Y","是"),
    是否_否(DictCode.是否,"N","否"),
	银行账场景代码_产品固定管理费支付(DictCode.银行账场景代码,"B8103","产品固定管理费支付"),
	银行账场景代码_产品固定管理费计提(DictCode.银行账场景代码,"B8104","产品固定管理费计提"),
	银行账场景代码_产品浮动管理费支付(DictCode.银行账场景代码,"B8105","产品浮动管理费支付"),
	银行账场景代码_产品浮动管理费计提(DictCode.银行账场景代码,"B8104","产品浮动管理费计提"), 
	;

    /**
     * 对应于数据字典code
     */
    private String value;

    public void setDictCode(DictCode dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 数据字典枚举
     */
    private DictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeList(DictCode dictCode, String value, String codeName) {
        this.dictCode = dictCode;
        this.value = value;
        this.setCodeName(codeName);
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
    public static DictCodeList from(DictCode dictCode, String value) {
        DictCodeList[] values = DictCodeList.values();
        for (DictCodeList code : values) {
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
    public static List<SelectOptionDto> fromDictCode(DictCode dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        DictCodeList[] values = DictCodeList.values();
        for (DictCodeList code : values) {
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
    public static List<String> valuesfromDictCode(DictCode dictCode) {
        List<String> result = new ArrayList<>();
        DictCodeList[] values = DictCodeList.values();
        for (DictCodeList code : values) {
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
    @Override
    public DictCode getDictCode() {
        return dictCode;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value value
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取 codeName
     *
     * @return codeName.
     */
    @Override
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

    /**
     * 设置 codeName
     *
     * @param codeName codeName
     */
    @Override
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}
