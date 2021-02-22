/*
 * 文件名：RedisKeyEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.constantrds;

/**
 * Redis存储Key枚举
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月14日
 * @see RedisKeyEnum
 * @since 1.0
 */
public enum RedisKeyEnum {
    /**
     * 风险事件配置-Redis主键
     */
    风险事件配置(
        "RSK{EVENT}"
    ),
    /**
     * 指标信息配置-Redis主键
     */
    指标信息配置(
        "RSK{INDICATOR}INFO"
    ),
    /**
     * 指标参数配置-Redis主键
     */
    指标参数配置(
        "RSK{INDICATOR}PARAM"
    ),
    /**
     * 指标参数值-Redis主键
     */
    指标参数值(
        "RSK{VALUE}:"
    ),
    /**
     * 预占实占信息-Redis主键
     */
    预占实占信息(
        "RSK{OCCUPY}PARAM"
    ),
    /**
     * 组合关联指标配置-Redis主键
     */
    组合关联指标配置(
        "RSK{INDICATOR}PROD"
    ),
    /**
     * 日历信息-Redis主键
     */
    日历信息(
        "RSK{INFO}CALENDAR"
    ),
    /**
     * 产品基本信息-Redis主键
     */
    产品基本信息(
        "RSK{INFO}PROD"
    ),
    /**
     * 市场代码-交易产所关联资产代码-Redis主键
     */
    资产代码索引(
        "RSK{INDEX}ASSET"
    ),
    /**
     * 汇率行情-Redis主键
     */
    汇率行情信息(
        "RSK{INFO}EXCHANGE"
    ),
    /**
     * 基金估值行情-Redis主键
     */
    基金估值行情信息(
        "RSK{INFO}FUNDVAL"
    ),
    /**
     * 资产实时规模信息-Redis主键
     */
    资产实时规模信息(
        "RSK{INFO}ASSETAMOUNT"
    ),
    /**
     * 资产久期附属信息-Redis主键
     */
    资产久期附属信息(
            "RSK{INFO}ASSETDURATION"
    ),
    /**
     * 资产基本信息-Redis主键
     */
    资产基本信息(
        "RSK{INFO}ASSET"
    );

    private String key;

    private RedisKeyEnum(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}
