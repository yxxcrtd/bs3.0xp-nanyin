/*
 * 文件名：DictCode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.common;

import com.joyintech.background.component.common.pack.common.base.BaseDictCode;
import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * 银行间上行服务组件数据字典集中定义枚举
 * 
 * @author Jiaoxujin
 * @version 1.0 2020年5月11日
 * @see IbuDictCode
 * @since 1.0
 */
public enum IbuDictCode implements BaseDictCode {

    /**
     * 银行间上行操作类型
     */
    银行间上行操作类型(
        "EXE_TYPE_IBU"
    ),

    /**
     * 银行间上行报价状态
     */
    银行间上行报价状态(
        "QUOTE_STUTAS_IBU"
    ),

    /**
     * 银行间上行发送状态
     */
    银行间上行发送状态(
        "SEND_TYPE_IBU"
    ),

    /**
     * 银行间上行回复查询方式
     */
    银行间上行回复查询方式(
        "REPLY_QUERY_TYPE_IBU"
    ),

    /**
     * 银行间成交状态
     */
    银行间成交状态(
        "DEAL_TYPE_IBU"
    ),

    /**
     * 报价系统区分类型
     */
    报价系统区分类型(
        "QUOTE_SYSMARK_IBU"
    ),
    
    /**
     * 银行间上行本方角色
     */
    银行间上行本方角色(
        "QUOTE_ROLE_TYPE"
    ),

    /**
     * 银行间上行等待方式
     */
    银行间上行等待方式(
        "WAIT_MODEL_IBU"
    ),
    
    /**
     * 银行间上行发送模式
     */
    银行间上行发送模式(
        "SEND_MODEL_IBU"
    ),

    /**
     * 是否
     */
    是否(
        "YES_OR_NO"
    ),
    
    /**
     * 是否
     */
    银行间上行数据状态(
        "STATUS_VALID"
    );

    /**
     * 对应于数据字典code
     */
    private String value;

    IbuDictCode(String value) {
        this.value = value;
    }

    /**
     * @param value 数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static IbuDictCode get(String value) {
        IbuDictCode[] values = IbuDictCode.values();
        for (IbuDictCode code : values) {
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
    public static IbuDictCode fromValue(String value) {
        for (IbuDictCode dictCode : IbuDictCode.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }
}
