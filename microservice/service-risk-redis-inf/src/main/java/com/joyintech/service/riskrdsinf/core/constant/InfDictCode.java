/*
 * 文件名：InfDictCode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.constant;

import com.joyintech.framework.base.BaseDictCode;

/**
 * 接口数据字典
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月8日
 * @see InfDictCode
 * @since 1.0
 */
public enum InfDictCode implements BaseDictCode {
    /**
     * 操作类型
     */
    操作类型(
        "OP_TYPE"
    ),
    /**
     * 交互系统
     */
    交互系统(
        "INF_CONN_SYS_CODE"
    ),
    /**
     * 处理状态
     */
    处理状态(
        "DO_STATUS"
    ),
    /**
     * 信息类型
     */
    信息类型(
        "STD400MSGT"
    ),
    /**
     * 信息码
     */
    信息码(
        "STD400MGID"
    ),
    /**
     * 报文类型
     */
    报文类型(
        "STDMSGTYPE"
    ),
    /**
     * 文件通知类型
     */
    文件通知类型(
        "CALL_TYPE"
    ),
    /**
     * 文件通知处理状态
     */
    文件通知处理状态(
        "DEAL_STATUS"
    ),
    /**
     * 投资性质
     */
    投资性质(
        "INF_INVEST_TYPE"
    ),
    /**
     * 开放类型
     */
    开放类型(
        "INF_ISSUE_MODE"
    ),
    /**
     * 开放频率
     */
    开放频率(
        "INF_OPEN_TYPE"
    ),
    /**
     * 募集方式
     */
    募集方式(
        "INF_RAISE_TYPE"
    ),
    /**
     * 产品收益类型
     */
    产品收益类型(
        "INF_PROD_TYPE"
    ),
    /**
     * 交易方向
     */
    交易方向(
        "INF_PS"
    ),
    /**
     * 监督状态
     */
    监督状态(
        "INF_SUP_STAT"
    ),
    /**
     * 资产大类
     */
    资产大类(
        "INF_ASSET_TYPE"
    ),
    /**
     * 事件类型
     */
    事件类型(
        "INF_EVENT_TYPE"
    );

    private String value;

    private InfDictCode(String value) {
        setValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

}
