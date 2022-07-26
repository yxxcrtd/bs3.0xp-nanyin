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

    产品运作方式_开放式净值型(DictCodeExt1.信披_产品运作方式, "01", "开放式净值型"),
    产品运作方式_开放式非净值型(DictCodeExt1.信披_产品运作方式, "02", "开放式非净值型"),
    产品运作方式_封闭式净值型(DictCodeExt1.信披_产品运作方式, "03", "封闭式净值型"),
    产品运作方式_封闭式非净值型(DictCodeExt1.信披_产品运作方式, "04", "封闭式非净值型"),

    信披产品状态产_运行中(DictCodeExt1.信披_产品状态, "01", "运行中"),
    信披产品状态_已到期(DictCodeExt1.信披_产品状态, "02", "已到期"),
    信披产品状态_已销户(DictCodeExt1.信披_产品状态, "03", "已销户"),

    /*信披是否是分级产品_是(DictCodeExt1.信披_是否是分级产品, "01", "是"),
    信披_是否是分级产品_否(DictCodeExt1.信披_是否是分级产品, "02", "否"),*/

    信披募集方式_公募(DictCodeExt1.信披_募集方式,"01","公募"),
    信披募集方式_私募(DictCodeExt1.信披_募集方式,"02","私募"),

    信披产品类型_净值型产品(DictCodeExt1.信披_产品类型,"N","净值型产品"),
    信披产品类型_现管类产品(DictCodeExt1.信披_产品类型,"M","现管类产品"),

    信披投资性质_固定收益类(DictCodeExt1.信披_投资性质,"01","固定收益类"),
    信披投资性质_权益类(DictCodeExt1.信披_投资性质,"02","权益类"),
    信披投资性质_商品及金融衍生品类(DictCodeExt1.信披_投资性质,"03","商品及衍生品类"),
    信披投资性质_混合类(DictCodeExt1.信披_投资性质,"04","混合类"),

    发行状态_发行成功(DictCodeExt1.信披_发行状态,"01","发行成功"),
    发行状态_发行失败(DictCodeExt1.信披_发行状态,"02","发行失败"),
    发行状态_确认发行(DictCodeExt1.信披_发行状态,"03","确认发行"),

    行情代码_中证500指数收益率(DictCodeExt1.行情代码,"01","中证500指数收益率"),
    行情代码_中债新综合全价指数收益率(DictCodeExt1.行情代码,"02","中债新综合全价指数收益率"),
    行情代码_沪深300指数收益率(DictCodeExt1.行情代码,"03","沪深300指数收益率"),
    行情代码_中债新综合财富指数(DictCodeExt1.行情代码,"04","中债新综合财富指数（1-3年）收益率"),

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
