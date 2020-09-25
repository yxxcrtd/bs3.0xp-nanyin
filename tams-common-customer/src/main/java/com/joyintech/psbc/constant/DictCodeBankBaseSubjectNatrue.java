/*
 * 文件名：DictCodeBankBaseSubjectNatrue.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 11, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.psbc.constant;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.util.StringUtil;

/**
 * 基础科目性质数据字典枚举 -- 银行账
 * @author zhangwei
 * @version 1.0 Apr 11, 2019
 * @see DictCodeBankBaseSubjectNatrue
 * @since 1.0
 */
public enum DictCodeBankBaseSubjectNatrue implements IDictCode {

    应收固定管理费(
        "YSGDGLF"
    ),
    
    固定管理费收入(
        "GDGLFSR"
    ),
    
    应收浮动管理费(
        "YSFDGLF"
    ),
    
    浮动管理费收入(
        "FDGLFSR"
    ),
    
    活期存款(
        "HQCK"
    ), 
    ;
    
    /**
     * 对应于数据字典code
     */
    private String value;

    DictCodeBankBaseSubjectNatrue(String value) {
        this.value = value;
    }

    /**
     * @param value 数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static DictCodeBaseSubjectNatrue get(String value) {
        DictCodeBaseSubjectNatrue[] values = DictCodeBaseSubjectNatrue.values();
        for (DictCodeBaseSubjectNatrue code : values) {
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
     * 设置 value
     *
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过value生成枚举
     *
     * @param value value值
     * @return DictCode
     */
    public static DictCodeBaseSubjectNatrue fromValue(String value) {
        for (DictCodeBaseSubjectNatrue dictCode : DictCodeBaseSubjectNatrue.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }
}
