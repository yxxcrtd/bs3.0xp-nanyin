package com.joyintech.tams.constant;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典明细枚举
 *
 * @author zhengxiaolian
 * @version 1.0 2022年07月20日
 * @see DictCodeListExt5
 * @since 1.0
 */
public enum DictCodeListExt5 implements BaseDictCodeList {

    发行方式_开放式(DictCodeExt1.发行方式_信批, "01", "开放式"),

    发行方式_封闭式(DictCodeExt1.发行方式_信批, "02", "封闭式"),

    信批产品类别_母产品(DictCodeExt1.信批产品类别, "01", "母产品"),

    信批产品类别_普通产品(DictCodeExt1.信批产品类别, "02", "普通产品"),

    信批产品类别_子产品(DictCodeExt1.信批产品类别, "03", "子产品"),

    ;
    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private DictCodeExt1 dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeListExt5(DictCodeExt1 dictCode, String value, String codeName) {
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
    public static DictCodeListExt4 from(DictCode dictCode, String value) {
        DictCodeListExt4[] values = DictCodeListExt4.values();
        for (DictCodeListExt4 code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == dictCode) {
                return code;
            }
        }
        return null;
    }

    /**
     * 转换成DICTCODELIST
     * @param dictCodeListExt5
     * @return
     */
    public static Map<String,Object> fromDictCodeList(DictCodeListExt5 dictCodeListExt5){
        Map<String,Object> retMap=new HashMap<String,Object>();
        retMap.put("codeName",dictCodeListExt5.codeName);
        retMap.put("value",dictCodeListExt5.value);
        retMap.put("dictCode",dictCodeListExt5.dictCode);
        return retMap;
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
        DictCodeListExt4[] values = DictCodeListExt4.values();
        for (DictCodeListExt4 code : values) {
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
        DictCodeListExt4[] values = DictCodeListExt4.values();
        for (DictCodeListExt4 code : values) {
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
    public DictCodeExt1 getDictCode() {
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
