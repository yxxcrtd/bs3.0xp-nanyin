/*
 * 文件名：DictCodeList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.common.pack.common.constant;

import com.joyintech.background.component.common.pack.common.base.BaseDictCode;
import com.joyintech.background.component.common.pack.common.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 框架的数据字典（和各个业务子系统无关）
 * @author 何阳阳
 * @version 1.0 2018年1月17日
 * @see DictCodeList
 * @since 1.0
 */
public enum DictCodeList implements com.joyintech.background.component.common.pack.common.base.BaseDictCodeList {
	
    /**
     *A：新建中、N：新建、U：修改中、M：修改、P：删除中、D：删除、R：作废。
     */
    数据状态_新建(BaseDictCode.数据状态, "N", "新建"),

   /**
    *
    */
    数据状态_新建中(BaseDictCode.数据状态, "A", "新建中"),
   
   /**
    *
    */
    数据状态_删除中(BaseDictCode.数据状态, "P", "删除中"),
   
   /**
    *
    */
    数据状态_删除(BaseDictCode.数据状态, "D", "删除"),
   
   /**
    * 数据状态_修改中
    */
    数据状态_修改中(BaseDictCode.数据状态,"U","修改中"),
  
   /**
    * 数据状态_修改中
    */
    数据状态_修改(BaseDictCode.数据状态,"M","修改"),

   /**
    *
    */
   数据状态_作废(BaseDictCode.数据状态, "R", "作废"),

   /**
    *
    */
   是否_是(BaseDictCode.是否, "01", "是"),

   /**
    *
    */
   是否_否(BaseDictCode.是否, "02", "否"),

    ;

    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private BaseDictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeList(BaseDictCode dictCode, String value, String codeName) {
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
    public static DictCodeList from(BaseDictCode dictCode, String value) {
        DictCodeList[] values = DictCodeList.values();
        for (DictCodeList code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode().equals(dictCode)) {
                return code;
            }
        }
        return null;
    }

    /**
     * 获取数据字典明细valueList
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<String> valuesfromDictCode(BaseDictCode dictCode) {
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
    public BaseDictCode getDictCode() {
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
