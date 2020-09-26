/*
 * 文件名：DictCodeInterf.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.constant;

import com.joyintech.framework.util.StringUtil;

public enum DictCodeInterf {
    /**
     * 接口发送接收状态
     */
    接口发送接收状态("INTERF_RS_STATUS"),
    
    /**
     * 接口对接系统ID
     */
    接口对接系统ID("INTERF_SYSTEMID"),
    
    /**
     * 接口服务器类型
     */
    接口服务器类型("INTERF_SERVERTYPE"),
    /**
     * 文件解析功能启动源
     */
    文件解析功能启动源("CALL_SOURCE"),
    /**
     * 接口文件类型
     */
    接口文件类型("TMP_TYPE"),
    
    /**
     * 接口执行状态
     */
    接口执行状态("INTERF_EXEC_STATUS"),
    /**
     * 接口执行状态
     */
    中债估值交易场所("CB_VAL_MKTCODE"),
    
   
    ;
    /**
     * 对应于数据字典code
     */
    private String value;

    DictCodeInterf(String value) {
        this.value = value;
    }

    /**
     * @param value 数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static DictCodeInterf get(String value) {
        DictCodeInterf[] values = DictCodeInterf.values();
        for (DictCodeInterf code : values) {
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
    public static DictCodeInterf fromValue(String value) {
        for (DictCodeInterf dictCode : DictCodeInterf.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }
}
